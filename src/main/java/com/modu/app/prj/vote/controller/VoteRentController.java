package com.modu.app.prj.vote.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modu.app.prj.vote.service.VoteService;
import com.modu.app.prj.vote.service.VoteVO;

@RestController
public class VoteRentController {

	
	@Autowired
	VoteService voteService;
	
	//투표 리스트 페이지
	@GetMapping("votes")
	public List<VoteVO> voteList(VoteVO vo){
		return voteService.voteList(vo);
	}
	
	
}
