package com.financeflow.controller;
import com.financeflow.dto.*;
import com.financeflow.entity.TipoTransacao;
import com.financeflow.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
@RestController @RequestMapping("/api/dashboard") @RequiredArgsConstructor
public class DashboardController {
  private final DashboardService service;
  @GetMapping("/resumo") public DashboardResumo resumo(@RequestParam(required=false) Integer mes,@RequestParam(required=false) Integer ano){LocalDate h=LocalDate.now();return service.resumo(mes==null?h.getMonthValue():mes,ano==null?h.getYear():ano);}
  @GetMapping("/despesas-por-categoria") public List<ValorPorCategoria> despesas(@RequestParam(required=false) Integer mes,@RequestParam(required=false) Integer ano){return categorias(TipoTransacao.DESPESA,mes,ano);}
  @GetMapping("/receitas-por-categoria") public List<ValorPorCategoria> receitas(@RequestParam(required=false) Integer mes,@RequestParam(required=false) Integer ano){return categorias(TipoTransacao.RECEITA,mes,ano);}
  @GetMapping("/ultimas-transacoes") public List<TransacaoResponse> ultimas(){return service.ultimas();}
  @GetMapping("/evolucao-mensal") public List<EvolucaoMensal> evolucao(){return service.evolucao();}
  private List<ValorPorCategoria> categorias(TipoTransacao tipo,Integer mes,Integer ano){LocalDate h=LocalDate.now();return service.porCategoria(tipo,mes==null?h.getMonthValue():mes,ano==null?h.getYear():ano);}
}

