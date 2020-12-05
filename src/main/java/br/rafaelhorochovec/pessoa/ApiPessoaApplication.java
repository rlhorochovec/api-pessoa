package br.rafaelhorochovec.pessoa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApiPessoaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPessoaApplication.class, args);
	}

}
