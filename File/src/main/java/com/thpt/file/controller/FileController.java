package com.thpt.file.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.thpt.file.response.FileUploadResponse;
import com.thpt.file.service.FileService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("files")
@AllArgsConstructor
public class FileController {
	
	private final FileService fileService;

	@PostMapping
	public FileUploadResponse uploadFile(
			@RequestParam("file_type") String fileType,
			@RequestParam("file") MultipartFile file,
			@RequestParam("duration") int duration,
			@RequestParam(name = "thumbnail", required = false) MultipartFile thumbnail
			) {
		return fileService.uploadFile(fileType, duration, file, thumbnail);
	}
}
