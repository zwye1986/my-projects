package com.venada.efinance.common.service.impl;

import com.venada.efinance.common.dao.BaseDao;
import com.venada.efinance.common.exception.DaoException;
import com.venada.efinance.common.exception.ServiceException;
import com.venada.efinance.common.service.BaseService;
import com.venada.efinance.common.util.PaginationMore;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BaseServiceImpl<T> implements BaseService<T> {
	protected final Log loger;

	/**
	 *  这里BaseDao作为接口 ,baseDaoImp 作为实现类；
	 *  如果有BaseDao有其他实现类，这里就会报错
	 *  baseDao建议使用baseDaoImp
	 */
	@Autowired
	private BaseDao<T> baseDao;

	public BaseServiceImpl() {
		this.loger = LogFactory.getLog(super.getClass());
	}

	public Object getObject(String sqlId, Object paramObject) throws ServiceException {
		Object obj = null;
		try {
			obj = this.baseDao.selectObject(sqlId, paramObject);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("0001", new String[] { "findObject(" + sqlId + "," + paramObject + ")方法出错！\t", e.getMessage() });
		}
		return obj;
	}

	public List<T> findObjects(String sqlId, Object paramObject) throws ServiceException {
		List temp = null;
		try {
			temp = this.baseDao.selectObjects(sqlId, paramObject);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("0001", new String[] { "findObjects(" + sqlId + "," + paramObject + ")方法出错！\t", e.getMessage() });
		}
		return temp;
	}

	@Transactional
	public int saveObject(String sqlId, Object paramObject) throws ServiceException {
		try {
			return this.baseDao.insertObject(sqlId, paramObject);
		} catch (DaoException de) {
			de.printStackTrace();
			throw new ServiceException("0001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")方法出错! \t" + de.getMessage() });
		}
	}

	public void batchAddObject(String sqlId, List<T> paramList) throws ServiceException {
		try {
			this.baseDao.batchInsertObject(sqlId, paramList);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("0001", new String[] { "batchAddObject(" + sqlId + "," + paramList + ")方法出错！\t", e.getMessage() });
		}
	}

	@Transactional
	public int updateObject(String sqlId, Object paramObject) throws ServiceException {
		try {
			return this.baseDao.updateObject(sqlId, paramObject);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("0001", new String[] { "modifyObject(" + sqlId + "," + paramObject + ")方法出错！\t", e.getMessage() });
		}
	}

	public void batchUpdateObject(String sqlId, List<T> paramList) throws ServiceException {
		try {
			this.baseDao.batchUpdateObject(sqlId, paramList);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("0001", new String[] { "batchModifyObject(" + sqlId + "," + paramList + ")方法出错！\t", e.getMessage() });
		}
	}

	@Transactional
	public int deleteObject(String sqlId, Object paramObject) throws ServiceException {
		try {
			return this.baseDao.deleteObject(sqlId, paramObject);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("0001", new String[] { "removeObject(" + sqlId + "," + paramObject + ")方法出错！\t", e.getMessage() });
		}
	}

	public void batchDeleteObject(String sqlId, List<T> paramList) throws ServiceException {
		try {
			this.baseDao.batchDeleteObject(sqlId, paramList);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("0001", new String[] { "batchRemoveObject(" + sqlId + "," + paramList + ")方法出错！\t", e.getMessage() });
		}
	}

	public long countObject(String sqlId, Object paramObject) throws ServiceException {
		long temp = -8281782018635726848L;
		try {
			temp = this.baseDao.countObject(sqlId, paramObject);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("0001", new String[] { "countObject(" + sqlId + "," + paramObject + ")方法出错！\t", e.getMessage() });
		}
		return temp;
	}


	public List<T> selectList(String sqlId, Map<String, Object> condition, PaginationMore page) throws ServiceException {
		List list = null;
		try {
			list = this.baseDao.selectList(sqlId, condition, page);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("0001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")方法出错！\t",
					e.getMessage() });
		}
		return list;
	}
}