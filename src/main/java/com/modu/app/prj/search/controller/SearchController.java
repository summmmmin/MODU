package com.modu.app.prj.search.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.modu.app.prj.search.service.SearchService;
import com.modu.app.prj.search.service.SearchVO;

//2023-07-20 즐겨찾기 관리 김성현 
@Controller
public class SearchController {

	@Autowired
	SearchService searchService;

	// 즐겨찾기 리스트 페이지
	@GetMapping("search")
	public String search() {
		return "search/search";
	}

	// 게시글 즐겨찾기 리스트
	@ResponseBody
	@GetMapping("BpList")
	public List<SearchVO> BpList(Model model, SearchVO vo, HttpSession session) {
		vo.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
		List<SearchVO> list = searchService.BpList(vo);
		model.addAttribute("bpList", list);
		return list;
	}

	// 게시글 즐겨찾기 리스트
	@ResponseBody
	@GetMapping("BpListSearch")
	public List<SearchVO> BpList(@RequestParam String ttl, SearchVO vo, HttpSession session) {
		vo.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
		vo.setTtl(ttl);
		List<SearchVO> list = searchService.BpList(vo);
		return list;
	}

	// 채팅 즐겨찾기 리스트
	@ResponseBody
	@GetMapping("ChatList")
	public List<SearchVO> chatList(Model model, SearchVO vo, HttpSession session) {
		vo.setPrjUniNo((String) session.getAttribute("prjUniNo"));
		List<SearchVO> list = searchService.chatList(vo);
		model.addAttribute("chatList", list);
		return list;
	}

	// 채팅 즐겨찾기 리스트
	@ResponseBody
	@GetMapping("ChatListSearch")
	public List<SearchVO> chatList(@RequestParam String cntn, SearchVO vo, HttpSession session) {
		vo.setPrjUniNo((String) session.getAttribute("prjUniNo"));
		vo.setTtl(cntn);
		List<SearchVO> list = searchService.chatList(vo);
		return list;
	}
}