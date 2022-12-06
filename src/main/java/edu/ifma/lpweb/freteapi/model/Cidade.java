package edu.ifma.lpweb.freteapi.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = "Campo nome e obrigatorio")
	private String nome;

	@NotEmpty(message = "Campo UF e obrigatorio")
	private String uf;

	@NotNull(message = "Campo taxa e obrigatorio")
	@Min(value = 1, message = "Campo taxa nao e valido")
	private BigDecimal taxa;

}