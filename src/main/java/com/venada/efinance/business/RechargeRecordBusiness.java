package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.RechargeRecord;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface RechargeRecordBusiness {
	
	public List<RechargeRecord> getRechargeRecords(Map<String,Object> condition,PaginationMore page) throws BusinessException ;
	

	public BigDecimal getRechargeAmountByUserId(String userid) throws BusinessException ;
	
	public List<RechargeRecord> getAllRechargeRecords(Map<String,Object> condition,PaginationMore page) throws BusinessException ;

    public List<RechargeRecord> getRechargeRecordesByConditionToExport(Map<String,Object> condition) throws BusinessException;

	public int getAllRechargeRecordsCountForIPN(Map<String,Object> condition) throws BusinessException ;
	
	public List<RechargeRecord> getRechargeRecordsForIndex() throws BusinessException ;
	
	public BigDecimal countTodayRechargeAmountByUserid(String userid) throws BusinessException ;
	
	public List<RechargeRecord> queryRechargeRecordsByMobilePhone(Map<String, Object> condition) throws BusinessException;

    public List<RechargeRecord> queryRechargeRecordsForMobile(Map<String ,Object> condition) throws BusinessException;

    public int getAllRechargeRecordsCount(Map<String,Object> condition) throws BusinessException ;
}
