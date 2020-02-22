package com.venada.efinance.business.impl;


import com.venada.efinance.business.ProjectBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.Project;
import com.venada.efinance.pojo.ProjectType;
import com.venada.efinance.pojo.ProjectUser;
import com.venada.efinance.service.ProjectService;
import com.venada.efinance.service.ProjectTypeService;
import com.venada.efinance.service.ProjectUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/***
 * 
 * @author xupei
 *
 */
@Service("projectBusiness")
public class ProjectBusinessImpl implements ProjectBusiness{
	@Autowired
	private ProjectService projectService;
	@Autowired
	private ProjectUserService projectUserService;
	@Autowired
	private ProjectTypeService projectTypeService;
	/** Logger */
	private static final Logger logger = LoggerFactory.getLogger(ProjectBusinessImpl.class);

	@Override
	public List<Project> queryProjects(Map<String, Object> map,PaginationMore page)
			throws BusinessException {		
		page.setTotalRows(getProjectsCounts(map));
		page.setPageSize(10);
		page.repaginate();
		return projectService.selectList("queryProjectsForAdmin", map, page);
	}

	@Override
	public int getProjectsCounts(Map<String, Object> map)
			throws BusinessException {
		
		return (Integer) projectService.getObject("getProjectsCounts", map);
	}

	@Override
	public void insertProject(Project project) throws BusinessException {
		projectService.saveObject("insertProject", project);
	}

	@Override
	public Project getProjectById(String id) throws BusinessException {
		return (Project) projectService.getObject("getProjectById", id);
	}

	@Override
	public void delProjectById(String id) throws BusinessException {
		projectService.deleteObject("delProjectById", id);
	}

	@Override
	public void updateProject(Project project) throws BusinessException {
		projectService.updateObject("updateProject", project);
	}

	@Override
	public List<Project> queryProjects(Map<String, Object> map)
			throws BusinessException {
		return projectService.findObjects("queryProjects", map);
	}

	@Override
	public List<Project> queryProjectsByRandom() throws BusinessException {
		return projectService.findObjects("queryProjectsByRandom", null);
	}

	@Override
	public List<ProjectUser> queryProjectUserForDetail(String projectId)
			throws BusinessException {
		return projectUserService.findObjects("queryProjectUserForDetail", projectId);
	}

	@Override
	public Integer getAleardySupportNum(String projectInvestId)
			throws BusinessException {
		Integer count=(Integer)projectUserService.getObject("getAleardySupportNum", projectInvestId);
		if(count==null){
			count=0;
		}
		return count;
	}

	@Override
	public ProjectType getProjectTypeById(String id) throws BusinessException {
		return (ProjectType) projectTypeService.getObject("getProjectTypeById", id);
	}

	@Override
	public void updateProjectType(ProjectType projectType)
			throws BusinessException {
		 projectTypeService.updateObject("updateProjectType", projectType);
	}

	@Override
	public void insertProjectType(ProjectType projectType)
			throws BusinessException {
		projectTypeService.saveObject("insertProjectType", projectType);
	}

	@Override
	public List<ProjectType> queryProjectType(Map<String, Object> map,
			PaginationMore page) throws BusinessException {
		page.setTotalRows(getProjectTypeCounts(map));
		page.setPageSize(10);
		page.repaginate();
		return projectTypeService.selectList("queryProjectType", map, page);
	}

	@Override
	public int getProjectTypeCounts(Map<String, Object> map)
			throws BusinessException {
		return (Integer) projectService.getObject("getProjectTypeCounts", map);
	}

	@Override
	public void delProjectTypeById(String id) throws BusinessException {
		projectTypeService.deleteObject("delProjectTypeById", id);
	}

	@Override
	public List<ProjectType> queryProjectType(Map<String, Object> map)
			throws BusinessException {
		return projectTypeService.findObjects("queryProjectType", map);
	}

	
	
}
