package com.thpt.file.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.thpt.common.utils.DateTimeUtils;
import com.thpt.common.utils.RequestContext;
import com.thpt.file.config.FileConfig;
import com.thpt.file.domain.File;
import com.thpt.file.enums.FileStatus;
import com.thpt.file.enums.FileType;
import com.thpt.file.repository.FileRepository;
import com.thpt.file.response.FileUploadResponse;
import com.thpt.file.utils.FileUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FileService {

	private final FileConfig fileConfig;

	private final FileRepository fileRepository;

	public FileUploadResponse uploadFile(String fileType, int duration, MultipartFile file, MultipartFile thumbnail) {
		try {
			if (file.getSize() > 0) {
				String userId = RequestContext.getUserId();
				String fileId = generateFileId();
				String fileExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
				long currentTime = DateTimeUtils.currentDateTime();
				String filePath = fileConfig.getPath() + "/" + fileId + "." + fileExtension;
				long fileSize = file.getSize();

				File f = new File();
				f.setDuration(duration);
				f.setFilePath(filePath);
				f.setFileStatus(FileStatus.ACTIVE);
				f.setFileType(FileType.getFileType(fileType));
				f.setId(fileId);
				f.setSize(fileSize);
				f.setUploadTime(currentTime);
				f.setUserId(userId);

				if (thumbnail != null && thumbnail.getSize() > 0) {
					String thumbnailId = generateFileId();
					String thumbnailExtension = StringUtils.getFilenameExtension(thumbnail.getOriginalFilename());
					String thumbnailPath = fileConfig.getPath() + "/" + thumbnailId + "." + thumbnailExtension;
					f.setThumbnailId(thumbnailId);
					f.setThumbnailPath(thumbnailPath);
					byte[] thumbnailData;
					thumbnailData = thumbnail.getBytes();
					FileUtils.saveFile(thumbnailPath, thumbnailData);
				}
				byte[] fileData = file.getBytes();
				FileUtils.saveFile(filePath, fileData);
				FileUploadResponse fileUploadResponse = fileToFileUpload(f);
				return fileUploadResponse;
			}
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	public List<FileUploadResponse> getByUserId(String userId, String fileType) {
		List<File> files = fileRepository.findByUserId(userId, fileType);
		List<FileUploadResponse> responses = files.stream().map(this::fileToFileUpload).collect(Collectors.toList());
		return responses;
	}

	public Map<String, List<FileUploadResponse>> getByUserIds(List<String> userIds, String fileType) {
		Map<String, List<File>> mapFiles = fileRepository.findByUserIds(userIds, fileType);
		Map<String, List<FileUploadResponse>> mapResponses = new HashMap<String, List<FileUploadResponse>>();
		for(Entry<String, List<File>> entry:mapFiles.entrySet()) {
			mapResponses.put(entry.getKey(), entry.getValue().stream().map(this::fileToFileUpload).collect(Collectors.toList()));
		}
		return mapResponses;
	}

	public FileUploadResponse getByFileId(String fileId) {
		File f = fileRepository.findById(fileId);
		if (f == null) {
			return null;
		}
		FileUploadResponse fileUploadResponse = fileToFileUpload(f);
		return fileUploadResponse;
	}

	public List<FileUploadResponse> getByFileIds(List<String> ids) {
		List<File> files = fileRepository.findByIds(ids);
		return files.stream().map(this::fileToFileUpload).collect(Collectors.toList());
	}

	private FileUploadResponse fileToFileUpload(File f) {
		FileUploadResponse fileUploadResponse = new FileUploadResponse();
		fileUploadResponse.setFileId(f.getId());
		fileUploadResponse.setFileStatus(f.getFileStatus());
		fileUploadResponse.setFileThumbnailUrl(f.getThumbnailPath());
		fileUploadResponse.setFileType(f.getFileType());
		fileUploadResponse.setFileUrl(f.getFilePath());
		return fileUploadResponse;
	}

	private String generateFileId() {
		return ObjectId.get().toHexString();
	}
}
