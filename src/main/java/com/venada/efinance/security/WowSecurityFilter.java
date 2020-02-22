package com.venada.efinance.security;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import javax.servlet.*;
import java.io.IOException;

public class WowSecurityFilter extends AbstractSecurityInterceptor implements Filter{

	private FilterInvocationSecurityMetadataSource securityMetadataSource;  
	  
    @Override  
    public SecurityMetadataSource obtainSecurityMetadataSource() {  
        return this.securityMetadataSource;  
    }  
  
    public void doFilter(ServletRequest request, ServletResponse response,  
            FilterChain chain) throws IOException, ServletException {  
        FilterInvocation fi = new FilterInvocation(request, response, chain);  
        invoke(fi);  
    }  
      
    private void invoke(FilterInvocation fi) throws IOException, ServletException {  
        InterceptorStatusToken token = null;  
        token = super.beforeInvocation(fi);  
        try {  
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());  
        } finally {  
            super.afterInvocation(token, null);  
        }  
    }  
  
    public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {  
        return securityMetadataSource;  
    }  
  
    public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource) {  
        this.securityMetadataSource = securityMetadataSource;  
    }  
      
    public void init(FilterConfig arg0) throws ServletException {  
    }  
      
    public void destroy() {  
          
    }  
  
    @Override  
    public Class<? extends Object> getSecureObjectClass() {  
        //下面的MyAccessDecisionManager的supports方面必须放回true,否则会提醒类型错误  
        return FilterInvocation.class;  
    }

}
