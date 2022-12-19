package br.rafaelhorochovec.pessoa.service;

import br.rafaelhorochovec.pessoa.model.PessoaJuridica;
import br.rafaelhorochovec.pessoa.repository.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PessoaJuridicaService {

    @Autowired
    PessoaJuridicaRepository pessoaJuridicaRepository;

    // CREATE 
    public PessoaJuridica createPessoaJuridica(PessoaJuridica pessoaJuridica) {
        return pessoaJuridicaRepository.save(pessoaJuridica);
    }

    // READ
    public List<PessoaJuridica> getPessoasJuridicas() {
        return pessoaJuridicaRepository.findAll();
    }

    // UPDATE
    public PessoaJuridica updatePessoaJuridica(UUID id, PessoaJuridica pessoaJuridicaDetails) {
        PessoaJuridica pessoaJuridica = pessoaJuridicaRepository.findById(id).get();

        pessoaJuridica.setNome(pessoaJuridicaDetails.getNome());
        pessoaJuridica.setNomeFantasia(pessoaJuridicaDetails.getNomeFantasia());
        pessoaJuridica.setCnpj(pessoaJuridicaDetails.getCnpj());

        pessoaJuridica.getContato().setEmail(pessoaJuridicaDetails.getContato().getEmail());
        pessoaJuridica.getContato().setTelefoneFixo(pessoaJuridicaDetails.getContato().getTelefoneFixo());
        pessoaJuridica.getContato().setTelefoneCelular(pessoaJuridicaDetails.getContato().getTelefoneCelular());
        return pessoaJuridicaRepository.save(pessoaJuridica);
    }

    // GET BY ID
    public PessoaJuridica getById(UUID id) {
        PessoaJuridica pessoaJuridica = pessoaJuridicaRepository.findById(id).get();
        return pessoaJuridicaRepository.getOne(id);
    }

    // DELETE
    public void deletePessoaJuridica(UUID id) {
        pessoaJuridicaRepository.deleteById(id);
    }
}
