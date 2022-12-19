package br.rafaelhorochovec.pessoa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PessoaFisica extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idade;
	private String cpf;
}
