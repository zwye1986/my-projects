package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.Project;
import com.venada.efinance.pojo.ProjectType;
import com.venada.efinance.pojo.ProjectUser;

import java.util.List;
import java.util.Map;

/***
 * 
 * @author xupei
 *
 */

public interface ProjectBusiness {
	
   List<Project> queryProjects(Map<String,Object> map,PaginationMore page) throws BusinessException;
   
   List<ProjectType> queryProjectType(Map<String,Object> map,PaginationMore page) throws BusinessException;
   
   List<ProjectType> queryProjectType(Map<String,Object> map) throws BusinessException;
   
   List<Project> queryProjects(Map<String,Object> map) throws BusinessException;
   
   int getProjectsCounts(Map<String,Object> map) throws BusinessException;
   
   int getProjectTypeCounts(Map<String,Object> map) throws BusinessException;
   
   void insertProject(Project project) throws BusinessException;
   
   Project getProjectById(String id) throws BusinessException;
   
   void delProjectById(String id) throws BusinessException;
   
   void delProjectTypeById(String id) throws BusinessException;
   
   void updateProject(Project project)  throws BusinessException;
   
   List<Project> queryProjectsByRandom() throws BusinessException; 
   
   List<ProjectUser> queryProjectUserForDetail(String projectId) throws BusinessException; 
   
   Integer getAleardySupportNum(String projectInvestId) throws BusinessException;
   
   ProjectType getProjectTypeById(String id) throws BusinessException;
   
   void updateProjectType(ProjectType projectType) throws BusinessException;
   
   void insertProjectType(ProjectType projectType) throws BusinessException;
}
