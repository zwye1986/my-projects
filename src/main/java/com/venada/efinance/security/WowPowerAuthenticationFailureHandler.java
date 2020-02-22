package com.venada.efinance.security;

import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登陆失败处理
 * 
 * @author sunwen
 */
public class WowPowerAuthenticationFailureHandler implements
		AuthenticationFailureHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	private String redirectUrl = "/login.html";

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		HttpSession httpSession = request.getSession(false);

		String errMsg = "";

		if (exception instanceof BadCredentialsException) {
			errMsg = "密码有问题.";
		} else if (exception instanceof UsernameNotFoundException) {
			errMsg = "账号有误.";
		} else if (exception instanceof AccountStatusException) {
			errMsg = "账户状态不正常.";
		}

		httpSession
				.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errMsg);
		redirectStrategy.sendRedirect(request, response, redirectUrl);
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

}