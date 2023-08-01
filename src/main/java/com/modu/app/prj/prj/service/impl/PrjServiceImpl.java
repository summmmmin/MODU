package com.modu.app.prj.prj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.app.prj.prj.mapper.PrjMapper;
import com.modu.app.prj.prj.service.PrjService;
import com.modu.app.prj.prj.service.PrjVO;

@Service
public class PrjServiceImpl implements PrjService {
	@Autowired
	PrjMapper prjMapper;
	
	@Override
	public int insertPrj(PrjVO prjVO) {
		return prjMapper.insertPrj(prjVO);
	}

	@Override
	public List<PrjVO> getPrjList(String membUniNo) {
		return prjMapper.selectPrjList(membUniNo);
	}

	@Override
	public List<PrjVO> getPrjPartiList(PrjVO prjVO) {
		return prjMapper.selectPrjParti(prjVO);
	}

	@Override
	public PrjVO prjSession(PrjVO vo) {
		return prjMapper.prjSession(vo);
	}

	@Override
	public List<PrjVO> grdCmmn() {
		return prjMapper.grdCmmn();
	}

	@Override
	public int setPrjNm(PrjVO vo) {
		return prjMapper.updatePrjNm(vo);
	}

	@Override
	public int delPrj(String prjNo) {
		return prjMapper.deletePrj(prjNo);
	}

	@Override
	public int setGrade(PrjVO vo) {
		return prjMapper.updateGrade(vo);
	}

	@Override
	public int kickPrjParti(PrjVO vo) {
		return prjMapper.kickPrjParti(vo);
	}

	@Override
	public PrjVO getMemInfo(PrjVO vo) {
		return prjMapper.selectMemInfo(vo);
	}

	@Override
	public int getPrjMemCnt(String prjUniNo) {
		return prjMapper.selectPrjMemCnt(prjUniNo);
	}

	@Override
	public PrjVO getPrjInfo(String prjUniNo) {
		return prjMapper.selectPrjInfo(prjUniNo);
	}

	@Override
	public int setPrjSubsp(PrjVO vo) {
		return prjMapper.updatePrjSubsp(vo);
	}

	@Override
	public int getBrdCnt(String prjUniNo) {
		return prjMapper.selectBrdCnt(prjUniNo);
	}

	@Override
	public int getChatrCnt(String prjUniNo) {
		return prjMapper.selectChatrCnt(prjUniNo);
	}

	@Override
	public List<PrjVO> getPrjCnt(PrjVO vo) {
		return prjMapper.selectPrjCnt(vo);
	}

	@Override
	public Map<String, Object> getGrdCnt(String prjUniNo) {
		List<PrjVO> list = prjMapper.selectGrdCnt(prjUniNo);
		
		List<String> grd = new ArrayList<String>();		// 등급 이름 담을 list
		List<Integer> cnt = new ArrayList<Integer>();	// count 수 담을 list
		for(PrjVO vo : list) {
			grd.add(vo.getCd());
			cnt.add(vo.getMemCnt());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("grd", grd);
		map.put("cnt", cnt);
		return map;
	}

	@Override
	public String insertInvite(PrjVO vo) {
		prjMapper.insertInvite(vo);
		return vo.getId();
	}

	@Override
	public PrjVO selectInvite(String tk) {
		System.out.println("서비스 토큰"+tk);
		return prjMapper.selectInvite(tk);
	}

	@Override
	public int insertPartiMemb(PrjVO vo) {
		PrjVO check = prjMapper.prjSession(vo);

		if(check != null) {
			// 이 사람이 원래 프로젝트에 있을때
			return 0;
		}else {
			if((getPrjMemCnt(vo.getPrjUniNo()) < 10 && getPrjInfo(vo.getPrjUniNo()).getExdt() == null) 
					|| getPrjInfo(vo.getPrjUniNo()).getExdt() != null) {
				// 무료 플랜 이용중이고 10명 이하일때 / 유료플랜 = 초대가능
				int result = prjMapper.insertPartiMemb(vo);
				if(result == 1) {
					// insert 성공
					if(vo.getId() != null) {
						// 참여테이블 쓴 거로 update
						prjMapper.updateInvite(vo.getId());
					}
					return 1;
				}else {
					// insert 오류
					return 2;
				}
			}else if((getPrjMemCnt(vo.getPrjUniNo()) >= 10 && getPrjInfo(vo.getPrjUniNo()).getExdt() == null)) {
				// 무료 플랜 이용중인데 10명 이상 
				return 3;
			}else {
				return 4;
			}			
		}
	}
}
