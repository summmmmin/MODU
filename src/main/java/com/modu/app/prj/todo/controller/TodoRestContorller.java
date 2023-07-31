package com.modu.app.prj.todo.controller;


import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.modu.app.prj.file.service.FileService;
import com.modu.app.prj.file.service.FileVO;
import com.modu.app.prj.todo.service.TodoService;
import com.modu.app.prj.todo.service.TodoVO;
import com.modu.app.prj.user.service.UserVO;

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
	
	// 첨부파일조회
	@GetMapping("attTodo/{todoUniNo}")
	public List<FileVO> fileListWithTodo(@PathVariable("todoUniNo") String todoUniNo){
		FileVO fileVO = new FileVO();
		fileVO.setTodoUniNo(todoUniNo);
		return fileService.fileList(fileVO);
	}
	

	//할일 삭제 
	@PostMapping("todoDelte")
	public int deleteTodo(@RequestBody TodoVO vo) {
		if(todoService.deleteTodo(vo.getTodoUniNo())>0) {
			// 삭제 성공
			return 1;
		}else {
			return 2;
		}	
	};

	
}
