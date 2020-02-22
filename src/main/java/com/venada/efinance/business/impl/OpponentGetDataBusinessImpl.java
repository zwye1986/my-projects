package com.venada.efinance.business.impl;


import com.venada.efinance.business.OpponentGetDataBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.OpponentData;
import com.venada.efinance.service.OpponentGetdataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class OpponentGetDataBusinessImpl implements OpponentGetDataBusiness {
	@Autowired
	private OpponentGetdataService opponentGetdataServiceImpl;

	@Override
	public void addOpponentData(OpponentData opponentData) throws BusinessException{
		opponentGetdataServiceImpl.saveObject("addOpponentData", opponentData);
	}

	@Override
	public List<OpponentData> getOpponentDataById(Map<String,Object>condition) {
		return ( List<OpponentData>) opponentGetdataServiceImpl.findObjects("getOpponentDataById", condition);
	}
	
	

}
