package com.venada.efinance.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecimalUtil
{
 
  public static BigDecimal round(BigDecimal source, int scale)
  {
    if (source == null)
      return null;
    else
      return source.setScale(scale, BigDecimal.ROUND_HALF_UP);
  }
  
  public static BigDecimal round(double dSource, int scale)
  {
    return round(new BigDecimal(Double.toString(dSource)), scale);
  }
  
  public static BigDecimal add(BigDecimal bd1, BigDecimal bd2)
  {
    if (bd1 == null && bd2 == null)
    {
      return new BigDecimal("0");
    }
    else if (bd1 == null && bd2 != null)
    {
      return bd2;
    }
    else if (bd1 != null && bd2 == null)
    {
      return bd1;
    }
    
    return bd1.add(bd2);
  }
  
  public static BigDecimal subtract(BigDecimal bd1, BigDecimal bd2)
  {
    if (bd1 == null && bd2 == null)
    {
      return new BigDecimal("0");
    }
    else if (bd2 == null && bd1 != null)
    {
      return bd1;
    }
    else if (bd1 == null && bd2 != null)
    {
      return multiply(new BigDecimal(-1), bd2);
    }
    
    return bd1.subtract(bd2);
  }
  
  public static BigDecimal multiply(BigDecimal bd1, BigDecimal bd2)
  {
    if (bd1 == null || bd2 == null)
    {
      return new BigDecimal("0");
    }
    
    return bd1.multiply(bd2);
  }
  
  public static BigDecimal multiply(BigDecimal bd1, BigDecimal bd2, int nScale)
  {
    if (bd1 == null || bd2 == null)
    {
      return new BigDecimal("0");
    }
    
    return round(bd1.multiply(bd2), nScale);
  }
  
  public static BigDecimal divide(BigDecimal bd1, BigDecimal bd2, int nScale)
  {
    if (bd1 == null || bd2 == null)
    {
      return new BigDecimal("0");
    }
    else if (bd2.compareTo(new BigDecimal("0")) == 0)
    {
      return new BigDecimal("0");
    }
    
    return bd1.divide(bd2, nScale,BigDecimal.ROUND_HALF_UP);
  }
  
  public static String formatDecimal(BigDecimal bd)
  {
		DecimalFormat df = new DecimalFormat("#0.########");
		return df.format(bd);
  }
  
  public static String formatDecimal_1(BigDecimal bd)
  {
		DecimalFormat df = new DecimalFormat("#0.####");
		return df.format(bd);
  }
  
  public static BigDecimal divide(BigDecimal bd1, BigDecimal bd2)
  {
    if (bd1 == null || bd2 == null)
    {
      return new BigDecimal("0");
    }
    else if (bd2.compareTo(new BigDecimal("0")) == 0)
    {
      return new BigDecimal("0");
    }
    
    return bd1.divide(bd2, 6, BigDecimal.ROUND_HALF_UP);
  }
  
  public static boolean isZero(BigDecimal bd)
  {
    boolean b = true;
    if (bd == null || bd.doubleValue()==0)
      b = true;
    else
      b = false;
    return b;
  }
  
  public static boolean isEquals(BigDecimal bd1, BigDecimal bd2)
  {
    BigDecimal temp = subtract(bd1, bd2);
    
    return isZero(temp);
  }
  
  public static double getDoubleValue(BigDecimal bd)
  {
    if (bd != null)
      return bd.doubleValue();
    else
      return 0;
  }
  
  public static String formatDecimal(double bd)
  {
		DecimalFormat df = new DecimalFormat("#0.########");
		return df.format(bd);
  }
  /**   
   * @param regex 正则表达式字符串   
   * @param str 要匹配的字符串   
   * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;   
   */   
  public static boolean match(String regex, String str)    
  {    
      Pattern pattern = Pattern.compile(regex);    
      Matcher matcher = pattern.matcher(str);    
      return matcher.matches();    
  }  
  
  /**
   * 产生随机六位数的方法
   * @return
   */
	public static String getNumber6FromRandom(){
		  Random r = new Random();
		  int xx = r.nextInt(1000000);
		  while(xx<100000){
		   xx = r.nextInt(1000000);
		  }
		  return String.valueOf(xx);
		 }
}
