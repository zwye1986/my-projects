package com.venada.efinance.controller;

//import beartool.MD5;
import com.tenpay.RequestHandler;
import com.tenpay.ResponseHandler;
import com.tenpay.client.ClientResponseHandler;
import com.tenpay.client.TenpayHttpClient;
import com.tenpay.util.TenpayUtil;
import com.venada.efinance.business.MessageRuleBusiness;
import com.venada.efinance.business.RechargeRecordBusiness;
import com.venada.efinance.business.SecurityCenterBusiness;
import com.venada.efinance.business.UserWalletBusiness;
import com.venada.efinance.pojo.User;
import com.venada.efinance.security.SpringSecurityUtil;
import com.venada.efinance.sms.SmsService;
import com.venada.efinance.util.DateUtils;
//import com.yeepay.Configuration;
//import com.yeepay.PaymentForOnlineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/notifyPay")
public class IpnController {
	private static final Logger logger = LoggerFactory
			.getLogger(FeedBackController.class);

	@Autowired
	private UserWalletBusiness userWalletBusiness;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;

	@Autowired
	private RechargeRecordBusiness rechargeRecordBusiness;

	@Autowired
	private SecurityCenterBusiness securityCenterBusiness;

	@Autowired
	private SmsService smsService;
	
	@Autowired
	private MessageRuleBusiness  messageRuleBusinessImpl;
	/*
	@RequestMapping(value={"/yeepayCallBack.html"})
    public void yeepayCallBack(HttpServletRequest request,HttpServletResponse response) throws IOException{
		logger.info("易宝充值回调开始");	
		String keyValue   = formatString(Configuration.getInstance().getValue("keyValue"));   // 商家密钥
			String r0_Cmd 	  = formatString(request.getParameter("r0_Cmd")); // 业务类型
			String p1_MerId   = formatString(Configuration.getInstance().getValue("p1_MerId"));   // 商户编号
			String r1_Code    = formatString(request.getParameter("r1_Code"));// 支付结果
			String r2_TrxId   = formatString(request.getParameter("r2_TrxId"));// 易宝支付交易流水号
			String r3_Amt     = formatString(request.getParameter("r3_Amt"));// 支付金额
			String r4_Cur     = formatString(request.getParameter("r4_Cur"));// 交易币种
			String r5_Pid     = new String(formatString(request.getParameter("r5_Pid")).getBytes("iso-8859-1"),"gbk");// 商品名称
			String r6_Order   = formatString(request.getParameter("r6_Order"));// 商户订单号
			String r7_Uid     = formatString(request.getParameter("r7_Uid"));// 易宝支付会员ID
			String r8_MP      = new String(formatString(request.getParameter("r8_MP")).getBytes("iso-8859-1"),"gbk");// 商户扩展信息
			String r9_BType   = formatString(request.getParameter("r9_BType"));// 交易结果返回类型
			String hmac       = formatString(request.getParameter("hmac"));// 签名数据

			logger.info(r6_Order + "||" + r1_Code + "||" + r3_Amt + "||"
					+ r4_Cur + "||" + hmac + "|| ||");

			boolean isOK = false;
			// 校验返回数据包
			isOK = PaymentForOnlineService.verifyCallback(hmac,p1_MerId,r0_Cmd,r1_Code,
					r2_TrxId,r3_Amt,r4_Cur,r5_Pid,r6_Order,r7_Uid,r8_MP,r9_BType,keyValue);
			if(isOK) {
				//在接收到支付结果通知后，判断是否进行过业务逻辑处理，不要重复进行业务逻辑处理
				if(r1_Code.equals("1")) {
					// 产品通用接口支付成功返回-浏览器重定向
					if(r9_BType.equals("1")) {
						System.out.println("callback方式:产品通用接口支付成功返回-浏览器重定向");
						// 产品通用接口支付成功返回-服务器点对点通讯
						Map<String, Object> c = new HashMap<String, Object>();
						c.put("serialNumber", r6_Order);
						c.put("status", "0");
						int i = rechargeRecordBusiness.getAllRechargeRecordsCountForIPN(c);
						logger.info("易宝充值是否已经成功?大于0的都已经成功:{}", i);

						if (i > 0) {
							logger.info("易宝充值记录{}在我侧系统已经完成。", r6_Order);
							response.sendRedirect("http://www.wowpower.cn/user/account/success?money="+r3_Amt);
						}

						userWalletBusiness.realRecharge(r6_Order);
						logger.info("易宝充值过程中钱包及充值记录操作", r6_Order);
					} else if(r9_BType.equals("2")) {
						logger.info("易宝支付进入自动应答");
						// 如果在发起交易请求时	设置使用应答机制时，必须应答以"success"开头的字符串，大小写不敏感
						Map<String, Object> c = new HashMap<String, Object>();
						c.put("serialNumber", r6_Order);
						c.put("status", "0");
						int i = rechargeRecordBusiness.getAllRechargeRecordsCountForIPN(c);
						logger.info("自动应答处理易宝充值是否已经成功?大于0的都已经成功:{}", i);

						if (i > 0) {
							logger.info("自动应答处理易宝充值记录{}在我侧系统已经完成。", r6_Order);
						}

						userWalletBusiness.realRecharge(r6_Order);
						logger.info("自动应答处理易宝充值过程中钱包及充值记录操作", r6_Order);

						response.getWriter().print("SUCCESS");
						logger.info("自动应答结束");
					}
				}else{
					logger.error("易宝支付失败");
				}
			} else {
				logger.info("交易签名被篡改!");
			}
			logger.info("易宝支付回调结束");
    }*/
	
	
	
