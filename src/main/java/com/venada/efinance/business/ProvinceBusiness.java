package com.venada.efinance.business;

import com.venada.efinance.pojo.Province;

import java.util.List;

public interface ProvinceBusiness {
	public List<Province> findAllProvinceList();

	public Province getProvinceById(Integer id);
}
