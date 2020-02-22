package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.Advice;

import java.util.List;
import java.util.Map;

public interface AdviceBusiness {
	public void saveAdvice(Advice advice) throws BusinessException  ;
	public List<Advice> selectAdvice(PaginationMore page) throws BusinessException  ;
	public int updateAdvice(Advice advice) throws BusinessException ;
	public int getAdviceCounts(Map<String, Object> condition) throws BusinessException ;
	public int getFeedBackCount(Map<String, Object> condition) throws BusinessException ;
	public Advice getAdviceById(String Id) throws BusinessException;
	public List<Advice> selectAdvice(Map<String, Object> condition, PaginationMore page)
			throws BusinessException;
	public List<Advice> selectAdvice(Map<String, Object> condition)throws BusinessException;
}
