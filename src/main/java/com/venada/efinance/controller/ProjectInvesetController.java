package com.venada.efinance.controller;

import com.venada.efinance.business.ProjectBusiness;
import com.venada.efinance.business.ProjectInvestBusiness;
import com.venada.efinance.business.ProjectInvestRateBusiness;
import com.venada.efinance.common.controller.BaseController;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.Project;
import com.venada.efinance.pojo.ProjectInvest;
import com.venada.efinance.util.RequestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProjectInvesetController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(ProjectInvesetController.class);
	
	@Autowired
	private ProjectInvestBusiness projectInvestBusinessImp;  
	@Autowired
	private ProjectBusiness projectBusiness;
	@Autowired
	private ProjectInvestRateBusiness projectInvestRateBusinessImp;
	
	@RequestMapping(value = "/manager/{projectId}/projectInvestLists")
	public String toProjectInvestLists(PaginationMore page, Model model,
			HttpServletRequest request,@PathVariable String projectId) {
		try {
			Map<String,Object> condition =setMapCondition(request);
			condition.put("ProjectId", projectId);
			page.setPageSize(20);
			List<ProjectInvest> projectInvests = projectInvestBusinessImp.getProjectInvestAll(page, condition);
			Project project =projectBusiness.getProjectById(projectId);
			if(project!=null){
				model.addAttribute("projectName", project.getName());
			}
			model.addAttribute("projectInvests", projectInvests);
			model.addAttribute("page", page);
		    model.addAttribute("projectId",projectId);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "/manager/projectInvest/projectInvestsList";
	}

	@RequestMapping(value = "/manager/projectInvest/view", method = RequestMethod.GET)
	public String toAddProjectInvest(HttpServletRequest request, Model model) {
		try {
			String id = RequestUtils.getStringParameter(request, "ids", "");
			if (StringUtils.isNotEmpty(id)) {
				ProjectInvest projectInvest =projectInvestBusinessImp.getProjectInvest(id);
				model.addAttribute("projectInvest",projectInvest);
				
				Project project =projectBusiness.getProjectById(projectInvest.getProject_id());
				if(project!=null){
					model.addAttribute("projectName", project.getName());
				}
			}
			String projectId = RequestUtils.getStringParameter(request, "projectId", "");
			if (projectId != null && !projectId.equals("")) {
				Project project = projectBusiness.getProjectById(projectId);
				if (project != null) {
					model.addAttribute("projectName", project.getName());
				}
				model.addAttribute("projectId", projectId);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "/manager/projectInvest/projectInvest_add";
	}
	
	@RequestMapping(value = "/manager/projectInvest/getProjcetAmount", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getProjcetAmount(ProjectInvest projectInvest){
		Map<String,Object> result=new HashMap<String,Object>();
		try {
			BigDecimal amount=projectInvest.getAmount();
			BigDecimal missRate;
			BigDecimal successRate;
			String projectId =projectInvest.getProject_id();
			if(projectId!=null){
				Project project=projectBusiness.getProjectById(projectId);
				if(project!=null){
					if(project.getMissRate()!=null){
						 missRate=projectInvestRateBusinessImp.getProjectInvestRate(project.getMissRate()).getSuccessRate();
						if (missRate == null) {
							result.put("code", "1");
							result.put("resultMsg", "项目回报利率未设置");
						} else {
							result.put("code", "0");
							result.put("miss_benefit_amount",amount.multiply(missRate));
						}
					}else{
						result.put("code", "1");
						result.put("resultMsg", "项目回报利率未设置");
					}
					if(project.getSuccessRate()!=null){
						successRate=projectInvestRateBusinessImp.getProjectInvestRate(project.getSuccessRate()).getSuccessRate();
						if (successRate == null) {
							result.put("code", "1");
							result.put("resultMsg", "项目回报利率未设置");
						} else {
							result.put("code", "0");
							result.put("benefit_amount", amount.multiply(successRate));
						}
					}else{
						result.put("code", "1");
						result.put("resultMsg", "项目回报利率未设置");
					}
				
				}
			}else{
				result.put("code", "1");
				result.put("resultMsg", "数据提交错误！");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return   result;
	}
	
	@RequestMapping(value = "/manager/projectInvest/getSumProjectInvest", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getSumProjectInvest(ProjectInvest projectInvest){
		Map<String,Object> result=new HashMap<String,Object>();
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			BigDecimal amount;
			String projectId =projectInvest.getProject_id();
			map.put("ProjectId", projectId);
			if(projectId!=null){
				Project project=projectBusiness.getProjectById(projectId);
				BigDecimal sum=projectInvestBusinessImp.getSumProjectInvest(map);
				if(project!=null){
					amount=	BigDecimal.valueOf(project.getGoalMoney()).subtract(sum);
					result.put("code", "0");
					result.put("amount", amount);
				}else{
					result.put("code", "1");
					result.put("resultMsg", "数据提交错误！");
				}
			}else{
				result.put("code", "1");
				result.put("resultMsg", "数据提交错误！");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return   result;
	}
	
	@RequestMapping(value = "/manager/projectInvest/save", method = RequestMethod.POST)
	public String addProjectInvest(ProjectInvest projectInvest) {
		String projectId=projectInvest.getProject_id();
		try {
			projectInvestBusinessImp.saveProjectInvest(projectInvest);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "redirect:/manager/"+projectId+"/projectInvestLists";
	}

	@RequestMapping(value = "manager/projectInvest/update", method = RequestMethod.POST)
	public String updateProjectInvest(ProjectInvest projectInvest){
		String projectId=projectInvest.getProject_id();
		try {
			projectInvestBusinessImp.updateProjectInvest(projectInvest);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "redirect:/manager/"+projectId+"/projectInvestLists";
	}
	@RequestMapping(value = "/manager/projectInvest/delete", method = RequestMethod.POST)
	public String deleteSystemConfig(HttpServletRequest request) {
		try {
			String[] ids = RequestUtils.getStringParameters(request, "ids");
			projectInvestBusinessImp.deleteProjectInvest(ids);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "redirect:/manager/projectInvestLists";
	}

	
	
}
