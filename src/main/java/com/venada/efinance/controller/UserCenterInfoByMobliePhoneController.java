package com.venada.efinance.controller;

import com.venada.efinance.business.*;
import com.venada.efinance.common.controller.BaseController;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.enumtype.LogTypeEnum;
import com.venada.efinance.pojo.*;
import com.venada.efinance.util.LevelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
/**
 * 提供手机端用户中心信息
 * @author hepei
 *
 */
@Controller
public class UserCenterInfoByMobliePhoneController extends BaseController{

	@Autowired
	private UserBusiness userBusiness;
	@Autowired
	private UserWalletBusiness userWalletBusinessImpl;
	@Autowired
	private UserSignInBusiness userSignInBusinessImpl;
	@Autowired
	private SystemConfigBusiness systemConfigBusinessImpl ;
	@Autowired
	private UserObtainCreditsBusiness userObtainCreditsBusiness;
    @Autowired
    private InviteBenefitBusiness inviteBenefitBusinessImp;
    @Autowired
	private MessageRuleBusiness messageRuleBusinessImpl;
    @Autowired
    private UserConvertCreditsBusiness userConvertCreditsBusinessImp;
    @Autowired
    private OrderBusiness orderBusinessImp;
	@Autowired
	private SecurityCenterBusiness securityCenterBusiness;
	@Autowired
	private RechargeRecordBusiness rechargeRecordBusiness;
	@Autowired
	private UserWalletBusiness userWalletBusiness;
	@Autowired
	private DealDetailBusiness dealDetailBusiness;
	@Autowired
	private OperationLogBusiness opeartionLogBusiness;
	@Autowired
	private GameBusiness gameBusinessImpl;
	
    
    private static final Logger logger = LoggerFactory.getLogger(UserCenterInfoByMobliePhoneController.class);
	
