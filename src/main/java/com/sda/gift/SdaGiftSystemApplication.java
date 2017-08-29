package com.sda.gift;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@MapperScan("com.sda.gift.mapper")
@EnableConfigurationProperties({com.sda.gift.config.GiftConfig.class})
public class SdaGiftSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SdaGiftSystemApplication.class, args);
	}
}
