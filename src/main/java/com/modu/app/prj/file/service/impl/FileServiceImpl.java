package com.modu.app.prj.file.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.modu.app.common.FileUtill;
import com.modu.app.prj.file.mapper.FileMapper;
import com.modu.app.prj.file.service.FileService;
import com.modu.app.prj.file.service.FileVO;
import com.modu.app.prj.post.service.PostVO;

@Service
public class FileServiceImpl implements FileService {
	
	//파일업로드경로
	@Value("${file.upload.path}")
	public String uploadPath;
	
	@Autowired
	FileMapper fileMapper;

	// 첨부파일등록(게시글)
	@Override
	public int insertFile(MultipartFile[] files, FileVO fileVO) {

		for (MultipartFile file : files) {
			// 첨부파일 있을 때
			if (file != null && !file.isEmpty()) {
				long fileSize = file.getSize();
				String fileExtension = FileUtill.getFileExtension(file.getOriginalFilename());
				String uuid = UUID.randomUUID().toString();
				String uploadFileName = uuid + "_" + file.getOriginalFilename();
				String saveName = uploadFileName;

				//FileVO fileVO = new FileVO();
				//fileVO.setPostUniNo(postvo.getPostUniNo());
				//fileVO.setParticiMembUniNo(postvo.getParticiMembUniNo());
				fileVO.setAttNm(file.getOriginalFilename());
				fileVO.setServerAttNm(saveName);
				fileVO.setFSize(fileSize);
				fileVO.setExt(fileExtension);

				fileMapper.insertFile(fileVO); // 파일이름DB에저장
				
				// modu/upload 폴더에 업로드
				String folderPath = FileUtill.makeFolder();
				Path savePath = Paths.get(uploadPath + File.separator + folderPath, saveName);

				try {
					file.transferTo(savePath);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return 1;
	}
	
	//첨부파일리스트
	@Override
	public List<FileVO> fileList(FileVO fileVO) {
		return fileMapper.fileList(fileVO);
	}
	
	//첨부파일단건조회
	@Override
	public FileVO findFileById(String attNo) {
		return fileMapper.findFileById(attNo);
	}
	
	//첨부파일다운로드시다운로드여부업데이트
	@Override
	public int downloadYN(String attNo) {
		return fileMapper.downloadYN(attNo);
	}
	
	//첨부파일단건삭제(DB에서이름만삭제하는거임)
	@Override
	public int deleteFile(String attNo) {
		return fileMapper.deleteFile(attNo);
	}

	//첨부파일List삭제(DB에서이름만삭제하는거임)
	@Override
	public int deleteFiles(FileVO fileVO) {
		return fileMapper.deleteFiles(fileVO);
	}
}
