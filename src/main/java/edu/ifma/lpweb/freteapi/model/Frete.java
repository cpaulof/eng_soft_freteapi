package edu.ifma.lpweb.freteapi.model;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Frete {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String descricao;

	@NotNull(message = "Campo peso e obrigatorio")
	private Float peso;

	@NotNull(message = "Campo valor e obrigatorio")
	private BigDecimal valor;

	@ManyToOne(cascade = CascadeType.ALL)
	private Cliente cliente;

	@ManyToOne(cascade = CascadeType.ALL)
	private Cidade cidade;

	public Frete() {
	}

	public Frete(String descricao, Float peso, BigDecimal valor, Cliente cliente, Cidade cidade) {
		this.descricao = descricao;
		this.peso = peso;
		this.valor = valor;
		this.cliente = cliente;
		this.cidade = cidade;
	}

	public Frete(int id, String descricao, Float peso, BigDecimal valor, Cliente cliente, Cidade cidade) {
		this(descricao, peso, valor, cliente, cidade);
		this.id = id;
	}

	public BigDecimal calcularFrete() {
		// R$10,00 é o valor fixo para o cálculo
		this.valor = new BigDecimal(this.peso * 10).add(this.cidade.getTaxa());
		return this.getValor();

	}

}