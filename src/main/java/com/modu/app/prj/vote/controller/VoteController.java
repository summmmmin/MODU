package com.modu.app.prj.vote.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.modu.app.prj.board.service.BoardService;
import com.modu.app.prj.board.service.BoardVO;
import com.modu.app.prj.user.service.UserVO;
import com.modu.app.prj.vote.service.MyDataModel;
import com.modu.app.prj.vote.service.VoteDetaVO;
import com.modu.app.prj.vote.service.VoteService;
import com.modu.app.prj.vote.service.VoteVO;

// 김동건 07/20 투표관리
@Controller
public class VoteController {
	
	@Autowired
	VoteService voteService;
	
	@Autowired
	BoardService boardService;
	
	
	//투표 페이지 이동
	@GetMapping("vote")
	public String vote() {
		return "vote/voteList";
	}
	
	//투표 리스트 출력
	@GetMapping("voteList")
	@ResponseBody
	public List<VoteVO> voteList(VoteVO vo,HttpServletRequest request) {
		HttpSession session = request.getSession();
		vo.setPrjUniNo((String) session.getAttribute("prjUniNo"));
		vo.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
		return voteService.voteList(vo);
	}
	
	//투표 등록페이지 이동
	@GetMapping("voteInsert")
	public String todoInsertForm(HttpSession session,Model model){
		BoardVO brd = new BoardVO();
		brd.setPrjUniNo((String) session.getAttribute("prjUniNo"));
		brd.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
		List<BoardVO> boardList = boardService.BoardList(brd);
		List<VoteVO> chatrList=voteService.chatrNm((String) session.getAttribute("particiMembUniNo"));
		System.out.println(boardList);
		
		MyDataModel myDataModel = new MyDataModel();
		myDataModel.setVoteVO(new VoteVO());
		myDataModel.setChatrList(chatrList);
		myDataModel.setBoardList(boardList);
		
		
		model.addAttribute("vote", myDataModel);
		return "vote/voteInsert";
	}
	
	// 투표 등록
	@PostMapping("voteInsert")
	@ResponseBody
	public String voteInsert(HttpSession session,@RequestBody VoteVO vote) {
		vote.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
		vote.setPrjUniNo((String) session.getAttribute("prjUniNo"));
		voteService.voteInsert(vote);
		return "success";
	}
	
	
	// 투표 단건
	@GetMapping("voteInfo/{voteNo}")
	public String voteInfo(HttpSession session,Model model,VoteVO vo, @PathVariable String voteNo){
		vo.setPrjUniNo((String) session.getAttribute("prjUniNo"));
		vo.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
		if(voteNo != null) {
			vo.setVoteNo(voteNo);
		}
		model.addAttribute("voteInfo",voteService.voteOne(vo));
		model.addAttribute("item",voteService.voteItem(voteNo));
		System.out.println(model.getAttribute("item"));;
		return "vote/voteInfo";
	}
	
	@PostMapping("voteInfo/{voteNo}")
	@ResponseBody
	public Map<String,Object> voteDO(HttpSession session,@RequestBody VoteDetaVO vo,@PathVariable String voteNo){
		vo.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
		vo.setVoteNo(voteNo);
		System.out.println(vo);
		Map<String, Object> map = new HashMap<>();
		int result = voteService.voteDo(vo);;
		
		//변경 성공 여부
		if(result > 0) {
			map.put("retCode", "Success");
			map.put("data", vo);
		}else {
			map.put("retCode", "Fail");
		}
		return map;	
	}
	// map 여러가지 넘기기

	// 투표 단건
		@GetMapping("voteResult/{voteNo}")
		public String voteInfo2(HttpSession session,Model model,VoteVO vo, @PathVariable String voteNo){
			vo.setPrjUniNo((String) session.getAttribute("prjUniNo"));
			vo.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
			if(voteNo != null) {
				vo.setVoteNo(voteNo);
			}
			model.addAttribute("voteInfo",voteService.voteOne(vo));
			model.addAttribute("item",voteService.voteItem(voteNo));
			System.out.println(model.getAttribute("item"));;
			return "vote/voteResult";
		}
	
	
	// 투표 등록	
//	@ResponseBody
//	@PostMapping("voteInsert")
//	public String voteInsert(VoteVO vo,HttpServletRequest request) {
//		model.addAttribute("VoteVO",vo);
//		HttpSession session = request.getSession();
//		BoardVO brd = new BoardVO();
//		brd.setPrjUniNo((String) session.getAttribute("prjUniNo"));
//		brd.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
//		boardService.BoardList(brd));
//		return "vote/voteInsert";
//	}
}
