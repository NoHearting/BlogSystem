package com.example.backgroundsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})   //忽略数据库自动配置，在并未配置数据库的时候
@SpringBootApplication()
@MapperScan("com.example.backgroundsystem.mapper")
public class BackgroundSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackgroundSystemApplication.class, args);
	}

}
