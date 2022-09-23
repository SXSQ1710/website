package com.SXSQ;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.SXSQ.mapper")
public class Springboot_websiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot_websiteApplication.class, args);
	}

}
