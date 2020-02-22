package com.venada.efinance.business.impl;

import com.venada.efinance.business.RoleBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.Role;
import com.venada.efinance.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleBusinessImpl implements RoleBusiness {
	@Autowired
	private RoleService roleServiceImpl;

	public Role selectRoleUser(Integer id) throws BusinessException{
		return (Role) roleServiceImpl.getObject("selectRoleUser", id);
	}

	public Role selectRole(Integer id)throws BusinessException {
		return (Role) roleServiceImpl.getObject("selectRole", id);
	}
	
	public Role getRoleResouces(Integer id) throws BusinessException{
		return (Role) roleServiceImpl.getObject("getRoleResouces", id);
	}

	public List<Role> getAllRoleResource() throws BusinessException {
		return (List<Role>) roleServiceImpl.findObjects("getAllRoleResource", null);
	}
	
	public void deleteResourceByRoleId(String Id) throws BusinessException {
		roleServiceImpl.deleteObject("deleteResourceByRoleId", Id);
	}
	
	public void deleteUserAssRoleByRoleId(String roleId) throws BusinessException {
		roleServiceImpl.deleteObject("deleteUserAssRoleByRoleId", roleId);
	}
	
	public void addRole(Role r) throws BusinessException {
		roleServiceImpl.saveObject("addRole", r);
	}

	public void updateRole(Role r) throws BusinessException {
		roleServiceImpl.updateObject("updateRole", r);
	}

	public List<Role> queryRole(Map<String, Object> condition,
			PaginationMore page) throws BusinessException {
		page.setTotalRows(getRoleDetailsCount(condition));
		page.repaginate();
		return roleServiceImpl.selectList("queryRole", condition, page);
	}

	public Integer getRoleDetailsCount(Map<String, Object> condition) {
		return (Integer) roleServiceImpl.getObject("getRoleDetailsCount", condition);
	}

	public void deleteRole(String Id) throws BusinessException {
		roleServiceImpl.deleteObject("deleteRoleById", Id);
	}

	public List<Role> getRoleById(Integer Id) throws BusinessException {
		List<Role> roleList = new ArrayList<Role>();
		Map<String, Object> condition=new HashMap<String,Object>();
		condition.put("Id", Id);
		roleList = roleServiceImpl.findObjects("getRoleByRoleId", condition);
		return roleList;
	}
	
	public List<Role> findRoleListByName(String value) throws BusinessException{
		List<Role> roleList = new ArrayList<Role>();
		Map<String, Object> condition=new HashMap<String,Object>();
		condition.put("value", value);
		roleList = roleServiceImpl.findObjects("findRoleListByName", condition);
		return roleList;
	}
	
	public List<Role> getRoleByUserId(String Id) throws BusinessException {
		List<Role> roleList = new ArrayList<Role>();
		Map<String, Object> condition=new HashMap<String,Object>();
		condition.put("id", Id);
		roleList =roleServiceImpl.findObjects("getRoleById", condition);
		return roleList;
	}

	public List<Role> queryAllRole() throws BusinessException {
		return roleServiceImpl.findObjects("queryRole",null);
	}

}
