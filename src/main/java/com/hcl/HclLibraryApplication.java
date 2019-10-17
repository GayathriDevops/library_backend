package com.hcl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HclLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(HclLibraryApplication.class, args);
	}

}
