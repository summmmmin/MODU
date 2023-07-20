package com.modu.app.prj.file.service.impl;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.modu.app.common.FileUtill;
import com.modu.app.prj.file.mapper.FileMapper;
import com.modu.app.prj.file.service.FileService;
import com.modu.app.prj.file.service.FileVO;
import com.modu.app.prj.post.service.PostVO;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	FileMapper fileMapper;

	// 첨부파일등록
	@Override
	public int insertFile(MultipartFile[] files, PostVO postvo) {

		for (MultipartFile file : files) {

			// 첨부파일 있을 때
			if (file != null && !file.isEmpty()) {
				long fileSize = file.getSize();
				String fileExtension = FileUtill.getFileExtension(file.getOriginalFilename());
				String uuid = UUID.randomUUID().toString();
				String uploadFileName = uuid + "_" + file.getOriginalFilename();
				String saveName = uploadFileName;

				FileVO fileVO = new FileVO();
				fileVO.setPostUniNo(postvo.getPostUniNo());
				fileVO.setParticiMembUniNo(postvo.getParticiMembUniNo());
				fileVO.setAttNm(file.getOriginalFilename());
				fileVO.setServerAttNm(saveName);
				fileVO.setFSize(fileSize);
				fileVO.setExt(fileExtension);

				fileMapper.insertFile(fileVO); // 파일이름DB에저장
				
				//업로드할폴더
				String folderPath = FileUtill.makeFolder();
				Path savePath = Paths.get(folderPath);

				try {
					file.transferTo(savePath);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

		return 1;

	}

}
