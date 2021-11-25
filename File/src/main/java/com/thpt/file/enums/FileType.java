package com.thpt.file.enums;

public enum FileType {

	VIDEO,
	IMAGE,
	AUDIO;
	
	public static FileType getFileType(String fileType) {
		return FileType.valueOf(fileType.toUpperCase());
	}
}
