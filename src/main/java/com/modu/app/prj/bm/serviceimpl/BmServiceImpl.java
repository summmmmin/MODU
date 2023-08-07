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

	// 게시판 즐겨찾기 등록
	@Override
	public int BrdBmInsert(BmVO vo) {
		int result = 0;
		// 값 조회
		int BrdbmCount = bmMapper.BrdBmCount(vo);
		// 값이 없으면 등록
		if (BrdbmCount == 0) {
			result = bmMapper.BrdBmInsert(vo);
			// 값이 있으면 삭제
		} else {
			result = bmMapper.BrdBmDelete(vo);
		}
		return result;
	}

	@Override
	public int BmInsert(BmVO vo) {
	    int result = 0;
	    // 값 조회
	    int BmCount = bmMapper.BmCount(vo);
	    
	    if (BmCount == 0) {
	    	// 값이 없으면 등록
	        result = bmMapper.BmInsert(vo);
	    } else {
	    	// 값이 있으면 삭제
	        result = bmMapper.BmDelete(vo);
	    }
	    return result;
	}

	@Override
	public int BrdBmCount(BmVO vo) {
		return bmMapper.BrdBmCount(vo);
	}

	// 즐겨찾기 삭제
	@Override
	public int BmDelete(BmVO vo) {
		return bmMapper.BmDelete(vo);
	}

	@Override
	public List<BmVO> BmList(BmVO vo) {
		return bmMapper.BmList(vo);
	}

	@Override
	public int BmCount(BmVO vo) {
		return bmMapper.BmCount(vo);
	}
	
	@Override
	public List<String> PostBmList(BmVO vo) {
		return bmMapper.PostBmList(vo);
	}
	
	@Override
	public int postParticiMembKick(BmVO vo) {
		return bmMapper.postParticiMembKick(vo);
	}
}
