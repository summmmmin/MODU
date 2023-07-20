package com.modu.app.prj.todo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modu.app.prj.todo.service.TodoService;
import com.modu.app.prj.todo.service.TodoVO;
import com.modu.app.prj.user.service.UserVO;

@RestController
public class TodoRestContorller {
	
	@Autowired
	TodoService todoService;
	
	
	
	//전체목록
//		@GetMapping("todoList")
//		public List<TodoVO> todoList(Model model, TodoVO vo,HttpServletRequest request) {
//			HttpSession session = request.getSession();
//			UserVO userVo = (UserVO) session.getAttribute("user");
//			vo.setCm(userVo.getNm());
//			vo.setMgr(userVo.getNm());
//			vo.setPrjUniNo((String) session.getAttribute("prjUniNo"));
//			System.out.println(vo);
//			model.addAttribute("todoList", todoService.todoList(vo));
//			return todoService.todoList(vo);
//		}
//	
}
