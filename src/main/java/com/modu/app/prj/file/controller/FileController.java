package com.modu.app.prj.file.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.modu.app.prj.file.service.FileService;



@Controller
public class FileController {
	
	@Autowired
	FileService fileService;
	
	@GetMapping("fileInsert")
	public String fileUplaodPage() {
		return "UPLOAD";
	}
	
	@PostMapping("fileInsert")
	public String insertFile(@RequestParam("file") MultipartFile file, @RequestParam("uniNo") String uniNo) {
		
		//파일사이즈
		long fileSize = file.getSize();
		
		//파일확장자
		String fileExtension = getFileExtension(file.getOriginalFilename());
		if (uniNo.startsWith("b")) {
			
		}
		
		fileService.insertFile(file, fileSize, fileExtension);
		return "success";
	} 
	
	//파일확장자
	private String getFileExtension(String filename) {
        if (filename == null) return null;
        int extensionIndex = filename.lastIndexOf(".");
        return (extensionIndex == -1) ? null : filename.substring(extensionIndex + 1).toLowerCase();
    }


}
