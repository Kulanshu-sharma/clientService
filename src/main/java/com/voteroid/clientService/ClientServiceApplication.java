package com.voteroid.clientService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.voteroid.clientService.configurations.ApplicationPropertiesConfiguration;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.voteroid.clientService")
public class ClientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientServiceApplication.class, args);
	}

}
