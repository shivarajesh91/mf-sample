package com.krishna.coding.mf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MFSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MFSampleApplication.class, args);
	}

}
