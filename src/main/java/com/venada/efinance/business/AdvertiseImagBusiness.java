package com.venada.efinance.business;


import com.venada.efinance.pojo.AdvertiseImag;

public interface AdvertiseImagBusiness {
	public void saveAdvertiseImag( AdvertiseImag advertiseImag);
	public AdvertiseImag getAdvertiseImagById(String id);
	public AdvertiseImag getAdvertiseImagByAdvertiseId(String advertiseid);
	public int getCountByAdvertiseId(String advertiseId);
	public void updateAdvertiseImg(AdvertiseImag advertiseImag);
	public void deleteAdvertiseImg (String advertiseId);
}
