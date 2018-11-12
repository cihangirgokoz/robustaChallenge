package com.robusta.excelread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//component scanning works
@ComponentScan(basePackages = "com")
public class ExcelreadApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExcelreadApplication.class, args);
	}
}
