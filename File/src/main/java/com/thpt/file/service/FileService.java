package com.thpt.file.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class FileService {

	@Value("${local.path}")
	private String path;
}
