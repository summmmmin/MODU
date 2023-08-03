package com.modu.app.prj.vote.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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
import com.modu.app.prj.vote.service.ListModel;
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
	public String vote(){
		/*
		 * vo.setPrjUniNo((String) session.getAttribute("prjUniNo"));
		 * vo.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
		 * List<VoteVO> list = voteService.voteList(vo); List<VoteDetaVO> count =
		 * voteService.allCount();
		 * 
		 * ListModel listModel = new ListModel(); listModel.setChatrList(list);
		 * listModel.setVoteDataList(count); System.out.println(list);
		 * model.addAttribute("voteList",listModel);
		 * System.out.println(model.getAttribute("voteList"));
		 */
		return "vote/voteList";
	}
	
	
	//투표 리스트 출력
	@GetMapping("voteList")
	@ResponseBody 
	public Map<String, Object> voteList(VoteVO vo,HttpServletRequest request) { 
	
	//투표 리스트를 뽑기위한 데이터를 세션에서 가져와서 넣어줌.
	HttpSession session = request.getSession();
	vo.setPrjUniNo((String) session.getAttribute("prjUniNo"));
	vo.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
	
	
	Map<String, Object> map = new HashMap<>();
	System.out.println(voteService.allCount((String) session.getAttribute("prjUniNo")));	  
	map.put("list",voteService.voteList(vo)); //투표 리스트
	map.put("count",voteService.allCount((String) session.getAttribute("prjUniNo")));	//투표 참여인원수
	map.put("grd", voteService.grdCheck((String) session.getAttribute("particiMembUniNo")));//회원 등급 처리
	System.out.println(map);
		return map; 
	 }
	 
	
	//투표 생성페이지 이동
	@GetMapping("voteInsert")
	public String todoInsertForm(HttpSession session,Model model){
				
	//로그인한 회원이 참여하고있는 프로젝트의 내가 참여하고있는 게시판 제목
	BoardVO brd = new BoardVO();
	brd.setPrjUniNo((String) session.getAttribute("prjUniNo"));
	brd.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
	List<BoardVO> boardList = boardService.BoardList(brd);
	
	//로그인한 회원이 참여하고있는 프로젝트의 참여하고있는 채팅 제목
	List<VoteVO> chatrList=voteService.chatrNm((String) session.getAttribute("particiMembUniNo"));
				
	//for문에 한번에 돌리고싶어서 방법을 모색하던중 한 객체 안에 다 넣어서 처리.
	MyDataModel myDataModel = new MyDataModel();
	myDataModel.setVoteVO(new VoteVO());
	myDataModel.setChatrList(chatrList);
	myDataModel.setBoardList(boardList);
				
				
	model.addAttribute("vote", myDataModel);
	return "vote/voteInsert";
			}
			
	// 투표 생성처리
	@PostMapping("voteInsert")
	@ResponseBody
	public String voteInsert(HttpSession session,@RequestBody VoteVO vote) {
		
	vote.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
	vote.setPrjUniNo((String) session.getAttribute("prjUniNo"));
	
	// 등록 처리하는 서비스
	int result = voteService.voteInsert(vote);
		
		return "success";
	}
	
	
	// 투표 단건(=투표하는 페이지)
	@GetMapping("voteInfo/{voteNo}")
	public String voteInfo(HttpSession session,Model model,VoteVO vo, @PathVariable String voteNo,VoteDetaVO vdvo){
		
		//투표 단건 조회에 필요한 세가지 데이터 넣어주기
		vo.setPrjUniNo((String) session.getAttribute("prjUniNo"));
		vo.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
		if(voteNo != null) {
			vo.setVoteNo(voteNo);
		}
		System.out.println(vo);
		// 모델에 투표 단건조회와 투표항목들 조회하기
		model.addAttribute("voteInfo",voteService.voteOne(vo));
		model.addAttribute("item",voteService.voteItem(voteNo));
		
			
		// 투표를 만든 사람과 프로젝트 관리자는 단건에서 수정 삭제가 가능함
		// 투표를 만든 사람을 알기위해서 model에 값을 넣어줌.
		model.addAttribute("maker",voteService.voteMaker(voteNo));
		
		
		//이미 투표한 장소인지 확인하기 위해 필요한 데이터 넣기
		vdvo.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
		vdvo.setVoteNo(voteNo);
		System.out.println(model.getAttribute("voteInfo"));
		System.out.println(vdvo);
		System.out.println("1111111111111111111");
		System.out.println(model.getAttribute("maker"));
		
		//투표가 마감날짜와 현재날짜를 비교하기 위해 날짜 가져오기
		Date today = new Date(); // 현재날짜 가져오기
		Date toDt = voteService.toDtCheck(voteNo); // 투표 마감날짜
		
		
		
		//1.로그인한 사람이 이미 해당 투표를 진행했거나 이미 마감날짜가 지나면 투표 결과 장소 
		//2.투표를 하지 않았다면 투표하는 장소로 이동
		//로그인 한사람이 get방식으로 1.투표하는 장소로 이동후 
		//post방식으로 투표를 등록한후
		//get방식으로 if문으로 2.투표를 행한 결과 장소로 이동 
		if(voteService.whoVote(vdvo) != null || toDt.before(today)) {
			return "vote/voteResult";
		}else {
			return "vote/voteInfo";
		}
		
	}
	
	//투표 등록
	@PostMapping("voteInfo/{voteNo}")
	@ResponseBody
	public VoteDetaVO voteDO(HttpSession session,@RequestBody VoteDetaVO vo,@PathVariable String voteNo){
		vo.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
		
		//투표 행위가 이루어짐.
		vo.setVoteNo(voteNo);
		System.out.println(vo);
		voteService.voteDo(vo);
		//변경 성공 여부
		return vo;
	}
	
	//투표 마감기간 수정
	@PostMapping("voteUpdate/{voteNo}")
	@ResponseBody
	public VoteVO voteDO(HttpSession session,@RequestBody VoteVO vo,@PathVariable String voteNo){
			
			voteService.voteDate(vo);
			//변경 성공 여부
			return vo;
		}
	
	
// map 여러가지 넘기기 map 공부
//	Map<String, Object> map = new HashMap<>();
//	if(result > 0) {
//		map.put("retCode", "Success");
//		map.put("data", vo);
//	}else {
//		map.put("retCode", "Fail");
//	}
//	return map;	

	
	// 투표 단건
//		@GetMapping("voteResult/{voteNo}")
//		public String voteInfo2(HttpSession session,Model model,VoteVO vo, @PathVariable String voteNo){
//			vo.setPrjUniNo((String) session.getAttribute("prjUniNo"));
//			vo.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
//			if(voteNo != null) {
//				vo.setVoteNo(voteNo);
//			}
//			model.addAttribute("voteInfo",voteService.voteOne(vo));
//			model.addAttribute("item",voteService.voteItem(voteNo));
//			System.out.println(model.getAttribute("item"));;
//			return "vote/voteResult";
//		}
		
	//
	
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