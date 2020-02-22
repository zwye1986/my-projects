package com.venada.efinance.business.impl;

import com.venada.efinance.business.ResourceBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.Resource;
import com.venada.efinance.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResourceBusinessImpl  implements ResourceBusiness{

	@Autowired
	private ResourceService resourceServiceImpl;
	public List<Resource> getResoucesByRoleId(Integer id)throws BusinessException {
		Map<String, Object> condition=new HashMap<String,Object>();
		condition.put("id", id);
		return (List<Resource>) resourceServiceImpl.findObjects("getResoucesByRoleId",condition);
	}

	public void addResource(Resource r) throws BusinessException {
		resourceServiceImpl.saveObject("addResource", r);
	}

	public void updateResource(Resource r) throws BusinessException {
		resourceServiceImpl.updateObject("updateResource", r);
	}

	public List<Resource> queryResource(Map<String, Object> condition,
			PaginationMore page) throws BusinessException {
		page.setTotalRows(getResourceDetailsCount(condition));
		page.repaginate();
		return resourceServiceImpl.selectList("queryResource", condition, page);
	}

	public Integer getResourceDetailsCount(Map<String, Object> condition) {
		return (Integer) resourceServiceImpl.getObject("getResourceDetailsCount", condition);
	}

	public void deleteResource(String Id) throws BusinessException {
		resourceServiceImpl.deleteObject("deleteResourceById", Id);
	}

	public void deleteResourceByResId(String Id) throws BusinessException {
		resourceServiceImpl.deleteObject("deleteResourceByResId", Id);
	}
	
	public Resource getResourceById(Integer Id) throws BusinessException {
//		Map<String, Object> condition=new HashMap<String,Object>();
//		condition.put("Id", Id);
		return (Resource) resourceServiceImpl.getObject("getResById", Id);
	}
	
	public List<Resource> getResourceListByValue(String value) throws BusinessException{
		List<Resource> resourceList = new ArrayList<Resource>();
		Map<String, Object> condition=new HashMap<String,Object>();
		condition.put("value", value);
		resourceList = resourceServiceImpl.findObjects("findResourceListByValue", condition);
		return resourceList;
	}
	
	public List<Resource> findResourceListByName(String value) throws BusinessException{
		List<Resource> resourceList = new ArrayList<Resource>();
		Map<String, Object> condition=new HashMap<String,Object>();
		condition.put("modelName", value);
		resourceList =resourceServiceImpl.findObjects("findResourceListByName", condition);
		return resourceList;
	}
	
	
	
	
	public List<Resource> queryResourceByList() throws BusinessException{
		List<Resource> resourceList = new ArrayList<Resource>();
		resourceList = resourceServiceImpl.findObjects("queryResourceByList",null);
		return resourceList;
	}
	
	
}
