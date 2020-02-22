package com.venada.efinance.business.impl;

import com.venada.efinance.business.OperationLogBusiness;
import com.venada.efinance.business.SystemConfigBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.exception.ServiceException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.enumtype.LogTypeEnum;
import com.venada.efinance.pojo.OperationLog;
import com.venada.efinance.pojo.SystemConfig;
import com.venada.efinance.service.SystemConfigService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class SystemConfigBusinessImpl implements SystemConfigBusiness {
	private static final Logger logger = LoggerFactory.getLogger(SystemConfigBusinessImpl.class);
	
	@Autowired
	private SystemConfigService systemConfigService ;
	@Autowired
	private OperationLogBusiness opeartionLogBusiness ;

	@Transactional
	public int saveSystemConfig(SystemConfig obj) throws BusinessException {
		try {
			if(StringUtils.isNotEmpty(obj.getParamId())){
				return this.updateSystemConfig(obj);
			}
			obj.setParamId(UUID.randomUUID().toString());
			return  systemConfigService.saveObject("addSystemConfig", obj);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"saveSystemConfig()方法出错！\t", e.getMessage() });
		}
	}

	public SystemConfig getSystemConfig(String id) throws BusinessException {
		try {
			return (SystemConfig) systemConfigService.getObject("selectSystemConfigById", id);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"getSystemConfig("+id+")方法出错！\t", e.getMessage() });
		}
	}

	@Transactional
	public int updateSystemConfig(SystemConfig obj) throws BusinessException {
		try {
			//记录日志信息
			OperationLog log = new OperationLog();
			log.setLogType(LogTypeEnum.SystemConfig.getIndex());
			log.setDataOld(JSONObject.fromObject(this.getSystemConfig(obj.getParamId())).toString());
			log.setDataNew(JSONObject.fromObject(obj).toString());
			opeartionLogBusiness.addOperationLog(log);
			
			return systemConfigService.updateObject("updateSystemConfigById", obj);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"updateSystemConfig()方法出错！\t", e.getMessage() });
		}
	}

	public List<SystemConfig> getSystemConfigAll(PaginationMore page, Map<String, Object> map) throws BusinessException {
		try {
			page.setTotalRows(this.getSystemConfigAllCount(map));
			page.repaginate();
			return systemConfigService.selectList("selectSystemConfigAll", map, page);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"getSystemConfigAll("+page+","+map+")方法出错！\t", e.getMessage() });
		}
	}

	@Transactional
	public int deleteSystemConfig(String... ids) throws BusinessException {
		int result = 0 ;
		try {
			for(String id : ids){
				//记录日志信息
				OperationLog log = new OperationLog();
				log.setLogType(LogTypeEnum.SystemConfig.getIndex());
				log.setDataOld(JSONObject.fromObject(this.getSystemConfig(id)).toString());
				log.setRemark("删除操作");
				opeartionLogBusiness.addOperationLog(log);
				
				systemConfigService.deleteObject("deleteSystemConfig", id);	
				result++ ;
			}
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"deleteSystemConfig()方法出错！\t", e.getMessage() });
		}
		return result ;
	}

	public int getSystemConfigAllCount(Map<String, Object> map) throws BusinessException {
		try {
			return (Integer) systemConfigService.getObject("selectSystemConfigAllCount", map);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"getSystemConfigAllCount("+map+")方法出错！\t", e.getMessage() });
		}
	}

}
