package com.venada.efinance.business;


import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.OpponentData;

import java.util.List;
import java.util.Map;

public interface OpponentGetDataBusiness {
	public void addOpponentData  ( OpponentData opponentData)throws BusinessException;
	public List<OpponentData>  getOpponentDataById(Map<String,Object>condition);
	
}