	@RequestMapping(value={"/sinaPay.html"})
    public void sinaPay(HttpServletRequest request,HttpServletResponse response) throws IOException{
		logger.info("新浪充值充值回调开始");	
		String merchantAcctId = "200100100120000672295700001";
		String version = "v2.3";
		String language = "1";
		String signType = "1"; //1是MD5
		String payType = request.getParameter("payType");
		String bankId = request.getParameter("bankId");
		String orderId = request.getParameter("orderId");
		String orderTime = request.getParameter("orderTime");
		String orderAmount = request.getParameter("orderAmount");
		String dealId = request.getParameter("dealId");
		String bankDealId = request.getParameter("bankDealId");
		String dealTime = request.getParameter("dealTime");
		String payAmount = request.getParameter("payAmount");
		String fee = request.getParameter("fee");
		String ext1 = request.getParameter("ext1");
		String ext2 = request.getParameter("ext2");
		String payResult = request.getParameter("payResult");
		String payIp = request.getParameter("payIp");
		String errCode = request.getParameter("errCode");
		StringBuilder sb = new StringBuilder();
		sb.append("merchantAcctId="+merchantAcctId).append("&version="+version).append("&language="+language).append("&signType="+signType);
		if(payType!=null && !"".equals(payType)){
			sb.append("&payType="+payType);
		}
		if(bankId!=null && !"".equals(bankId)){
			sb.append("&bankId="+bankId);
		}
		if(orderId!=null && !"".equals(orderId)){
			sb.append("&orderId="+orderId);
		}
		if(orderTime!=null && !"".equals(orderTime)){
			sb.append("&orderTime="+orderTime);
		}
		if(orderAmount!=null && !"".equals(orderAmount)){
			sb.append("&orderAmount="+orderAmount);
		}
		if(dealId!=null && !"".equals(dealId)){
			sb.append("&dealId="+dealId);
		}
		if(bankDealId!=null && !"".equals(bankDealId)){
			sb.append("&bankDealId="+bankDealId);
		}
		if(dealTime!=null && !"".equals(dealTime)){
			sb.append("&dealTime="+dealTime);
		}
		if(payAmount!=null && !"".equals(payAmount)){
			sb.append("&payAmount="+payAmount);
		}
		if(fee!=null && !"".equals(fee)){
			sb.append("&fee="+fee);
		}
		if(ext1!=null && !"".equals(ext1)){
			sb.append("&ext1="+ext1);
		}
		if(ext2!=null && !"".equals(ext2)){
			sb.append("&ext2="+ext2);
		}
		if(payResult!=null && !"".equals(payResult)){
			sb.append("&payResult="+payResult);
		}
		if(payIp!=null && !"".equals(payIp)){
			sb.append("&payIp="+payIp);
		}
		if(errCode!=null && !"".equals(errCode)){
			sb.append("&errCode="+errCode);
		}
		sb.append("&key=venada0203wowpower");
		String signMsg = md5PasswordEncoder.encodePassword(sb.toString(), "").toLowerCase();
		String realsignMsg = request.getParameter("signMsg");
		if(signMsg.equals(realsignMsg)){
			if(payResult.equals("10")){
				Map<String, Object> c = new HashMap<String, Object>();
				c.put("serialNumber", orderId);
				c.put("status", "0");
				int i = rechargeRecordBusiness.getAllRechargeRecordsCountForIPN(c);
				logger.info("新浪充值是否已经成功?大于0的都已经成功:{}", i);

				if (i > 0) {
					logger.info("新浪充值记录{}在我侧系统已经完成。", orderId);
				//	response.sendRedirect("http://www.wowpower.cn/user/account/success?money="+orderAmount);
				}else{
		    
				userWalletBusiness.realRecharge(orderId);
				logger.info("新浪充值过程中钱包及充值记录操作", orderId);
				}
				BigDecimal amount = new BigDecimal(orderAmount);
				
				response.getWriter().print("<result>1</result><redirecturl><![CDATA[http://www.wowpower.cn/user/account/success?money="+amount.divide(new BigDecimal(100))+"]]></redirecturl>");
			}
		}
			
    }
	   
