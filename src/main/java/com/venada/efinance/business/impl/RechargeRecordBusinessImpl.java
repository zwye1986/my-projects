package com.venada.efinance.business.impl;

import com.venada.efinance.business.RechargeRecordBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.exception.ServiceException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.RechargeRecord;
import com.venada.efinance.service.RechargeRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class RechargeRecordBusinessImpl implements RechargeRecordBusiness {
	private static final Logger logger = LoggerFactory.getLogger(RechargeRecordBusinessImpl.class);
	
	@Autowired
	private RechargeRecordService rechargeRecordService ;

    @Override
    public List<RechargeRecord> getRechargeRecords(Map<String, Object> condition, PaginationMore page)
            throws BusinessException {
        try {
            return rechargeRecordService.selectList("getRechargeRecords", condition, page);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            throw new BusinessException("0002", new String[] { "getRechargeRecords()方法出错！\t", e.getMessage() });
        }
    }

	@Override
	public List<RechargeRecord> queryRechargeRecordsByMobilePhone(Map<String, Object> condition)
			throws BusinessException {
		try {
		return rechargeRecordService.findObjects("queryRechargeRecordsByMobilePhone",condition);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] { "queryRechargeRecordsByMobilePhone()方法出错！\t", e.getMessage() });
		}
	}

    @Override
    public List<RechargeRecord> queryRechargeRecordsForMobile(Map<String, Object> condition) throws BusinessException {
        return rechargeRecordService.findObjects("queryRechargeRecordsForMobile", condition);
    }

    public int getRechargeRecordsCount(Map<String, Object> condition) throws BusinessException {
		try {
			return  (Integer) rechargeRecordService.getObject("getRechargeRecordsCount", condition);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] { "getRechargeRecordsCount()方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public BigDecimal getRechargeAmountByUserId(String userid)
			throws BusinessException {
		try {
			BigDecimal bigDecimal = (BigDecimal)rechargeRecordService.getObject("getRechargeAmountByUserId", userid);
			if(bigDecimal != null){
				bigDecimal = bigDecimal.setScale(2,BigDecimal.ROUND_DOWN);
			}
			
			return bigDecimal;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] { "getRechargeAmountByUserId()方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public List<RechargeRecord> getAllRechargeRecords(
			Map<String, Object> condition, PaginationMore page)
			throws BusinessException {
		try {
			page.setTotalRows(getAllRechargeRecordsCount(condition));
			page.repaginate();
			return rechargeRecordService.selectList("getAllRechargeRecords",
					condition, page);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"getRechargeRecords()方法出错！\t", e.getMessage() });
		}
	}

    @Override
    public List<RechargeRecord> getRechargeRecordesByConditionToExport(Map<String, Object> condition) throws BusinessException {
        return rechargeRecordService.findObjects("getAllRechargeRecords",condition);
    }

    @Override
	public List<RechargeRecord> getRechargeRecordsForIndex() throws BusinessException {
		try {
			return rechargeRecordService.findObjects("getRechargeRecordsForIndex",null);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"getRechargeRecords()方法出错！\t", e.getMessage() });
		}
	}

    @Override
    public int getAllRechargeRecordsCount(Map<String, Object> condition)
            throws BusinessException {
        try {
            return (Integer) rechargeRecordService.getObject(
                    "getAllRechargeRecordsCount", condition);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            throw new BusinessException("0002", new String[] {
                    "getRechargeRecordsCount()方法出错！\t", e.getMessage() });
        }
    }


    @Transactional
	public int getAllRechargeRecordsCountForIPN(Map<String,Object> condition) throws BusinessException{

		try {
			return (Integer) rechargeRecordService.getObject(
					"getAllRechargeRecordsCountForIPN", condition);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"getRechargeRecordsCount()方法出错！\t", e.getMessage() });
		}
	
	}

	@Override
	public BigDecimal countTodayRechargeAmountByUserid(String userid)
			throws BusinessException {
		return (BigDecimal) rechargeRecordService.getObject("countTodayRechargeAmountByUserid", userid);
	}

}
