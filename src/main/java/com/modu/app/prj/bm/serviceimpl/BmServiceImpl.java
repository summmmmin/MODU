package com.modu.app.prj.bm.serviceimpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.app.prj.bm.mapper.BmMapper;
import com.modu.app.prj.bm.service.BmService;
import com.modu.app.prj.bm.service.BmVO;

@Service
public class BmServiceImpl implements BmService {

	@Autowired
	BmMapper bmMapper;
	
	
	//게시판 즐겨찾기 등록
	@Override
	public int BrdBmInsert(BmVO vo) {
		int result = 0;
		// 값 조회
		int BrdbmCount = bmMapper.BrdBmCount(vo);
		// 값이 없으면 등록
		if (BrdbmCount == 0) {
			result = bmMapper.BrdBmInsert(vo);
		// 값이 있으면 삭제
		}else {
			result = bmMapper.BrdBmDelete(vo);
		}
		return result;
	}
	
	//파일, 채팅, 댓글 즐겨찾기 등록
	@Override
	public int BmInsert(BmVO vo) {
		int result = 0;
		//값 조회
		int BmCount = bmMapper.BmCount(vo);
		System.out.println(BmCount);
		System.out.println(vo);
		// 값이 없으면 등록
		if (BmCount == 0) {
			result = bmMapper.BmInsert(vo);
		// 없으면 삭제
		}else {
			result = bmMapper.BmDelete(vo);
		}
		return result;
	}
	
	@Override
	public int BrdBmCount(BmVO vo) {
		return bmMapper.BrdBmCount(vo);
	}
	
	// 게시판 즐겨찾기 삭제
	@Override
	public int BmDelete(BmVO vo) {
		return bmMapper.BrdBmDelete(vo);
	}

	
	@Override
	public List<BmVO> BmList(BmVO vo) {
		return bmMapper.BmList(vo);
	}
	
	@Override
	public int BmCount(BmVO vo) {
		return bmMapper.BmCount(vo);
	}
}
