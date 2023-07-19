package com.modu.app.prj.file.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.modu.app.prj.file.mapper.FileMapper;
import com.modu.app.prj.file.service.FileService;
import com.modu.app.prj.file.service.FileVO;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	FileMapper fileMapper;

	//첨부파일등록
	@Override
	public int insertFile(MultipartFile file, long fileSize, String fileExtension, String particiMembUniNo) {
		FileVO fileVO = new FileVO();
        fileVO.setAttNm(file.getOriginalFilename());
        
        //파일서버명생성
        String uuid = UUID.randomUUID().toString();
		String uploadFileName = uuid + "_" + file.getOriginalFilename();
		String saveName =  uploadFileName;
		fileVO.setServerAttNm(saveName);
		
		fileVO.setFSize(fileSize);
	    fileVO.setExt(fileExtension);
	    fileVO.setParticiMembUniNo(particiMembUniNo);
		
		return fileMapper.insertFile(fileVO);
		
	}
	


}
