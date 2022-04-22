package com.accenture.lkm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {											//Primary Configuration Class
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}


/*
 * @Configuration
 * @ComponentScan - com.accenture.lkm
 * @EnableAutoConfiguration
 */
