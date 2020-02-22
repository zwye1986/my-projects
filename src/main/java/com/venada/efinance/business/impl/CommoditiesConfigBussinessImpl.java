package com.venada.efinance.business.impl;import com.venada.efinance.business.CommoditiesConfigBussiness;import com.venada.efinance.common.exception.BusinessException;import com.venada.efinance.pojo.CommoditiesConfig;import com.venada.efinance.service.CommoditiesConfigService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import java.util.List;import java.util.Map;@Servicepublic class CommoditiesConfigBussinessImpl implements		CommoditiesConfigBussiness {	@Autowired	private CommoditiesConfigService commoditiesConfigService;	@Override	public List<CommoditiesConfig> getCommoditiesConfig(Map<String,Object> conditions)			throws BusinessException {		return commoditiesConfigService.findObjects("getCommoditiesConfig", conditions);	}	@Override	public void addCommoditiesConfig(CommoditiesConfig commoditiesConfig)			throws BusinessException {		commoditiesConfigService.saveObject("addCommoditiesConfig", commoditiesConfig);	}	@Override	public void updateCommoditiesConfig(CommoditiesConfig commoditiesConfig)			throws BusinessException {		commoditiesConfigService.updateObject("updateCommoditiesConfig", commoditiesConfig);	}	@Override	public CommoditiesConfig getCommoditiesConfigById(int id)			throws BusinessException {		return (CommoditiesConfig) commoditiesConfigService.getObject("getCommoditiesConfigById", id);	}	@Override	public void deleteCommoditiesConfig(int id) throws BusinessException {		commoditiesConfigService.deleteObject("deleteCommoditiesConfig", id);	}}