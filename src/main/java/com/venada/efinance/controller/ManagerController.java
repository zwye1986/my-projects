package com.venada.efinance.controller;

import com.venada.efinance.business.*;
import com.venada.efinance.common.controller.BaseController;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.*;
import com.venada.efinance.security.SpringSecurityUtil;
import com.venada.efinance.util.DateUtils;
import com.venada.efinance.util.RequestUtils;
import jxl.Workbook;
import jxl.write.*;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/manager")
public class ManagerController extends BaseController {
	private static final Logger logger = LoggerFactory
			.getLogger(ManagerController.class);
	@Autowired
	private UserBusiness userBusiness;
	@Autowired
	private ResourceBusiness resourceBusinessImpl;
	@Autowired
	private AmountOptionBusiness amountOptionBusiness;
	@Autowired
	private SystemConfigBusiness systemConfigBusiness;
	@Autowired
	private WithdrawalRecordBussiness withdrawalRecordBussiness;
	@Autowired
	private ValidateCodeBusiness validateCodeBusiness;
	@Autowired
	private CommoditiesConfigBussiness commoditiesConfigBussiness;
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	@Autowired
	private ProjectInvestBusiness projectInvestBusiness;

	@RequestMapping(value = { "", "/", "/index", "/index.html", "/index.jsp" })
	public String toManager() {
		return "/manager/main";
	}

	@RequestMapping(value = "/top")
	public String toTop() {
		return "/manager/top";
	}

	@RequestMapping(value = "/center")
	public String toCenter() {
		return "/manager/center";
	}

	@RequestMapping(value = "/welcome")
	public String welcome() {
		return "/manager/welcome";
	}

	@RequestMapping(value = "/left")
	public String toLeft(Model model) {
		User user = SpringSecurityUtil.getCurrentUser();
		List<Resource> reslist = new ArrayList<Resource>();
		for (Role role : user.getUserRoles()) {
			reslist.addAll(resourceBusinessImpl.getResoucesByRoleId(role
					.getId()));
		}
		// 去掉用户绑定了多个角色导致的重复菜单
		for (int i = 0; i < reslist.size() - 1; i++) {
			for (int j = reslist.size() - 1; j > i; j--) {
				if (reslist.get(j).getValue().equals(reslist.get(i).getValue())) {
					reslist.remove(j);
				}
			}
		}
		// 过滤/manager这个url
		for (int i = 0; i < reslist.size(); i++) {
			if (reslist.get(i).getValue().equals("/manager")) {
				reslist.remove(i);
			}
		}
		model.addAttribute("reslist", reslist);
		return "/manager/left";
	}

	@RequestMapping(value = "/right")
	public String toRight() {
		return "/manager/right";
	}

	@RequestMapping(value = "/charts")
	public String toCharts() {
		return "/manager/charts";
	}

	@RequestMapping(value = "/buttom")
	public String toButtom() {
		return "/manager/buttom";
	}

