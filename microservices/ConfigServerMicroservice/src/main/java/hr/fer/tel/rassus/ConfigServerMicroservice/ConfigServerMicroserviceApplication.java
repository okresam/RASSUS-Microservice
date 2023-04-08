package hr.fer.tel.rassus.ConfigServerMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@SpringBootApplication
@EnableConfigServer
public class ConfigServerMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerMicroserviceApplication.class, args);
	}

}
