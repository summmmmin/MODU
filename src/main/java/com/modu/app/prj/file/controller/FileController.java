package com.modu.app.prj.file.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.modu.app.common.FileUtill;
import com.modu.app.prj.file.service.FileService;
import com.modu.app.prj.file.service.FileVO;
import com.modu.app.prj.post.service.PostService;

@Controller
public class FileController {
	
	@Autowired
	FileService fileService;
	
	@Autowired
	PostService postService;
	
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
    
    //서버에서 파일삭제(단건)
    @PostMapping("/removeFile")
    public ResponseEntity<Boolean> removeFile(@RequestBody String attNo) {
    	
        FileVO fileVO = fileService.findFileById(attNo); // 파일 단건 조회
        
        if (fileVO == null) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
        String fileName = fileVO.getServerAttNm();
        try {
            File file = new File(uploadPath+"/upload" + File.separator, fileName);
            if (file.exists()) {
                boolean result = file.delete(); // 서버에서 파일 삭제
                if (result) {
                    // DB에서 이름 삭제
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
    
    //게시글 삭제시 첨부파일 다 삭제
    @PostMapping("/removeFilesWith")
    @ResponseBody
    public ResponseEntity<Boolean> removeFiles(@RequestBody FileVO fileVO) {
    	
        List<FileVO> files = fileService.fileList(fileVO); // 파일 리스트 조회
        String postUniNo = fileVO.getPostUniNo();
  
        for(FileVO file : files) {
        	if (file == null) {
        		return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        	}
        	String fileName = file.getServerAttNm();
        	try {
        		File filef = new File(uploadPath+"/upload" + File.separator, fileName);
        		
        		if (filef.exists()) {
        			boolean result = filef.delete(); // 서버에서 파일 삭제
        			if (result) {
        				fileService.deleteFile(file.getAttNo()); //DB에서 이름 삭제
        			}
        		} else {
        			// 파일이 서버에 존재하지 않는 경우
        			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        		}
        	} catch (Exception e) {
        		e.printStackTrace();
        		return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        	}
        }
        postService.deletePost(postUniNo); //게시글삭제
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
    @GetMapping("attTodo/{todoUniNo1}")
	public List<FileVO> fileListWithTodo(@PathVariable("todoUniNo1") String todoUniNo){
		FileVO fileVO = new FileVO();
		fileVO.setTodoUniNo(todoUniNo);
		return fileService.fileList(fileVO);
	}
  
}
