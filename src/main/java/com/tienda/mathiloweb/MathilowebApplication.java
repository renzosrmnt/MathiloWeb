package com.tienda.mathiloweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MathilowebApplication {

	public static void main(String[] args) {
		SpringApplication.run(MathilowebApplication.class, args);
	}

    @Bean
    RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
