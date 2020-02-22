package com.venada.efinance.business.impl;

import com.venada.efinance.business.CityBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.BankCity;
import com.venada.efinance.pojo.City;
import com.venada.efinance.service.BankCityService;
import com.venada.efinance.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CityBusinessImpl implements CityBusiness {
	@Autowired
private CityService cityService;
	@Autowired
	private BankCityService bankCityService;
	@Override
	public List<City> findCityListByProvinceid(int provinceid) {
		return cityService.findObjects("findCityListByProvinceid", provinceid);
	}
	@Override
	public List<BankCity> findBankCityListByProvinceid(int provinceid)
			throws BusinessException {
		return bankCityService.findObjects("findBankCityListByProvinceid", provinceid);
	}
	
	@Override
	public City getCityById(int cityId) throws BusinessException {
		return (City) cityService.getObject("getCityById", cityId);
	}

}
