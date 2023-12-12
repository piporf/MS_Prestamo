package com.monte.msprestamo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API Prestamos", version = "1.0", description = "API que calcula el monto del préstamo para un artículo."))
public class MsPrestamoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPrestamoApplication.class, args);
	}

}
