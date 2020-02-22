package com.venada.efinance.business.impl;

import com.venada.efinance.business.ProjectInvestRateBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.exception.ServiceException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.ProjectInvestRate;
import com.venada.efinance.service.ProjectInvestRateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author hepei
 * 
 */
@Service(value = "projectInvestRateBusinessImp")
public class ProjectInvestRateBusinessImp implements ProjectInvestRateBusiness {

	@Autowired
	private ProjectInvestRateService projectInvestRateServiceImp;


	 
	private static final Logger logger = LoggerFactory.getLogger(SystemConfigBusinessImpl.class);


	@Transactional
	@Override
	public int saveProjectInvestRateRate(ProjectInvestRate obj)
			throws BusinessException {
		try {
			return projectInvestRateServiceImp.saveObject(
					"addprojectInvestRate", obj);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"addprojectInvestRate()方法出错！\t", e.getMessage() });
		}
	}


	@Override
	public ProjectInvestRate getProjectInvestRate(String id)
			throws BusinessException {
		try {
			return (ProjectInvestRate) projectInvestRateServiceImp.getObject("selectprojectInvestRateById", id);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"selectprojectInvestRateById("+id+")方法出错！\t", e.getMessage() });
		}
	}

	@Transactional
	@Override
	public int updateProjectInvestRate(ProjectInvestRate obj)
			throws BusinessException {
		try {
			return projectInvestRateServiceImp.updateObject("updateprojectInvestRateById", obj);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"updateprojectInvestRateById()方法出错！\t", e.getMessage() });
		}
	}


	@Override
	public List<ProjectInvestRate> getProjectInvestRateAll(PaginationMore page,
			Map<String, Object> map) throws BusinessException {
		try {
			page.setTotalRows(this.getProjectInvestRateAllCount(map));
			page.repaginate();
			return projectInvestRateServiceImp.selectList("selectprojectInvestRateAll", map, page);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"selectprojectInvestRateAll("+page+","+map+")方法出错！\t", e.getMessage() });
		}
	}

	@Transactional
	@Override
	public int deleteProjectInvestRate(String... ids) throws BusinessException {
		int result = 0 ;
		try {
			for(String id : ids){
				projectInvestRateServiceImp.deleteObject("deleteprojectInvestRate", id);	
				result++ ;
			}
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"deleteprojectInvestRate()方法出错！\t", e.getMessage() });
		}
		return result ;
	}


	@Override
	public int getProjectInvestRateAllCount(Map<String, Object> map)
			throws BusinessException {
		try {
			return (Integer) projectInvestRateServiceImp.getObject("getProjectInvestRateAllCount", map);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"getProjectInvestRateAllCount("+map+")方法出错！\t", e.getMessage() });
		}
	}


	@Override
	public List<ProjectInvestRate> getProjectInvestRateAll(
			Map<String, Object> map) throws BusinessException {
		try {
			return projectInvestRateServiceImp.findObjects("selectprojectInvestRateAll", map);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"selectprojectInvestRateAll("+map+")方法出错！\t", e.getMessage() });
		}
	}

	
}
