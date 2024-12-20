package com.fororoms.foros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableDiscoveryClient // Activar Eureka CLIENT
@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableFeignClients
@SpringBootApplication
public class ForosApplication {
	public static void main(String[] args) {
		SpringApplication.run(ForosApplication.class, args);
	}
}
