package com.financeflow.repository;
import com.financeflow.entity.OrcamentoMensal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface OrcamentoRepository extends JpaRepository<OrcamentoMensal, Long> {
  List<OrcamentoMensal> findByMesAndAnoOrderByCategoriaNome(Integer mes,Integer ano);
  Optional<OrcamentoMensal> findByCategoriaIdAndMesAndAno(Long categoriaId,Integer mes,Integer ano);
}

