package edu.ifma.lpweb.freteapi.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message="Campo nome e obrigatorio")
    private String nome;

    @NotEmpty(message="Campo UF e obrigatorio")
    private String uf;

    @NotNull(message="Campo taxa e obrigatorio")
    @Min(value=1, message="Campo taxa nao e valido")
    private BigDecimal taxa;

    public Cidade() {
    }


    public Cidade(String nome, String uf, BigDecimal taxa) {
        this.nome = nome;
        this.uf = uf;
        this.taxa = taxa;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return this.uf;
    }


    public void setUf(String uf) {
        this.uf = uf;
    }

    public BigDecimal getTaxa() {
        return this.taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cidade cidade = (Cidade) o;
        return Objects.equals(id, cidade.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}