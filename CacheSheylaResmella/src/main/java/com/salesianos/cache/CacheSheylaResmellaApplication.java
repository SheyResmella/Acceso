package com.salesianos.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CacheSheylaResmellaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheSheylaResmellaApplication.class, args);
	}

}
