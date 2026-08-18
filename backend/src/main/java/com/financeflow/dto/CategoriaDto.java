package com.financeflow.dto;
import com.financeflow.entity.TipoTransacao;
import jakarta.validation.constraints.*;
public record CategoriaDto(Long id, @NotBlank @Size(max=60) String nome, @NotNull TipoTransacao tipo) {}

