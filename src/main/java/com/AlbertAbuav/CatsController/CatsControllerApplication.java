package com.AlbertAbuav.CatsController;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CatsControllerApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(CatsControllerApplication.class, args);
		System.out.println("Spring IoC Container was loaded!");
	}

}
