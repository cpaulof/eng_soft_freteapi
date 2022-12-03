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
public class ClienteRepositoryTest {
    
    @Autowired
    private ClienteRepository clienteRepository;

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
        Cliente cliente = new Cliente(null, "Rua da paz", "88844414");

        ConstraintViolationException exception =
				assertThrows(ConstraintViolationException.class,
				() -> {  clienteRepository.save(cliente);
					  },
				"Deveria lançar um ConstraintViolationException");

		Assertions.assertTrue(exception.getMessage().contains("Campo nome e obrigatorio"));
    }

    @Test
    public void salvarComEnderecoNullLancaExcecao() throws Exception{
        Cliente cliente = new Cliente("Joao Oliveira", null, "88844414");

        ConstraintViolationException exception =
				assertThrows(ConstraintViolationException.class,
				() -> {  clienteRepository.save(cliente);
					  },
				"Deveria lançar um ConstraintViolationException");

		Assertions.assertTrue(exception.getMessage().contains("Campo endereco e obrigatorio"));
    }

    @Test
    public void salvarComTelefoneNullLancaExcecao() throws Exception{
        Cliente cliente = new Cliente("Joao Oliveira", "Rua da Paz", null);

        ConstraintViolationException exception =
				assertThrows(ConstraintViolationException.class,
				() -> {  clienteRepository.save(cliente);
					  },
				"Deveria lançar um ConstraintViolationException");

		Assertions.assertTrue(exception.getMessage().contains("Campo telefone e obrigatorio"));
    }

    @Test
    public void salvarComNomeInvalidoLancaExcecao(){
        // nome muito curto
        Cliente cliente1 = new Cliente("ip", "Rua da paz", "88844414");

        // nome muito longo
        String nomeLongo = String.join("", Collections.nCopies(100, "abc"));        
        Cliente cliente2 = new Cliente(nomeLongo, "Rua da paz", "88844414");

        ConstraintViolationException exception1 =
				assertThrows(ConstraintViolationException.class,
				() -> {  clienteRepository.save(cliente1);
					  },
				"Deveria lançar um ConstraintViolationException");

        ConstraintViolationException exception2 =
				assertThrows(ConstraintViolationException.class,
				() -> {  clienteRepository.save(cliente2);
					  },
				"Deveria lançar um ConstraintViolationException");

		Assertions.assertTrue(exception1.getMessage().contains("Campo deve ser entre 3 e 200 caracteres"));
        Assertions.assertTrue(exception2.getMessage().contains("Campo deve ser entre 3 e 200 caracteres"));
    }

    @Test
    public void salvarComTelefoneInvalidoLancaExcecao() throws Exception{

        // telefone menor que 8 caracteres
        Cliente cliente = new Cliente("Joao Oliveira", "Rua da Paz", "15457");

        ConstraintViolationException exception =
				assertThrows(ConstraintViolationException.class,
				() -> {  clienteRepository.save(cliente);
					  },
				"Deveria lançar um ConstraintViolationException");

		Assertions.assertTrue(exception.getMessage().contains("Deve possuir 8 dígitos no mínimo"));
    }





    @Test
    public void deveEncontrarTodosOsClientes(){
        Cliente cliente = new Cliente("Joao Carlos", "Rua da paz", "88844414");

        int length = clienteRepository.findAll().size();
        // deve ter 7 elementos (data.sql)
        Assertions.assertEquals(7, length);

        // adicionado +1
        clienteRepository.save(cliente);

        // agora deve conter 8 elementos
        length = clienteRepository.findAll().size();
        Assertions.assertEquals(8, length);
    }

    @Test
    public void deveEncontrarClientePorId(){
        Cliente cliente = new Cliente("Joao Carlos", "Rua da paz", "88844414");
        clienteRepository.save(cliente);

        int idBuscado = cliente.getId();

        Cliente clienteBuscado = clienteRepository.findById(idBuscado).get();
        Assertions.assertEquals(cliente, clienteBuscado);
    }

    @Test
    public void deveEncontrarFretePorNome(){
        Cliente cliente = new Cliente("Paulo Fernando", "Rua da paz", "88844414");
        clienteRepository.save(cliente);

        Cliente clienteBuscado = clienteRepository.findByNomeContaining("Fernando").get(0);
        Assertions.assertEquals(cliente, clienteBuscado);
    }

}
