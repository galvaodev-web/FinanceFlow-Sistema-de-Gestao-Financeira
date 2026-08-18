package com.financeflow.dto;
import java.math.BigDecimal;
public record OrcamentoResponse(Long id, CategoriaDto categoria, BigDecimal limite, Integer mes, Integer ano,
  BigDecimal valorGasto, BigDecimal percentualUtilizado, BigDecimal valorRestante) {}

