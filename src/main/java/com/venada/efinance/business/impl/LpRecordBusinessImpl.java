package com.venada.efinance.business.impl;


import com.venada.efinance.business.LpRecordBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.LpRecord;
import com.venada.efinance.service.LpRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/***
 * 
 * @author xupei
 *
 */
@Service("lpRecordBusiness")
public class LpRecordBusinessImpl implements LpRecordBusiness{
	@Autowired
	private LpRecordService lpRecordService;
	@Override
	public List<LpRecord> queryLpRecord(Map<String, Object> map)
			throws BusinessException {
		return lpRecordService.findObjects("queryLpRecord", map);
	}
	@Override
	public void addLpRecord(LpRecord lpRecord) throws BusinessException {
		lpRecordService.saveObject("addLpRecord", lpRecord);
	}
	

}
