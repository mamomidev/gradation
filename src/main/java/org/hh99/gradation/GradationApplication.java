package org.hh99.gradation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class GradationApplication {

	public static void main(String[] args) {
		SpringApplication.run(GradationApplication.class, args);
	}

}
