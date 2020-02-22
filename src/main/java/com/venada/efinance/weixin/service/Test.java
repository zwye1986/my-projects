package com.venada.efinance.weixin.service;

import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		 ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
	                "applicationContext.xml");
	       CoreService coreSerivce =(CoreService) ctx.getBean("coreService");
	       long beginTime=System.currentTimeMillis();
	       Map<String, Object> result =coreSerivce.getUserCredits("oODFsuE0QZpS2gkfa0G3AEEFmTWs");
	       System.out.println("credits"+result.get("credits").toString());
	       System.out.println("dealDetailAmount"+coreSerivce.getUserDealDetailAmount("oODFsuE0QZpS2gkfa0G3AEEFmTWs").get("dealDetailAmount"));
	       System.out.println("gameBenefit"+coreSerivce.getUserGameBenefit("oODFsuE0QZpS2gkfa0G3AEEFmTWs").get("gameBenefit").toString());
	       System.out.println("gameDeposit"+coreSerivce.getUserGameBenefit("oODFsuE0QZpS2gkfa0G3AEEFmTWs").get("gameDeposit").toString());
	       System.out.println("total"+coreSerivce.getUserInviteBenefit("oODFsuE0QZpS2gkfa0G3AEEFmTWs").get("total").toString());
	       System.out.println("signTotal"+coreSerivce.getUserSignBenefit("oODFsuE0QZpS2gkfa0G3AEEFmTWs").get("signTotal").toString());
	       System.out.println("walletAmount"+coreSerivce.getUserWallertAmount("oODFsuE0QZpS2gkfa0G3AEEFmTWs").get("walletAmount").toString());

	       System.out.println(System.currentTimeMillis()-beginTime);
//	    // 账户积分
//			Integer credits = 0;
//			if (result.get("credits") == null) {
//				credits = 0;
//			} else {
//				credits = Integer.valueOf(result.get("credits")
//						.toString());
//			}
//
//			// 账户余额
//			String walletAmount = result.get("walletAmount")
//					.toString();
//			// 账户收入
//			String dealDetailAmount = result.get(
//					"dealDetailAmount").toString();
//			// 用户当前投资金额（押金）
//			String gameDeposit = result.get("gameDeposit")
//					.toString();
//			// 预计收入
//			String gameBenefit = result.get("gameBenefit")
//					.toString();
//			// 查询推荐奖励
//			String total = result.get("total").toString();
//			// 查询签到奖励
//			String signTotal = result.get("signTotal")
//					.toString();
	}

}
