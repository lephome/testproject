package org.leo.test.redistest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableScheduling
public class App {
	public static void main(String[] args) {
		System.out.println("SpringBoot start!");
		SpringApplication.run(App.class, args);
	}
}
