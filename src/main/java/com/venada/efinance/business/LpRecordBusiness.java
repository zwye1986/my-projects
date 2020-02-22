package com.venada.efinance.business;


import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.LpRecord;

import java.util.List;
import java.util.Map;


/***
 * 
 * @author xupei
 *
 */

public interface LpRecordBusiness {
	public List<LpRecord> queryLpRecord(Map<String,Object> map) throws  BusinessException;
	public void addLpRecord(LpRecord lpRecord) throws  BusinessException;
}
