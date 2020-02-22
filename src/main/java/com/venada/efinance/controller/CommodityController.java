package com.venada.efinance.controller;


import com.venada.efinance.business.CommodityBusiness;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.Commodity;
import com.venada.efinance.pojo.CommodityCategory;
import com.venada.efinance.pojo.CommodityPic;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class CommodityController {
	/** Logger */
	private static final Logger logger = LoggerFactory.getLogger(CommodityController.class);
	@Autowired
	private CommodityBusiness commodityBusiness;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@RequestMapping("manager/category")
	public String category(HttpServletRequest request,PaginationMore page,Model model) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			String keyword = request.getParameter("keyword");
			String minIntegral= request.getParameter("minIntegral");
			String maxIntegral= request.getParameter("maxIntegral");
			if (keyword != null && !"".equals(keyword.trim())) {
				keyword = "%" + keyword + "%";
				map.put("keyword", keyword);
			}
			if (minIntegral != null && !"".equals(minIntegral.trim())) {
				map.put("minIntegral", minIntegral);
			}
			if (maxIntegral != null && !"".equals(maxIntegral.trim())) {
				map.put("maxIntegral", maxIntegral);
			}
			List<CommodityCategory>list = commodityBusiness.queryCommodityCategory(map, page);
			model.addAttribute("list", list);
			model.addAttribute("page", page);
		}catch(Exception e){
			logger.error("跳转页面出错");
		}
		return "manager/adminCategoryList";
	}
	
	@RequestMapping("manager/adminCategoryAdd")
	public String adminCategoryAdd(HttpServletRequest request,Model model){
		String id = request.getParameter("id");
		if (id != null && !"".equals(id)) {
			CommodityCategory category = commodityBusiness.getCommodityCategory(id);
			model.addAttribute("category", category);
		}
		return "manager/adminCategoryAdd";
	}
	
	@RequestMapping("/manager/adminCategoryDealAdd")
	public ModelAndView adminCategoryDealAdd(PaginationMore page,
			HttpServletRequest request) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		CommodityCategory category = new CommodityCategory();
		if (id != null && !"".equals(id)) {
			category = commodityBusiness.getCommodityCategory(id);
		} else {
			category.setId(UUID.randomUUID().toString());
		}
		category.setName(name);
	
		// 判断新增还是更新
		if (id != null && !"".equals(id)) {
			commodityBusiness.updateCommodityCategory(category);
		} else {
			commodityBusiness.addCommodityCategory(category);
		}

		// 跳转页面
		ModelAndView mv = new ModelAndView();
		mv.setViewName("manager/adminCategoryList");
		Map<String, Object> map = new HashMap<String, Object>();

		List<CommodityCategory>list = commodityBusiness.queryCommodityCategory(map, page);
		mv.addObject("list", list);
		mv.addObject("page", page);
		return mv;
	}
	
	@RequestMapping("/manager/adminCategoryDel")
	public ModelAndView adminCategoryDel(PaginationMore page,
			HttpServletRequest request) {
		String ids = request.getParameter("ids");
		if (ids != null && !"".equals(ids)) {
			String[] id = ids.split(",");

			for (int i = 0; i < id.length; i++) {
				commodityBusiness.delCommodityCategory(id[i]);
			}
		}

		// 跳转页面
		ModelAndView mv = new ModelAndView();
		mv.setViewName("manager/adminCategoryList");
		Map<String, Object> map = new HashMap<String, Object>();

		List<CommodityCategory>list = commodityBusiness.queryCommodityCategory(map, page);
		mv.addObject("list", list);
		mv.addObject("page", page);

		return mv;
	}
	
	@RequestMapping("/manager/adminCommodityDel")
	public String adminCommodityDel(PaginationMore page,
			HttpServletRequest request,Model model) {
		String ids = request.getParameter("ids");
		if (ids != null && !"".equals(ids)) {
			String[] id = ids.split(",");

			for (int i = 0; i < id.length; i++) {
				commodityBusiness.delCommodity(id[i]);
			}
		}

		// 跳转页面
				Map<String, Object> map = new HashMap<String, Object>();
				String keyword = request.getParameter("keyword");
				String minIntegral= request.getParameter("minIntegral");
				String maxIntegral= request.getParameter("maxIntegral");
				if (keyword != null && !"".equals(keyword.trim())) {
					keyword = "%" + keyword + "%";
					map.put("keyword", keyword);
				}
				if (minIntegral != null && !"".equals(minIntegral.trim())) {
					map.put("minIntegral", minIntegral);
				}
				if (maxIntegral != null && !"".equals(maxIntegral.trim())) {
					map.put("maxIntegral", maxIntegral);
				}
				List<Commodity>list = commodityBusiness.queryCommodity(map, page);
				model.addAttribute("list", list);
				model.addAttribute("page", page);
				return "manager/adminCommodityList";
	}
	
	@RequestMapping("manager/commodity")
	public String commodity(HttpServletRequest request,PaginationMore page,Model model) {
		try{
			page.setPageSize(10);
			Map<String, Object> map = new HashMap<String, Object>();
			String keyword = request.getParameter("keyword");
			String minIntegral= request.getParameter("minIntegral");
			String maxIntegral= request.getParameter("maxIntegral");
			String category = request.getParameter("category");
			if (keyword != null && !"".equals(keyword.trim())) {
				keyword = "%" + keyword + "%";
				map.put("keyword", keyword);
			}
			if (minIntegral != null && !"".equals(minIntegral.trim())) {
				map.put("minIntegral", minIntegral);
			}
			if (maxIntegral != null && !"".equals(maxIntegral.trim())) {
				map.put("maxIntegral", maxIntegral);
			}
			if (category != null && !"".equals(category.trim())) {
				map.put("category", category);
			}
			List<Commodity>list = commodityBusiness.queryCommodity(map, page);
			model.addAttribute("list", list);
			model.addAttribute("page", page);
			
			Map<String, Object> catemap = new HashMap<String, Object>();
			List<CommodityCategory> typeList = commodityBusiness.queryCommodityCategory(catemap);
			model.addAttribute("typeList", typeList);
			model.addAttribute("category", category);
			
		}catch(Exception e){
			logger.error("跳转页面出错");
		}
		return "manager/adminCommodityList";
	}
	
	@RequestMapping("/manager/adminCommodityAdd")
	public ModelAndView adminCommodityAdd(PaginationMore page,
			HttpServletRequest request) { 
		ModelAndView mv = new ModelAndView(); 
		mv.setViewName("manager/adminCommodityAdd");
		String id = request.getParameter("id");
		if (id != null && !"".equals(id)) {
			Commodity commodity = commodityBusiness.getCommodity(id);
			mv.addObject("commodity", commodity);

			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("commodityId", commodity.getId());
			// 商品图片
			List<CommodityPic> list = commodityBusiness.queryCommodityPics(condition);
			mv.addObject("list", list);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<CommodityCategory> typeList = commodityBusiness.queryCommodityCategory(map);
		mv.addObject("typeList", typeList);
		return mv;
	}
	
	
	
	@RequestMapping(value="commodityUpload.do", method = RequestMethod.POST)
	public @ResponseBody
	String commodityUpload(HttpServletRequest request,
			HttpServletResponse response) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		String picids = "";

		try {
			for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
				MultipartFile mf = entity.getValue();
			    CommodityPic pic = new CommodityPic();
			    String id = UUID.randomUUID().toString();
			    pic.setId(id);
			    pic.setName(request.getParameter("name"));
			    pic.setPic(mf.getBytes());
			    pic.setAddTime(sdf.parse(sdf.format(new Date())));
			    
			    //生成中等缩略图
				OutputStream os_m = new ByteArrayOutputStream();
				Thumbnails.of( mf.getInputStream()).size(600, 645).toOutputStream(os_m);
			    pic.setMediumPic(((ByteArrayOutputStream)os_m).toByteArray());
			    os_m.close();
			    
			    //生成小缩略图
				OutputStream os_s = new ByteArrayOutputStream();
				Thumbnails.of( mf.getInputStream()).size(300, 300).toOutputStream(os_s);
			    pic.setSmallPic(((ByteArrayOutputStream)os_s).toByteArray());
				os_s.close();
			    commodityBusiness.addCommodityPic(pic);
				picids = picids+","+id;
			}
		} catch (Exception e) {
			logger.error("数字转换错误:"+e.getMessage());
		}

		return picids.substring(1);
	}
	
	@RequestMapping("/showSmallPic")
	public void showSmallPic(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			ServletOutputStream op = response.getOutputStream();
			
			if (!"".equals(id) && id != null) {
				response.reset();
				CommodityPic pic = commodityBusiness.getCommodityPic(id);
				op.write(pic.getSmallPic());
			} 
			op.flush();
			op.close();
		} catch (Exception e) {
			logger.error("展示图片错误，主键Id为:"+id +"错误原因为:"+ e.getMessage());
		}
	}
	
	@RequestMapping("/showPic")
	public void showPic(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			ServletOutputStream op = response.getOutputStream();
			
			if (!"".equals(id) && id != null) {
				response.reset();
				CommodityPic pic = commodityBusiness.getCommodityPic(id);
				op.write(pic.getPic());
			} 
			op.flush();
			op.close();
		} catch (Exception e) {
			logger.error("展示图片错误，主键Id为:"+id +"错误原因为:"+ e.getMessage());
		}
	}
	
	@RequestMapping("/showMediumPic")
	public void showMediumPic(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			ServletOutputStream op = response.getOutputStream();
			
			if (!"".equals(id) && id != null) {
				response.reset();
				CommodityPic pic = commodityBusiness.getCommodityPic(id);
				op.write(pic.getMediumPic());
			} 
			op.flush();
			op.close();
		} catch (Exception e) {
			logger.error("展示图片错误，主键Id为:"+id +"错误原因为:"+ e.getMessage());
		}
	}
	
	@RequestMapping("/delPic")
	public void delPic(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			if (!"".equals(id) && id != null) {
				response.reset();
				commodityBusiness.delCommodityPic(id);
			} 
		} catch (Exception e) {
			logger.error("展示删除错误" + e.getMessage());
		}
	}
	
	@RequestMapping("/manager/adminCommodityDealAdd")
	public String adminCommodtiyDealAdd(PaginationMore page,
			HttpServletRequest request,Model model) {
		page.setPageSize(10);
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String category = request.getParameter("category");
		String desc = request.getParameter("content1");
		String price = request.getParameter("price");
		String num = request.getParameter("num");
		String integral = request.getParameter("integral");
		String models = request.getParameter("model");
		String remark = request.getParameter("remark");
		
		String[] picid = request.getParameterValues("picid");
		Commodity commodity = new Commodity();
		if (id != null && !"".equals(id)) {
			commodity = commodityBusiness.getCommodity(id);
		} else {
			commodity.setId(UUID.randomUUID().toString());
		}
		commodity.setModel(models);
		commodity.setRemark(remark);
		commodity.setName(name);
		commodity.setPrice(Float.parseFloat(price));
		commodity.setIntegral(Integer.parseInt(integral));
		commodity.setNum(Integer.parseInt(num));
		Date CreateTime = null;
		try {
			CreateTime = sdf.parse(sdf.format(new Date()));
			commodity.setAddTime(CreateTime);
		} catch (Exception e) {
			logger.error("时间转换失败：" + new Date());
		}
		commodity.setCategory(category);
		commodity.setCommodityDesc(desc);
		
		// 判断新增还是更新
		if (id != null && !"".equals(id)) {
			commodityBusiness.updateCommodity(commodity, picid);
		} else {
			commodityBusiness.addCommodity(commodity, picid);
		}

		// 跳转页面
		Map<String, Object> map = new HashMap<String, Object>();
		String keyword = request.getParameter("keyword");
		String minIntegral= request.getParameter("minIntegral");
		String maxIntegral= request.getParameter("maxIntegral");
		if (keyword != null && !"".equals(keyword.trim())) {
			keyword = "%" + keyword + "%";
			map.put("keyword", keyword);
		}
		if (minIntegral != null && !"".equals(minIntegral.trim())) {
			map.put("minIntegral", minIntegral);
		}
		if (maxIntegral != null && !"".equals(maxIntegral.trim())) {
			map.put("maxIntegral", maxIntegral);
		}
		List<Commodity>list = commodityBusiness.queryCommodity(map, page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		Map<String, Object> catemap = new HashMap<String, Object>();
		List<CommodityCategory> typeList = commodityBusiness.queryCommodityCategory(catemap);
		model.addAttribute("typeList", typeList);
		model.addAttribute("category", category);
		return "manager/adminCommodityList";
	}
	
}
