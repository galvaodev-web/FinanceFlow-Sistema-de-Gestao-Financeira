package com.financeflow.dto;
import com.financeflow.entity.TipoTransacao;
import java.math.BigDecimal;
import java.time.*;
public record TransacaoResponse(Long id, String descricao, BigDecimal valor, TipoTransacao tipo,
  CategoriaDto categoria, LocalDate data, String observacao, LocalDateTime dataCriacao) {}

