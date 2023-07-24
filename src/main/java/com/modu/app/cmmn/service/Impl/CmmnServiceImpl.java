package com.modu.app.cmmn.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.app.cmmn.mapper.CmmnMapper;
import com.modu.app.cmmn.service.CmmnService;
import com.modu.app.cmmn.service.CmmnVO;

@Service
public class CmmnServiceImpl implements CmmnService {

	@Autowired
	CmmnMapper cmmnMapper;
	
	@Override
	public List<CmmnVO> getCmmn(String grpCd) {
		return cmmnMapper.getCmmn(grpCd);
	}

}
