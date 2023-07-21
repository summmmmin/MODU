package com.modu.app.prj.vote.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modu.app.prj.vote.service.VoteService;
import com.modu.app.prj.vote.service.VoteVO;

@RestController
public class VoteRestController {

	
	@Autowired
	VoteService voteService;
	
//	//투표 리스트 페이지
//	@GetMapping("voteList")
//	public List<VoteVO> voteList(VoteVO vo,Model model,HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		vo.setPrjUniNo((String) session.getAttribute("prjUniNo"));
//		vo.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
//		model.addAttribute("voteList",voteService.voteList(vo));
//		return voteService.voteList(vo);
//	}
	
}
