package com.venada.efinance.business.impl;

import com.venada.efinance.business.AdvertisingBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.Advertising;
import com.venada.efinance.service.AdvertisingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdvertisingBusinessImpl implements AdvertisingBusiness {
	@Autowired
	private AdvertisingService advertisingService;
	@Override
	public void saveAdvertising(Advertising advertising) {
		advertisingService.saveObject("saveAdvertising", advertising);
	}
	@Override
	public List<Advertising> getAdvertising(PaginationMore page) {
		page.setTotalRows(getAdvertisingCount());
		page.repaginate();
		return advertisingService.selectList("getAdvertising", null,page);
	}
	@Override
	public Advertising getAdvertisingById(String id) {
		return (Advertising) advertisingService.getObject("getAdvertisingById", id);
	}
	@Override
	public void updateAdvertise(Advertising advertising) {
		advertisingService.updateObject("updateAdvertise", advertising);
	}
	@Override
	public void deleteAdvertise(String id) {
		advertisingService.updateObject("deleteAdvertise", id);
	}
	
	private int getAdvertisingCount(){
		return (Integer) advertisingService.getObject("getAdvertisingCount", null);
	}
	@Override
	public List<Advertising> getAdvertiseByType(int type)
			throws BusinessException {
		return advertisingService.findObjects("getAdvertiseByType", type);
	}

    @Override
    public List<Advertising> getAdvertiseListByPosition(Integer location) throws BusinessException {
        return advertisingService.findObjects("getAdvertiseListByPosition",location);
    }

}
