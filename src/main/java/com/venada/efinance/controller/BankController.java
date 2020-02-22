package com.venada.efinance.controller;

import com.venada.efinance.business.*;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.*;
import com.venada.efinance.security.SpringSecurityUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@Controller
public class BankController {
	private static final Logger logger = LoggerFactory.getLogger(BankController.class);
	@Autowired
	private CityBusiness cityBusiness;
	@Autowired
	private BankBusiness bankBusiness;
	@Autowired
	private BranchBankBusiness branchBankBusiness;
	
	@Autowired
	private BankCardBusiness bankCardBusiness;
	
	@Autowired
	private UserBusiness userBusiness;
	@ResponseBody
	@RequestMapping(value = "/{provinceid}/getBankCityJson", method = { RequestMethod.POST })
	public List<BankCity> getCityJson(@PathVariable int provinceid) {
		List<BankCity> cities = null;
		try {
			cities = cityBusiness.findBankCityListByProvinceid(provinceid);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage());
		}
		return cities;
	}
	
	@RequestMapping(value = "/{cityid}/getBankJson")
	public @ResponseBody  Object getBankJson(@PathVariable int cityid ) {
		List<Bank> banks = bankBusiness.findBankListByCityid(cityid);
		return banks;
	}
	
	@ResponseBody
	@RequestMapping(value = "/{bankId}/{cityid}/getSubBankJson", method = { RequestMethod.POST })
	public List<BranchBank> getSubBankJson(@PathVariable int bankId,@PathVariable int cityid) {
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("bankid", bankId);
		map.put("cityid", cityid);
		return branchBankBusiness.queryBranchBankByCityidAndBankid(map);
	}
	
    @RequestMapping(value = "/user/manager/dealBindCard", method = RequestMethod.POST)
    public @ResponseBody
    Object bindCard(HttpServletRequest request) {
        User user = SpringSecurityUtil.getCurrentUser();
        // 查询银行卡绑定信息
        List<BankCard> bankCards = bankCardBusiness
                .getAllByUserId(user.getId());
        Map<String,Object> resultMap = new HashMap<String,Object>();
        if (bankCards.size() >= 5) {
            resultMap.put("resCode", 0);
            resultMap.put("resMsg", "您最多可以绑定5张银行卡！");
            return resultMap;
        }
        String name = userBusiness.findUserByMoblieNumber(
                user.getMobileNumber()).getName();
        if (name == null || "".equals(name.replaceAll("\\s*", ""))) {
            resultMap.put("resCode", 0);
            resultMap.put("resMsg", "开户姓名不能为空，请完善个人资料！");
            return resultMap;
        }
        String id = UUID.randomUUID().toString().toUpperCase();

        String provinceid = request.getParameter("provinceid");
        if (provinceid == null || "".equals(provinceid.replaceAll("\\s*", ""))
                || "0".equals(provinceid.replaceAll("\\s*", ""))) {
            resultMap.put("resCode", 0);
            resultMap.put("resMsg", "请选择开户银行所在省份！");
            return resultMap;
        }

        String cityid = request.getParameter("cityid");
        if (cityid == null || "".equals(cityid.replaceAll("\\s*", ""))
                || "0".equals(cityid.replaceAll("\\s*", ""))) {
            resultMap.put("resCode", 0);
            resultMap.put("resMsg", "请选择开户银行所在城市！");
            return resultMap;
        }

        String bankid = request.getParameter("bankid");

        if (bankid == null || "".equals(bankid.replaceAll("\\s*", ""))
                || "0".equals(bankid.replaceAll("\\s*", ""))) {
            resultMap.put("resCode", 0);
            resultMap.put("resMsg", "请选择银行！");
            return resultMap;
        }

        String subBank = request.getParameter("subBankid");

        if (subBank == null || "0".equals(subBank.replaceAll("\\s*", ""))
                || "".equals(subBank.replaceAll("\\s*", ""))) {
            resultMap.put("resCode", 0);
            resultMap.put("resMsg", "请正确填写开户支行！");
            return resultMap;
        }

        String cardNumber = request.getParameter("cardNumber");

        if (cardNumber == null || "".equals(cardNumber.replaceAll("\\s*", ""))
                || cardNumber.length() > 20
                || !StringUtils.isNumeric(cardNumber)) {
            resultMap.put("resCode", 0);
            resultMap.put("resMsg", "请填写正确填写银行卡号！");
            return resultMap;
        }

        String cardType = request.getParameter("cardType");
        if (cardType == null || "".equals(cardType.replaceAll("\\s*", ""))) {
            resultMap.put("resCode", 0);
            resultMap.put("resMsg", "请选择银行卡种类！");
            return resultMap;
        }

        BankCard bankCard = new BankCard();
        bankCard.setId(id);
        bankCard.setBankProvinceid(Integer.parseInt(provinceid.replaceAll(
                "\\s*", "")));
        bankCard.setBankCityid(Integer.parseInt(cityid.replaceAll("\\s*", "")));
        bankCard.setBankid(Integer.parseInt(bankid.replaceAll("\\s*", "")));
        bankCard.setCardNumber(cardNumber.replaceAll("\\s*", ""));
        bankCard.setCardType(Integer.parseInt(cardType.replaceAll("\\s*", "")));
        bankCard.setBindUserId(user.getId());
        bankCard.setSubBank(Integer.parseInt(subBank.replaceAll("\\s*", "")));
        try {
            bankCardBusiness.saveBankCard(bankCard);
        } catch (BusinessException e) {
            logger.error(e.getMessage());
            resultMap.put("resCode", 0);
            resultMap.put("resMsg", "操作失败！网络异常，请重试。");
            return resultMap;
        }
        resultMap.put("resCode", 1);
        resultMap.put("resMsg", "操作成功！");
        return resultMap;
    }
	@RequestMapping(value="/user/manager/{id}/delUserCardById")
	public String delUserCardById(@PathVariable String id){
		User user = SpringSecurityUtil.getCurrentUser();
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("userid", user.getId());
		bankCardBusiness.delUserCardById(map);
		return "forward:/user/manager/bindCard";
	} 
}
