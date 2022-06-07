package com.exercise4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Exercise 4 APIs"))
public class Exercise4Application {

	public static void main(String[] args) {
		SpringApplication.run(Exercise4Application.class, args);
	}
}
