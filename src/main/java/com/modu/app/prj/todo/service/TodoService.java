package com.modu.app.prj.todo.service;

import java.util.List;

public interface TodoService {
	//전체조회
	public List<TodoVO> todoList(TodoVO vo);
	
	//단건조회
	public TodoVO oneTodo(String tno);
	
	//할일 생성
	public int insertTodo(TodoVO vo);
	
	//진행도 변경
	public int udpatePercent(TodoVO vo);
	
	//할일 수정
	public int updateTodo(TodoVO vo);
	
	//할일 삭제
	public int deleteTodo(String tno);
}
