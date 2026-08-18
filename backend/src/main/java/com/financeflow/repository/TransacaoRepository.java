package com.financeflow.repository;
import com.financeflow.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
public interface TransacaoRepository extends JpaRepository<Transacao, Long>, JpaSpecificationExecutor<Transacao> {
  List<Transacao> findTop5ByOrderByDataDescDataCriacaoDesc();
  @Query("select coalesce(sum(t.valor),0) from Transacao t where t.tipo=:tipo") BigDecimal totalPorTipo(@Param("tipo") TipoTransacao tipo);
  @Query("select coalesce(sum(t.valor),0) from Transacao t where t.tipo=:tipo and t.data between :inicio and :fim") BigDecimal totalPorTipoPeriodo(@Param("tipo") TipoTransacao tipo,@Param("inicio") LocalDate inicio,@Param("fim") LocalDate fim);
  @Query("select coalesce(sum(t.valor),0) from Transacao t where t.tipo=com.financeflow.entity.TipoTransacao.DESPESA and t.categoria.id=:categoriaId and t.data between :inicio and :fim") BigDecimal gastosCategoria(@Param("categoriaId") Long categoriaId,@Param("inicio") LocalDate inicio,@Param("fim") LocalDate fim);
  @Query("select t.categoria.id,t.categoria.nome,sum(t.valor) from Transacao t where t.tipo=:tipo and t.data between :inicio and :fim group by t.categoria.id,t.categoria.nome order by sum(t.valor) desc") List<Object[]> totaisPorCategoria(@Param("tipo") TipoTransacao tipo,@Param("inicio") LocalDate inicio,@Param("fim") LocalDate fim);
  @Query("select year(t.data),month(t.data),t.tipo,sum(t.valor) from Transacao t where t.data>=:inicio group by year(t.data),month(t.data),t.tipo order by year(t.data),month(t.data)") List<Object[]> evolucaoDesde(@Param("inicio") LocalDate inicio);
}

