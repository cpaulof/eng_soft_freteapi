package edu.ifma.lpweb.freteapi.controller;

import edu.ifma.lpweb.freteapi.model.Frete;
import edu.ifma.lpweb.freteapi.repository.filter.FreteFiltro;
import edu.ifma.lpweb.freteapi.service.FreteService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/fretes")
public class FreteController {

    @Value("${paginacao.qtd_por_pagina}")
    private Integer quantidadePorPagina;

    private final FreteService freteService;

    @Autowired
    public FreteController(FreteService freteService) {
        this.freteService = freteService;
    }


    @GetMapping
    public Page<Frete> busca(FreteFiltro filtro, Pageable page  ) {
         return freteService.busca(filtro, page );

    }

    // @GetMapping("/{id}")
    // public ResponseEntity<Frete> buscaPor(@PathVariable Integer id) {
    //     Optional<Cidade> optional = cidadeService.buscaPor(id);

    //     if (optional.isPresent()) {
    //         return ResponseEntity.ok(optional.get());
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }


}
