package com.venada.efinance.business.impl;import com.venada.efinance.business.LoginBussiness;import com.venada.efinance.business.SecurityCenterBusiness;import com.venada.efinance.business.UserBusiness;import com.venada.efinance.business.UserObtainCreditsBusiness;import com.venada.efinance.common.exception.BusinessException;import com.venada.efinance.pojo.LoginLog;import com.venada.efinance.pojo.ObtainCredits;import com.venada.efinance.pojo.User;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;import java.util.Date;import java.util.UUID;@Servicepublic class LoginBussinessImpl implements LoginBussiness {		@Autowired	private UserBusiness userBusiness;	@Autowired	private UserObtainCreditsBusiness userObtainCreditsBusiness;	@Autowired	private SecurityCenterBusiness securityCenterBusiness;	@Override	@Transactional	public void setUserCredits(User user,LoginLog loginLog) throws BusinessException {		Integer credits = 0;		//判断是否当天第一次登录		int c = userBusiness.queryUserLoginLog(user.getId());		if (c == 0) {			boolean b = securityCenterBusiness.isOpen(user.getId());			if (b) {				credits = 15;			} else {				credits = 10;			}		}		user.setLevel(user.getLevel() + credits);		user.setCredits(user.getCredits() + credits);		if (credits > 0) {			ObtainCredits obtainCredits = new ObtainCredits();			obtainCredits.setId(UUID.randomUUID().toString());			obtainCredits.setActionTime(new Date());			obtainCredits.setCreateTime(new Date());			obtainCredits.setUserId(user.getId());			obtainCredits.setCredits(Long.valueOf(credits));			obtainCredits.setActionType(0);			userObtainCreditsBusiness.addObtainCredits(obtainCredits);		}		userBusiness.updateUserByMobileNumber(user, loginLog);	}}