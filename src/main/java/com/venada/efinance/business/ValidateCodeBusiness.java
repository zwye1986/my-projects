package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.ValidateCode;

import java.util.List;
import java.util.Map;

public interface ValidateCodeBusiness {
	public List<ValidateCode> queryLastestCodeByMobileNumber(String mobileNumber) throws BusinessException;
	public int queryCrossTimeById(String id) throws BusinessException;
	public void addValiDateCode(ValidateCode validateCode) throws BusinessException;
	public void sendSms(String mobileNumber,String sms,String initPassword) throws BusinessException;
	public List<ValidateCode> queryLastestCodeByMobileNumberAndPassword(Map<String,String> map) throws BusinessException;
	public List<ValidateCode> queryLastestCodeByMobileNumberAndEncodedPassword(Map<String,String> map) throws BusinessException; 
	public List<ValidateCode> queryValidateCodeByConditions(Map<String,Object> map,PaginationMore page) throws BusinessException; 
	public int queryCountOfValidateCodeByConditions(Map<String,Object> map) throws BusinessException;
    public ValidateCode queryLastestCodeByMobileNumberAndCode(Map<String,Object> map) throws BusinessException;
    public void sendSmsByTemple(String mobileNumber,String code,String temple) throws BusinessException;
    public void deleteUserCode(String mobileNumber) throws  BusinessException;
}