	   private String formatString(String text){
		   if(text == null) {
				return ""; 
			}
			return text;
	   }
/*
	@RequestMapping(value = "/chinabank", method = RequestMethod.POST)
	public String callBackChinaBank(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("网银在线用户充值回调开始");

		String key = "venadaroot%$#@!";

		// 获取参数
		String v_oid = request.getParameter("v_oid"); // 订单号
		// String v_pmode = request.getParameter("v_pmode"); //
		// 支付方式中文说明，如"中行长城信用卡"
		String v_pstatus = request.getParameter("v_pstatus"); // 支付结果，20支付完成；30支付失败；
		// String v_pstring = request.getParameter("v_pstring"); //
		// 对支付结果的说明，成功时（v_pstatus=20）为"支付成功"，支付失败时（v_pstatus=30）为"支付失败"
		String v_amount = request.getParameter("v_amount"); // 订单实际支付金额
		String v_moneytype = request.getParameter("v_moneytype"); // 币种
		String v_md5str = request.getParameter("v_md5str"); // MD5校验码
		String remark1 = request.getParameter("remark1"); // 备注1
		String remark2 = request.getParameter("remark2"); // 备注2

		logger.info(v_oid + "||" + v_pstatus + "||" + v_amount + "||"
				+ v_moneytype + "||" + v_md5str + "||" + remark1 + "||"
				+ remark2);

		String text = v_oid + v_pstatus + v_amount + v_moneytype + key;
		MD5 md5 = new MD5();
		String v_md5text = md5.getMD5ofStr(text).toUpperCase();

		if (v_md5str.equals(v_md5text)) {
			if ("30".equals(v_pstatus)) {
				logger.error("网银在线用户支付失败");
			} else if ("20".equals(v_pstatus)) {
				Map<String, Object> c = new HashMap<String, Object>();
				c.put("serialNumber", v_oid);
				c.put("status", "0");
				int i = rechargeRecordBusiness
						.getAllRechargeRecordsCountForIPN(c);

				logger.info("网银在线用户充值是否已经成功?大于0的都为是:{}", i);

				if (i > 0) {
					logger.info("网银在线用户充值记录{}在我侧系统已经完成。", v_oid);
					return "redirect:http://www.wowpower.cn/user/account/success?money="+v_amount;
				}

				userWalletBusiness.realRecharge(v_oid);
				logger.info("网银在线用户充值过程中钱包及充值记录操作", v_oid);
			
			}
		} else {
			logger.info("网银在线用户充值请求有被篡改嫌疑.");
		}

		logger.info("网银在线用户充值回调结束");
		return "redirect:http://www.wowpower.cn/user/account/manager";
	}

	@RequestMapping(value = "/chinabankAutoReceive", method = RequestMethod.GET)
	public @ResponseBody
	String chinaBankAutoReceive(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String result = "";
		String key = "venadaroot%$#@!";
		// 获取参数
		String v_oid = request.getParameter("v_oid"); // 订单号
		logger.info("开始自动接收网银在线结果,订单号:{}", v_oid);
		String v_pstatus = request.getParameter("v_pstatus"); // 支付结果，20支付完成；30支付失败；
		String v_amount = request.getParameter("v_amount"); // 订单实际支付金额
		String v_moneytype = request.getParameter("v_moneytype"); // 币种
		String v_md5str = request.getParameter("v_md5str"); // MD5校验码
		String remark2 = request.getParameter("remark2"); // 备注2
		logger.info("自动接收网银在线结果:" + v_oid + "||" + v_pstatus + "||" + "||"
				+ v_amount + "||" + v_moneytype + "||" + v_md5str + "||"
				+ remark2);
		String text = v_oid + v_pstatus + v_amount + v_moneytype + key; // 拼凑加密串
		MD5 md5 = new MD5();
		String v_md5text = md5.getMD5ofStr(text).toUpperCase();
		if (v_md5str.equals(v_md5text)) {
			if ("20".equals(v_pstatus)) {
				Map<String, Object> c = new HashMap<String, Object>();
				c.put("serialNumber", v_oid);
				c.put("status", "0");
				int i = rechargeRecordBusiness
						.getAllRechargeRecordsCountForIPN(c);

				logger.info("网银在线用户充值是否已经成功?大于0的都为是:{}", i);

				if (i > 0) {
					logger.info("网银在线用户充值记录{}在我侧系统已经完成。", v_oid);
					return "ok";
				}

				userWalletBusiness.realRecharge(v_oid);

				
				result = "ok";
				logger.info("自动接收网银在线结果成功结束,订单号:{}", v_oid);
			}

		} else {
			result = "error";
			logger.error("自动接收网银在线结果失败,签名有问题,订单号:{}", v_oid);
		}

		return result;
	}*/

