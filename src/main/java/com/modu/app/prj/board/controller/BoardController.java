package com.modu.app.prj.board.controller;



import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.modu.app.prj.board.mapper.BoardMapper;
import com.modu.app.prj.board.service.BoardService;
import com.modu.app.prj.board.service.BoardVO;


@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("index")
	public String empList(Model model,HttpSession session) {
		return "index";
	}
	
	@GetMapping("BoardList")
	public String BoardList(Model model) {
		model.addAttribute("boardList",boardService.BoardList());
		return "index";
	}
}