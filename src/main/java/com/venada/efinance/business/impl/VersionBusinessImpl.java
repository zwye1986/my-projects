package com.venada.efinance.business.impl;


import com.venada.efinance.business.VersionBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.Version;
import com.venada.efinance.service.VersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/***
 * 
 * @author xupei
 *
 */
@Service("versionBusiness")
public class VersionBusinessImpl implements VersionBusiness{
	/** Logger */
	private static final Logger logger = LoggerFactory.getLogger(VersionBusinessImpl.class);
	@Autowired
	private VersionService versionService;
	@Override
	public void addVersion(Version version) throws BusinessException {
		try {
			versionService.saveObject("insertVersion", version);
		} catch (DuplicateKeyException duplicateKeyException) {
			logger.error("错误堆栈:" + duplicateKeyException.getMessage());
			throw new BusinessException("数据重复.");
		} catch (Exception e) {
			logger.error("错误堆栈:" + e.getMessage());
			throw new BusinessException("系统异常.");
		}
	}
	@Override
	public Version getLastApp() throws BusinessException {
		return (Version) versionService.getObject("getLastVersion",null);
	}
	@Override
	public void updateVersion(Version version) throws BusinessException {
		try {
			versionService.updateObject("updateVersion", version);
		} catch (DuplicateKeyException duplicateKeyException) {
			logger.error("错误堆栈:" + duplicateKeyException.getMessage());
			throw new BusinessException("数据重复.");
		} catch (Exception e) {
			logger.error("错误堆栈:" + e.getMessage());
			throw new BusinessException("系统异常.");
		}
	}


}
