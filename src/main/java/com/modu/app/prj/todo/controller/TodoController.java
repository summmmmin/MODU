package com.modu.app.prj.todo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.modu.app.prj.todo.service.TodoService;
import com.modu.app.prj.todo.service.TodoVO;
import com.modu.app.prj.user.service.UserVO;

@Controller
public class TodoController {
	
	@Autowired
	TodoService todoService;
	
	
	//할일 리스트 페이지	
	@GetMapping("todo")
	public String todo(Model model, TodoVO vo,HttpServletRequest request) {
		return "todo/todoList";
	}
	
	//할일 리스트 출력
	@GetMapping("todoList")
	@ResponseBody
	public List<TodoVO> todoList(Model model, TodoVO vo,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVO userVo = (UserVO) session.getAttribute("user");
		vo.setCm(userVo.getNm());
		vo.setMgr(userVo.getNm());
		vo.setPrjUniNo((String) session.getAttribute("prjUniNo"));
		return todoService.todoList(vo);
	}

	
	//todo 등록
	@PostMapping("todoInsert")
	@ResponseBody
	public TodoVO todoInsert(HttpSession session, TodoVO vo) {
		UserVO userVo = (UserVO) session.getAttribute("user");
		vo.setWriter(userVo.getNm());
		vo.setPrjUniNo((String) session.getAttribute("prjUniNo"));
		todoService.insertTodo(vo);
		return vo;
	}
	
<<<<<<< HEAD
=======
	 
	
>>>>>>> branch 'kdg/vote' of https://github.com/happypotatoC/MODU
}
