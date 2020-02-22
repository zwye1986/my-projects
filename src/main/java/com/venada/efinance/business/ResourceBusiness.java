package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.Resource;

import java.util.List;
import java.util.Map;

public interface ResourceBusiness {

	public List<Resource> getResoucesByRoleId(Integer id)
			throws BusinessException;

	public void addResource(Resource r) throws BusinessException;

	public void updateResource(Resource r) throws BusinessException;

	public List<Resource> queryResource(Map<String, Object> condition,
			PaginationMore page) throws BusinessException;

	public Integer getResourceDetailsCount(Map<String, Object> condition);

	public void deleteResource(String Id) throws BusinessException;

	public void deleteResourceByResId(String Id) throws BusinessException;

	public Resource getResourceById(Integer Id) throws BusinessException;

	public List<Resource> getResourceListByValue(String value)
			throws BusinessException;

	public List<Resource> findResourceListByName(String value)
			throws BusinessException;

	public List<Resource> queryResourceByList() throws BusinessException;
}
