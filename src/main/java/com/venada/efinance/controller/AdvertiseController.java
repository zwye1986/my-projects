package com.venada.efinance.controller;


import com.venada.efinance.business.AdvertiseImagBusiness;
import com.venada.efinance.business.AdvertisePositionBussiness;
import com.venada.efinance.business.AdvertisingBusiness;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.AdvertisePosition;
import com.venada.efinance.pojo.Advertising;
import com.venada.efinance.util.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@Controller
public class AdvertiseController {
	private static final Logger logger = LoggerFactory.getLogger(AdvertiseController.class);
	@Autowired
	private AdvertisingBusiness advertisingBusiness;
	@Autowired
	private AdvertiseImagBusiness advertiseImagBusiness;
    @Autowired
    private AdvertisePositionBussiness advertisePositionBussiness;
	@RequestMapping(value="manager/addAdvertise")
	public String addAdvertising(Model model){
        List<AdvertisePosition> advertisePositions = advertisePositionBussiness.getAdvertisePositionList();
        model.addAttribute("advertisePositions",advertisePositions);
		return "manager/addAdvertise";
	}
	
	@RequestMapping(value = "manager/saveAdvertising")
	public ModelAndView saveAdvertise(Advertising advertising){
		try {
			advertisingBusiness.saveAdvertising(advertising);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ModelAndView("redirect:/manager/showAdverstisings");
	}
	
	@RequestMapping(value = "manager/showAdverstisings")
	public ModelAndView showAdverstisings(PaginationMore page){
		page.setPageSize(20);
		List<Advertising> advertisings = advertisingBusiness.getAdvertising(page);
		ModelAndView mv = new ModelAndView("manager/showAdverstisings");
		mv.addObject("page",page);
		mv.addObject("advertisings", advertisings);
		return mv;
	}
	@RequestMapping(value="manager/toUpdateAdver")
	public String toUpdateAdver(Model model,HttpServletRequest request){
		String advertisingId = request.getParameter("ids");
		Advertising advertising = advertisingBusiness.getAdvertisingById(advertisingId);
		model.addAttribute("advertising", advertising);
		return addAdvertising(model);
	}
	
	@RequestMapping("manager/dealUpdateAdvertise")
	public ModelAndView dealUpdateAdvertise(Advertising advertising) {
		advertisingBusiness.updateAdvertise(advertising);
		return new ModelAndView("redirect:/manager/showAdverstisings");
	}
	@RequestMapping("manager/delAdver")
	public ModelAndView delAdver(HttpServletRequest request,HttpServletResponse response){
		 String[] ids = RequestUtils.getStringParameters(request, "ids");
			for(String str : ids){
				advertisingBusiness.deleteAdvertise(str);
				advertiseImagBusiness.deleteAdvertiseImg(str);
			}
		
		return new ModelAndView("redirect:/manager/showAdverstisings");
	}
	
}
