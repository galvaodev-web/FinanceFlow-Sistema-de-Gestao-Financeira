import { X } from 'lucide-react';
export function Button({ children, variant = 'primary', ...props }) { return <button className={`button ${variant}`} {...props}>{children}</button>; }
export function Field({ label, as = 'input', children, ...props }) { const Tag = as; return <label className="field"><span>{label}</span><Tag {...props}>{children}</Tag></label>; }
export function Badge({ type }) { return <span className={`badge ${type.toLowerCase()}`}>{type === 'RECEITA' ? 'Receita' : 'Despesa'}</span>; }
export function Modal({ title, onClose, children }) { return <div className="modal-backdrop" onMouseDown={onClose}><section className="modal" onMouseDown={e=>e.stopPropagation()}><header><h2>{title}</h2><button className="icon-button" onClick={onClose}><X size={18}/></button></header>{children}</section></div>; }
export function State({ loading, error, empty, children }) { if (loading) return <div className="state">Carregando dados...</div>; if (error) return <div className="state error">{error}</div>; if (empty) return <div className="state">Nenhum registro encontrado.</div>; return children; }

