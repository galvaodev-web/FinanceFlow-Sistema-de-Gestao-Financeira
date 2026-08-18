package com.financeflow.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record OrcamentoRequest(@NotNull Long categoriaId, @NotNull @Positive BigDecimal limite,
  @NotNull @Min(1) @Max(12) Integer mes, @NotNull @Min(2000) Integer ano) {}

