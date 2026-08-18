package com.financeflow.service;
import com.financeflow.dto.*;
import com.financeflow.entity.TipoTransacao;
import com.financeflow.mapper.FinanceMapper;
import com.financeflow.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
@Service @RequiredArgsConstructor @Transactional(readOnly=true)
public class DashboardService {
  private final TransacaoRepository repository; private final FinanceMapper mapper;
  public DashboardResumo resumo(int mes,int ano){LocalDate i=LocalDate.of(ano,mes,1),f=i.withDayOfMonth(i.lengthOfMonth());BigDecimal r=repository.totalPorTipo(TipoTransacao.RECEITA),d=repository.totalPorTipo(TipoTransacao.DESPESA),rm=repository.totalPorTipoPeriodo(TipoTransacao.RECEITA,i,f),dm=repository.totalPorTipoPeriodo(TipoTransacao.DESPESA,i,f);return new DashboardResumo(r.subtract(d),r,d,rm.subtract(dm));}
  public List<ValorPorCategoria> porCategoria(TipoTransacao tipo,int mes,int ano){LocalDate i=LocalDate.of(ano,mes,1);return repository.totaisPorCategoria(tipo,i,i.withDayOfMonth(i.lengthOfMonth())).stream().map(x->new ValorPorCategoria((Long)x[0],(String)x[1],(BigDecimal)x[2])).toList();}
  public List<TransacaoResponse> ultimas(){return repository.findTop5ByOrderByDataDescDataCriacaoDesc().stream().map(mapper::toDto).toList();}
  public List<EvolucaoMensal> evolucao(){Map<String,BigDecimal[]> dados=new LinkedHashMap<>();for(Object[] x:repository.evolucaoDesde(LocalDate.now().minusMonths(11).withDayOfMonth(1))){String k=x[0]+"-"+x[1];BigDecimal[] v=dados.computeIfAbsent(k,z->new BigDecimal[]{BigDecimal.ZERO,BigDecimal.ZERO});v[x[2]==TipoTransacao.RECEITA?0:1]=(BigDecimal)x[3];}return dados.entrySet().stream().map(e->{String[] p=e.getKey().split("-");return new EvolucaoMensal(Integer.valueOf(p[0]),Integer.valueOf(p[1]),e.getValue()[0],e.getValue()[1],e.getValue()[0].subtract(e.getValue()[1]));}).toList();}
}
