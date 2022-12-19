package br.rafaelhorochovec.pessoa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PessoaJuridica extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomeFantasia;
	private String cnpj;
}
