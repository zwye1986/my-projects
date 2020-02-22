package com.venada.efinance.controller;

import com.venada.efinance.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 异常处理器 web.xml error-page元素不能展示页面
 * 
 * @author sunwen
 */
@Controller
@RequestMapping(value="/errorPages")
public class ErrorController extends BaseController {
	
	@RequestMapping(value="/error403")
	public String error403(){
		return ".403";
	}
	
	@RequestMapping(value="/error404")
	public String error404(){
		return ".404";
	}
	
	@RequestMapping(value="/error500")
	public String error500(){
		return ".500";
	}
	
	@RequestMapping(value="/error503")
	public String error503(){
		return ".503";
	}

}
