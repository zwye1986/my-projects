package com.venada.efinance.business.impl;

import com.venada.efinance.business.UserAssRoleBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.UserAssRole;
import com.venada.efinance.service.UserAssRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * @author hepei
 *
 */
@Service
public class UserAssRoleBusinessImpl implements UserAssRoleBusiness {
	
	@Autowired
	private UserAssRoleService userAssRoleServiceImpl;

	public void insertUserAssRole(List<UserAssRole> userAssRole)
			throws BusinessException {
		userAssRoleServiceImpl.batchAddObject("insertUserAssRole", userAssRole);
	}
}
