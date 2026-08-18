package com.financeflow.service;
import com.financeflow.dto.*;
import com.financeflow.entity.*;
import com.financeflow.exception.RegraNegocioException;
import com.financeflow.mapper.FinanceMapper;
import com.financeflow.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.*;
import java.time.*;
import java.util.List;
@Service @RequiredArgsConstructor
public class OrcamentoService {
  private final OrcamentoRepository repository; private final TransacaoRepository transacoes; private final CategoriaService categorias; private final FinanceMapper mapper;
  @Transactional(readOnly=true) public List<OrcamentoResponse> listar(int mes,int ano){return repository.findByMesAndAnoOrderByCategoriaNome(mes,ano).stream().map(this::resposta).toList();}
  @Transactional public OrcamentoResponse salvar(OrcamentoRequest dto){Categoria c=categorias.buscar(dto.categoriaId()); if(c.getTipo()!=TipoTransacao.DESPESA)throw new RegraNegocioException("Orçamentos só podem usar categorias de despesa."); OrcamentoMensal o=repository.findByCategoriaIdAndMesAndAno(c.getId(),dto.mes(),dto.ano()).orElseGet(OrcamentoMensal::new);o.setCategoria(c);o.setLimite(dto.limite());o.setMes(dto.mes());o.setAno(dto.ano());return resposta(repository.save(o));}
  @Transactional public void excluir(Long id){repository.deleteById(id);}
  private OrcamentoResponse resposta(OrcamentoMensal o){LocalDate inicio=LocalDate.of(o.getAno(),o.getMes(),1);BigDecimal gasto=transacoes.gastosCategoria(o.getCategoria().getId(),inicio,inicio.withDayOfMonth(inicio.lengthOfMonth()));BigDecimal percentual=gasto.multiply(BigDecimal.valueOf(100)).divide(o.getLimite(),2,RoundingMode.HALF_UP);return new OrcamentoResponse(o.getId(),mapper.toDto(o.getCategoria()),o.getLimite(),o.getMes(),o.getAno(),gasto,percentual,o.getLimite().subtract(gasto));}
}

