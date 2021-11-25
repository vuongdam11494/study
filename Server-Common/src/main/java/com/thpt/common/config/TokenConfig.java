package com.thpt.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class TokenConfig {

	@Value("${jwt.jwtSecret}")
	private String secret;
	@Value("${jwt.jwtExpirationMs}")
	private Long expiration;
}
