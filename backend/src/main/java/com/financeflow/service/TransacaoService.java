package com.financeflow.service;
import com.financeflow.dto.*;
import com.financeflow.entity.*;
import com.financeflow.exception.*;
import com.financeflow.mapper.FinanceMapper;
import com.financeflow.repository.TransacaoRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.*;
@Service @RequiredArgsConstructor
public class TransacaoService {
  private final TransacaoRepository repository; private final CategoriaService categoriaService; private final FinanceMapper mapper;
  @Transactional(readOnly=true) public List<TransacaoResponse> listar(TipoTransacao tipo,Long categoriaId,LocalDate inicio,LocalDate fim,Integer mes,Integer ano,String texto){
    return repository.findAll((root,q,cb)->{List<Predicate> p=new ArrayList<>();
      if(tipo!=null)p.add(cb.equal(root.get("tipo"),tipo)); if(categoriaId!=null)p.add(cb.equal(root.get("categoria").get("id"),categoriaId));
      if(inicio!=null)p.add(cb.greaterThanOrEqualTo(root.get("data"),inicio)); if(fim!=null)p.add(cb.lessThanOrEqualTo(root.get("data"),fim));
      if(mes!=null)p.add(cb.equal(cb.function("month",Integer.class,root.get("data")),mes)); if(ano!=null)p.add(cb.equal(cb.function("year",Integer.class,root.get("data")),ano));
      if(texto!=null&&!texto.isBlank())p.add(cb.like(cb.lower(root.get("descricao")),"%"+texto.toLowerCase()+"%")); return cb.and(p.toArray(Predicate[]::new));
    },Sort.by(Sort.Direction.DESC,"data","dataCriacao")).stream().map(mapper::toDto).toList();
  }
  @Transactional public TransacaoResponse criar(TransacaoRequest dto){Transacao t=new Transacao(); atualizar(t,dto); return mapper.toDto(repository.save(t));}
  @Transactional public TransacaoResponse atualizar(Long id,TransacaoRequest dto){Transacao t=buscar(id); atualizar(t,dto); return mapper.toDto(repository.save(t));}
  @Transactional public void excluir(Long id){repository.delete(buscar(id));}
  private Transacao buscar(Long id){return repository.findById(id).orElseThrow(()->new RecursoNaoEncontradoException("Transação não encontrada."));}
  private void atualizar(Transacao t,TransacaoRequest dto){Categoria c=categoriaService.buscar(dto.categoriaId()); if(c.getTipo()!=dto.tipo()) throw new RegraNegocioException("A categoria deve ser do mesmo tipo da transação."); t.setDescricao(dto.descricao().trim());t.setValor(dto.valor());t.setTipo(dto.tipo());t.setCategoria(c);t.setData(dto.data());t.setObservacao(dto.observacao());}
}

