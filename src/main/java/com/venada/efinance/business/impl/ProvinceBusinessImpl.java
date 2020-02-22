package com.venada.efinance.business.impl;

import com.venada.efinance.business.ProvinceBusiness;
import com.venada.efinance.pojo.Province;
import com.venada.efinance.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProvinceBusinessImpl implements ProvinceBusiness {
	@Autowired
	private ProvinceService provinceService;
	@Override
	public List<Province> findAllProvinceList() {
		return provinceService.findObjects("findAllProvinceList", null);
	}
	
	
	@Override
	public Province getProvinceById(Integer id) {
		return (Province) provinceService.getObject("getProvinceById", id);
	}

}
