package com.modu.app.prj.board.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.modu.app.prj.board.service.BoardService;
import com.modu.app.prj.board.service.BoardVO;
import com.modu.app.prj.prj.service.PrjService;
import com.modu.app.prj.prj.service.PrjVO;
import com.modu.app.prj.user.service.UserVO;


@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	PrjService prjService;
	
	// 사이드바 게시판 리스트
	@GetMapping("/main")
	public String BoardList(Model model,PrjVO prjVO,HttpServletRequest request){
		HttpSession session = request.getSession();
		prjVO.setMembUniNo(((UserVO) session.getAttribute("user")).getMembUniNo());
		PrjVO vo = prjService.prjSession(prjVO);
	    session.setAttribute("prjUniNo", vo.getPrjUniNo());
	    session.setAttribute("particiMembUniNo", vo.getParticiMembUniNo());
	    BoardVO brd = new BoardVO();
	    brd.setParticiMembUniNo(vo.getParticiMembUniNo());
	    brd.setPrjUniNo(vo.getPrjUniNo());
	    model.addAttribute("Brd",boardService.BoardList(brd));
		return "index";
	}
	
	//단건 조회
//	@GetMapping("index2")
//	public String BoardGet(Model model,BoardVO vo) {
//		model.addAttribute("BoardGet",boardService.GetBoard(vo));
//		return "index";
//	}
	
	@PostMapping("/InsertBoard")
	public String InsertBoard(BoardVO vo) {
		System.out.println("1111");
		System.out.println(vo);
		boardService.InsertBoard(vo);
		return "index";
	}
}