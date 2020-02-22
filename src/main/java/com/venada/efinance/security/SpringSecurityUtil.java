package com.venada.efinance.security;

import com.venada.efinance.pojo.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * SpringSecurity的工具类.
 */
public class SpringSecurityUtil {

	/**
	 * 取得当前用户的登录名,如果当前用户未登录则返回空字符串.
	 */
	public static String getCurrentUserName() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth == null)
			return "";
		return auth.getName();
	}

	/**
	 * 取得当前用户,返回值为SpringSecurity的User类及其子类, 如果当前用户未登录则返回null.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends User> T getCurrentUser() {
		User user = null;
		SecurityContext ctx = SecurityContextHolder.getContext();
		if (ctx != null) {
			Authentication auth = ctx.getAuthentication();
			if (auth != null) {
				Object principal = auth.getPrincipal();
				if (principal != null && principal instanceof UserDetails) {
					user = (User) principal;
				}
			}
		}
		return (T) user;
	}

	/**
	 * 取得当前用户的登录名,如果当前用户未登录则返回null.
	 */
	public static String getCurrUsername() {
		String userName = "";
		SecurityContext ctx = SecurityContextHolder.getContext();

		if (ctx != null) {
			Authentication auth = ctx.getAuthentication();
			if (auth != null) {
				Object principal = auth.getPrincipal();
				if (principal != null && principal instanceof UserDetails) {
					userName = ((UserDetails) principal).getUsername();
				}
			}
		}

		return userName;
	}
	
	public static void destroyUser(){
		SecurityContext ctx = SecurityContextHolder.getContext(); 
		if (ctx != null) {
			Authentication auth = ctx.getAuthentication();
			if (auth != null) {
				Object principal = auth.getPrincipal();
				auth.setAuthenticated(false);
			}
		}
	}
}