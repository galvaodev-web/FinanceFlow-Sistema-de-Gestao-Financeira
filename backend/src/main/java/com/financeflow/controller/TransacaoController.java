package com.financeflow.controller;
import com.financeflow.dto.*;
import com.financeflow.entity.TipoTransacao;
import com.financeflow.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
@RestController @RequestMapping("/api/transacoes") @RequiredArgsConstructor
public class TransacaoController {
  private final TransacaoService service;
  @GetMapping @Operation(summary="Lista e filtra transações") public List<TransacaoResponse> listar(@RequestParam(required=false) TipoTransacao tipo,@RequestParam(required=false) Long categoriaId,@RequestParam(required=false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate dataInicial,@RequestParam(required=false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate dataFinal,@RequestParam(required=false) Integer mes,@RequestParam(required=false) Integer ano,@RequestParam(required=false) String texto){return service.listar(tipo,categoriaId,dataInicial,dataFinal,mes,ano,texto);}
  @PostMapping @ResponseStatus(HttpStatus.CREATED) @Operation(summary="Cria uma transação") public TransacaoResponse criar(@Valid @RequestBody TransacaoRequest dto){return service.criar(dto);}
  @PutMapping("/{id}") public TransacaoResponse atualizar(@PathVariable Long id,@Valid @RequestBody TransacaoRequest dto){return service.atualizar(id,dto);}
  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void excluir(@PathVariable Long id){service.excluir(id);}
}

