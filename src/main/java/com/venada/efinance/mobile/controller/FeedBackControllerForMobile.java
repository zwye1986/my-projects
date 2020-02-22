package com.venada.efinance.mobile.controller;

import com.venada.efinance.business.AdviceBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.Advice;
import com.venada.efinance.util.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class FeedBackControllerForMobile {
	@Autowired
	private AdviceBusiness adviceBusiness;
	
	@RequestMapping(value = "/addFeedBack"  )

	public @ResponseBody Object dealFeedBack(HttpServletRequest request){
		String name = request.getParameter("name");
		String advice = request.getParameter("advice");
		String contact = request.getParameter("contact");
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		if(name == null || "".equals(name.replaceAll("\\s*", ""))){
			resultMap.put("code", "0");
			resultMap.put("meg", "姓名不能为空！");
			return resultMap;
		}else{
			name = name.replaceAll("\\s*", "");
		}
		
		if(advice == null || "".equals(advice.replaceAll("\\s*", ""))){
			resultMap.put("code", "0");
			resultMap.put("meg", "请填写建议内容！");
			return resultMap;
		}else{
			advice = advice.replaceAll("\\s*", "");
			advice = SystemUtils.StringFilter(advice);
			if(advice.length() > 200){
				resultMap.put("code", "0");
				resultMap.put("meg", "建议内容不能超过200个字符！");
				return resultMap;
			}
		}
		
		if(contact == null || "".equals(contact.replaceAll("\\s*", ""))){
			resultMap.put("code", "0");
			resultMap.put("meg", "联系方式不能为空！");
			return resultMap;
		}else{
			contact = contact.replaceAll("\\s*", "");
			if(contact.length() < 7 || contact.length() > 13){
				resultMap.put("code", "0");
				resultMap.put("meg", "请确填写联系方式！");
				return resultMap;
			}
		}
		
		Advice useradvice = new Advice();
		useradvice.setId(UUID.randomUUID().toString());
		useradvice.setAdvice(advice);
		useradvice.setContact(contact);
		useradvice.setName(name);
        try {
            adviceBusiness.saveAdvice(useradvice);
        } catch (BusinessException e) {
            e.printStackTrace();
            resultMap.put("code", "0");
            resultMap.put("meg", "系统出错！");
            return resultMap;
        }
        resultMap.put("code", "1");
		resultMap.put("meg", "操作成功！");
		return resultMap;
	}
}
