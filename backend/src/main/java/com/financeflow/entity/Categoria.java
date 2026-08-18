package com.financeflow.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity @Table(name="categorias", uniqueConstraints=@UniqueConstraint(columnNames={"nome","tipo"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Categoria {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @Column(nullable=false, length=60) private String nome;
  @Enumerated(EnumType.STRING) @Column(nullable=false, length=10) private TipoTransacao tipo;
}

