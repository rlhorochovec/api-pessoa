package br.rafaelhorochovec.pessoa.service;

import br.rafaelhorochovec.pessoa.model.PessoaFisica;
import br.rafaelhorochovec.pessoa.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PessoaFisicaService {

    @Autowired
    PessoaFisicaRepository pessoaFisicaRepository;

    // CREATE 
    public PessoaFisica createPessoaFisica(PessoaFisica pessoaFisica) {
        return pessoaFisicaRepository.save(pessoaFisica);
    }

    // READ
    public List<PessoaFisica> getPessoasFisicas() {
        return pessoaFisicaRepository.findAll();
    }

    // UPDATE
    public PessoaFisica updatePessoaFisica(UUID id, PessoaFisica pessoaFisicaDetails) {
        PessoaFisica pessoaFisica = pessoaFisicaRepository.findById(id).get();

        pessoaFisica.setNome(pessoaFisicaDetails.getNome());
        pessoaFisica.setCpf(pessoaFisicaDetails.getCpf());
        pessoaFisica.setIdade(pessoaFisicaDetails.getIdade());

        pessoaFisica.getContato().setEmail(pessoaFisicaDetails.getContato().getEmail());
        pessoaFisica.getContato().setTelefoneFixo(pessoaFisicaDetails.getContato().getTelefoneFixo());
        pessoaFisica.getContato().setTelefoneCelular(pessoaFisicaDetails.getContato().getTelefoneCelular());
        return pessoaFisicaRepository.save(pessoaFisica);
    }

    // GET BY ID
    public PessoaFisica getById(UUID id) {
        PessoaFisica pessoaFisica = pessoaFisicaRepository.findById(id).get();
        return pessoaFisicaRepository.getOne(id);
    }

    // DELETE
    public void deletePessoaFisica(UUID id) {
        pessoaFisicaRepository.deleteById(id);
    }
}
