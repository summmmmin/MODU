package com.modu.app.prj.prj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modu.app.prj.prj.service.PrjService;
import com.modu.app.prj.prj.service.PrjVO;
import com.modu.app.prj.user.service.UserVO;

@Controller
public class PrjController {
	@Autowired
	PrjService prjService;
	
	@GetMapping("prjInsert")
	public String prjInsertForm(Model model) {
		model.addAttribute("prjVO", new PrjVO());
		return "prj/prjInsert";
	}
	
	@PostMapping("prjInsert")
	public String prjInsert(PrjVO prjVO, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVO vo = (UserVO) session.getAttribute("user");
		prjVO.setMembUniNo(vo.getMembUniNo());
		prjService.insertPrj(prjVO);
		return "redirect:prjList";
	}
	
	// 프로젝트 리스트 페이지
	@GetMapping("prjList")
	public String prjList(String membUniNo, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVO vo = (UserVO) session.getAttribute("user");
		System.out.println(vo);
		model.addAttribute("prjList",prjService.getPrjList(vo.getMembUniNo()));
		return "prj/prjList";
	}
}
