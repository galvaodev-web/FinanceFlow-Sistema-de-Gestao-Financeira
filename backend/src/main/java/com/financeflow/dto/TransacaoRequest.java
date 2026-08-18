package com.financeflow.dto;
import com.financeflow.entity.TipoTransacao;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
public record TransacaoRequest(@NotBlank @Size(max=120) String descricao, @NotNull @Positive BigDecimal valor,
  @NotNull TipoTransacao tipo, @NotNull Long categoriaId, @NotNull LocalDate data, @Size(max=500) String observacao) {}

