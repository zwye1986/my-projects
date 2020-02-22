package com.venada.efinance.business.impl;

import com.venada.efinance.business.TransactionDetailsBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.TransactionDetails;
import com.venada.efinance.service.TransactionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class TransactionDetailsBusinessImpl implements
		TransactionDetailsBusiness {
	@Autowired
	private TransactionDetailsService transactionDetailsService;

	@Override
	public List<TransactionDetails> queryAllTransactionDetails(
			Map<String, Object> condition, PaginationMore page)
			throws BusinessException {
        return transactionDetailsService.selectList("queryAllTransactionDetails", condition, page);
	}

    @Override
    public int getTransactionDetailsCount(Map<String, Object> condition)
            throws BusinessException {
        return (Integer) transactionDetailsService.getObject("getTransactionDetailsCount", condition);
    }

    @Override
    public List<TransactionDetails> getWithdrawalRecords(
            Map<String, Object> condition, PaginationMore page)
            throws BusinessException {
        return transactionDetailsService.selectList("getWithdrawalRecords", condition, page);
    }

    @Override
    public List<TransactionDetails> queryTransactionDetailsForMobile(Map<String, Object> conditions) throws BusinessException {
        return transactionDetailsService.findObjects("queryTransactionDetailsForMobile", conditions);
    }

    @Override
    public int getWithdrawalRecordsCount(Map<String, Object> condition)
            throws BusinessException {
        return (Integer) transactionDetailsService.getObject("getWithdrawalRecordsCount", condition);
    }

	@Override
	public List<TransactionDetails> queryAllTransactionDetails(
			Map<String, Object> condition) throws BusinessException {
		return transactionDetailsService.findObjects("queryAllTransactionDetails", condition);
	}
	
}
