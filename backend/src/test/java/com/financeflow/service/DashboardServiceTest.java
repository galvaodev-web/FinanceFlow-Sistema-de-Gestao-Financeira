package com.financeflow.service;
import com.financeflow.dto.DashboardResumo;
import com.financeflow.entity.TipoTransacao;
import com.financeflow.mapper.FinanceMapper;
import com.financeflow.repository.TransacaoRepository;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
class DashboardServiceTest {
  @Test void deveCalcularResumo(){TransacaoRepository r=mock(TransacaoRepository.class);when(r.totalPorTipo(TipoTransacao.RECEITA)).thenReturn(new BigDecimal("5000"));when(r.totalPorTipo(TipoTransacao.DESPESA)).thenReturn(new BigDecimal("1800"));when(r.totalPorTipoPeriodo(eq(TipoTransacao.RECEITA),any(),any())).thenReturn(new BigDecimal("3000"));when(r.totalPorTipoPeriodo(eq(TipoTransacao.DESPESA),any(),any())).thenReturn(new BigDecimal("900"));DashboardResumo x=new DashboardService(r,new FinanceMapper()).resumo(8,2026);assertEquals(new BigDecimal("3200"),x.saldoAtual());assertEquals(new BigDecimal("2100"),x.resultadoMes());}
}
