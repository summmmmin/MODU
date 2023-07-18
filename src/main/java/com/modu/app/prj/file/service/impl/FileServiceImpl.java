package com.modu.app.prj.file.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.modu.app.prj.file.mapper.FileMapper;
import com.modu.app.prj.file.service.FileService;
import com.modu.app.prj.file.service.FileVO;

public class FileServiceImpl implements FileService {

	@Autowired
	FileMapper fileMapper;
	
	//첨부파일등록
	@Override
	public int insertFile(FileVO fileVO) {
		return fileMapper.insertFile(fileVO);
	}

}
