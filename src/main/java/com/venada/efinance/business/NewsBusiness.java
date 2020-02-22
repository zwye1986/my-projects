package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.News;

import java.util.List;

public interface NewsBusiness {
	public void addNews(News news) throws BusinessException;
	public List<News> listAllNews(PaginationMore page) throws BusinessException;
	public List<News> listAllNews() throws BusinessException;
	public void updateNews(News news) throws BusinessException;
	public void delNews(int id) throws BusinessException;
	public News getNews(int id) throws  BusinessException;
	public List<News> listOtherNews(int id) throws BusinessException;
}
