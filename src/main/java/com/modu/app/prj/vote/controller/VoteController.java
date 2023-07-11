package com.modu.app.prj.vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.modu.app.prj.vote.service.VoteService;

@Controller
public class VoteController {
	
	@Autowired
	VoteService voteService;
	
	//전체조회 페이지
		@GetMapping("voteList")
		public String empList(Model model) {
			//model.addAttribute("empList", empService.getEmpList());
			return "vote/vote";
		}
}
