package com.venada.efinance.controller;

import com.venada.efinance.business.UserBusiness;
import com.venada.efinance.business.UserConvertCreditsBusiness;
import com.venada.efinance.pojo.ConvertCredits;
import com.venada.efinance.security.SpringSecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/user/credits/")
public class UserConvertCreditsController {
	private static final Logger logger = LoggerFactory
			.getLogger(UserConvertCreditsController.class);
	@Autowired
	private UserConvertCreditsBusiness userConvertCreditsBusinessImp;
	@Autowired
	private UserBusiness userBusinessImp;

//	@RequestMapping(value = { "/convertlist.html", "/convertlist" })
//	public String convertlist(
//			@RequestParam(value = "id", required = false) Integer Id,
//			PaginationMore page, Model model) {
//		User u = SpringSecurityUtil.getCurrentUser();
//		logger.info("userId='"+u.getId()+"' 查询兑换积分记录开始");
//		try {
//			List<ConvertCredits> convertCreditsList = new ArrayList<ConvertCredits>();
//			Map<String, Object> condition = new HashMap<String, Object>();
//			condition.put("userId", u.getId());
//			if (Id != null) {
//				condition.put("Id", Id);
//				page.setPageSize(10);
//				convertCreditsList = userConvertCreditsBusinessImp
//						.queryConvertCredits(condition, page);
//				model.addAttribute("page", page);
//			} else {
//				page.setPageSize(10);
//				convertCreditsList = userConvertCreditsBusinessImp
//						.queryConvertCredits(condition, page);
//				model.addAttribute("page", page);
//			}
//			model.addAttribute("convertCreditsList", convertCreditsList);
//			model.addAttribute("user", userBusinessImp.findUserById(u.getId()));
//		} catch (BusinessException e) {
//			logger.error("userId='"+u.getId()+"' 查询兑换积分记录"+e.getMessage());
//		}
//		logger.info("userId='"+u.getId()+"' 查询兑换积分记录结束");
//		return ".convertCreditsList";
//	}
	
	@RequestMapping(value = { "/convertlist.html", "/convertlist" })
	public String convertlist(HttpServletRequest request,Model model) {
		int pageSize=10;
		String type="convertlist";
		type=request.getParameter("type");
		if(null!=type){
			type="convertlist";
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
		gamesCounts=userConvertCreditsBusinessImp.queryAllConvertCreditsCount(map);
		gamesPage = (gamesCounts / pageSize) + (gamesCounts % pageSize > 0 ? 1 : 0);
      	   if (page > gamesPage && gamesCounts != 0) {
				page = gamesPage;
		   }
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("limitCount", pageSize);
		condition.put("limitIndex", (page - 1) * pageSize);
		condition.put("userid", SpringSecurityUtil.getCurrentUser().getId());
		List<ConvertCredits> convertCreditsList =new ArrayList<ConvertCredits> ();
		convertCreditsList= userConvertCreditsBusinessImp.queryConvertCreditsByMobilePhone(condition);
		model.addAttribute("convertCreditsList", convertCreditsList);
   		model.addAttribute("page", page);
   		model.addAttribute("type",type);
  	    model.addAttribute("gamesPage", gamesPage);
		return ".obtainCreditsList";
	}
	
	@RequestMapping(value = {  "getConvertlist.html"},method = RequestMethod.GET)
	public String queryAllconvertlist(HttpServletRequest request,Model model) {
		int pageSize=10;
		String type="convertlist";
		type=request.getParameter("convertlist");
		if(null!=type){
			type="convertlist";
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
		gamesCounts=userConvertCreditsBusinessImp.queryAllConvertCreditsCount(map);
		gamesPage = (gamesCounts / pageSize) + (gamesCounts % pageSize > 0 ? 1 : 0);
      	   if (page > gamesPage && gamesCounts != 0) {
				page = gamesPage;
		   }
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("limitCount", pageSize);
		condition.put("limitIndex", (page - 1) * pageSize);
		condition.put("userid", SpringSecurityUtil.getCurrentUser().getId());
		List<ConvertCredits> convertCreditsList =new ArrayList<ConvertCredits> ();
		convertCreditsList= userConvertCreditsBusinessImp.queryConvertCreditsByMobilePhone(condition);
		model.addAttribute("convertCreditsList", convertCreditsList);
   		model.addAttribute("page", page);
   		model.addAttribute("type",type);
  	    model.addAttribute("gamesPage", gamesPage);
		return "credits/convertCreditsList";
	}

}
