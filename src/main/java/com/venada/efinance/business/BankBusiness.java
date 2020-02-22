package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.Bank;

import java.util.List;
public interface BankBusiness {
	public List<Bank> queryBank() throws BusinessException;
	public void initBank(List<Bank> list) throws BusinessException;
	public List<Bank> queryBankHot() throws BusinessException;
	public List<Bank> findBankListByCityid(int cityid) throws BusinessException;
}
