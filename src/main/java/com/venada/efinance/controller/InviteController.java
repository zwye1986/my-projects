package com.venada.efinance.controller;

import com.venada.efinance.business.InviteBenefitBusiness;
import com.venada.efinance.business.UserBusiness;
import com.venada.efinance.common.controller.BaseController;
import com.venada.efinance.pojo.InviteBenefit;
import com.venada.efinance.pojo.User;
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
public class InviteController extends BaseController{
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(InviteController.class);

	@Autowired
	private InviteBenefitBusiness inviteBenefitBusinessImp;
	@Autowired
	private UserBusiness userBusinessImpl;
	
//	@RequestMapping(value = "/user/account/getMyInviteList")
//	public String getMyInviteList(PaginationMore page, Model model) {
//		User u = SpringSecurityUtil.getCurrentUser();
//		Map<String, Object> condition = new HashMap<String, Object>();
//		condition.put("inviteUserId", u.getId());
//		try {
//			page.setPageSize(15);
//			List<InviteBenefit> list = inviteBenefitBusinessImp.queryInviteBenefit(condition, page);
//			model.addAttribute("inviteBenefitList", list);
//			model.addAttribute("total",inviteBenefitBusinessImp.getInviteBenefitTotal(condition));
//		} catch (BusinessException e) {
//		}
//		model.addAttribute("page", page);
//		return ".myInviteList";
//	}
	

	@RequestMapping(value = "/user/account/getMyInviteList", method = RequestMethod.GET)
	public String getMyInviteList(HttpServletRequest request, Model model) {
		String type = request.getParameter("type");
		int pageSize=10;
		if (null == type) {
			type = "getMyInviteList";
		}
		User u = SpringSecurityUtil.getCurrentUser();
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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("inviteUserId", u.getId());
		gamesCounts = inviteBenefitBusinessImp.getInviteBenefitCount(map);
		gamesPage = (gamesCounts / pageSize) + (gamesCounts % pageSize > 0 ? 1 : 0);
		if (page > gamesPage && gamesCounts != 0) {
			page = gamesPage;
		}
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("limitCount", pageSize);
		condition.put("limitIndex", (page - 1) * pageSize);
		condition.put("inviteUserId", u.getId());
		List<InviteBenefit> list = new ArrayList<InviteBenefit>();
		list = inviteBenefitBusinessImp.queryInviteBenefitByPhone(condition);
		model.addAttribute("type", type);
		model.addAttribute("gamesPage", gamesPage);
		model.addAttribute("page", page);
		model.addAttribute("inviteBenefitList", list);
		model.addAttribute("total",inviteBenefitBusinessImp.getInviteBenefitTotal(condition));
		return "/invite/myInviteList";
	}
	
	@RequestMapping(value = "/user/account/getMyInvite")
	public String getMyInvite(HttpServletRequest request,Model model) {
		User u = SpringSecurityUtil.getCurrentUser();
		HttpSession session = request.getSession();
		String inviteCodeSelf=u.getInviteCodeSelf();
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("inviteUserId", u.getId());
		session.setAttribute("total",inviteBenefitBusinessImp.getInviteBenefitTotal(condition));
		session.setAttribute("inviteCodeSelf", inviteCodeSelf);
		return ".newMyInvite";
	}
	
	@RequestMapping(value = "/user/account/getMyInviteCode",method=RequestMethod.GET)
	public String getMyInviteCode(HttpServletRequest request,Model model) {
		String type=request.getParameter("type");
		if(null==type){
			type="mycode";
		}
		User u = SpringSecurityUtil.getCurrentUser();
		HttpSession session = request.getSession();
		String inviteCodeSelf=u.getInviteCodeSelf();
		session.setAttribute("inviteCodeSelf", inviteCodeSelf);
		return "/invite/myInviteCode";
	}
	
	
//	@RequestMapping(value = "/user/account/getMyInviteUser")
//	public String getMyInviteUser(PaginationMore page,Model model) {
//		User u = SpringSecurityUtil.getCurrentUser();
//		Map<String, Object> condition = new HashMap<String, Object>();
//		condition.put("inviteCodeFromOther", u.getInviteCodeSelf());
//		page.setPageSize(15);
//		List<User> userlist=userBusinessImpl.listUserByInviteCodeFromOther(page, condition);
//		model.addAttribute("userlist",userlist);
//		model.addAttribute("page", page);
//		return ".myInviteUser";
//	}
	
	@RequestMapping(value = "/user/account/getMyInviteUser",method=RequestMethod.GET)
	public String getMyInviteUser(HttpServletRequest request,Model model) {
		String type=request.getParameter("type");
		int pageSize=10;
		if(null==type){
			type="myInviteUser";
		}
		User u = SpringSecurityUtil.getCurrentUser();
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
		map.put("inviteCodeFromOther", u.getInviteCodeSelf());
		gamesCounts=userBusinessImpl.getUsersCountByInviteCodeFromOther(map);
		gamesPage = (gamesCounts / pageSize) + (gamesCounts % pageSize > 0 ? 1 : 0);
      	   if (page > gamesPage && gamesCounts != 0) {
				page = gamesPage;
		   }
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("limitCount", pageSize);
		condition.put("limitIndex", (page - 1) * pageSize);
		condition.put("inviteCodeFromOther", u.getInviteCodeSelf());
		List<User> userlist =new ArrayList<User>();
		userlist=userBusinessImpl.listUserByInviteCodeFromOtherMobilePhone(condition);
   		model.addAttribute("type",type);
  	    model.addAttribute("gamesPage", gamesPage);
		model.addAttribute("userlist",userlist);
		model.addAttribute("page", page);
		return "/invite/myInviteUser";
	}
	
	
	
}
