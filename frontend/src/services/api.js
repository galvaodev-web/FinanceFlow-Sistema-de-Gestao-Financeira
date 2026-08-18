const BASE_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080/api';
const DEMO_MODE = import.meta.env.VITE_DEMO_MODE === 'true';
async function request(path, options = {}) {
  const response = await fetch(`${BASE_URL}${path}`, { headers: { 'Content-Type': 'application/json', ...options.headers }, ...options });
  if (!response.ok) { const error = await response.json().catch(() => ({})); throw new Error(error.mensagem || 'Não foi possível concluir a operação.'); }
  return response.status === 204 ? null : response.json();
}
const query = params => { const q = new URLSearchParams(Object.entries(params).filter(([,v]) => v !== '' && v != null)); return q.size ? `?${q}` : ''; };
const remoteApi = {
  dashboard: () => Promise.all([request('/dashboard/resumo'), request('/dashboard/despesas-por-categoria'), request('/dashboard/evolucao-mensal'), request('/dashboard/ultimas-transacoes')]),
  transacoes: filters => request(`/transacoes${query(filters)}`),
  salvarTransacao: (data, id) => request(`/transacoes${id ? `/${id}` : ''}`, { method: id ? 'PUT' : 'POST', body: JSON.stringify(data) }),
  excluirTransacao: id => request(`/transacoes/${id}`, { method: 'DELETE' }),
  categorias: () => request('/categorias'),
  criarCategoria: data => request('/categorias', { method: 'POST', body: JSON.stringify(data) }),
  excluirCategoria: id => request(`/categorias/${id}`, { method: 'DELETE' }),
  orcamentos: (mes, ano) => request(`/orcamentos?mes=${mes}&ano=${ano}`),
  salvarOrcamento: data => request('/orcamentos', { method: 'POST', body: JSON.stringify(data) }),
  excluirOrcamento: id => request(`/orcamentos/${id}`, { method: 'DELETE' })
};

const initialCategories = [
  [1, 'Salário', 'RECEITA'], [2, 'Freelance', 'RECEITA'], [3, 'Investimentos', 'RECEITA'],
  [4, 'Alimentação', 'DESPESA'], [5, 'Transporte', 'DESPESA'], [6, 'Moradia', 'DESPESA'],
  [7, 'Saúde', 'DESPESA'], [8, 'Lazer', 'DESPESA']
].map(([id, nome, tipo]) => ({ id, nome, tipo }));

const today = new Date();
const isoDate = day => new Date(today.getFullYear(), today.getMonth(), day).toISOString().slice(0, 10);
const initialTransactions = [
  { id: 1, descricao: 'Salário mensal', valor: 5200, tipo: 'RECEITA', categoriaId: 1, data: isoDate(5), observacao: '' },
  { id: 2, descricao: 'Aluguel', valor: 1450, tipo: 'DESPESA', categoriaId: 6, data: isoDate(8), observacao: '' },
  { id: 3, descricao: 'Supermercado', valor: 486.7, tipo: 'DESPESA', categoriaId: 4, data: isoDate(12), observacao: '' },
  { id: 4, descricao: 'Projeto freelance', valor: 1250, tipo: 'RECEITA', categoriaId: 2, data: isoDate(15), observacao: '' },
  { id: 5, descricao: 'Transporte', valor: 184.5, tipo: 'DESPESA', categoriaId: 5, data: isoDate(17), observacao: '' }
];

const read = (key, fallback) => JSON.parse(localStorage.getItem(`financeflow:${key}`) || JSON.stringify(fallback));
const write = (key, value) => (localStorage.setItem(`financeflow:${key}`, JSON.stringify(value)), value);
const categories = () => read('categories', initialCategories);
const transactions = () => read('transactions', initialTransactions);
const expand = item => ({ ...item, categoria: categories().find(c => c.id === Number(item.categoriaId)), dataCriacao: `${item.data}T12:00:00` });
const monthItems = () => transactions().filter(t => { const date = new Date(`${t.data}T00:00:00`); return date.getMonth() === today.getMonth() && date.getFullYear() === today.getFullYear(); });
const sum = (items, type) => items.filter(x => x.tipo === type).reduce((total, x) => total + Number(x.valor), 0);
const byCategory = type => Object.values(monthItems().filter(x => x.tipo === type).reduce((map, item) => { const category = categories().find(c => c.id === Number(item.categoriaId)); map[item.categoriaId] ||= { categoriaId: Number(item.categoriaId), categoria: category?.nome, valor: 0 }; map[item.categoriaId].valor += Number(item.valor); return map; }, {}));
const demoApi = {
  dashboard: async () => { const all = transactions(), current = monthItems(), receitas = sum(all, 'RECEITA'), despesas = sum(all, 'DESPESA'); return [{ saldoAtual: receitas - despesas, totalReceitas: receitas, totalDespesas: despesas, resultadoMes: sum(current, 'RECEITA') - sum(current, 'DESPESA') }, byCategory('DESPESA'), [], [...all].sort((a,b) => b.data.localeCompare(a.data)).slice(0,5).map(expand)]; },
  transacoes: async filters => transactions().filter(t => (!filters.tipo || t.tipo === filters.tipo) && (!filters.categoriaId || Number(t.categoriaId) === Number(filters.categoriaId)) && (!filters.texto || t.descricao.toLowerCase().includes(filters.texto.toLowerCase()))).sort((a,b) => b.data.localeCompare(a.data)).map(expand),
  salvarTransacao: async (data, id) => { const items = transactions(); const saved = { ...data, id: id || Math.max(0, ...items.map(x => x.id)) + 1 }; write('transactions', id ? items.map(x => x.id === id ? saved : x) : [...items, saved]); return expand(saved); },
  excluirTransacao: async id => write('transactions', transactions().filter(x => x.id !== id)),
  categorias: async () => categories(),
  criarCategoria: async data => { const items = categories(), saved = { ...data, id: Math.max(0, ...items.map(x => x.id)) + 1 }; write('categories', [...items, saved]); return saved; },
  excluirCategoria: async id => write('categories', categories().filter(x => x.id !== id)),
  orcamentos: async (mes, ano) => read('budgets', []).filter(x => x.mes === mes && x.ano === ano).map(x => { const gasto = transactions().filter(t => t.tipo === 'DESPESA' && Number(t.categoriaId) === Number(x.categoriaId) && new Date(`${t.data}T00:00:00`).getMonth()+1 === mes && new Date(`${t.data}T00:00:00`).getFullYear() === ano).reduce((v,t) => v+Number(t.valor),0); const limite=Number(x.limite); return {...x,categoria:categories().find(c=>c.id===Number(x.categoriaId)),valorGasto:gasto,percentualUtilizado:(gasto/limite*100).toFixed(2),valorRestante:limite-gasto}; }),
  salvarOrcamento: async data => { const items=read('budgets',[]), found=items.find(x=>Number(x.categoriaId)===Number(data.categoriaId)&&x.mes===data.mes&&x.ano===data.ano), saved={...data,id:found?.id||Math.max(0,...items.map(x=>x.id))+1}; write('budgets',found?items.map(x=>x.id===found.id?saved:x):[...items,saved]); return saved; },
  excluirOrcamento: async id => write('budgets', read('budgets',[]).filter(x => x.id !== id))
};

export const api = DEMO_MODE ? demoApi : remoteApi;
