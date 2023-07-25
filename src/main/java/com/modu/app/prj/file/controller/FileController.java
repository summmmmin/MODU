package com.modu.app.prj.file.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    //img파일 미리보기??
    @GetMapping("/images/{attNo}") //이걸로대체됨?
    @ResponseBody
    public Resource showImage(String attNm, @PathVariable String attNo) throws MalformedURLException {
    	attNm = fileService.findFileById(attNo).getServerAttNm();
        return new UrlResource("file:" + (uploadPath+"/upload/") + attNm);
    }
    
    //첨부파일단건삭제(DB에서이름만삭제하는거임)
    @GetMapping("/delFile/{attNo}")
    @ResponseBody
    public int deleteFile(@PathVariable String attNo) {
    	fileService.deleteFile(attNo);
    	return 1;
    }
    
    //서버에서? 파일삭제?
//    @PostMapping("/removeFile")
//    public ResponseEntity<Boolean> removeFile(String attNo){
//    	FileVO fileVO = fileService.findFileById(attNo); //파일단건조회
//    	String fileName = fileVO.getServerAttNm();
//        //String srcFileName = null;
//
//        //srcFileName = URLDecoder.decode(fileName,"UTF-8");
//		//UUID가 포함된 파일이름을 디코딩해줍니다.
//		File file = new File(uploadPath + File.separator + "upload", fileName);
//		boolean result = file.delete();

//            File thumbnail = new File(file.getParent()+file.getName());
//            //getParent() - 현재 File 객체가 나태내는 파일의 디렉토리의 부모 디렉토리의 이름 을 String으로 리턴해준다.
//            result = thumbnail.delete();
//		if(result) {
//			fileService.deleteFile(attNo);
//		}
//		return new ResponseEntity<>(result,HttpStatus.OK);
//    }
    
    @PostMapping("/removeFile")
    public ResponseEntity<Boolean> removeFile(@RequestBody String attNo) {
        FileVO fileVO = fileService.findFileById(attNo); // 파일 단건 조회
        if (fileVO == null) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
        String fileName = fileVO.getServerAttNm();
        try {
            File file = new File(uploadPath + File.separator + "upload", fileName);
            if (file.exists()) {
                boolean result = file.delete(); // 서버에서 파일 삭제
                if (result) {
                    // 파일 삭제가 성공한 경우에만 DB에서 파일 정보를 삭제합니다.
                    fileService.deleteFile(attNo);
                }
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                // 파일이 서버에 존재하지 않는 경우
                return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
