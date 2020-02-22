package com.venada.efinance.business.impl;

//import beartool.MD5;
import com.tenpay.RequestHandler;
import com.tenpay.util.TenpayUtil;
import com.venada.efinance.business.*;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.exception.ServiceException;
import com.venada.efinance.enumtype.DealType;
import com.venada.efinance.enumtype.LogTypeEnum;
import com.venada.efinance.enumtype.TradeStatus;
import com.venada.efinance.pojo.*;
import com.venada.efinance.security.SpringSecurityUtil;
import com.venada.efinance.service.*;
import com.venada.efinance.sms.SmsService;
import com.venada.efinance.util.DateUtils;
import com.venada.efinance.util.DecimalUtil;
//import com.yeepay.Configuration;
//import com.yeepay.PaymentForOnlineService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserWalletBusinessImpl implements UserWalletBusiness {
	private static final Logger logger = LoggerFactory
			.getLogger(UserWalletBusinessImpl.class);

	@Autowired
	private ProjectInvestBusiness projectInvestBusiness;
	@Autowired
	private UserWalletService userWalletService;
	@Autowired
	private RechargeRecordService rechargeRecordService;
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	@Autowired
	private WithdrawalRecordService withdrawalRecordService;
	@Autowired
	private UserService userService;
	@Autowired
	private SystemConfigBusiness systemConfigBusiness;
	@Autowired
	private BankCardBusiness bankCardBusiness;
	@Autowired
	private DealDetailBusinessImpl dealDetailBusinessImpl;
	@Autowired
	private OrderBusiness orderBusinessImpl;
	@Autowired
	private UserBusiness userBusiness;
	@Autowired
	private SecurityCenterBusiness securityCenterBusiness;
	@Autowired
	private MessageRuleBusiness messageRuleBusinessImpl;
	@Autowired
	private SmsService smsService;
	@Autowired
	private OperationLogBusiness opeartionLogBusiness;
	@Autowired
	OrderIdRecordService orderIdRecordService;
    @Autowired
    private ServletContext servletContext;

	@Transactional
	public String recharge(BigDecimal amount, String mobileNumber,
			String bank_type, String remoteIp, String bankCardId,
			HttpServletRequest request, HttpServletResponse response,
			String returnUrl) throws BusinessException {
		try {
			// 添加充值流水号
			String trackingId = generateCSerialNumber();

			RechargeRecord rechargeRecord = new RechargeRecord();
			rechargeRecord.setId(UUID.randomUUID().toString());
			rechargeRecord.setAmount(amount);
			rechargeRecord.setSerialNumber(trackingId);
			// 先设置为正在处理，等paypal|tenpay回调后再正式增加金额
			rechargeRecord.setStatus("2");
			rechargeRecord.setIpAddress(remoteIp);
			rechargeRecord.setDateTime(new Date());
			// 根据手机号码，获得用户信息
			User user = (User) userService.getObject("findUserByMoblieNumber",
					mobileNumber);
			rechargeRecord.setUserId(user.getId());

			// 查询被充值用户电子钱包
			UserWallet userWallet = getUserWalletByUserId(user.getId());

			// 当前用户
			User currentUser = SpringSecurityUtil.getCurrentUser();

			// 判断是否为他人充值
			if (!currentUser.getMobileNumber().equals(mobileNumber)) {
				rechargeRecord.setBalance(DecimalUtil.add(
						userWallet.getAmount(), amount));
				rechargeRecord.setDescription("用户：" + currentUser.getNickName()
						+ "为您充值");
				rechargeRecordService.saveObject("addRechargeRecord",
						rechargeRecord);

				rechargeRecord.setId(UUID.randomUUID().toString());
				rechargeRecord.setUserId(currentUser.getId());
				rechargeRecord.setBalance(getUserWalletByUserId(
						currentUser.getId()).getAmount());
				rechargeRecord.setDescription("为他人充值，充值号码：" + mobileNumber);
				rechargeRecordService.saveObject("addRechargeRecord",
						rechargeRecord);
			} else {
				rechargeRecord.setBalance(DecimalUtil.add(
						userWallet.getAmount(), amount));
				rechargeRecord.setDescription("充值");
				rechargeRecordService.saveObject("addRechargeRecord",
						rechargeRecord);
			}
			String product_name = mobileNumber + "纳币充值";
			// 网银在线
			/*if (request.getParameter("rechargeMethod").equals("3")) {
				return this.chinaBank(mobileNumber, amount, trackingId,
						request, response, returnUrl);
			}else if (request.getParameter("rechargeMethod").equals("5")) {
				return this.chinaBankByPost(mobileNumber, amount, trackingId, request,
						response, returnUrl);
			}//新浪支付
			else if (request.getParameter("rechargeMethod").equals("6")) {
				return this.sinaPayByPost(mobileNumber, amount, trackingId, request,
						response, returnUrl);
			}
			// 易宝支付
			else if (request.getParameter("rechargeMethod").equals("4")) {
				return this.ybBank(mobileNumber, amount, trackingId, request,
						response, returnUrl);
			} else {
				return this.tenpay(amount, bank_type, trackingId, remoteIp,
						request, response, product_name, returnUrl);
			}*/

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"recharge(" + amount + "," + mobileNumber + "," + remoteIp
							+ "," + bankCardId + ")方法出错！\t", e.getMessage() });
		}

		return null;
	}

	@Transactional
	public void addSignBenefit(BigDecimal amount, String userId)
			throws BusinessException {
		// 查询用户电子钱包
		UserWallet userWallet = getUserWalletByUserId(userId);
		// 更新用户电子钱包,增加余额
		userWallet.setAmount(DecimalUtil.add(userWallet.getAmount(), amount));
		int result = this.updateUserWallet(userWallet);
		if (result <= 0) {
			throw new BusinessException("0002");
		}
	}

	@Transactional
	public void realRecharge(String serialNumber) throws BusinessException {
		final  Integer s = 0;
		synchronized (s) {

			try {

				// 1.去充值记录表找到该记录2.根据ipn记录状态对用户钱包进行充值
				RechargeRecord rechargeRecord = (RechargeRecord) rechargeRecordService
						.getObject("findRechargeRecordBySerialNumber",
								serialNumber);

				if (rechargeRecord != null
						&& !rechargeRecord.getStatus().equals("0")) {
					// 根据手机号码，获得用户信息
					User user = (User) userService.getObject("findUserById",
							rechargeRecord.getUserId());

					// 查询被充值用户电子钱包
					UserWallet userWallet = getUserWalletByUserId(user.getId());
					
					OrderIdRecord orderIdRecord = new OrderIdRecord();
					orderIdRecord.setOrderId(serialNumber);
					orderIdRecord.setCreateTime(new Date());
					orderIdRecord.setAmount(rechargeRecord.getAmount());
					orderIdRecord.setUserId(user.getId());
					this.insertOrderIdRecord(orderIdRecord);

					OperationLog log = new OperationLog();
					log.setLogType(LogTypeEnum.RechargeLog.getIndex());
					log.setDataOld("用户:"+user.getMobileNumber()+"当前余额:"+userWallet.getAmount());
					
					// 更新被充值用户电子钱包,增加余额
					userWallet
							.setAmount(DecimalUtil.add(userWallet.getAmount(),
									rechargeRecord.getAmount()));

					int result = this.updateUserWallet(userWallet);
					
					log.setDataNew("用户:"+user.getMobileNumber()+"充值了金额:"+rechargeRecord.getAmount()+";当前余额:"+userWallet.getAmount());
					log.setRemark(serialNumber);
					log.setCreateTime(new Date());
					
					opeartionLogBusiness.addOperationLog(log);
					if (result <= 0) {
						throw new BusinessException("0002");
					}

					// 更新充值记录
					rechargeRecordService.updateObject(
							"updateRechargeRecordStatus", serialNumber);
					
					
					

					// 会员短信发送开始
					// ------------------------------

					logger.info("网银在线用户充值当前用户的手机号",
							serialNumber + "--" + user.getMobileNumber());
					boolean b = securityCenterBusiness.isOpen(user.getId());
					logger.info("网银在线用户充值是否会员", serialNumber + "--" + b);
					if (b) {
						logger.info("网银在线用户充值进入短信通知", serialNumber + "--" + b);
						BigDecimal totalFree = rechargeRecord.getAmount();
						String mobileNumber = messageRuleBusinessImpl
								.getSendMobileNumber(user);
						logger.info("网银在线用户充值是否打开短信通知", serialNumber + "--"
								+ messageRuleBusinessImpl.isSend(user));
						if (messageRuleBusinessImpl.isSend(user)) {
							logger.info(
									"网银在线用户充值开始发短信",
									serialNumber
											+ "--"
											+ mobileNumber
											+ "--MB-2014011458--"
											+ DateUtils
													.currentTime("yyyy-MM-dd HH:mm:ss")
											+ "--" + totalFree.toString());
							smsService.sendSms(mobileNumber, "",
									"MB-2014011458",
									DateUtils.currentTime("yyyy-MM-dd HH:mm"),
									totalFree.toString());
						}
					}
					// ------------------------------
					// 会员短信发送结束
					//---微信提醒
//					WeixinUser weixinUser=weixinUserBusiness.getWeixinUserByUserId(user.getId());
//					if(weixinUser!=null){
//						AdvancedUtil.sendCustomMessage(TokenThread.accessToken.getAccessToken(), AdvancedUtil.makeTextCustomMessage(weixinUser.getOpenid(),DateUtils
//							.currentTime("yyyy-MM-dd HH:mm:ss")+"\n您充值"+rechargeRecord.getAmount()+"成功！"));
//					}
					//---微信提醒结束
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new BusinessException("0002");
			}

		}

	}

	public String generateCSerialNumber() throws BusinessException {
		try {
			return "C" + DateUtils.today("yyyyMMddHHmmssSSS")
					+ System.currentTimeMillis();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"generateCSerialNumber()方法出错！\t", e.getMessage() });
		}
	}

	public boolean generateAccount(String userId) throws BusinessException {
		try {
			int result = 0;
			try {
				UserWallet userWallet = new UserWallet();
				userWallet.setAmount(new BigDecimal("0"));
				userWallet.setId(UUID.randomUUID().toString());
				userWallet.setStatus("0");
				userWallet.setUserId(userId);
				result = userWalletService.saveObject("addUserWallet",
						userWallet);
			} catch (ServiceException e) {
				throw new BusinessException("0002", new String[] {
						"generateAccount(userId:" + userId + ")方法出错！\t",
						e.getMessage() });
			}
			return result > 0;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"generateAccount(" + userId + ")方法出错！\t", e.getMessage() });
		}
	}

	public UserWallet getAmountByUserId(String userid) throws BusinessException {
		try {
			return (UserWallet) userWalletService.getObject(
					"getAmountByUserId", userid);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002",
					new String[] { "getAmountByUserId(" + userid + ")方法出错！\t",
							e.getMessage() });
		}
	}

	@Transactional
	public UserWallet getUserWalletByUserId(String userid)
			throws BusinessException {
		try {
			return (UserWallet) userWalletService.getObject(
					"selectUserWalletByUserId", userid);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"getUserWalletByUserId(" + userid + ")方法出错！\t",
					e.getMessage() });
		}
	}

	@Transactional
	public boolean withdrawal(BigDecimal amount, String bankCardId, String ip,String password)
			throws BusinessException {
		try {
			BankCard bankCard = bankCardBusiness.getBankCardById(bankCardId);
			// 获取当前用户
			User user = SpringSecurityUtil.getCurrentUser();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userid", user.getId());
			map.put("cardNumber", bankCard.getCardNumber());
			List<BankCard> list = bankCardBusiness.getBankCardByConditions(map);
			if(list.size()==0){
				throw new ServiceException("0002");
			}
			SecurityCenter securityCenter = securityCenterBusiness
					.getSecurityCenterByUserId(user.getId());
			if (securityCenter != null
					&& StringUtils.isNotEmpty(securityCenter.getPassword())
					&& securityCenter.getIsOpen() == 0) {
				if (md5PasswordEncoder.encodePassword(password, null).equals(
						securityCenter.getPassword())) {
					
				} else {
					throw new ServiceException("002");
				}
			}
			else if (md5PasswordEncoder.encodePassword(password, null).equals(
					user.getPassword())) {
			}else{
				throw new ServiceException("0002");
			}
			
			// 获取提现费率
			SystemConfig sysConfig = systemConfigBusiness
					.getSystemConfig("103");
			BigDecimal rate = BigDecimal.valueOf(Double.valueOf(sysConfig
					.getParamValue()));
			UserWallet userWallet = this.getUserWalletByUserId(user.getId());
			BigDecimal balance = userWallet.getAmount();
			// 扣除费率后最多可以提取的金额
			BigDecimal maxAmount = DecimalUtil.subtract(balance,
					DecimalUtil.multiply(balance, rate));

			// BEGIN 当天充值，当天不能取出
//			BigDecimal todayRecharge = rechargeRecordBusiness
//					.countTodayRechargeAmountByUserid(userWallet.getUserId());
//
//			maxAmount = DecimalUtil.subtract(balance, todayRecharge);
			// END
/**
 * 提现逻辑改变
 */
			if (maxAmount.compareTo(amount) == -1) {
				throw new ServiceException("0001");
			}
			
			BigDecimal allGameAmount = userBusiness.getAllGameAmount(user.getMobileNumber());
			BigDecimal allRechargeAmount = userBusiness.getAllRechargeAmount(user.getId());
			BigDecimal allInviteAmount = userBusiness.getAllInviteAmount(user.getId());
			BigDecimal allVipAmount = userBusiness.getAllVipAmount(user.getId());
			
			BigDecimal sub = allGameAmount.add(allInviteAmount).add(allVipAmount);
			BigDecimal mo = allRechargeAmount.add(allInviteAmount);
			
			if(user.getStatus()!=4 && mo.compareTo(new BigDecimal(0))!=0){
				 BigDecimal result = sub.divide(mo,RoundingMode.HALF_DOWN);
				 if(result.compareTo(new BigDecimal(1)) < 0){
					 throw new ServiceException("0001");
				 }
			}
			

			// 提现流水号
			String serialNo = generateTSerialNumber();

			// 提现流水记录
			WithdrawalRecord withdrawalRecord = new WithdrawalRecord();
			withdrawalRecord.setId(UUID.randomUUID().toString());
			withdrawalRecord.setDescription("提现");
			withdrawalRecord.setCardNumber(bankCard.getCardNumber());
			withdrawalRecord.setDateTime(new Date());
			withdrawalRecord.setIpAddress(ip);
			withdrawalRecord.setAmount(amount);
			// 提现记录加BankCardId字段
			withdrawalRecord.setBankCardId(bankCardId);
            // TODO: 设置提现到账时间
            Date cashDay = countWithdrawalSuccessTime(user);
            if (!securityCenterBusiness.isOpen(user.getId())) {
                withdrawalRecord.setCashDay(cashDay);
            }else{
                withdrawalRecord.setVipCashDay(cashDay);
            }
            /**
			// VIP与普通用户提现时间区别对待：VIP上午12点前(包括)提现，当天到账
			Integer tempCurrDay = Integer.parseInt(DateUtils
					.getDayOfWeekCn(DateFormatUtils.format(new Date(),
							"yyyy-MM-dd")));
			Integer delay = 0;
			// 普通用户提现

			if (!securityCenterBusiness.isOpen(user.getId())) {
				switch (tempCurrDay) {
				case 3:
					delay = 5;
					break;
				case 4:
					delay = 5;
					break;
				case 5:
					delay = 5;
					break;
				case 6:
					delay = 4;
					break;
				default:
					delay = 3;
					break;
				}
				withdrawalRecord
						.setCashDay(org.apache.commons.lang.time.DateUtils
								.addDays(new Date(), delay));
			} else {// VIP用户提现。如果周末，延迟。周一至周五每天中午12点之前当天到账，12点之后次日到账

				if (org.apache.commons.lang.time.DateUtils.getFragmentInHours(
						new Date(), Calendar.DAY_OF_YEAR) < 12) {
					switch (tempCurrDay) {
					case 6:
						delay = 2;
						break;
					case 7:
						delay = 1;
						break;
					default:
						delay = 0;
						break;
					}
				} else {

					switch (tempCurrDay) {
					case 5:
						delay = 3;
						break;
					case 6:
						delay = 2;
						break;
					default:
						delay = 1;
						break;
					}

				}

				withdrawalRecord
						.setVipCashDay(org.apache.commons.lang.time.DateUtils
								.addDays(new Date(), delay));
			}**/

			// 暂时由人工手动打到用户指定的账户中
			withdrawalRecord.setStatus("2");
			withdrawalRecord.setSerialNumber(serialNo);
			withdrawalRecord.setBalance(DecimalUtil.subtract(
					userWallet.getAmount(), amount));
			withdrawalRecord.setUserId(user.getId());
			withdrawalRecord.setOrderBy(Long.valueOf(1));
			withdrawalRecordService.saveObject("addWithdrawalRecord",
					withdrawalRecord);
			
			OrderIdRecord orderIdRecord = new OrderIdRecord();
			orderIdRecord.setOrderId(serialNo);
			orderIdRecord.setCreateTime(new Date());
			orderIdRecord.setAmount(amount);
			orderIdRecord.setUserId(user.getId());
			this.insertOrderIdRecord(orderIdRecord);

			// 提现手续费用流水记录
			DealDetail dealDetail = new DealDetail();
			dealDetail.setId(UUID.randomUUID().toString());
			dealDetail.setDescription("卡号为" + bankCard.getCardNumber()
					+ "的提现手续费用");
			dealDetail.setDateTime(new Date());
			dealDetail.setIpAddress(ip);
			dealDetail.setAmount(DecimalUtil.multiply(amount, BigDecimal
					.valueOf(Double.valueOf(sysConfig.getParamValue()))));
			dealDetail.setSerialNumber(serialNo);
			dealDetail.setBalance(DecimalUtil.subtract(userWallet.getAmount(),
					DecimalUtil.add(amount, dealDetail.getAmount())));
			dealDetail.setDealType(DealType.T.getIndex());
			dealDetail.setType("2");
			dealDetail.setStatus("0");
			dealDetail.setUserId(user.getId());
			dealDetail.setOrderBy(Long.valueOf(2));
			dealDetailBusinessImpl.addDealDetail(dealDetail);

			// 更新提现用户电子钱包余额
			userWallet.setAmount(DecimalUtil.subtract(userWallet.getAmount(),
					DecimalUtil.add(amount, dealDetail.getAmount())));
			int result = this.updateUserWallet(userWallet);

			return result > 0;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"withdrawal()方法出错！\t", e.getMessage() });
		}
	}

	public int updateUserWallet(UserWallet wallet) throws BusinessException {
		try {
			return userWalletService.updateObject("updateUserWallet", wallet);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"updateUserWallet()方法出错！\t", e.getMessage() });
		}
	}
	
	public void insertOrderIdRecord(OrderIdRecord oderIdRecord) throws BusinessException {
		orderIdRecordService.saveObject("insertOrderIdRecord", oderIdRecord);
	}

	public String generateTSerialNumber() throws BusinessException {
		try {
			return "T" + DateUtils.today("yyyyMMddHHmmssSSS")
					+ System.currentTimeMillis();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"generateCSerialNumber()方法出错！\t", e.getMessage() });
		}
	}

	public String generateYSerialNumber() throws BusinessException {
		try {
			return "Y" + DateUtils.today("yyyyMMddHHmmssSSS")
					+ System.currentTimeMillis();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"generateCSerialNumber()方法出错！\t", e.getMessage() });
		}
	}

	public String generateSerialNumber(String tag) throws BusinessException {
		try {
			return tag + DateUtils.today("yyyyMMddHHmmssSSS")
					+ System.currentTimeMillis();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"generateCSerialNumber()方法出错！\t", e.getMessage() });
		}
	}

	public String generatePSerialNumber() throws BusinessException {
		try {
			return "P" + DateUtils.today("yyyyMMddHHmmssSSS")
					+ System.currentTimeMillis();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"generateCSerialNumber()方法出错！\t", e.getMessage() });
		}
	}

	public String generateGSerialNumber() throws BusinessException {
		try {
			return "G" + DateUtils.today("yyyyMMddHHmmssSSS")
					+ System.currentTimeMillis();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"generateCSerialNumber()方法出错！\t", e.getMessage() });
		}
	}

	/**
	 * 扣除游戏押金
	 */
	@Transactional
	public BigDecimal withGameDeposit(BigDecimal amount, UserWallet userWallet,
			String ip) throws BusinessException {
		try {
			// 提现手续费用流水记录
			DealDetail dealDetail = new DealDetail();
			dealDetail.setId(UUID.randomUUID().toString());
			dealDetail.setDescription("扣除押金，扣除用户的游戏押金费用");
			dealDetail.setDateTime(new Date());
			dealDetail.setIpAddress(ip);
			dealDetail.setAmount(amount);
			dealDetail.setSerialNumber(this.generateYSerialNumber());
			dealDetail.setBalance(DecimalUtil.subtract(userWallet.getAmount(),
					amount));
			dealDetail.setDealType(DealType.Y.getIndex());
			dealDetail.setType("2");
			dealDetail.setStatus("0");
			dealDetail.setUserId(userWallet.getUserId());
			dealDetailBusinessImpl.addDealDetail(dealDetail);

			// 更新提现用户电子钱包余额

			userWallet.setAmount(DecimalUtil.subtract(userWallet.getAmount(),
					amount));
			this.updateUserWallet(userWallet);

			return userWallet.getAmount();
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"withGameDeposit()方法出错！\t", e.getMessage() });
		}
	}

	/**
	 * 返还游戏押金
	 */
	@Transactional
	public BigDecimal withAddGameDeposit(BigDecimal amount,
			UserWallet userWallet, String ip) throws BusinessException {
		try {
			// 提现手续费用流水记录
			DealDetail dealDetail = new DealDetail();
			dealDetail.setId(UUID.randomUUID().toString());
			dealDetail.setDescription("返还押金，返还用户的游戏押金费用");
			dealDetail.setDateTime(new Date());
			dealDetail.setIpAddress(ip);
			dealDetail.setAmount(amount);
			dealDetail.setSerialNumber(this.generateYSerialNumber());
			dealDetail.setBalance(DecimalUtil.add(userWallet.getAmount(),
					amount));
			dealDetail.setDealType(DealType.Y.getIndex());
			dealDetail.setType("1");
			dealDetail.setStatus("0");
			dealDetail.setUserId(userWallet.getUserId());
			dealDetailBusinessImpl.addDealDetail(dealDetail);

			// 更新提现用户电子钱包余额

			userWallet
					.setAmount(DecimalUtil.add(userWallet.getAmount(), amount));
			this.updateUserWallet(userWallet);

			return userWallet.getAmount();
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"withGameDeposit()方法出错！\t", e.getMessage() });
		}
	}

	/**
	 * 返还游戏奖励
	 */
	@Transactional
	public BigDecimal withAddGameReward(BigDecimal amount,
			UserWallet userWallet, String ip) throws BusinessException {
		try {
			// 提现手续费用流水记录
			DealDetail dealDetail = new DealDetail();
			dealDetail.setId(UUID.randomUUID().toString());
			dealDetail.setDescription("获得奖励，获得奖励费用");
			dealDetail.setDateTime(new Date());
			dealDetail.setIpAddress(ip);
			dealDetail.setAmount(amount);
			dealDetail.setSerialNumber(this.generateGSerialNumber());
			dealDetail.setBalance(DecimalUtil.add(userWallet.getAmount(),
					amount));
			dealDetail.setDealType(DealType.G.getIndex());
			dealDetail.setType("1");
			dealDetail.setStatus("0");
			dealDetail.setUserId(userWallet.getUserId());
			dealDetailBusinessImpl.addDealDetail(dealDetail);

			// 更新提现用户电子钱包余额

			userWallet
					.setAmount(DecimalUtil.add(userWallet.getAmount(), amount));
			this.updateUserWallet(userWallet);

			return userWallet.getAmount();
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"withAddGameReward()方法出错！\t", e.getMessage() });
		}
	}

	@SuppressWarnings("unused")
	private void paypal() {
		// PayInfo payInfo = new PayInfo();
		// payInfo.setAmount(amount.toPlainString());
		// payInfo.setCustomerId(mobileNumber);
		// payInfo.setTrackingId(trackingId);
		//
		// PayResponse resp = payBiz.recharge(payInfo);
		//
		// if (resp != null
		// && resp.getResponseEnvelope().getAck().toString()
		// .equalsIgnoreCase("SUCCESS")) {
		// map.put("ack", resp.getResponseEnvelope().getAck().getValue());

		/**
		 * Correlation identifier. It is a 13-character, alphanumeric string
		 * (for example, db87c705a910e) that is used only by PayPal Merchant
		 * Technical Support. Note: You must log and store this data for every
		 * response you receive. PayPal Technical Support uses the information
		 * to assist with reported issues.
		 */
		// map.put("correlationId", resp.getResponseEnvelope()
		// .getCorrelationId());

		/**
		 * Date on which the response was sent, for example:
		 * 2012-04-02T22:33:35.774-07:00 Note: You must log and store this data
		 * for every response you receive. PayPal Technical Support uses the
		 * information to assist with reported issues.
		 */
		// map.put("timeStamp", resp.getResponseEnvelope().getTimestamp());

		/**
		 * The pay key, which is a token you use in other Adaptive Payment APIs
		 * (such as the Refund Method) to identify this payment. The pay key is
		 * valid for 3 hours; the payment must be approved while the pay key is
		 * valid.
		 */
		// map.put("payKey", resp.getPayKey());

		/**
		 * The status of the payment. Possible values are: CREATED � The payment
		 * request was received; funds will be transferred once the payment is
		 * approved COMPLETED � The payment was successful INCOMPLETE � Some
		 * transfers succeeded and some failed for a parallel payment or, for a
		 * delayed chained payment, secondary receivers have not been paid ERROR
		 * � The payment failed and all attempted transfers failed or all
		 * completed transfers were successfully reversed REVERSALERROR � One or
		 * more transfers failed when attempting to reverse a payment PROCESSING
		 * � The payment is in progress PENDING � The payment is awaiting
		 * processing
		 */
		// map.put("payStatus", resp.getPaymentExecStatus());
		//
		// if (resp.getDefaultFundingPlan() != null) {
		// /** Default funding plan. */
		// map.put("Default Funding Plan", resp
		// .getDefaultFundingPlan().getFundingPlanId());
		// }
		//
		// map.put("redirectUrl",
		// "https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_ap-payment&paykey="
		// + resp.getPayKey());

		// map.put("redirectUrl",
		// "https://www.paypal.com/cgi-bin/webscr?cmd=_ap-payment&paykey="
		// + resp.getPayKey());
		// } else {
		// throw new BusinessException("0002", new String[] { "recharge("
		// + amount + "," + mobileNumber + "," + remoteIp + ","
		// + bankCardId + ")方法出错！\t" });
		// }
		//
		// rechargeRecord.setAck(map.get("ack"));
		// rechargeRecord.setCorrelationId(map.get("correlationId"));
		// rechargeRecord.setTimeStamp(map.get("timeStamp"));
		// rechargeRecord.setPayKey(map.get("payKey"));
		// rechargeRecord.setPayStatus(map.get("payStatus"));
	}

	/*private String chinaBank(String mobileNumber, BigDecimal amount,
			String trackingId, HttpServletRequest request,
			HttpServletResponse response, String returnUrl) {
		// 初始化定义参数
		String v_mid = "22843974";// 商户号
		String key = "venadaroot%$#@!";
		String v_url = "http://www.wowpower.cn/notifyPay/chinabank.html"; // 商户自定义返回接收支付结果的页面
		String v_url2 = "[url:=http://www.wowpower.cn/notifyPay/chinabankAutoReceive.html]"; // 自动对账地址，防止掉单
		String v_oid = trackingId;
		String v_amount = amount.toString();// 订单金额
		String v_moneytype = "CNY"; // 币种

		String text = v_amount + v_moneytype + v_oid + v_mid + v_url + key; // 拼凑加密串
		MD5 md5 = new MD5();
		String v_md5info = md5.getMD5ofStr(text).toUpperCase(); // 对拼凑串MD5私钥加密后的值,网银支付平台对MD5值只认大写字符串，所以小写的MD5值得转换为大写

		StringBuilder sb = new StringBuilder(
				"https://pay3.chinabank.com.cn/PayGate?encoding=UTF-8");
		sb.append("&v_md5info=").append(v_md5info).append("&v_mid=")
				.append(v_mid).append("&v_oid=").append(v_oid)
				.append("&v_amount=").append(v_amount).append("&v_moneytype=")
				.append(v_moneytype).append("&v_url=").append(v_url)
				.append("&remark2=").append(v_url2).append("&remark1=")
				.append(mobileNumber);
		return sb.toString();
	}
	
	private String chinaBankByPost(String mobileNumber, BigDecimal amount,
			String trackingId, HttpServletRequest request,
			HttpServletResponse response, String returnUrl) {
		// 初始化定义参数
		String v_mid = "22843974";// 商户号
		String key = "venadaroot%$#@!";
		String v_url = "http://www.wowpower.cn/notifyPay/chinabank.html"; // 商户自定义返回接收支付结果的页面
		String v_url2 = "[url:=http://www.wowpower.cn/notifyPay/chinabankAutoReceive.html]"; // 自动对账地址，防止掉单
		String v_oid = trackingId;
		String v_amount = amount.toString();// 订单金额
		String v_moneytype = "CNY"; // 币种

		String text = v_amount + v_moneytype + v_oid + v_mid + v_url + key; // 拼凑加密串
		MD5 md5 = new MD5();
		String v_md5info = md5.getMD5ofStr(text).toUpperCase(); // 对拼凑串MD5私钥加密后的值,网银支付平台对MD5值只认大写字符串，所以小写的MD5值得转换为大写
		StringBuilder sbr=new StringBuilder("");
        sbr.append("<form action=\"https://pay3.chinabank.com.cn/PayGate\" method=\"POST\" name=\"E_FORM\">")
           .append("<input type=\"hidden\" name=\"v_md5info\" ").append("value=\"").append(v_md5info).append("\">")
           .append("<input type=\"hidden\" name=\"v_mid\" ").append("value=\"").append(v_mid).append("\">")
           .append("<input type=\"hidden\" name=\"v_oid\" ").append("value=\"").append(v_oid).append("\">")
           .append("<input type=\"hidden\" name=\"v_amount\" ").append("value=\"").append(v_amount).append("\">")
           .append("<input type=\"hidden\" name=\"v_moneytype\" ").append("value=\"").append(v_moneytype).append("\">")
           .append("<input type=\"hidden\" name=\"v_url\" ").append("value=\"").append(v_url).append("\">")
           .append("<input type=\"hidden\" name=\"remark1\" ").append("value=\"").append(mobileNumber).append("\">")
           .append("<input type=\"hidden\" name=\"remark2\" ").append("value=\"").append(v_url2).append("\">")
           .append("</form>");
		return sbr.toString();
	}*/
	
	
	private String sinaPayByPost(String mobileNumber, BigDecimal amount,
			String trackingId, HttpServletRequest request,
			HttpServletResponse response, String returnUrl) {
		// 初始化定义参数
		String inputCharset = "1";// utf-8
		String bgUrl = "http://www.wowpower.cn/notifyPay/sinaPay.html"; // 商户自定义返回接收支付结果的页面
		String version = "v2.3";
		String language = "1";
		String signType = "1"; //1是MD5
		String merchantAcctId = "200100100120000672295700001";
		String orderId = trackingId;
		String orderAmount = amount.multiply(new BigDecimal(100)).toString();
		String orderTime = DateUtils.currentTime("yyyyMMddHHmmss");
		String productName = "纳币";
		String pid = "200006722957";
		String text = "inputCharset=1&bgUrl=http://www.wowpower.cn/notifyPay/sinaPay.html&version=v2.3&language=1&signType=1&merchantAcctId="+merchantAcctId+"&orderId="+orderId+"&orderAmount="+orderAmount+"&orderTime="+orderTime+"&productName="+productName+"&pid="+pid+"&key=venada0203wowpower"; // 拼凑加密串
	//	MD5 md5 = new MD5();	
	//	String signMsg = md5.getMD5ofStr(text).toLowerCase(); 
		String signMsg = md5PasswordEncoder.encodePassword(text, "").toLowerCase(); 
		
		StringBuilder sbr=new StringBuilder("");
        sbr.append("<form action=\"https://gate.pay.sina.com.cn/acquire-order-channel/gateway/receiveOrderLoading.htm\" method=\"POST\" name=\"E_FORM\">")
           .append("<input type=\"hidden\" name=\"inputCharset\" ").append("value=\"").append(inputCharset).append("\">")
           .append("<input type=\"hidden\" name=\"bgUrl\" ").append("value=\"").append(bgUrl).append("\">")
           .append("<input type=\"hidden\" name=\"version\" ").append("value=\"").append(version).append("\">")
          .append("<input type=\"hidden\" name=\"language\" ").append("value=\"").append(language).append("\">")
           .append("<input type=\"hidden\" name=\"signType\" ").append("value=\"").append(signType).append("\">")
           .append("<input type=\"hidden\" name=\"merchantAcctId\" ").append("value=\"").append(merchantAcctId).append("\">")
           .append("<input type=\"hidden\" name=\"orderId\" ").append("value=\"").append(orderId).append("\">")
           .append("<input type=\"hidden\" name=\"orderAmount\" ").append("value=\"").append(orderAmount).append("\">")
           .append("<input type=\"hidden\" name=\"orderTime\" ").append("value=\"").append(orderTime).append("\">")
           .append("<input type=\"hidden\" name=\"productName\" ").append("value=\"").append(productName).append("\">")
            .append("<input type=\"hidden\" name=\"pid\" ").append("value=\"").append(pid).append("\">")
             .append("<input type=\"hidden\" name=\"signMsg\" ").append("value=\"").append(signMsg).append("\">")
           .append("</form>");
		return sbr.toString();
	}
