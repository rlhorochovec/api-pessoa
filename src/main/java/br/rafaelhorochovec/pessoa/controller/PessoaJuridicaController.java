package br.rafaelhorochovec.pessoa.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.rafaelhorochovec.pessoa.exception.ResourceNotFoundException;
import br.rafaelhorochovec.pessoa.model.Contato;
import br.rafaelhorochovec.pessoa.model.PessoaJuridica;
import br.rafaelhorochovec.pessoa.repository.PessoaJuridicaRepository;

@RestController
@RequestMapping("/api")
public class PessoaJuridicaController {
	
	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;

	@GetMapping("/empresas")
	public Page<PessoaJuridica> getPessoaJuridicas(Pageable pageable) {
		return pessoaJuridicaRepository.findAll(pageable);
	}

	@GetMapping("/empresas/{id}")
	public ResponseEntity<PessoaJuridica> getPessoaJuridicaById(@PathVariable(value = "id") Long pessoaJuridicaId)
			throws ResourceNotFoundException {
		PessoaJuridica pessoaJuridica = pessoaJuridicaRepository.findById(pessoaJuridicaId)
				.orElseThrow(() -> new ResourceNotFoundException("Não existe empresa com ID: " + pessoaJuridicaId));
		return ResponseEntity.ok().body(pessoaJuridica);
	}

	@PostMapping("/empresas")
	public PessoaJuridica createPessoaJuridica(@Valid @RequestBody PessoaJuridica pessoaJuridica) {
		return pessoaJuridicaRepository.save(pessoaJuridica);
	}

	@PutMapping("/empresas/{id}")
	public ResponseEntity<PessoaJuridica> updatePessoaJuridica(@PathVariable(value = "id") Long pessoaJuridicaId,
			@Valid @RequestBody PessoaJuridica pessoaJuridicaRequest) throws ResourceNotFoundException {
		PessoaJuridica pessoaJuridica = pessoaJuridicaRepository.findById(pessoaJuridicaId)
				.orElseThrow(() -> new ResourceNotFoundException("Não existe pessoa com ID: " + pessoaJuridicaId));
		pessoaJuridica.setNome(pessoaJuridicaRequest.getNome());
		pessoaJuridica.setNomeFantasia(pessoaJuridicaRequest.getNomeFantasia());
		pessoaJuridica.setCnpj(pessoaJuridicaRequest.getCnpj());
		
		Contato contato = new Contato();
		contato.setId(pessoaJuridica.getContato().getId());
		contato.setEmail(pessoaJuridicaRequest.getContato().getEmail());
		contato.setTelefoneFixo(pessoaJuridicaRequest.getContato().getTelefoneFixo());
		contato.setTelefoneCelular(pessoaJuridicaRequest.getContato().getTelefoneCelular());
		
		pessoaJuridica.setContato(contato);
		final PessoaJuridica updatedPessoaJuridica = pessoaJuridicaRepository.save(pessoaJuridica);
		return ResponseEntity.ok(updatedPessoaJuridica);
	}

	@DeleteMapping("/empresas/{id}")
	public Map<String, Boolean> deletePessoaJuridica(@PathVariable(value = "id") Long pessoaJuridicaId) throws ResourceNotFoundException {
		PessoaJuridica pessoaJuridica = pessoaJuridicaRepository.findById(pessoaJuridicaId)
				.orElseThrow(() -> new ResourceNotFoundException("Não existe empresa com ID: " + pessoaJuridicaId));

		pessoaJuridicaRepository.delete(pessoaJuridica);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}