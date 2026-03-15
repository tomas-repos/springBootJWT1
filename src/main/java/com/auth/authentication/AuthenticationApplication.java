package com.auth.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
		if (args.length > 0) {
			System.out.println("Received arguments:");
			for (String arg : args) {
				System.out.println(arg);
			}
		} else {
			System.out.println("No arguments received.");
		}
	}

}
