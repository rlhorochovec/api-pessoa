package br.rafaelhorochovec.pessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.rafaelhorochovec.pessoa.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
}