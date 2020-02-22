package com.venada.efinance.controller;

import com.venada.efinance.business.FusionChartBusiness;
import com.venada.efinance.business.UserBusiness;
import com.venada.efinance.common.controller.BaseController;
import com.venada.efinance.pojo.User;
import com.venada.efinance.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

@Controller
@RequestMapping(value="/manager/chart")
public class ChartsController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(ChartsController.class);
	
	@Autowired
	private FusionChartBusiness fusionChartBusiness ;
	@Autowired
	private UserBusiness userBusinessImpl;
	
	@RequestMapping(value="/account")
	public String rechargeAndWithdrawal(Model model,HttpServletRequest request){
		try {
			Map<String,Object> condition = setMapCondition(request);
			if(condition.get("searchTime") == null){
				model.addAttribute("searchTime",DateUtils.today());
				condition.put("searchTime", DateUtils.today());
			}else{
				model.addAttribute("searchTime",condition.get("searchTime"));
				condition.put("searchTime", condition.get("searchTime"));
			}
			if(condition.get("chart_swf") != null){
				model.addAttribute("chart_swf",condition.get("chart_swf"));
			}else{
				model.addAttribute("chart_swf","Column3D.swf");
			}
			String data = fusionChartBusiness.selectRechargeAndWithdrawal(condition);
			model.addAttribute("chart_data",data);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "charts/month_chart";
	}
	
	@RequestMapping(value="/recharge")
	public String recharge(Model model,HttpServletRequest request){
		try {
			Map<String,Object> condition = setMapCondition(request);
			if(condition.get("searchTime") == null){
				model.addAttribute("searchTime",DateUtils.today());
				condition.put("searchTime", DateUtils.today());
			}else{
				model.addAttribute("searchTime",condition.get("searchTime"));
				condition.put("searchTime", condition.get("searchTime"));
			}
			if(condition.get("chart_swf") != null){
				model.addAttribute("chart_swf",condition.get("chart_swf"));
			}else{
				model.addAttribute("chart_swf","Column3D.swf");
			}
			String data = fusionChartBusiness.selectRechargeLine(condition);
			model.addAttribute("chart_data",data);
		//	model.addAttribute("chart_swf","Line.swf");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "charts/month_chart";
	}
	
	@RequestMapping(value="/withdrawal")
	public String withdrawal(Model model,HttpServletRequest request){
		try {
			Map<String,Object> condition = setMapCondition(request);
			if(condition.get("searchTime") == null){
				model.addAttribute("searchTime",DateUtils.today());
				condition.put("searchTime", DateUtils.today());
			}else{
				model.addAttribute("searchTime",condition.get("searchTime"));
			}
			if(condition.get("chart_swf") != null){
				model.addAttribute("chart_swf",condition.get("chart_swf"));
			}else{
				model.addAttribute("chart_swf","Column3D.swf");
			}
			String data = fusionChartBusiness.selectWithdrawalLine(condition);
			model.addAttribute("chart_data",data);
		//	model.addAttribute("chart_swf","Line.swf");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "charts/month_chart";
	}
	
	@RequestMapping(value="/withdrawalsuccess")
	public String withdrawalSuccess(Model model,HttpServletRequest request){
		try {
			Map<String,Object> condition = setMapCondition(request);
			if(condition.get("searchTime") == null){
				model.addAttribute("searchTime",DateUtils.today());
				condition.put("searchTime", DateUtils.today());
			}else{
				model.addAttribute("searchTime",condition.get("searchTime"));
			}
			if(condition.get("chart_swf") != null){
				model.addAttribute("chart_swf",condition.get("chart_swf"));
			}else{
				model.addAttribute("chart_swf","Column3D.swf");
			}
			String data = fusionChartBusiness.selectWithdrawalSuccessLine(condition);
			model.addAttribute("chart_data",data);
		//	model.addAttribute("chart_swf","Line.swf");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "charts/month_chart";
	}
	
	@RequestMapping(value="/users/hour")
	public String countUsersByHour(Model model,HttpServletRequest request){
		try {
			Map<String,Object> condition = setMapCondition(request);
			String data = fusionChartBusiness.selectUserByHour();
			if(condition.get("chart_swf") != null){
				model.addAttribute("chart_swf",condition.get("chart_swf"));
			}else{
				model.addAttribute("chart_swf","Column3D.swf");
			}
			model.addAttribute("chart_data",data);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "charts/mainCharts";
	}
	
	
	@RequestMapping(value="/users/getAllInfo")
	public String getAllInfo(Model model,HttpServletRequest request){
		try {
			Map<String,Object> condition = setMapCondition(request);
			String mobileNumber=(String) condition.get("moblieNumber");
			
			String startTime = (String) condition.get("startDate");
			String endTime = (String) condition.get("endDate");
			if ((startTime != null) && (!startTime.equals("")) &&(!startTime.contains("00:00:00"))) {
				condition.put("startTime", startTime.concat(" 00:00:00"));
				model.addAttribute("startDate",startTime);
			}else{
//				condition.put("actionStartTime", DateUtils.today().concat(" 00:00:00"));
			}
			if((endTime != null)	&& (!endTime.equals(""))&&(!endTime.contains("23:59:59"))){
				condition.put("endTime", endTime.concat(" 23:59:59"));
				model.addAttribute("endDate",endTime);
			}else{
//				condition.put("actionStartTime", DateUtils.today().concat(" 23:59:59"));
			}
			
//			if(condition.get("startDate") == null){
//				Calendar cal = Calendar.getInstance();
//				cal.set(GregorianCalendar.DAY_OF_MONTH, 1);   
//				String startDate = DateUtils.toString(cal.getTime());
//				model.addAttribute("startDate",startDate);
//				condition.put("startDate", startDate);
//			}else{
//				model.addAttribute("startDate",condition.get("startDate").toString().concat(" 00:00:00"));
//			}
//			if(condition.get("endDate") == null){
//				model.addAttribute("endDate",DateUtils.today());
//				condition.put("endDate", DateUtils.today());
//			}else{
//				model.addAttribute("endDate",condition.get("endDate").toString().concat(" 23:59:59"));
//			}
			
			if(mobileNumber!=null){
				User u=userBusinessImpl.findUserByMoblieNumber(mobileNumber);
				condition.put("userid", u.getId());
				condition.put("mobileNumber", mobileNumber);
			}
			String data = fusionChartBusiness.getAllInfo(condition);
			if(condition.get("chart_swf") != null){
				model.addAttribute("chart_swf",condition.get("chart_swf"));
			}else{
				model.addAttribute("chart_swf","Column3D.swf");
			}
			model.addAttribute("moblieNumber",mobileNumber);
			model.addAttribute("chart_data",data);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "charts/person_day_chart";
	}
	
	
	@RequestMapping(value="/users/getAllCapitalInfo")
    public String getAllCapitalInfo(Model model,HttpServletRequest request){
        try {
            Map<String,Object> condition = setMapCondition(request);
            String mobileNumber=(String) condition.get("moblieNumber");
            
            String startTime = (String) condition.get("startDate");
            String endTime = (String) condition.get("endDate");
            if ((startTime != null) && (!startTime.equals("")) &&(!startTime.contains("00:00:00"))) {
                condition.put("startTime", startTime.concat(" 00:00:00"));
                model.addAttribute("startDate",startTime);
            }else{
//              condition.put("actionStartTime", DateUtils.today().concat(" 00:00:00"));
            }
            if((endTime != null)    && (!endTime.equals(""))&&(!endTime.contains("23:59:59"))){
                condition.put("endTime", endTime.concat(" 23:59:59"));
                model.addAttribute("endDate",endTime);
            }else{
//              condition.put("actionStartTime", DateUtils.today().concat(" 23:59:59"));
            }
            
//          if(condition.get("startDate") == null){
//              Calendar cal = Calendar.getInstance();
//              cal.set(GregorianCalendar.DAY_OF_MONTH, 1);   
//              String startDate = DateUtils.toString(cal.getTime());
//              model.addAttribute("startDate",startDate);
//              condition.put("startDate", startDate);
//          }else{
//              model.addAttribute("startDate",condition.get("startDate").toString().concat(" 00:00:00"));
//          }
//          if(condition.get("endDate") == null){
//              model.addAttribute("endDate",DateUtils.today());
//              condition.put("endDate", DateUtils.today());
//          }else{
//              model.addAttribute("endDate",condition.get("endDate").toString().concat(" 23:59:59"));
//          }
            
            if(mobileNumber!=null){
                User u=userBusinessImpl.findUserByMoblieNumber(mobileNumber);
                condition.put("userid", u.getId());
                condition.put("mobileNumber", mobileNumber);
            }
            String data = fusionChartBusiness.getAllCapitalInfo(condition);
            if(condition.get("chart_swf") != null){
                model.addAttribute("chart_swf",condition.get("chart_swf"));
            }else{
                model.addAttribute("chart_swf","Column3D.swf");
            }
            model.addAttribute("moblieNumber",mobileNumber);
            model.addAttribute("chart_data",data);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "charts/person_capital_chart";
    }
	
	
	
	
	@RequestMapping(value="/users/day")
	public String countUsersByDay(Model model,HttpServletRequest request){
		try {
			Map<String,Object> condition = setMapCondition(request);
			String data = fusionChartBusiness.selectUserByDay();
			if(condition.get("chart_swf") != null){
				model.addAttribute("chart_swf",condition.get("chart_swf"));
			}else{
				model.addAttribute("chart_swf","Column3D.swf");
			}
			model.addAttribute("chart_data",data);
//			model.addAttribute("chart_swf","Column3D.swf");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "charts/mainCharts";
	}
	
	@RequestMapping(value="/estimated/cost")
	public String estimatedCostByDate(Model model,HttpServletRequest request){
		try {
			Map<String,Object> condition = setMapCondition(request);
			if(condition.get("startDate") == null){
				Calendar cal = Calendar.getInstance();
				cal.set(GregorianCalendar.DAY_OF_MONTH, 1);   
				String startDate = DateUtils.toString(cal.getTime());
				model.addAttribute("startDate",startDate);
				condition.put("startDate", startDate);
			}else{
				model.addAttribute("startDate",condition.get("startDate").toString().concat(" 00:00:00"));
			}
			if(condition.get("endDate") == null){
				model.addAttribute("endDate",DateUtils.today());
				condition.put("endDate", DateUtils.today());
			}else{
				model.addAttribute("endDate",condition.get("endDate").toString().concat(" 23:59:59"));
			}
			if(condition.get("chart_swf") != null){
				model.addAttribute("chart_swf",condition.get("chart_swf"));
			}else{
				model.addAttribute("chart_swf","Column3D.swf");
			}
			String data = fusionChartBusiness.estimatedCostByDate(condition);
			model.addAttribute("chart_data",data);
//			model.addAttribute("chart_swf","Column3D.swf");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "charts/estimatedCostByDate";
	}
	
	@RequestMapping(value="/depost/geter")
	public String depostGeter(Model model,HttpServletRequest request){
		try {
			Map<String,Object> condition = setMapCondition(request);
			String data = fusionChartBusiness.depostGeter();
			model.addAttribute("chart_data",data);
			if(condition.get("chart_swf") != null){
				model.addAttribute("chart_swf",condition.get("chart_swf"));
			}else{
				model.addAttribute("chart_swf","Column3D.swf");
			}
//			model.addAttribute("chart_swf","Column3D.swf");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "charts/mainCharts";
	}
	
	@RequestMapping(value="/sex/ratio")
	public String sexRatio(Model model){
		try {
			String data = fusionChartBusiness.sexRatio();
			model.addAttribute("chart_data",data);
			model.addAttribute("chart_swf","Column3D.swf");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "charts/mainCharts";
	}
	
	@RequestMapping(value="/income/ratio")
	public String incomeCount(Model model){
		try {
			String data = fusionChartBusiness.incomeCount();
			model.addAttribute("chart_data",data);
			model.addAttribute("chart_swf","Column3D.swf");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "charts/mainCharts";
	}
	
	@RequestMapping(value="/age/ratio")
	public String ageRatio(Model model){
		try {
			String data = fusionChartBusiness.ageRatio();
			model.addAttribute("chart_data",data);
			model.addAttribute("chart_swf","Column3D.swf");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "charts/mainCharts";
	}
	
	@RequestMapping(value="/game/count")
	public String gameCount(Model model){
		try {
			String data = fusionChartBusiness.gameCount();
			model.addAttribute("chart_data",data);
			model.addAttribute("chart_swf","Column3D.swf");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "charts/mainCharts";
	}

}
