package com.modu.app.prj.bm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.modu.app.prj.bm.service.BmService;
import com.modu.app.prj.bm.service.BmVO;

@Controller
public class BmController {

	@Autowired
	BmService bmService;

	@GetMapping("bmList")
	public String BmList(Model model, BmVO vo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		vo.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
		model.addAttribute("bmList", bmService.BmList(vo));
		return "bm/bmList";
	}

	// 게시판 생성
	@PostMapping("BrdBmInsert")
	@ResponseBody
	public BmVO BrdBmInsert(@RequestBody BmVO vo) {
		System.out.println(vo);
		bmService.BrdBmInsert(vo);
		System.out.println("11");
		return vo;
	}

	// 즐겨찾기 목록
	@GetMapping("BmSelect")
	@ResponseBody
	public BmVO BmSelect(BmVO vo) {
		System.out.println(vo);
		System.out.println("리스트");
		bmService.BmList(vo);
		return vo;
	}

	// 즐겨찾기 삭제
//	@PostMapping("DeleteBm")
//	@ResponseBody
//	public String DeleteBm(@RequestBody BmVO vo) {
//		bmService.
//		return ;
//	}

}