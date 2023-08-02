package com.modu.app.prj.vote.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.modu.app.prj.prj.service.PrjVO;
import com.modu.app.prj.vote.service.VoteService;
import com.modu.app.prj.vote.service.VoteVO;

@RestController
public class VoteRestController {

	
	@Autowired
	VoteService voteService;
	
	// 프로젝트 삭제
	@DeleteMapping("voteDelte")
	public int delVote(@RequestBody VoteVO vo) { //@RequestBody 할때는 무조건 VO, 객체로 받아야한다.
			if(voteService.voteDelete(vo.getVoteNo()) > 0) {
				// 삭제 성공
				return 1;
			}else {
				return 2;
			}
	}
	
}
