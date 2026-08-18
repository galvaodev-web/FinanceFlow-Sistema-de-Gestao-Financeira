package com.financeflow.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.*;
@Entity @Table(name="transacoes") @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Transacao {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @Column(nullable=false, length=120) private String descricao;
  @Column(nullable=false, precision=15, scale=2) private BigDecimal valor;
  @Enumerated(EnumType.STRING) @Column(nullable=false) private TipoTransacao tipo;
  @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="categoria_id") private Categoria categoria;
  @Column(nullable=false) private LocalDate data;
  @Column(length=500) private String observacao;
  @Column(nullable=false, updatable=false) private LocalDateTime dataCriacao;
  @PrePersist void prePersist() { if (dataCriacao == null) dataCriacao = LocalDateTime.now(); }
}

