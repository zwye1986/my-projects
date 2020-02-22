package com.venada.efinance.business.impl;

import com.venada.efinance.business.AreaBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.Area;
import com.venada.efinance.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AreaBusinessImpl implements AreaBusiness {

	@Autowired
	private AreaService areaService;
	@Override
	public List<Area> findAreaListByCityid(int cityid) {
		return areaService.findObjects("findAreaListByCityid", cityid);
	}
	@Override
	public Area getAreaById(int id) throws BusinessException {
		return (Area) areaService.getObject("getAreaById", id);
	}

}
