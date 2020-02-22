package com.venada.efinance.business.impl;

import com.venada.efinance.business.UserWalletCheckBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.exception.ServiceException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.UserWalletCheck;
import com.venada.efinance.service.UserWalletCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 
* @ClassName: UserWalletCheckBusinessImpl 
* @author hepei
* @date 2014年2月12日 下午2:57:20
 */
@Service(value = "userWalletCheckBusinessImpl")
public class UserWalletCheckBusinessImpl implements UserWalletCheckBusiness {

	@Autowired
	private UserWalletCheckService userWalletCheckServiceImpl;
	 
	private static final Logger logger = LoggerFactory.getLogger(UserWalletCheckBusinessImpl.class);


	/* (non-Javadoc)
	 * @see com.venada.efinance.business.UserWalletCheckBusiness#getAllUserWalletCheck(java.util.Map, com.venada.efinance.common.util.PaginationMore)
	 */
	@Override
	public List<UserWalletCheck> getAllUserWalletCheck(
			Map<String, Object> condition, PaginationMore page)
			throws BusinessException {
		page.setTotalRows(getAllUserWalletCheckCount(condition));
		page.repaginate();
		return userWalletCheckServiceImpl.selectList("getAllUserWalletCheck", condition, page);
	}


	/* (non-Javadoc)
	 * @see com.venada.efinance.business.UserWalletCheckBusiness#getAllUserWalletCheckCount(java.util.Map)
	 */
	@Override
	public int getAllUserWalletCheckCount(Map<String, Object> condition)
			throws BusinessException {
		try {
			return (Integer) userWalletCheckServiceImpl.getObject("getAllUserWalletCheckCount", condition);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"getAllUserWalletCheckCount("+condition+")方法出错！\t", e.getMessage() });
		}
	}

}
