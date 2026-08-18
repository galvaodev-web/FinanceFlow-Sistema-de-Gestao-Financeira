package com.financeflow.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
@Entity @Table(name="orcamentos_mensais", uniqueConstraints=@UniqueConstraint(columnNames={"categoria_id","mes","ano"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrcamentoMensal {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="categoria_id") private Categoria categoria;
  @Column(nullable=false, precision=15, scale=2) private BigDecimal limite;
  @Column(nullable=false) private Integer mes;
  @Column(nullable=false) private Integer ano;
}

