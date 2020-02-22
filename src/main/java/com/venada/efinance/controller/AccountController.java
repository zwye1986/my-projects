package com.venada.efinance.controller;

import com.venada.efinance.business.*;
import com.venada.efinance.common.controller.BaseController;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.*;
import com.venada.efinance.security.SpringSecurityUtil;
import com.venada.efinance.sms.SmsService;
import com.venada.efinance.util.DecimalUtil;
import com.venada.efinance.util.LevelUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户账户相关操作的控制器
 * 
 * @author yma
 * 
 */
@Controller
@RequestMapping(value = "/user/account")
public class AccountController extends BaseController {
	private static final Logger logger = LoggerFactory
			.getLogger(AccountController.class);

	@Autowired
	private UserBusiness userBusiness;
	@Autowired
	private RechargeRecordBusiness rechargeRecordBusiness;
	@Autowired
	private UserWalletBusiness userWalletBusiness;
	@Autowired
	private DealDetailBusiness dealDetailBusiness;
	@Autowired
	private TransactionDetailsBusiness transactionDetailsBusiness;
	@Autowired
	private BankCardBusiness bankCardBusiness;
	@Autowired
	private SystemConfigBusiness systemConfigBusiness;
	@Autowired
	private AmountOptionBusiness amountOptionBusiness;
	@Autowired
	private SecurityCenterBusiness securityCenterBusiness;
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	@Autowired
	private UserDetailBusiness userDetailBusiness;
	@Autowired
	private SmsService smsService;
	@Autowired
	private LpRecordBusiness recordBusiness;
	@Autowired
	private MessageRuleBusiness messageRuleBusinessImpl;
	@Autowired
	private GameBusiness gameBusinessImpl;
	@Autowired
	private InviteBenefitBusiness inviteBenefitBusinessImp;
	@Autowired
	private UserSignInBusiness userSignInBusinessImpl;

