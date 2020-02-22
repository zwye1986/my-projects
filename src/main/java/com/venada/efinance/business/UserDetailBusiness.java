package com.venada.efinance.business;


import com.venada.efinance.common.exception.BaseException;
import com.venada.efinance.pojo.UserDetail;
public interface UserDetailBusiness {
	public UserDetail findUserDetailByUserId(String id) throws BaseException;

	public UserDetail findUserDetailByMoblieNumber(String mobileNumber)
			throws BaseException;
	
	public void addUserDetail( UserDetail userDetail) throws BaseException;
	
	public void updateUserDetailByUserId(UserDetail userDetail) throws BaseException;
	
	public void updateUserDetailByMobileNumber (UserDetail userDetail)
			throws BaseException;
}
