package com.modu.app.prj.todo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.app.prj.todo.mapper.TodoMapper;
import com.modu.app.prj.todo.service.TodoService;
import com.modu.app.prj.todo.service.TodoVO;

@Service
public class TodoServiceImpl implements TodoService{

	@Autowired
	TodoMapper todoMapper;
	
	//전체조회
	@Override
	public List<TodoVO> todoList(TodoVO vo) {
		return todoMapper.todoList(vo);
	}
	
	//단건조회
	@Override
	public TodoVO oneTodo(String tno) {
		return todoMapper.oneTodo(tno);
	}

	//할일 생성
	@Override
	public int insertTodo(TodoVO vo) {
		return todoMapper.insertTodo(vo);
	}

	//진행도 변경
	@Override
	public int udpatePercent(TodoVO vo) {
		return todoMapper.udpatePercent(vo);
	}
	
	//할일 수정
	@Override
	public int updateTodo(TodoVO vo) {
		return todoMapper.updateTodo(vo);
	}

	//할일 삭제
	@Override
	public int deleteTodo(String tno) {
		return todoMapper.deleteTodo(tno);
	}

}
