package com.venada.efinance.business.impl;

import com.venada.efinance.business.NewsCategoryBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.NewsCategory;
import com.venada.efinance.service.NewsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NewsCategoryBusinessImpl implements NewsCategoryBusiness {
	@Autowired
	private NewsCategoryService newsCategoryService;
	@Override
	public List<NewsCategory> listAllNewsCategory() throws BusinessException {
		return newsCategoryService.findObjects("listAllNewsCategory", null);
	}

}
