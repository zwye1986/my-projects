package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;

import java.util.Map;

public interface FusionChartBusiness {
	
	public String selectRechargeAndWithdrawal(Map<String,Object> map) throws BusinessException ;
	
	public String selectRechargeLine(Map<String,Object> map) throws BusinessException ;
	
	public String selectWithdrawalLine(Map<String,Object> map) throws BusinessException ;
	
	public String selectWithdrawalSuccessLine(Map<String,Object> map) throws BusinessException ;
	
	public String selectUserByHour() throws BusinessException ;
	
	public String selectUserByDay() throws BusinessException ;
	
	public String estimatedCostByDate(Map<String,Object> map) throws BusinessException ;
	
	public String depostGeter() throws BusinessException ;
	
	public String sexRatio() throws BusinessException ;
	
	public String incomeCount() throws BusinessException ;
	
	public String ageRatio() throws BusinessException ;
	
	public String gameCount() throws BusinessException ;
	
	public String  getAllInfo(Map<String,Object> map) throws BusinessException;
	
    public String  getAllCapitalInfo(Map<String,Object> map) throws BusinessException;


}
