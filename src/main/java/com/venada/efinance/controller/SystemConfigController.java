package com.venada.efinance.controller;

import com.venada.efinance.business.SystemConfigBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/test")
public class SystemConfigController {

	@Autowired
	private SystemConfigBusiness systemConfigBusiness;

	@RequestMapping(value = "/systemConfig", method = RequestMethod.GET)
	public ModelAndView test(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("systemConfig");
		return mv;
	}

}
