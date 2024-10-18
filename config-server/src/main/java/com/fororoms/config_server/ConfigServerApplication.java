package com.fororoms.config_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer // Activar Config SERVER
@SpringBootApplication
public class ConfigServerApplication {
	public static void main(String[] args) {

		SpringApplication.run(ConfigServerApplication.class, args);

	}
}
