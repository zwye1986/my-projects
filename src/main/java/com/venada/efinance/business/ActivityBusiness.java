package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.Activity;

import java.util.List;
import java.util.Map;








/***
 * 
 * @author xupei
 *
 */

public interface ActivityBusiness {
    public void addActivity(Activity activity) throws BusinessException ;
    
    public void updateActivity(Activity activity) throws BusinessException ;
    
    public void delActivity(String id) throws BusinessException ;
    
    public Activity getActivityById(String id) throws BusinessException ;
    
    public List<Activity> queryActivityList(Map<String,Object> map,PaginationMore page) throws BusinessException ;
    
    public List<Activity> queryActivityList(Map<String,Object> map) throws BusinessException ;
    
    public int getActivityCounts(Map<String,Object> map) throws BusinessException ;
}
