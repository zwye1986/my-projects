package com.venada.efinance.security;

import com.venada.efinance.business.*;
import com.venada.efinance.pojo.LoginLog;
import com.venada.efinance.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

public class LstpAuthenticationSuccessHandler implements
		AuthenticationSuccessHandler {

	@Autowired
	private UserBusiness userBusiness;
	@Autowired
	private SecurityCenterBusiness securityCenterBusiness;
	@Autowired
	private SecurityQuestionBusiness securityQuestionBusiness;
	@Autowired
	private LoginBussiness loginBussiness;
	@Autowired
	private ValidateCodeBusiness validateCodeBusiness;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		User user = SpringSecurityUtil.getCurrentUser();

		LoginLog loginLog = new LoginLog();
		loginLog.setId(UUID.randomUUID().toString());
		loginLog.setIp(request.getRemoteAddr());
		loginLog.setMobileNumber(user.getMobileNumber());
		loginLog.setUserid(user.getId());
		loginLog.setClient(1);
		loginBussiness.setUserCredits(user, loginLog);
		HttpSession session = request.getSession();
		String url = (String) session.getAttribute("urlbefore");
		if (url != null) {
			if (url.indexOf("order/manager") != -1 ) { 
				response.sendRedirect(url);
			} else {
				response.sendRedirect(request.getContextPath() + "/index.html");
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/index.html");
		}
		return;
	}
}
