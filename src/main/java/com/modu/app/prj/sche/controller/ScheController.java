package com.modu.app.prj.sche.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.modu.app.prj.prj.service.PrjService;
import com.modu.app.prj.prj.service.PrjVO;
import com.modu.app.prj.sche.service.ScheService;
import com.modu.app.prj.sche.service.ScheVO;

@Controller
public class ScheController {
	
	@Autowired
	ScheService scheService;
	
	@Autowired
	PrjService prjService;
	
	@GetMapping("scheList")
	public String ScheList(Model model,HttpSession session) {
		// 리스트 뽑기 , 리스트에 필요한 세션 값가져와서 바로 집어넣음.
		PrjVO vo = new PrjVO();
		vo.setPrjUniNo((String) session.getAttribute("prjUniNo"));
		model.addAttribute("scheList",scheService.scheList((String) session.getAttribute("prjUniNo")));
		model.addAttribute("membList", prjService.getPrjPartiList(vo));
		return "sche/scheList";
	}
	
	@GetMapping("scheListFetch")
	@ResponseBody
	public Map<String, Object> scheListFetch(HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		map.put("scheList",scheService.scheList((String) session.getAttribute("prjUniNo")));
		return map;
	}
	
	//단건조회
	@GetMapping("scheInfo/{scheUniNo}")
	@ResponseBody
	public Map<String, Object> scheInfo(@PathVariable String scheUniNo,HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		
		ScheVO vo = new ScheVO();
		vo.setPrjUniNo((String) session.getAttribute("prjUniNo"));
		vo.setScheUniNo(scheUniNo);
		
		map.put("scheInfo",scheService.scheInfo(scheUniNo));
		map.put("partici", scheService.schePartici(scheUniNo)); //일정 참여자 번호/닉네임
		map.put("yetPartici", scheService.yetPartici(vo));
		
		return map;
	}
	

	//수정 모달
	@GetMapping("yetPartici")
	public String schePartici(Model model,HttpSession session,String scheUniNo) {
		PrjVO pvo = new PrjVO();
		pvo.setPrjUniNo((String) session.getAttribute("prjUniNo"));
		
		ScheVO vo = new ScheVO();
		vo.setPrjUniNo((String) session.getAttribute("prjUniNo"));
		vo.setScheUniNo(scheUniNo);
		
		List<String> list3 = new ArrayList<>();
		
		List<PrjVO> pList = prjService.getPrjPartiList(pvo);
		List<ScheVO> sList =  scheService.schePartici(scheUniNo);
        for (PrjVO element1 : pList) {
            for (ScheVO element2 : sList) {
                if (element1.getParticiMembUniNo().equals(element2.getParticiMembUniNo())) {                
                	list3.add(element1.getParticiMembUniNo());
                }
            }
        }
        
        model.addAttribute("scheInfo",scheService.scheInfo(scheUniNo));//
		model.addAttribute("partici",list3);	//현재참여하고있는 사람들 되어야할 아이들
		model.addAttribute("membList", prjService.getPrjPartiList(pvo));// 프로젝트내에 모든 사람들
		//model.addAttribute("partici", scheService.schePartici(scheUniNo)); //일정 참여자 번호/닉네임
		return "sche/test";
	}
	
	   //일정 등록
	   @PostMapping("scheInsert")
	   @ResponseBody
	   public ScheVO scheInsert(HttpSession session, @RequestBody ScheVO vo) {
	      
		  //일정 등록 사람
		  vo.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo"));
	      vo.setPrjUniNo((String) session.getAttribute("prjUniNo"));
	      
	      
	      scheService.scheInsert(vo);
	      
	      //참가자 등록을 위한 새로운 일정 VO 
	      ScheVO scheVO = new ScheVO();
	      scheVO.setScheUniNo(vo.getScheUniNo());
	      
	      if(vo.getParticiMembUniNos() != null) {
	      List<String> membList = vo.getParticiMembUniNos();
	      for(String memb : membList) {
	    	  scheVO.setParticiMembUniNo(memb);
	    	  scheService.scheInsertPartici(scheVO);
	      }
	      }
	      return  vo;
	      
	   }
	   
	   //일정 업데이트
	   @PostMapping("scheUpdate")
	   @ResponseBody
	   public String scheUpdate(HttpSession session, @RequestBody ScheVO vo) {
		   int result = scheService.scheParticiDelete(vo.getScheUniNo());
		   ScheVO scheVO = new ScheVO();
		      scheVO.setScheUniNo(vo.getScheUniNo());
		      
		      if(vo.getParticiMembUniNos() != null) {
		      List<String> membList = vo.getParticiMembUniNos();
		      for(String memb : membList) {
		    	  scheVO.setParticiMembUniNo(memb);
		    	  scheService.scheInsertPartici(scheVO);
		      }
		     
		      }
		   scheService.scheUpdate(vo);
		   
		   return "111";
	   }
	   

	
	//삭제
	@PostMapping("scheDelte")
	@ResponseBody
	public int deleteSche(@RequestBody ScheVO vo) {
		if(scheService.scheDelete(vo.getScheUniNo()) >0) {
			return 1;
		}else {
			return 2;
		}	
	}
}
