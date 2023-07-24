package com.modu.app.common;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import com.modu.app.prj.file.service.FileVO;

@Component
public class FileUtill {
	
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
	
    /**
     * 다운로드할 첨부파일(리소스) 조회 (as Resource)
     * @param file - 첨부파일 상세정보
     * @return 첨부파일(리소스)
     */
    public static Resource readFileAsResource(FileVO file) {
        //String uploadedDate = file.getCreatedDate().toLocalDate().format(DateTimeFormatter.ofPattern("yyMMdd"));
        String filename = file.getServerAttNm();
        Path filePath = Paths.get("/modu/upload", filename);
        
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() == false || resource.isFile() == false) {
                throw new RuntimeException("file not found : " + filePath.toString());
            }
            return resource;
        } catch (MalformedURLException e) {
            throw new RuntimeException("file not found : " + filePath.toString());
        }
    }
}
