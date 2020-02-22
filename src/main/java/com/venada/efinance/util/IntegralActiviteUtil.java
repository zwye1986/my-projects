package com.venada.efinance.util;

import com.venada.efinance.common.util.PropertiesUtil;

import java.util.HashMap;
import java.util.Map;

public class IntegralActiviteUtil {
	
	public  static Map<String,Object> IntegralActive=new HashMap<String,Object>();
	
	public static Map<String,Object> getInegralActive(){
		if(IntegralActive.isEmpty()){
			Boolean activteFlag=Boolean.valueOf(PropertiesUtil.getProperty("Integral_activities.properties", "activteFlag"));
			String activeBeginTime=PropertiesUtil.getProperty("Integral_activities.properties", "activeBeginTime");
			String activeEndTime=PropertiesUtil.getProperty("Integral_activities.properties", "activeEndTime");
			IntegralActive.put("activteFlag", activteFlag);
			IntegralActive.put("activeBeginTime", activeBeginTime);
			IntegralActive.put("activeEndTime", activeEndTime);
		}
		return IntegralActive;
	}
}
