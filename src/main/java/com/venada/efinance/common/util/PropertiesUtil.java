package com.venada.efinance.common.util;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author yma
 * 获取资源文件工具类
 */
public class PropertiesUtil{
	 
  public static ResourceBundle rb;
  
 
  
  public static String getProperty(String propertiesFileName, String key) {  
      Properties props = new Properties();  
      try {  
          props.load(PropertiesUtil.class.getResourceAsStream("/" + propertiesFileName));  
      } catch (IOException e) {  
          e.printStackTrace();  
      }  
      return (String) props.get(key);  
  }  
  
  public static String getProperty(String keyName){
	  if(rb == null){
		  rb = ResourceBundle.getBundle("exception", Locale.getDefault());
	  }
	  return rb.getString(keyName); 
  }
  
}

