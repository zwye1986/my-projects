package com.venada.efinance.security;

import com.venada.efinance.business.UserBusiness;
import com.venada.efinance.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 加载用户数据
 * 
 * @author sunwen
 */
@Service
public class WowPowerUserDetailsService implements UserDetailsService {

	@Autowired
	private UserBusiness userBusiness;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userBusiness.findUserByMoblieNumberByLogin(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		return user;
	}

}