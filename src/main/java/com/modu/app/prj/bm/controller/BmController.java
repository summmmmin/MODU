package com.modu.app.prj.bm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.modu.app.prj.bm.service.BmService;

@Controller
public class BmController {

	@Autowired
	BmService bmService;
	
	
}