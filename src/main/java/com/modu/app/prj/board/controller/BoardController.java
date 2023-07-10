package com.modu.app.prj.board.controller;



import java.util.List;

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
	BoardMapper boardMapper;
	
	@GetMapping("index")
	public String empList(Model model) {
		return "index";
	}
	
	@GetMapping("BoardList")
	public String BoardList(Model model) {
		List<BoardVO> list = boardMapper.BoardList(); 
		return "index";
	}
	
}