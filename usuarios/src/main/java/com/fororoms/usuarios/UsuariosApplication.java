package com.fororoms.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient // Activar Eureka CLIENT
@SpringBootApplication(scanBasePackages = "com.fororoms.usuarios")
public class UsuariosApplication {
	public static void main(String[] args) {
		SpringApplication.run(UsuariosApplication.class, args);
	}
}