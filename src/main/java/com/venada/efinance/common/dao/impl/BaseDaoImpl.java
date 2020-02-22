package com.venada.efinance.common.dao.impl;

import com.venada.efinance.common.dao.BaseDao;
import com.venada.efinance.common.exception.DaoException;
import com.venada.efinance.common.pojo.BaseEntity;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.security.SpringSecurityUtil;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.*;
import org.springframework.jdbc.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked"})
public class BaseDaoImpl<T> implements BaseDao<T> {
	private static final Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

	@Resource
	protected SqlSession sqlSession;

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;


	public Object selectObject(String sqlId, Object paramObject) throws DaoException {
		Object object;
		try {
			object = this.sqlSession.selectOne(sqlId, paramObject);
		} catch (EmptyResultDataAccessException erde) {
			logger.error(erde.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "返回结果为空!" });
		} catch (JdbcUpdateAffectedIncorrectNumberOfRowsException juainoe) {
			logger.error(juainoe.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "错误更新数据行数!" });
		} catch (CannotGetJdbcConnectionException cgjce) {
			logger.error(cgjce.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "不能获得连接!" });
		} catch (CannotAcquireLockException cale) {
			logger.error(cale.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "不能获得锁!" });
		} catch (CannotSerializeTransactionException cste) {
			logger.error(cste.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "事务连接失败!" });
		} catch (DeadlockLoserDataAccessException dlde) {
			logger.error(dlde.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "数据死锁!" });
		} catch (IncorrectResultSizeDataAccessException irsde) {
			logger.error(irsde.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "结果大小超出范围!" });
		} catch (IncorrectResultSetColumnCountException irscce) {
			logger.error(irscce.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "不正确的结果集字段统计!" });
		} catch (LobRetrievalFailureException irsde) {
			logger.error(irsde.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "lob恢复失败!" });
		} catch (IncorrectUpdateSemanticsDataAccessException iusde) {
			logger.error(iusde.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "错误的数据更新语句!" });
		} catch (TypeMismatchDataAccessException tmde) {
			logger.error(tmde.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "数据类型不匹配!" });
		} catch (BadSqlGrammarException bge) {
			logger.error(bge.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "不正确的sql语句!" });
		} catch (InvalidResultSetAccessException irsae) {
			logger.error(irsae.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "不正确的结果集!" });
		} catch (DataAccessResourceFailureException darfe) {
			logger.error(darfe.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "资源访问失败!" });
		} catch (SQLWarningException sqe) {
			logger.error(sqe.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "sql警告!" });
		} catch (UncategorizedSQLException usqe) {
			logger.error(usqe.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "非类别sql问题!" });
		} catch (OptimisticLockingFailureException dive) {
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "乐观锁失败，没有检测到数据库!" });
		} catch (PessimisticLockingFailureException plfe) {
			logger.error(plfe.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "悲观锁失败!" });
		} catch (CleanupFailureDataAccessException cfdae) {
			logger.error(cfdae.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "清除失败，可能连接已关闭!" });
		} catch (NonTransientDataAccessResourceException ntdare) {
			logger.error(ntdare.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "不是短暂的资源访问失败!" });
		} catch (DataIntegrityViolationException dive) {
			logger.error(dive.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "插入或更新数据库；违反数据完整性约束!" });
		} catch (DataRetrievalFailureException drfe) {
			logger.error(drfe.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "违反数据完整性!" });
		} catch (InvalidDataAccessApiUsageException idaae) {
			logger.error(idaae.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "无效的使用api!" });
		} catch (InvalidDataAccessResourceUsageException idarue) {
			logger.error(idarue.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "无效的资源使用!" });
		} catch (PermissionDeniedDataAccessException pddae) {
			logger.error(pddae.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "许可访问拒绝!" });
		} catch (UncategorizedDataAccessException ude) {
			logger.error(ude.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "非类别数据访问问题!" });
		} catch (ConcurrencyFailureException cfe) {
			logger.error(cfe.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "同步失败!" });
		} catch (TransientDataAccessResourceException tdare) {
			logger.error(tdare.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "短暂数据访问资源失败!" });
		} catch (NonTransientDataAccessException ntdae) {
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "非短暂的数据访问!" });
		} catch (RecoverableDataAccessException rde) {
			logger.error(rde.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "重新获得数据访问失败!" });
		} catch (TransientDataAccessException tdae) {
			logger.error(tdae.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "短暂数据访问!" });
		} catch (DataAccessException de) {
			logger.error(de.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "数据访问失败!" });
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DaoException("1001", new String[] { "selectObject(" + sqlId + "," + paramObject + ")出错!!!", "其他访问出错!" });
		}
		return object;
	}

	public List<T> selectObjects(String sqlId, Object paramObject) throws DaoException {

		List queryResult = null;
		try {
			queryResult = this.sqlSession.selectList(sqlId, paramObject);
		} catch (EmptyResultDataAccessException erde) {
			logger.error(erde.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "返回结果为空!" });
		} catch (JdbcUpdateAffectedIncorrectNumberOfRowsException juainoe) {
			logger.error(juainoe.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "错误更新数据行数!" });
		} catch (CannotGetJdbcConnectionException cgjce) {
			logger.error(cgjce.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "不能获得连接!" });
		} catch (CannotAcquireLockException cale) {
			logger.error(cale.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "不能获得锁!" });
		} catch (CannotSerializeTransactionException cste) {
			logger.error(cste.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "事务连接失败!" });
		} catch (DeadlockLoserDataAccessException dlde) {
			logger.error(dlde.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "数据死锁!" });
		} catch (IncorrectResultSizeDataAccessException irsde) {
			logger.error(irsde.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "结果大小超出范围!" });
		} catch (IncorrectResultSetColumnCountException irscce) {
			logger.error(irscce.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "不正确的结果集字段统计!" });
		} catch (LobRetrievalFailureException irsde) {
			logger.error(irsde.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "lob恢复失败!" });
		} catch (IncorrectUpdateSemanticsDataAccessException iusde) {
			logger.error(iusde.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "错误的数据更新语句!" });
		} catch (TypeMismatchDataAccessException tmde) {
			logger.error(tmde.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "数据类型不匹配!" });
		} catch (BadSqlGrammarException bge) {
			logger.error(bge.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "不正确的sql语句!" });
		} catch (InvalidResultSetAccessException irsae) {
			logger.error(irsae.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "不正确的结果集!" });
		} catch (DataAccessResourceFailureException darfe) {
			logger.error(darfe.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "资源访问失败!" });
		} catch (SQLWarningException sqe) {
			logger.error(sqe.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "sql警告!" });
		} catch (UncategorizedSQLException usqe) {
			logger.error(usqe.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "非类别sql问题!" });
		} catch (OptimisticLockingFailureException dive) {
			logger.error(dive.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "乐观锁失败，没有检测到数据库!" });
		} catch (PessimisticLockingFailureException plfe) {
			logger.error(plfe.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "悲观锁失败!" });
		} catch (CleanupFailureDataAccessException cfdae) {
			logger.error(cfdae.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "清除失败，可能连接已关闭!" });
		} catch (NonTransientDataAccessResourceException ntdare) {
			logger.error(ntdare.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "不是短暂的资源访问失败!" });
		} catch (DataIntegrityViolationException dive) {
			logger.error(dive.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "插入或更新数据库；违反数据完整性约束!" });
		} catch (DataRetrievalFailureException drfe) {
			logger.error(drfe.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "违反数据完整性!" });
		} catch (InvalidDataAccessApiUsageException idaae) {
			logger.error(idaae.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "无效的使用api!" });
		} catch (InvalidDataAccessResourceUsageException idarue) {
			logger.error(idarue.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "无效的资源使用!" });
		} catch (PermissionDeniedDataAccessException pddae) {
			logger.error(pddae.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "许可访问拒绝!" });
		} catch (UncategorizedDataAccessException ude) {
            ude.printStackTrace();
			logger.error(ude.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "非类别数据访问问题!" });
		} catch (ConcurrencyFailureException cfe) {
			logger.error(cfe.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "同步失败!" });
		} catch (TransientDataAccessResourceException tdare) {
			logger.error(tdare.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "短暂数据访问资源失败!" });
		} catch (NonTransientDataAccessException ntdae) {
			logger.error(ntdae.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "非短暂的数据访问!" });
		} catch (RecoverableDataAccessException rde) {
			logger.error(rde.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "重新获得数据访问失败!" });
		} catch (TransientDataAccessException tdae) {
			logger.error(tdae.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "短暂数据访问!" });
		} catch (DataAccessException de) {
			logger.error(de.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "数据访问失败!" });
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DaoException("1001", new String[] { "selectObjects(" + sqlId + "," + paramObject + ")出错!!!", "其他访问出错!" });
		}
		return queryResult;
	}

	public int insertObject(String sqlId, Object paramObject) throws DaoException {
		try {
			 if (paramObject instanceof BaseEntity && null != SpringSecurityUtil.getCurrentUser()) {
				 BaseEntity baseEntity = (BaseEntity)paramObject;
				 baseEntity.setCreateBy(SpringSecurityUtil.getCurrentUser().getId());
				 baseEntity.setCreateTime(new Date());
			 }
			return this.sqlSession.insert(sqlId, paramObject);
		} catch (EmptyResultDataAccessException erde) {
			logger.error(erde.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "返回结果为空!" });
		} catch (JdbcUpdateAffectedIncorrectNumberOfRowsException juainoe) {
			logger.error(juainoe.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "错误更新数据行数!" });
		} catch (CannotGetJdbcConnectionException cgjce) {
			logger.error(cgjce.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "不能获得连接!" });
		} catch (CannotAcquireLockException cale) {
			logger.error(cale.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "不能获得锁!" });
		} catch (CannotSerializeTransactionException cste) {
			logger.error(cste.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "事务连接失败!" });
		} catch (DeadlockLoserDataAccessException dlde) {
			logger.error(dlde.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "数据死锁!" });
		} catch (IncorrectResultSizeDataAccessException irsde) {
			logger.error(irsde.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "结果大小超出范围!" });
		} catch (IncorrectResultSetColumnCountException irscce) {
			logger.error(irscce.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "不正确的结果集字段统计!" });
		} catch (LobRetrievalFailureException irsde) {
			logger.error(irsde.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "lob恢复失败!" });
		} catch (IncorrectUpdateSemanticsDataAccessException iusde) {
			logger.error(iusde.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "错误的数据更新语句!" });
		} catch (TypeMismatchDataAccessException tmde) {
			logger.error(tmde.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "数据类型不匹配!" });
		} catch (BadSqlGrammarException bge) {
			logger.error(bge.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "不正确的sql语句!" });
		} catch (InvalidResultSetAccessException irsae) {
			logger.error(irsae.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "不正确的结果集!" });
		} catch (DataAccessResourceFailureException darfe) {
			logger.error(darfe.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "资源访问失败!" });
		} catch (SQLWarningException sqe) {
			logger.error(sqe.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "sql警告!" });
		} catch (UncategorizedSQLException usqe) {
			logger.error(usqe.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "非类别sql问题!" });
		} catch (OptimisticLockingFailureException dive) {
			logger.error(dive.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "乐观锁失败，没有检测到数据库!" });
		} catch (PessimisticLockingFailureException plfe) {
			logger.error(plfe.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "悲观锁失败!" });
		} catch (CleanupFailureDataAccessException cfdae) {
			logger.error(cfdae.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "清除失败，可能连接已关闭!" });
		} catch (NonTransientDataAccessResourceException ntdare) {
			logger.error(ntdare.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "不是短暂的资源访问失败!" });
		} catch (DataIntegrityViolationException dive) {
			logger.error(dive.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "插入或更新数据库；违反数据完整性约束!" });
		} catch (DataRetrievalFailureException drfe) {
			logger.error(drfe.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "违反数据完整性!" });
		} catch (InvalidDataAccessApiUsageException idaae) {
			idaae.printStackTrace();
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "无效的使用api!" });
		} catch (InvalidDataAccessResourceUsageException idarue) {
			logger.error(idarue.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "无效的资源使用!" });
		} catch (PermissionDeniedDataAccessException pddae) {
			logger.error(pddae.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "许可访问拒绝!" });
		} catch (UncategorizedDataAccessException ude) {
			logger.error(ude.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "非类别数据访问问题!" });
		} catch (ConcurrencyFailureException cfe) {
			logger.error(cfe.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "同步失败!" });
		} catch (TransientDataAccessResourceException tdare) {
			logger.error(tdare.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "短暂数据访问资源失败!" });
		} catch (NonTransientDataAccessException ntdae) {
			logger.error(ntdae.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "非短暂的数据访问!" });
		} catch (RecoverableDataAccessException rde) {
			rde.printStackTrace();
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "重新获得数据访问失败!" });
		} catch (TransientDataAccessException tdae) {
			logger.error(tdae.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "短暂数据访问!" });
		} catch (DataAccessException de) {
			logger.error(de.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "数据访问失败!" });
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DaoException("1001", new String[] { "insertObject(" + sqlId + "," + paramObject + ")出错!!!", "其他访问出错!" });
		}
	}

	public void batchInsertObject(String sqlId, List<T> paramList) throws DaoException {
		SqlSession sqlBatchSession = this.sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		try {
			sqlBatchSession.getConnection().setAutoCommit(false);
			int batch = 0;
			int listSize = paramList.size();
			for (int i = 0; i < listSize; ++i) {
				sqlBatchSession.insert(sqlId, paramList.get(i));
				++batch;
				if (batch != 300)
					continue;
				sqlBatchSession.commit();

				sqlBatchSession.clearCache();
			}

			sqlBatchSession.commit();

			sqlBatchSession.clearCache();
		} catch (EmptyResultDataAccessException erde) {
			logger.error(erde.getMessage());
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (JdbcUpdateAffectedIncorrectNumberOfRowsException juainoe) {
			logger.error(juainoe.getMessage());
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (CannotGetJdbcConnectionException cgjce) {
			logger.error(cgjce.getMessage());
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (CannotAcquireLockException cale) {
			logger.error(cale.getMessage());
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (CannotSerializeTransactionException cste) {
			logger.error(cste.getMessage());
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (DeadlockLoserDataAccessException dlde) {
			logger.error(dlde.getMessage());
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (IncorrectResultSizeDataAccessException irsde) {
			logger.error(irsde.getMessage());
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (IncorrectResultSetColumnCountException irscce) {
			logger.error(irscce.getMessage());

			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (LobRetrievalFailureException irsde) {
			logger.error(irsde.getMessage());
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (IncorrectUpdateSemanticsDataAccessException iusde) {
			logger.error(iusde.getMessage());
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (TypeMismatchDataAccessException tmde) {
			logger.error(tmde.getMessage());
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (BadSqlGrammarException bge) {
			logger.error(bge.getMessage());
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (InvalidResultSetAccessException irsae) {
			logger.error(irsae.getMessage());
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (DataAccessResourceFailureException darfe) {
			logger.error(darfe.getMessage());
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (SQLWarningException sqe) {
			sqe.printStackTrace();
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (UncategorizedSQLException usqe) {
			usqe.printStackTrace();
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (OptimisticLockingFailureException dive) {
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (PessimisticLockingFailureException plfe) {
			plfe.printStackTrace();
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (CleanupFailureDataAccessException cfdae) {
			cfdae.printStackTrace();
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (NonTransientDataAccessResourceException ntdare) {
			ntdare.printStackTrace();
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (DataIntegrityViolationException dive) {
			dive.printStackTrace();
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (DataRetrievalFailureException drfe) {
			drfe.printStackTrace();
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (InvalidDataAccessApiUsageException idaae) {
			idaae.printStackTrace();
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (InvalidDataAccessResourceUsageException idarue) {
			idarue.printStackTrace();
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (PermissionDeniedDataAccessException pddae) {
			pddae.printStackTrace();
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (UncategorizedDataAccessException ude) {
			ude.printStackTrace();
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (ConcurrencyFailureException cfe) {
			cfe.printStackTrace();
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (TransientDataAccessResourceException tdare) {
			tdare.printStackTrace();
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (NonTransientDataAccessException ntdae) {
			ntdae.printStackTrace();
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (RecoverableDataAccessException rde) {
			rde.printStackTrace();
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (TransientDataAccessException tdae) {
			tdae.printStackTrace();
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (DataAccessException de) {
			de.printStackTrace();
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("1001", new String[] { "batchInsertObject(" + sqlId + "," + paramList + ")出错!!!" });
		} finally {
			sqlBatchSession.close();
		}
	}

	public int updateObject(String sqlId, Object paramObject) throws DaoException {
		try {
			if (paramObject instanceof BaseEntity && null != SpringSecurityUtil.getCurrentUser()) {
				 BaseEntity baseEntity = (BaseEntity)paramObject;
				 baseEntity.setModifyBy(SpringSecurityUtil.getCurrentUser().getId());
				 baseEntity.setModifyTime(new Date());
			 }
			return this.sqlSession.update(sqlId, paramObject);
		} catch (EmptyResultDataAccessException erde) {
			logger.error(erde.getMessage());
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "返回结果为空!" });
		} catch (JdbcUpdateAffectedIncorrectNumberOfRowsException juainoe) {
			logger.error(juainoe.getMessage());
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "错误更新数据行数!" });
		} catch (CannotGetJdbcConnectionException cgjce) {
			logger.error(cgjce.getMessage());
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "不能获得连接!" });
		} catch (CannotAcquireLockException cale) {
			logger.error(cale.getMessage());
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "不能获得锁!" });
		} catch (CannotSerializeTransactionException cste) {
			logger.error(cste.getMessage());
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "事务连接失败!" });
		} catch (DeadlockLoserDataAccessException dlde) {
			logger.error(dlde.getMessage());
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "数据死锁!" });
		} catch (IncorrectResultSizeDataAccessException irsde) {
			logger.error(irsde.getMessage());
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "结果大小超出范围!" });
		} catch (IncorrectResultSetColumnCountException irscce) {
			logger.error(irscce.getMessage());

			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "不正确的结果集字段统计!" });
		} catch (LobRetrievalFailureException irsde) {
			logger.error(irsde.getMessage());
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "lob恢复失败!" });
		} catch (IncorrectUpdateSemanticsDataAccessException iusde) {
			logger.error(iusde.getMessage());
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "错误的数据更新语句!" });
		} catch (TypeMismatchDataAccessException tmde) {
			logger.error(tmde.getMessage());
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "数据类型不匹配!" });
		} catch (BadSqlGrammarException bge) {
			logger.error(bge.getMessage());
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "不正确的sql语句!" });
		} catch (InvalidResultSetAccessException irsae) {
			logger.error(irsae.getMessage());
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "不正确的结果集!" });
		} catch (DataAccessResourceFailureException darfe) {
			logger.error(darfe.getMessage());
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "资源访问失败!" });
		} catch (SQLWarningException sqe) {
			sqe.printStackTrace();
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "sql警告!" });
		} catch (UncategorizedSQLException usqe) {
			usqe.printStackTrace();
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "非类别sql问题!" });
		} catch (OptimisticLockingFailureException dive) {
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "乐观锁失败，没有检测到数据库!" });
		} catch (PessimisticLockingFailureException plfe) {
			plfe.printStackTrace();
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "悲观锁失败!" });
		} catch (CleanupFailureDataAccessException cfdae) {
			cfdae.printStackTrace();
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "清除失败，可能连接已关闭!" });
		} catch (NonTransientDataAccessResourceException ntdare) {
			ntdare.printStackTrace();
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "不是短暂的资源访问失败!" });
		} catch (DataIntegrityViolationException dive) {
			dive.printStackTrace();
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "插入或更新数据库；违反数据完整性约束!" });
		} catch (DataRetrievalFailureException drfe) {
			drfe.printStackTrace();
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "违反数据完整性!" });
		} catch (InvalidDataAccessApiUsageException idaae) {
			idaae.printStackTrace();
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "无效的使用api!" });
		} catch (InvalidDataAccessResourceUsageException idarue) {
			idarue.printStackTrace();
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "无效的资源使用!" });
		} catch (PermissionDeniedDataAccessException pddae) {
			pddae.printStackTrace();
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "许可访问拒绝!" });
		} catch (UncategorizedDataAccessException ude) {
			ude.printStackTrace();
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "非类别数据访问问题!" });
		} catch (ConcurrencyFailureException cfe) {
			cfe.printStackTrace();
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "同步失败!" });
		} catch (TransientDataAccessResourceException tdare) {
			tdare.printStackTrace();
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "短暂数据访问资源失败!" });
		} catch (NonTransientDataAccessException ntdae) {
			ntdae.printStackTrace();
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "非短暂的数据访问!" });
		} catch (RecoverableDataAccessException rde) {
			rde.printStackTrace();
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "重新获得数据访问失败!" });
		} catch (TransientDataAccessException tdae) {
			tdae.printStackTrace();
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "短暂数据访问!" });
		} catch (DataAccessException de) {
			de.printStackTrace();
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "数据访问失败!" });
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("1001", new String[] { "updateObject(" + sqlId + "," + paramObject + ")出错!!!", "其他访问出错!" });
		}
	}

	public void batchUpdateObject(String sqlId, List<T> paramList) throws DaoException {
		SqlSession sqlBatchSession = this.sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		try {
			sqlBatchSession.getConnection().setAutoCommit(false);
			int batch = 0;
			int listSize = paramList.size();
			for (int i = 0; i < listSize; ++i) {
				sqlBatchSession.update(sqlId, paramList.get(i));
				++batch;
				if (batch != 300)
					continue;
				sqlBatchSession.commit();

				sqlBatchSession.clearCache();
			}

			sqlBatchSession.commit();

			sqlBatchSession.clearCache();
		} catch (EmptyResultDataAccessException erde) {
			logger.error(erde.getMessage());
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (JdbcUpdateAffectedIncorrectNumberOfRowsException juainoe) {
			logger.error(juainoe.getMessage());
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (CannotGetJdbcConnectionException cgjce) {
			logger.error(cgjce.getMessage());
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (CannotAcquireLockException cale) {
			logger.error(cale.getMessage());
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (CannotSerializeTransactionException cste) {
			logger.error(cste.getMessage());
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (DeadlockLoserDataAccessException dlde) {
			logger.error(dlde.getMessage());
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (IncorrectResultSizeDataAccessException irsde) {
			logger.error(irsde.getMessage());
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (IncorrectResultSetColumnCountException irscce) {
			logger.error(irscce.getMessage());

			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (LobRetrievalFailureException irsde) {
			logger.error(irsde.getMessage());
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (IncorrectUpdateSemanticsDataAccessException iusde) {
			logger.error(iusde.getMessage());
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (TypeMismatchDataAccessException tmde) {
			logger.error(tmde.getMessage());
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (BadSqlGrammarException bge) {
			logger.error(bge.getMessage());
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (InvalidResultSetAccessException irsae) {
			logger.error(irsae.getMessage());
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (DataAccessResourceFailureException darfe) {
			logger.error(darfe.getMessage());
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (SQLWarningException sqe) {
			sqe.printStackTrace();
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (UncategorizedSQLException usqe) {
			usqe.printStackTrace();
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (OptimisticLockingFailureException dive) {
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (PessimisticLockingFailureException plfe) {
			plfe.printStackTrace();
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (CleanupFailureDataAccessException cfdae) {
			cfdae.printStackTrace();
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (NonTransientDataAccessResourceException ntdare) {
			ntdare.printStackTrace();
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (DataIntegrityViolationException dive) {
			dive.printStackTrace();
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (DataRetrievalFailureException drfe) {
			drfe.printStackTrace();
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (InvalidDataAccessApiUsageException idaae) {
			idaae.printStackTrace();
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (InvalidDataAccessResourceUsageException idarue) {
			idarue.printStackTrace();
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (PermissionDeniedDataAccessException pddae) {
			pddae.printStackTrace();
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (UncategorizedDataAccessException ude) {
			ude.printStackTrace();
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (ConcurrencyFailureException cfe) {
			cfe.printStackTrace();
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (TransientDataAccessResourceException tdare) {
			tdare.printStackTrace();
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (NonTransientDataAccessException ntdae) {
			ntdae.printStackTrace();
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (RecoverableDataAccessException rde) {
			rde.printStackTrace();
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (TransientDataAccessException tdae) {
			tdae.printStackTrace();
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (DataAccessException de) {
			de.printStackTrace();
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("1001", new String[] { "batchUpdateObject(" + sqlId + "," + paramList + ")出错!!!" });
		} finally {
			sqlBatchSession.close();
		}
	}

	public int deleteObject(String sqlId, Object paramObject) throws DaoException {
		try {
			return this.sqlSession.delete(sqlId, paramObject);
		} catch (EmptyResultDataAccessException erde) {
			logger.error(erde.getMessage());
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "返回结果为空!" });
		} catch (JdbcUpdateAffectedIncorrectNumberOfRowsException juainoe) {
			logger.error(juainoe.getMessage());
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "错误更新数据行数!" });
		} catch (CannotGetJdbcConnectionException cgjce) {
			logger.error(cgjce.getMessage());
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "不能获得连接!" });
		} catch (CannotAcquireLockException cale) {
			logger.error(cale.getMessage());
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "不能获得锁!" });
		} catch (CannotSerializeTransactionException cste) {
			logger.error(cste.getMessage());
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "事务连接失败!" });
		} catch (DeadlockLoserDataAccessException dlde) {
			logger.error(dlde.getMessage());
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "数据死锁!" });
		} catch (IncorrectResultSizeDataAccessException irsde) {
			logger.error(irsde.getMessage());
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "结果大小超出范围!" });
		} catch (IncorrectResultSetColumnCountException irscce) {
			logger.error(irscce.getMessage());

			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "不正确的结果集字段统计!" });
		} catch (LobRetrievalFailureException irsde) {
			logger.error(irsde.getMessage());
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "lob恢复失败!" });
		} catch (IncorrectUpdateSemanticsDataAccessException iusde) {
			logger.error(iusde.getMessage());
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "错误的数据更新语句!" });
		} catch (TypeMismatchDataAccessException tmde) {
			logger.error(tmde.getMessage());
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "数据类型不匹配!" });
		} catch (BadSqlGrammarException bge) {
			logger.error(bge.getMessage());
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "不正确的sql语句!" });
		} catch (InvalidResultSetAccessException irsae) {
			logger.error(irsae.getMessage());
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "不正确的结果集!" });
		} catch (DataAccessResourceFailureException darfe) {
			logger.error(darfe.getMessage());
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "资源访问失败!" });
		} catch (SQLWarningException sqe) {
			sqe.printStackTrace();
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "sql警告!" });
		} catch (UncategorizedSQLException usqe) {
			usqe.printStackTrace();
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "非类别sql问题!" });
		} catch (OptimisticLockingFailureException dive) {
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "乐观锁失败，没有检测到数据库!" });
		} catch (PessimisticLockingFailureException plfe) {
			plfe.printStackTrace();
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "悲观锁失败!" });
		} catch (CleanupFailureDataAccessException cfdae) {
			cfdae.printStackTrace();
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "清除失败，可能连接已关闭!" });
		} catch (NonTransientDataAccessResourceException ntdare) {
			ntdare.printStackTrace();
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "不是短暂的资源访问失败!" });
		} catch (DataIntegrityViolationException dive) {
			dive.printStackTrace();
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "插入或更新数据库；违反数据完整性约束!" });
		} catch (DataRetrievalFailureException drfe) {
			drfe.printStackTrace();
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "违反数据完整性!" });
		} catch (InvalidDataAccessApiUsageException idaae) {
			idaae.printStackTrace();
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "无效的使用api!" });
		} catch (InvalidDataAccessResourceUsageException idarue) {
			idarue.printStackTrace();
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "无效的资源使用!" });
		} catch (PermissionDeniedDataAccessException pddae) {
			pddae.printStackTrace();
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "许可访问拒绝!" });
		} catch (UncategorizedDataAccessException ude) {
			ude.printStackTrace();
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "非类别数据访问问题!" });
		} catch (ConcurrencyFailureException cfe) {
			cfe.printStackTrace();
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "同步失败!" });
		} catch (TransientDataAccessResourceException tdare) {
			tdare.printStackTrace();
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "短暂数据访问资源失败!" });
		} catch (NonTransientDataAccessException ntdae) {
			ntdae.printStackTrace();
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "非短暂的数据访问!" });
		} catch (RecoverableDataAccessException rde) {
			rde.printStackTrace();
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "重新获得数据访问失败!" });
		} catch (TransientDataAccessException tdae) {
			tdae.printStackTrace();
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "短暂数据访问!" });
		} catch (DataAccessException de) {
			de.printStackTrace();
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "数据访问失败!" });
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("1001", new String[] { "deleteObject(" + sqlId + "," + paramObject + ")出错!!!", "其他访问出错!" });
		}
	}

	public void batchDeleteObject(String sqlId, List<T> paramList) throws DaoException {
		SqlSession sqlBatchSession = this.sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		try {
			sqlBatchSession.getConnection().setAutoCommit(false);
			int batch = 0;
			int listSize = paramList.size();
			for (int i = 0; i < listSize; ++i) {
				sqlBatchSession.delete(sqlId, paramList.get(i));
				++batch;
				if (batch != 300)
					continue;
				sqlBatchSession.commit();

				sqlBatchSession.clearCache();
			}

			sqlBatchSession.commit();

			sqlBatchSession.clearCache();
		} catch (EmptyResultDataAccessException erde) {
			logger.error(erde.getMessage());
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (JdbcUpdateAffectedIncorrectNumberOfRowsException juainoe) {
			logger.error(juainoe.getMessage());
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (CannotGetJdbcConnectionException cgjce) {
			logger.error(cgjce.getMessage());
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (CannotAcquireLockException cale) {
			logger.error(cale.getMessage());
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (CannotSerializeTransactionException cste) {
			logger.error(cste.getMessage());
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (DeadlockLoserDataAccessException dlde) {
			logger.error(dlde.getMessage());
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (IncorrectResultSizeDataAccessException irsde) {
			logger.error(irsde.getMessage());
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (IncorrectResultSetColumnCountException irscce) {
			logger.error(irscce.getMessage());

			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (LobRetrievalFailureException irsde) {
			logger.error(irsde.getMessage());
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (IncorrectUpdateSemanticsDataAccessException iusde) {
			logger.error(iusde.getMessage());
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (TypeMismatchDataAccessException tmde) {
			logger.error(tmde.getMessage());
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (BadSqlGrammarException bge) {
			logger.error(bge.getMessage());
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (InvalidResultSetAccessException irsae) {
			logger.error(irsae.getMessage());
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (DataAccessResourceFailureException darfe) {
			logger.error(darfe.getMessage());
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (SQLWarningException sqe) {
			sqe.printStackTrace();
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (UncategorizedSQLException usqe) {
			usqe.printStackTrace();
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (OptimisticLockingFailureException dive) {
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (PessimisticLockingFailureException plfe) {
			plfe.printStackTrace();
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (CleanupFailureDataAccessException cfdae) {
			cfdae.printStackTrace();
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (NonTransientDataAccessResourceException ntdare) {
			ntdare.printStackTrace();
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (DataIntegrityViolationException dive) {
			dive.printStackTrace();
			throw new DaoException("1001", new String[] { "batchDeleteObjectbject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (DataRetrievalFailureException drfe) {
			drfe.printStackTrace();
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (InvalidDataAccessApiUsageException idaae) {
			idaae.printStackTrace();
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (InvalidDataAccessResourceUsageException idarue) {
			idarue.printStackTrace();
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (PermissionDeniedDataAccessException pddae) {
			pddae.printStackTrace();
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (UncategorizedDataAccessException ude) {
			ude.printStackTrace();
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (ConcurrencyFailureException cfe) {
			cfe.printStackTrace();
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (TransientDataAccessResourceException tdare) {
			tdare.printStackTrace();
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (NonTransientDataAccessException ntdae) {
			ntdae.printStackTrace();
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (RecoverableDataAccessException rde) {
			rde.printStackTrace();
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (TransientDataAccessException tdae) {
			tdae.printStackTrace();
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (DataAccessException de) {
			de.printStackTrace();
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("1001", new String[] { "batchDeleteObject(" + sqlId + "," + paramList + ")出错!!!" });
		} finally {
			sqlBatchSession.close();
		}
	}

	public long countObject(String sqlId, Object paramObject) throws DaoException {
		try {
			return ((Long) this.sqlSession.selectOne(sqlId, paramObject)).longValue();
		} catch (EmptyResultDataAccessException erde) {
			logger.error(erde.getMessage());
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "返回结果为空!" });
		} catch (JdbcUpdateAffectedIncorrectNumberOfRowsException juainoe) {
			logger.error(juainoe.getMessage());
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "错误更新数据行数!" });
		} catch (CannotGetJdbcConnectionException cgjce) {
			logger.error(cgjce.getMessage());
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "不能获得连接!" });
		} catch (CannotAcquireLockException cale) {
			logger.error(cale.getMessage());
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "不能获得锁!" });
		} catch (CannotSerializeTransactionException cste) {
			logger.error(cste.getMessage());
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "事务连接失败!" });
		} catch (DeadlockLoserDataAccessException dlde) {
			logger.error(dlde.getMessage());
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "数据死锁!" });
		} catch (IncorrectResultSizeDataAccessException irsde) {
			logger.error(irsde.getMessage());
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "结果大小超出范围!" });
		} catch (IncorrectResultSetColumnCountException irscce) {
			logger.error(irscce.getMessage());

			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "不正确的结果集字段统计!" });
		} catch (LobRetrievalFailureException irsde) {
			logger.error(irsde.getMessage());
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "lob恢复失败!" });
		} catch (IncorrectUpdateSemanticsDataAccessException iusde) {
			logger.error(iusde.getMessage());
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "错误的数据更新语句!" });
		} catch (TypeMismatchDataAccessException tmde) {
			logger.error(tmde.getMessage());
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "数据类型不匹配!" });
		} catch (BadSqlGrammarException bge) {
			logger.error(bge.getMessage());
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "不正确的sql语句!" });
		} catch (InvalidResultSetAccessException irsae) {
			logger.error(irsae.getMessage());
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "不正确的结果集!" });
		} catch (DataAccessResourceFailureException darfe) {
			logger.error(darfe.getMessage());
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "资源访问失败!" });
		} catch (SQLWarningException sqe) {
			sqe.printStackTrace();
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "sql警告!" });
		} catch (UncategorizedSQLException usqe) {
			usqe.printStackTrace();
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "非类别sql问题!" });
		} catch (OptimisticLockingFailureException dive) {
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "乐观锁失败，没有检测到数据库!" });
		} catch (PessimisticLockingFailureException plfe) {
			plfe.printStackTrace();
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "悲观锁失败!" });
		} catch (CleanupFailureDataAccessException cfdae) {
			cfdae.printStackTrace();
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "清除失败，可能连接已关闭!" });
		} catch (NonTransientDataAccessResourceException ntdare) {
			ntdare.printStackTrace();
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "不是短暂的资源访问失败!" });
		} catch (DataIntegrityViolationException dive) {
			dive.printStackTrace();
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "插入或更新数据库；违反数据完整性约束!" });
		} catch (DataRetrievalFailureException drfe) {
			drfe.printStackTrace();
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "违反数据完整性!" });
		} catch (InvalidDataAccessApiUsageException idaae) {
			idaae.printStackTrace();
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "无效的使用api!" });
		} catch (InvalidDataAccessResourceUsageException idarue) {
			idarue.printStackTrace();
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "无效的资源使用!" });
		} catch (PermissionDeniedDataAccessException pddae) {
			pddae.printStackTrace();
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "许可访问拒绝!" });
		} catch (UncategorizedDataAccessException ude) {
			ude.printStackTrace();
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "非类别数据访问问题!" });
		} catch (ConcurrencyFailureException cfe) {
			cfe.printStackTrace();
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "同步失败!" });
		} catch (TransientDataAccessResourceException tdare) {
			tdare.printStackTrace();
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "短暂数据访问资源失败!" });
		} catch (NonTransientDataAccessException ntdae) {
			ntdae.printStackTrace();
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "非短暂的数据访问!" });
		} catch (RecoverableDataAccessException rde) {
			rde.printStackTrace();
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "重新获得数据访问失败!" });
		} catch (TransientDataAccessException tdae) {
			tdae.printStackTrace();
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "短暂数据访问!" });
		} catch (DataAccessException de) {
			de.printStackTrace();
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "数据访问失败!" });
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("1001", new String[] { "countObject(" + sqlId + "," + paramObject + ")出错!!!", "其他访问出错!" });
		}
	}

	public List<T> selectList(String sqlId, Object condition, PaginationMore page) throws DaoException {
		List queryResult = null;
		try {
			queryResult = this.sqlSession.selectList(sqlId, condition, new RowBounds(page.getStartNum(), page.getPageSize()));
		} catch (EmptyResultDataAccessException erde) {
			logger.error(erde.getMessage());
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "返回结果为空!" });
		} catch (JdbcUpdateAffectedIncorrectNumberOfRowsException juainoe) {
			logger.error(juainoe.getMessage());
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "错误更新数据行数!" });
		} catch (CannotGetJdbcConnectionException cgjce) {
			logger.error(cgjce.getMessage());
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "不能获得连接!" });
		} catch (CannotAcquireLockException cale) {
			logger.error(cale.getMessage());
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "不能获得锁!" });
		} catch (CannotSerializeTransactionException cste) {
			logger.error(cste.getMessage());
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "事务连接失败!" });
		} catch (DeadlockLoserDataAccessException dlde) {
			logger.error(dlde.getMessage());
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "数据死锁!" });
		} catch (IncorrectResultSizeDataAccessException irsde) {
			logger.error(irsde.getMessage());
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "结果大小超出范围!" });
		} catch (IncorrectResultSetColumnCountException irscce) {
			logger.error(irscce.getMessage());

			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "不正确的结果集字段统计!" });
		} catch (LobRetrievalFailureException irsde) {
			logger.error(irsde.getMessage());
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "lob恢复失败!" });
		} catch (IncorrectUpdateSemanticsDataAccessException iusde) {
			logger.error(iusde.getMessage());
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "错误的数据更新语句!" });
		} catch (TypeMismatchDataAccessException tmde) {
			logger.error(tmde.getMessage());
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "数据类型不匹配!" });
		} catch (BadSqlGrammarException bge) {
			logger.error(bge.getMessage());
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "不正确的sql语句!" });
		} catch (InvalidResultSetAccessException irsae) {
			logger.error(irsae.getMessage());
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "不正确的结果集!" });
		} catch (DataAccessResourceFailureException darfe) {
			logger.error(darfe.getMessage());
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "资源访问失败!" });
		} catch (SQLWarningException sqe) {
			sqe.printStackTrace();
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "sql警告!" });
		} catch (UncategorizedSQLException usqe) {
			usqe.printStackTrace();
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "非类别sql问题!" });
		} catch (OptimisticLockingFailureException dive) {
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "乐观锁失败，没有检测到数据库!" });
		} catch (PessimisticLockingFailureException plfe) {
			plfe.printStackTrace();
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "悲观锁失败!" });
		} catch (CleanupFailureDataAccessException cfdae) {
			cfdae.printStackTrace();
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "清除失败，可能连接已关闭!" });
		} catch (NonTransientDataAccessResourceException ntdare) {
			ntdare.printStackTrace();
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "不是短暂的资源访问失败!" });
		} catch (DataIntegrityViolationException dive) {
			dive.printStackTrace();
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "插入或更新数据库；违反数据完整性约束!" });
		} catch (DataRetrievalFailureException drfe) {
			drfe.printStackTrace();
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "违反数据完整性!" });
		} catch (InvalidDataAccessApiUsageException idaae) {
			idaae.printStackTrace();
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "无效的使用api!" });
		} catch (InvalidDataAccessResourceUsageException idarue) {
			idarue.printStackTrace();
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "无效的资源使用!" });
		} catch (PermissionDeniedDataAccessException pddae) {
			pddae.printStackTrace();
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "许可访问拒绝!" });
		} catch (UncategorizedDataAccessException ude) {
			ude.printStackTrace();
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "非类别数据访问问题!" });
		} catch (ConcurrencyFailureException cfe) {
			cfe.printStackTrace();
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "同步失败!" });
		} catch (TransientDataAccessResourceException tdare) {
			tdare.printStackTrace();
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "短暂数据访问资源失败!" });
		} catch (NonTransientDataAccessException ntdae) {
			ntdae.printStackTrace();
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "非短暂的数据访问!" });
		} catch (RecoverableDataAccessException rde) {
			rde.printStackTrace();
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "重新获得数据访问失败!" });
		} catch (TransientDataAccessException tdae) {
			tdae.printStackTrace();
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "短暂数据访问!" });
		} catch (DataAccessException de) {
			de.printStackTrace();
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "数据访问失败!" });
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("1001", new String[] { "selectList(" + sqlId + "," + condition + "," + page.toString() + ")出错!!!", "其他访问出错!" });
		}
		return queryResult;
	}
}
