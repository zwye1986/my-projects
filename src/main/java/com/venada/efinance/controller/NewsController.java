package com.venada.efinance.controller;

import com.venada.efinance.business.GameBusiness;
import com.venada.efinance.business.NewsBusiness;
import com.venada.efinance.business.NewsCategoryBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.Game;
import com.venada.efinance.pojo.GamePolicy;
import com.venada.efinance.pojo.News;
import com.venada.efinance.pojo.NewsCategory;
import com.venada.efinance.util.RequestUtils;
import com.venada.efinance.util.SystemUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
public class NewsController {
	@Autowired
	private NewsBusiness newsBusiness;
	@Autowired
	private NewsCategoryBusiness newsCategoryBusiness;
	@Autowired
	private GameBusiness gameBusiness;
	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(NewsController.class);
	
	@RequestMapping("manager/newsList")
	public String newsList(News news,PaginationMore page,Model model) {
		page.setPageSize(20);
		List<News> list = newsBusiness.listAllNews(page);
		model.addAttribute("newsList", list);
		model.addAttribute("news", news);
		model.addAttribute("page", page);
		return "manager/newsList";
	}
	
	@RequestMapping("manager/addNews")
	public ModelAndView addNews() {
		ModelAndView mv = new ModelAndView();
		List<NewsCategory> newsCategoriesList = newsCategoryBusiness
				.listAllNewsCategory();
		mv.addObject("newsCategoriesList", newsCategoriesList);
		mv.setViewName("manager/addNews");
		return mv;
	}
	
