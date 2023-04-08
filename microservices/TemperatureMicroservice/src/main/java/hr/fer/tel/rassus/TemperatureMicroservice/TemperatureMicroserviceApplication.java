package hr.fer.tel.rassus.TemperatureMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TemperatureMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TemperatureMicroserviceApplication.class, args);
	}

}
