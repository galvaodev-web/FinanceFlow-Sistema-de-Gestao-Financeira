package com.financeflow.repository;
import com.financeflow.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
  List<Categoria> findAllByOrderByNomeAsc();
  boolean existsByNomeIgnoreCaseAndTipo(String nome, TipoTransacao tipo);
}

