package com.venada.efinance.business.impl;

import com.venada.efinance.business.AdviceBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.Advice;
import com.venada.efinance.service.AdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class AdviceBusinessImpl implements AdviceBusiness {
	@Autowired
	private AdviceService adviceService;
	@Override
	public void saveAdvice(Advice advice) throws BusinessException {
		adviceService.saveObject("saveAdvice", advice);
	}
	@Override
	public List<Advice> selectAdvice(PaginationMore page) throws BusinessException {
		
		page.setTotalRows(getFeedBackCount());
		page.repaginate();
		return adviceService.selectList("selectFeedBacks", null,page);
		
	}
	
	@Override
	public List<Advice> selectAdvice(Map<String,Object> condition,PaginationMore page) throws BusinessException {
		
		page.setTotalRows(getFeedBackCount(condition));
		page.repaginate();
		return adviceService.selectList("selectAdvice", condition,page);
		
	}
	private int getFeedBackCount() throws BusinessException {
		return (Integer) adviceService.getObject("getFeedBackCount", null);
	}
	
	public int getFeedBackCount(Map<String,Object>condition) throws BusinessException {
		return (Integer) adviceService.getObject("getAdviceCount", condition);
	}
	
	@Override
	public int updateAdvice(Advice advice) throws BusinessException {
		return  adviceService.updateObject("updateAdvice", advice);
	}
	@Override
	public Advice getAdviceById(String Id) throws BusinessException {
		return (Advice) adviceService.getObject("getAdviceById", Id);
	}
	@Override
	public int getAdviceCounts(Map<String, Object> condition)
			throws BusinessException {
			int count = (Integer) adviceService.getObject("getAdviceCounts", condition);
			return count;
			
	}
	@Override
	public List<Advice> selectAdvice(Map<String, Object> condition)
			throws BusinessException {
		 List<Advice> list = adviceService.findObjects("queryAdvice", condition);
         return list;
	}

}
