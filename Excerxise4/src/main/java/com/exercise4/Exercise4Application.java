package com.exercise4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@EnableAutoConfiguration
@SpringBootApplication
@OpenAPIDefinition
public class Exercise4Application {

	public static void main(String[] args) {
		SpringApplication.run(Exercise4Application.class, args);
	}
}