	@RequestMapping(value = "/userCenter", method = RequestMethod.GET)
	public String toUserCenter() {
		return ".newAccountManager";
	}

	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String toAccountManger(Model model, HttpServletRequest request,
			HttpSession session) {
		try {
			User user = SpringSecurityUtil.getCurrentUser();
			int vipTag ;
			model.addAttribute("rechargeAmount", rechargeRecordBusiness
					.getRechargeAmountByUserId(user.getId()));
			model.addAttribute("walletAmount",
					userWalletBusiness.getAmountByUserId(user.getId()));
			model.addAttribute("dealDetailAmount", dealDetailBusiness
					.getDealDetailAmountByUserId(user.getId()));
			String cate = request.getParameter("cate");
			model.addAttribute("cate", cate);
			model.addAttribute("level", user.getLevel());
			// 注册日期
			model.addAttribute("createDate", user.getCreateTime());
			// 获取用户下一个积分等级
			int nextlevel = (int) Math
					.ceil(LevelUtils.getLevel(user.getLevel() / 100.0));
			model.addAttribute("nextlevel", LevelUtils.getNextLevel(nextlevel));
			// 计算用户距离下一个积分等级的积分
			int nextLevelCredits = LevelUtils.getNextLevel(user.getLevel(),
					nextlevel);
			model.addAttribute("nextLevelCredits", nextLevelCredits);

			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("userid", user.getMobileNumber());
			model.addAttribute("gameBenefit",
					gameBusinessImpl.getUserGameBenefitInfo(condition));
			// 查询推荐奖励
			condition.clear();
			condition.put("inviteUserId", user.getId());
			model.addAttribute("total",
					inviteBenefitBusinessImp.getInviteBenefitTotal(condition));
			// 查询签到奖励
			condition.clear();
			condition.put("mobilePhone", user.getMobileNumber());
			model.addAttribute("signTotal", userSignInBusinessImpl
					.sumSignBenefitByMobileNumber(condition));
			model.addAttribute("isAutoRenew",securityCenterBusiness.isAutoRenew(user.getId()));
			int isExpire = 0;
			if (securityCenterBusiness.isOpen(user.getId())) {
				vipTag = 1;
				// 显示会员到期时间
				SecurityCenter securityCenter = securityCenterBusiness
						.getSecurityCenterByUserId(user.getId());
				model.addAttribute("securityCenter", securityCenter);
			} else {
				vipTag = 0;
				SecurityCenter securityCenter = securityCenterBusiness
						.getSecurityCenterByUserId(user.getId());
				// 判断用户是否过期。 如果SecurityCenter 表没记录说明 尚未开通
				if (securityCenter == null) {
					isExpire = 0;
				} else {
					// 判断用户是否过期。 如果SecurityCenter 表有记录说明过期
					isExpire = 1;
				}
			}
			model.addAttribute("isExpire", isExpire);
			model.addAttribute("vipTag", vipTag);
			UserDetail userDetail = userDetailBusiness
					.findUserDetailByMoblieNumber(user.getMobileNumber());
			if (userDetail.getPhoto() != null) {
				session.setAttribute("PHOTO_PATH", userDetail.getUserid()
						+ "/getPhoto");
			}

			user = userBusiness.findUserByMoblieNumber(user.getMobileNumber());

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", user.getId());
			map.put("category", 1); // 注册领10纳币活动
			List<LpRecord> lplist = recordBusiness.queryLpRecord(map);
			if (lplist.size() > 0) {
				model.addAttribute("activeReward", 1);
			} else {
				model.addAttribute("activeReward", 0);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		SystemConfig systemConfig = systemConfigBusiness.getSystemConfig("104");
		model.addAttribute("month_cost", systemConfig.getParamValue());
		return ".accountManager";
	}

	public String toAccountSuccess(Model model, HttpServletRequest request,
			HttpSession session) {
		User user = SpringSecurityUtil.getCurrentUser();
		if (user != null) {
			String money = request.getParameter("money");
			UserDetail userDetail = userDetailBusiness
					.findUserDetailByMoblieNumber(user.getMobileNumber());
			if (userDetail.getPhoto() != null) {
				session.setAttribute("PHOTO_PATH", userDetail.getUserid()
						+ "/getPhoto");
			}
			model.addAttribute("money", money);
			model.addAttribute("mobilenumber", user.getMobileNumber());
		}

		return ".accountSuccess";
	}
	
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String toNewAccountSuccess(Model model, HttpServletRequest request,
			HttpSession session) {
		User user = SpringSecurityUtil.getCurrentUser();
		if (user != null) {
			String money = request.getParameter("money");
			UserDetail userDetail = userDetailBusiness
					.findUserDetailByMoblieNumber(user.getMobileNumber());
			if (userDetail.getPhoto() != null) {
				session.setAttribute("PHOTO_PATH", userDetail.getUserid()
						+ "/getPhoto");
			}
			model.addAttribute("money", money);
			model.addAttribute("mobilenumber", user.getMobileNumber());
		}

		return ".accountSuccess";
	}

	@RequestMapping(value = "/recharge/list", method = RequestMethod.POST)
	public String toRechargeList(PaginationMore page, Model model,
			HttpServletRequest request) {
		try {
			Map<String, Object> condition = setMapCondition(request);
			User user = SpringSecurityUtil.getCurrentUser();
			condition.put("userid", user.getId());
			List<RechargeRecord> list = rechargeRecordBusiness
					.getRechargeRecords(condition, page);
			model.addAttribute("rechargeRecords", list);
			model.addAttribute("page", page);
		} catch (BusinessException e) {
			logger.error(e.getMessage());
		}
		return "rechargeList";
	}

	/**
	 * 收支明细
	 * 
	 * @param page
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/incomeAndPay/list", method = RequestMethod.POST)
	public String toIncmoeAndPayList(PaginationMore page, Model model,
			HttpServletRequest request) {
		Map<String, Object> condition = setMapCondition(request);
		User user = SpringSecurityUtil.getCurrentUser();
		condition.put("userid", user.getId());
		List<TransactionDetails> list = transactionDetailsBusiness
				.queryAllTransactionDetails(condition, page);
		model.addAttribute("transactionDetails", list);
		model.addAttribute("page", page);
		return "incomeAndPay";
	}

	/**
	 * 提现明细
	 * 
	 * @param page
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/withdraw/list", method = RequestMethod.POST)
	public String toWithdrawList(PaginationMore page, Model model,
			HttpServletRequest request) {
		Map<String, Object> condition = setMapCondition(request);
		User user = SpringSecurityUtil.getCurrentUser();
		condition.put("userid", user.getId());
		List<TransactionDetails> list = transactionDetailsBusiness
				.getWithdrawalRecords(condition, page);
		model.addAttribute("withdraw", list);
		model.addAttribute("page", page);
		return "withdrawList";
	}

	/**
	 * 目标查询
	 * 
	 * @param page
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/target/list", method = RequestMethod.POST)
	public String tagetQuery(PaginationMore page, Model model,
			HttpServletRequest request) {
		Map<String, Object> condition = setMapCondition(request);

		User user = SpringSecurityUtil.getCurrentUser();

		if (condition.get("startTime") != null) {
			String startTime = condition.get("startTime").toString();
			condition.put("startTime", startTime.concat(" 00:00:00"));
		}

		if (condition.get("endTime") != null) {
			String endTime = condition.get("endTime").toString();
			condition.put("endTime", endTime.concat(" 23:59:59"));
		}

		condition.put("userid", user.getId());
		List<TransactionDetails> list = transactionDetailsBusiness
				.queryAllTransactionDetails(condition, page);
		model.addAttribute("target", list);
		model.addAttribute("page", page);
		model.addAttribute("condition", condition);
		return "targetQuery";
	}

	
	public String toRecharge(Model model) {/*
											 * try { List<BankCard> bankCards =
											 * bankCardBusiness
											 * .getAllByUserId(SpringSecurityUtil
											 * .getCurrentUser().getId()); for
											 * (BankCard bc : bankCards) {
											 * bc.setCardNumber
											 * (bc.getCardNumber().substring(
											 * bc.getCardNumber().length() -
											 * 4)); }
											 * model.addAttribute("bankCards",
											 * bankCards); } catch (Exception e)
											 * { logger.error(e.getMessage()); }
											 */
		return ".recharge";
	}

	@RequestMapping(value = "/recharge", method = RequestMethod.GET)
	public String toNewRecharge(Model model) {
		model.addAttribute("mobileNumber", SpringSecurityUtil.getCurrentUser().getMobileNumber());
		model.addAttribute("item", 5);
		return ".newRecharge";
	}

	@RequestMapping(value = "/recharge", method = RequestMethod.POST)
	public String doRecharge( String payTo, Model model) {
		List<AmountOption> amountOptions = amountOptionBusiness
				.getAmountOptions();
		try {
			logger.info("{} 开始充值了....", SpringSecurityUtil.getCurrentUser()
					.getMobileNumber());
			/*
			 * BankCard bankCard = bankCardBusiness.getBankCardById(bankCardId);
			 * if (bankCard != null) {
			 * bankCard.setCardNumber(bankCard.getCardNumber().substring(
			 * bankCard.getCardNumber().length() - 4));
			 * model.addAttribute("bankCard", bankCard); }
			 */
			if (payTo.equals("0")) {
				model.addAttribute("mobileNumber", SpringSecurityUtil
						.getCurrentUser().getMobileNumber());
			}
			model.addAttribute("amountOptions", amountOptions);
			logger.info("{} 跳转充值....", SpringSecurityUtil.getCurrentUser()
					.getMobileNumber());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return ".recharge/submit";
	}

	@RequestMapping(value = "/recharge/done", method = RequestMethod.POST)
	public String recharge(String mobileNumber, BigDecimal amount,
			String bankCardId, String bank_type, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			logger.info("用户:{}开始充值:{}", mobileNumber, amount);
			// 充值成功后跳转的地址
			String returnUrl = "http://www.wowpower.cn/user/account/manager";
			String url = userWalletBusiness.recharge(amount, mobileNumber,
					bank_type, request.getRemoteAddr(), bankCardId, request,
					response, returnUrl);
			logger.info("跳转充值地址:{}", url);
			return "redirect:" + url;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "redirect:/user/account/manager";
	}

	@ResponseBody
	@RequestMapping(value = "/recharge/doneByChinaBank", method = RequestMethod.POST)
	public Map<String, Object> rechargeByChinaBank(String mobileNumber,
			BigDecimal amount, String bankCardId, String bank_type,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			logger.info("用户:{}开始充值:{}", mobileNumber, amount);
			// 充值成功后跳转的地址
			String returnUrl = "http://www.wowpower.cn/user/account/manager";
			String url = userWalletBusiness.recharge(amount, mobileNumber,
					bank_type, request.getRemoteAddr(), bankCardId, request,
					response, returnUrl);
			logger.info("跳转充值地址:{}", url);
			result.put("data", url);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/exchange", method = RequestMethod.GET)
	public String toExchange() {
		return ".exchange";
	}

	/**
	public String toWithdrawal(Model model) {
		try {
			User user = SpringSecurityUtil.getCurrentUser();
			List<BankCard> bankCards = bankCardBusiness.getAllByUserId(user
					.getId());
			model.addAttribute("userWallet",
					userWalletBusiness.getAmountByUserId(user.getId()));
			for (BankCard bc : bankCards) {
				bc.setCardNumber(bc.getCardNumber().substring(
						bc.getCardNumber().length() - 4));
			}
			model.addAttribute("bankCards", bankCards);

			// 获取提现费率
			SystemConfig sysConfig = systemConfigBusiness
					.getSystemConfig("103");
			BigDecimal rate = BigDecimal.valueOf(Double.valueOf(sysConfig
					.getParamValue()));
			// 获取余额信息
			UserWallet userWallet = userWalletBusiness
					.getUserWalletByUserId(SpringSecurityUtil.getCurrentUser()
							.getId());
			BigDecimal balance = userWallet.getAmount();
			// 扣除费率后最多可以提取的金额
			BigDecimal maxAmount = DecimalUtil.subtract(balance,
					DecimalUtil.multiply(balance, rate));

			// BEGIN 当天充值，当天不能取出
			// BigDecimal todayRecharge = rechargeRecordBusiness
			// .countTodayRechargeAmountByUserid(userWallet.getUserId());
			//
			// maxAmount = DecimalUtil.subtract(balance, todayRecharge);
			// END

			model.addAttribute("maxAmount",
					maxAmount.setScale(2, RoundingMode.DOWN));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ".withdrawal";
	}**/
	
	@RequestMapping(value = "/withdrawal", method = RequestMethod.GET)
	public String toNewWithdrawal(Model model) {
		try {
			User user = SpringSecurityUtil.getCurrentUser();
			List<BankCard> bankCards = bankCardBusiness.getAllByUserId(user
					.getId());
			model.addAttribute("userWallet",
					userWalletBusiness.getAmountByUserId(user.getId()));
			model.addAttribute("name",
					user.getName());
			for (BankCard bc : bankCards) {
				bc.setCardNumber(bc.getCardNumber().substring(
						bc.getCardNumber().length() - 4));
			}
			model.addAttribute("bankCards", bankCards);

			// 获取提现费率
			SystemConfig sysConfig = systemConfigBusiness
					.getSystemConfig("103");
			BigDecimal rate = BigDecimal.valueOf(Double.valueOf(sysConfig
					.getParamValue()));
			// 获取余额信息
			UserWallet userWallet = userWalletBusiness
					.getUserWalletByUserId(SpringSecurityUtil.getCurrentUser()
							.getId());
			BigDecimal balance = userWallet.getAmount();
			// 扣除费率后最多可以提取的金额
			BigDecimal maxAmount = DecimalUtil.subtract(balance,
					DecimalUtil.multiply(balance, rate));

			// BEGIN 当天充值，当天不能取出
			// BigDecimal todayRecharge = rechargeRecordBusiness
			// .countTodayRechargeAmountByUserid(userWallet.getUserId());
			//
			// maxAmount = DecimalUtil.subtract(balance, todayRecharge);
			// END
     
			model.addAttribute("maxAmount",
					maxAmount.setScale(2, RoundingMode.DOWN));
			model.addAttribute("item", 6);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ".newWithdrawal";
	}

	@RequestMapping(value = "/withdrawal", method = RequestMethod.POST)
	@ResponseBody
	public Object doWithdrawal(BigDecimal amount) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 返回结果
			resultMap.put("resultCode", "0");
			resultMap.put("resultMsg", "提现成功");
             // 获取提现费率
			SystemConfig sysConfig = systemConfigBusiness
					.getSystemConfig("103");
			BigDecimal rate = BigDecimal.valueOf(Double.valueOf(sysConfig
					.getParamValue()));
			// 获取余额信息
			UserWallet userWallet = userWalletBusiness
					.getUserWalletByUserId(SpringSecurityUtil.getCurrentUser()
							.getId());
			BigDecimal balance = userWallet.getAmount();

			// 扣除费率后最多可以提取的金额
			BigDecimal maxAmount = DecimalUtil.subtract(balance,
					DecimalUtil.multiply(balance, rate));

			// BEGIN 当天充值，当天不能取出
			// BigDecimal todayRecharge = rechargeRecordBusiness
			// .countTodayRechargeAmountByUserid(userWallet.getUserId());
			//
			// maxAmount = DecimalUtil.subtract(balance, todayRecharge);

			// END

			if (maxAmount.compareTo(amount) == -1) {
				// 如果macAmount < 0 显示给用户0
				BigDecimal zero = new BigDecimal(0);
				if (zero.compareTo(maxAmount) == 1) {
					maxAmount = new BigDecimal("0");
				}
				resultMap.put("resultCode", "1");
				resultMap.put("resultMsg", "余额不足");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put("resultCode", "1");
			resultMap.put("resultMsg", "未知错误");
		}
		return resultMap;
	}
	@RequestMapping(value = "/cancelAutoRenew", method = RequestMethod.POST)
	@ResponseBody
	public Object cancelAutoRenew() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 返回结果
			securityCenterBusiness.cancelAutoRenewByUserId(SpringSecurityUtil.getCurrentUser().getId());
			resultMap.put("resultCode", "0");
			resultMap.put("resultMsg", "操作成功");
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put("resultCode", "1");
			resultMap.put("resultMsg", "未知错误");
		}
		return resultMap;
	}
	
	/**
	 * 验证当前用户密码是否匹配
	 * 
	 *            手机号码
	 * @return
	 */
	@RequestMapping(value = "/{password}/password", method = RequestMethod.POST)
	@ResponseBody
	public Object checkPassword(@PathVariable String password) {
		try {
			User user = SpringSecurityUtil.getCurrentUser();
			password = URLDecoder.decode(password);
			SecurityCenter securityCenter = securityCenterBusiness
					.getSecurityCenterByUserId(user.getId());
			if (securityCenter != null
					&& StringUtils.isNotEmpty(securityCenter.getPassword())
					&& securityCenter.getIsOpen() == 0) {
				if (md5PasswordEncoder.encodePassword(password, null).equals(
						securityCenter.getPassword())) {
					return 0;
				} else {
					return -1;
				}
			}
			if (md5PasswordEncoder.encodePassword(password, null).equals(
					user.getPassword())) {
				return 0;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return -1;
	}

	@RequestMapping(value = "/withdrawal/done", method = RequestMethod.POST)
	public void withdrawal(BigDecimal amount, String bankCardId,
			HttpServletRequest request,HttpServletResponse response) throws IOException {
		boolean flag = false;
		try {
			if (amount.intValueExact() >= 100
					&& amount.intValueExact() <= 50000) {
				String password = request.getParameter("password");
				userWalletBusiness.withdrawal(amount, bankCardId,
						request.getRemoteAddr(),password );
				User user = SpringSecurityUtil.getCurrentUser();
				boolean b = securityCenterBusiness.isOpen(user.getId());
				if (b) {
					String mobileNumber = messageRuleBusinessImpl
							.getSendMobileNumber(user);
					if (messageRuleBusinessImpl.isSend(user)) {
						smsService.sendSms(mobileNumber, "", "MB-2014022554",
								com.venada.efinance.util.DateUtils
										.currentTime("yyyy-MM-dd HH:mm"),
								amount.toString());
					}
				}
			}
		} catch (Exception e) {
            e.printStackTrace();
			logger.error(e.getMessage());
			flag = true;
		}
		
		if(flag == false){
		response.sendRedirect(request.getContextPath()+"/user/1/dealDetail");
		}else{
		response.sendRedirect(request.getContextPath()+"/user/account/withdrawal");
		}
	}

	@RequestMapping(value = "/checkWithDrawal", method = RequestMethod.GET)
	public @ResponseBody
	Object checkWithdrawal() {
		boolean flag;
		try {
			User user = SpringSecurityUtil.getCurrentUser();
			BigDecimal allGameAmount = userBusiness.getAllGameAmount(user
					.getMobileNumber());
			BigDecimal allRechargeAmount = userBusiness
					.getAllRechargeAmount(user.getId());
			BigDecimal allInviteAmount = userBusiness.getAllInviteAmount(user
					.getId());
			BigDecimal allVipAmount = userBusiness
					.getAllVipAmount(user.getId());

			BigDecimal sub = allGameAmount.add(allInviteAmount).add(
					allVipAmount);
			BigDecimal mo = allRechargeAmount.add(allInviteAmount);

			if (user.getStatus() == 4) {
				flag = true;
				return flag;
			}

			if (mo.compareTo(new BigDecimal(0)) == 0) {
				flag = true;
			} else {
				BigDecimal result = sub.divide(mo, RoundingMode.HALF_DOWN);
				if (result.compareTo(new BigDecimal(1)) >= 0) {
					flag = true;
				} else {
					flag = false;
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			flag = false;
		}
		return flag;
	}

}