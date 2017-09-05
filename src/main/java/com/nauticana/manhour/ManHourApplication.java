package com.nauticana.manhour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.nauticana.manhour.utils.DataCache;

@EnableJpaRepositories
@SpringBootApplication
public class ManHourApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManHourApplication.class, args);
		try {
			DataCache.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
