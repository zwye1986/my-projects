package com.venada.efinance.business.impl;

import com.venada.efinance.business.BankBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.Bank;
import com.venada.efinance.service.BankCityMapService;
import com.venada.efinance.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("bankBusiness")
public class BankBusinessImpl implements BankBusiness {
	
	@Autowired
	private BankService bankService;
	@Autowired
	private BankCityMapService bankCityMapService;

	@Override
	public List<Bank> queryBank() {
		return bankService.findObjects("queryBank", null);
	}

	@Override
	public void initBank(List<Bank> list) {
		bankService.batchUpdateObject("initBank", list);
	}

	@Override
	public List<Bank> queryBankHot() {
		return bankService.findObjects("queryBankHot", null);
	}

	@Override
	public List<Bank> findBankListByCityid(int cityid)
			throws BusinessException {
		return bankCityMapService.findObjects("findBankListByCityid", cityid);
	}

}
