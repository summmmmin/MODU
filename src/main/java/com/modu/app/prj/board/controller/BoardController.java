package com.modu.app.prj.board.controller;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.modu.app.prj.board.service.BoardService;


@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
//	@GetMapping("index")
//	public String index() {
//		return "index";
//	}
	
	//Brd테이블 전체 조회
	@GetMapping("index")
	public String BoardList(Model model) {
		model.addAttribute("boardList",boardService.BoardList());
		return "index";
	}
}