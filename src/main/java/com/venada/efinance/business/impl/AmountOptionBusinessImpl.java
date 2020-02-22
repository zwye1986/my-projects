package com.venada.efinance.business.impl;

import com.venada.efinance.business.AmountOptionBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.exception.ServiceException;
import com.venada.efinance.pojo.AmountOption;
import com.venada.efinance.service.AmountOptionService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class AmountOptionBusinessImpl implements AmountOptionBusiness {
	private static final Logger logger = LoggerFactory.getLogger(AmountOptionBusinessImpl.class);
	
	@Autowired
	private AmountOptionService amountOptionService ;

	public List<AmountOption> getAmountOptions() throws BusinessException {
		try {
			return amountOptionService.findObjects("selectAmountOptions", null);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"getAmountOptions()方法出错！\t", e.getMessage() });
		}
	}

	@Transactional
	public int addAmountOption(AmountOption entity) throws BusinessException {
		try {
			entity.setId(UUID.randomUUID().toString());
			return  amountOptionService.saveObject("addAmountOption", entity);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"addAmountOption()方法出错！\t", e.getMessage() });
		}
	}

	@Transactional
	public int updateAmountOption(AmountOption entity) throws BusinessException {
		try {
			return  amountOptionService.updateObject("updateAmountOption", entity);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"updateAmountOption()方法出错！\t", e.getMessage() });
		}
	}

	@Transactional
	public int deleteAmountOption(String... ids) throws BusinessException {
		int count = 0 ;
		try {
			for(String id : ids){
				  amountOptionService.deleteObject("deleteAmountOption", id);
				  count++;
			}
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"deleteAmountOption()方法出错！\t", e.getMessage() });
		}
		return count ;
	}

	@Transactional
	public int saveAmountOption(AmountOption entity) throws BusinessException {
		if(StringUtils.isNotEmpty(entity.getId())){
			return this.updateAmountOption(entity);
		}
		return	this.addAmountOption(entity);
	}

	public AmountOption getAmountOptionById(String id) throws BusinessException {
		try {
			return (AmountOption) amountOptionService.getObject("selectAmountOptionById", id);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"getAmountOptionById()方法出错！\t", e.getMessage() });
		}
	}

}
