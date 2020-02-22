package com.venada.efinance.business.impl;

import com.venada.efinance.business.RoleAssResourceBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.RoleAssResource;
import com.venada.efinance.service.RoleAssResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 
 * @author hepei
 *
 */
@Service
public class RoleAssResourceBusinessImpl implements RoleAssResourceBusiness {

	@Autowired
	private RoleAssResourceService roleAssResourceServiceImpl;
	public void insertRoleAssResorcer(List<RoleAssResource> roleAssResource)
			throws BusinessException {
		roleAssResourceServiceImpl.batchAddObject("insertRoleAssResorcer", roleAssResource);
	}
}
