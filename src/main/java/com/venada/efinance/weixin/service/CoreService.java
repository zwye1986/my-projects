package com.venada.efinance.weixin.service;

import com.venada.efinance.business.*;
import com.venada.efinance.pojo.User;
import com.venada.efinance.pojo.UserGameRelation;
import com.venada.efinance.pojo.WeixinDesposit;
import com.venada.efinance.pojo.WeixinUser;
import com.venada.efinance.servlet.TokenThread;
import com.venada.efinance.weixin.common.util.AdvancedUtil;
import com.venada.efinance.weixin.common.util.DesUtil;
import com.venada.efinance.weixin.common.util.MessageUtil;
import com.venada.efinance.weixin.message.resp.Article;
import com.venada.efinance.weixin.message.resp.NewsMessage;
import com.venada.efinance.weixin.message.resp.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.net.URLEncoder;
import java.util.*;

/**
 * 核心服务类
 * 
 * @author hepei
 * @date 2014-7-09
 */
@Service
public class CoreService {
	private static Logger log = LoggerFactory.getLogger(CoreService.class);
	@Autowired
	private WeixinUserBusiness weixinUserBusinessImpl;
	@Autowired
	private UserBusiness userBusiness;

	@Autowired
	private UserWalletBusiness userWalletBusinessImpl;
	@Autowired
	private UserSignInBusiness userSignInBusinessImpl;
	@Autowired
	private InviteBenefitBusiness inviteBenefitBusinessImp;
	@Autowired
	private UserWalletBusiness userWalletBusiness;
	@Autowired
	private DealDetailBusiness dealDetailBusiness;
	@Autowired
	private GameBusiness gameBusinessImpl;
	@Autowired
	private WeixinDespositBusiness weixinDespositBusinessImpl;
	private static String url = "http://www.wowpower.cn";

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	public String processRequest(HttpServletRequest request) {

		// xml格式的消息数据
		String respXml = null;
		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号
			String fromUserName = requestMap.get("FromUserName");
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			// 事件推送
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					NewsMessage newsMessage = new NewsMessage();
					newsMessage.setToUserName(fromUserName);
					newsMessage.setFromUserName(toUserName);
					newsMessage.setCreateTime(new Date().getTime());
					newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
					List<Article> articleList = new ArrayList<Article>();
					Article article = new Article();
					article.setTitle("欢迎关注蛙宝网");
					article.setDescription("您好,欢迎关注蛙宝网,月息最高可达3.3%!");
					article.setPicUrl(url+"/images/weixinsub.png");
					article.setUrl(url);
					articleList.add(article);
					// 设置图文消息个数
					newsMessage.setArticleCount(articleList.size());
					// 设置图文消息包含的图文集合
					newsMessage.setArticles(articleList);
					// 将图文消息对象转换成xml字符串
					respXml = MessageUtil.messageToXml(newsMessage);
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// 删除蛙宝和微信的绑定关系
				  if(weixinUserBusinessImpl.isOAuth(fromUserName)){
					  weixinUserBusinessImpl.delWeixinUserByOpenId(fromUserName);
				  }
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 判断用户是否已经是本系统用户
					if (!weixinUserBusinessImpl.isOAuth(fromUserName)) {
						textMessage.setContent("您的微信号尚未绑定蛙宝账号<a href=\""
								+ url
								+ "/weixin/bundling?openid="
								+ URLEncoder.encode(DesUtil
										.encrypt(fromUserName))
								+ "\">点这里进行绑定</a>!");
						respXml = MessageUtil.messageToXml(textMessage);
					} else {
						// 事件KEY值，与创建菜单时的key值对应
						log.info("eventType:" + eventType);
						String eventKey = requestMap.get("EventKey");
						// 根据key值判断用户点击的按钮
						if (eventKey.equals("balance")) {
							long beginTime =System.currentTimeMillis();
							// 账户积分
							Integer credits = 0;
							Object o=getUserCredits(fromUserName).get("credits");
							if (o == null) {
								credits = 0;
							} else {
								credits = Integer.valueOf(o.toString());
							}
							// 查询用户余额
							String walletAmount=getUserWallertAmount(fromUserName).get("walletAmount").toString();
							
							long endTime=System.currentTimeMillis();
							log.info("balance耗时"+(endTime-beginTime));
							if((endTime-beginTime)<5000){
								textMessage.setContent("您的账户\n余额:"
										+ walletAmount + "纳币\n当前积分" + credits);
								respXml = MessageUtil.messageToXml(textMessage);
							}else{
								AdvancedUtil.sendCustomMessage(TokenThread.accessToken.getAccessToken(), AdvancedUtil.makeTextCustomMessage(fromUserName,"您的账户\n余额:"
										+ walletAmount + "纳币\n当前积分" + credits));
							}
						}
						// 查询预计收益
						else if (eventKey.equals("anticipatedIncome")) {
							long beginTime =System.currentTimeMillis();
							Map<String,Object> result=getUserGameBenefit(fromUserName);
							// 预计收入
							String gameBenefit = result.get("gameBenefit").toString();
							// 用户当前投资金额（押金）
							String gameDeposit = result.get("gameDeposit").toString();
							long endTime=System.currentTimeMillis();
							log.info("anticipatedIncome耗时"+(endTime-beginTime));
							if((endTime-beginTime)<5000){
								textMessage.setContent("您的账户\n预计收益:"
										+ gameBenefit + "纳币\n当前投资金额"
										+ gameDeposit + "纳币");
								respXml = MessageUtil.messageToXml(textMessage);
							}else{
								AdvancedUtil.sendCustomMessage(TokenThread.accessToken.getAccessToken(), AdvancedUtil.makeTextCustomMessage(fromUserName,"您的账户\n预计收益:"
										+ gameBenefit + "纳币\n当前投资金额"+ gameDeposit + "纳币"));
							}
							
						}
						// 查询累计收益
						else if (eventKey.equals("accumulatedIncome")) {
							long beginTime =System.currentTimeMillis();
							String dealDetailAmount=getUserDealDetailAmount(fromUserName).get("dealDetailAmount").toString();
							String total=getUserInviteBenefit(fromUserName).get("total").toString();
							String signTotal=getUserSignBenefit(fromUserName).get("signTotal").toString();
							long endTime=System.currentTimeMillis();
							log.info("accumulatedIncome耗时"+(endTime-beginTime));
							if((endTime-beginTime)<5000){
							textMessage.setContent("您的账户\n累计收益:"
											+ dealDetailAmount
											+ "纳币\n推荐奖励:" + total
											+ "纳币\n签到奖励" + signTotal + "纳币");
							respXml = MessageUtil.messageToXml(textMessage);
							}else{
								AdvancedUtil.sendCustomMessage(TokenThread.accessToken.getAccessToken(), AdvancedUtil.makeTextCustomMessage(fromUserName,"您的账户\n累计收益:"
											+ dealDetailAmount
											+ "纳币\n推荐奖励:" + total
											+ "纳币\n签到奖励" + signTotal + "纳币"));
							}
						}// 用户签到
						else if (eventKey.equals("signByWeixin")) {
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						List<Article> articleList = new ArrayList<Article>();
						Article article = new Article();
						article.setTitle("蛙宝网微信公众平台签到");
//						article.setDescription("以连续 7 天为周期，单日签到可获 0.1元; 连续 6 天签到后,第 7 天签到即可获 0.5元!签到次数越多，人民币领取越多哦！");
						article.setPicUrl(url+"/images/weixin/sign_title.png");
						article.setUrl(url+"/weixin/signWeixin?openid="+URLEncoder.encode(DesUtil.encrypt(fromUserName)));
						articleList.add(article);
						// 设置图文消息个数
						newsMessage.setArticleCount(articleList.size());
						// 设置图文消息包含的图文集合
						newsMessage.setArticles(articleList);
						// 将图文消息对象转换成xml字符串
						respXml = MessageUtil.messageToXml(newsMessage);
					} else if (eventKey.equals("weixinDesposit")) {
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						List<Article> articleList = new ArrayList<Article>();
						Article article = new Article();
						article.setTitle("蛙宝网微信公众平台预计结算明细");
						article.setDescription("通过手机让您更方便的查看预计结算明细,为您的理财做出更准确的抉择!");
						article.setPicUrl(url+"/images/weixinsub.png");
						article.setUrl(url+"/weixin/weixinDesposit?openid="+URLEncoder.encode(DesUtil.encrypt(fromUserName)));
						articleList.add(article);
						// 设置图文消息个数
						newsMessage.setArticleCount(articleList.size());
						// 设置图文消息包含的图文集合
						newsMessage.setArticles(articleList);
						// 将图文消息对象转换成xml字符串
						respXml = MessageUtil.messageToXml(newsMessage);
					}

//						long beginTime=System.currentTimeMillis();
//						log.info("beginTime:"+beginTime);
////						Map<String, Object> result = getUserInfoByMobilePhone(fromUserName);
//						long costTime=System.currentTimeMillis()-beginTime;
//						log.info("costTime:"+costTime);
//						if(costTime<500){
//							if (result.get("resCode") != null
//									&& !result.get("resCode").equals("")) {
//								// 判断openid绑定的用户是否存在系统中
//								textMessage.setContent(result.get("resMsg")
//										.toString());
//								respXml = MessageUtil.messageToXml(textMessage);
//							} else {
//								// 账户积分
//								Integer credits = 0;
//								if (result.get("credits") == null) {
//									credits = 0;
//								} else {
//									credits = Integer.valueOf(result.get("credits").toString());
//								}
//
//								// 账户余额
//								String walletAmount = result.get("walletAmount").toString();
//								// 账户收入
//								String dealDetailAmount = result.get("dealDetailAmount").toString();
//								// 用户当前投资金额（押金）
//								String gameDeposit = result.get("gameDeposit").toString();
//								// 预计收入
//								String gameBenefit = result.get("gameBenefit").toString();
//								// 查询推荐奖励
//								String total = result.get("total").toString();
//								// 查询签到奖励
//								String signTotal = result.get("signTotal").toString();
//								// 事件KEY值，与创建菜单时的key值对应
//								log.info("eventType:" + eventType);
//								String eventKey = requestMap.get("EventKey");
//								// 根据key值判断用户点击的按钮
//								// 查询用户余额
//								if (eventKey.equals("balance")) {
//
//									textMessage.setContent("您的账户\n余额:"
//											+ walletAmount + "纳币\n当前积分" + credits);
//									respXml = MessageUtil.messageToXml(textMessage);
//								}
//								// 查询预计收益
//								else if (eventKey.equals("anticipatedIncome")) {
//									textMessage.setContent("您的账户\n预计收益:"
//											+ gameBenefit + "纳币\n当前投资金额"
//											+ gameDeposit + "纳币");
//									respXml = MessageUtil.messageToXml(textMessage);
//								}
//								// 查询累计收益
//								else if (eventKey.equals("accumulatedIncome")) {
//									textMessage
//											.setContent("您的账户\n累计收益:"
//													+ dealDetailAmount
//													+ "纳币\n推荐奖励:" + total
//													+ "纳币\n签到奖励" + signTotal + "纳币");
//									respXml = MessageUtil.messageToXml(textMessage);
//								}
//								// 用户签到
//								else if (eventKey.equals("signByWeixin")) {
//									NewsMessage newsMessage = new NewsMessage();
//									newsMessage.setToUserName(fromUserName);
//									newsMessage.setFromUserName(toUserName);
//									newsMessage.setCreateTime(new Date().getTime());
//									newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
//									List<Article> articleList = new ArrayList<Article>();
//									Article article = new Article();
//									article.setTitle("蛙宝网微信公众平台签到");
//									article.setDescription("以连续 7 天为周期，单日签到可获 0.1元; 连续 6 天签到后,第 7 天签到即可获 0.5元!签到次数越多，人民币领取越多哦！");
//									article.setPicUrl(url+"/images/weixin/sign_title.png");
//									article.setUrl(url+"/weixin/signWeixin?openid="+URLEncoder.encode(DesUtil.encrypt(fromUserName)));
//									articleList.add(article);
//									// 设置图文消息个数
//									newsMessage.setArticleCount(articleList.size());
//									// 设置图文消息包含的图文集合
//									newsMessage.setArticles(articleList);
//									// 将图文消息对象转换成xml字符串
//									respXml = MessageUtil.messageToXml(newsMessage);
//								} else if (eventKey.equals("weixinDesposit")) {
//									NewsMessage newsMessage = new NewsMessage();
//									newsMessage.setToUserName(fromUserName);
//									newsMessage.setFromUserName(toUserName);
//									newsMessage.setCreateTime(new Date().getTime());
//									newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
//									List<Article> articleList = new ArrayList<Article>();
//									Article article = new Article();
//									article.setTitle("蛙宝网微信公众平台预计结算明细");
//									article.setDescription("通过手机让您更方便的查看预计结算明细,为您的理财做出更准确的抉择!");
//									article.setPicUrl(url+"/images/weixinsub.png");
//									article.setUrl(url+"/weixin/weixinDesposit?openid="+URLEncoder.encode(DesUtil.encrypt(fromUserName)));
//									articleList.add(article);
//									// 设置图文消息个数
//									newsMessage.setArticleCount(articleList.size());
//									// 设置图文消息包含的图文集合
//									newsMessage.setArticles(articleList);
//									// 将图文消息对象转换成xml字符串
//									respXml = MessageUtil.messageToXml(newsMessage);
//								}
//							}
//							
//						}else{
//							textMessage.setContent("请求超时了！！");
//							log.info("请求超时了！！");
//							respXml = MessageUtil.messageToXml(textMessage);
//							AdvancedUtil.sendCustomMessage(TokenThread.accessToken.getAccessToken(), AdvancedUtil.makeTextCustomMessage(fromUserName,"请求超时!"));
//						}
					
					}
				}
			}
			// 当用户发消息时
			else {
				textMessage.setContent("请通过菜单使用网址导航服务！");
				respXml = MessageUtil.messageToXml(textMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}

	/**
	 * 根据openid获取预计到张列表
	 */
	public List<WeixinDesposit> getWeixinDespositByOpenid(String openid) {
		List<WeixinDesposit> list=new ArrayList<WeixinDesposit>();
		User user = getUserByOpenid(openid);
		if (user == null) {
			
		} else {
			list=weixinDespositBusinessImpl.listWeixinDesposit(user.getMobileNumber());
		}
		return list;
	}

	/**
	 * 根据openid获取蛙宝用户
	 */

	public User getUserByOpenid(String openid) {
		String userid = "";
		// 查询用户是否已经绑定了微信账号
		WeixinUser weixinUser = weixinUserBusinessImpl
				.getWeixinUserOpenid(openid);
		if (weixinUser != null && !weixinUser.equals("")) {
			userid = weixinUser.getUserid();
		}
		User user = userBusiness.findUserById(userid);
		return user;
	}

	/**
	 * 根据微信签到
	 */
	public Map<String, Object> signInByOpenid(String openid) {
		Map<String, Object> result = new HashMap<String, Object>();
		User user = getUserByOpenid(openid);
		if (user == null) {
			result.put("code", 100);
			result.put("msg", "用户不存在");
			return result;
		} else {
			result = userSignInBusinessImpl.userSignInTodayByMobilePhone(
					user.getMobileNumber(), user.getPassword());
		}
		return result;
	}

	/**
	 * 获取用户积分
	 * @param openid
	 * @return
	 */
	public Map<String, Object>  getUserCredits(String openid){
		Map<String, Object> result = new HashMap<String, Object>();
		String userid = "";
		// 查询用户是否已经绑定了微信账号
		WeixinUser weixinUser = weixinUserBusinessImpl
				.getWeixinUserOpenid(openid);
		if (weixinUser != null && !weixinUser.equals("")) {
			userid = weixinUser.getUserid();
		}
		User user = userBusiness.findUserById(userid);
		if (user == null) {
			result.put("resCode", 100);
			result.put("resMsg", "用户不存在");
			return result;
		}else{
			// 账户积分
			result.put("credits", user.getCredits());
			return result;
		}
	}
	
	/**
	 * 获取用户余额
	 * @param openid
	 * @return
	 */
	public Map<String, Object>  getUserWallertAmount(String openid){
		Map<String, Object> result = new HashMap<String, Object>();
		String userid = "";
		// 查询用户是否已经绑定了微信账号
		WeixinUser weixinUser = weixinUserBusinessImpl
				.getWeixinUserOpenid(openid);
		if (weixinUser != null && !weixinUser.equals("")) {
			userid = weixinUser.getUserid();
		}
		User user = userBusiness.findUserById(userid);
		if (user == null) {
			result.put("resCode", 100);
			result.put("resMsg", "用户不存在");
			return result;
		}else{
			// 账户余额
			result.put("walletAmount",userWalletBusiness.getAmountByUserId(user.getId()).getAmount());
			return result;
		}
	}
	
	/**
	 * 获取账户收入
	 * @param openid
	 * @return
	 */
	public Map<String, Object> getUserDealDetailAmount(String openid) {
		Map<String, Object> result = new HashMap<String, Object>();
		String userid = "";
		// 查询用户是否已经绑定了微信账号
		WeixinUser weixinUser = weixinUserBusinessImpl
				.getWeixinUserOpenid(openid);
		if (weixinUser != null && !weixinUser.equals("")) {
			userid = weixinUser.getUserid();
		}
		User user = userBusiness.findUserById(userid);
		if (user == null) {
			result.put("resCode", 100);
			result.put("resMsg", "用户不存在");
			return result;
		} else {
			// 账户收入
			String dealDetailAmount = "0";
			if (dealDetailBusiness.getDealDetailAmountByUserId(user.getId()) == null) {
				dealDetailAmount = "0";
			} else {
				dealDetailAmount = String.valueOf(dealDetailBusiness
						.getDealDetailAmountByUserId(user.getId()));
			}
			result.put("dealDetailAmount", dealDetailAmount);
			return result;
		}
	}
	
	/**
	 * 获取预计收入
	 * @param openid
	 * @return
	 */
	public Map<String, Object> getUserGameBenefit(String openid) {
		Map<String, Object> result = new HashMap<String, Object>();
		String userid = "";
		// 查询用户是否已经绑定了微信账号
		WeixinUser weixinUser = weixinUserBusinessImpl
				.getWeixinUserOpenid(openid);
		if (weixinUser != null && !weixinUser.equals("")) {
			userid = weixinUser.getUserid();
		}
		User user = userBusiness.findUserById(userid);
		if (user == null) {
			result.put("resCode", 100);
			result.put("resMsg", "用户不存在");
			return result;
		} else {
			// 预计收入 and 当前投资金额（押金）
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("userid", user.getMobileNumber());
			UserGameRelation userGameRelation=gameBusinessImpl.getUserGameBenefitInfo(condition);
			result.put("gameBenefit",userGameRelation.getReward());
			result.put("gameDeposit",userGameRelation.getDeposit());
			return result;
		}
	}
	
	
	/**
	 *  查询推荐奖励
	 * @param openid
	 * @return
	 */
	public Map<String, Object> getUserInviteBenefit(String openid) {
		Map<String, Object> result = new HashMap<String, Object>();
		String userid = "";
		// 查询用户是否已经绑定了微信账号
		WeixinUser weixinUser = weixinUserBusinessImpl
				.getWeixinUserOpenid(openid);
		if (weixinUser != null && !weixinUser.equals("")) {
			userid = weixinUser.getUserid();
		}
		User user = userBusiness.findUserById(userid);
		if (user == null) {
			result.put("resCode", 100);
			result.put("resMsg", "用户不存在");
			return result;
		} else {
			// 查询推荐奖励
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("inviteUserId", user.getId());
			result.put("total",inviteBenefitBusinessImp.getInviteBenefitTotal(condition));
			return result;
		}
	}
	
	/**
	 *  查询签到奖励
	 * @param openid
	 * @return
	 */
	public Map<String, Object> getUserSignBenefit(String openid) {
		Map<String, Object> result = new HashMap<String, Object>();
		String userid = "";
		// 查询用户是否已经绑定了微信账号
		WeixinUser weixinUser = weixinUserBusinessImpl
				.getWeixinUserOpenid(openid);
		if (weixinUser != null && !weixinUser.equals("")) {
			userid = weixinUser.getUserid();
		}
		User user = userBusiness.findUserById(userid);
		if (user == null) {
			result.put("resCode", 100);
			result.put("resMsg", "用户不存在");
			return result;
		} else {
			// 查询推荐奖励
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("mobilePhone", user.getMobileNumber());
			result.put("signTotal", userSignInBusinessImpl.sumSignBenefitByMobileNumber(condition));
			return result;
		}
	}
	
	
//	/**
//	 * 根据openid获取蛙宝用户信息
//	 * 
//	 * @param openid
//	 * @return
//	 */
//	public Map<String, Object> getUserInfoByMobilePhone(String openid) {
//
//		Map<String, Object> result = new HashMap<String, Object>();
//		String userid = "";
//		// 查询用户是否已经绑定了微信账号
//		WeixinUser weixinUser = weixinUserBusinessImpl
//				.getWeixinUserOpenid(openid);
//		if (weixinUser != null && !weixinUser.equals("")) {
//			userid = weixinUser.getUserid();
//		}
//		User user = userBusiness.findUserById(userid);
//		if (user == null) {
//			result.put("resCode", 100);
//			result.put("resMsg", "用户不存在");
//			return result;
//		} else {
//			// 账户积分
//			result.put("credits", user.getCredits());
//			// 账户余额
//			result.put("walletAmount",
//					userWalletBusiness.getAmountByUserId(user.getId())
//							.getAmount());
//			// 账户收入
//			String dealDetailAmount="0";
//			if(dealDetailBusiness.getDealDetailAmountByUserId(user.getId())==null){
//				dealDetailAmount="0";
//			}else{
//				dealDetailAmount=String.valueOf(dealDetailBusiness.getDealDetailAmountByUserId(user.getId()));
//			}
//			result.put("dealDetailAmount",dealDetailAmount);
//			
//			// 预计收入
//			Map<String, Object> condition = new HashMap<String, Object>();
//			condition.put("userid", user.getMobileNumber());
//			result.put("gameBenefit",gameBusinessImpl.getUserGameBenefitInfo(condition).getReward());
//			// 用户当前投资金额（押金）
//			result.put("gameDeposit",gameBusinessImpl.getUserGameBenefitInfo(condition).getDeposit());
//			// 查询推荐奖励
//			condition.clear();
//			condition.put("inviteUserId", user.getId());
//			result.put("total",
//					inviteBenefitBusinessImp.getInviteBenefitTotal(condition));
//			// 查询签到奖励
//			condition.clear();
//			condition.put("mobilePhone", user.getMobileNumber());
//			result.put("signTotal", userSignInBusinessImpl
//					.sumSignBenefitByMobileNumber(condition));
//		}
//		return result;
//	}

}