/*

	private String ybBank(String mobileNumber, BigDecimal amount,
			String trackingId, HttpServletRequest request,
			HttpServletResponse response, String returnUrl) {
		// 初始化定义参数
		String keyValue = Configuration.getInstance().getValue("keyValue");
		String nodeAuthorizationURL = Configuration.getInstance().getValue(
				"yeepayCommonReqURL");
		String p0_Cmd = "Buy";
		String p1_MerId = Configuration.getInstance().getValue("p1_MerId");// 商户号
		String p2_Order = trackingId;
		String p3_Amt = amount.toString();// 订单金额
		String p4_Cur = "CNY"; // 币种
		String p5_Pid = "蛙宝网纳币充值";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		String p8_Url = "http://www.wowpower.cn/notifyPay/yeepayCallBack.html"; // 商户自定义返回接收支付结果的页面
		String p9_SAF = "0";
		String pa_MP = "";
		String pd_FrpId = "";
		String pr_NeedResponse = "1";
		String hmac = PaymentForOnlineService.getReqMd5HmacForOnlinePayment(
				p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat,
				p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse,
				keyValue);
		try {
			p5_Pid = URLEncoder.encode(p5_Pid, "gb2312");
		} catch (UnsupportedEncodingException e) {
			logger.error("中文转换失败", "蛙宝网纳币充值");
		}

		StringBuilder sb = new StringBuilder(nodeAuthorizationURL);
		sb.append("?p0_Cmd=").append(p0_Cmd).append("&p1_MerId=")
				.append(p1_MerId).append("&p2_Order=").append(p2_Order)
				.append("&p3_Amt=").append(p3_Amt).append("&p4_Cur=")
				.append(p4_Cur).append("&p5_Pid=").append(p5_Pid)
				.append("&p6_Pcat=").append(p6_Pcat).append("&p7_Pdesc=")
				.append(p7_Pdesc).append("&p8_Url=").append(p8_Url)
				.append("&p9_SAF=").append(p9_SAF).append("&pa_MP=")
				.append(pa_MP).append("&pd_FrpId=").append(pd_FrpId)
				.append("&pr_NeedResponse=").append(pr_NeedResponse)
				.append("&hmac=").append(hmac);

		return sb.toString();
	}*/

	private String tenpay(BigDecimal amount, String bank_type,
			String trackingId, String remoteIp, HttpServletRequest request,
			HttpServletResponse response, String productName, String returnUrl)
			throws UnsupportedEncodingException {
		/* 商品价格（包含运费），以分为单位 */
		double total_fee = (amount.doubleValue() * 100);
		int fee = (int) total_fee;

		// 获取提交的商品名称
		// String product_name = "纳币充值";

		// 获取提交的备注信息
		String remarkexplain = trackingId;

		String desc = "商品:" + productName + ",备注:" + remarkexplain;

		// 获取提交的订单号
		String out_trade_no = trackingId;

		// 支付方式
		String trade_mode = "1";

		// ---------------生成订单号 开始------------------------
		// 当前时间 yyyyMMddHHmmss
		// String currTime = TenpayUtil.getCurrTime();
		// 8位日期
		// String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		// String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		// String strReq = strTime + strRandom;
		// 订单号，此处用时间加随机数生成，商户根据自己情况调整，只要保持全局唯一就行
		// String out_trade_no = strReq;
		// ---------------生成订单号 结束------------------------

		String currTime = TenpayUtil.getCurrTime();
		// 创建支付请求对象
		RequestHandler reqHandler = new RequestHandler(request, response);
		reqHandler.init();
		// 设置密钥
		reqHandler.setKey("34239766a166764470e9880db21c145c");
		// 设置支付网关
		reqHandler.setGateUrl("https://gw.tenpay.com/gateway/pay.htm");

		// -----------------------------
		// 设置支付参数
		// -----------------------------
		reqHandler.setParameter("partner", "1216941201"); // 商户号
		reqHandler.setParameter("out_trade_no", out_trade_no); // 商家订单号
		reqHandler.setParameter("total_fee", String.valueOf(fee)); // 商品金额,以分为单位
		reqHandler.setParameter("return_url", returnUrl);
		// "http://www.wowpower.cn/user/account/manager"); // 交易完成后跳转的URL
		reqHandler.setParameter("notify_url",
				"http://www.wowpower.cn/notifyPay/tenpay"); // 接收财付通通知的URL
		reqHandler.setParameter("body", desc); // 商品描述
		reqHandler.setParameter("bank_type",
				StringUtils.isBlank(bank_type) ? "DEFAULT" : bank_type); // 银行类型(中介担保时此参数无效)
		reqHandler.setParameter("spbill_create_ip", remoteIp); // 用户的公网ip，不是商户服务器IP
		reqHandler.setParameter("fee_type", "1"); // 币种，1人民币
		reqHandler.setParameter("subject", trackingId); // 商品名称(中介交易时必填)

		// 系统可选参数
		reqHandler.setParameter("sign_type", "MD5"); // 签名类型,默认：MD5
		reqHandler.setParameter("service_version", "1.0"); // 版本号，默认为1.0
		reqHandler.setParameter("input_charset", "UTF-8"); // 字符编码
		reqHandler.setParameter("sign_key_index", "1"); // 密钥序号

		// 业务可选参数
		reqHandler.setParameter("attach", ""); // 附加数据，原样返回
		reqHandler.setParameter("product_fee", ""); // 商品费用，必须保证transport_fee +
													// product_fee=total_fee
		reqHandler.setParameter("transport_fee", "0"); // 物流费用，必须保证transport_fee
														// +
														// product_fee=total_fee
		reqHandler.setParameter("time_start", currTime); // 订单生成时间，格式为yyyymmddhhmmss
		reqHandler.setParameter("time_expire", ""); // 订单失效时间，格式为yyyymmddhhmmss
		reqHandler.setParameter("buyer_id", ""); // 买方财付通账号
		reqHandler.setParameter("goods_tag", ""); // 商品标记
		reqHandler.setParameter("trade_mode", trade_mode); // 交易模式，1即时到账(默认)，2中介担保，3后台选择（买家进支付中心列表选择）
		reqHandler.setParameter("transport_desc", ""); // 物流说明
		reqHandler.setParameter("trans_type", "1"); // 交易类型，1实物交易，2虚拟交易
		reqHandler.setParameter("agentid", ""); // 平台ID
		reqHandler.setParameter("agent_type", ""); // 代理模式，0无代理(默认)，1表示卡易售模式，2表示网店模式
		reqHandler.setParameter("seller_id", ""); // 卖家商户号，为空则等同于partner

		// 请求的url
		String requestUrl = reqHandler.getRequestURL();

		// 获取debug信息,建议把请求和debug信息写入日志，方便定位问题
		String debuginfo = reqHandler.getDebugInfo();
		logger.info("requestUrl:{}", requestUrl);
		logger.info("sign_String:{}", debuginfo);

		return requestUrl;

	}

	@Transactional
	public String payforCoolpos(Long orderId, BigDecimal amount,
			String bank_type, String remoteIp, String bankCardId,
			HttpServletRequest request, HttpServletResponse response)
			throws BusinessException {
		try {
			// 添加充值流水号
			String returnUrl = "http://www.wowpower.cn/manager/orderDetailPaymentSucess";
			String serialNumber = "O" + generateCSerialNumber();
			Order order = new Order();
			order.setId(orderId);
			order.setPayTime(new Date());
			order.setTradeStatus(TradeStatus.PayIng.ordinal());
			order.setSerialNumber(serialNumber);
			orderBusinessImpl.updateOrderById(order);
			String product_name = "购买酷刷coolpos移动金融刷卡机（器）";
			return tenpay(amount, bank_type, serialNumber, remoteIp, request,
					response, product_name, returnUrl);

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"payforCoolpos(" + amount + "," + "," + remoteIp + ","
							+ bankCardId + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public void realPayFor(String serialNumber) throws BusinessException {
		try {

			Order order = (Order) orderBusinessImpl
					.queryorderBySerialNumber(serialNumber);

			if (order != null && !order.getTradeStatus().equals("2")) {
				// 更新订单记录
				order.setTradeStatus(TradeStatus.TradeCommit.ordinal());
				order.setPayTime(new Date());
				orderBusinessImpl.updateOrderById(order);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002");
		}
	}

	@Override
	public BigDecimal withInviteBenefit(BigDecimal amount,
			UserWallet userWallet, String ip) throws BusinessException {
		try {
			// 推荐的用户玩游戏后获取奖励
			DealDetail dealDetail = new DealDetail();
			dealDetail.setId(UUID.randomUUID().toString());
			dealDetail.setDescription("邀请用户玩游戏,获取奖励");
			dealDetail.setDateTime(new Date());
			dealDetail.setIpAddress(ip);
			dealDetail.setAmount(amount);
			dealDetail.setSerialNumber(this.generateSerialNumber("B"));
			dealDetail.setBalance(DecimalUtil.add(userWallet.getAmount(),
					amount));
			dealDetail.setDealType(DealType.B.getIndex());
			dealDetail.setType("1");
			dealDetail.setStatus("0");
			dealDetail.setUserId(userWallet.getUserId());
			dealDetailBusinessImpl.addDealDetail(dealDetail);

			// 更新提现用户电子钱包余额

			userWallet
					.setAmount(DecimalUtil.add(userWallet.getAmount(), amount));
			this.updateUserWallet(userWallet);

			return userWallet.getAmount();
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"withGameDeposit()方法出错！\t", e.getMessage() });
		}
	}

	/**
	 * 扣除用户支持众筹项目的费用
	 */
	@Transactional
	public BigDecimal payForProject(ProjectUser puser, UserWallet userWallet,
			String ip, ProjectInvest projectInvest, Integer count)
			throws BusinessException {
		try {
			User user = userBusiness.findUserById(userWallet.getUserId());
			DealDetail dealDetail = new DealDetail();
			dealDetail.setId(UUID.randomUUID().toString());
			dealDetail.setDescription("扣除用户支持众筹项目的费用");
			dealDetail.setDateTime(new Date());
			dealDetail.setIpAddress(ip);
			dealDetail.setAmount(puser.getRealAmount());
			dealDetail.setSerialNumber(this.generateSerialNumber("Z"));
			dealDetail.setBalance(DecimalUtil.subtract(userWallet.getAmount(),
					puser.getRealAmount()));
			dealDetail.setDealType(DealType.Z.getIndex());
			dealDetail.setType("2");
			dealDetail.setStatus("0");
			dealDetail.setUserId(userWallet.getUserId());

			projectInvest.setAlready_support_num(projectInvest
					.getAlready_support_num() + count);
			projectInvestBusiness.updateProjectInvest(projectInvest);
			dealDetailBusinessImpl.addDealDetail(dealDetail);

			userWallet.setAmount(DecimalUtil.subtract(userWallet.getAmount(),
					puser.getRealAmount()));
			this.updateUserWallet(userWallet);

			// 保存用户支持众筹项目记录
			puser.setStatus(2);
			puser.setSuccessTime(new Date());
			projectInvestBusiness.saveProjectUser(puser);
			int credits = user.getCredits();
			int realAmountInteger = puser.getRealAmount().intValue();
			credits = credits + realAmountInteger * 10;
			user.setLevel(user.getLevel() + credits);
			user.setCredits(credits);
			userBusiness.updateUserByMobileNumber(user);
			return userWallet.getAmount();
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"payForProject()方法出错！\t", e.getMessage() });
		}
	}

	@Override
	@Transactional
	public void payForFriends(User user, User _user, int needToPay, String ip,
			UserWallet userWallet, int months) throws BusinessException,
			ParseException {
		DealDetail dealDetail = new DealDetail();
		dealDetail.setId(UUID.randomUUID().toString());
		dealDetail.setDescription("扣除用户赠送好友会员的费用");
		dealDetail.setDateTime(new Date());
		dealDetail.setIpAddress(ip);
		dealDetail.setAmount(new BigDecimal(needToPay));
		dealDetail.setSerialNumber(this.generateSerialNumber("V"));
		dealDetail.setBalance(DecimalUtil.subtract(userWallet.getAmount(),
				new BigDecimal(needToPay)));
		dealDetail.setDealType(DealType.V.getIndex());
		dealDetail.setType("2");
		dealDetail.setStatus("0");
		dealDetail.setUserId(userWallet.getUserId());
		// 插入交易明细表
		dealDetailBusinessImpl.addDealDetail(dealDetail);
		// 更新用户钱包
		userWallet.setAmount(DecimalUtil.subtract(userWallet.getAmount(),
				new BigDecimal(needToPay)));
		this.updateUserWallet(userWallet);

		//
		SecurityCenter securityCenter = securityCenterBusiness
				.getSecurityCenterByUserId(user.getId());
		if (securityCenter == null) {
			securityCenter = new SecurityCenter();
			securityCenter.setId(UUID.randomUUID().toString());
			securityCenter.setUserId(user.getId());
			securityCenter.setIsAutoRenew(1);
			securityCenter.setIsOpen(0);
			securityCenter.setExpiryDate(DateUtils.getAdd(
					DateUtils.DEFAULT_MONTH, months));
			securityCenter.setCreateBy(_user.getId());
			securityCenter.setCreateTime(new Date());
			securityCenterBusiness.addSecurityCenter(securityCenter);
		} else {
			securityCenter.setIsAutoRenew(1);
			securityCenter.setIsOpen(0);
			if (DateUtils.compareCurrentDate(DateUtils.toString(
					securityCenter.getExpiryDate(), "yyyy-MM-dd HH:mm:ss"))) {
				securityCenter.setExpiryDate(DateUtils.parseDate(DateUtils
						.getAddDay(
								DateUtils.toString(new Date(), "yyyy-MM-dd"),
								DateUtils.DEFAULT_MONTH, months, "yyyy-MM-dd"),
						"yyyy-MM-dd"));
			} else {
				securityCenter.setExpiryDate(DateUtils.parseDate(DateUtils
						.getAddDay(DateUtils.toString(
								securityCenter.getExpiryDate(), "yyyy-MM-dd"),
								DateUtils.DEFAULT_MONTH, months, "yyyy-MM-dd"),
						"yyyy-MM-dd"));
			}

			securityCenter.setCreateBy(_user.getId());
			securityCenter.setModifyTime(new Date());
			securityCenter.setModifyBy(_user.getId());
			securityCenterBusiness
					.updateSecurityCenterWithNoPassword(securityCenter);
		}

	}
	
	

	@Override
	public void payForRenewal(User user, int needToPay, String ip,
			UserWallet userWallet, int months) throws BusinessException,
			ParseException {

		DealDetail dealDetail = new DealDetail();
		dealDetail.setId(UUID.randomUUID().toString());
		dealDetail.setDescription("扣续费vip会员的费用");
		dealDetail.setDateTime(new Date());
		dealDetail.setIpAddress(ip);
		dealDetail.setAmount(new BigDecimal(needToPay));
		dealDetail.setSerialNumber(this.generateSerialNumber("N"));
		dealDetail.setBalance(DecimalUtil.subtract(userWallet.getAmount(),
				new BigDecimal(needToPay)));
		dealDetail.setDealType(DealType.N.getIndex());
		dealDetail.setType("2");
		dealDetail.setStatus("0");
		dealDetail.setUserId(userWallet.getUserId());
		// 插入交易明细表
		dealDetailBusinessImpl.addDealDetail(dealDetail);
		// 更新用户钱包
		userWallet.setAmount(DecimalUtil.subtract(userWallet.getAmount(),
				new BigDecimal(needToPay)));
		this.updateUserWallet(userWallet);

		//
		SecurityCenter securityCenter = securityCenterBusiness
				.getSecurityCenterByUserId(user.getId());
		if (securityCenter == null) {
			securityCenter = new SecurityCenter();
			securityCenter.setId(UUID.randomUUID().toString());
			securityCenter.setUserId(user.getId());
			securityCenter.setIsAutoRenew(1);
			securityCenter.setIsOpen(0);
			securityCenter.setExpiryDate(DateUtils.getAdd(
					DateUtils.DEFAULT_MONTH, months));
			securityCenter.setCreateBy(user.getId());
			securityCenter.setCreateTime(new Date());
			securityCenterBusiness.addSecurityCenter(securityCenter);
		} else {
			securityCenter.setIsAutoRenew(1);
			securityCenter.setIsOpen(0);
			if (DateUtils.compareCurrentDate(DateUtils.toString(
					securityCenter.getExpiryDate(), "yyyy-MM-dd HH:mm:ss"))) {
				securityCenter.setExpiryDate(DateUtils.parseDate(DateUtils
						.getAddDay(
								DateUtils.toString(new Date(), "yyyy-MM-dd"),
								DateUtils.DEFAULT_MONTH, months, "yyyy-MM-dd"),
						"yyyy-MM-dd"));
			} else {
				securityCenter.setExpiryDate(DateUtils.parseDate(DateUtils
						.getAddDay(DateUtils.toString(
								securityCenter.getExpiryDate(), "yyyy-MM-dd"),
								DateUtils.DEFAULT_MONTH, months, "yyyy-MM-dd"),
						"yyyy-MM-dd"));
			}

			securityCenter.setCreateBy(user.getId());
			securityCenter.setModifyTime(new Date());
			securityCenter.setModifyBy(user.getId());
			securityCenterBusiness
					.updateSecurityCenterWithNoPassword(securityCenter);
		}

	}

    @Override
    public void randomVip(User user, Integer months) throws BusinessException, ParseException {


        SecurityCenter securityCenter = securityCenterBusiness
                .getSecurityCenterByUserId(user.getId());
        if (securityCenter == null) {
            securityCenter = new SecurityCenter();
            securityCenter.setId(UUID.randomUUID().toString());
            securityCenter.setUserId(user.getId());
            securityCenter.setIsAutoRenew(1);
            securityCenter.setIsOpen(0);
            securityCenter.setExpiryDate(DateUtils.getAdd(
                    DateUtils.DEFAULT_MONTH, months));
            securityCenter.setCreateBy(user.getId());
            securityCenter.setCreateTime(new Date());
            securityCenterBusiness.addSecurityCenter(securityCenter);
        } else {
            securityCenter.setIsAutoRenew(1);
            securityCenter.setIsOpen(0);
            if (DateUtils.compareCurrentDate(DateUtils.toString(
                    securityCenter.getExpiryDate(), "yyyy-MM-dd HH:mm:ss"))) {
                securityCenter.setExpiryDate(DateUtils.parseDate(DateUtils
                                .getAddDay(
                                        DateUtils.toString(new Date(), "yyyy-MM-dd"),
                                        DateUtils.DEFAULT_MONTH, months, "yyyy-MM-dd"),
                        "yyyy-MM-dd"));
            } else {
                securityCenter.setExpiryDate(DateUtils.parseDate(DateUtils
                                .getAddDay(DateUtils.toString(
                                                securityCenter.getExpiryDate(), "yyyy-MM-dd"),
                                        DateUtils.DEFAULT_MONTH, months, "yyyy-MM-dd"),
                        "yyyy-MM-dd"));
            }

            securityCenter.setCreateBy(user.getId());
            securityCenter.setModifyTime(new Date());
            securityCenter.setModifyBy(user.getId());
            securityCenterBusiness
                    .updateSecurityCenterWithNoPassword(securityCenter);
        }
    }

    @Override
	public BigDecimal withAddExchange(BigDecimal amount, UserWallet userWallet,
			String ip) throws BusinessException {
		try {

			DealDetail dealDetail = new DealDetail();
			dealDetail.setId(UUID.randomUUID().toString());
			dealDetail.setDescription("获得兑换券金额");
			dealDetail.setDateTime(new Date());
			dealDetail.setIpAddress(ip);
			dealDetail.setAmount(amount);
			dealDetail.setSerialNumber(this.generateGSerialNumber());
			dealDetail.setBalance(DecimalUtil.add(userWallet.getAmount(),
					amount));
			dealDetail.setDealType(DealType.G.getIndex());
			dealDetail.setType("1");
			dealDetail.setStatus("0");
			dealDetail.setUserId(userWallet.getUserId());
			dealDetailBusinessImpl.addDealDetail(dealDetail);

			// 更新提现用户电子钱包余额

			userWallet
					.setAmount(DecimalUtil.add(userWallet.getAmount(), amount));
			this.updateUserWallet(userWallet);

			return userWallet.getAmount();
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"withAddGameReward()方法出错！\t", e.getMessage() });
		}
	}

    private Date countWithdrawalSuccessTime(User user){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        Holidays today = (Holidays) servletContext.getAttribute(sdf.format(currentDate));
        int delayDays ;
        if(!securityCenterBusiness.isOpen(user.getId())){
            delayDays = 3;
        }else{
            if (org.apache.commons.lang.time.DateUtils.getFragmentInHours(currentDate, Calendar.DAY_OF_YEAR) < 12) {
                delayDays = 0;
                //如果提现当天是节假日，计算延迟到账天数
                if(today.getIs_holiday() == 1){
                    delayDays = 1;
                }

            }else{
                delayDays = 1;
            }
        }

        //当延迟到账天数大于0时，看后面的一天是否是工作日
        /*
        if(delayDays > 0){
            Date nextDay = org.apache.commons.lang.time.DateUtils.addDays(currentDate, 1);
            String nextDayStr = sdf.format(nextDay);
            Holidays nextHoliday = (Holidays) servletContext.getAttribute(nextDayStr);
            while(nextHoliday.getIs_holiday() == 1){
                nextDay = org.apache.commons.lang.time.DateUtils.addDays(nextDay, 1);
                nextHoliday = (Holidays) servletContext.getAttribute(sdf.format(nextDay));
                delayDays ++;
            }
            return org.apache.commons.lang.time.DateUtils.addDays(currentDate, delayDays);
        }
        */

        while(delayDays > 0){
            currentDate = org.apache.commons.lang.time.DateUtils.addDays(currentDate, 1);
            String nextDayStr = sdf.format(currentDate);
            Holidays nextHoliday = (Holidays) servletContext.getAttribute(nextDayStr);
            if(nextHoliday.getIs_holiday() == 0){
                delayDays--;
            }
        }


        return currentDate;
    }

}