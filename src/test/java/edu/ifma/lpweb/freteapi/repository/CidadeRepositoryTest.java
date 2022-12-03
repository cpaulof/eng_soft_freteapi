package edu.ifma.lpweb.freteapi.repository;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;

import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import antlr.StringUtils;
import edu.ifma.lpweb.freteapi.model.Cidade;
import edu.ifma.lpweb.freteapi.model.Cliente;
import edu.ifma.lpweb.freteapi.model.Frete;

@DataJpaTest
public class CidadeRepositoryTest {
    
    @Autowired
    private CidadeRepository cidadeRepository;

    @BeforeEach
    void initEach(){
    }

    @AfterEach
    public void cleanEach(){
    }

    // @NotEmpty(message="Campo nome e obrigatorio")
    // private String nome;

    // @NotEmpty(message="Campo UF e obrigatorio")
    // private String uf;

    // @Min(value=1, message="Campo taxa nao e valido")
    // private BigDecimal taxa;

    @Test
    public void salvarComNomeNullLancaExcecao() throws Exception{
      Cidade cidade = new Cidade(null, "SP", BigDecimal.valueOf(50));

      ConstraintViolationException exception =
			  assertThrows(ConstraintViolationException.class,
				() -> {  cidadeRepository.save(cidade);
					  },
				"Deveria lançar um ConstraintViolationException");

		  Assertions.assertTrue(exception.getMessage().contains("Campo nome e obrigatorio"));
    }

    @Test
    public void salvarComUFNullLancaExcecao() throws Exception{
      Cidade cidade = new Cidade("Sao Paulo", null, BigDecimal.valueOf(50));

      ConstraintViolationException exception =
			  assertThrows(ConstraintViolationException.class,
				() -> {  cidadeRepository.save(cidade);
					  },
				"Deveria lançar um ConstraintViolationException");

		  Assertions.assertTrue(exception.getMessage().contains("Campo UF e obrigatorio"));
    }

    @Test
    public void salvarComTaxaNullLancaExcecao() throws Exception{
      Cidade cidade = new Cidade("Sao Paulo", "SP", null);

      ConstraintViolationException exception =
			  assertThrows(ConstraintViolationException.class,
				() -> {  cidadeRepository.save(cidade);
					  },
				"Deveria lançar um ConstraintViolationException");

		  Assertions.assertTrue(exception.getMessage().contains("Campo taxa e obrigatorio"));
    }

    @Test
    public void salvarComTaxaInvalidoLancaExcecao(){
      // valor invalido de taxa
      Cidade cidade = new Cidade("Sao Paulo", "SP", BigDecimal.valueOf(0));

      ConstraintViolationException exception =
			  assertThrows(ConstraintViolationException.class,
				() -> {  cidadeRepository.save(cidade);
					  },
				"Deveria lançar um ConstraintViolationException");

		  Assertions.assertTrue(exception.getMessage().contains("Campo taxa nao e valido"));
    }

    @Test
    public void deveEncontrarTodosAsCidades(){
        Cidade cidade = new Cidade("Sao Paulo", "SP", BigDecimal.valueOf(60));

        int length = cidadeRepository.findAll().size();
        // deve ter 3elementos (data.sql)
        Assertions.assertEquals(3, length);

        // adicionado +1
        cidadeRepository.save(cidade);

        // agora deve conter 8 elementos
        length = cidadeRepository.findAll().size();
        Assertions.assertEquals(4, length);
    }

    @Test
    public void deveEncontrarClientePorId(){
        Cidade cidade = new Cidade("Sao Paulo", "SP", BigDecimal.valueOf(60));
        cidadeRepository.save(cidade);

        int idBuscado = cidade.getId();

        Cidade cidadeBuscada = cidadeRepository.findById(idBuscado).get();
        Assertions.assertEquals(cidade, cidadeBuscada);
    }

    @Test
    public void deveEncontrarCidadePorNome(){
      Cidade cidade = new Cidade("Sao Paulo", "SP", BigDecimal.valueOf(60));
      cidadeRepository.save(cidade);

      Cidade cidadeBuscada = cidadeRepository.findByNomeContaining("Paulo").get(0);
      Assertions.assertEquals(cidade, cidadeBuscada);
    }

}
