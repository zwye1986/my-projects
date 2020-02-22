package com.venada.efinance.controller;

import com.venada.efinance.business.AreaBusiness;
import com.venada.efinance.business.CityBusiness;
import com.venada.efinance.business.ProvinceBusiness;
import com.venada.efinance.business.SignUpBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.Area;
import com.venada.efinance.pojo.City;
import com.venada.efinance.pojo.Province;
import com.venada.efinance.pojo.SignUp;
import com.venada.efinance.util.DateUtils;
import com.venada.efinance.util.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class SignUpController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(SignUpController.class);
	
	
	@Autowired
	private SignUpBusiness signUpBusinessImpl;
	@Autowired
	private ProvinceBusiness provinceBusiness;
	@Autowired
	private CityBusiness cityBusiness;
	@Autowired
	private AreaBusiness areaBusiness;
	
	@ResponseBody
	@RequestMapping(value = "/getCityJson", method = { RequestMethod.GET , RequestMethod.POST })
	public Object getCityJson(HttpServletRequest request,
			HttpServletResponse response) {
		List<City> cities = null;
		try {
			cities = cityBusiness.findCityListByProvinceid(Integer
					.parseInt(request.getParameter("provinceid")));
		} catch (NumberFormatException e) {
			logger.error(e.getMessage());
		}
		return cities;
	}

	@ResponseBody
	@RequestMapping(value = "/getAreaJson", method = {
			RequestMethod.GET, RequestMethod.POST })
	public Object getAreaJson(HttpServletRequest request,
			HttpServletResponse response) {
		List<Area> area = null;
		try {
			area = areaBusiness.findAreaListByCityid(Integer.parseInt(request
					.getParameter("cityid")));
		} catch (NumberFormatException e) {
			logger.error(e.getMessage());
		}
		return area;
	}
	
	@RequestMapping("viewSignUp")
	public String viewSignUp(HttpServletRequest request,Model model){
		// 加载全国省份
		List<Province> provinces = provinceBusiness.findAllProvinceList();
		model.addAttribute("liveProvince", provinces);
		return "/signup/viewsignup";
	}
	
	@RequestMapping("dealSignUp")
	@ResponseBody
	public Object dealSignUp(HttpServletRequest request){
		Map<String,String> resultMap = new HashMap<String,String>();
		if(DateUtils.compareCurrentDate("2014-05-03 23:59:59")){
			resultMap.put("code", "0");
			resultMap.put("meg", "探店报名已结束！");
			return resultMap;
		}
		String nickName = request.getParameter("nickName");
		String question = request.getParameter("question");
		String mobileNumber = request.getParameter("mobileNumber");
		String checkCode = request.getParameter("checkCode");
		String liveaddress= request.getParameter("liveaddress");
		Integer liveProvince=Integer.valueOf(request.getParameter("liveProvince"));
		Integer liveCity=Integer.valueOf(request.getParameter("liveCity"));
		Integer liveArea=Integer.valueOf(request.getParameter("liveArea"));
		if(checkCode == null || "".equals(checkCode.replaceAll("\\s*", "")) ){
			resultMap.put("code", "0");
			resultMap.put("meg", "验证码不能为空！");
			return resultMap;
		}else{
			checkCode = checkCode.replaceAll("\\s*", "");
			HttpSession session = request.getSession();
			if(session.getAttribute("signUpCode") != null){
				String signUpCode = session.getAttribute("signUpCode").toString();
				if(!checkCode.equalsIgnoreCase(signUpCode)){
					resultMap.put("code", "0");
					resultMap.put("meg", "验证码错误！");
					return resultMap;
				}
			}else{
				resultMap.put("code", "0");
				resultMap.put("meg", "验证码错误！");
				return resultMap;
			}
		}
		
		if(nickName == null || "".equals(nickName.replaceAll("\\s*", ""))){
			resultMap.put("code", "0");
			resultMap.put("meg", "姓名不能为空！");
			return resultMap;
		}else{
			nickName = nickName.replaceAll("\\s*", "");
		}
		
		if(question == null || "".equals(question.replaceAll("\\s*", ""))){
			resultMap.put("code", "0");
			resultMap.put("meg", "请填写建议内容！");
			return resultMap;
		}else{
			question = question.replaceAll("\\s*", "");
			question = SystemUtils.StringFilter(question);
			if(question.length() > 200){
				resultMap.put("code", "0");
				resultMap.put("meg", "建议内容不能超过200个字符！");
				return resultMap;
			}
		}
		
		if(mobileNumber == null || "".equals(mobileNumber.replaceAll("\\s*", ""))){
			resultMap.put("code", "0");
			resultMap.put("meg", "蛙宝账号不能为空！");
			return resultMap;
		}else{
			mobileNumber = mobileNumber.replaceAll("\\s*", "");
			if(!SystemUtils.checkMobileNumber(mobileNumber)){
				resultMap.put("code", "0");
				resultMap.put("meg", "请确填写蛙宝账号！");
				return resultMap;
			}else{
				if(!signUpBusinessImpl.getSignUpByMobileNumber(mobileNumber).isEmpty()){
					resultMap.put("code", "0");
					resultMap.put("meg", "该蛙宝账号已经参加报名！");
					return resultMap;
				}
			}
		}
		
		SignUp signup = new SignUp();
		signup.setId(UUID.randomUUID().toString());
		signup.setQuestion(question);
		signup.setMobileNumber(mobileNumber);
		signup.setNickName(nickName);
		signup.setCreatetime(new Date());
		signup.setLiveProvince(liveProvince);
		signup.setLiveCity(liveCity);
		signup.setLiveArea(liveArea);
		signup.setLiveaddress(liveaddress);
		try {
			signUpBusinessImpl.addSignUp(signup);
		} catch (BusinessException e) {
			resultMap.put("code", "0");
			resultMap.put("meg", "系统出错！");
			return resultMap;
		}
		resultMap.put("code", "1");
		resultMap.put("meg", "操作成功！");
		return resultMap;
	}
	
}
