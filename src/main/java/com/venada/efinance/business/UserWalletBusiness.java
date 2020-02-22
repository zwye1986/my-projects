package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.ParseException;

public interface UserWalletBusiness {
	
	/**
	 * 用户充值
	 * @param amount 充值金额
	 * @param rechargeId 充值的账号
	 * @param user 当前登录用户
	 * @return
	 * @throws BusinessException
	 */
	public String recharge(BigDecimal amount,String mobileNumber,String bank_type,String remoteIp,String bankCardId, HttpServletRequest request,
			HttpServletResponse response,String returnUrl) throws BusinessException ;
	
	/**
	 * 用户签到，增加余额
	 * @param amount
	 * @param userId
	 * @throws BusinessException
	 */
	public void addSignBenefit(BigDecimal amount,String userId)throws BusinessException;
	
	/**
	 * 支付酷刷coolpos移动金融刷卡机（器）
	 * @param amount 充值金额
	 * @param rechargeId 充值的账号
	 * @param user 当前登录用户
	 * @return
	 * @throws BusinessException
	 */
	public String payforCoolpos(Long orderId,BigDecimal amount,String bank_type,String remoteIp,String bankCardId, HttpServletRequest request,
			HttpServletResponse response) throws BusinessException ;
	
	
	
	/**
	 * 真实充值
	 * 
	 * @param payKey
	 * @throws BusinessException
	 */
	public void realRecharge(String payKey) throws BusinessException;
	
	
	/**
	 * 真实支付
	 * 
	 * @param payKey
	 * @throws BusinessException
	 */
	public void realPayFor(String payKey) throws BusinessException;
	
	
	/**
	 * 生成充值流水号
	 * @return
	 * @throws BusinessException
	 */
	public String generateCSerialNumber() throws BusinessException ;
	
	
	/**
	 * 开户
	 */
	public boolean generateAccount(String userId) throws BusinessException ;
	
	public UserWallet getAmountByUserId(String uesrid) throws BusinessException ;
	
	/**
	 * 此方法用于查询用户余额，并且锁行记录，直至事务提交自动解锁
	 * @param userid 用户ID
	 * @return
	 * @throws BusinessException
	 */
	public UserWallet getUserWalletByUserId(String userid) throws BusinessException ;
	
	public int updateUserWallet(UserWallet wallet) throws BusinessException ;
	
	/**
	 * 提现处理操作
	 * @return
	 * @throws BusinessException
	 */
	public boolean withdrawal(BigDecimal amount,String bankCardId,String ip,String password) throws BusinessException ;
	
	/**
	 * 生成充值流水号
	 * @return
	 * @throws BusinessException
	 */
	public String generateTSerialNumber() throws BusinessException ;
	
	public BigDecimal withGameDeposit(BigDecimal amount,UserWallet userWallet, String ip) throws BusinessException;
	
	public BigDecimal withInviteBenefit(BigDecimal amount,UserWallet userWallet, String ip) throws BusinessException;
	
	public BigDecimal withAddGameDeposit(BigDecimal amount,UserWallet userWallet, String ip) throws BusinessException;
	
	public BigDecimal withAddExchange(BigDecimal amount,UserWallet userWallet, String ip) throws BusinessException;
	
	public BigDecimal withAddGameReward(BigDecimal amount,UserWallet userWallet, String ip) throws BusinessException;
	
	public BigDecimal payForProject(ProjectUser puser,UserWallet userWallet,
			String ip,ProjectInvest projectInvest,Integer count) throws BusinessException;
	/**
	 * 
	 * @param user(被赠送的用户) 
	 * @param _user(当前用户)
	 * @return
	 * @throws BusinessException
	 */
	public void payForFriends(User user,User _user,int needToPay,String ip , UserWallet userWallet,int months) throws BusinessException,ParseException ;
	
	/**
	 * 
	 * @param user(当前用户)
	 * @return
	 * @throws BusinessException
	 */
	public void payForRenewal(User user,int needToPay,String ip , UserWallet userWallet,int months) throws BusinessException,ParseException ;
    public void randomVip(User user,Integer months) throws  BusinessException,ParseException;
	public String generateSerialNumber(String tag) throws BusinessException;
	public void insertOrderIdRecord(OrderIdRecord orderIdRecord) throws BusinessException;
}
