package com.modu.app.prj.bm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.modu.app.prj.bm.service.BmService;
import com.modu.app.prj.bm.service.BmVO;

@Controller
public class BmController {

	@Autowired
	BmService bmService;
	
	@GetMapping("bmList")
	public String BmList(Model model,BmVO vo,HttpServletRequest request) {
		HttpSession session = request.getSession();
		vo.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
		model.addAttribute("bmlist",bmService.BmList(vo));
		return "bm/bmList";
	}
	
	@PostMapping("bmInsert")
	public String bmInsert(Model model,BmVO vo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		vo.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
		model.addAttribute("bmInsert",bmService.bmInsert(vo));
		return "index";
	}
	
	
	
}