package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.Holidays;

import java.util.List;

/**
 * Created by zhangwenyun on 14/11/28.
 */
public interface HolidaysBussiness  {
    public List<Holidays> getAllHolidays() throws BusinessException;
    public void insertHolidays(Holidays holidays) throws BusinessException;
}
