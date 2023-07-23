package com.modu.app.prj.pay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.app.prj.pay.mapper.PayMapper;
import com.modu.app.prj.pay.service.PayService;
import com.modu.app.prj.pay.service.PayVO;

@Service
public class PayServiceImpl implements PayService {
	@Autowired
	PayMapper payMapper;

	@Override
	public List<PayVO> prjPayList(String prjUniNO) {
		return payMapper.selectPrjPay(prjUniNO);
	}
	
	
}
