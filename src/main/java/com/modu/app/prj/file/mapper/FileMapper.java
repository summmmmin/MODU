package com.modu.app.prj.file.mapper;

import java.util.List;

import com.modu.app.prj.file.service.FileVO;

public interface FileMapper {
	
	//첨부파일저장
	public int insertFile(FileVO fileVO);
	
	//첨부파일단건조회
	public FileVO findFileById(String attNo);
	
	//첨부파일리스트
	public List<FileVO> fileList(FileVO fileVO);
}
