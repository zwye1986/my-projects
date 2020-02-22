package com.venada.efinance.business.impl;

import com.venada.efinance.business.NewsBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.News;
import com.venada.efinance.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NewsBusinessIpml implements NewsBusiness {
	@Autowired
	private NewsService newsService;

	@Override
	public void addNews(News news) throws BusinessException {
		newsService.updateObject("addNews", news);
	}

	@Override
	public List<News> listAllNews(PaginationMore page) throws BusinessException {
		page.setTotalRows(getNewsCount());
		page.repaginate();
		return newsService.selectList("listAllNews", null,page);
	}

	@Override
	public void updateNews(News news) throws BusinessException {
		newsService.updateObject("updateNews", news);
	}

	@Override
	public void delNews(int id) throws BusinessException {
		newsService.updateObject("delNews", id);
	}

	@Override
	public News getNews(int id) throws BusinessException {
		return (News) newsService.getObject("getNews", id);
	}


	@Override
	public List<News> listAllNews() throws BusinessException {
		return newsService.findObjects("listAllNews", null);
	}

	private int getNewsCount() throws BusinessException {
		return (Integer) newsService.getObject("getNewsCount", null);
	}

	@Override
	public List<News> listOtherNews(int id) throws BusinessException {
		return newsService.findObjects("listOtherNews", id);
	}

}
