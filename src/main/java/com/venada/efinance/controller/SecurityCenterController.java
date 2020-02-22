package com.venada.efinance.controller;

import com.venada.efinance.business.SecurityCenterBusiness;
import com.venada.efinance.pojo.SecurityCenter;
import com.venada.efinance.pojo.User;
import com.venada.efinance.security.SpringSecurityUtil;
import com.venada.efinance.sms.SmsService;
import com.venada.efinance.util.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/user/security/center")
public class SecurityCenterController {
	private static final Logger logger = LoggerFactory.getLogger(SecurityCenterController.class);
	
	@Autowired
	private SecurityCenterBusiness securityCenterBusiness ;
	@Autowired
	private SmsService smsService;
	
	@ResponseBody
	@RequestMapping(value="/generate/code", method = {RequestMethod.GET})
	public Object toValidateCode(HttpSession session,HttpServletRequest request){
		try {
			String mobileNumber = request.getParameter("mobileNumber");
			if(mobileNumber == null || !SystemUtils.checkMobileNumber(mobileNumber)){
				return "1" ;
			}else if(mobileNumber.equals(SpringSecurityUtil.getCurrentUser().getMobileNumber())){
				return "2" ;
			}
			String validateCode = SystemUtils.randomCheckcode("23456789ABCDEFGHJKLMNPRSTUVWXYZ",6);
			//smsService.sendSms(mobileNumber, "尊敬的会员，您目前使用会员特权绑定第二个手机号码，短信验证码"+validateCode+"。请尽快操作！");
			  smsService.sendSms(mobileNumber, "", "MB-2014011425",validateCode);
			session.setAttribute("validateCode", validateCode);
			return "0";
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "-1" ;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/generate/code", method = {RequestMethod.POST})
	public Object getValidateCode(HttpSession session){
		try {
			return session.getAttribute("validateCode");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "-1" ;
		}
	}
	
	@RequestMapping(value="/close", method = RequestMethod.POST)
	public String closeSecurityCenter(){
		try {
			User user = SpringSecurityUtil.getCurrentUser() ;
			SecurityCenter securityCenter = securityCenterBusiness.getSecurityCenterByUserId(user.getId());
			securityCenter.setIsOpen(1);
			securityCenterBusiness.updateSecurityCenterWithNoPassword(securityCenter);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "forward:/user/security/center"; 
	}

}
