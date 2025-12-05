package com.example.JobApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.JobApp.Controller","com.example.JobApp.service","com.example.JobApp.repositary"})
public class JobAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobAppApplication.class, args);
	}

}
