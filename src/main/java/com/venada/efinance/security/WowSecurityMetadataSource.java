package com.venada.efinance.security;

import com.venada.efinance.business.impl.RoleBusinessImpl;
import com.venada.efinance.pojo.Resource;
import com.venada.efinance.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class WowSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {

	private static LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> resourceMap = null; 
	
	@Autowired
	private  RoleBusinessImpl roleDaoImpl;
	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	
	
	public static LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> getResourceMap() {
		return resourceMap;
	}



	public static void setResourceMap(
			LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> resourceMap) {
		WowSecurityMetadataSource.resourceMap = resourceMap;
	}



	@Override
	//返回所请求资源所需要的权限  
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {  
          
        HttpServletRequest request = ((FilterInvocation) object).getRequest();  
        String requestUrl = ((FilterInvocation) object).getRequestUrl(); 
        if(null==resourceMap){  
            loadResourceDefine();  
        }  
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {  
            if (entry.getKey().matches(request)) {  
                return entry.getValue();  
            }  
        }  
        return  resourceMap.get(requestUrl); 
    }  

	@Override
	public boolean supports(Class<?> class1) {
		return true;
	}
	
	public  void loadResourceDefine(){  
        resourceMap = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();  
        Map<String, String> resource = getResource();  
        for(Map.Entry<String, String> entry:resource.entrySet()){  
            Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();  
            configAttributes.add(new SecurityConfig(entry.getValue()));  
            resourceMap.put(new AntPathRequestMatcher(entry.getKey()), configAttributes);  
        }  
          
    }  
	
	
	//加载所有资源与权限的关系  
    private Map<String, String> getResource() {  
        Map<String, String> resourceMap = new HashMap<String, String>();  

            for(Role role : roleDaoImpl.getAllRoleResource()) {  
                List<Resource> resources = role.getRoleResources();  
                for(Resource resource : resources) {  
                        String url = resource.getValue();  
                        if(!resourceMap.containsKey(url)) {  
                            resourceMap.put(url, role.getRoleName());  
                        }else{  
                            String roleName = resourceMap.get(url);  
                            resourceMap.put(url, roleName+","+role.getRoleName());  
                        }  
                }  
            }  
        return resourceMap;  
          
    }  
      

}
