package com.thpt.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.thpt.user.config.MongoConfig;
import com.thpt.user.repository.mongo.DBManager;

import lombok.AllArgsConstructor;

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
