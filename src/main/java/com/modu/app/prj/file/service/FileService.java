package com.modu.app.prj.file.service;

import org.springframework.web.multipart.MultipartFile;

import com.modu.app.prj.post.service.PostVO;
import com.modu.app.prj.todo.service.TodoVO;

public interface FileService {
	
	// 첨부파일등록
	public int insertFileWihtpost(MultipartFile[] files, PostVO postvo);
	
	public int insertFileWihtTodo(MultipartFile[] files, TodoVO todovo);
}
