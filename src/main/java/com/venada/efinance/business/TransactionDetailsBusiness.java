package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.TransactionDetails;

import java.util.List;
import java.util.Map;

public interface TransactionDetailsBusiness {
	
	public List<TransactionDetails> queryAllTransactionDetails(Map<String,Object> condition,PaginationMore page) throws BusinessException ;
	
	public List<TransactionDetails> queryAllTransactionDetails(Map<String,Object> condition) throws BusinessException ;
	
	public List<TransactionDetails> getWithdrawalRecords(Map<String,Object> condition,PaginationMore page) throws BusinessException ;

    public List<TransactionDetails> queryTransactionDetailsForMobile(Map<String,Object> conditions) throws BusinessException;
    public int getTransactionDetailsCount(Map<String,Object> condition) throws BusinessException ;
    public int getWithdrawalRecordsCount(Map<String,Object> condition) throws BusinessException ;
}
