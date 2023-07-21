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

// 김동건 07/21 할일 관리
@Controller
public class TodoController {
	
	@Autowired
	TodoService todoService;
	
	
	//할일 리스트 페이지	
	@GetMapping("todo")
	public String todo() {
		return "todo/todoList";
	}
	
	//할일 리스트 출력
	@GetMapping("todoList")
	@ResponseBody
	public List<TodoVO> todoList(TodoVO vo,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVO userVo = (UserVO) session.getAttribute("user");
		vo.setPrjUniNo((String) session.getAttribute("prjUniNo"));
		vo.setCm((String) session.getAttribute("particiMembUniNo"));
		vo.setMgr((String) session.getAttribute("particiMembUniNo"));
		return todoService.todoList(vo);
	}

	//할일 등록 페이지 이동
	@GetMapping("todoInsert")
	public String todoInsertForm(HttpSession session) {
		
		return "todo/todoInsert";
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
	
	//
	
}
