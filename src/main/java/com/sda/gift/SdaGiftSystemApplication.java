package com.sda.gift;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sda.gift.mapper")
public class SdaGiftSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SdaGiftSystemApplication.class, args);
	}
}
