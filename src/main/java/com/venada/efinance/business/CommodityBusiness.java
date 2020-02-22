package com.venada.efinance.business;


import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.Commodity;
import com.venada.efinance.pojo.CommodityCategory;
import com.venada.efinance.pojo.CommodityPic;

import java.util.List;
import java.util.Map;



/***
 * 
 * @author xupei
 *
 */

public interface CommodityBusiness {
	public List<CommodityCategory> queryCommodityCategory(Map<String,Object> condition,PaginationMore page) throws BusinessException;
	
	public List<CommodityCategory> queryCommodityCategory(Map<String,Object> condition) throws BusinessException;
	
	public List<Commodity> queryCommodity(Map<String,Object> condition,PaginationMore page) throws BusinessException;
	
	public int getCommodityCategoryCounts(Map<String, Object> map) throws BusinessException;
	
	public int getCommodityCounts(Map<String, Object> map) throws BusinessException;
	
	public CommodityCategory getCommodityCategory(String id) throws BusinessException;
	
	public Commodity getCommodity(String id) throws BusinessException;
	
	public CommodityPic getCommodityPic(String id) throws BusinessException;
	
	public List<CommodityPic> queryCommodityPics(Map<String,Object> map) throws  BusinessException;
	
	public void updateCommodityCategory(CommodityCategory category) throws BusinessException;
	
	public void addCommodityCategory(CommodityCategory category) throws BusinessException;
	
    public void updateCommodity(Commodity commodity,String[] picid) throws BusinessException;
	
	public void addCommodity(Commodity commodity,String[] picid) throws BusinessException;
	
	public void addCommodityPic(CommodityPic pic) throws BusinessException;
	
	public void delCommodityCategory(String id) throws BusinessException;
	
	public void delCommodityPic(String id) throws BusinessException;
	
	public void delCommodity(String id) throws BusinessException;
	
	public List<CommodityCategory> getAllCommodityCategory() throws BusinessException;
}
