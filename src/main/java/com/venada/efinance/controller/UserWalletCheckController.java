package com.venada.efinance.controller;

import com.venada.efinance.business.UserBusiness;
import com.venada.efinance.business.UserWalletCheckBusiness;
import com.venada.efinance.common.controller.BaseController;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.UserWalletCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
* @ClassName: UserWalletCheckController 
* @Description: TODO(后台查看用户余额异常列表) 
* @author hepei
* @date 2014年2月12日 下午3:02:32
 */
@Controller
@RequestMapping(value = "/manager")
public class UserWalletCheckController extends BaseController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserWalletCheckController.class);

	@Autowired
	private UserBusiness userBusinessimpl;
	@Autowired
	private UserWalletCheckBusiness userWalletCheckBusinessImpl;

	@RequestMapping(value = { "/userWalletCheckList.html", "/userWalletCheckList" })
	public String convertlist(PaginationMore page, Model model,
			HttpServletRequest request) {
		logger.info("后台查看用户余额异常列表开始");
		try {
			List<UserWalletCheck> userWalletCheckList = new ArrayList<UserWalletCheck>();
			Map<String, Object> condition = new HashMap<String, Object>();
			condition = setMapCondition(request);
			logger.info("查询条件:{}",condition.toString());
			String actionStartTime = (String) condition.get("actionStartTime");
			String actionEndTime = (String) condition.get("actionEndTime");
			if ((actionStartTime != null) && (!actionStartTime.equals("")) &&(!actionStartTime.contains("00:00:00"))) {
				condition.put("actionStartTime", actionStartTime.concat(" 00:00:00"));
			}else{
			}
			if((actionEndTime != null)	&& (!actionEndTime.equals(""))&&(!actionEndTime.contains("23:59:59"))){
				condition.put("actionEndTime", actionEndTime.concat(" 23:59:59"));
			}else{
			}
			page.setPageSize(10);
			userWalletCheckList = userWalletCheckBusinessImpl.getAllUserWalletCheck(condition, page);
			model.addAttribute("page", page);
			model.addAttribute("userWalletCheckList", userWalletCheckList);
			model.addAttribute("condition", condition);
		} catch (BusinessException e) {
			logger.error(e.getMessage());
		}
		logger.info("后台查看用户余额异常列表结束");
		return "/manager/userWalletCheckList";
	}
}
