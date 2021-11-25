package com.thpt.file.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@Getter
public class FileConfig {

	@Value("${local.path}")
	private String path;
}
