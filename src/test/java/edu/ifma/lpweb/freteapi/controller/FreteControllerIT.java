package edu.ifma.lpweb.freteapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import edu.ifma.lpweb.freteapi.model.Frete;
import edu.ifma.lpweb.freteapi.repository.FreteRepository;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FreteControllerIT {
    
    @Autowired
	private TestRestTemplate testRestTemplate;

    @Autowired
    private FreteRepository freteRepository;


    @BeforeEach
	public void start() {
	}

	@AfterEach
	public void end() {
	}


    @Test
	public void deveMostrarTodosFretes() {
		ResponseEntity<String> resposta =
				testRestTemplate.exchange("/fretes/",HttpMethod.GET,
						                   null, String.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}

	@Test
	public void deveBuscarPorId() {
		ResponseEntity<Frete> frete = testRestTemplate.getForEntity("/fretes/3", Frete.class);

		String name = "Produtos de Limpeza";

		assertEquals(HttpStatus.OK, frete.getStatusCode());
		Frete resultado = frete.getBody();
		assertNotNull(resultado);
		assertEquals(name, resultado.getDescricao());
	}
    

}
