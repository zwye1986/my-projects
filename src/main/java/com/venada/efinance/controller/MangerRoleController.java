package com.venada.efinance.controller;

import com.venada.efinance.business.ResourceBusiness;
import com.venada.efinance.business.RoleBusiness;
import com.venada.efinance.business.impl.RoleAssResourceBusinessImpl;
import com.venada.efinance.common.controller.BaseController;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.Resource;
import com.venada.efinance.pojo.Role;
import com.venada.efinance.pojo.RoleAssResource;
import com.venada.efinance.security.SpringSecurityUtil;
import com.venada.efinance.security.WowSecurityMetadataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/manager")
public class MangerRoleController extends BaseController {

	@Autowired
	private RoleBusiness roleBusinessImpl;
	@Autowired
	private ResourceBusiness resourceBusinessImpl;
	@Autowired
	private RoleAssResourceBusinessImpl roleAssResourceDaoImpl;
	@Autowired 
	private WowSecurityMetadataSource mySecurityMetadataSource;

	List<Resource> treelist = new ArrayList<Resource>();
	
	@RequestMapping("/roleList")
	public String roleList(PaginationMore page, Model model,
			HttpServletRequest request) {
		Map<String, Object> condition = setMapCondition(request);
		List<Role> roleList = new ArrayList<Role>();
		page.setPageSize(10);
		roleList = roleBusinessImpl.queryRole(condition, page);
		for (Role r:roleList){
			r.setResSel(getResouceNames(r));
		}
		model.addAttribute("roleList", roleList);
		model.addAttribute("page", page);
		return "/manager/rolemanager/roleManagerlist";
	}
	
	

