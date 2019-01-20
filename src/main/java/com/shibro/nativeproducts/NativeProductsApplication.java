package com.shibro.nativeproducts;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shibro.nativeproducts.persistence")
public class NativeProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NativeProductsApplication.class, args);
	}

}