	// 充值金额配置列表界面
	@RequestMapping(value = "/amount/option")
	public String toAmountOption(Model model) {
		try {
			List<AmountOption> options = amountOptionBusiness
					.getAmountOptions();
			model.addAttribute("options", options);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "/manager/amount_option";
	}

	// 充值金额配置修改或新增页面
	@RequestMapping(value = "/amount/option/save", method = RequestMethod.GET)
	public String toAddAmountOption(HttpServletRequest request, Model model) {
		try {
			String id = RequestUtils.getStringParameter(request, "ids", "");
			if (StringUtils.isNotEmpty(id)) {
				model.addAttribute("amountOption",
						amountOptionBusiness.getAmountOptionById(id));
			}
		} catch (BusinessException e) {
			logger.error(e.getMessage());
		}
		return "/manager/amount_option_add";
	}

	// 保存充值金额配置
	@RequestMapping(value = "/amount/option/save", method = RequestMethod.POST)
	public String saveAmountOption(AmountOption amountOption) {
		try {
			amountOptionBusiness.saveAmountOption(amountOption);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "redirect:/manager/amount/option";
	}

	// 删除充值金额配置
	@RequestMapping(value = "/amount/option/delete", method = RequestMethod.POST)
	public String deleteAmountOption(HttpServletRequest request) {
		try {
			String[] ids = RequestUtils.getStringParameters(request, "ids");
			amountOptionBusiness.deleteAmountOption(ids);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "redirect:/manager/amount/option";
	}

	@RequestMapping(value = "/system/config")
	public String toSystemConfig(PaginationMore page, Model model,
			HttpServletRequest request) {
		try {
			page.setPageSize(20);
			List<SystemConfig> systemConfigs = systemConfigBusiness
					.getSystemConfigAll(page, setMapCondition(request));
			model.addAttribute("systemConfigs", systemConfigs);
			model.addAttribute("page", page);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "/manager/system_config";
	}

	@RequestMapping(value = "/system/config/save", method = RequestMethod.GET)
	public String toAddSystemConfig(HttpServletRequest request, Model model) {
		try {
			String id = RequestUtils.getStringParameter(request, "ids", "");
			if (StringUtils.isNotEmpty(id)) {
				model.addAttribute("systemConfig",
						systemConfigBusiness.getSystemConfig(id));
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "/manager/system_config_add";
	}

	@RequestMapping(value = "/system/config/save", method = RequestMethod.POST)
	public String addSystemConfig(SystemConfig sysConfig) {
		try {
			systemConfigBusiness.saveSystemConfig(sysConfig);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "redirect:/manager/system/config";
	}

	@RequestMapping(value = "/system/config/delete", method = RequestMethod.POST)
	public String deleteSystemConfig(HttpServletRequest request) {
		try {
			String[] ids = RequestUtils.getStringParameters(request, "ids");
			systemConfigBusiness.deleteSystemConfig(ids);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "redirect:/manager/system/config";
	}

	@RequestMapping(value = "/commodityConfigList")
	public String commodityConfigList(Model model) {
		List<CommoditiesConfig> commoditiesConfigs = commoditiesConfigBussiness
				.getCommoditiesConfig(null);
		model.addAttribute("commoditiesConfigs", commoditiesConfigs);
		return "/manager/commodityConfigList";
	}

	@RequestMapping(value = "/addCommoditiesConfig")
	public String addCommoditiesConfig(Model model) {
		return "/manager/addCommoditiesConfig";
	}

	@RequestMapping(value = "/dealAddItem")
	public String dealAddItem(CommoditiesConfig commoditiesConfig) {
		commoditiesConfigBussiness.addCommoditiesConfig(commoditiesConfig);
		return "redirect:/manager/commodityConfigList";
	}

	@RequestMapping(value = "/{id}/toUpdateItem")
	public String updateItem(Model model, @PathVariable int id) {
		CommoditiesConfig commoditiesConfig = commoditiesConfigBussiness
				.getCommoditiesConfigById(id);
		model.addAttribute("commoditiesConfig", commoditiesConfig);
		return "/manager/addCommoditiesConfig";
	}

	@RequestMapping(value = "/dealUpdateItem")
	public String dealUpdateItem(Model model,
			CommoditiesConfig commoditiesConfig) {
		commoditiesConfigBussiness.updateCommoditiesConfig(commoditiesConfig);
		return "redirect:/manager/commodityConfigList";
	}

	@RequestMapping(value = "/{id}/delItem")
	public String delItem(@PathVariable int id) {
		commoditiesConfigBussiness.deleteCommoditiesConfig(id);
		return "redirect:/manager/commodityConfigList";
	}

	@RequestMapping(value = "/queryValidateCode")
	public String queryValidateCode(Model model, HttpServletRequest request,
			PaginationMore page) {
		page.setPageSize(20);
		Map<String, Object> condition = setMapCondition(request);
		List<ValidateCode> validateCodes = validateCodeBusiness
				.queryValidateCodeByConditions(condition, page);
		model.addAttribute("validateCodes", validateCodes);
		model.addAttribute("page", page);
		return "/manager/showValidateCode";
	}

	@RequestMapping(value = "/resetPassword")
	@ResponseBody
	public Object resetPassword(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String userid = request.getParameter("userid");
		User user = userBusiness.findUserById(userid);
		if (user == null) {
			resMap.put("msg", "操作失败，用户不存在！");
		} else {
			if (user.getMobileNumber() == null
					|| user.getMobileNumber().length() < 11) {
				resMap.put("msg", "操作失败，用户手机号码不正确！");
			} else {
				user.setPassword(md5PasswordEncoder.encodePassword(user
						.getMobileNumber().substring(5, 11), null));
				userBusiness.updateUserPassword(userid, user.getPassword());
				resMap.put("msg", "操作成功！");
			}
		}
		return resMap;
	}

	@RequestMapping(value = "/delTips")
	public String delTips() {
		return "/manager/tips";
	}

	@RequestMapping(value = "/projectUser.html")
	public String projectUserList(PaginationMore page,
			HttpServletRequest request, Model model) {
		try {
			page.setPageSize(20);
			Map<String, Object> condition = setMapCondition(request);
			if (condition.get("dateTimeBegin") != null) {
				String dateTimeBegin = condition.get("dateTimeBegin")
						.toString();
				condition.put("dateTimeBegin",
						dateTimeBegin.concat(" 00:00:00"));
			}
			if (condition.get("dateTimeEnd") != null) {
				String dateTimeEnd = condition.get("dateTimeEnd").toString();
				condition.put("dateTimeEnd", dateTimeEnd.concat(" 23:59:59"));
			}
			List<ProjectUser> list = projectInvestBusiness.getSupportLog(
					condition, page);
			model.addAttribute("condition", condition);

			model.addAttribute("page", page);
			model.addAttribute("list", list);
		} catch (BusinessException e) {
			logger.info("orderList failure");
		}
		return "manager/projectUserList";
	}

	@RequestMapping(value = "/{type}/withDrawalList")
	public String withDrawalList(PaginationMore page,
			HttpServletRequest request, Model model, @PathVariable int type) {
		try {
			page.setPageSize(20);
			Map<String, Object> condition = setMapCondition(request);
			condition.put("type", type);
			if (condition.get("dateTimeBegin") != null) {
				String dateTimeBegin = condition.get("dateTimeBegin")
						.toString();
				condition.put("dateTimeBegin",
						dateTimeBegin.concat(" 00:00:00"));
			}
			if (condition.get("dateTimeEnd") != null) {
				String dateTimeEnd = condition.get("dateTimeEnd").toString();
				condition.put("dateTimeEnd", dateTimeEnd.concat(" 23:59:59"));
			}

			if (condition.get("cashDayBegin") != null) {
				String cashDayBegin = condition.get("cashDayBegin").toString();
				condition.put("cashDayBegin", cashDayBegin.concat(" 00:00:00"));
			}
			if (condition.get("cashDayEnd") != null) {
				String cashDayEnd = condition.get("cashDayEnd").toString();
				condition.put("cashDayEnd", cashDayEnd.concat(" 23:59:59"));
			}

			List<WithdrawalRecord> list = withdrawalRecordBussiness
					.queryWithDrawalRecord(page, condition);
			model.addAttribute("condition", condition);
			model.addAttribute("page", page);
			model.addAttribute("list", list);
		} catch (BusinessException e) {
			logger.info("orderList failure");
		}
		return "manager/withDrawalList";
	}

	@RequestMapping(value = "/{type}/exportWithdrawalRecordQueryResult.html", method = RequestMethod.POST)
	public void exportWithdrawalRecordQueryResult(HttpServletRequest request,
			HttpServletResponse response,  @PathVariable int type) {
		Map<String, Object> condition = setMapCondition(request);
		condition.put("type", type);
		if (condition.get("dateTimeBegin") != null) {
			String dateTimeBegin = condition.get("dateTimeBegin").toString();
			condition.put("dateTimeBegin", dateTimeBegin.concat(" 00:00:00"));
		}
		if (condition.get("dateTimeEnd") != null) {
			String dateTimeEnd = condition.get("dateTimeEnd").toString();
			condition.put("dateTimeEnd", dateTimeEnd.concat(" 23:59:59"));
		}

		if (condition.get("cashDayBegin") != null) {
			String cashDayBegin = condition.get("cashDayBegin").toString();
			condition.put("cashDayBegin", cashDayBegin.concat(" 00:00:00"));
		}
		if (condition.get("cashDayEnd") != null) {
			String cashDayEnd = condition.get("cashDayEnd").toString();
			condition.put("cashDayEnd", cashDayEnd.concat(" 23:59:59"));
		}

		

		WritableWorkbook wbook = null;
		OutputStream os = null;
		try {

			os = response.getOutputStream();
			response.reset();// 清空输出流
			// 不能用用中文设置 filename，会出错
			response.setHeader("Content-disposition",
					"attachment; filename=user.xls");// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			wbook = Workbook.createWorkbook(os);

			WritableSheet wsheet = wbook.createSheet("提现记录", 0); // 工作表名称
			wsheet.setColumnView(1, 70);
			wsheet.setColumnView(2, 15);
			wsheet.setColumnView(3, 20);
			wsheet.setColumnView(4, 30);
			wsheet.setColumnView(5, 50);
			wsheet.setColumnView(6, 50);
			wsheet.setColumnView(7, 60);
			wsheet.setColumnView(8, 50);
			wsheet.setColumnView(9, 50);
			wsheet.setColumnView(10, 20);
			wsheet.setColumnView(11, 40);
			wsheet.setColumnView(12, 70);
			wsheet.setColumnView(13, 20);
			wsheet.setColumnView(14, 20);
			
			
			// 设置Excel字体
			WritableFont wfont = new WritableFont(WritableFont.ARIAL,
												  20,
												  WritableFont.BOLD, false,
												  jxl.format.UnderlineStyle.NO_UNDERLINE,
												  jxl.format.Colour.BLACK);
			WritableCellFormat nameFormat = new WritableCellFormat(wfont);
			nameFormat.setAlignment(jxl.format.Alignment.CENTRE);
			nameFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			WritableCellFormat contentFormat = new WritableCellFormat(new WritableFont(WritableFont.ARIAL,
					  14,
					  WritableFont.NO_BOLD, false,
					  jxl.format.UnderlineStyle.NO_UNDERLINE,
					  jxl.format.Colour.BLACK));
			
			
			Label index = new Label(0, 0, "序号", nameFormat);
			wsheet.addCell(index);
			
			Label serialNumber = new Label(1, 0, "交易流水号", nameFormat);
			wsheet.addCell(serialNumber);
			
			Label name = new Label(2, 0, "姓名", nameFormat);
			wsheet.addCell(name);
			
			Label mobileNumber = new Label(3, 0, "手机号码", nameFormat);
			wsheet.addCell(mobileNumber);
			
			Label amount = new Label(4, 0, "提现金额", nameFormat);
			wsheet.addCell(amount);
			
			Label withdrawalTime = new Label(5, 0, "提现时间", nameFormat);
			wsheet.addCell(withdrawalTime);
			
			Label status = new Label(6, 0, "状态", nameFormat);
			wsheet.addCell(status);
			
			Label cardNumber = new Label(7, 0, "卡号", nameFormat);
			wsheet.addCell(cardNumber);
			
			Label estimatedTimeIntoAccount = new Label(8, 0, "预计到账时间", nameFormat);
			wsheet.addCell(estimatedTimeIntoAccount);
			
			Label estimatedTimeIntoVipAccount = new Label(9, 0, "VIP预计到账时间", nameFormat);
			wsheet.addCell(estimatedTimeIntoVipAccount);
			
			Label cardStatus = new Label(10, 0, "银行卡状态", nameFormat);
			wsheet.addCell(cardStatus);
			
			Label bankName = new Label(11, 0, "银行名称", nameFormat);
			wsheet.addCell(bankName);
			
			Label subBank = new Label(12, 0, "支行", nameFormat);
			wsheet.addCell(subBank);
			
			Label province = new Label(13, 0, "支行所在省", nameFormat);
			wsheet.addCell(province);
			
			Label city = new Label(14, 0, "支行所在市", nameFormat);
			wsheet.addCell(city);
			
			List<WithdrawalRecord> withdrawalRecords = withdrawalRecordBussiness
					.queryWithDrawalRecordForExport(condition);
			int beginRow = 1;
			String _status = "";
			String _cardStatus = "";
			
			for(WithdrawalRecord w : withdrawalRecords){
				wsheet.addCell(new Label(0,beginRow,  String.valueOf(beginRow), contentFormat));
				wsheet.addCell(new Label(1,beginRow,  w.getSerialNumber(), contentFormat));
				wsheet.addCell(new Label(2,beginRow,  w.getUserName(), contentFormat));
				wsheet.addCell(new Label(3,beginRow,  w.getMobileNumber(), contentFormat));
				jxl.write.Number numberCell = new jxl.write.Number(4, beginRow, w.getAmount().doubleValue(), contentFormat);
				wsheet.addCell(numberCell);
				wsheet.addCell(new Label(5,beginRow,  DateUtils.toString(w.getDateTime(), "yyyy-MM-dd HH:mm:ss"), contentFormat));
				
				if("0".equals(w.getStatus())){
					_status = "成功";
				}else if("1".equals(w.getStatus())){
					_status = "失败";
				}else if("2".equals(w.getStatus())){
					_status = "处理中";
				}
				wsheet.addCell(new Label(6,beginRow,  _status, contentFormat));
				wsheet.addCell(new Label(7,beginRow,  w.getCardNumber(), contentFormat));
				wsheet.addCell(new Label(8,beginRow,  w.getCashDay() == null ? "" : DateUtils.toString(w.getCashDay(), "yyyy-MM-dd"), contentFormat));
				wsheet.addCell(new Label(9,beginRow,  w.getVipCashDay() == null ? "" : DateUtils.toString(w.getVipCashDay(), "yyyy-MM-dd"), contentFormat));
				
				
				if(w.getCardStatus() == 0 ){
					_cardStatus = "已删除";
				}else if(w.getCardStatus() == 1 ){
					_cardStatus = "正常";
				}
				wsheet.addCell(new Label(10,beginRow,  _cardStatus, contentFormat));
				wsheet.addCell(new Label(11,beginRow,  w.getBankName(), contentFormat));
				wsheet.addCell(new Label(12,beginRow,  w.getSubBankName(), contentFormat));
				wsheet.addCell(new Label(13,beginRow,  w.getProvince(), contentFormat));
				wsheet.addCell(new Label(14,beginRow,  w.getCityName(), contentFormat));
				
				beginRow++;
			}
			
			wbook.write(); // 写入文件
			wbook.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}finally{
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@RequestMapping(value = "/{serialNumber}/{userid}/{mobileNumber}/{type}/showWithDrawDetail")
	public String showWithDrawDetail(PaginationMore page,
			@PathVariable String serialNumber, @PathVariable String userid,
			@PathVariable String mobileNumber, @PathVariable int type,
			Model model) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("serialNumber", serialNumber);
		condition.put("type", type);
		List<WithdrawalRecord> res = withdrawalRecordBussiness
				.queryWithDrawalRecord(page, condition);
		model.addAttribute("res", res);
		/*
		 * //计算总共充值的钱 BigDecimal rechargeTotal =
		 * rechargeRecordBusiness.getRechargeAmountByUserId(userid);
		 * if(rechargeTotal == null){ rechargeTotal = new BigDecimal(0.00); }
		 * //计算总共提出的钱 BigDecimal withdrawalTotal =
		 * withdrawalRecordBussiness.getWithdrawalAmountByUserId(userid); if(
		 * withdrawalTotal == null){ withdrawalTotal = new BigDecimal(0.00); }
		 * 
		 * //推荐所得 Map<String, Object> _condition = new HashMap<String,
		 * Object>(); _condition.put("inviteUserId", userid); BigDecimal
		 * inviteBnefitTotal=
		 * inviteBenefitBusiness.getInviteBenefitTotal(_condition);
		 * if(inviteBnefitTotal == null){ inviteBnefitTotal = new
		 * BigDecimal(0.00); } //签到奖励 _condition.put("mobilePhone",
		 * mobileNumber); BigDecimal signBenefitTotal =
		 * userSignInBusiness.sumSignBenefitByMobileNumber(_condition);
		 * if(signBenefitTotal == null){ signBenefitTotal = new
		 * BigDecimal(0.00); } //正在进行的游戏押金 _condition.put("status", 1);
		 * BigDecimal unFinishedGameAmountTotal =
		 * gameBusiness.getGameAmountTotalByUserid(_condition);
		 * if(unFinishedGameAmountTotal == null){ unFinishedGameAmountTotal =
		 * new BigDecimal(0.00); } //已完成游戏押金 _condition.put("status", 2);
		 * BigDecimal finishedGameAmountTotal =
		 * gameBusiness.getGameAmountTotalByUserid(_condition);
		 * if(finishedGameAmountTotal == null){ finishedGameAmountTotal = new
		 * BigDecimal(0.00); } //游戏返利 BigDecimal rewardTotal =
		 * gameBusiness.getGameRewardTotalByUserid(_condition); if(rewardTotal
		 * == null){ rewardTotal = new BigDecimal(0.00); } //会员月费
		 * _condition.put("detailType", 4); _condition.put("userid", userid);
		 * BigDecimal vipFee =
		 * dealDetailBusiness.getUserDealdetailAmountByDetailtype(_condition);
		 * if(vipFee == null){ vipFee = new BigDecimal(0.00); } //赠送会员费用
		 * _condition.put("detailType", 10); BigDecimal givenVipFee =
		 * dealDetailBusiness.getUserDealdetailAmountByDetailtype(_condition);
		 * if(givenVipFee == null){ givenVipFee = new BigDecimal(0.00); }
		 * 
		 * //提现手续费 _condition.put("detailType", 1); BigDecimal withdrawalFee =
		 * dealDetailBusiness.getUserDealdetailAmountByDetailtype(_condition);
		 * if(withdrawalFee == null){ withdrawalFee = new BigDecimal(0.00); }
		 * BigDecimal walletRest = rechargeTotal.subtract(withdrawalTotal)
		 * .add(inviteBnefitTotal) .add(signBenefitTotal)
		 * .subtract(unFinishedGameAmountTotal) .add(finishedGameAmountTotal)
		 * .add(rewardTotal) .subtract(vipFee) .subtract(givenVipFee)
		 * .subtract(withdrawalFee); model.addAttribute("walletRest",
		 * walletRest); BigDecimal walletAmount =
		 * userWalletBusiness.getAmountByUserId
		 * (userid).getAmount().add(res.get(0).getAmount());
		 * model.addAttribute("walletAmount", walletAmount);
		 * if(walletAmount.compareTo(walletRest) != 0 ){
		 * if(walletAmount.compareTo(walletRest) == -1 ){
		 * model.addAttribute("msg", "用户帐户余额异常，账户余额小于计算出的值"); }
		 * if(walletAmount.compareTo(walletRest) == 1 ){
		 * model.addAttribute("msg", "用户帐户余额异常，账户余额大于计算出的值"); } }else{
		 * model.addAttribute("msg", "用户帐户余额正常"); }
		 */

		return "manager/showWithDrawDetail";
	}

	@RequestMapping(value = "/{serialNumber}/setDealStatus")
	public @ResponseBody
	Object setDealStatus(@PathVariable String serialNumber) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			withdrawalRecordBussiness.setDealStatus(serialNumber);
			logger.info("设置提现状态为成功状态，serialNumber ＝ " + serialNumber);
		} catch (BusinessException e) {
			resMap.put("resCode", 1);
			resMap.put("resMsg", "操作失败,系统异常！");
			logger.error("设置提现状态失败!" + e.getMessage());
		}
		resMap.put("resCode", 0);
		resMap.put("resMsg", "操作成功！");
		return resMap;
	}
	
	@RequestMapping(value = "/{serialNumber}/setDealStatusFreeze")
	public @ResponseBody
	Object setDealStatusFreeze(@PathVariable String serialNumber) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			withdrawalRecordBussiness.setDealStatusFreeze(serialNumber);
			logger.info("设置提现状态为成功状态，serialNumber ＝ " + serialNumber);
		} catch (BusinessException e) {
			resMap.put("resCode", 1);
			resMap.put("resMsg", "操作失败,系统异常！");
			logger.error("设置提现状态失败!" + e.getMessage());
		}
		resMap.put("resCode", 0);
		resMap.put("resMsg", "操作成功！");
		return resMap;
	}
	
	@RequestMapping(value = "/{serialNumber}/setDealStatusThaw")
	public @ResponseBody
	Object setDealStatusThaw(@PathVariable String serialNumber) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			withdrawalRecordBussiness.setDealStatusThaw(serialNumber);
			logger.info("设置提现状态为成功状态，serialNumber ＝ " + serialNumber);
		} catch (BusinessException e) {
			resMap.put("resCode", 1);
			resMap.put("resMsg", "操作失败,系统异常！");
			logger.error("设置提现状态失败!" + e.getMessage());
		}
		resMap.put("resCode", 0);
		resMap.put("resMsg", "操作成功！");
		return resMap;
	}
	
	@RequestMapping(value = "/{serialNumber}/setDealStatusFail")
	public @ResponseBody
	Object setDealStatusFail(@PathVariable String serialNumber) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			withdrawalRecordBussiness.setDealStatusFail(serialNumber);
			logger.info("设置提现状态为失败状态，serialNumber ＝ " + serialNumber);
		} catch (BusinessException e) {
			resMap.put("resCode", 1);
			resMap.put("resMsg", "操作失败,系统异常！");
			logger.error("设置提现状态失败!" + e.getMessage());
		}
		resMap.put("resCode", 0);
		resMap.put("resMsg", "操作成功！");
		return resMap;
	}
}
