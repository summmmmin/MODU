package com.modu.app.prj.file.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.modu.app.common.FileUtill;
import com.modu.app.prj.file.service.FileService;
import com.modu.app.prj.file.service.FileVO;

@Controller
public class FileController {
	
	@Autowired
	FileService fileService;
	
	//파일업로드경로
	@Value("${file.upload.path}")
	public String uploadPath;
	
	
	// 첨부파일 다운로드
    @GetMapping("/files/{attNo}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable String attNo) {
        FileVO file = fileService.findFileById(attNo); //파일단건조회
        Resource resource = FileUtill.readFileAsResource(uploadPath+"/upload",file); //파일리소스조회
        
        try {
            String filename = URLEncoder.encode(file.getAttNm(), "UTF-8"); //원래첨부파일이름
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + filename + "\";")
                    .header(HttpHeaders.CONTENT_LENGTH, file.getFSize() + "")
                    .body(resource);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("filename encoding failed : " + file.getAttNm());
        }
    }
    
    //첨부파일다운로드시다운로드여부업데이트
    @PostMapping("/file/{attNo}")
    @ResponseBody
    public int downloadYN(@PathVariable String attNo) {
    	 FileVO file = fileService.findFileById(attNo); //단건조회
    	 if(file.getDnYn() == 'N') { // 다운로드가 한 번도 된 적 없음
    		 fileService.downloadYN(attNo);    		 
    	 }
    	return 1;
    }
    
    //첨부파일단건삭제(DB에서이름만삭제하는거임)
    @GetMapping("/delFile/{attNo}")
    @ResponseBody
    public int deleteFile(@PathVariable String attNo) {
    	fileService.deleteFile(attNo);
    	return 1;
    }

}
