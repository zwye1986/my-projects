package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.RoleAssResource;

import java.util.List;

public interface RoleAssResourceBusiness {

	public void insertRoleAssResorcer(List<RoleAssResource> roleAssResource)
			throws BusinessException ;
}
