package com.upt.lp.perdidosapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.upt.lp.perdidosapi", "controller", "service"})
@EntityScan(basePackages = "entity")
@EnableJpaRepositories(basePackages = "repository")
public class PerdidosapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PerdidosapiApplication.class, args);
	}
	//teste 1 commit avaliação

}
