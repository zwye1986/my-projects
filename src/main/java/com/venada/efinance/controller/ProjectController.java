package com.venada.efinance.controller;


import com.venada.efinance.business.ProjectBusiness;
import com.venada.efinance.business.ProjectInvestBusiness;
import com.venada.efinance.business.ProjectInvestRateBusiness;
import com.venada.efinance.business.UserWalletBusiness;
import com.venada.efinance.common.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;

@Controller
public class ProjectController extends BaseController{
	@Autowired
	private ProjectBusiness projectBusiness;
	@Autowired
	private UserWalletBusiness userWalletBusiness;
	@Autowired
	private ProjectInvestBusiness projectInvestBusinessImp;
	@Autowired
	private ProjectInvestRateBusiness projectInvestRateBusinessImp;  
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/** Logger */
	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
	/*
	 
	@RequestMapping("project.html")
	public String project(HttpServletRequest request, Model model) {
		String type = request.getParameter("type");
		
		Map<String,Object> map = new HashMap<String,Object>();
		if ("".equals(type) || type == null || "all".equals(type)) {
			type = "all";
		}else{
			type = SystemUtils.transfer(type);
			map.put("type", type);
		}
		
		int page = 1;
		try{
			page = Integer.parseInt(request.getParameter("page"));
		}catch(Exception e){
			page = 1;
		}
		
		map.put("limitCount", 8);
		map.put("limitIndex", (page - 1) * 8);
		
		
		int pageCounts = projectBusiness.getProjectsCounts(map);
		int projectPage = (pageCounts / 8) + (pageCounts % 8 > 0 ? 1 : 0);
		
		if(page<= 1){
			page = 1;
		}
		if(page >= projectPage){
			page = projectPage;
		}
		
		int beginPage = (((page-1)/5)+1)*5-4;
		int endPage = beginPage + 4;
		if(projectPage <= endPage){
			endPage = projectPage;
		}
		model.addAttribute("beginPage", beginPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("page", page);
		
		List<Project> list = projectBusiness.queryProjects(map);
		model.addAttribute("list", list);
		model.addAttribute("type", type);
		
		Map<String,Object> typemap = new HashMap<String,Object> ();
		List<ProjectType> typeList = projectBusiness.queryProjectType(typemap);
		model.addAttribute("typeList",typeList);
		
		return ".project";
	}

	@RequestMapping("manager/adminProjectList.html")
	public String adminProjectList(PaginationMore page,HttpServletRequest request, Model model) {
		Map<String,Object> map = new HashMap<String,Object>();
		String keyword = request.getParameter("keyword");
		model.addAttribute("keyword", keyword);
		if (keyword != null && !"".equals(keyword.trim())) {
			keyword = "%" + keyword + "%";
			map.put("keyword", keyword);
		}
		List<Project> list = projectBusiness.queryProjects(map, page);
		model.addAttribute("page", page);
		model.addAttribute("list", list);
		return "manager/adminProjectList";
	}
	
	@RequestMapping("manager/adminProjectTypeList.html")
	public String adminProjectTypeList(PaginationMore page,HttpServletRequest request, Model model) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<ProjectType> list = projectBusiness.queryProjectType(map, page);
		model.addAttribute("page", page);
		model.addAttribute("list", list);
		return "manager/adminProjectTypeList";
	}

	@RequestMapping("/manager/adminProjectAdd")
	public String adminProjectAdd(PaginationMore page,
			HttpServletRequest request,Model model) {	
		String id = request.getParameter("id");
		if(!"".equals(id) && id != null){
			Project project = projectBusiness.getProjectById(id);
			model.addAttribute("project",project);
		}
		
		Map<String,Object> map = new HashMap<String,Object> ();
		List<ProjectType> typeList = projectBusiness.queryProjectType(map);
		model.addAttribute("typeList",typeList);
		
		List<ProjectInvestRate> rateList = projectInvestRateBusinessImp.getProjectInvestRateAll(map);
		model.addAttribute("rateList",rateList);
		
		return "manager/adminProjectAdd";
	}
	
	@RequestMapping("/manager/adminProjectTypeAdd.html")
	public String adminProjectTypeAdd(PaginationMore page,
			HttpServletRequest request,Model model) {	
		String id = request.getParameter("id");
		if(!"".equals(id) && id != null){
			ProjectType projectType = projectBusiness.getProjectTypeById(id);
			model.addAttribute("projectType",projectType);
		}
		
		return "manager/adminProjectTypeAdd";
	}
	
	@RequestMapping("/manager/adminProjectDealAdd")
	public String adminProjectDealAdd(PaginationMore page,
			HttpServletRequest request,Model model) {
		Project project = new Project();	
		String id = request.getParameter("id");
		Date createTime = null;
		if(!"".equals(id) && id != null){
			project = projectBusiness.getProjectById(id);
			createTime = project.getCreateTime();
		}else{
			project.setId(UUID.randomUUID().toString());
			try {
				createTime = sdf.parse(sdf.format(new Date()));
			} catch (Exception e) {
				logger.error("时间转换失败：" + new Date());
			}
		}
		String name = request.getParameter("name");
		String nameDesc = request.getParameter("namedesc");
		String type = request.getParameter("type");
		String missRateId = request.getParameter("missRate");
		String successRateId = request.getParameter("successRate");
		ProjectInvestRate missRate = projectInvestRateBusinessImp.getProjectInvestRate(missRateId);
		ProjectInvestRate successRate = projectInvestRateBusinessImp.getProjectInvestRate(successRateId);
		Calendar now = Calendar.getInstance(); 
		now.setTime(createTime);
	    now.set(Calendar.DATE, now.get(Calendar.DATE) +missRate.getInterval());
	    String endTime = sdf.format(now.getTime()).substring(0,10)+" 23:59:59";
	    
	        try {
				project.setEndTime(sdf.parse(endTime));
			} catch (ParseException e) {
				logger.error("日期转换错误");
			}
		
		
	
		
		
		
		double goalMoney = Double.parseDouble(request.getParameter("goalmoney"));
		String projectDesc = request.getParameter("projectdesc");
		String explanation = request.getParameter("explanation");
		String addr = request.getParameter("addr");
		
		
		
		
		
	    project.setAddr(addr);
	    project.setExplanation(explanation);
	    project.setGoalMoney(goalMoney);
	    project.setName(name);
	    project.setNameDesc(nameDesc);
	    project.setProjectDesc(projectDesc);
	    project.setType(type);
	    project.setCreateTime(createTime);
	    project.setDays(0);
	    project.setStatus(0);
	    now = Calendar.getInstance();  
	    now.setTime(createTime);
	    now.set(Calendar.DATE, now.get(Calendar.DATE) +successRate.getInterval());
	    
        try {
			project.setDaysDate(sdf.parse(sdf.format(now.getTime())));
		} catch (ParseException e) {
			logger.error("日期转换错误");
		}
        project.setMissRate(missRateId);
        project.setSuccessRate(successRateId);
	    
	    if(!"".equals(id) && id != null){
	    	projectBusiness.updateProject(project);
	    }else{
	    	 projectBusiness.insertProject(project);
	    }
	   

		return adminProjectList(page, request, model);
	}
	
	@RequestMapping("/manager/adminProjectTypeDealAdd")
	public String adminProjectTypeDealAdd(PaginationMore page,
			HttpServletRequest request,Model model) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
	
		
		
		ProjectType projectType = new ProjectType();
		if(!"".equals(id) && id != null){
			projectType = projectBusiness.getProjectTypeById(id);
		}else{
			projectType.setId(UUID.randomUUID().toString());
		}
		
	    projectType.setName(name);
	    
	    if(!"".equals(id) && id != null){
	    	projectBusiness.updateProjectType(projectType);
	    }else{
	    	 projectBusiness.insertProjectType(projectType);
	    }
		return adminProjectTypeList(page, request, model);
	}
	*/
	
