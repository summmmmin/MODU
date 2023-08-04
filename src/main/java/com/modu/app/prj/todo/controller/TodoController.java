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
		
		//Todo는 담당자나 참가자가 본인인 경우에만 나타나도록 매퍼를 구성해놨음.
		HttpSession session = request.getSession();
		vo.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
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
	public TodoVO todoInsert(HttpSession session, @RequestParam(value="file",required=false) MultipartFile[] file, @RequestParam("ttl") String ttl,
			@RequestParam("cntn") String cntn,	@RequestParam("frDt") String Date, @RequestParam("toDt") String lastDate,@RequestParam("cm") String cm, @RequestParam("mgr") String mgr) throws ParseException {
		TodoVO vo = new TodoVO();
		vo.setWriter((String) session.getAttribute("particiMembUniNo"));
		vo.setPrjUniNo((String) session.getAttribute("prjUniNo"));
		vo.setTtl(ttl);
		vo.setCntn(cntn);
		vo.setFrDt(Date);
		vo.setCm(cm);
		vo.setMgr(mgr);
		vo.setToDt(lastDate);

		todoService.insertTodo(vo);// 할일 만들면서 시퀀스로 생긴 고유번호를
		
		System.out.println(vo);
		
		if (file != null) {
		FileVO fileVO = new FileVO();
		fileVO.setTodoUniNo(vo.getTodoUniNo()); 
		fileVO.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
		fileService.insertFile(file, fileVO);
		}
		return vo;
	}
	
	
	
	//할일 단건 페이지
	@GetMapping("todoInfo/{todoUniNo}")
	public String todoInfo(HttpSession session,TodoVO vo,Model model,@PathVariable String todoUniNo){
		//할일 번호로 파일을 보기.
		FileVO fileVO = new FileVO(); 
		fileVO.setTodoUniNo(todoUniNo);
		
		vo.setPrjUniNo((String) session.getAttribute("prjUniNo"));
		vo.setCm((String) session.getAttribute("particiMembUniNo"));
		vo.setMgr((String) session.getAttribute("particiMembUniNo"));
		vo.setTodoUniNo(todoUniNo);
		
		model.addAttribute("todoInfo",fileService.fileList(fileVO)); //todo파일 조회
		model.addAttribute("todoInfo",todoService.oneTodo(vo));		 //todo 한개의 정보 (담당자와 참가자는 partici로 들어가지만 함수를 이용해서 닉네임화한것들)
		model.addAttribute("pctList",cmmnService.getCmmn("퍼센트"));	 //공통코드 퍼센트 나열을 위한것
		//담당자만 수정이 가능 -> html 단에서 현재 로그인 한사람의 partici == 담당자 partici일시 수정삭제 버튼등장
		//담당자의 조회 정보를 model 따로 담아서보냄
		model.addAttribute("mgrCheck",todoService.mgrCheck(todoUniNo));
		return "todo/todoInfo";	
	}
	
	
	//할일 수정 페이지 이동
	@GetMapping("todoUpdate/{todoUniNo}")
	public String todoUpdateForm(HttpSession session,Model model,@PathVariable String todoUniNo){
		PrjVO vo = new PrjVO();
		TodoVO todoVo = new TodoVO();
		FileVO fileVO = new FileVO();
		fileVO.setTodoUniNo(todoUniNo);
		
		vo.setPrjUniNo((String) session.getAttribute("prjUniNo"));
		todoVo.setTodoUniNo(todoUniNo);
		vo.setPrjUniNo((String) session.getAttribute("prjUniNo"));
		model.addAttribute("membList", prjService.getPrjPartiList(vo));
		model.addAttribute("todoInfo",todoService.oneTodo(todoVo));
		model.addAttribute("attList", fileService.fileList(fileVO)); 
		model.addAttribute("todoUniNo",todoUniNo);	
		model.addAttribute("todo",new TodoVO());
		model.addAttribute("mgrcm",todoService.mgrCmCheck(todoUniNo));
		System.out.println(model.getAttribute("mgrcm"));
		return "todo/todoUpdate";
	}
	
	
	//할일 수정
	@PostMapping("updateTodo")
	@ResponseBody
	public int updateTodo(HttpSession session, @RequestParam(value="file",required=false) MultipartFile[] file, @RequestParam("ttl") String ttl,
			@RequestParam("cntn") String cntn,	@RequestParam("frDt") String Date, @RequestParam("toDt") String lastDate
			,@RequestParam("cm") String cm, @RequestParam("mgr") String mgr,
			@RequestParam("todoUniNo") String todoUniNo) throws ParseException {
		
		
		System.out.println("111111111111111111111111111111111111111111111111");
		TodoVO vo = new TodoVO();
		UserVO userVo = (UserVO) session.getAttribute("user");
		vo.setWriter((String) session.getAttribute("particiMembUniNo"));
		vo.setPrjUniNo((String) session.getAttribute("prjUniNo"));
		vo.setTtl(ttl);
		vo.setCntn(cntn);
		vo.setFrDt(Date);
		vo.setToDt(lastDate);
		vo.setCm(cm);
		vo.setCntn(cntn);
		vo.setTodoUniNo(todoUniNo);
		
		System.out.println(vo);
		todoService.updateTodo(vo);
		
		if (file != null) {
			FileVO fileVO = new FileVO();
			fileVO.setTodoUniNo(vo.getTodoUniNo()); 
			fileVO.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
			fileService.insertFile(file, fileVO);
			}
		
		return 1;
	}
	
			
}
