package com.financeflow.service;
import com.financeflow.dto.*;
import com.financeflow.entity.*;
import com.financeflow.exception.RegraNegocioException;
import com.financeflow.mapper.FinanceMapper;
import com.financeflow.repository.TransacaoRepository;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class TransacaoServiceTest {
  @Mock TransacaoRepository repository; @Mock CategoriaService categorias; TransacaoService service;
  @BeforeEach void setup(){MockitoAnnotations.openMocks(this);service=new TransacaoService(repository,categorias,new FinanceMapper());}
  @Test void deveCriarTransacaoValida(){Categoria c=Categoria.builder().id(1L).nome("Salário").tipo(TipoTransacao.RECEITA).build();when(categorias.buscar(1L)).thenReturn(c);when(repository.save(any())).thenAnswer(i->{Transacao t=i.getArgument(0);t.setId(10L);return t;});TransacaoResponse result=service.criar(new TransacaoRequest("Pagamento",new BigDecimal("1000"),TipoTransacao.RECEITA,1L,LocalDate.now(),null));assertEquals(10L,result.id());assertEquals(new BigDecimal("1000"),result.valor());}
  @Test void deveRejeitarCategoriaDeTipoDiferente(){Categoria c=Categoria.builder().id(1L).tipo(TipoTransacao.DESPESA).build();when(categorias.buscar(1L)).thenReturn(c);assertThrows(RegraNegocioException.class,()->service.criar(new TransacaoRequest("Pagamento",BigDecimal.TEN,TipoTransacao.RECEITA,1L,LocalDate.now(),null)));verify(repository,never()).save(any());}
}

