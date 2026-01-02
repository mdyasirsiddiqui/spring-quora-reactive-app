package com.mdyasirsiddiqui.spring_quora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
public class SpringQuoraReactiveAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringQuoraReactiveAppApplication.class, args);
	}

}
