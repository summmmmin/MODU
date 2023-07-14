package com.modu.app.prj.vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.modu.app.prj.vote.service.VoteService;
import com.modu.app.prj.vote.service.VoteVO;

@Controller
public class VoteController {
	
	@Autowired
	VoteService voteService;
	
	
	// 페이지
	@GetMapping("voteInsert")
	public String voteInsert(Model model,VoteVO vo) {
		return "vote/voteInsert";
	}
}
