package com.thpt.file.controller.internal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thpt.file.service.FileService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("internal/file")
@AllArgsConstructor
public class FileControllerInternal {
	
	private final FileService fileService;
	
	
}
