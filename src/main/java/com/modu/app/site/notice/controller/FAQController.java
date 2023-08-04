package com.modu.app.site.notice.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.modu.app.prj.user.service.UserVO;
import com.modu.app.site.notice.service.FAQService;
import com.modu.app.site.notice.service.FAQVO;


@Controller
public class FAQController {
	//파일 업로드 경로
	@Value("${file.upload.path}")
    private String uploadPath; // 파일 저장 경로
	
	@Autowired
	FAQService faqService;
	
	@GetMapping("FAQInsert")
	public String faqInsert() {
		return "faq/FAQInsert";
	}
	
	// 이미지 업로드 후 경로 반환
    @PostMapping("upload-image")
    @ResponseBody
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile image) {
    	System.out.println(image);
        try {
            String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            Path filePath = Paths.get(uploadPath, fileName);
            Files.write(filePath, image.getBytes());

            String imageUrl = "uploaded-images/" + fileName; // 이미지 URL
            System.out.println(imageUrl);
            return ResponseEntity.ok().body(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    // 이미지 경로
    @GetMapping("/uploaded-images/{attNm:.+}")
    @ResponseBody
    public ResponseEntity<Resource> showImage(@PathVariable String attNm) throws MalformedURLException {
        Path imagePath = Paths.get(uploadPath).resolve(attNm);
        Resource imageResource = new UrlResource(imagePath.toUri());
        System.out.println(imagePath);
        if (imageResource.exists() && imageResource.isReadable()) {
            System.out.println("readable");
        	return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageResource);
        } else {
        	System.out.println("else");
            return ResponseEntity.notFound().build();
        }
    }
    
    // faq 작성
    @PostMapping("save-content")
    @ResponseBody
    public ResponseEntity<String> saveContent(@RequestBody FAQVO faq, HttpSession session) {
        try {
        	//로그인한 사람 아이디
        	String membNo = ((UserVO) session.getAttribute("user")).getMembUniNo();
        	faq.setMembUniNo(membNo);
            // 데이터베이스에 저장
        	faqService.insertFAQ(faq);
            
            return ResponseEntity.ok().body("Success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    //FAQ 전체리스트
    @GetMapping("FAQList")
    public String faqList(Model model) {
    	List<FAQVO> list = faqService.faqList();
    	model.addAttribute("faqList", list);
    	
    	return "faq/FAQList";
    }
    
    //FAQ 단건
    @GetMapping("FAQInfo")
    public String faqInfo(Model model, String no) {
    	
    	model.addAttribute("faq", faqService.selectFAQ(no));
    	
    	return "faq/FAQInfo";
    }
}
