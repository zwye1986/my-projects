package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.WeixinUser;

/***
 * 
 * @author hepei
 * 
 */

public interface WeixinUserBusiness {
	public void addWeixinUser(WeixinUser weixinUser) throws BusinessException;

	public void updateWeixinUser(WeixinUser weixinUser) throws BusinessException;

	public void delWeixinUser(String id) throws BusinessException;

	public WeixinUser getWeixinUserOpenid(String openid)
			throws BusinessException;
	
	public WeixinUser getWeixinUserByUserId(String userid)
			throws BusinessException;
	
	public int getWeixinUserOpenidCount(String openid)
			throws BusinessException;
	
	public boolean isOAuth(String openid) throws BusinessException;
	
    public void delWeixinUserByOpenId(String openid) throws BusinessException;
    
    
    public boolean isOAuthByUserid(String userid) throws BusinessException;


}
