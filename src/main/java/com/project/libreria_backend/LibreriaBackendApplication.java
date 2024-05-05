package com.project.libreria_backend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibreriaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibreriaBackendApplication.class, args);
	}
	@Bean
	public ModelMapper ModelMapper(){
		return new ModelMapper();
	}

}
