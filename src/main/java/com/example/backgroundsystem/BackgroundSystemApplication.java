package com.example.backgroundsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class BackgroundSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackgroundSystemApplication.class, args);
	}

}
