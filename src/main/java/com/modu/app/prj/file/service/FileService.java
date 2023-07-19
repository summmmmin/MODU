package com.modu.app.prj.file.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	// 첨부파일등록
	public int insertFile(MultipartFile file, long fileSize, String fileExtension, String particiMembUniNo);
}
