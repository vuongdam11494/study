package com.thpt.file.domain;

import com.thpt.file.enums.FileStatus;
import com.thpt.file.enums.FileType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class File {

	private String id;
	private String userId;
	private String thumbnailId;
	private FileType fileType;
	private FileStatus fileStatus;
	private long uploadTime;
	private long size;
	private String filePath;
	private int duration;
	private String thumbnailPath;
}
