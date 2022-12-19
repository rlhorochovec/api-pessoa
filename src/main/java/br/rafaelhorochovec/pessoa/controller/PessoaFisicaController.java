package br.rafaelhorochovec.pessoa.controller;

import br.rafaelhorochovec.pessoa.model.PessoaFisica;
import br.rafaelhorochovec.pessoa.service.PessoaFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class PessoaFisicaController {

	@Autowired
	PessoaFisicaService pessoaFisicaService;

	// POST
	@PostMapping("/pessoas")
	public PessoaFisica createPessoaFisica(@RequestBody PessoaFisica pessoaFisica) {
		return pessoaFisicaService.createPessoaFisica(pessoaFisica);
	}

	// GET
	@GetMapping("/pessoas")
	public List<PessoaFisica> listPessoasFisicas() {
		return pessoaFisicaService.getPessoasFisicas();
	}

	// PUT
	@PutMapping("/pessoas/{id}")
	public PessoaFisica updatePessoaFisica(@PathVariable(value = "id") UUID id, @RequestBody PessoaFisica pessoaFisicaDetails) {
		return pessoaFisicaService.updatePessoaFisica(id, pessoaFisicaDetails);
	}

	// GET BY ID
	@GetMapping("/pessoas/{id}")
	public PessoaFisica getPessoaFisica(@PathVariable(value = "id") UUID id) {
		return pessoaFisicaService.getById(id);
	}

	// DELETE
	@DeleteMapping("/pessoas/{id}")
	public void deletePessoaFisica(@PathVariable(value = "id") UUID id) {
		pessoaFisicaService.deletePessoaFisica(id);
	}
}