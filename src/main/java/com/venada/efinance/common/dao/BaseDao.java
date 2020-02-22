package com.venada.efinance.common.dao;

import com.venada.efinance.common.exception.DaoException;
import com.venada.efinance.common.util.PaginationMore;

import java.util.List;

public abstract interface BaseDao<T> {
	public abstract Object selectObject(String paramString, Object paramObject) throws DaoException;

	public abstract List<T> selectObjects(String paramString, Object paramObject) throws DaoException;

	public abstract int insertObject(String paramString, Object paramObject) throws DaoException;

	public abstract void batchInsertObject(String paramString, List<T> paramList) throws DaoException;

	public abstract int updateObject(String paramString, Object paramObject) throws DaoException;

	public abstract void batchUpdateObject(String paramString, List<T> paramList) throws DaoException;

	public abstract int deleteObject(String paramString, Object paramObject) throws DaoException;

	public abstract void batchDeleteObject(String paramString, List<T> paramList) throws DaoException;

	public abstract long countObject(String paramString, Object paramObject) throws DaoException;

	public abstract List<T> selectList(String sqlId, Object condition, PaginationMore page) throws DaoException;
}
