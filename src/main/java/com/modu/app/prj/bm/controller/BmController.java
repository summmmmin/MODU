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


//2023-07-20 즐겨찾기 관리 김성현 
@Controller
public class BmController {

	@Autowired
	BmService bmService;

	//즐겨찾기 리스트 출력
	@GetMapping("bmList")
	public String BmList(Model model, BmVO vo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		vo.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
		model.addAttribute("bmList", bmService.BmList(vo));
		return "bm/bmList";
	}

	// 즐겨찾기 추가
	@PostMapping("BrdBmInsert")
	@ResponseBody
	public BmVO BrdBmInsert(@RequestBody BmVO vo) {
		bmService.BrdBmInsert(vo);
		return vo;
	}

	// 즐겨찾기 목록
	@GetMapping("BmSelect")
	@ResponseBody
	public BmVO BmSelect(BmVO vo) {
		bmService.BmList(vo);
		return vo;
	}

}