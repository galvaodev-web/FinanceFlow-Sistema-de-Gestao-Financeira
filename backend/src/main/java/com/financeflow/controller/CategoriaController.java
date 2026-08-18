package com.financeflow.controller;
import com.financeflow.dto.CategoriaDto;
import com.financeflow.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/categorias") @RequiredArgsConstructor
public class CategoriaController {
  private final CategoriaService service;
  @GetMapping @Operation(summary="Lista as categorias") public List<CategoriaDto> listar(){return service.listar();}
  @PostMapping @ResponseStatus(HttpStatus.CREATED) @Operation(summary="Cria uma categoria") public CategoriaDto criar(@Valid @RequestBody CategoriaDto dto){return service.criar(dto);}
  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void excluir(@PathVariable Long id){service.excluir(id);}
}

