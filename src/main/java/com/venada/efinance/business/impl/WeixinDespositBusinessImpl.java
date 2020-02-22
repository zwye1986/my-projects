package com.venada.efinance.business.impl;

import com.venada.efinance.business.WeixinDespositBusiness;
import com.venada.efinance.pojo.WeixinDesposit;
import com.venada.efinance.service.WeixinDespositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 * 
 * @author hepei
 * 
 */
@Service
public class WeixinDespositBusinessImpl implements WeixinDespositBusiness {
	@Autowired
	private WeixinDespositService weixinDespositServiceImpl;
	@Override
	public List<WeixinDesposit> listWeixinDesposit(String mobileNumber) {
		return weixinDespositServiceImpl.findObjects("listWeixinDesposit", mobileNumber);
	}
}
