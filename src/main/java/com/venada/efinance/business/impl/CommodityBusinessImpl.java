package com.venada.efinance.business.impl;


import com.venada.efinance.business.CommodityBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.exception.ServiceException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.Commodity;
import com.venada.efinance.pojo.CommodityCategory;
import com.venada.efinance.pojo.CommodityPic;
import com.venada.efinance.service.CommodityCategoryService;
import com.venada.efinance.service.CommodityPicService;
import com.venada.efinance.service.CommodityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



/***
 * 
 * @author xupei
 *
 */
@Service("commodityBusiness")
public class CommodityBusinessImpl implements CommodityBusiness{
	/** Logger */
	private static final Logger logger = LoggerFactory.getLogger(CommodityBusinessImpl.class);
	@Autowired
	private CommodityCategoryService commodityCategoryService;
	@Autowired
	private CommodityService commodityService;
	@Autowired
	private CommodityPicService commodityPicService;

	@Override
	public List<CommodityCategory> queryCommodityCategory(
			Map<String, Object> condition, PaginationMore page)
			throws BusinessException {
		try {
			page.setTotalRows(getCommodityCategoryCounts(condition));
			page.setPageSize(10);
			page.repaginate();
			return commodityCategoryService.selectList("queryCategoryByAdmin", condition, page);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"queryGames(" + condition + "," + page + ")方法出错！\t",
					e.getMessage() });
		}
	}
	
	@Override
	public List<CommodityCategory> queryCommodityCategory(
			Map<String, Object> condition)
			throws BusinessException {
		try {
			return commodityCategoryService.findObjects("queryCategoryByAdmin", condition);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"queryGames(" + condition + ")方法出错！\t",
					e.getMessage() });
		}
	}
	
	@Override
	public int getCommodityCategoryCounts(Map<String, Object> map) throws BusinessException {
		try {
			int counts = (Integer) commodityCategoryService.getObject("getCommodityCategoryCounts",
					map);
			return counts;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"getCommodityCategoryCounts(" + map + ")方法出错！\t", e.getMessage() });
		}
	}
	
	@Override
	public int getCommodityCounts(Map<String, Object> map) throws BusinessException {
		try {
			int counts = (Integer) commodityService.getObject("getCommodityCounts",
					map);
			return counts;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"getCommodityCounts(" + map + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public CommodityCategory getCommodityCategory(String id)
			throws BusinessException {
		return (CommodityCategory) commodityCategoryService.getObject("getCommodityCategory", id);
	}
	
	@Override
	public Commodity getCommodity(String id)
			throws BusinessException {
		return (Commodity) commodityService.getObject("getCommodity", id);
	}

	@Override
	public void updateCommodityCategory(CommodityCategory category)
			throws BusinessException {
		 commodityCategoryService.updateObject("updateCommodityCategory", category);
	}

	@Override
	public void addCommodityCategory(CommodityCategory category)
			throws BusinessException {
		commodityCategoryService.saveObject("addCommodityCategory", category);
	}

	@Override
	public void delCommodityCategory(String id) throws BusinessException {
		commodityCategoryService.deleteObject("delCommodityCategory", id);
	}

	@Override
	public List<Commodity> queryCommodity(Map<String, Object> condition,
			PaginationMore page) throws BusinessException {
		try {
			page.setTotalRows(getCommodityCounts(condition));
			
			page.repaginate();
			
			if(page.getTotalPages() < page.getCurrentPage()){
				page.setCurrentPage(page.getTotalPages());
			}
			
			
			
			page.repaginate();
			
			return commodityService.selectList("queryCommodityByAdmin", condition, page);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"queryCommodity(" + condition + "," + page + ")方法出错！\t",
					e.getMessage() });
		}
	}
	
	@Override
	public List<CommodityPic> queryCommodityPics(Map<String, Object> map)
			throws BusinessException {
		try {
			return commodityPicService.findObjects("queryCommodityPic", map);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"queryCommodityPics(" + map + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public void addCommodityPic(CommodityPic pic) throws BusinessException {
		 commodityPicService.saveObject("addCommodityPic", pic);
	}

	@Override
	public CommodityPic getCommodityPic(String id) throws BusinessException {
		return (CommodityPic) commodityService.getObject("getCommodityPic", id);
	}

	@Override
	public void delCommodityPic(String id) throws BusinessException {
		commodityPicService.deleteObject("delCommodityPic", id);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class, ServiceException.class })
	public void updateCommodity(Commodity commodity, String[] picid)
			throws BusinessException {
		Map<String,Object> map = new HashMap<String,Object>();
		
		 if(picid != null && picid.length>0){
				String picId = "";
				for (String string : picid) {
					picId = picId+",'"+string+"'";
				}
				picId = "("+picId.substring(1)+")";
				map = new HashMap<String,Object>();
				map.put("picId",picId);
				map.put("id",commodity.getId());
				commodityPicService.updateObject("updateCommodityPic", map);
		 }
		 
		 map = new HashMap<String,Object>();
		 map.put("commodityId", commodity.getId());
			List<CommodityPic> list = commodityPicService.findObjects("queryCommodityPic", map);
			 if(list.size()>0){
				 commodity.setCoverPic(list.get(0).getId());
			 }
			 commodityService.updateObject("updateCommodity", commodity);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class, ServiceException.class })
	public void addCommodity(Commodity commodity, String[] picid)
			throws BusinessException {
		 if(picid != null && picid.length>0){
			 commodity.setCoverPic(picid[0]);
		 }
		 commodityService.saveObject("addCommodity", commodity);
		 if(picid != null && picid.length>0){
				String picId = "";
				for (String string : picid) {
					picId = picId+",'"+string+"'";
				}
				picId = "("+picId.substring(1)+")";
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("picId",picId);
				map.put("id",commodity.getId());
				commodityPicService.updateObject("updateCommodityPic", map);
		 }
	}

	@Override
	@Transactional(rollbackFor = { Exception.class, ServiceException.class })
	public void delCommodity(String id) throws BusinessException {
		 commodityService.deleteObject("delCommodity", id);
		 commodityPicService.deleteObject("delCommodityPicByCommodityId", id);
	}

	@Override
	public List<CommodityCategory> getAllCommodityCategory()
			throws BusinessException {
		return commodityCategoryService.findObjects("getAllCommodityCategory", null);
	}

	

}
