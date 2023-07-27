package com.modu.app.prj.todo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.modu.app.prj.file.service.FileService;
import com.modu.app.prj.file.service.FileVO;
import com.modu.app.prj.todo.service.TodoService;
import com.modu.app.prj.todo.service.TodoVO;

@RestController
public class TodoRestContorller {
	
	@Autowired
	TodoService todoService;
	
	@Autowired
	FileService fileService;
	
	//할일 진행도 수정
	@PostMapping("udpatePct")
	public int updatePct(@RequestBody TodoVO vo) {	
		return todoService.udpatePercent(vo);
	}
	
	// 첨부파일조회?
	@GetMapping("attPost/{todoUniNo}")
	public List<FileVO> fileListWithPost(@PathVariable("todoUniNo") String todoUniNo){
		FileVO fileVO = new FileVO();
		fileVO.setTodoUniNo(todoUniNo);
		return fileService.fileList(fileVO);
	}
	
	
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
