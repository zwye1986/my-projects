package com.venada.efinance.business.impl;

import com.venada.efinance.business.ProjectInvestBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.exception.ServiceException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.ProjectInvest;
import com.venada.efinance.pojo.ProjectUser;
import com.venada.efinance.service.AdvertiseImagService;
import com.venada.efinance.service.ProjectInvestService;
import com.venada.efinance.service.ProjectUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author hepei
 * 
 */
@Service(value = "projectInvestBusinessImp")
public class ProjectInvestBusinessImp implements ProjectInvestBusiness {

	@Autowired
	private ProjectInvestService projectInvestServiceImp;
	@Autowired
	private AdvertiseImagService advertiseImagServiceImpl;
	@Autowired
	private ProjectUserService projectUserService;
	 
	private static final Logger logger = LoggerFactory.getLogger(SystemConfigBusinessImpl.class);

	
	@Transactional
	public int saveProjectInvest(ProjectInvest obj) throws BusinessException {
		try {
			return  projectInvestServiceImp.saveObject("addprojectInvest", obj);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"addprojectInvest()方法出错！\t", e.getMessage() });
		}
	}

	
	@Override
	public ProjectInvest getProjectInvest(String id) throws BusinessException {
		try {
			return (ProjectInvest) projectInvestServiceImp.getObject("selectprojectInvestById", id);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"selectprojectInvestById("+id+")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public int updateProjectInvest(ProjectInvest obj) throws BusinessException {
		try {
			return projectInvestServiceImp.updateObject("updateprojectInvestById", obj);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"updateprojectInvestById()方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public List<ProjectInvest> getProjectInvestAll(PaginationMore page,
			Map<String, Object> map) throws BusinessException {
		try {
			page.setTotalRows(this.getProjectInvestAllCount(map));
			page.repaginate();
			return projectInvestServiceImp.selectList("selectprojectInvestAll", map, page);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"selectprojectInvestAll("+page+","+map+")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public List<ProjectInvest> selectProjectInvestByProjectId(	Map<String, Object> map) throws BusinessException {
		try {
			return projectInvestServiceImp.findObjects("selectprojectInvestByProjectId", map);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"selectprojectInvestByProjectId(,"+map+")方法出错！\t", e.getMessage() });
		}
	}
	
	@Override
	public int deleteProjectInvest(String... ids) throws BusinessException {
		int result = 0 ;
		try {
			for(String id : ids){
				projectInvestServiceImp.deleteObject("deleteprojectInvest", id);	
				advertiseImagServiceImpl.deleteObject("deleteAdvertiseImg", id);
				result++ ;
			}
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"deleteprojectInvest()方法出错！\t", e.getMessage() });
		}
		return result ;
	}

	@Override
	public int getProjectInvestAllCount(Map<String, Object> map)
			throws BusinessException {
		try {
			return (Integer) projectInvestServiceImp.getObject("selectprojectInvestAllCount", map);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"selectprojectInvestAllCount("+map+")方法出错！\t", e.getMessage() });
		}
	}


	@Override
	public void saveProjectUser(ProjectUser projectUser)
			throws BusinessException {
		projectUserService.saveObject("saveProjectUser", projectUser);
	}


	@Override
	public List<ProjectUser> getSupportLog(Map<String,Object> conditions,PaginationMore page)
			throws BusinessException {
		page.setTotalRows(getSupportLogCount(conditions));
		page.repaginate();
		return projectUserService.selectList("getSupportLog", conditions, page);
	}
	
	private int getSupportLogCount(Map<String,Object> conditions) throws BusinessException{
		return (Integer) projectUserService.getObject("getSupportLogCount", conditions);
	}


	@Override
	public BigDecimal getSumProjectInvest(Map<String,Object> map)
			throws BusinessException {
		return (BigDecimal) projectUserService.getObject("getSumProjectInvest", map);
	}

}
