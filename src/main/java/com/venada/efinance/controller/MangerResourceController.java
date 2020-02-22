package com.venada.efinance.controller;

import com.venada.efinance.business.ResourceBusiness;
import com.venada.efinance.common.controller.BaseController;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.Resource;
import com.venada.efinance.security.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;



@Controller
@RequestMapping("/manager")
public class MangerResourceController extends BaseController {

	@Autowired
	private ResourceBusiness resourceBusinessImpl;
	List<Resource> treelist = new ArrayList<Resource>();

	
	@RequestMapping("/resourceList")
	public String resourceList(PaginationMore page, Model model,
			HttpServletRequest request) {
		Map<String, Object> condition = setMapCondition(request);
		List<Resource> rescList = new ArrayList<Resource>();
		page.setPageSize(10);
		rescList = resourceBusinessImpl.queryResource(condition, page);
		model.addAttribute("rescList", rescList);
		model.addAttribute("page", page);
		return "/manager/resourcemanager/resourceManagerlist";
	}
	
	

	@RequestMapping(value = "/delResource")
	@ResponseBody
	public Map<String, Object> deleteResource(@RequestParam(value = "resourceId", required = false) String resourceId) {
		Map<String, Object> data = new HashMap<String, Object>();
			try {
				resourceBusinessImpl.deleteResource(resourceId);
				resourceBusinessImpl.deleteResourceByResId(resourceId);
				data.put("msgcode", "true");
				data.put("msg", "菜单删除成功");
			} catch (Exception e) {
				data.put("msgcode", "false");
				data.put("msg", "菜单删除失败");
			}
		
		return data;
	}
	
	@RequestMapping(value = "/saveResource")
	@ResponseBody
	public Map<String, Object> saveResource(Resource u) {
		Map<String, Object> data = new HashMap<String, Object>();
		if (u.getValue() != null) {
			u.setCreateTime(new Date());
			u.setCreateBy(SpringSecurityUtil.getCurrUsername());
			if(u.getParentId()==null){
				u.setParentId(0);
			}
			try {
				resourceBusinessImpl.addResource(u);
				data.put("msgcode", "true");
				data.put("msg", "菜单创建成功");
			} catch (Exception e) {
				data.put("msgcode", "false");
				data.put("msg", "菜单创建失败");
			}
		} else {
			data.put("msgcode", "false");
			data.put("msg", "菜单地址为空");
		}
		return data;
	}

	@RequestMapping(value = { "/resourceExist" })
	@ResponseBody
	public Map<String, Object> resourceExist(
			@RequestParam(value = "value", required = false) String value) {
		List<Resource> resourceList = resourceBusinessImpl.getResourceListByValue(value);
		Map<String, Object> data = new HashMap<String, Object>();
		if (resourceList.isEmpty()) {
			data.put("isExists", "false");
		} else {
			data.put("isExists", "true");
		}
		return data;
	}

	@RequestMapping(value = { "/getResourceById" })
	@ResponseBody
	public Map<String, Object> getResourceById(
			@RequestParam(value = "Id", required = false) Integer Id) {
		Resource resource = resourceBusinessImpl.getResourceById(Id);
		Map<String, Object> data = new HashMap<String, Object>();
		if (resource == null) {
			data.put("msgCode", "false");
		} else {
			Resource parentResource = resourceBusinessImpl.getResourceById(resource
					.getParentId());
			data.put("msgCode", "true");
			data.put("id", resource.getId());
			data.put("value", resource.getValue());
			data.put("modelName", resource.getModelName());
			if (parentResource != null) {
				data.put("parentId", parentResource.getId());
				data.put("umodeName", parentResource.getModelName());
			}
		}
		return data;
	}
	
	
	@RequestMapping(value = "/updateResource")
	@ResponseBody
	public Map<String, Object> updateResource(Resource u) {
		Map<String, Object> data = new HashMap<String, Object>();
		if (u.getValue() != null) {
			u.setId(u.getId());
			u.setLastUpdateTime(new Date());
			u.setLastUpdateBy(SpringSecurityUtil.getCurrUsername());
			try {
				resourceBusinessImpl.updateResource(u);
				data.put("msgcode", "true");
				data.put("msg", "菜单更新成功");
			} catch (Exception e) {
				data.put("msgcode", "false");
				data.put("msg", "菜单更新失败");
			}
		} else {
			data.put("msgcode", "false");
			data.put("msg", "菜单地址为空");
		}
		return data;
	}
	
	@RequestMapping(value = "/getResourceTreeByRId")
	@ResponseBody
	public Object getResourceTree(
			@RequestParam(value = "resourceId", required = false) Integer resourceId) {
		List<Resource> list = new ArrayList<Resource>();
		Resource resource=resourceBusinessImpl.getResourceById(resourceId);
		if (resourceBusinessImpl.queryResourceByList().size() > 0) {
			List<Resource> querylist = resourceBusinessImpl.queryResourceByList();
			for (Resource rescource : querylist) {
				treelist.clear();
				list.addAll(getTree(rescource));
			}
			
			if (resource != null) {
				if (resource.getParentId() != null) {
					for (Resource res : list) {
						if (res.getId().equals(resource.getParentId())) {
							res.setChecked(true);
						}
					}
				}
			}
			
		}
		return list;
	}
	
	public List<Resource> getTree(Resource r) {
	
		treelist.add(r);
		if (!r.getChildren().isEmpty()) {
			for (Resource resc : r.getChildren()) {
				getTree(resc);
			}
		}
		return treelist;
	}
}
