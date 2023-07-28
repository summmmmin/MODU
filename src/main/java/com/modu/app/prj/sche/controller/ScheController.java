package com.modu.app.prj.sche.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.modu.app.prj.sche.service.ScheService;

@Controller
public class ScheController {
	
	@Autowired
	ScheService scheService;
	
	@GetMapping("scheList")
	public String ScheList(Model model,HttpSession session) {
		// 리스트 뽑기 , 리스트에 필요한 세션 값가져와서 바로 집어넣음.
		model.addAttribute("scheList",scheService.scheList((String) session.getAttribute("prjUniNo")));
		System.out.println(model.getAttribute("scheList"));
		return "sche/scheList";
	}
	
}
