package com.assignment.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringTaskManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTaskManagementApplication.class, args);
		System.out.println("App started!");
	}


}
