package edu.ifma.lpweb.freteapi.service;

import edu.ifma.lpweb.freteapi.model.Cidade;
import edu.ifma.lpweb.freteapi.model.Cliente;
import edu.ifma.lpweb.freteapi.model.Frete;
import edu.ifma.lpweb.freteapi.repository.ClienteRepository;
import edu.ifma.lpweb.freteapi.repository.FreteRepository;
import edu.ifma.lpweb.freteapi.repository.filter.FreteFiltro;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class FreteService {

    private final FreteRepository freteRepository;

    @Autowired
    public FreteService(FreteRepository freteRepository) {
        this.freteRepository = freteRepository;
    }

    @Transactional(readOnly = true)
    public Page<Frete> busca(FreteFiltro filtro, Pageable page) {
        return freteRepository.filtra(filtro, page );
    }

    public BigDecimal valorFrete(Float peso, Cidade cidade){
        return cidade.getTaxa().add(BigDecimal.valueOf(peso * 10.0));
    }

    public void novo(String desc, Float peso, Cliente cliente, Cidade cidade, ClienteService clienteService, CidadeService cidadeService) throws Exception{
        if(cidadeService.existe(cidade))
            throw new Exception("Cidade nao existe");

        if(clienteService.cadastrado(cliente))
            throw new Exception("Cliente nao cadastrado");

        Frete novoFrete = new Frete(desc, peso, valorFrete(peso, cidade), cliente, cidade);
        freteRepository.save(novoFrete);
    }

    public Frete obterMaiorFrete(){
        return freteRepository.findTopByOrderByValorDesc();
    }
    
    public Cidade obterCidadeComMaisFretes(){
        Cidade cidade = freteRepository.cidadeComMaisFrentes(PageRequest.of(0, 1)).get(0);
        return cidade;
    }
}
