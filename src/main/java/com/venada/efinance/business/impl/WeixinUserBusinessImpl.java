package com.venada.efinance.business.impl;

import com.venada.efinance.business.WeixinUserBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.WeixinUser;
import com.venada.efinance.service.WeixinUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/***
 * 
 * @author hepei
 * 
 */
@Service
public class WeixinUserBusinessImpl implements WeixinUserBusiness {
	/** Logger */
	private static final Logger logger = LoggerFactory
			.getLogger(WeixinUserBusinessImpl.class);
	@Autowired
	private WeixinUserService weixinUserServiceImpl;

	
	@Override
	public void addWeixinUser(WeixinUser weixinUser) throws BusinessException {
		try {
			weixinUserServiceImpl.saveObject("addWeixinUser", weixinUser);
		} catch (DuplicateKeyException duplicateKeyException) {
			logger.error("错误堆栈:" + duplicateKeyException.getMessage());
			throw new BusinessException("数据重复.");
		} catch (Exception e) {
			logger.error("错误堆栈:" + e.getMessage());
			throw new BusinessException("系统异常.");
		}
	}

	@Override
	public void updateWeixinUser(WeixinUser weixinUser)
			throws BusinessException {
		try {
			weixinUserServiceImpl.saveObject("updateWeixinUser", weixinUser);
		} catch (DuplicateKeyException duplicateKeyException) {
			logger.error("错误堆栈:" + duplicateKeyException.getMessage());
			throw new BusinessException("数据重复.");
		} catch (Exception e) {
			logger.error("错误堆栈:" + e.getMessage());
			throw new BusinessException("系统异常.");
		}
	}

	@Override
	public void delWeixinUser(String id) throws BusinessException {
		weixinUserServiceImpl.deleteObject("delWeixinUser", id);
	}
	
	@Override
	public void delWeixinUserByOpenId(String openid) throws BusinessException {
		weixinUserServiceImpl.deleteObject("delWeixinUserByOpenId", openid);
	}

	@Override
	public WeixinUser getWeixinUserOpenid(String openid)
			throws BusinessException {
		return (WeixinUser) weixinUserServiceImpl.getObject("getWeixinUserOpenid", openid);
	}

	@Override
	public int getWeixinUserOpenidCount(String openid) throws BusinessException {
		return  (Integer) weixinUserServiceImpl.getObject("getWeixinUserOpenidCount", openid);
	}
	
	public int getWeixinUserIdCount(String userid) throws BusinessException {
		return  (Integer) weixinUserServiceImpl.getObject("getWeixinUserIdCount", userid);
	}

	@Override
	public boolean isOAuth(String openid) throws BusinessException {
		boolean tag=false;
		int count=getWeixinUserOpenidCount(openid);
		if(count>0){
			tag=true;
		}else{
			tag=false;
		}
		return tag;
	}

	@Override
	public WeixinUser getWeixinUserByUserId(String userid)
			throws BusinessException {
		return (WeixinUser) weixinUserServiceImpl.getObject("getWeixinUserByUserId", userid);
	}

	@Override
	public boolean isOAuthByUserid(String userid) throws BusinessException {
		boolean tag=false;
		int count=getWeixinUserIdCount(userid);
		if(count>0){
			tag=true;
		}else{
			tag=false;
		}
		return tag;
		
	}

}
