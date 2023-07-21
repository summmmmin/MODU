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

	// 즐겨찾기 추가
	@PostMapping("BrdBmInsert")
	@ResponseBody
	public BmVO BrdBmInsert(@RequestBody BmVO vo) {
		bmService.BrdBmInsert(vo);
		System.out.println("asdasdasdasdasdlma;lsdm;alsmlams;dlmsaldmaknsoindaonsd");
		System.out.println(vo);
		return vo;
	}
}