package com.venada.efinance.business;


import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.SignIn;
import com.venada.efinance.pojo.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
/***
 * 
 * @author hepei
 *
 */

public interface UserSignInBusiness {
	
	public void addSignIn(SignIn obj) throws  BusinessException;
	
    public List<SignIn> listSignInBySelf(Map<String,Object> condition,PaginationMore page) throws BusinessException;
    
    public Integer getSignInBySelfCount(Map<String,Object> condition) throws BusinessException;
    
    public List<SignIn> listSignInBySelf(Map<String,Object> condition) throws BusinessException;

    public List<String> listSignInByMonth(Map<String,Object> condition) throws BusinessException;

    public Integer getCustomerSignInMonthCount(Map<String,Object> condition) throws BusinessException;
    
    public List<SignIn> getCustomerSignInMonthSeriesCount(Map<String,Object> condition) throws BusinessException;
    
    public Integer getSignInByAllCount() throws BusinessException;

    public List<SignIn> listSignByMobileNumber(Map<String,Object> condition, PaginationMore page) throws BusinessException;

    public List<SignIn> listSignByYearAndMonth(Map<String,Object> condition) throws BusinessException;

    public BigDecimal sumSignBenefitByMobileNumber(Map<String,Object> condition) throws BusinessException;
    
    public Map<String,Object> getSignResult() throws BusinessException;
    
    public Map<String ,Object> isSignIn(int i)throws BusinessException;
    
    public Map<String,Object> userSignInTodayByMobilePhone(String mobileNumber, String password) throws BusinessException;
    
    public Map<String ,Object> isSignIn(User u,int i)throws BusinessException;
    
    public List<SignIn> listSignByMobilePhone(Map<String,Object> condition) throws BusinessException;  
}
