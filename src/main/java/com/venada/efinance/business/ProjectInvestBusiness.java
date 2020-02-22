package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.ProjectInvest;
import com.venada.efinance.pojo.ProjectUser;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public interface ProjectInvestBusiness {
	
	public int saveProjectInvest(ProjectInvest obj) throws  BusinessException;
	
	public ProjectInvest getProjectInvest(String id) throws BusinessException ;
	
	public int updateProjectInvest(ProjectInvest obj) throws BusinessException ;
	
	public List<ProjectInvest> getProjectInvestAll(PaginationMore page,Map<String,Object> map) throws BusinessException ;
	
	public int deleteProjectInvest(String... ids) throws BusinessException ;
	
	public int getProjectInvestAllCount(Map<String,Object> map) throws BusinessException ;

	public List<ProjectInvest> selectProjectInvestByProjectId(Map<String,Object> map) throws BusinessException ;
	
	public void saveProjectUser(ProjectUser projectUser) throws BusinessException;
	
	public List<ProjectUser> getSupportLog(Map<String,Object> conditions ,PaginationMore page) throws BusinessException;

	public BigDecimal getSumProjectInvest(Map<String,Object> map)throws BusinessException;
}
