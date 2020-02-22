package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.ProjectInvestRate;

import java.util.List;
import java.util.Map;


public interface ProjectInvestRateBusiness {
	
	public int saveProjectInvestRateRate(ProjectInvestRate obj) throws  BusinessException;
	
	public ProjectInvestRate getProjectInvestRate(String id) throws BusinessException ;
	
	public int updateProjectInvestRate(ProjectInvestRate obj) throws BusinessException ;
	
	public List<ProjectInvestRate> getProjectInvestRateAll(PaginationMore page,Map<String,Object> map) throws BusinessException ;
	
	public List<ProjectInvestRate> getProjectInvestRateAll(Map<String,Object> map) throws BusinessException ;
	
	public int deleteProjectInvestRate(String... ids) throws BusinessException ;
	
	public int getProjectInvestRateAllCount(Map<String,Object> map) throws BusinessException ;
}
