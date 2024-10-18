package com.fororoms.foros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient // Activar Eureka CLIENT
@SpringBootApplication
public class ForosApplication {
	public static void main(String[] args) {

		SpringApplication.run(ForosApplication.class, args);

	}
}
