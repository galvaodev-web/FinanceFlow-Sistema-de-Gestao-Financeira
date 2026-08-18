package com.financeflow.config;
import com.financeflow.entity.*;
import com.financeflow.repository.CategoriaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import java.util.List;
@Configuration
public class DataInitializer {
  @Bean CommandLineRunner seedCategorias(CategoriaRepository repository){return args->{if(repository.count()>0)return;repository.saveAll(List.of(
    categoria("Salário",TipoTransacao.RECEITA),categoria("Freelance",TipoTransacao.RECEITA),categoria("Investimentos",TipoTransacao.RECEITA),categoria("Alimentação",TipoTransacao.DESPESA),categoria("Transporte",TipoTransacao.DESPESA),categoria("Moradia",TipoTransacao.DESPESA),categoria("Saúde",TipoTransacao.DESPESA),categoria("Lazer",TipoTransacao.DESPESA)));};}
  private Categoria categoria(String nome,TipoTransacao tipo){return Categoria.builder().nome(nome).tipo(tipo).build();}
}

