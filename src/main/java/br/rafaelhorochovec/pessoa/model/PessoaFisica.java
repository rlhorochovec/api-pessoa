package br.rafaelhorochovec.pessoa.model;

import javax.persistence.Entity;

@Entity
public class PessoaFisica extends Pessoa {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idade;
	private String cpf;
	
	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
