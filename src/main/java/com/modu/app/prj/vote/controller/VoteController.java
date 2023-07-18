package com.modu.app.prj.vote.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.modu.app.prj.board.service.BoardService;
import com.modu.app.prj.board.service.BoardVO;
import com.modu.app.prj.vote.service.VoteService;
import com.modu.app.prj.vote.service.VoteVO;

@Controller
public class VoteController {
	
	@Autowired
	VoteService voteService;
	
	@Autowired
	BoardService boardService;
	
	
	//투표 리스트 페이지
	@GetMapping("voteList")
	public String empList(Model model,VoteVO vo,HttpServletRequest request) {
		HttpSession session = request.getSession();
		vo.setPrjUniNo((String) session.getAttribute("prjUniNo"));
		vo.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
		System.out.println(vo);
		model.addAttribute("voteList",voteService.voteList(vo));
		return "vote/voteList";
	}
	
	// 투표 등록페이지
	@GetMapping("voteInsert")
	public String voteInsert(Model model,VoteVO vo,HttpServletRequest request) {
		model.addAttribute("VoteVO",vo);
		HttpSession session = request.getSession();
		BoardVO brd = new BoardVO();
		brd.setPrjUniNo((String) session.getAttribute("prjUniNo"));
		brd.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
		System.out.println(brd);
		model.addAttribute("Brd1", boardService.BoardList(brd));
		return "vote/voteInsert";
	}
}
