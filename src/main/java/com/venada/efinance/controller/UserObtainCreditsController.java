package com.venada.efinance.controller;

import com.venada.efinance.business.UserBusiness;
import com.venada.efinance.business.UserObtainCreditsBusiness;
import com.venada.efinance.pojo.ObtainCredits;
import com.venada.efinance.security.SpringSecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/user/credits/")
public class UserObtainCreditsController {
	private static final Logger logger = LoggerFactory
			.getLogger(UserObtainCreditsController.class);
	@Autowired
	private UserObtainCreditsBusiness userObtainCreditsBusiness;
	@Autowired
	private  UserBusiness userBusinessImpl;

//	@RequestMapping(value = { "/obtainlist.html", "/obtainlist" })
//	public String obtainlist(
//			@RequestParam(value = "id", required = false) Integer Id,
//			PaginationMore page, Model model) {
//		User u = SpringSecurityUtil.getCurrentUser();
//		logger.info("userid='"+u.getId()+"' 查询获取积分记录开始");
//		try {
//			
//			List<ObtainCredits> obtainCreditsList = new ArrayList<ObtainCredits>();
//			Map<String, Object> condition = new HashMap<String, Object>();
//			condition.put("userId", u.getId());
//			logger.info("查询条件"+condition.toString());
//			if (Id != null) {
//				condition.put("Id", Id);
//				page.setPageSize(10);
//				obtainCreditsList = userObtainCreditsBusiness
//						.queryObtainCredits(condition, page);
//				model.addAttribute("page", page);
//			} else {
//				page.setPageSize(10);
//				obtainCreditsList = userObtainCreditsBusiness
//						.queryObtainCredits(condition, page);
//				model.addAttribute("page", page);
//			}
//			model.addAttribute("obtainCreditsList", obtainCreditsList);
//			User user=userBusinessImpl.findUserById(u.getId());
//			model.addAttribute("user", user);
//		} catch (BusinessException e) {
//			logger.error("userid='"+u.getId()+"' 查询获取积分记录"+e.getMessage());
//		}
//		logger.info("userid='"+u.getId()+"' 查询获取积分记录结束");
//		return ".obtainCreditsList";
//	}
	
	@RequestMapping(value = { "/obtainlist.html", "/obtainlist" })
	public String obtainlist(HttpServletRequest request,Model model) {
		int page = 1;
		int gamesCounts = 0;
		int gamesPage = 0;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			logger.error("页数转换为数字失败：" + page);
			page = 1;
		}
		if (page < 1) {
			page = 1;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userId",SpringSecurityUtil.getCurrentUser().getId());
		gamesCounts=userObtainCreditsBusiness.queryAllObtainCreditsCount(map);
		gamesPage = (gamesCounts / 16) + (gamesCounts % 16 > 0 ? 1 : 0);
      	   if (page > gamesPage && gamesCounts != 0) {
				page = gamesPage;
		   }
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("limitCount", 16);
		condition.put("limitIndex", (page - 1) * 16);
		condition.put("userid", SpringSecurityUtil.getCurrentUser().getId());
		List<ObtainCredits> obtainCreditslist =new ArrayList<ObtainCredits> ();
		obtainCreditslist= userObtainCreditsBusiness.queryAllObtainCreditsByMobileNumber(condition);
		HttpSession session=request.getSession();
		session.setAttribute("credits", userBusinessImpl.findUserById(SpringSecurityUtil.getCurrentUser().getId()).getCredits());
		model.addAttribute("obtainCreditslist", obtainCreditslist);
   		model.addAttribute("page", page);
  	    model.addAttribute("gamesPage", gamesPage);
		return ".newObtainCreditsList";
	}
	
	@RequestMapping(value = {  "getObtainlist.html"},method = RequestMethod.GET)
	public String queryAllObtainCreditsByMobilePhone(HttpServletRequest request,Model model) {
		String type="ing";
		type=request.getParameter("type");
		if(null!=type){
			type="ing";
		}
		int page = 1;
		int gamesCounts = 0;
		int gamesPage = 0;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			logger.error("页数转换为数字失败：" + page);
			page = 1;
		}
		if (page < 1) {
			page = 1;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userId",SpringSecurityUtil.getCurrentUser().getId());
		gamesCounts=userObtainCreditsBusiness.queryAllObtainCreditsCount(map);
		gamesPage = (gamesCounts / 16) + (gamesCounts % 16 > 0 ? 1 : 0);
      	   if (page > gamesPage && gamesCounts != 0) {
				page = gamesPage;
		   }
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("limitCount", 16);
		condition.put("limitIndex", (page - 1) * 16);
		condition.put("userid", SpringSecurityUtil.getCurrentUser().getId());
		List<ObtainCredits> obtainCreditslist =new ArrayList<ObtainCredits> ();
		obtainCreditslist= userObtainCreditsBusiness.queryAllObtainCreditsByMobileNumber(condition);
		model.addAttribute("obtainCreditslist", obtainCreditslist);
   		model.addAttribute("page", page);
   		model.addAttribute("type",type);
  	    model.addAttribute("gamesPage", gamesPage);
		return "credits/creditsList";
	}

}
