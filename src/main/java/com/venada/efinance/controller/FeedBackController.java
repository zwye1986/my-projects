package com.venada.efinance.controller;

import com.venada.efinance.business.AdviceBusiness;
import com.venada.efinance.business.GameBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.Advice;
import com.venada.efinance.pojo.GameRecommend;
import com.venada.efinance.pojo.User;
import com.venada.efinance.security.SpringSecurityUtil;
import com.venada.efinance.sms.SmsService;
import com.venada.efinance.util.SystemUtils;
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
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class FeedBackController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(FeedBackController.class);
	
	
	@Autowired
	private AdviceBusiness adviceBusiness;
	@Autowired
	private GameBusiness gameBusiness;
	@Autowired
	private SmsService smsService;
	
	public String toFeedBackPage(){
		return ".feedBack";
	}
	
	@RequestMapping(value="feedBack")
	public String toNewFeedBackPage(Model model){
		model.addAttribute("item", 7);
		return ".newFeedBack";
	}
	
	@RequestMapping(value="feedBackIndex")
	public String indexToFeedBackPage(){
		return ".feedBackIndex";
	}
	
	@RequestMapping(value = "manager/feedBackList")
	public String feedBackList(Model model, PaginationMore page) {
		try {
			List<Advice> list = adviceBusiness.selectAdvice(page);
			model.addAttribute("feedBackList", list);
			model.addAttribute("page", page);
		} catch (BusinessException e) {
			logger.info(e.getMessage());
		}
		return "manager/feedBackList";
	}
	
	
	@RequestMapping(value = "manager/{id}/feedbackView")
	public String feedbackView(Model model,@PathVariable String id) {
		try {
			if(id!=null){
			Advice advice=adviceBusiness.getAdviceById(id);
			model.addAttribute("advice", advice);
			}
		} catch (BusinessException e) {
			logger.info(e.getMessage());
		}
		return "manager/feedbackView";
	}
	
	
	
	public String feedbackList(Model model, PaginationMore page) {
		User u = SpringSecurityUtil.getCurrentUser();
		try {
			List<Advice> list = new ArrayList<Advice>();
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("mobilePhone", u.getMobileNumber());
			page.setPageSize(15);
			list = adviceBusiness.selectAdvice(condition, page);
			model.addAttribute("page", page);
			model.addAttribute("list", list);
		} catch (BusinessException e) {

		}
		return ".userfeedbackList";
	}
	
	@RequestMapping(value = { "user/feedbackList.html", "/feedbackList" })
	public String newFeedbackList(Model model, PaginationMore page) {
		User u = SpringSecurityUtil.getCurrentUser();
		try {
			List<Advice> list = new ArrayList<Advice>();
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("mobilePhone", u.getMobileNumber());
			page.setPageSize(15);
			list = adviceBusiness.selectAdvice(condition, page);
			model.addAttribute("page", page);
			model.addAttribute("list", list);
		} catch (BusinessException e) {

		}
		model.addAttribute("item", 8);
		return ".newUserfeedbackList";
	}
	
	   @RequestMapping(value = "/showList", method = RequestMethod.GET)
		public String showList(HttpServletRequest request,Model model) {
	    	User user = SpringSecurityUtil.getCurrentUser();
	    	if(user != null){
	    	Map<String, Object> map = new HashMap<String, Object>();
	    	map.put("mobilePhone", user.getMobileNumber());
			int page = 1;
			try {
				page = Integer.parseInt(request.getParameter("page"));
			} catch (Exception e) {
				logger.error("页数转换为数字失败：" + page);
				page = 1;
			}
			if (page < 1) {
				page = 1;
			}
			int gamesCounts = 0;
			int gamesPage = 0;
			List<Advice> list = null;
	   		
	   		   // 计算总共有多少页
			
	      	   gamesCounts = adviceBusiness.getFeedBackCount(map);
	      	   gamesPage = (gamesCounts / 8) + (gamesCounts % 8 > 0 ? 1 : 0);
	      	   if (page > gamesPage && gamesCounts != 0) {
					page = gamesPage;
			   }
	      	   map.put("limitCount", 8);
			   map.put("limitIndex", (page - 1) * 8);
			   list = adviceBusiness.selectAdvice(map);
	   		
	   		model.addAttribute("list", list);
	   		model.addAttribute("page", page);
	   	    model.addAttribute("gamesPage", gamesPage);
	    	}
	   	    return "advicelist";
	    }
	
	@RequestMapping("dealFeedBack")
	@ResponseBody
	public Object dealFeedBack(HttpServletRequest request){
		String name = request.getParameter("name");
		String advice = request.getParameter("advice");
		String contact = request.getParameter("contact");
		String checkCode = request.getParameter("checkCode");
		Map<String,String> resultMap = new HashMap<String,String>();
		if(checkCode == null || "".equals(checkCode.replaceAll("\\s*", "")) ){
			resultMap.put("code", "0");
			resultMap.put("msg", "验证码不能为空！");
			return resultMap;
		}else{
			checkCode = checkCode.replaceAll("\\s*", "");
			HttpSession session = request.getSession();
			if(session.getAttribute("feedBackCode") != null){
				String feedBackCode = session.getAttribute("feedBackCode").toString();
				if(!checkCode.equalsIgnoreCase(feedBackCode)){
					resultMap.put("code", "0");
					resultMap.put("meg", "验证码错误！");
					return resultMap;
				}
			}else{
				resultMap.put("code", "0");
				resultMap.put("meg", "验证码错误！");
				return resultMap;
			}
		}
		
		if(name == null || "".equals(name.replaceAll("\\s*", ""))){
			resultMap.put("code", "0");
			resultMap.put("meg", "姓名不能为空！");
			return resultMap;
		}else{
			name = name.replaceAll("\\s*", "");
		}
		
		if(advice == null || "".equals(advice.replaceAll("\\s*", ""))){
			resultMap.put("code", "0");
			resultMap.put("meg", "请填写建议内容！");
			return resultMap;
		}else{
			advice = advice.replaceAll("\\s*", "");
			advice = SystemUtils.StringFilter(advice);
			if(advice.length() > 200){
				resultMap.put("code", "0");
				resultMap.put("meg", "建议内容不能超过200个字符！");
				return resultMap;
			}
		}
		
		if(contact == null || "".equals(contact.replaceAll("\\s*", ""))){
			resultMap.put("code", "0");
			resultMap.put("meg", "联系方式不能为空！");
			return resultMap;
		}else{
			contact = contact.replaceAll("\\s*", "");
			if(contact.length() < 7 || contact.length() > 13){
				resultMap.put("code", "0");
				resultMap.put("meg", "请确填写联系方式！");
				return resultMap;
			}
		}
		
		Advice useradvice = new Advice();
		useradvice.setId(UUID.randomUUID().toString());
		useradvice.setAdvice(advice);
		useradvice.setContact(contact);
		useradvice.setName(name);
		
		try {
			adviceBusiness.saveAdvice(useradvice);
		} catch (BusinessException e) {
			resultMap.put("code", "0");
			resultMap.put("meg", "系统出错！");
			return resultMap;
		}
		resultMap.put("code", "1");
		resultMap.put("meg", "操作成功！");
		return resultMap;
	}
	
	public String feedBackMessage(Model model){
		List<GameRecommend> commendList = gameBusiness.queryGameRecommendInSearch();
		if(commendList.size() > 8){
			commendList = commendList.subList(0, 8);
		}
		
		model.addAttribute("games", commendList);
		return ".feedBackMessage";
	}
	
	@RequestMapping(value="feedBackMessage")
	public String newFeedBackMessage(Model model){
		return ".newFeedBackMessage";
	}
	
	@RequestMapping(value="feedBackMessageIndex")
	public String feedBackMessageIndex(Model model){
		List<GameRecommend> commendList = gameBusiness.queryGameRecommendInSearch();
		if(commendList.size() > 8){
			commendList = commendList.subList(0, 8);
		}
		
		model.addAttribute("games", commendList);
		return ".feedBackMessageIndex";
	}
	
	@RequestMapping(value = "/manager/updateFeedBack")
	@ResponseBody
	public Map<String, Object> updateFeedBack(Advice u) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			Advice advice = adviceBusiness.getAdviceById(u.getId());
			advice.setId(u.getId());
			advice.setReplyContent(u.getReplyContent());
			advice.setReplyer(SpringSecurityUtil.getCurrentUser()
					.getMobileNumber());
			int i = adviceBusiness.updateAdvice(advice);
			if (i > 0) {
				try {
						smsService.sendSms(advice.getContact(),
								advice.getReplyContent());
					
					data.put("msgcode", "true");
					data.put("msg", "回复成功");
				} catch (BusinessException e) {
					data.put("msgcode", "true");
					data.put("msg", "短信发送失败成功");
				}
			} else {
				data.put("msgcode", "false");
				data.put("msg", "回复失败");
			}
		} catch (BusinessException e) {
			logger.error(e.getMessage());
		}
		return data;
	}
	
	@RequestMapping(value = "/manager/getAdviceById")
	@ResponseBody
	public Map<String, Object> getAdviceById(String Id) {
		Map<String, Object> data = new HashMap<String, Object>();
		Advice advice=adviceBusiness.getAdviceById(Id);
		data.put("id", advice.getId());
		data.put("name", advice.getName());
		data.put("contact", advice.getContact());
		data.put("replayStatus", advice.getReplayStatus());
		return data;
	}
	
	
	@RequestMapping(value = "/user/manager/getUserAdviceById")
	@ResponseBody
	public Map<String, Object> getUserAdviceById(String id) {
		Map<String, Object> data = new HashMap<String, Object>();
		Advice advice=adviceBusiness.getAdviceById(id);
		if (advice != null) {
			data.put("id", advice.getId());
			data.put("name", advice.getName());
			data.put("contact", advice.getContact());
			data.put("replayStatus", advice.getReplayStatus());
			data.put("advice", advice.getAdvice());
			data.put("replyContent", advice.getReplyContent());
		} else {
            data.put("errorcode", "1");
            data.put("errormsg", "数据异常");
		}
		return data;
	}
	
}
