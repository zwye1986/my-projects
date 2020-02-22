package com.venada.efinance.controller;

import com.venada.efinance.business.ExchangeCodeBussiness;
import com.venada.efinance.pojo.ExchangeCode;
import com.venada.efinance.pojo.ExchangeRecord;
import com.venada.efinance.pojo.User;
import com.venada.efinance.security.SpringSecurityUtil;
import com.venada.efinance.util.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ExchangeController {
	@Autowired
	private ExchangeCodeBussiness exchangeCodeBusinessImpl;
	
	@RequestMapping("exchange.html")
       public @ResponseBody int exchange(HttpServletRequest request){
		   String exchangeCode = request.getParameter("exchangeCode");
		   exchangeCode = SystemUtils.transfer(exchangeCode);
		   User user = SpringSecurityUtil.getCurrentUser();
			if(user == null){
				return -1;  //用户未登录
			}else{
				final Integer s = 0;
				synchronized (s) {
				   try{
					   List<ExchangeRecord> list = exchangeCodeBusinessImpl.queryExchangeRecord(user.getId());
					   if(list.size() > 0){
						   return 1; //用户已经使用过兑换码
					   }
					   ExchangeCode code = exchangeCodeBusinessImpl.getExchangecode(exchangeCode);
					   if(code == null){
						   return 2;//该兑换码无效或已经被使用
					   }
					   exchangeCodeBusinessImpl.exchange(user.getId(), exchangeCode);
					   return 0; //成功
					   
				   }catch(Exception e){
					   return 3;
				   }
				}
			}
    	  
       }
}
