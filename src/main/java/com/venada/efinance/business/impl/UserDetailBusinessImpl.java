package com.venada.efinance.business.impl;

import com.venada.efinance.business.UserDetailBusiness;
import com.venada.efinance.common.exception.BaseException;
import com.venada.efinance.pojo.UserDetail;
import com.venada.efinance.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserDetailBusinessImpl implements UserDetailBusiness {
	
	@Autowired
	private UserDetailService userDetailService;

	@Override
	public UserDetail findUserDetailByUserId(String id) throws BaseException {
		return (UserDetail)userDetailService.getObject("findUserDetailByUserId", id);
	}

	@Override
	public UserDetail findUserDetailByMoblieNumber(String mobileNumber)
			throws BaseException {
		return (UserDetail)userDetailService.getObject("findUserDetailByMoblieNumber", mobileNumber);
	}

	@Override
	public void addUserDetail(UserDetail userDetail) throws BaseException {
		userDetailService.saveObject("addUserDetail", userDetail);
	}

	@Override
	public void updateUserDetailByUserId(UserDetail userDetail) throws BaseException {
		userDetailService.updateObject("updateUserDetailByUserId", userDetail);
	}

	@Override
	public void updateUserDetailByMobileNumber(UserDetail userDetail)
			throws BaseException {
		userDetailService.updateObject("updateUserDetailByMobileNumber", userDetail);
	}

}
