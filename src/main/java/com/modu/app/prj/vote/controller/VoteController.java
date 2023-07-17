package com.modu.app.prj.vote.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modu.app.prj.vote.service.VoteService;
import com.modu.app.prj.vote.service.VoteVO;

@RestController
public class VoteController {
	
	@Autowired
	VoteService voteService;
	
	
	//투표 리스트 페이지
	@GetMapping("")
	public List<VoteVO> empList(VoteVO vo,HttpServletRequest request) {
		HttpSession session = request.getSession();
		vo.setPrjUniNo((String) session.getAttribute("prjUniNO"));
		vo.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
		return voteService.voteList(vo);
	}
	
	// 투표 등록페이지
	@GetMapping("voteInsert")
	public String voteInsert(Model model,VoteVO vo) {
		
		return "vote/voteInsert";
	}
}
