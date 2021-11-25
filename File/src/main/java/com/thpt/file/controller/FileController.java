package com.thpt.file.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("files")
public class FileController {

	@PostMapping
	public void uploadFile(
			@RequestParam("file_type") String fileType,
			@RequestParam("file") MultipartFile file,
			@RequestParam("duration") int duration,
			@RequestParam(name = "thumbnail", required = false) MultipartFile thumbnail
			) {
		
	}
}
