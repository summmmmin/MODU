package com.modu.app.prj.bm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.modu.app.prj.bm.service.BmService;
import com.modu.app.prj.bm.service.BmVO;


//2023-07-20 즐겨찾기 관리 김성현 
@Controller
public class BmController {

	@Autowired
	BmService bmService;

	// 게시판 즐겨찾기 등록
	@PostMapping("BrdBmInsert")
	@ResponseBody
	public BmVO BrdBmInsert(@RequestBody BmVO vo) {
		bmService.BrdBmInsert(vo);
		return vo;
	}
	

	//파일, 채팅, 댓글 즐겨찾기 등록
	@PostMapping("BmInsert")
	@ResponseBody
	public BmVO BmInsert(@RequestBody BmVO vo) {
		bmService.BmInsert(vo);
		return vo;
	}
	
}