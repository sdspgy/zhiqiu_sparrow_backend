package com.pgy.sds;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.pgy.sds.**")
//@MapperScan(basePackages = { "com.pgy.sds.dao" })
public class SdsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SdsApplication.class, args);
	}

}
