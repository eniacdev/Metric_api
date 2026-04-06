package com.example.metric_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
/*@EntityScan(basePackages = {"com.example.metric_api.model"})
@ComponentScan(basePackages = {"com.example.metric_api"})*/
@EnableScheduling
@SpringBootApplication
public class HomeServerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeServerApiApplication.class, args);
	}

}
