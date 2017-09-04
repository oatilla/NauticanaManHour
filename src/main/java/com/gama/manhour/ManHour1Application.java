package com.gama.manhour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.gama.manhour.utils.DataCache;

@EnableJpaRepositories
@SpringBootApplication
public class ManHour1Application {

	public static void main(String[] args) {
		SpringApplication.run(ManHour1Application.class, args);
		try {
			DataCache.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
