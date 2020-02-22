package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.Area;

import java.util.List;
public interface AreaBusiness {
	public List<Area> findAreaListByCityid(int cityid);
	public Area getAreaById(int id) throws BusinessException;
}
