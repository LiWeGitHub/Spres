package com.hugo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.hugo.dao")
//@EnableCaching
public class SpresApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpresApplication.class, args);
	}
}