	/**
	 * 手机端用户推荐获利信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mobilePhone/queryInviteBenefitByPhone")
	@ResponseBody
	public Object queryInviteBenefitByPhone(HttpServletRequest request) {
		String mobileNumber = request.getParameter("mobileNumber");
		String password = request.getParameter("password");
		int page = 1;
		int limitIndex = 0;
		int count = 0;
		Map<String, Object> result = new HashMap<String, Object>();
		if (!"".equals(request.getParameter("count"))
				&& request.getParameter("count") != null) {
			count = Integer.parseInt(request.getParameter("count"));
		} else {
			count = 10;
		}
		try {
			page = Integer.parseInt(request.getParameter("page"));
			limitIndex = (page - 1) * count;
		} catch (Exception e) {
			logger.error("页数转换为数字失败：" + request.getParameter("page"));
			return result;
		}
		
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("limitCount", count);
		condition.put("limitIndex", limitIndex);
		User user = userBusiness.findUserByMoblieNumber(mobileNumber);
		if (user == null) {
			result.put("resCode",102);
			result.put("resMsg", "用户不存在");
			return result;
		} else {
			if (!user.getPassword().equals(password)) {
				result.put("resCode", 103);
				result.put("resMsg", "密码错误");
				return result;
			} else {
				condition.put("inviteUserId", user.getId());
				List<InviteBenefit> benefitList=inviteBenefitBusinessImp.queryInviteBenefitByPhone(condition);
				return benefitList;
			}
		}
	}
	
	/**
	 * 手机端用户推荐用户
	 * @param request
	 * @return
	 * （vipTag=1 是vip会员,vipTag=0 非vip会员）
	 */
	@RequestMapping(value = "/mobilePhone/queryInviteUserByPhone")
	@ResponseBody
	public Object queryInviteUserByPhone(HttpServletRequest request) {
		String mobileNumber = request.getParameter("mobileNumber");
		String password = request.getParameter("password");
		int page = 1;
		int limitIndex = 0;
		int count = 0;
		Map<String, Object> result = new HashMap<String, Object>();
		if (!"".equals(request.getParameter("count"))
				&& request.getParameter("count") != null) {
			count = Integer.parseInt(request.getParameter("count"));
		} else {
			count = 10;
		}
		try {
			page = Integer.parseInt(request.getParameter("page"));
			limitIndex = (page - 1) * count;
		} catch (Exception e) {
			logger.error("页数转换为数字失败：" + request.getParameter("page"));
			return result;
		}
		
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("limitCount", count);
		condition.put("limitIndex", limitIndex);
		User user = userBusiness.findUserByMoblieNumber(mobileNumber);
		if (user == null) {
			result.put("resCode",102);
			result.put("resMsg", "用户不存在");
			return result;
		} else {
			if (!user.getPassword().equals(password)) {
				result.put("resCode", 103);
				result.put("resMsg", "密码错误");
				return result;
			} else {
				condition.put("inviteCodeFromOther", user.getInviteCodeSelf());
				List<User> userlist=userBusiness.listUserByInviteCodeFromOtherMobilePhone(condition);
				List<User> ulist=new ArrayList<User>();
				for(User u:userlist){
					  u.setLevel((int)  Math.ceil(LevelUtils.getLevel(u.getLevel()/100.0)));
					  if(securityCenterBusiness.isOpen(u.getId())){
						  u.setVipTag("1");
					  }else{
						  u.setVipTag("0");
					  }
					  ulist.add(u);
				}
				return ulist;
			}
		}
	}
	
	
	/**
	 * 手机端用户获取用户的推荐code
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mobilePhone/queryInviteCodeByPhone")
	@ResponseBody
	public Object queryInviteCodeByPhone(HttpServletRequest request) {
		String mobileNumber = request.getParameter("mobileNumber");
		String password = request.getParameter("password");
	
		Map<String, Object> result = new HashMap<String, Object>();
		User user = userBusiness.findUserByMoblieNumber(mobileNumber);
		if (user == null) {
			result.put("resCode",102);
			result.put("resMsg", "用户不存在");
			return result;
		} else {
			if (!user.getPassword().equals(password)) {
				result.put("resCode", 103);
				result.put("resMsg", "密码错误");
				return result;
			} else {
				result.put("inviteCode", user.getInviteCodeSelf());
				return result;
			}
		}
	}
	
	/**
	 * 手机端获取用户的积分获取记录
	 * @param request
	 * @return
	 * actionType=0 登录
	 * actionType=1 领取任务
	 * actionType=2 活动送积分
	 */
	@RequestMapping(value = "/mobilePhone/queryAllObtainCreditsByMobilePhone")
	@ResponseBody
	public Object queryAllObtainCreditsByMobilePhone(HttpServletRequest request) {
		String mobileNumber = request.getParameter("mobileNumber");
		String password = request.getParameter("password");
		int page = 1;
		int limitIndex = 0;
		int count = 0;
		Map<String, Object> result = new HashMap<String, Object>();
		if (!"".equals(request.getParameter("count"))
				&& request.getParameter("count") != null) {
			count = Integer.parseInt(request.getParameter("count"));
		} else {
			count = 10;
		}
		try {
			page = Integer.parseInt(request.getParameter("page"));
			limitIndex = (page - 1) * count;
		} catch (Exception e) {
			logger.error("页数转换为数字失败：" + request.getParameter("page"));
			return result;
		}
		
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("limitCount", count);
		condition.put("limitIndex", limitIndex);
		User user = userBusiness.findUserByMoblieNumber(mobileNumber);
		if (user == null) {
			result.put("resCode",102);
			result.put("resMsg", "用户不存在");
			return result;
		} else {
			if (!user.getPassword().equals(password)) {
				result.put("resCode", 103);
				result.put("resMsg", "密码错误");
				return result;
			} else {
				condition.put("userid", user.getId());
				List<ObtainCredits> obtainCreditslist=userObtainCreditsBusiness.queryAllObtainCreditsByMobileNumber(condition);
				return obtainCreditslist;
			}
		}
	}
	
