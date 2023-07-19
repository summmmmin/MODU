package com.modu.app.prj.file.mapper;

import com.modu.app.prj.file.service.FileVO;

public interface FileMapper {
	
	//첨부파일저장
	public int insertFile(FileVO fileVO);
	
}
