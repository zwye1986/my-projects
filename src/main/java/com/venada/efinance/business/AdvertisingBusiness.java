package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.Advertising;

import java.util.List;

public interface AdvertisingBusiness {
	public void saveAdvertising(Advertising advertising);
	public List<Advertising> getAdvertising (PaginationMore page);
	public Advertising getAdvertisingById(String id);
	public void updateAdvertise(Advertising advertising);
	public void deleteAdvertise(String id);
	public List<Advertising> getAdvertiseByType(int type) throws BusinessException;

    public List<Advertising> getAdvertiseListByPosition(Integer location) throws BusinessException;
}
