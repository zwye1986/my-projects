package com.venada.efinance.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.venada.efinance.business.PrizeBusiness;
import com.venada.efinance.business.UserBusiness;
import com.venada.efinance.business.UserDetailBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.PrizeDetail;
import com.venada.efinance.pojo.SignIn;
import com.venada.efinance.pojo.User;
import com.venada.efinance.pojo.UserDetail;
import com.venada.efinance.pojo.UserIntegralLotteryDetail;
import com.venada.efinance.pojo.UserLottery;
import com.venada.efinance.security.SpringSecurityUtil;
import com.venada.efinance.util.DateUtils;

@Controller
public class IntegralLotteryController {
	@Autowired
	private PrizeBusiness prizeBusiness;
	@Autowired
	private UserDetailBusiness userDetailBusiness;
	@Autowired
	private UserBusiness userBusiness;

	@RequestMapping(value="/integralLottery.html")
	public String festival(Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		List<UserIntegralLotteryDetail> lotteryList = prizeBusiness.getUserIntegralLotteryDetailList(map);
		model.addAttribute("lotteryList", lotteryList);
		model.addAttribute("lotterTime", 0);
		return ".integralLottery";
	}
	
	
	
	//抽奖
	@RequestMapping(value="/doIntegralLottery.html")
	public @ResponseBody int lottery(){	
		
		User user = SpringSecurityUtil.getCurrentUser();
		
		if(user == null){
			return -1;  //用户未登录
		}
		
		  try {
			  return prizeBusiness.integralLottery(user);
		} catch (Exception e) {
			return 6;
		}
	
	}
	
	
	@RequestMapping(value="/user/integralLotteryLog.html")
	public String festivalAll(Model model,PaginationMore page){
		User user = SpringSecurityUtil.getCurrentUser();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", user.getId());
		
		
		try {
			page.setPageSize(15);
			List<UserIntegralLotteryDetail> lotteryList = prizeBusiness.getUserIntegralLotteryDetailList(map,page);
			model.addAttribute("lotteryList", lotteryList);
		} catch (BusinessException e) {
		}
		model.addAttribute("page", page);
		
		return ".lotteryList";
	}
	
	@RequestMapping(value = "/user/account/getLotteryList",method=RequestMethod.GET)
	public String getSignList(HttpServletRequest request,Model model) {
		Integer pageSize=10;
		String type=request.getParameter("type");
		if(null==type){
			type="lottery";
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
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("userId", u.getId());
		gamesCounts=prizeBusiness.getLotteryCount(condition);
		gamesPage = (gamesCounts / pageSize) + (gamesCounts % pageSize > 0 ? 1 : 0);
      	   if (page > gamesPage && gamesCounts != 0) {
				page = gamesPage;
		   }
		condition.put("limitCount", pageSize);
		condition.put("limitIndex", (page - 1) * pageSize);
		List<UserIntegralLotteryDetail> lotteryList = prizeBusiness.getUserIntegralLotteryDetailList(condition);
   		model.addAttribute("type",type);
  	    model.addAttribute("gamesPage", gamesPage);
		model.addAttribute("lotteryList",lotteryList);
		model.addAttribute("page", page);
		return "/lottery/mylotterlist";
	}
	
}
