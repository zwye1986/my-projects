package com.venada.efinance.controller;

import com.venada.efinance.business.SystemConfigBusiness;
import com.venada.efinance.business.UserBusiness;
import com.venada.efinance.business.UserSignInBusiness;
import com.venada.efinance.business.UserWalletBusiness;
import com.venada.efinance.common.controller.BaseController;
import com.venada.efinance.pojo.SignIn;
import com.venada.efinance.pojo.User;
import com.venada.efinance.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
/**
 * 提供手机端签到请求的服务
 * @author hepei
 *
 */
@Controller
public class UserSignByMobliePhoneController extends BaseController{

	@Autowired
	private UserBusiness userBusiness;
	@Autowired
	private UserWalletBusiness userWalletBusinessImpl;
	@Autowired
	private UserSignInBusiness userSignInBusinessImpl;
	@Autowired
	private SystemConfigBusiness systemConfigBusinessImpl ;
	
	private static final Logger logger = LoggerFactory.getLogger(UserSignByMobliePhoneController.class);

	/**
	 * 用户签到
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mobilePhone/{mobileNumber}/{password}/signIn", method = {
			RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public  Map<String, Object> userSignInToday(@PathVariable String mobileNumber,@PathVariable String password) {
		Map<String, Object> result = new HashMap<String, Object>();
		result=userSignInBusinessImpl.userSignInTodayByMobilePhone(mobileNumber,password);
		return result;
	}
	/**
	 * 判断用户今天是否已经签到
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mobilePhone/{mobileNumber}/{password}/issignIn", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> issignIn(@PathVariable String mobileNumber,@PathVariable String password) {
		Map<String, Object> result = new HashMap<String, Object>();
		User user =userBusiness.findUserByMoblieNumber(mobileNumber);
		if(user==null){
			result.put("errorcode", "1");
			result.put("errormsg", "用户不存在");
			return result;
		}else{
			if(!user.getPassword().equals(password)){
				result.put("errorcode", "2");
				result.put("errormsg", "密码错误");
				return result;
			}else{
				Map<String, Object> isSignInResult = userSignInBusinessImpl.isSignIn(user,0);
				boolean flag = Boolean.valueOf(isSignInResult.get("flag").toString());
				if (flag) {
					result.put("flag", "havesignIn");
					result.put("msg", "您今日已经签到!");
				} else {
					result.put("flag", "notsignIn");
					result.put("msg", "未签到!");
				}
			}
		}
		return result;
	}
	
    /**
     * 查询用户已经签到的具体时间
     * @param searchTime
     * @return
     */
	public List<String> searchDaysSignIn(User u,String searchTime) {
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
     * 查询用户已经签到的具体时间
     * @param searchTime
     * @return
     */
	public List<SignIn> searchDaysInfoSignIn(User u,Map<String, Object> condition) {
		String searchTime=(String) condition.get("searchTime");
		if (searchTime == null || searchTime.equals("")) {
			searchTime = DateUtils.toString(new Date(), "yyyyMMdd");
		}
		List<SignIn> list=new ArrayList<SignIn>();
		if (u == null) {
			
		} else {
			condition.put("mobilePhone", u.getMobileNumber());
		    list = userSignInBusinessImpl.listSignByMobilePhone(condition);
		}
		return list;
	}
	
	/**
	 * 查询该月用户的签到情况
	 * @param year
	 * @param month
	 * @return
	 */
	@RequestMapping(value = "/mobilePhone/{mobileNumber}/{password}/{year}/{month}/signInDetailSerach", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> signInDetailSerach(@PathVariable String mobileNumber,@PathVariable String password,@PathVariable String year,@PathVariable String month) {
		Map<String, Object> result = new HashMap<String, Object>();
		User user =userBusiness.findUserByMoblieNumber(mobileNumber);
		if (user == null) {
			result.put("errorcode", "1");
			result.put("errormsg", "用户不存在");
			return result;
		} else {
			if (!user.getPassword().equals(password)) {
				result.put("errorcode", "2");
				result.put("errormsg", "密码错误");
				return result;
			}else{
				if (Integer.parseInt(month) < 10) {
					if (month.length() <2) {
						month = "0" + month;
					}
				}
				String searchTime = year + month + "01";
				result.put("searchTime", searchTime);
				List<String> dayHasSignIn = searchDaysSignIn(user,searchTime);
				result.put("data", dayHasSignIn);
			}
		}
		
		return result;
	}
	
	/**
	 * 查询该月用户的签到情况
	 * @param year
	 * @param month
	 * @return
	 */
	@RequestMapping(value = "/mobilePhone/signInDetailInfoSerach", method = {RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> signInDetailInfoSerach(HttpServletRequest request) {
		String mobileNumber = request.getParameter("mobileNumber");
		String password = request.getParameter("password");
		String month=request.getParameter("month");
		String year=request.getParameter("year");
		Map<String, Object> result = new HashMap<String, Object>();
		User user =userBusiness.findUserByMoblieNumber(mobileNumber);
		if (user == null) {
			result.put("errorcode", "1");
			result.put("errormsg", "用户不存在");
			return result;
		} else {
			if (!user.getPassword().equals(password)) {
				result.put("errorcode", "2");
				result.put("errormsg", "密码错误");
				return result;
			}else{
				if (Integer.parseInt(month) < 10) {
					if (month.length() <2) {
						month = "0" + month;
					}
				}
				String searchTime = year + month + "01";
				Map<String, Object> condition = new HashMap<String, Object>();
				condition.put("searchTime", searchTime);
				result.put("searchTime", searchTime);
				List<SignIn> dayHasSignIn = searchDaysInfoSignIn(user,condition);
				result.put("data", dayHasSignIn);
			}
		}
		
		return result;
	}
	
	
	/**
	 * 查询用户当月的签到情况
	 * @return
	 */
	@RequestMapping(value = "/mobilePhone/{mobileNumber}/{password}/getcustomerSignInMonthCount")
	@ResponseBody
	public Map<String, Object> getCustomerSignInMonthCount(
			@PathVariable String mobileNumber, @PathVariable String password) {
		Map<String, Object> result = new HashMap<String, Object>();
		String monthHasSignIn = "";
		Map<String, Object> condition = new HashMap<String, Object>();
		User user = userBusiness.findUserByMoblieNumber(mobileNumber);
		if (user == null) {
			result.put("errorcode", "1");
			result.put("errormsg", "用户不存在");
			return result;
		} else {
			if (!user.getPassword().equals(password)) {
				result.put("errorcode", "2");
				result.put("errormsg", "密码错误");
				return result;
			} else {
				condition.put("mobilePhone", user.getMobileNumber());
				monthHasSignIn = userSignInBusinessImpl
						.getCustomerSignInMonthCount(condition).toString();
				result.put("monthHasSignIn", monthHasSignIn);
				List<SignIn> signInList=userSignInBusinessImpl.getCustomerSignInMonthSeriesCount(condition);
				String series="0";
				if(signInList.isEmpty()){
					series="0";
				}else if(signInList.size()>=1){
					series=String.valueOf(signInList.get(0).getNewSignCount());
				}
				result.put("series", series);
			}
		}

		return result;
	}
	
	
}
