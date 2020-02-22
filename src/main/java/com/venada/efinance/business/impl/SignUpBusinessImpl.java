package com.venada.efinance.business.impl;


import com.venada.efinance.business.SignUpBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.SignUp;
import com.venada.efinance.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/***
 * 
 * @author hepei
 *
 */
@Service("signUpBusinessImpl")
public class SignUpBusinessImpl implements SignUpBusiness{
	@Autowired
	private SignUpService signUpServiceImpl;
	

	
	@Override
	public void addSignUp(SignUp signUp) throws BusinessException {
		signUpServiceImpl.saveObject("addSignUp", signUp);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<SignUp> getSignUpByMobileNumber(String mobileNumber) throws BusinessException {
		return (List<SignUp>) signUpServiceImpl.findObjects("getSignUpByMobileNumber", mobileNumber);
	}
	@Override
	public List<SignUp> querySignUpList(Map<String, Object> map)
			throws BusinessException {
		return signUpServiceImpl.findObjects("querySignUpList", map);	}
	
}
