package com.financeflow.service;
import com.financeflow.dto.CategoriaDto;
import com.financeflow.entity.Categoria;
import com.financeflow.exception.*;
import com.financeflow.mapper.FinanceMapper;
import com.financeflow.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service @RequiredArgsConstructor
public class CategoriaService {
  private final CategoriaRepository repository; private final FinanceMapper mapper;
  @Transactional(readOnly=true) public List<CategoriaDto> listar(){return repository.findAllByOrderByNomeAsc().stream().map(mapper::toDto).toList();}
  @Transactional public CategoriaDto criar(CategoriaDto dto){
    if(repository.existsByNomeIgnoreCaseAndTipo(dto.nome().trim(),dto.tipo())) throw new RegraNegocioException("Já existe uma categoria com esse nome e tipo.");
    return mapper.toDto(repository.save(Categoria.builder().nome(dto.nome().trim()).tipo(dto.tipo()).build()));
  }
  @Transactional public void excluir(Long id){if(!repository.existsById(id)) throw new RecursoNaoEncontradoException("Categoria não encontrada."); repository.deleteById(id);}
  public Categoria buscar(Long id){return repository.findById(id).orElseThrow(()->new RecursoNaoEncontradoException("Categoria não encontrada."));}
}

