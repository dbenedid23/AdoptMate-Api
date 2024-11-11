package com.adoptmate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.adoptmate.tools.Configuration;

@SpringBootApplication
public class AdoptMate {

	public static void main(String[] args) {
		Configuration c = new Configuration();
		SpringApplication.run(AdoptMate.class, args);
	}

}
