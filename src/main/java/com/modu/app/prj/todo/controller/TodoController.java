package com.modu.app.prj.todo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.modu.app.prj.todo.service.TodoService;
import com.modu.app.prj.todo.service.TodoVO;
import com.modu.app.prj.user.service.UserVO;

@Controller
public class TodoController {
	
	@Autowired
	TodoService todoService;
	
	//전체목록
	@GetMapping("todoList")
	public String todoList(Model model, TodoVO vo,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVO userVo = (UserVO) session.getAttribute("user");
		vo.setCm(userVo.getNm());
		vo.setMgr(userVo.getNm());
		vo.setPrjUniNo((String) session.getAttribute("prjUniNo"));
		System.out.println(vo);
		model.addAttribute("todoList", todoService.todoList(vo));
		return "todo/todoList";
	}
	
	//todo 등록
	@GetMapping("todoInsert")
	public String todoInsertForm(Model model, TodoVO vo) {
		model.addAttribute("todo",vo);
		return "todo/todoInsert";
	}
	
	//todo 등록
	@PostMapping("todoInsert")
	public String todoInsert(Model model,HttpServletRequest request ,TodoVO vo) {
		HttpSession session = request.getSession();
		UserVO userVo = (UserVO) session.getAttribute("user");
		vo.setWriter(userVo.getNm());
		vo.setPrjUniNo((String) session.getAttribute("prjUniNo"));
		System.out.println(vo);
		todoService.insertTodo(vo);
		return "redirect:todoList";
	}
	
}
