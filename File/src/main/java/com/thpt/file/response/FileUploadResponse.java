package com.thpt.file.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thpt.common.constant.ParamKey;
import com.thpt.file.enums.FileStatus;
import com.thpt.file.enums.FileType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileUploadResponse {
	
	@JsonProperty(ParamKey.FILE_ID)
	private String fileId;
	@JsonProperty(ParamKey.FILE_TYPE)
	private FileType fileType;
	@JsonProperty(ParamKey.FILE_URL)
	private String fileUrl;
	@JsonProperty(ParamKey.THUMBNAIL_URL)
	private String fileThumbnailUrl;
	@JsonProperty(ParamKey.FILE_STATUS)
	private FileStatus fileStatus;
}
