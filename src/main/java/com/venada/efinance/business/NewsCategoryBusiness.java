package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.NewsCategory;

import java.util.List;

public interface NewsCategoryBusiness {
	public List<NewsCategory> listAllNewsCategory() throws BusinessException;
}
