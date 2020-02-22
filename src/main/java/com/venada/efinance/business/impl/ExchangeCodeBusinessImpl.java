package com.venada.efinance.business.impl;


import com.venada.efinance.business.ExchangeCodeBussiness;
import com.venada.efinance.business.SystemConfigBusiness;
import com.venada.efinance.business.UserWalletBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.exception.ServiceException;
import com.venada.efinance.pojo.ExchangeCode;
import com.venada.efinance.pojo.ExchangeRecord;
import com.venada.efinance.pojo.SystemConfig;
import com.venada.efinance.pojo.UserWallet;
import com.venada.efinance.service.ExchangeCodeService;
import com.venada.efinance.service.ExchangeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/***
 * 
 * @author hepei
 *
 */
@Service("exchangeCodeBusinessImpl")
public class ExchangeCodeBusinessImpl implements ExchangeCodeBussiness{
	@Autowired
	private ExchangeCodeService exchangeCodeServiceImpl;
	@Autowired
	private ExchangeRecordService exchangeRecordService;
	@Autowired
	private  SystemConfigBusiness systemConfigBusinessImpl;
	@Autowired
	private UserWalletBusiness userWalletBusiness;

	@Override
	public ExchangeCode getExchangecode(String code) throws BusinessException {
		try {
			return (ExchangeCode) exchangeCodeServiceImpl.getObject("getExchangecode", code);
		} catch (ServiceException e) {
			throw new BusinessException("0002", new String[] {"getExchangecode()方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public int addExchangecode(ExchangeCode ec) throws BusinessException {
		try {
			ec.setId(UUID.randomUUID().toString());
			return exchangeCodeServiceImpl.saveObject("addExchangecode", ec);
		} catch (ServiceException e) {
			throw new BusinessException("0002", new String[] {"addExchangecode()方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public List<ExchangeRecord> queryExchangeRecord(String userId) throws BusinessException {
		return exchangeRecordService.findObjects("queryExchangeRecord", userId);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class, ServiceException.class })
	public void exchange(String userId, String code) throws BusinessException {
		 SystemConfig sysConfig = systemConfigBusinessImpl.getSystemConfig("113");
		 ExchangeRecord record = new ExchangeRecord();
		 record.setId(UUID.randomUUID().toString());
		 record.setUserId(userId);
		 record.setExchangeCode(code);
		 record.setCreateTime(new Date());
		 record.setMoney(new BigDecimal(sysConfig.getParamValue()));
		 exchangeRecordService.saveObject("addExchangeRecord", record);
		 
		 UserWallet userWallet = userWalletBusiness.getUserWalletByUserId(userId);
		 userWalletBusiness.withAddExchange(record.getMoney(), userWallet, "");
		 
		 exchangeCodeServiceImpl.deleteObject("deleteExchangeCord", code);
	}
	
	

}
