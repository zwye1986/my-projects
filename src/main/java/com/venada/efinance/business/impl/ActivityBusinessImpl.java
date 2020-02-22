package com.venada.efinance.business.impl;


import com.venada.efinance.business.ActivityBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.exception.ServiceException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.Activity;
import com.venada.efinance.service.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/***
 * 
 * @author xupei
 *
 */
@Service("activityBusiness")
public class ActivityBusinessImpl implements ActivityBusiness{
	/** Logger */
	private static final Logger logger = LoggerFactory.getLogger(ActivityBusinessImpl.class);
	@Autowired
	private ActivityService activityService;
	@Override
	public void addActivity(Activity activity) throws BusinessException {
		activityService.saveObject("addActivity", activity);
	}
	@Override
	public Activity getActivityById(String id) throws BusinessException {
		return (Activity) activityService.getObject("getActivityById", id);
	}
	@Override
	public void updateActivity(Activity activity) throws BusinessException {
		activityService.updateObject("updateActivity", activity);
	}
	@Override
	public List<Activity> queryActivityList(Map<String, Object> map,
			PaginationMore page) throws BusinessException {
		try {
			page.setTotalRows(getActivityCounts(map));
			page.setPageSize(10);
			page.repaginate();
			return activityService.selectList("queryActivityList", map, page);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"queryActivityList(" + map + "," + page + ")方法出错！\t",
					e.getMessage() });
		}
		
		
	}
	@Override
	public int getActivityCounts(Map<String, Object> map)
			throws BusinessException {
		return (Integer) activityService.getObject("getActivityCounts", map);
	}
	@Override
	public List<Activity> queryActivityList(Map<String, Object> map)
			throws BusinessException {
		return activityService.findObjects("queryActivityList", map);
	}
	@Override
	public void delActivity(String id) throws BusinessException {
		activityService.deleteObject("delActivity", id);
	}
}
