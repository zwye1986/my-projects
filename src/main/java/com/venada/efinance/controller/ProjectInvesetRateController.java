package com.venada.efinance.controller;

import com.venada.efinance.business.ProjectInvestRateBusiness;
import com.venada.efinance.common.controller.BaseController;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.ProjectInvestRate;
import com.venada.efinance.util.RequestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 
 * @author hepei
 *
 */
@Controller
public class ProjectInvesetRateController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(ProjectInvesetRateController.class);
	
	@Autowired
	private ProjectInvestRateBusiness projectInvestRateBusinessImp;  
	
	@RequestMapping(value = "/manager/projectInvestRateLists")
	public String toProjectInvestLists(PaginationMore page, Model model,
			HttpServletRequest request) {
		try {
			Map<String,Object> condition =setMapCondition(request);
			page.setPageSize(20);
			List<ProjectInvestRate> projectInvestRates = projectInvestRateBusinessImp.getProjectInvestRateAll(page, condition);
			model.addAttribute("projectInvestRates", projectInvestRates);
			model.addAttribute("page", page);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "/manager/projectInvest/projectInvestsRateList";
	}

	@RequestMapping(value = "/manager/projectInvestRate/view", method = RequestMethod.GET)
	public String toAddProjectInvestRate(HttpServletRequest request, Model model) {
		try {
			String id = RequestUtils.getStringParameter(request, "ids", "");
			if (StringUtils.isNotEmpty(id)) {
				ProjectInvestRate projectInvestRate =projectInvestRateBusinessImp.getProjectInvestRate(id);
				model.addAttribute("projectInvestRate",projectInvestRate);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "/manager/projectInvest/projectInvestRate_add";
	}

	@RequestMapping(value = "/manager/projectInvestRate/save", method = RequestMethod.POST)
	public String addProjectInvest(ProjectInvestRate projectInvestRate) {
		try {
			projectInvestRate.setId(UUID.randomUUID().toString());
			projectInvestRateBusinessImp.saveProjectInvestRateRate(projectInvestRate);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "redirect:/manager/projectInvestRateLists";
	}
	
	

	@RequestMapping(value = "manager/projectInvestRate/update", method = RequestMethod.POST)
	public String updateProjectInvest(ProjectInvestRate projectInvestRate){
		try {
			projectInvestRateBusinessImp.updateProjectInvestRate(projectInvestRate);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "redirect:/manager/projectInvestLists";
	}
	
	
	
	@RequestMapping(value = "/manager/projectInvestRate/delete", method = RequestMethod.POST)
	public String deleteSystemConfig(HttpServletRequest request) {
		try {
			String[] ids = RequestUtils.getStringParameters(request, "ids");
			projectInvestRateBusinessImp.deleteProjectInvestRate(ids);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "redirect:/manager/projectInvestRateLists";
	}
	
	
}
