package com.venada.efinance.business.impl;

import com.venada.efinance.business.OperationLogBusiness;
import com.venada.efinance.business.SecurityCenterBusiness;
import com.venada.efinance.business.SystemConfigBusiness;
import com.venada.efinance.business.UserWalletBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.exception.ServiceException;
import com.venada.efinance.enumtype.DealType;
import com.venada.efinance.enumtype.LogTypeEnum;
import com.venada.efinance.pojo.*;
import com.venada.efinance.security.SpringSecurityUtil;
import com.venada.efinance.service.SecurityCenterService;
import com.venada.efinance.util.DateUtils;
import com.venada.efinance.util.DecimalUtil;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class SecurityCenterBusinessImpl implements SecurityCenterBusiness {
	private static final Logger logger = LoggerFactory.getLogger(SecurityCenterBusinessImpl.class);
	
	@Autowired
	private SecurityCenterService securityCenterService ;
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	@Autowired
	private UserWalletBusiness userWalletBusiness;
	@Autowired
	private SystemConfigBusiness systemConfigBusiness ;
	@Autowired
	private DealDetailBusinessImpl dealDetailBusinessImpl;
	@Autowired
	private OperationLogBusiness opeartionLogBusiness ;

	public SecurityCenter getSecurityCenterByUserId(String userId) throws BusinessException {
		try {
			return (SecurityCenter) securityCenterService.getObject("getSecurityCenterByUserId", userId);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"getSecurityCenterByUserId()方法出错！\t", e.getMessage() });
		}
	}
	
	public SecurityCenter getSecurityCenterById(String id) throws BusinessException {
		try {
			return (SecurityCenter) securityCenterService.getObject("getSecurityCenterById", id);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"getSecurityCenterById()方法出错！\t", e.getMessage() });
		}
	}

	/**
	 * 开启安全中心
	 */
	@Transactional
	public int addSecurityCenter(SecurityCenter entity) throws BusinessException {
		try {
			entity.setId(UUID.randomUUID().toString());
			if(StringUtils.isNotEmpty(entity.getPassword())){
				entity.setPassword(md5PasswordEncoder.encodePassword(entity.getPassword(), null));
			}
			return securityCenterService.saveObject("addSecurityCenter", entity);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"addSecurityCenter()方法出错！\t", e.getMessage() });
		}
	}

	/**
	 * 开启安全中心
	 */
	@Transactional
	public int updateSecurityCenter(SecurityCenter entity) throws BusinessException {
		try {
			if(StringUtils.isNotEmpty(entity.getPassword())){
				entity.setPassword(md5PasswordEncoder.encodePassword(entity.getPassword(), null));
			}
			//记录日志信息
			OperationLog log = new OperationLog();
			log.setLogType(LogTypeEnum.SecurityCenter.getIndex());
			log.setDataOld(JSONObject.fromObject(this.getSecurityCenterById(entity.getId())).toString());
			log.setDataNew(JSONObject.fromObject(entity).toString());
			opeartionLogBusiness.addOperationLog(log);
			
			return securityCenterService.updateObject("updateSecurityCenter", entity);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"updateSecurityCenter()方法出错！\t", e.getMessage() });
		}
	}

	public boolean isOpen(String userId) throws BusinessException {
		try {
			return (Integer)securityCenterService.getObject("selectCountSecurityCenterIsOpen", userId) > 0;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"isOpen("+userId+")方法出错！\t", e.getMessage() });
		}
	}

	/**
	 * 开启安全中心
	 */
	@Transactional
	public Map<String, String> openSecurityCenter(int isAutoRenew) throws BusinessException {
		Map<String, String> map = null;
		try {
			map = new HashMap<String, String>();
			map.put("resCode", "0");
			map.put("resMsg", "开启成功");
			User user = SpringSecurityUtil.getCurrentUser() ;
			//查询用户余额
			UserWallet userWallet = userWalletBusiness.getUserWalletByUserId(user.getId());
			//查询安全中心月功能费
			SystemConfig systemConfig = systemConfigBusiness.getSystemConfig("104");
			//判断余额是否大于安全中心的月功能费
			if(userWallet.getAmount().compareTo(BigDecimal.valueOf(Double.valueOf(systemConfig.getParamValue()))) < 0){
				map.put("resCode", "1");
				map.put("resMsg", "对不起，余额不足!");
				return map ;
			}
			SecurityCenter securityCenter = this.getSecurityCenterByUserId(user.getId());
			if(securityCenter != null){
				securityCenter.setIsAutoRenew(isAutoRenew);
				securityCenter.setExpiryDate(DateUtils.getAdd("MONTH", 1));
				securityCenter.setIsOpen(0);
				this.updateSecurityCenterWithNoPassword(securityCenter);
			}else{
				securityCenter = new SecurityCenter();
				securityCenter.setUserId(user.getId());
				securityCenter.setIsAutoRenew(isAutoRenew);
				securityCenter.setExpiryDate(DateUtils.getAdd("MONTH", 1));
				securityCenter.setIsOpen(0);
				this.addSecurityCenter(securityCenter);
			}
			
			//增加流水记录
			DealDetail dealDetail = new DealDetail();
			dealDetail.setId(UUID.randomUUID().toString());
			dealDetail.setDescription("开启会员中心功能费");
			dealDetail.setDateTime(new Date());
			dealDetail.setAmount(BigDecimal.valueOf(Double.valueOf(systemConfig.getParamValue())));
			dealDetail.setSerialNumber(generateASerialNumber());
			dealDetail.setBalance(DecimalUtil.subtract(userWallet.getAmount(), BigDecimal.valueOf(Double.valueOf(systemConfig.getParamValue()))));
			dealDetail.setDealType(DealType.K.getIndex());
			dealDetail.setType("2");
			dealDetail.setStatus("0");
			dealDetail.setUserId(user.getId());
			dealDetailBusinessImpl.addDealDetail(dealDetail);
			
			//扣除功能费
			userWallet.setAmount(DecimalUtil.subtract(userWallet.getAmount(), BigDecimal.valueOf(Double.valueOf(systemConfig.getParamValue()))));
			userWalletBusiness.updateUserWallet(userWallet);
			
		} catch (ServiceException e) {
			map.put("resCode", "1");
			map.put("resMsg", "开启失败，未知错误，请联系管理员");
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"openSecurityCenter("+isAutoRenew+")方法出错！\t", e.getMessage() });
		}
		return map;
	}
	
	public String generateASerialNumber() throws BusinessException {
		return "A" + DateUtils.today("yyyyMMddHHmmssSSS") + System.currentTimeMillis();
	}

	@Override
	public int updateSecurityCenterWithNoPassword(SecurityCenter entity)
			throws BusinessException {

		try {
			//记录日志信息
			OperationLog log = new OperationLog();
			log.setLogType(LogTypeEnum.SecurityCenter.getIndex());
			log.setDataOld(JSONObject.fromObject(this.getSecurityCenterById(entity.getId())).toString());
			log.setDataNew(JSONObject.fromObject(entity).toString());
			opeartionLogBusiness.addOperationLog(log);
			return securityCenterService.updateObject("updateSecurityCenterWithNoPassword", entity);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"updateSecurityCenterWithNoPassword()方法出错！\t", e.getMessage() });
		}
	
	}

	/**
	 * 判断用户是否为自动续费会员
	 */
	@Override
	public int isAutoRenew(String userId) throws BusinessException {
		int isAutoRenew=1;
		
		SecurityCenter  securityCenter= getSecurityCenterByUserId(userId);
		if(securityCenter!=null){
			isAutoRenew=securityCenter.getIsAutoRenew();
		}
		return isAutoRenew;
	}

	@Override
	/**
	 * 取消会员自动续费功能
	 */
	public void cancelAutoRenewByUserId(String userId) throws BusinessException {
		SecurityCenter securityCenter = this.getSecurityCenterByUserId(userId);
		OperationLog log = new OperationLog();
		log.setLogType(LogTypeEnum.SecurityCenter.getIndex());
		log.setDataOld(JSONObject.fromObject(securityCenter).toString());
		securityCenter.setIsAutoRenew(1);
		log.setDataNew(JSONObject.fromObject(securityCenter).toString());
		opeartionLogBusiness.addOperationLog(log);
		securityCenterService.updateObject("updateSecurityCenter",
				securityCenter);
	}
	

}
