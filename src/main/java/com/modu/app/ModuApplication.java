package com.modu.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@MapperScan(basePackages = "com.modu.app.**.mapper")
@SpringBootApplication
public class ModuApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModuApplication.class, args);
	}
}
