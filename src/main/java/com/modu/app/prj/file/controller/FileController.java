package com.modu.app.prj.file.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.modu.app.common.FileUtill;
import com.modu.app.prj.file.service.FileService;
import com.modu.app.prj.file.service.FileVO;

@Controller
public class FileController {
	
	@Autowired
	FileService fileService;
	
	// 첨부파일 다운로드
    @GetMapping("/files/{attNo}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable String attNo) {
        FileVO file = fileService.findFileById(attNo); //파일단건조회
        Resource resource = FileUtill.readFileAsResource(file); //파일리소스조회
        try {
            String filename = URLEncoder.encode(file.getAttNm(), "UTF-8");
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + filename + "\";")
                    .header(HttpHeaders.CONTENT_LENGTH, file.getFSize() + "")
                    .body(resource);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("filename encoding failed : " + file.getAttNm());
        }
    }

}
