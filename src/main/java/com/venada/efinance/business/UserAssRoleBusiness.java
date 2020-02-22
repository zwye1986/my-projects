package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.UserAssRole;

import java.util.List;

public interface UserAssRoleBusiness {
	public void insertUserAssRole(List<UserAssRole> userAssRole)
			throws BusinessException;
}