	@RequestMapping(value = "/deleteRole")
	@ResponseBody
	@Transactional
	public Map<String, Object> deleteRole(@RequestParam(value = "roleId", required = false) String roleId) {
		Map<String, Object> data = new HashMap<String, Object>();
			try {
				roleBusinessImpl.deleteRole(roleId);
				roleBusinessImpl.deleteResourceByRoleId(roleId);
				roleBusinessImpl.deleteUserAssRoleByRoleId(roleId);
				data.put("msgcode", "true");
				data.put("msg", "角色删除成功");
			} catch (Exception e) {
				data.put("msgcode", "false");
				data.put("msg", "角色删除失败");
			}
		
		return data;
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/saveRole")
	@ResponseBody
	@Transactional
	public Map<String, Object> saveRole(Role u) {
		Map<String, Object> data = new HashMap<String, Object>();
		String[] resSelect = u.getResSel().split(",");
		List<RoleAssResource> roleAssResList = new ArrayList<RoleAssResource>();
		if (u.getRoleName() != null) {
			if (roleBusinessImpl.findRoleListByName(u.getRoleName()).isEmpty()) {
				u.setCreateTime(new Date());
				u.setCreateBy(SpringSecurityUtil.getCurrUsername());
				try {
					roleBusinessImpl.addRole(u);
					if (resSelect.length > 0 && resSelect[0] != ""&& resSelect[0].length() > 0) {
						for (String resource : resSelect) {
							Integer rid = resourceBusinessImpl.findResourceListByName(resource).get(0).getId();
							RoleAssResource r = new RoleAssResource();
							r.setRoleId(u.getId());
							r.setResourceId(rid);
							roleAssResList.add(r);
						}
						roleAssResourceDaoImpl.insertRoleAssResorcer(roleAssResList);
						mySecurityMetadataSource.setResourceMap(null);
						mySecurityMetadataSource.loadResourceDefine();
					}
					data.put("msgcode", "true");
					data.put("msg", "角色创建成功");
				} catch (Exception e) {
					data.put("msgcode", "false");
					data.put("msg", "角色创建失败");
				}
			} else {
				data.put("msgcode", "false");
				data.put("msg", "角色已经存在");
			}
		} else {
			data.put("msgcode", "false");
			data.put("msg", "角色名为空");
		}
		return data;
	}

	@RequestMapping(value = { "/roleExist" })
	@ResponseBody
	public Map<String, Object> roleExist(
			@RequestParam(value = "roleName", required = false) String roleName) {
		List<Role> resourceList = roleBusinessImpl.findRoleListByName(roleName);
		Map<String, Object> data = new HashMap<String, Object>();
		if (resourceList.isEmpty()) {
			data.put("isExists", "false");
		} else {
			data.put("isExists", "true");
		}
		return data;
	}

	@RequestMapping(value = { "/getRoleById" })
	@ResponseBody
	public Object getRoleById(
			@RequestParam(value = "Id", required = false) Integer Id) {
		Role role = roleBusinessImpl.getRoleResouces(Id);
		Map<String, Object> data = new HashMap<String, Object>();
		String resourceName="";
		if (role==null||role.equals("")){
			data.put("msgCode", "false");
		} else {
			resourceName=getResouceNames(role);
			data.put("msgCode", "true");
			data.put("id",role.getId());
			data.put("value",role.getRoleName());
			data.put("modelName",role.getDESCRIPTION());
			data.put("resourceName", resourceName);
		}
		return data;
	}
	
	public String getResouceNames(Role role) {
		String resourceName = "";
		List<Resource> reslist = role.getRoleResources();
		if ((reslist == null) || (reslist.isEmpty())) {
			
		} else {
			for (int i = 0; i < reslist.size(); i++) {
				if (i == reslist.size() - 1) {
					resourceName += reslist.get(i).getModelName();
				} else {
					resourceName += reslist.get(i).getModelName() + ",";
				}
			}
		}
		return resourceName;
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/updateRole")
	@ResponseBody
	@Transactional
	public Map<String, Object> updateRole(Role u) {
		Map<String, Object> data = new HashMap<String, Object>();
		String[] resSelect = u.getResSel().split(",");
		List<RoleAssResource> roleAssResList = new ArrayList<RoleAssResource>();
		if (u.getRoleName() != null) {
			u.setId(u.getId());
			u.setLastUpdateTime(new Date());
			u.setLastUpdateBy(SpringSecurityUtil.getCurrUsername());
			try {
				roleBusinessImpl.deleteResourceByRoleId(String.valueOf(u.getId()));
				roleBusinessImpl.updateRole(u);
				if (resSelect.length > 0 && resSelect[0] != ""&& resSelect[0].length() > 0) {
					for (String resource : resSelect) {
						Integer rid = resourceBusinessImpl.findResourceListByName(resource).get(0).getId();
						RoleAssResource r = new RoleAssResource();
						r.setRoleId(u.getId());
						r.setResourceId(rid);
						roleAssResList.add(r);
					}
					roleAssResourceDaoImpl.insertRoleAssResorcer(roleAssResList);
					mySecurityMetadataSource.setResourceMap(null);
					mySecurityMetadataSource.loadResourceDefine();
				}
				data.put("msgcode", "true");
				data.put("msg", "角色更新成功");
			} catch (Exception e) {
				data.put("msgcode", "false");
				data.put("msg", "角色更新失败");
			}
		} else {
			data.put("msgcode", "false");
			data.put("msg", "角色名为空");
		}
		return data;
	}
	

	
	@RequestMapping(value = "/getResourceTree")
	@ResponseBody
	public Object getResourceTree(
			@RequestParam(value = "roleId", required = false) Integer roleId) {
		List<Resource> list = new ArrayList<Resource>();
		List<Resource> haslist = new ArrayList<Resource>();
		haslist = resourceBusinessImpl.getResoucesByRoleId(roleId);
		if (resourceBusinessImpl.queryResourceByList().size() > 0) {
			List<Resource> querylist=resourceBusinessImpl.queryResourceByList();
			for (Resource rescource :querylist ) {
				treelist.clear();
				list.addAll(getTree(rescource));
			}
			if(!haslist.isEmpty()){
				for (Resource res : list) {
					for (Resource r : haslist) {
						if (res.getId().equals(r.getId())) {
							res.setChecked(true);
						}
					}
				}
			}
			}
		return list;
	}
	
	public List<Resource> getTree(Resource r) {
		r.setChecked(false);
		treelist.add(r);
		if (!r.getChildren().isEmpty()) {
			for (Resource resc : r.getChildren()) {
				getTree(resc);
			}
		}
		return treelist;
	}
}
