package com.modu.app.prj.todo.mapper;

import java.util.List;

import com.modu.app.prj.todo.service.TodoVO;

public interface TodoMapper {
	//전체조회
	public List<TodoVO> todoList(TodoVO vo);
	
	//단건조회
	public TodoVO oneTodo(String tno);
	
	//할일 생성
	public int insertTodo(TodoVO vo);
	
	//진행도 변경
	public int udpatePercent(TodoVO vo);
}
