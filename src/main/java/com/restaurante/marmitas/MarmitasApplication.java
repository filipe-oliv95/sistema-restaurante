package com.restaurante.marmitas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
	    info = @Info(
	        title = "Restaurante Marmitas - API de Produtos",
	        description = "API REST para gerenciamento de um restaurante.",
	        version = "v1",
	        contact = @Contact(
	            name = "Filipe Oliveira",
	            email = "filipec.oliveira95@gmail.com",
	            url = "https://github.com/filipe-oliv95"
	        )
	    ),
	    externalDocs = @ExternalDocumentation(
	        description = "Documentação completa da API",
	        url = "https://github.com/filipe-oliv95/sistema-restaurante"
	    )
	)
@SpringBootApplication
@EnableJpaAuditing
public class MarmitasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarmitasApplication.class, args);
	}

}
