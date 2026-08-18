const BASE_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080/api';
async function request(path, options = {}) {
  const response = await fetch(`${BASE_URL}${path}`, { headers: { 'Content-Type': 'application/json', ...options.headers }, ...options });
  if (!response.ok) { const error = await response.json().catch(() => ({})); throw new Error(error.mensagem || 'Não foi possível concluir a operação.'); }
  return response.status === 204 ? null : response.json();
}
const query = params => { const q = new URLSearchParams(Object.entries(params).filter(([,v]) => v !== '' && v != null)); return q.size ? `?${q}` : ''; };
export const api = {
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

