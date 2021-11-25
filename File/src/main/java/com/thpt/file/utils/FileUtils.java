package com.thpt.file.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class FileUtils {

	public static boolean saveFile(String path, byte[] data) {
		File file = null;
		FileOutputStream fileOutputStream = null;
		try {
			file =	new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}
			fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(data);
			return true;
		} catch (Exception e) {
			return false;
		}finally {
			try {
				if(fileOutputStream != null) {
					fileOutputStream.close();
				}
			} catch (Exception e2) {
			}
		}
	}

	public static boolean saveFIle(String path, InputStream inputStream) {
		File file = null;
		FileOutputStream fileOutputStream = null;
		try {
			file =	new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}
			fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(inputStream.readAllBytes());
			return true;
		} catch (Exception e) {
			return false;
		}finally {
			try {
				if(fileOutputStream != null) {
					fileOutputStream.close();
				}
			} catch (Exception e2) {
			}
		}
	}

}
