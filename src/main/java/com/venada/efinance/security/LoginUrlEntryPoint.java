package com.venada.efinance.security;

import com.venada.efinance.pojo.User;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Service
public class LoginUrlEntryPoint implements AuthenticationEntryPoint {

	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		String targetUrl = null;
		String url = request.getRequestURL().toString();
//		String target=request.getParameter("target");
		User u =SpringSecurityUtil.getCurrentUser();
		//判断输入地址包含order/manager 并且未登录 将登录地址放入到session，现跳转到登录页面，在登录之后跳转
		if(u==null){
			if (url.indexOf("order/manager") != -1 ) { 
				request.getSession().setAttribute("urlbefore", url); 
				targetUrl= request.getContextPath()+"/login.html" ;
			}else{
				targetUrl=request.getContextPath()+"/login.html";
			}
		}else{
			targetUrl=url;
		}
		response.sendRedirect(targetUrl);
	}
}