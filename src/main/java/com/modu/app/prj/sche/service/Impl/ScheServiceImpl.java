package com.modu.app.prj.sche.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.app.prj.sche.mapper.ScheMapper;
import com.modu.app.prj.sche.service.ScheService;
import com.modu.app.prj.sche.service.ScheVO;

@Service
public class ScheServiceImpl implements ScheService{
	
	@Autowired
	ScheMapper scheMapper;
	
	//프로젝트 리스트
	@Override
	public List<ScheVO> scheList(String prj) {
		return scheMapper.scheList(prj);
	}
	
}
