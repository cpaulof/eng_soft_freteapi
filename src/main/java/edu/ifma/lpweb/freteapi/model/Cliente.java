package edu.ifma.lpweb.freteapi.model;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = "Campo nome e obrigatorio")
	@Length(min = 3, max = 200, message = "Campo deve ser entre 3 e 200 caracteres")
	private String nome;

	@NotEmpty(message = "Campo endereco e obrigatorio")
	private String endereco;

	@NotEmpty(message = "Campo telefone e obrigatorio")
	@Length(min = 8, message = "Deve possuir {min} dígitos no mínimo")
	private String telefone;

	public Cliente() {
	}

	public Cliente(String nome, String endereco, String telefone) {
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
	}
}
