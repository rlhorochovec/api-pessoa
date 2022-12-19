package br.rafaelhorochovec.pessoa.controller;

import br.rafaelhorochovec.pessoa.model.PessoaJuridica;
import br.rafaelhorochovec.pessoa.service.PessoaJuridicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class PessoaJuridicaController {

	@Autowired
	PessoaJuridicaService pessoaJuridicaService;

	// POST
	@PostMapping("/empresas")
	public PessoaJuridica createPessoaJuridica(@RequestBody PessoaJuridica pessoaJuridica) {
		return pessoaJuridicaService.createPessoaJuridica(pessoaJuridica);
	}

	// GET
	@GetMapping("/empresas")
	public List<PessoaJuridica> listPessoasJuridicas() {
		return pessoaJuridicaService.getPessoasJuridicas();
	}

	// PUT
	@PutMapping("/empresas/{id}")
	public PessoaJuridica updatePessoaJuridica(@PathVariable(value = "id") UUID id, @RequestBody PessoaJuridica pessoaJuridicaDetails) {
		return pessoaJuridicaService.updatePessoaJuridica(id, pessoaJuridicaDetails);
	}

	// GET BY ID
	@GetMapping("/empresas/{id}")
	public PessoaJuridica getPessoaJuridica(@PathVariable(value = "id") UUID id) {
		return pessoaJuridicaService.getById(id);
	}

	// DELETE
	@DeleteMapping("/empresas/{id}")
	public void deletePessoaJuridica(@PathVariable(value = "id") UUID id) {
		pessoaJuridicaService.deletePessoaJuridica(id);
	}
}