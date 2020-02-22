package com.venada.efinance.mobile.controller;

import com.venada.efinance.business.BankBusiness;
import com.venada.efinance.business.BankCardBusiness;
import com.venada.efinance.business.UserBusiness;
import com.venada.efinance.pojo.BankCard;
import com.venada.efinance.pojo.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class BindUserCard {
	@Autowired
	private BankCardBusiness bankCardBusiness;
	
	@Autowired
	private BankBusiness bankBusiness;
	
	@Autowired
	private UserBusiness userBusiness;
	

	private static final Logger logger = LoggerFactory
			.getLogger(BindUserCard.class);
	
	@RequestMapping(value = "/{mobileNumber}/{password}/{provinceid}/{bankid}/{cardNumber}/{cardType}/{subBank}/{cityid}/dealBindCard", method = RequestMethod.POST)
	public @ResponseBody Object bindCard(@PathVariable String mobileNumber,@PathVariable String password,@PathVariable Integer provinceid,@PathVariable Integer bankid,@PathVariable String cardNumber,@PathVariable Integer cardType,@PathVariable Integer subBank,@PathVariable Integer cityid) {
		User user = userBusiness.findUserByMoblieNumberAndPasswordNotCoded(mobileNumber, password);
		Map<String,Object> resMap = new HashMap<String, Object>();
		if(user == null){
			resMap.put("resCode", "101");
			resMap.put("resMsg", "用户不存在或者密码错误！");
			return resMap;
		}
		List<BankCard> bankCards = bankCardBusiness
				.getAllByUserId(user.getId());
		if(bankCards.size() >= 5){
			resMap.put("resCode", "102");
			resMap.put("resMsg", "您最多可以绑定5张银行卡！");
			return resMap;
		}
		String name = user.getName();
		if (name == null || "".equals(name.replaceAll("\\s*", ""))) {
			resMap.put("resCode", "103");
			resMap.put("resMsg", "开户姓名不能为空，请完善个人资料");
			return resMap;
		}
		try {
			String id = UUID.randomUUID().toString().toUpperCase();
			BankCard bankCard = new BankCard();
			bankCard.setId(id);
			bankCard.setBankProvinceid(provinceid);
			bankCard.setBankCityid(cityid);
			bankCard.setBankid(bankid);
			bankCard.setCardNumber(cardNumber.replaceAll("\\s*", ""));
			bankCard.setCardType(cardType);
			bankCard.setBindUserId(user.getId());
			bankCard.setSubBank(subBank);
			bankCardBusiness.saveBankCard(bankCard);
		} catch (Exception e) {
			logger.error("手机端绑定银行卡失败："+e.getMessage());
			resMap.put("resCode", "104");
			resMap.put("resMsg", "手机端绑定银行卡失败!");
			return resMap;
		}
		
		resMap.put("resCode", "100");
		resMap.put("resMsg", "绑定银行卡成功！");
		return resMap;
	}
	
	@RequestMapping(value="/{mobileNumber}/{password}/getBindCards")
	@ResponseBody
	public Object getBindCardsByMobileNumberAndPassword(@PathVariable String mobileNumber,@PathVariable String password){
		User user = userBusiness.findUserByMoblieNumberAndPasswordNotCoded(mobileNumber, password);
		if(user == null){
			return null;
		}else{
			return bankCardBusiness.getAllByUserId(user.getId());
		}
	}
	
	
	@RequestMapping(value = "/mobileInterface/{cityid}/getBankJson")
	public @ResponseBody Object getBankJson(@PathVariable int cityid ) {
		return bankBusiness.findBankListByCityid(cityid);
	}
	
	@RequestMapping(value="/{mobileNumber}/{password}/{id}/delUserCardById")
	public @ResponseBody Object delUserCardById(@PathVariable String mobileNumber,@PathVariable String password,@PathVariable String id){
		Map<String,Object> resMap = new HashMap<String,Object>();
		User user = userBusiness.findUserByMoblieNumberAndPasswordNotCoded(mobileNumber, password);
		if(user == null){
			resMap.put("resCode", 0);
			resMap.put("resMsg", "删除失败，用户名或者密码错误！");
			logger.info("用户["+mobileNumber+"]删除银行卡失败,cardid="+id);
		}else{
			Map<String,String> map = new HashMap<String,String>();
			map.put("id", id);
			map.put("userid", user.getId());
			bankCardBusiness.delUserCardById(map);
			resMap.put("resCode", 1);
			resMap.put("resMsg", "删除成功！");
			logger.info("用户["+mobileNumber+"]删除银行卡成功,cardid="+id);
		}
		
		return resMap;
	} 
	
}
