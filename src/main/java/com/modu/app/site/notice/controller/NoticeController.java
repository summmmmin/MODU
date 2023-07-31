package com.modu.app.site.notice.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.modu.app.prj.file.service.FileVO;
import com.modu.app.site.notice.service.NoticeService;
import com.modu.app.site.notice.service.NoticeVO;

@Controller
public class NoticeController {
	
	@Autowired
	NoticeService noticeService;
	
	// 사이트 공지사항 리스트
	@GetMapping("noticeList")
	public String noticeList(Model model) {
		model.addAttribute("noticeList", noticeService.noticeList());
		return "notice/noticeList";
	}
	
	// 사이트 공지사항 등록폼
	@GetMapping("noticeInsert")
	public String noticeForm(Model model) {
		NoticeVO noticeVO = new NoticeVO();
		model.addAttribute("notice", noticeVO);
		return "notice/noticeInsert";
	}
	
	// 사이트 공지사항 등록처리
	@PostMapping("noticeInsert")
	public String noticeInsert(NoticeVO noticeVO) {
		
		//공지사항등록
		noticeService.insertNotice(noticeVO);
		
		//첨부xxx
		
		return "redirect : /notice/noticeList";
	}

}
