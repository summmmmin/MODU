package com.modu.app.prj.file.service;

import org.springframework.web.multipart.MultipartFile;

import com.modu.app.prj.post.service.PostVO;

public interface FileService {
	
	// 첨부파일등록
	public int insertFile(MultipartFile[] files, PostVO postvo);
}
