package com.venada.efinance.business.impl;


import com.venada.efinance.business.AdvertiseImagBusiness;
import com.venada.efinance.pojo.AdvertiseImag;
import com.venada.efinance.service.AdvertiseImagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AdvertiseImagBusinessImpl implements AdvertiseImagBusiness {
	@Autowired
	private AdvertiseImagService advertiseImagService;
	@Override
	public void saveAdvertiseImag(AdvertiseImag advertiseImag) {
		advertiseImagService.saveObject("saveAdvertiseImag", advertiseImag);
	}
	@Override
	public AdvertiseImag getAdvertiseImagById(String id) {
		return (AdvertiseImag)advertiseImagService.getObject("getAdvertiseImagById", id);
	}
	@Override
	public int getCountByAdvertiseId(String advertiseId) {
		return (Integer) advertiseImagService.getObject("getCountByAdvertiseId", advertiseId);
	}
	@Override
	public void updateAdvertiseImg(AdvertiseImag advertiseImag) {
		advertiseImagService.updateObject("updateAdvertiseImg", advertiseImag);
	}
	@Override
	public void deleteAdvertiseImg(String advertiseId) {
		advertiseImagService.updateObject("deleteAdvertiseImg", advertiseId);
	}
	@Override
	public AdvertiseImag getAdvertiseImagByAdvertiseId(String advertiseid) {
		return (AdvertiseImag)advertiseImagService.getObject("getAdvertiseImagByAdvertiseId", advertiseid);
	}

}
