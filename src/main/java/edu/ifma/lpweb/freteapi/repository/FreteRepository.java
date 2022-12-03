package edu.ifma.lpweb.freteapi.repository;

import edu.ifma.lpweb.freteapi.model.Cidade;
import edu.ifma.lpweb.freteapi.model.Cliente;
import edu.ifma.lpweb.freteapi.model.Frete;
import edu.ifma.lpweb.freteapi.repository.frete.FreteRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FreteRepository extends JpaRepository<Frete, Integer>, FreteRepositoryQuery {

    List<Frete> findByDescricaoContaining(String descricao );
    Page<Frete> findByDescricaoContaining(String descricao, Pageable paginacao);

    // Recuperar uma lista com todos os fretes 
    // de um determinado cliente ordenado por valor.
    List<Frete> findByClienteOrderByValorAsc(Cliente cliente);

    Frete findTopByOrderByValorDesc();

    

    @Query(value="select f.cidade from Frete f, Cidade c group by f.cidade.nome order by count(f.cidade.nome) DESC")
    //@Query(value="select cidade.* from frete, cidade group by frete.cidade_id, cidade.id order by count(frete.cidade_id) DESC limit 1", nativeQuery = true)
    List<Cidade> cidadeComMaisFrentes(Pageable pageable);

}
