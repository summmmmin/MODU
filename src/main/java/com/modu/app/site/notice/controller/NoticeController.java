package com.modu.app.site.notice.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.modu.app.prj.file.service.FileVO;
import com.modu.app.prj.user.service.UserVO;
import com.modu.app.site.notice.service.NoticeService;
import com.modu.app.site.notice.service.NoticeVO;

import oracle.jdbc.proxy.annotation.Post;

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
	
	// 사이트 공지사항 단건조회
	@GetMapping("/noticePage")
	public String selectNotice (Model model, String noticeUniNo) {
		model.addAttribute("notice", noticeService.selectNotice(noticeUniNo));
		return "notice/noticePage";
	}

	// 사이트 공지사항 등록폼
	@GetMapping("noticeInsert")
	public String noticeInsertForm(Model model) {
		NoticeVO noticeVO = new NoticeVO();
		model.addAttribute("notice", noticeVO);
		return "notice/noticeInsert";
	}
	
	// 사이트 공지사항 등록처리
	@PostMapping("noticeInsert")
	public String noticeInsert(NoticeVO noticeVO, HttpSession session) {
		UserVO vo = (UserVO) session.getAttribute("user");
		noticeVO.setMembUniNo(vo.getMembUniNo());
		
		//공지사항등록
		noticeService.insertNotice(noticeVO);
		
		//첨부xxx
		
		return "redirect:/noticeList";
	}
	
	// 사이트 공지사항 수정폼
	@GetMapping("noticeUpdate")
	public String noticeUpdateForm(Model model, String noticeUniNo) {
		model.addAttribute("notice",noticeService.selectNotice(noticeUniNo));
		return "notice/noticeUpdate";
	}
	
	// 사이트 공지사항 수정처리
	@PostMapping("noticeUpdate")
	public String noticeUpdate(NoticeVO noticeVO) {
		noticeService.updateNotece(noticeVO);
		return "redirect:/noticeList";
	}
	
	// 사이트 공지사항 삭제
	@PostMapping("noticeDelete")
	@ResponseBody
	public String noticeDelete(@RequestBody String noticeUniNo) {
		noticeService.deleteNotice(noticeUniNo);
		return noticeUniNo;
	}
}