	/**
	 * 手机端获取用户的积分兑换记录
	 * @param request
	 * @return 
	 * tradeStatus=0 兑换成功 
	 * tradeStatus=1 邮寄中 
	 * tradeStatus=2 发货成功
	 * tradeStatus=3 退货成功
	 */
	@RequestMapping(value = "/mobilePhone/queryConvertCreditsByMobilePhone")
	@ResponseBody
	public Object queryConvertCreditsByMobilePhone(HttpServletRequest request) {
		String mobileNumber = request.getParameter("mobileNumber");
		String password = request.getParameter("password");
		int page = 1;
		int limitIndex = 0;
		int count = 0;
		Map<String, Object> result = new HashMap<String, Object>();
		if (!"".equals(request.getParameter("count"))
				&& request.getParameter("count") != null) {
			count = Integer.parseInt(request.getParameter("count"));
		} else {
			count = 10;
		}
		try {
			page = Integer.parseInt(request.getParameter("page"));
			limitIndex = (page - 1) * count;
		} catch (Exception e) {
			logger.error("页数转换为数字失败：" + request.getParameter("page"));
			return result;
		}
		
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("limitCount", count);
		condition.put("limitIndex", limitIndex);
		User user = userBusiness.findUserByMoblieNumber(mobileNumber);
		if (user == null) {
			result.put("resCode",102);
			result.put("resMsg", "用户不存在");
			return result;
		} else {
			if (!user.getPassword().equals(password)) {
				result.put("resCode", 103);
				result.put("resMsg", "密码错误");
				return result;
			} else {
				condition.put("userid", user.getId());
				List<ConvertCredits> obtainCreditslist=userConvertCreditsBusinessImp.queryConvertCreditsByMobilePhone(condition);
//				ObjectMapper mapper=new ObjectMapper();
//				try {
//					mapper.writeValueAsString(obtainCreditslist);
//				} catch (JsonGenerationException e) {
//					e.printStackTrace();
//				} catch (JsonMappingException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
				return obtainCreditslist;
			}
		}
	}
	
	
	/**
	 * 手机端获取用户的订单记录
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mobilePhone/queryorderByMobilePhone")
	@ResponseBody
	public Object queryorderByMobilePhone(HttpServletRequest request) {
		String mobileNumber = request.getParameter("mobileNumber");
		String password = request.getParameter("password");
		int page = 1;
		int limitIndex = 0;
		int count = 0;
		Map<String, Object> result = new HashMap<String, Object>();
		if (!"".equals(request.getParameter("count"))
				&& request.getParameter("count") != null) {
			count = Integer.parseInt(request.getParameter("count"));
		} else {
			count = 10;
		}
		try {
			page = Integer.parseInt(request.getParameter("page"));
			limitIndex = (page - 1) * count;
		} catch (Exception e) {
			logger.error("页数转换为数字失败：" + request.getParameter("page"));
			return result;
		}
		
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("limitCount", count);
		condition.put("limitIndex", limitIndex);
		User user = userBusiness.findUserByMoblieNumber(mobileNumber);
		if (user == null) {
			result.put("resCode",102);
			result.put("resMsg", "用户不存在");
			return result;
		} else {
			if (!user.getPassword().equals(password)) {
				result.put("resCode", 103);
				result.put("resMsg", "密码错误");
				return result;
			} else {
				condition.put("mobileNumber", user.getMobileNumber());
				List<Order> orderlist=orderBusinessImp.queryorderByMobilePhone(condition);
				return orderlist;
			}
		}
	}
	
	/**
	 * 获取用户中心的短信配置
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mobilePhone/getMessageRuleByMobilePhone")
	@ResponseBody
	public Object getMessageRule(HttpServletRequest request,Model model) {
		String mobileNumber = request.getParameter("mobileNumber");
		String password = request.getParameter("password");
		Map<String, Object> result = new HashMap<String, Object>();
		User user = userBusiness.findUserByMoblieNumber(mobileNumber);
		if (user == null) {
			result.put("resCode",102);
			result.put("resMsg", "用户不存在");
			return result;
		} else {
			if (!user.getPassword().equals(password)) {
				result.put("resCode", 103);
				result.put("resMsg", "密码错误");
				return result;
			} else {
				String startTime="0";
				String endTime="24";
				Integer switchTag=0;
				List<MessageRule> messageruleList=messageRuleBusinessImpl.findMessageRule(user.getId());
				if(messageruleList.isEmpty()){
					SecurityCenter securityCenter= securityCenterBusiness.getSecurityCenterByUserId(user.getId());
					if (securityCenter != null) {
						if (securityCenter.getMobile() != null
								&& !securityCenter.getMobile().equals("")) {
							mobileNumber = securityCenter.getMobile();
						}
					}
				}else{
					MessageRule messageRule=messageruleList.get(0);
					mobileNumber=messageRule.getMobileNumber();
					startTime=messageRule.getStartTime();
					endTime=messageRule.getEndTime();
					switchTag=messageRule.getSwitchTag();
				}
				result.put("mobileNumber", mobileNumber);
				result.put("startTime", startTime);
				result.put("endTime", endTime);
				result.put("switchTag", switchTag);
			}
		}
		return result;
	}
	
	/**
	 * 更新用户中心的短信配置
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mobilePhone/setMessageRuleByMobilePhone")
	@ResponseBody
	public Object setMessageRule(HttpServletRequest request,Model model) {
		String mobileNumber = request.getParameter("mobileNumber");
		String modifyMoblieNumber = request.getParameter("modifyMoblieNumber");
		String password = request.getParameter("password");
		Map<String, Object> result = new HashMap<String, Object>();
		User user = userBusiness.findUserByMoblieNumber(mobileNumber);
		if (user == null) {
			result.put("resCode",102);
			result.put("resMsg", "用户不存在");
			return result;
		} else {
			if (!user.getPassword().equals(password)) {
				result.put("resCode", 103);
				result.put("resMsg", "密码错误");
				return result;
			} else {
				String startTime = request.getParameter("startTime");
				String endTime = request.getParameter("endTime");
				String switchTag=request.getParameter("switchTag");
				logger.info("setMessageRuleByMobilePhone(startTime:"+startTime+" endTime:"+endTime+" switchTag:"+switchTag+")");
				MessageRule messageRule=new MessageRule();
				messageRule.setCreatetime(new Date());
				messageRule.setEndTime(endTime);
				if(!modifyMoblieNumber.equals("")&&null!=modifyMoblieNumber){
					messageRule.setMobileNumber(modifyMoblieNumber);
				}else{
					messageRule.setMobileNumber(mobileNumber);
				}
				messageRule.setStartTime(startTime);
				messageRule.setUserid(user.getId());
				messageRule.setId(UUID.randomUUID().toString());
				try {
					messageRule.setSwitchTag(Integer.parseInt(switchTag));
					List<MessageRule> messageRuleList=messageRuleBusinessImpl.findMessageRule(user.getId());
					if (messageRuleList.isEmpty()) {
						messageRuleBusinessImpl.addMessageRule(messageRule);
						OperationLog log = new OperationLog();
						log.setLogType(LogTypeEnum.MessageRule.getIndex());
						log.setDataOld("");
						log.setDataNew("新增短信接受配置:startTime:"+startTime+",endTime:"+endTime+",mobileNumber:"+mobileNumber+",switchTag:"+switchTag);
						log.setCreateBy(user.getId());
						log.setCreateTime(new Date());
						opeartionLogBusiness.addOperationLog(log);
						result.put("code", "0");
						result.put("msg", "新增成功");
					}else{
						OperationLog log = new OperationLog();
						messageRule=messageRuleList.get(0);
						log.setDataOld("startTime:"+messageRule.getStartTime()+",endTime:"+messageRule.getEndTime()+",mobileNumber:"+messageRule.getMobileNumber()+",switchTag:"+messageRule.getSwitchTag());
						messageRule.setEndTime(endTime);
						if(!modifyMoblieNumber.equals("")&&null!=modifyMoblieNumber){
							messageRule.setMobileNumber(modifyMoblieNumber);
						}else{
							messageRule.setMobileNumber(mobileNumber);
						}
						messageRule.setStartTime(startTime);
						messageRule.setCreatetime(new Date());
						messageRule.setSwitchTag(Integer.parseInt(switchTag));
						messageRuleBusinessImpl.updateMessageRuleById(messageRule);
						
						log.setLogType(LogTypeEnum.MessageRule.getIndex());
						log.setCreateBy(user.getId());
						log.setCreateTime(new Date());
						log.setDataNew("修改短信接受配置:startTime:"+messageRule.getStartTime()+",endTime:"+messageRule.getEndTime()+",mobileNumber:"+messageRule.getMobileNumber()+",switchTag:"+messageRule.getSwitchTag());
						opeartionLogBusiness.addOperationLog(log);
						result.put("code", "0");
						result.put("msg", "更新成功");
					}
				} catch (BusinessException e) {
					result.put("code", "1");
					result.put("msg", "操作失败");
					logger.error(e.getMessage());
				}
			}
		}
		return result;
	}

	/**
	 * 获取用户中心的收入情况
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mobilePhone/getUserInfoByMobilePhone")
	@ResponseBody
	public Object getUserInfoByMobilePhone(HttpServletRequest request,Model model) {
		String mobileNumber = request.getParameter("mobileNumber");
		String password = request.getParameter("password");
		Map<String, Object> result = new HashMap<String, Object>();
		User user = userBusiness.findUserByMoblieNumber(mobileNumber);
		if (user == null) {
			result.put("resCode",102);
			result.put("resMsg", "用户不存在");
			return result;
		} else {
			if (!user.getPassword().equals(password)) {
				result.put("resCode", 103);
				result.put("resMsg", "密码错误");
				return result;
			} else {
				//充值金额
				result.put("rechargeAmount", rechargeRecordBusiness.getRechargeAmountByUserId(user.getId()));
				//账户余额
				result.put("walletAmount", userWalletBusiness.getAmountByUserId(user.getId()).getAmount());
				//账户收入
				result.put("dealDetailAmount", dealDetailBusiness.getDealDetailAmountByUserId(user.getId()));
			}
		}
		return result;
	}
	
	/**
	 * 获取用户中心的收入情况
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mobilePhone/getUserInfoByMobilePhoneByIOS")
	@ResponseBody
	public Object getUserInfoByMobilePhoneByISO(HttpServletRequest request,Model model) {
		String mobileNumber = request.getParameter("mobileNumber");
		String password = request.getParameter("password");
		Map<String, Object> result = new HashMap<String, Object>();
		User user = userBusiness.findUserByMoblieNumber(mobileNumber);
		if (user == null) {
			result.put("resCode",102);
			result.put("resMsg", "用户不存在");
			return result;
		} else {
			if (!user.getPassword().equals(password)) {
				result.put("resCode", 103);
				result.put("resMsg", "密码错误");
				return result;
			} else {
				//用户积分
				result.put("credits", user.getCredits());
				//充值金额
				result.put("rechargeAmount", rechargeRecordBusiness.getRechargeAmountByUserId(user.getId()));
				//账户余额
				result.put("walletAmount", userWalletBusiness.getAmountByUserId(user.getId()).getAmount());
				//账户收入
				result.put("dealDetailAmount", dealDetailBusiness.getDealDetailAmountByUserId(user.getId()));
				//预计收入
				Map<String,Object> condition =new HashMap<String, Object>();
				condition.put("userid", user.getMobileNumber());
				result.put("gameBenefit",gameBusinessImpl.getUserGameBenefitInfo(condition).getReward());
				//用户当前投资金额（押金）
				result.put("gameDeposit",gameBusinessImpl.getUserGameBenefitInfo(condition).getDeposit());
				//查询推荐奖励
				condition.clear();
				condition.put("inviteUserId", user.getId());
				result.put("total",inviteBenefitBusinessImp.getInviteBenefitTotal(condition));
				//查询签到奖励
				condition.clear();
				condition.put("mobilePhone", user.getMobileNumber());
				result.put("signTotal",userSignInBusinessImpl.sumSignBenefitByMobileNumber(condition));
			}
		}
		return result;
	}
	
	/**
	 * 手机客户端查看充值记录
	 * @param page
	 * @param model
	 * @param request
	 * @return
	 * status(0:充值成功 2:正在处理 其他:充值失败)
	 */
	@RequestMapping(value = "/mobilePhone/queryRechargeListByMobilePhone")
	@ResponseBody
	public Object queryRechargeListByMobilePhone(HttpServletRequest request) {
		try {
			String mobileNumber = request.getParameter("mobileNumber");
			String password = request.getParameter("password");
			int page = 1;
			int limitIndex = 0;
			int count = 0;
			Map<String, Object> result = new HashMap<String, Object>();
			if (!"".equals(request.getParameter("count"))
					&& request.getParameter("count") != null) {
				count = Integer.parseInt(request.getParameter("count"));
			} else {
				count = 10;
			}
			try {
				page = Integer.parseInt(request.getParameter("page"));
				limitIndex = (page - 1) * count;
			} catch (Exception e) {
				logger.error("页数转换为数字失败：" + request.getParameter("page"));
				return result;
			}
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("limitCount", count);
			condition.put("limitIndex", limitIndex);
			User user = userBusiness.findUserByMoblieNumber(mobileNumber);
			if (user == null) {
				result.put("resCode",102);
				result.put("resMsg", "用户不存在");
				return result;
			} else {
				if (!user.getPassword().equals(password)) {
					result.put("resCode", 103);
					result.put("resMsg", "密码错误");
					return result;
				} else {
					condition.put("userid", user.getId());
					List<RechargeRecord> list = rechargeRecordBusiness.queryRechargeRecordsByMobilePhone(condition);
					return list;
				}
			}
		} catch (BusinessException e) {
			logger.error(e.getMessage());
		}
		return "rechargeList";
	}
	
}
