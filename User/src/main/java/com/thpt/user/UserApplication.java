package com.thpt.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.thpt.common.config.MongoConfig;
import com.thpt.common.config.TokenConfig;
import com.thpt.common.mongo.DBManager;
import com.thpt.common.utils.token.TokenUtils;

import lombok.AllArgsConstructor;

@ComponentScan(basePackages = {"com.thpt"})
@SpringBootApplication
@AllArgsConstructor
public class UserApplication implements CommandLineRunner{

	private final MongoConfig mongoConfig;
	
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		DBManager.initConncetion(mongoConfig);
	}
}
