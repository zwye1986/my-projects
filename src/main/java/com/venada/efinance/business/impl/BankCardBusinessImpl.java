package com.venada.efinance.business.impl;

import com.venada.efinance.business.BankCardBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.exception.ServiceException;
import com.venada.efinance.pojo.BankCard;
import com.venada.efinance.service.BankCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class BankCardBusinessImpl implements BankCardBusiness {
	private static final Logger logger = LoggerFactory.getLogger(BankCardBusinessImpl.class);

	
	@Autowired
	private BankCardService bankCardService ;

	public List<BankCard> getAllByUserId(String userId) throws BusinessException {
		List<BankCard> list = null ;
		try{
			list = bankCardService.findObjects("getBankCardsByUserId", userId);
		}catch(ServiceException e){
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] { "getAllByUserId(userId:" + userId + ")方法出错！\t", e.getMessage() });
		}
		return list;
	}

	@Transactional(rollbackFor = { Exception.class, ServiceException.class })
	public void saveBankCard(BankCard bankCard) throws BusinessException {
		try {
			bankCardService.saveObject("saveBankCard", bankCard);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] { "saveBankCard()方法出错！\t", e.getMessage() });
		}
	}

	public BankCard getBankCardById(String id) throws BusinessException {
		try {
			return (BankCard) bankCardService.getObject("getBankCardById", id);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] { "getBankCardById()方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public int delUserCardById(Map<String, String> map)
			throws BusinessException {
		return bankCardService.deleteObject("delUserCardById", map);
	}

    @Override
    public List<BankCard> getBankCardByConditions(Map<String, Object> conditions) throws BusinessException {
        return bankCardService.findObjects("getBankCardByUserId",conditions);
    }

}
