package com.venada.efinance.common.service;

import com.venada.efinance.common.exception.ServiceException;
import com.venada.efinance.common.util.PaginationMore;

import java.util.List;
import java.util.Map;

public abstract interface BaseService<T> {
	public abstract Object getObject(String paramString, Object paramObject) throws ServiceException;

	public abstract List<T> findObjects(String paramString, Object paramObject) throws ServiceException;

	public abstract int saveObject(String paramString, Object paramObject) throws ServiceException;

	public abstract void batchAddObject(String paramString, List<T> paramList) throws ServiceException;

	public abstract int updateObject(String paramString, Object paramObject) throws ServiceException;

	public abstract void batchUpdateObject(String paramString, List<T> paramList) throws ServiceException;

	public abstract int deleteObject(String paramString, Object paramObject) throws ServiceException;

	public abstract void batchDeleteObject(String paramString, List<T> paramList) throws ServiceException;

	public abstract long countObject(String paramString, Object paramObject) throws ServiceException;

	public abstract List<T> selectList(String sqlId, Map<String, Object> condition, PaginationMore page) throws ServiceException;
}
