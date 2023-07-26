package com.modu.app.prj.search.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.app.prj.search.mapper.SearchMapper;
import com.modu.app.prj.search.service.SearchService;
import com.modu.app.prj.search.service.SearchVO;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	SearchMapper searchMapper;

	@Override
	public List<SearchVO> BpList(SearchVO vo) {
		return searchMapper.BpList(vo);
	}
	
}
