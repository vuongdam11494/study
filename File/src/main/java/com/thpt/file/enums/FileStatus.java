package com.thpt.file.enums;

public enum FileStatus {

	ACTIVE,
	UNACTIVE;
	
	public static FileStatus getFileStatus(String status) {
		return FileStatus.valueOf(status.toUpperCase());
	}
}
