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
import br.rafaelhorochovec.pessoa.model.PessoaFisica;
import br.rafaelhorochovec.pessoa.repository.PessoaFisicaRepository;

@RestController
@RequestMapping("/api")
public class PessoaFisicaController {
	
	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;

	@GetMapping("/pessoas")
	public Page<PessoaFisica> getPessoasFisicas(Pageable pageable) {
		return pessoaFisicaRepository.findAll(pageable);
	}

	@GetMapping("/pessoas/{id}")
	public ResponseEntity<PessoaFisica> getPessoaFisicaById(@PathVariable(value = "id") Long pessoaFisicaId)
			throws ResourceNotFoundException {
		PessoaFisica pessoaFisica = pessoaFisicaRepository.findById(pessoaFisicaId)
				.orElseThrow(() -> new ResourceNotFoundException("Não existe pessoa com ID: " + pessoaFisicaId));
		return ResponseEntity.ok().body(pessoaFisica);
	}

	@PostMapping("/pessoas")
	public PessoaFisica createPessoaFisica(@Valid @RequestBody PessoaFisica pessoaFisica) {
		return pessoaFisicaRepository.save(pessoaFisica);
	}

	@PutMapping("/pessoas/{id}")
	public ResponseEntity<PessoaFisica> updatePessoaFisica(@PathVariable(value = "id") Long pessoaFisicaId,
			@Valid @RequestBody PessoaFisica pessoaFisicaRequest) throws ResourceNotFoundException {
		PessoaFisica pessoaFisica = pessoaFisicaRepository.findById(pessoaFisicaId)
				.orElseThrow(() -> new ResourceNotFoundException("Não existe pessoa com ID: " + pessoaFisicaId));
		pessoaFisica.setNome(pessoaFisicaRequest.getNome());
		pessoaFisica.setCpf(pessoaFisicaRequest.getCpf());
		pessoaFisica.setIdade(pessoaFisicaRequest.getIdade());
		
		Contato contato = new Contato();
		contato.setId(pessoaFisica.getContato().getId());
		contato.setEmail(pessoaFisicaRequest.getContato().getEmail());
		contato.setTelefoneFixo(pessoaFisicaRequest.getContato().getTelefoneFixo());
		contato.setTelefoneCelular(pessoaFisicaRequest.getContato().getTelefoneCelular());
		
		pessoaFisica.setContato(contato);
		final PessoaFisica updatedPessoaFisica = pessoaFisicaRepository.save(pessoaFisica);
		return ResponseEntity.ok(updatedPessoaFisica);
	}

	@DeleteMapping("/pessoas/{id}")
	public Map<String, Boolean> deletePessoaFisica(@PathVariable(value = "id") Long pessoaFisicaId) throws ResourceNotFoundException {
		PessoaFisica pessoaFisica = pessoaFisicaRepository.findById(pessoaFisicaId)
				.orElseThrow(() -> new ResourceNotFoundException("Não existe pessoa com ID: " + pessoaFisicaId));

		pessoaFisicaRepository.delete(pessoaFisica);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}