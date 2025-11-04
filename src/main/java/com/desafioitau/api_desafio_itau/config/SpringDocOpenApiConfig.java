package com.desafioitau.api_desafio_itau.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocOpenApiConfig {

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("API de transações")
						.description("API de transações desenvolvida para desafio técnico do itaú")
						.version("v1.0")
						.license(new License()
								.name("Apache 2.0")
								.url("https://www.apache.org/licenses/LICENSE-2.0"))
						.contact(new Contact()
								.name("Romulo Wagnes Ferreira")
								.email("romulowagnes99@gmail.com")));
	}
}
