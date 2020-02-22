package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.Role;

import java.util.List;
import java.util.Map;

public interface RoleBusiness {

	public Role selectRoleUser(Integer id) throws BusinessException;

	public Role selectRole(Integer id) throws BusinessException;

	public Role getRoleResouces(Integer id) throws BusinessException;

	public List<Role> getAllRoleResource() throws BusinessException;

	public void deleteResourceByRoleId(String Id) throws BusinessException;

	public void deleteUserAssRoleByRoleId(String roleId)
			throws BusinessException;

	public void addRole(Role r) throws BusinessException;

	public void updateRole(Role r) throws BusinessException;

	public List<Role> queryRole(Map<String, Object> condition,
			PaginationMore page) throws BusinessException;

	public Integer getRoleDetailsCount(Map<String, Object> condition);

	public void deleteRole(String Id) throws BusinessException;

	public List<Role> getRoleById(Integer Id) throws BusinessException;

	public List<Role> findRoleListByName(String value) throws BusinessException;

	public List<Role> getRoleByUserId(String Id) throws BusinessException;

	public List<Role> queryAllRole() throws BusinessException;

}
