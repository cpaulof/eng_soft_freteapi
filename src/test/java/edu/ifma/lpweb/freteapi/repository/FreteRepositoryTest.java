package edu.ifma.lpweb.freteapi.repository;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import javax.validation.ConstraintViolationException;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import edu.ifma.lpweb.freteapi.model.Cidade;
import edu.ifma.lpweb.freteapi.model.Cliente;
import edu.ifma.lpweb.freteapi.model.Frete;

@DataJpaTest
public class FreteRepositoryTest {
    
    @Autowired
    private FreteRepository freteRepository;

    private Cidade cidade;

    private Cliente cliente;

    @BeforeEach
    void initEach(){
        cidade = new Cidade();
        cidade.setNome("Sao Paulo");
        cidade.setTaxa(BigDecimal.valueOf(150));
        cidade.setUf("SP");

        cliente = new Cliente();
        cliente.setEndereco("Rua 13");
        cliente.setNome("Joao Pereira");
        cliente.setTelefone("88987878787");
    }

    @AfterEach
    public void cleanEach(){
        cidade = null;
        cliente = null;
    }

    @Test
    public void salvarComPesoNullLancaExcecao() throws Exception{
        Frete frete = new Frete("desc", null, BigDecimal.valueOf(30.), cliente, cidade);

        ConstraintViolationException exception =
				assertThrows(ConstraintViolationException.class,
				() -> {  freteRepository.save(frete);
					  },
				"Deveria lançar um ConstraintViolationException");

		Assertions.assertTrue(exception.getMessage().contains("Campo peso e obrigatorio"));
    }

    @Test
    public void salvarComValorNullLancaExcecao() throws Exception{
        Frete frete = new Frete("desc", 10.0f, null, cliente, cidade);

        ConstraintViolationException exception =
				assertThrows(ConstraintViolationException.class,
				() -> {  freteRepository.save(frete);
					  },
				"Deveria lançar um ConstraintViolationException");

		Assertions.assertTrue(exception.getMessage().contains("Campo valor e obrigatorio"));
    }

    @Test
    public void deveEncontrarTodosOsFretes(){
        Frete frete = new Frete("desc", 10.0f, BigDecimal.valueOf(30.), cliente, cidade);

        int length = freteRepository.findAll().size();
        // deve ter 5 elementos (data.sql)
        Assertions.assertEquals(5, length);

        // adicionado +1
        freteRepository.save(frete);

        // agora deve conter 6 elementos
        length = freteRepository.findAll().size();
        Assertions.assertEquals(6, length);
    }

    @Test
    public void deveEncontrarFretePorId(){
        Frete frete = new Frete("desc", 10.0f, BigDecimal.valueOf(30.), cliente, cidade);
        freteRepository.save(frete);

        int idBuscado = frete.getId();

        Frete freteBuscado = freteRepository.findById(idBuscado).get();
        Assertions.assertEquals(frete, freteBuscado);
    }

    @Test
    public void deveEncontrarFretePorDescricao(){
        Frete frete = new Frete("Livros Escolares", 10.0f, BigDecimal.valueOf(30.), cliente, cidade);
        freteRepository.save(frete);

        Frete freteBuscado = freteRepository.findByDescricaoContaining("Livros").get(0);
        Assertions.assertEquals(frete, freteBuscado);
    }

    @Test
    public void deveEncontrarCidadeComMaisFretes(){
        Cidade cidade = freteRepository.cidadeComMaisFrentes(PageRequest.of(0, 1)).get(0);
        Assertions.assertEquals("Teresina", cidade.getNome());
    }


}
