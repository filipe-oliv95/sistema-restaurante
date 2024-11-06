package com.restaurante.marmitas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MarmitasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarmitasApplication.class, args);
	}

}
