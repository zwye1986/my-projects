package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.BankCity;
import com.venada.efinance.pojo.City;

import java.util.List;
public interface CityBusiness {
	public List<City> findCityListByProvinceid(int provinceid) throws BusinessException;
	public List<BankCity> findBankCityListByProvinceid(int provinceid) throws BusinessException;
	
	public City getCityById (int cityId) throws BusinessException;
}
