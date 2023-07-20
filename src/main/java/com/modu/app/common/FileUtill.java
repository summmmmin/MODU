package com.modu.app.common;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileUtill {
	
	//파일업로드경로
	@Value("${file.upload.path}")
	public String uploadPath;
	
	

	public static String makeFolder() {

		String folderPath = "upload".replace("/", File.separator);
		File uploadPathFoler = new File(folderPath);
		// File newFile= new File(dir,"파일명");
		
		if (uploadPathFoler.exists() == false) {
			uploadPathFoler.mkdirs();
		}
		return folderPath;
	}

	// 파일 확장자 추출 메소드 (동일한 코드를 FileController와 PostController에서 사용)
	public static String getFileExtension(String filename) {
		if (filename == null)
			return null;
		int extensionIndex = filename.lastIndexOf(".");
		return (extensionIndex == -1) ? null : filename.substring(extensionIndex + 1).toLowerCase();
	}
}
