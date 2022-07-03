package com.api.CrudMvcRestFul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableAutoConfiguration
@EnableTransactionManagement
@SpringBootApplication
public class CrudMvcRestFulApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(CrudMvcRestFulApplication.class, args);
	}
	
	
  /*Mapeamento Global que refletem em todo o sistema*/
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedMethods("*")
		.allowedOrigins("*");
		/*Liberando o mapeamento de usuario para todas as origens*/
		
	}
}
