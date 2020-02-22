package com.venada.efinance.controller;

import com.venada.efinance.business.ResourceBusiness;
import com.venada.efinance.business.UserBusiness;
import com.venada.efinance.business.UserConvertCreditsBusiness;
import com.venada.efinance.common.controller.BaseController;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.ConvertCredits;
import com.venada.efinance.pojo.User;
import com.venada.efinance.security.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



@Controller
@RequestMapping("/manager")
public class ManagerConvertCreditsJsonController extends BaseController {

	@Autowired
	private ResourceBusiness resourceBusinessImpl;
	@Autowired
	private UserConvertCreditsBusiness userConvertCreditsBusinessImp;
	@Autowired
	private UserBusiness  userBusinessimpl;

	
	@ResponseBody
	@RequestMapping(value = "/creditsManagerByJson/showDetailByJson")
	public Map<String, Object> showDetailByJson(HttpServletRequest request,
			PaginationMore page, Model model) {
		String id=request.getParameter("id");
		ConvertCredits convertCredits = new ConvertCredits();
		Map<String, Object> condition = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();

		if (id != null) {
			condition.put("Id", id);
			convertCredits = userConvertCreditsBusinessImp.queryConvertCreditsById(condition);
		    result.put("id", convertCredits.getId());
		    result.put("creditsGoodsName", convertCredits.getCreditsGoodsName());
		    result.put("num", convertCredits.getNum());
		    result.put("expendCredits", convertCredits.getExpendCredits());
		    result.put("actionTime", convertCredits.getActionTime());
		    result.put("username", convertCredits.getUser().getName());
		    result.put("umobileNumber", convertCredits.getUser().getMobileNumber());
		    if(null!=convertCredits.getMobileNumber()&&!convertCredits.getMobileNumber().equals("")&&!convertCredits.getMobileNumber().equals("0")){
			    result.put("mobileNumber", convertCredits.getMobileNumber());
		    }else{
			    result.put("mobileNumber", "");
		    }
		    result.put("status", convertCredits.getStatus());
		    result.put("remark", convertCredits.getRemark());
		}
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/creditsManagerByJson/toUpdate")
	public Map<String, Object> toUpdate( HttpServletRequest request,
			Model model) {
		Map<String, Object> data = new HashMap<String, Object>();
		int status = Integer.parseInt(request.getParameter("status"));
		String remark = request.getParameter("remark");
		String id=request.getParameter("id");
	    User user=	SpringSecurityUtil.getCurrentUser();
		ConvertCredits convertCredits=new ConvertCredits();
		convertCredits.setId(id);
		convertCredits.setStatus(status);
		convertCredits.setRemark(remark);
		convertCredits.setModifyBy(user.getMobileNumber());
		convertCredits.setModifyTime(new Date());
		try {
			userConvertCreditsBusinessImp.updateConvertCreditsById(convertCredits);
			data.put("msgcode", "true");
			data.put("msg", "更新成功");
		} catch (Exception e) {
			data.put("msgcode", "false");
			data.put("msg", "更新失败");
		}
		return data;
	}
	
	
	
	
	
	
}
