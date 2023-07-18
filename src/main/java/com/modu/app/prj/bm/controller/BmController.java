package com.modu.app.prj.bm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.modu.app.prj.bm.service.BmService;
import com.modu.app.prj.bm.service.BmVO;

@Controller
public class BmController {

	@Autowired
	BmService bmService;
	
	@GetMapping("BmList")
	public String BmList(Model model,BmVO vo) {
		
		List<BmVO> list = bmService.BmList(vo);
		model.addAttribute("BmList",list);
		return "index";
	}
	
	
	
}