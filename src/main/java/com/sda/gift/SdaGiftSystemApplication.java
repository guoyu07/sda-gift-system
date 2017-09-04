package com.sda.gift;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@MapperScan("com.sda.gift.mapper")
public class SdaGiftSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SdaGiftSystemApplication.class, args);
	}
}
