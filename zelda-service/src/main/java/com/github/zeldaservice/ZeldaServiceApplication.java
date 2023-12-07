package com.github.zeldaservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Api zelda", version = "1", description = "Api retorna dados dos principais jogos do zelda" ))
@EnableScheduling
public class ZeldaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZeldaServiceApplication.class, args);
	}

}
