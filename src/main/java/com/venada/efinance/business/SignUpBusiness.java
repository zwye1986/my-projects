package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.SignUp;

import java.util.List;
import java.util.Map;








/***
 * 
 * @author hepei
 *
 */

public interface SignUpBusiness {
	
    public void addSignUp(SignUp signUp) throws BusinessException ;
    
    public List<SignUp> getSignUpByMobileNumber(String mobileNumber) throws BusinessException ;
    
    public List<SignUp> querySignUpList(Map<String,Object> map) throws BusinessException ;
    
}
