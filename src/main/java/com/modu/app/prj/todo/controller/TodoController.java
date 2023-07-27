package com.modu.app.prj.todo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.modu.app.cmmn.service.CmmnService;
import com.modu.app.prj.file.service.FileService;
import com.modu.app.prj.file.service.FileVO;
import com.modu.app.prj.prj.service.PrjService;
import com.modu.app.prj.prj.service.PrjVO;
import com.modu.app.prj.todo.service.TodoService;
import com.modu.app.prj.todo.service.TodoVO;
import com.modu.app.prj.user.service.UserVO;

// 김동건 07/21 할일 관리
@Controller
public class TodoController {
	
	@Autowired
	TodoService todoService;
	
	@Autowired
	CmmnService cmmnService;
	
	@Autowired
	FileService fileService; //첨부파일용
	
	@Autowired
	PrjService prjService;
	
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
	public String todoInsertForm(HttpSession session,Model model) {
		PrjVO vo = new PrjVO();
		vo.setPrjUniNo((String) session.getAttribute("prjUniNo"));
		model.addAttribute("membList", prjService.getPrjPartiList(vo));
		model.addAttribute("todo",new TodoVO());
		return "todo/todoInsert";
	}
	
	
	//todo 등록
	@PostMapping("todoInsert")
	@ResponseBody
	public TodoVO todoInsert(HttpSession session,TodoVO vo, @RequestParam("file") MultipartFile[] file, @RequestParam("ttl") String ttl,
			@RequestParam("cntn") String cntn,	@RequestParam("frDt") String Date, @RequestParam("toDt") String lastDate) throws ParseException {
		System.out.println(vo);
		System.out.println(file);
		UserVO userVo = (UserVO) session.getAttribute("user");
		vo.setWriter((String) session.getAttribute("particiMembUniNo"));
		vo.setPrjUniNo((String) session.getAttribute("prjUniNo"));
		vo.setTtl(ttl);
		vo.setCntn(cntn);
		vo.setFrDt(Date);
		vo.setToDt(lastDate);
		
		
		todoService.insertTodo(vo);
		
		FileVO fileVO = new FileVO();
		fileVO.setTodoUniNo(vo.getTodoUniNo());
		fileVO.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
		fileService.insertFile(file, fileVO);
		return vo;
	}
	
	
	
	//투표 단건 페이지
	@GetMapping("todoInfo/{todoNo}")
	public String todoInfo(HttpSession session,Model model,@PathVariable String todoNo){
		model.addAttribute("todoInfo",todoService.oneTodo(todoNo));
		model.addAttribute("pctList",cmmnService.getCmmn("퍼센트"));
		System.out.println(model.getAttribute("pctList"));
		System.out.println(model.getAttribute("todoInfo"));
		return "todo/todoInfo";	
	}
	
	
	
}
