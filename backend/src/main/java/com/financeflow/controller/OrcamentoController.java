package com.financeflow.controller;
import com.financeflow.dto.*;
import com.financeflow.service.OrcamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
@RestController @RequestMapping("/api/orcamentos") @RequiredArgsConstructor
public class OrcamentoController {
  private final OrcamentoService service;
  @GetMapping public List<OrcamentoResponse> listar(@RequestParam(required=false) Integer mes,@RequestParam(required=false) Integer ano){LocalDate h=LocalDate.now();return service.listar(mes==null?h.getMonthValue():mes,ano==null?h.getYear():ano);}
  @PostMapping @ResponseStatus(HttpStatus.CREATED) public OrcamentoResponse salvar(@Valid @RequestBody OrcamentoRequest dto){return service.salvar(dto);}
  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void excluir(@PathVariable Long id){service.excluir(id);}
}

