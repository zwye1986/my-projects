package com.venada.efinance.mobile.controller;

import com.venada.efinance.business.UserBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.User;
import com.venada.efinance.pojo.UserDetail;
import com.venada.efinance.util.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserRegistForMobile {
	
	private static final Logger logger = LoggerFactory .getLogger(UserRegistForMobile.class); 
	
	@Autowired
	private UserBusiness userBusiness;

	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	@RequestMapping(value = "/{mobileNumber}/{nickName}/{password}/{inviteCode}/doMobileRegist")
	public @ResponseBody Object doMobileRegist(@PathVariable String mobileNumber,@PathVariable String nickName , @PathVariable String password , @PathVariable String inviteCode) {
		// 返回结果
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 手机号码
			if (mobileNumber == null) {
				// 手机号码不符合规则
				resultMap.put("resultCode", 'f');
				resultMap.put("resultMsg", "手机号码不符合规则!");
				return resultMap;
			} else {
				boolean b = SystemUtils.checkMobileNumber(mobileNumber);
				if (!b) {
					// 手机号码不符合规则
					resultMap.put("resultCode", 'f');
					resultMap.put("resultMsg", "手机号码不符合规则!");
					return resultMap;
				}
			}

			User user = userBusiness.findUserByMoblieNumber(mobileNumber);
			if (user != null) {
				resultMap.put("resultCode", 'f');
				resultMap.put("resultMsg", "用户已经存在,不能重复注册!");
				return resultMap;
			} 
			//设置用户信息
			user = new User();
			user.setId(UUID.randomUUID().toString().toUpperCase());
			user.setMobileNumber(mobileNumber);
			user.setCreateTime(new Date());
			user.setPassword(md5PasswordEncoder.encodePassword(password,null));
			if(!"0".equals(inviteCode))
			user.setInviteCodeFromOther(inviteCode);
			user.setInviteCodeSelf(userBusiness.obtionInviteCode());
			user.setNickName(nickName);
			user.setStatus(1);
			
			//用户详细信息
			UserDetail userDetail = new UserDetail();
			userDetail.setId(UUID.randomUUID().toString().toUpperCase());
			userDetail.setUserid(user.getId());
			userDetail.setMobileNumber(mobileNumber);

			userBusiness.addUser(user, userDetail);
			resultMap.put("resultCode", 's');
			resultMap.put("resultMsg", "注册成功!");
			return resultMap;
		} catch (BusinessException e) {
			logger.error(e.getMessage());
			resultMap.put("resultCode", 'f');
			resultMap.put("resultMsg", "系统错误，请重试!");
			return resultMap;
		}
	}
}
