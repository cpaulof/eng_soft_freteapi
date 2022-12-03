package edu.ifma.lpweb.freteapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
public class FreteControllerTest {
    
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

	// @Test
	// public void deveBuscarPorId() {
		
	// 	Map<String, String> urlVars = new HashMap<>();
	// 	urlVars.put("cliente_id", "2");
	// 	ResponseEntity<String> frete = testRestTemplate.getForEntity("/fretes/", String.class, urlVars);

	// 	// ResponseEntity<String> resposta =
	// 	// 		testRestTemplate.exchange("/fretes/",HttpMethod.GET,
	// 	// 				                   null, String.class, urlVars);
	// 	System.out.println("\n\n-----------------\n"+frete.getBody());
	// 	//assertEquals(HttpStatus.OK, resposta.getStatusCode());
	// }
    

}
