package com.venada.efinance.controller;

import com.venada.efinance.business.SystemConfigBusiness;
import com.venada.efinance.business.UserBusiness;
import com.venada.efinance.business.UserSignInBusiness;
import com.venada.efinance.business.UserWalletBusiness;
import com.venada.efinance.common.controller.BaseController;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.DayList;
import com.venada.efinance.pojo.SignIn;
import com.venada.efinance.pojo.User;
import com.venada.efinance.security.SpringSecurityUtil;
import com.venada.efinance.util.CalenderUtil;
import com.venada.efinance.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class UserSignController extends BaseController{

	@Autowired
	private UserBusiness userBusiness;
	@Autowired
	private UserWalletBusiness userWalletBusinessImpl;
	@Autowired
	private UserSignInBusiness userSignInBusinessImpl;
	@Autowired
	private SystemConfigBusiness systemConfigBusinessImpl ;

	/**
	 * 用户签到
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user/account/signIn", method = {
			RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public synchronized Map<String, Object>  userSignInToday(Model model) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result=userSignInBusinessImpl.getSignResult();
		return result;
	}
	/**
	 * 判断用户今天是否已经签到
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/issignIn", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> issignIn(Model model) {
		Map<String, Object> result = new HashMap<String, Object>();

		User user = SpringSecurityUtil.getCurrentUser();
		if (user == null) {
			result.put("flag", "notlogin");
			result.put("msg", "用户未登录!");
		} else {
			Map<String, Object> isSignInResult = userSignInBusinessImpl.isSignIn(0);
			boolean flag = Boolean.valueOf(isSignInResult.get("flag")
					.toString());
			if (flag) {
				result.put("flag", "havesignIn");
				result.put("msg", "您今日已经签到!");
			} else {
				result.put("flag", "notsignIn");
				result.put("msg", "未签到!");
			}
		}
		return result;
	}
	
	
	@RequestMapping(value = { "/signInDetail", "/signInDetail.html" })
	public String signInDetail(Model model,HttpServletRequest request) {
		User user = SpringSecurityUtil.getCurrentUser();
		model.addAttribute("user",user);
		return ".signInDeatil";
	}
	
	
	
    /**
     * 查询用户已经签到的具体时间
     * @param searchTime
     * @return
     */
	public List<String> searchDaysSignIn(String searchTime) {
		User u = SpringSecurityUtil.getCurrentUser();
		Map<String, Object> condition = new HashMap<String, Object>();
		List<String> dayHasSignIn = new ArrayList<String>();
		if (searchTime == null || searchTime.equals("")) {
			searchTime = DateUtils.toString(new Date(), "yyyyMMdd");
		}
		if (u == null) {
			
		} else {
			condition.put("searchTime", searchTime);
			condition.put("mobilePhone", u.getMobileNumber());
			dayHasSignIn = userSignInBusinessImpl.listSignInByMonth(condition);
		}
		return dayHasSignIn;
	}
	
	/**
	 * 查询该月用户的签到情况
	 * @param year
	 * @param month
	 * @return
	 */
	@RequestMapping(value = "/signInDetailSerach", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> signInDetailSerach(
			@RequestParam(value = "year") String year,
			@RequestParam(value = "month") String month) {
		Map<String, Object> result = new HashMap<String, Object>();
		User u = SpringSecurityUtil.getCurrentUser();
		String searchTime = year + month + "01";
		result.put("retCode", 200);
		CalenderUtil myCalender = new CalenderUtil();
		List<DayList> daylist = myCalender.setDate(searchTime);
		List<String> dayHasSignIn = searchDaysSignIn(searchTime);
		Integer compareMonth = Integer.valueOf(month);
		if (u != null) {  //如果user不为空，进行下面的判断。
			for (DayList d : daylist) {
				for (String day : dayHasSignIn) {
					if ((d.getMonth().equals(compareMonth) || d.getMonth() == compareMonth)
							&& d.getDay().trim().equals(day.trim())) {
						d.setNum("2");
					}
				}
			}
		}
		result.put("data", daylist);
		return result;
	}
	
	/**
	 * 查询当天所有已经的签到
	 * @return
	 */
	@RequestMapping(value =  "/getSignInByAllCount")
	@ResponseBody
	public Map<String, Object> 	getSignInByAllCount() {
		Map<String, Object> result=new HashMap<String,Object>();
		String dayHasSignIn="";
		dayHasSignIn=userSignInBusinessImpl.getSignInByAllCount().toString();
		result.put("dayHasSignIn", dayHasSignIn);
		return result;
	}
	
	/**
	 * 查询用户当月的签到情况
	 * @return
	 */
	@RequestMapping(value = "/getcustomerSignInMonthCount")
	@ResponseBody
	public Map<String, Object> getCustomerSignInMonthCount() {
		Map<String, Object> result = new HashMap<String, Object>();
		String monthHasSignIn = "";
		Map<String, Object> condition = new HashMap<String, Object>();
		User u = SpringSecurityUtil.getCurrentUser();
		if (u == null) {
			result.put("monthHasSignIn", 0);
		} else {
			condition.put("mobilePhone", u.getMobileNumber());
			monthHasSignIn = userSignInBusinessImpl
					.getCustomerSignInMonthCount(condition).toString();
			result.put("monthHasSignIn", monthHasSignIn);
		}
		return result;
	}
	
	
	
	@RequestMapping("getWeather")
	public String getWeather(HttpServletRequest request){
		return "weather";
	}

	
	@RequestMapping(value = "/user/account/getSignedDate")
	public String getSignedData(
			@RequestParam(value = "id", required = false) Integer Id,
			PaginationMore page, Model model) {
		User u = SpringSecurityUtil.getCurrentUser();
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("mobilePhone", u.getMobileNumber());
		try {
			page.setPageSize(15);
			List<SignIn> list = userSignInBusinessImpl.listSignByMobileNumber(
					condition, page);
			model.addAttribute("signedList", list);
			model.addAttribute("total",userSignInBusinessImpl.sumSignBenefitByMobileNumber(condition));
		} catch (BusinessException e) {
		}
		model.addAttribute("page", page);
		return ".newSignedDateList";
	}
	
	@RequestMapping(value = "/user/account/getSignList",method=RequestMethod.GET)
	public String getSignList(HttpServletRequest request,Model model) {
		Integer pageSize=10;
		String type=request.getParameter("type");
		if(null==type){
			type="sign";
		}
		User u = SpringSecurityUtil.getCurrentUser();
		int page = 1;
		int gamesCounts = 0;
		int gamesPage = 0;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			page = 1;
		}
		if (page < 1) {
			page = 1;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("mobilePhone", u.getMobileNumber());
		gamesCounts=userSignInBusinessImpl.getSignInBySelfCount(map);
		gamesPage = (gamesCounts / pageSize) + (gamesCounts % pageSize > 0 ? 1 : 0);
      	   if (page > gamesPage && gamesCounts != 0) {
				page = gamesPage;
		   }
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("limitCount", pageSize);
		condition.put("limitIndex", (page - 1) * pageSize);
		condition.put("mobilePhone", u.getMobileNumber());
		List<SignIn> signInList =new ArrayList<SignIn>();
		signInList=userSignInBusinessImpl.listSignInBySelf(condition);
   		model.addAttribute("type",type);
  	    model.addAttribute("gamesPage", gamesPage);
		model.addAttribute("signedList",signInList);
		model.addAttribute("page", page);
		model.addAttribute("total",userSignInBusinessImpl.sumSignBenefitByMobileNumber(condition));
		return "/signin/mysignedlist";
	}
	
}
