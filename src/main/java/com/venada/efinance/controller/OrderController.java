package com.venada.efinance.controller;

import com.venada.efinance.business.*;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.Constant;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.enumtype.LogTypeEnum;
import com.venada.efinance.enumtype.TradeStatus;
import com.venada.efinance.pojo.*;
import com.venada.efinance.security.SpringSecurityUtil;
import com.venada.efinance.util.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
	private static final Logger logger = LoggerFactory
			.getLogger(OrderController.class);
	@Autowired
	private UserDetailBusiness userDetailBusiness;

	@Autowired
	private OrderBusiness orderBusiness;
	@Autowired
	private ProvinceBusiness provinceBusiness;

	@Autowired
	private CityBusiness cityBusiness;
	@Autowired
	private AreaBusiness areaBusiness;
	
	@Autowired
	private UserWalletBusiness userWalletBusinessImpl;
	
	@Autowired
	private OperationLogBusiness opeartionLogBusiness;

	@RequestMapping(value = {  "/manager/orderDetail", "/manager/orderDetail.html"})
	public String orderDetail(Model model) {

		// 加载全国省份
		List<Province> provinces = provinceBusiness.findAllProvinceList();
		model.addAttribute("liveProvince", provinces);


		Goods goods = new Goods();
		goods.setGoodsName(Constant.goodsName);
		goods.setNum(1);
		goods.setPrice(Constant.goodsPrice);
		goods.setPayment(Constant.goodsPrice);
		goods.setTotalPrice(Constant.goodsPrice * 1);
		model.addAttribute("goods", goods);
		return ".orderManager";
	}

	@RequestMapping(value = "/manager/orderDetailPayment")
	public String orderDetailPayment(Model model) {
		return ".orderDetailPayment";
	}
	
	
	@RequestMapping(value = "/manager/orderDetailPaymentSucess")
	public String orderDetailPaymentSucess(Model model) {
		return ".orderDetailPaymentSucess";
	}
	
	
	
	/*@RequestMapping(value = "/recharge/done", method = RequestMethod.POST)
	public String recharge( BigDecimal amount,
			String bankCardId,String bank_type, HttpServletRequest request,HttpServletResponse response) {
		try {
			String url = userWalletBusiness.payforCoolpos(amount, bank_type,
					request.getRemoteAddr(), bankCardId,request,response);
			return "redirect:" + url;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "redirect:/order/manager/orderlist";
	}*/
	
	
	
	@RequestMapping(value = "/manager/addorder", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addOrder(Order o) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			User u = SpringSecurityUtil.getCurrentUser();
			if(o.getNum()<5){
				o.setTotalPrice(o.getNum()*Constant.goodsPrice);
				o.setPaymentConvert(o.getNum()*Constant.goodsPrice);
				o.setIsNegotiate(0);
			}else{
				o.setTotalPrice(0.0);
				o.setPaymentConvert(0.0);
				o.setIsNegotiate(1);
			}
			o.setMobileNumberBuy(u.getMobileNumber());
			o.setTradeStatus(TradeStatus.CreateOrder.ordinal());
			orderBusiness.addOrder(o);
			
			OperationLog log = new OperationLog();
			log.setLogType(LogTypeEnum.OrderKS.getIndex());
			log.setDataOld("");
			log.setDataNew("新增订单：酷刷:"+o.getNum()+"个，订单号:"+o.getId());
			opeartionLogBusiness.addOperationLog(log);
			result.put("success", true);
			result.put("id", o.getId());
			logger.info("addOrder success");
		} catch (Exception e) {
			result.put("success", false);
			logger.info("addOrder failure");
		}
		return result;
	}

	@RequestMapping(value = "/manager/getorderById", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getOrderById(@RequestParam(value = "Id", required = false) Long Id) {
		Map<String, Object> data = new HashMap<String, Object>();
		User u = SpringSecurityUtil.getCurrentUser();
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("user", u);
		try {
			condition.put("Id", Id);
			Order order = orderBusiness.queryOrderById(condition);
			if (order != null) {
				data.put("success", true);
				data.put("data", order);
				logger.info("getOrder success");
			} else {
				data.put("success", false);
				logger.info("getOrder failure");

			}
		} catch (Exception e) {
			data.put("success", false);
			logger.error(e.getMessage());
		}
		return data;
	}

	@RequestMapping(value = { "/manager/orderlist", "/manager/orderlist.html" })
	public String orderList(
			@RequestParam(value = "id", required = false) Integer Id,
			PaginationMore page, Model model) {
		User u = SpringSecurityUtil.getCurrentUser();
		List<Order> orderList = new ArrayList<Order>();
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("user", u);
		if (Id != null) {
			condition.put("Id", Id);
			Order order = orderBusiness.queryOrderById(condition);
			orderList.add(order);
		} else {
			page.setPageSize(10);
			orderList = orderBusiness.queryOrder(condition, page);
			model.addAttribute("page", page);
		}
		model.addAttribute("orderList", orderList);
		return ".orderList";
	}

	@Transactional
	@RequestMapping(value = { "/manager/payorder" })
	@ResponseBody
	public Map<String, Object> payOrder(Long Id, String payforMethod,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data = new HashMap<String, Object>();
		User u = SpringSecurityUtil.getCurrentUser();
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("Id", Id);
		condition.put("user", u);
		Order order = orderBusiness.queryOrderById(condition);
		try {
			BigDecimal amount = BigDecimal.valueOf(order.getPayment());
			String bank_type = "";
			String bankCardId = "";
			String url = "";
			String msg = "";
			if (payforMethod.equals("2")) {
				url = userWalletBusinessImpl.payforCoolpos(Id, amount,
						bank_type, request.getRemoteAddr(), bankCardId,
						request, response);
			} else {
				UserWallet userWallet = userWalletBusinessImpl.getAmountByUserId(u.getId());
				if (userWallet != null) {
					if (userWallet.getAmount().compareTo(amount) == -1) {
						url = "/user/account/recharge";
						msg = "余额不足,请充值！";
					} else {
						order.setTradeStatus(TradeStatus.TradeCommit.ordinal());
						order.setPayTime(new Date());
						orderBusiness.updateOrderById(order);
						userWallet.setAmount(userWallet.getAmount().subtract(amount));
						userWalletBusinessImpl.updateUserWallet(userWallet);
						OperationLog log = new OperationLog();
						log.setLogType(LogTypeEnum.OrderKS.getIndex());
						log.setDataOld("");
						log.setDataNew("支付酷刷订单号:"+order.getId()+"成功！"+"共支付"+amount+"纳币！");
						opeartionLogBusiness.addOperationLog(log);
						
						msg = "纳币支付成功！";
						url = "/order/manager/orderDetailPaymentSucess";
					}
				}
			}
			data.put("success", true);
			data.put("url", url);
			data.put("msg", msg);
			logger.info("payOrder success");
		} catch (Exception e) {
			order.setTradeStatus(TradeStatus.PayFailure.ordinal());
			orderBusiness.updateOrderById(order);
			data.put("success", false);
			data.put("url", "/order/manager/orderlist");
			logger.error(e.getMessage());
		}
		return data;
	}
	
	@RequestMapping(value = { "/manager/orderDetailCancel" })
	public String orderDetailCancel(Long Id) {
		Map<String, Object> data = new HashMap<String, Object>();
		Order order = new Order();
		try {
			order.setId(Id);
			order.setCancelTime(new Date());
			order.setTradeStatus(TradeStatus.TradeCancel.ordinal());
			orderBusiness.updateOrderById(order);
			data.put("success", true);
			logger.info("orderDetailCancel success");
		} catch (Exception e) {
			data.put("success", false);
			logger.error(e.getMessage());
		}
		return "redirect:/order/manager/orderlist";
	}
	

	@RequestMapping(value = "/manager/validateMobilePhone")
	@ResponseBody
	public Map<String, Object> validateMobilePhone(@RequestParam(value = "mobileNumber", required = false) String mobileNumber) {
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
				}else{
					resultMap.put("resultCode", 't');
					resultMap.put("resultMsg", "手机号码符合规则!");
					return resultMap;
				}
			}
		} catch (BusinessException e) {
			logger.error(e.getMessage());
			resultMap.put("resultCode", 'f');
			resultMap.put("resultMsg", "系统错误，请重试!");
			return resultMap;
		}
	}
	
}
