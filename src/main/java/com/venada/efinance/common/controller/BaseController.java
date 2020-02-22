package com.venada.efinance.common.controller;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class BaseController {
	
	/**
	 * 封装请求中的需要的参数，规则 参数名 =$.XX 
	 * @param request
	 * @return
	 */
	@SuppressWarnings({"rawtypes"})
	public Map<String,Object> setMapCondition(HttpServletRequest request){
		Map<String,Object> condition = new HashMap<String, Object>();
		Enumeration paraNames = request.getParameterNames();
		while (paraNames.hasMoreElements()) {
			String paraName = (String) paraNames.nextElement();
			// 是filter参数
			if (paraName.charAt(0) == '$') {
				String paraValue = request.getParameter(paraName);
				paraValue = StringUtils.trimToEmpty(paraValue);
				if(StringUtils.isNotEmpty(paraValue)){
					condition.put(paraName.substring(2), paraValue);
				}
			}
		}
		return condition;
		
	}

}
