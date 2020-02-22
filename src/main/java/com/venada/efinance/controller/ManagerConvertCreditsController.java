package com.venada.efinance.controller;

import com.venada.efinance.business.UserBusiness;
import com.venada.efinance.business.UserConvertCreditsBusiness;
import com.venada.efinance.common.controller.BaseController;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.ConvertCredits;
import com.venada.efinance.pojo.User;
import com.venada.efinance.security.SpringSecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping(value = "/manager")
public class ManagerConvertCreditsController  extends BaseController{
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory
			.getLogger(ManagerConvertCreditsController.class);
	@Autowired
	private UserConvertCreditsBusiness userConvertCreditsBusinessImp;
	@Autowired
	private UserBusiness  userBusinessimpl;
	
	
	@RequestMapping(value = { "/convertCreditsList.html", "/convertCreditsList" })
	public String convertlist(
			@RequestParam(value = "id", required = false) Integer Id,
			PaginationMore page, Model model,HttpServletRequest request) {
		List<ConvertCredits> convertCreditsList = new ArrayList<ConvertCredits>();
		Map<String, Object> condition = new HashMap<String, Object>();
		condition=setMapCondition(request);
		
		String actionStartTime = (String) condition.get("actionStartTime");
		String actionEndTime = (String) condition.get("actionEndTime");
		if ((actionStartTime != null) && (!actionStartTime.equals("")) || (actionEndTime != null)
				&& (!actionEndTime.equals(""))) {
			condition.put("actionStartTime", actionStartTime.concat(" 00:00:00"));
			condition.put("actionEndTime", actionEndTime.concat(" 23:59:59"));
		}
		String mobilePhone=(String) condition.get("mobilePhone");
		if(mobilePhone!=null){
			User u =userBusinessimpl.findUserByMoblieNumber(mobilePhone);
			if(u!=null){
				condition.put("userId",u.getId());
			}else{
				condition.put("userId"," ");
			}
		}
		
		if (Id != null) {
			condition.put("Id", Id);
			page.setPageSize(10);
			convertCreditsList = userConvertCreditsBusinessImp.queryConvertCredits(condition,page);
			model.addAttribute("page", page);
		} else {
			page.setPageSize(10);
			convertCreditsList = userConvertCreditsBusinessImp.queryConvertCredits(condition, page);
			model.addAttribute("page", page);
		}
		
		model.addAttribute("convertCreditsList", convertCreditsList);
		model.addAttribute("condition", condition);
		return "/manager/converCreditsList";
	}

	@RequestMapping(value = "/creditsManager/{id}/showDetail")
	public String showDetail( @PathVariable String id,
			PaginationMore page, Model model) {
		ConvertCredits convertCredits =new  ConvertCredits();
		Map<String, Object> condition = new HashMap<String, Object>();
		if (id != null) {
			condition.put("Id",id);
			convertCredits = userConvertCreditsBusinessImp.queryConvertCreditsById(condition);
		} 
		model.addAttribute("convertCredits", convertCredits);
		return "/manager/showConvertCreditsDetail";
	}

	@RequestMapping(value = "/creditsManager/{id}/toUpdate")
	public String toUpdate(
			@PathVariable String id,
			PaginationMore page, Model model) {
		ConvertCredits convertCredits =new  ConvertCredits();
		Map<String, Object> condition = new HashMap<String, Object>();
		if (id != null) {
			condition.put("Id", id);
			convertCredits = userConvertCreditsBusinessImp.queryConvertCreditsById(condition);
		} 
		model.addAttribute("convertCredits", convertCredits);
		return "/manager/modifyConvertCreditsDetail";
	}
	
	@RequestMapping(value="/credits/updateConvertCredits")
	public String updateConvertCredits(HttpServletRequest request){
		String id = request.getParameter("id");
		int status = Integer.parseInt(request.getParameter("status"));
		String remark = request.getParameter("remark");
	    User user=	SpringSecurityUtil.getCurrentUser();
		
		ConvertCredits convertCredits=new ConvertCredits();
		convertCredits.setId(id);
		convertCredits.setStatus(status);
		convertCredits.setRemark(remark);
		convertCredits.setModifyBy(user.getMobileNumber());
		convertCredits.setModifyTime(new Date());
		userConvertCreditsBusinessImp.updateConvertCreditsById(convertCredits);
		return "redirect:/manager/convertCreditsList";
	}
	
}
