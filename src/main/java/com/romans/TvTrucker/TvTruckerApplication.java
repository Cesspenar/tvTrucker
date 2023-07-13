package com.romans.TvTrucker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// (exclude = {DataSourceAutoConfiguration.class })
public class TvTruckerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TvTruckerApplication.class, args);
	}

}
