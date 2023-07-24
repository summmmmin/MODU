package com.modu.app.prj.file.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.modu.app.prj.post.service.PostVO;

public interface FileService {
	
	// 첨부파일등록
	public int insertFileWihtpost(MultipartFile[] files, PostVO postvo);
	
	//첨부파일리스트
	public List<FileVO> fileList(FileVO fileVO);
}
