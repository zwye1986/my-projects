package com.venada.efinance.business.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venada.efinance.business.GameBusiness;
import com.venada.efinance.business.SignSysConfigBusiness;
import com.venada.efinance.business.UserWalletBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.exception.ServiceException;
import com.venada.efinance.pojo.SignSysConfig;
import com.venada.efinance.pojo.User;
import com.venada.efinance.pojo.UserGameRelation;
import com.venada.efinance.pojo.UserWallet;
import com.venada.efinance.service.SignSysconfigService;
import com.venada.efinance.util.DateUtils;

@Service
public class SignSysConfigBusinessImpl implements SignSysConfigBusiness {
	private static final Logger logger = LoggerFactory
			.getLogger(SignSysConfigBusinessImpl.class);

	@Autowired
	private SignSysconfigService signSysconfigServiceImpl;
	@Autowired
	private UserWalletBusiness userWalletBusinessImpl;
	@Autowired
	private GameBusiness gameBusinessImpl;

	@Override
	public List<SignSysConfig> listSignSysConfig() throws BusinessException {
		int type=0;
		if(DateUtils.compareCurrentDate("2015-02-19 00:00:00")&&!DateUtils.compareCurrentDate("2015-02-24 23:59:59")){
			type=1;
		}
		try {
			List<SignSysConfig> list = (List<SignSysConfig>) signSysconfigServiceImpl
					.findObjects("listSignSysConfig", type);
			return list;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"getSystemConfig()方法出错！\t", e.getMessage() });
		}
	}
    /**
     * 根据领取任务的总资产获取签到收益
     */
	@Override
	public BigDecimal getSignBenfitByUser(BigDecimal amount) throws BusinessException {
		BigDecimal result=new BigDecimal(0);
		List<SignSysConfig> list=listSignSysConfig();
		for(SignSysConfig signSysconfig:list){
			if(amount.compareTo(signSysconfig.getAssetBegin())==1){
				if((amount.compareTo(signSysconfig.getAssetEnd())==-1)||(amount.compareTo(signSysconfig.getAssetEnd())==0)){
					result=signSysconfig.getBenefit().multiply(amount);
					break;
				}
			}
			}
		return result  ;
	}

	/**
	 * 获取当前领取任务的资产
	 */
	@Override
	public BigDecimal getAllAmountByUser(User user) throws BusinessException {
		// String userid = user.getId();
		// BigDecimal userwallertAmount = new BigDecimal(0);
		BigDecimal userGameDesposit = new BigDecimal(0);

		// UserWallet userWallert = userWalletBusinessImpl
		// .getAmountByUserId(userid);
		// if (userWallert != null) {
		// userwallertAmount = userWallert.getAmount();
		// }
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("userid", user.getMobileNumber());
		UserGameRelation userGameRelation = gameBusinessImpl
				.getUserGameBenefitInfo(condition);

		if (userGameRelation != null) {
			userGameDesposit = new BigDecimal(userGameRelation.getDeposit());
		}

		BigDecimal allAmount = userGameDesposit;

		return allAmount;
	}
	

}
