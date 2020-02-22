package com.venada.efinance.controller;

import com.venada.efinance.business.UserBusiness;
import com.venada.efinance.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RankController {
	@Autowired
	private UserBusiness userBusiness;
	
	@RequestMapping(value = "/rank.html")
	public String rank(Model model){
		Map<String,Integer> limits = new HashMap<String,Integer>();
		limits.put("begin", 0);
		limits.put("end", 10);
		//财富排行榜
		List<User> cfList = userBusiness.getTopWealth(limits);
		model.addAttribute("cfList", cfList);
		//收入排行榜
		List<User> srList = userBusiness.getTopIncome(limits);
		model.addAttribute("srList", srList);
		//级别排行榜
		List<User> jbList = userBusiness.getTopLevel(limits);
		model.addAttribute("jbList", jbList);

		model.addAttribute("cfCurrentPage", 1);
		model.addAttribute("srCurrentPage", 1);
		model.addAttribute("jbCurrentPage", 1);
		return ".rank";
	}
	@RequestMapping(value = "/{currentPage}/cfPage")
	public String cfRankPage(@PathVariable Integer currentPage,Model model){
		Map<String,Integer> limits = new HashMap<String,Integer>();
		limits.put("begin", 10*(currentPage - 1));
		limits.put("end", 10);
		//财富排行榜
		List<User> cfList = userBusiness.getTopWealth(limits);
		model.addAttribute("cfList", cfList);
		model.addAttribute("cfCurrentPage", currentPage);
		return "/cfRank";
	}
	
	@RequestMapping(value = "/{currentPage}/srPage")
	public String srRankPage(@PathVariable Integer currentPage,Model model){
		Map<String,Integer> limits = new HashMap<String,Integer>();
		limits.put("begin", 10*(currentPage - 1));
		limits.put("end", 10);
		//收入排行榜
		List<User> srList = userBusiness.getTopIncome(limits);
		model.addAttribute("srList", srList);
		model.addAttribute("srCurrentPage", currentPage);
		return "/srRank";
	}
	
	@RequestMapping(value = "/{currentPage}/jbPage")
	public String jbRankPage(@PathVariable Integer currentPage,Model model){
		Map<String,Integer> limits = new HashMap<String,Integer>();
		limits.put("begin", 10*(currentPage - 1));
		limits.put("end", 10);
		//级别排行榜
		List<User> jbList = userBusiness.getTopLevel(limits);
		model.addAttribute("jbList", jbList);
		model.addAttribute("jbCurrentPage", currentPage);
		return "/jpRank";
	}
	
}
