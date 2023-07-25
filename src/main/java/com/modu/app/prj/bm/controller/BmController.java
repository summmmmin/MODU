package com.modu.app.prj.bm.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.modu.app.prj.bm.service.BmService;
import com.modu.app.prj.bm.service.BmVO;
import com.modu.app.prj.todo.service.TodoVO;
import com.modu.app.prj.user.service.UserVO;


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
	public int BmInsert(@RequestBody BmVO vo) {
		int result = bmService.BmInsert(vo);
		if(result == 1) {
			return 1;
		}else if(result == 2){
			return 2;
		}else
		return 0;
	}
	
	//즐겨찾기 리스트 페이지
	@GetMapping("bm")
	public String bm() {
		return "bm/bmList";
	}
	
	//즐겨찾기 리스트 출력
	@GetMapping("bmList")
	@ResponseBody
	public List<BmVO> bmList(BmVO vo,HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		UserVO userVo = (UserVO) session.getAttribute("user");
		vo.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
		return bmService.BmList(vo);
	}
	
	
}