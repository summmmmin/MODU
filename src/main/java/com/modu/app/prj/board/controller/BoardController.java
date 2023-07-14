package com.modu.app.prj.board.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.modu.app.prj.board.service.BoardService;
import com.modu.app.prj.board.service.BoardVO;


@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	//전체 조회
	@GetMapping("/main")
	public String BoardList(Model model) {
		System.out.println("22222");
		model.addAttribute("BoardList",boardService.BoardList());
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