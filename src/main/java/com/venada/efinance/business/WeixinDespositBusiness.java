package com.venada.efinance.business;

import com.venada.efinance.pojo.WeixinDesposit;

import java.util.List;

/***
 * 
 * @author hepei
 * 
 */

public interface WeixinDespositBusiness {
	public List<WeixinDesposit> listWeixinDesposit(String mobileNumber);

}
