package com.fororoms.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient // Activar Eureka CLIENT
@SpringBootApplication(scanBasePackages = "com.fororoms.usuarios")
@EnableFeignClients
public class UsuariosApplication {
	public static void main(String[] args) {
		SpringApplication.run(UsuariosApplication.class, args);
	}
}