	@RequestMapping(value = "/tenpay", method = RequestMethod.GET)
	public void callBackTenpay(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 创建支付应答对象
		ResponseHandler resHandler = new ResponseHandler(request, response);
		resHandler.setKey("34239766a166764470e9880db21c145c");

		logger.info("后台回调返回参数:{}", resHandler.getAllParameters());
		// 判断签名
		if (resHandler.isTenpaySign()) {

			// 通知id
			String notify_id = resHandler.getParameter("notify_id");

			// 创建请求对象
			RequestHandler queryReq = new RequestHandler(null, null);
			// 通信对象
			TenpayHttpClient httpClient = new TenpayHttpClient();
			// 应答对象
			ClientResponseHandler queryRes = new ClientResponseHandler();

			// 通过通知ID查询，确保通知来至财付通
			queryReq.init();
			queryReq.setKey("34239766a166764470e9880db21c145c");
			queryReq.setGateUrl("https://gw.tenpay.com/gateway/simpleverifynotifyid.xml");
			queryReq.setParameter("partner", "1216941201");
			queryReq.setParameter("notify_id", notify_id);

			// 通信对象
			httpClient.setTimeOut(5);
			// 设置请求内容
			httpClient.setReqContent(queryReq.getRequestURL());
			logger.info("验证ID请求字符串:" + queryReq.getRequestURL());

			// 后台调用
			if (httpClient.call()) {
				// 设置结果参数
				queryRes.setContent(httpClient.getResContent());
				logger.info("验证ID返回字符串:" + httpClient.getResContent());
				queryRes.setKey("34239766a166764470e9880db21c145c");

				// 获取id验证返回状态码，0表示此通知id是财付通发起
				String retcode = queryRes.getParameter("retcode");

				// 商户订单号
				String out_trade_no = resHandler.getParameter("out_trade_no");
				// 财付通订单号
				String transaction_id = resHandler
						.getParameter("transaction_id");
				// 金额,以分为单位
				String total_fee = resHandler.getParameter("total_fee");
				// 如果有使用折扣券，discount有值，total_fee+discount=原请求的total_fee
				String discount = resHandler.getParameter("discount");
				// 支付结果
				String trade_state = resHandler.getParameter("trade_state");
				// 交易模式，1即时到账，2中介担保
				String trade_mode = resHandler.getParameter("trade_mode");

				// 判断签名及结果
				if (queryRes.isTenpaySign() && "0".equals(retcode)) {
					logger.info("id验证成功");

					if ("1".equals(trade_mode)) { // 即时到账
						// 查询充值记录是否为成功，如果成功，则无需处理了。
						Map<String, Object> c = new HashMap<String, Object>();
						c.put("serialNumber", out_trade_no);
						c.put("status", "0");
						int i = rechargeRecordBusiness
								.getAllRechargeRecordsCountForIPN(c);

						logger.info("是否已经充值成功?大于0的都为是:{}", i);

						if (i > 0) {
							logger.info("充值记录{}在我侧系统已经完成。", out_trade_no);
							resHandler.sendToCFT("success");
							return;
						}

						if ("0".equals(trade_state)) {
							// 给财付通系统发送成功信息，财付通系统收到此结果后不再进行后续通知
							resHandler.sendToCFT("success");

							// ------------------------------
							// 即时到账处理业务开始
							// ------------------------------
							if (out_trade_no.substring(0, 2).equals("OC")) {
								userWalletBusiness.realPayFor(out_trade_no);
							} else {
								userWalletBusiness.realRecharge(out_trade_no);
							}
							// ------------------------------
							// 即时到账处理业务完毕
							// ------------------------------

							// ------------------------------
							// 会员短信发送开始
							// ------------------------------
							User user = SpringSecurityUtil.getCurrentUser();
							boolean b = securityCenterBusiness.isOpen(user
									.getId());
							if (b) {
								BigDecimal totalFree = new BigDecimal(0);
								try {
									totalFree = new BigDecimal(total_fee)
											.divide(new BigDecimal(100));
								} catch (java.lang.NumberFormatException e) {
									logger.info("total_fee:" + total_fee
											+ "转换为BigDecimal异常");
								} catch (Exception e) {
									logger.info("total_fee:" + total_fee
											+ "转换为BigDecimal异常");
								}
								String sms = "您于"
										+ DateUtils
												.currentTime("yyyy-MM-dd HH:mm:ss")
										+ "成功充值" + totalFree + "元";
								//getSendMobileNumber
								String mobileNumber = messageRuleBusinessImpl
										.getSendMobileNumber(user);
								if (messageRuleBusinessImpl.isSend(user)) {
								//	smsService.sendSms(mobileNumber, sms);
									smsService.sendSms(mobileNumber, "", "MB-2014011458",DateUtils.currentTime("yyyy-MM-dd HH:mm:ss"),totalFree.toString());
								}
//								smsService.sendSms(user.getMobileNumber(), sms);
							}
							// ------------------------------
							// 会员短信发送结束
							// ------------------------------
							logger.info("即时到账支付成功，我侧系统处理完毕。");

						} else {
							logger.info("即时到账支付失败");
							resHandler.sendToCFT("fail");
						}
					} else if ("2".equals(trade_mode)) { // 中介担保
						// ------------------------------
						// 中介担保处理业务开始
						// ------------------------------

						// 处理数据库逻辑
						// 注意交易单不要重复处理
						// 注意判断返回金额

						int iStatus = TenpayUtil.toInt(trade_state);
						switch (iStatus) {
						case 0: // 付款成功

							break;
						case 1: // 交易创建

							break;
						case 2: // 收获地址填写完毕

							break;
						case 4: // 卖家发货成功

							break;
						case 5: // 买家收货确认，交易成功

							break;
						case 6: // 交易关闭，未完成超时关闭

							break;
						case 7: // 修改交易价格成功

							break;
						case 8: // 买家发起退款

							break;
						case 9: // 退款成功

							break;
						case 10: // 退款关闭

							break;
						default:
						}

						// ------------------------------
						// 中介担保处理业务完毕
						// ------------------------------

						logger.info("trade_state = " + trade_state);
						// 给财付通系统发送成功信息，财付通系统收到此结果后不再进行后续通知
						resHandler.sendToCFT("success");
					}
				} else {
					// 错误时，返回结果未签名，记录retcode、retmsg看失败详情。
					logger.info("查询验证签名失败或id验证失败" + ",retcode:"
							+ queryRes.getParameter("retcode"));
				}
			} else {
				logger.info("后台调用通信失败");
				logger.info(String.valueOf(httpClient.getResponseCode()));
				logger.info(httpClient.getErrInfo());
				// 有可能因为网络原因，请求已经处理，但未收到应答。
			}
		} else {
			logger.info("通知签名验证失败");
		}
	}
}