	@RequestMapping("manager/dealAddNews")
	public ModelAndView dealAddNews(HttpServletRequest request,
			HttpServletResponse response) {
		String newsTitle = request.getParameter("newsTitle");
		String newsRef = request.getParameter("newsRef");
		String newsLink = request.getParameter("newsLink");
		String content1 = request.getParameter("content1");
		String author = request.getParameter("author");
		String priority = request.getParameter("priority");
		String[] types = request.getParameterValues("type");
		String type = "";
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			News news = new News();
			news.setNewsTitle(newsTitle);
			news.setNewsRef(newsRef);
			news.setNewsLink(newsLink);
			news.setContent(content1);
			news.setAuthor(author);
			news.setPriority(Integer.parseInt(priority));
			news.setAddTime(sdf.parse(sdf.format(new Date())));
			String isImp = request.getParameter("isImp");
			if(isImp != null){
				news.setIsImp(Integer.parseInt(isImp));
			}
			for (int i = 0; i<types.length;i++){
				if(i == 0){
					type = types[i];
				}else{
					type = type + ","+types[i];
				}
			}
			news.setType(type);
			String summary = SystemUtils.Html2Text(content1);
			
			if(summary.length()>147){
				news.setSummary(SystemUtils.Html2Text(content1).substring(0,147)+"...");
			}else{
				news.setSummary(summary);
			}
			
			newsBusiness.addNews(news);
		} catch (Exception e) {
			logger.error(e.getMessage());
		
		}
		return new ModelAndView("redirect:/manager/newsList");

	}
	
	@RequestMapping("manager/toUpdateNews")
	public ModelAndView toUpdateNews(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String id = request.getParameter("ids");
		News news = newsBusiness.getNews(Integer.parseInt(id));
		List<NewsCategory> newsCategoriesList = newsCategoryBusiness.listAllNewsCategory();
		mv.addObject("newsCategoriesList", newsCategoriesList);
		mv.addObject("news", news);
		
		mv.setViewName("manager/addNews");
		return mv;
	}
	
	@RequestMapping("manager/dealUpdateNews")
	public ModelAndView dealUpdateNews(HttpServletRequest request,
			HttpServletResponse response) {
		String newsTitle = request.getParameter("newsTitle");
		String newsRef = request.getParameter("newsRef");
		String newsLink = request.getParameter("newsLink");
		String author = request.getParameter("author");
		String content1 = request.getParameter("content1");
		String[] types = request.getParameterValues("type");
		String type = "";
		String newsid = request.getParameter("newsid");
		String priority = request.getParameter("priority");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			News news = new News();
			news.setNewsTitle(newsTitle);
			news.setNewsRef(newsRef);
			news.setNewsLink(newsLink);
			news.setContent(content1);
			news.setAuthor(author);
			news.setAddTime(sdf.parse(sdf.format(new Date())));
			news.setPriority(Integer.parseInt(priority));
			String isImp = request.getParameter("isImp");
			if(isImp != null){
				news.setIsImp(Integer.parseInt(isImp));
			}
			for (int i = 0; i<types.length;i++){
				if(i == 0){
					type = types[i];
				}else{
					type = type + ","+types[i];
				}
			}
			news.setType(type);
			String summary = SystemUtils.Html2Text(content1);
			
			if(summary.length()>147){
				news.setSummary(SystemUtils.Html2Text(content1).substring(0,147)+"...");
			}else{
				news.setSummary(summary);
			}
			news.setId(Integer.parseInt(newsid));
			newsBusiness.updateNews(news);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ModelAndView("redirect:/manager/newsList");
	}
	
	 @RequestMapping("manager/delNews")
		public ModelAndView delNews(HttpServletRequest request) {

		 String[] ids = RequestUtils.getStringParameters(request, "ids");
			for(String str : ids){
				newsBusiness.delNews(Integer.parseInt(str));
			}
			return new ModelAndView("redirect:/manager/newsList");
		}
	 
	 @RequestMapping(value="/{id}/showNews.html")
	 public String getNewsDetail(HttpServletRequest request,Model model,@PathVariable int id){
		 try {
			News news = newsBusiness.getNews(id);
			 model.addAttribute("news", news);
			 
			//-------
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("category", Game.FLASH_GAME);
				map.put("count", 10);
				List<Game> gameRankList = new ArrayList<Game>();
				// 查询小游戏排行榜
				gameRankList = gameBusiness.queryGameForRank(map);
				model.addAttribute("flashRank", gameRankList);

				// 排行榜上的推荐
				if (gameRankList.size() > 0) {
					model.addAttribute("bigFlashRank", gameRankList.get(0));
					Game game = new Game();
					game = gameRankList.get(0);
					if (game != null) {
						// 查询游戏策略
						GamePolicy policy = gameBusiness.getGamePolicyByGameId(game
								.getId());
						model.addAttribute("policy", policy);
						int gamePlayCounts = gameBusiness.getGamePlayCounts(game
								.getId());
						model.addAttribute("gamePlayCounts", gamePlayCounts);
					}
				}
			 List<News> otherNews = newsBusiness.listOtherNews(id);
			 model.addAttribute("otherNews", otherNews);
		} catch (BusinessException e) {
			logger.error("公告展示错误："+e.getMessage());
		}
		 return ".newsDetail";
	 }
	 
//	@RequestMapping(value="showNewsIndex")
//	public String showNewsIndex(Model model){
//		//首页公告新闻
//		List<News> news = newsBusiness.listAllNews();
//		model.addAttribute("news", news);
//		return "indexNews";
//	}

    /**
     * 新版首页加载公告
     * @param model
     * @return
     */
    @RequestMapping(value="showNewsIndex")
    public String showNewsIndex(Model model){
        //首页公告新闻
        List<News> news = newsBusiness.listAllNews();
        model.addAttribute("news", news);
        return "indexNewsv2";
    }
	
	@RequestMapping(value="/{currentPage}/showAllNews")
	public String showAllNews(PaginationMore page,Model model,@PathVariable int currentPage){
		page.setCurrentPage(currentPage);
		page.setPageSize(20);
		model.addAttribute("page", page);
		
		List<News> newsList = newsBusiness.listAllNews(page);
		model.addAttribute("newsList", newsList);
		return ".announcementDetail";
	}
	 
}
