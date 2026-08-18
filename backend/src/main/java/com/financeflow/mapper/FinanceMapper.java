package com.financeflow.mapper;
import com.financeflow.dto.*;
import com.financeflow.entity.*;
import org.springframework.stereotype.Component;
@Component
public class FinanceMapper {
  public CategoriaDto toDto(Categoria c) { return new CategoriaDto(c.getId(), c.getNome(), c.getTipo()); }
  public TransacaoResponse toDto(Transacao t) { return new TransacaoResponse(t.getId(), t.getDescricao(), t.getValor(), t.getTipo(), toDto(t.getCategoria()), t.getData(), t.getObservacao(), t.getDataCriacao()); }
}