	/**
	 * 后台游戏删除
	 */
	/*
	@RequestMapping("/manager/adminProjectDel")
	public String adminProjectDel(PaginationMore page,
			HttpServletRequest request,Model model) {
		String ids = request.getParameter("ids");
		if (ids != null && !"".equals(ids)) {
			String[] id = ids.split(",");

			for (int i = 0; i < id.length; i++) {
				Project project = projectBusiness.getProjectById(id[i]);
				if(project != null){
					projectBusiness.delProjectById(id[i]);
				}
			
			}
		}

		return adminProjectList(page, request, model);
	}
	
	@RequestMapping("/manager/adminProjectTypeDel")
	public String adminProjectTypeDel(PaginationMore page,
			HttpServletRequest request,Model model) {
		String ids = request.getParameter("ids");
		if (ids != null && !"".equals(ids)) {
			String[] id = ids.split(",");

			for (int i = 0; i < id.length; i++) {
				ProjectType projectType = projectBusiness.getProjectTypeById(id[i]);
				if(projectType != null){
					projectBusiness.delProjectTypeById(id[i]);
				}
			
			}
		}

		return adminProjectTypeList(page, request, model);
	}
	
	@RequestMapping(value="/{projectId}/projectDetail.html")
	public String projectDetail(@PathVariable String projectId,HttpServletRequest request,Model model){
		Map<String,Object> condition =setMapCondition(request);
		condition.put("ProjectId", projectId);
		List<ProjectInvest> projectInvests = projectInvestBusinessImp.selectProjectInvestByProjectId(condition);
		if (!projectInvests.isEmpty()) {
			for (ProjectInvest projectInvest : projectInvests) {
				projectInvest.setAlready_support_num(projectBusiness.getAleardySupportNum(projectInvest.getId()));
			}
			model.addAttribute("projectInvests", projectInvests);
		}
		Project project = projectBusiness.getProjectById(projectId);
		model.addAttribute("project", project);
		
		List<Project> randList = projectBusiness.queryProjectsByRandom();
		model.addAttribute("randList", randList);
		
		List<ProjectUser> userList = projectBusiness.queryProjectUserForDetail(projectId);
		model.addAttribute("userList", userList);
		
		return ".projectDetail";
	}
	
	@RequestMapping("/showProjectPic")
	public void showSquarePic(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			ServletOutputStream op = response.getOutputStream();
			String id = request.getParameter("id");
			if (!"".equals(id) && id != null) {
				response.reset();
				Project project = projectBusiness.getProjectById(id);
				if(project.getProjectPic() != null){
					op.write(project.getProjectPic());
				}
				
			}
			op.flush();
			op.close();
	}
	
	@RequestMapping("/{projectInvestid}/{count}/getProjectInfo")
	public @ResponseBody Object getProjectInfo(@PathVariable String projectInvestid,@PathVariable int count){
		Map<String,Object> resMap = new HashMap<String, Object>();
		try {
			ProjectInvest projectInvest = projectInvestBusinessImp.getProjectInvest(projectInvestid);
			if(projectInvest == null ){
				resMap.put("resCode", "0");
				resMap.put("resMsg", "参数有误！");
			}else{
				if(count > (projectInvest.getSupport_num() - projectInvest.getAlready_support_num())){
					resMap.put("resCode", "0");
					resMap.put("resMsg", "您超过了最大支持数！");
				}else{
					User user = SpringSecurityUtil.getCurrentUser();
					UserWallet userWallet = userWalletBusiness.getAmountByUserId(user.getId());
					ProjectUser puser = new ProjectUser();
					puser.setPrice(projectInvest.getAmount());
					puser.setCount(count);
					puser.setName(projectInvest.getInvest_name());
					puser.setRealAmount(projectInvest.getAmount().multiply(new BigDecimal(count)));
					puser.setUserWalletAmount(userWallet.getAmount());
					
					resMap.put("resCode", "1");
					resMap.put("resMsg", puser);
				}
			}
		} catch (BusinessException e) {
			logger.error("获取项目信息失败：" + e.getMessage());
			resMap.put("resCode", "0");
			resMap.put("resMsg", "获取项目信息失败！");
		}
		return resMap;
	}
	
	@RequestMapping("/{projectInvestid}/{count}/payForProject")
	public @ResponseBody Object payForProject(@PathVariable String projectInvestid,@PathVariable int count,HttpServletRequest request){
		Map<String,Object> resMap = new HashMap<String, Object>();
		ProjectInvest projectInvest = projectInvestBusinessImp.getProjectInvest(projectInvestid);
		if(projectInvest == null ){
			resMap.put("resCode", "0");
			resMap.put("resMsg", "参数有误！");
		}else{
			if(count > ( projectInvest.getSupport_num() - projectInvest.getAlready_support_num())){
				resMap.put("resCode", "0");
				resMap.put("resMsg", "您超过了最大支持数！");
			}else{
				User user = SpringSecurityUtil.getCurrentUser();
				UserWallet userWallet = userWalletBusiness.getAmountByUserId(user.getId());
				ProjectUser puser = new ProjectUser();
				
				puser.setId(UUID.randomUUID().toString());
				puser.setProjectInvesId(projectInvestid);
				puser.setMobileNumber(user.getMobileNumber());
				String remark = request.getParameter("remark");
				if(remark != null && !"".equals(remark.replaceAll("\\s*", ""))){
					if(remark.length() > 200){
						puser.setRemark(remark.substring(0, 200));
					}else{
						puser.setRemark(remark);
					}
				}
				puser.setPrice(projectInvest.getAmount());
				puser.setCount(count);
				puser.setName(projectInvest.getInvest_name());
				puser.setProjectId(projectInvest.getProject_id());
				puser.setRealAmount(projectInvest.getAmount().multiply(new BigDecimal(count)));
				puser.setAmount(puser.getRealAmount());
				puser.setUserid(user.getId());
				
				Project p = projectBusiness.getProjectById(puser.getProjectId());
				Double goalMoney = p.getGoalMoney();//目标金额
				Double amountMoney = p.getSumAmount();//现在已经筹集的钱
				BigDecimal futureMoney = puser.getRealAmount().add(BigDecimal.valueOf(amountMoney));
				if(BigDecimal.valueOf(goalMoney).compareTo(futureMoney) == -1){
					resMap.put("resCode", "0");
					resMap.put("resMsg", "您支持的金额超过了最大额度！");
				}else if(userWallet.getAmount().compareTo(puser.getRealAmount()) == -1){//帐户的钱不能小于支持的金额
					resMap.put("resCode", "0");
					resMap.put("resMsg", "您的余额不足！");
				}else{
					
						BigDecimal money = userWalletBusiness.payForProject(puser, userWallet, request.getRemoteAddr(), projectInvest,count);
						logger.info("扣除用户"+user.getMobileNumber()+"支持众筹项目的金额:" + money );
		                
						resMap.put("resCode", "1");
				}
				
			}
		}
		return resMap;
	}
	*/
}
