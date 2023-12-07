package com.github.userservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Api do usuario", version = "1", description = "Api que cuida do usuario q acessa a zelda-api" ))
@EnableScheduling

public class UserServiceApplication{

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
