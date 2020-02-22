package com.venada.efinance.business.impl;

import com.venada.efinance.business.HolidaysBussiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.Holidays;
import com.venada.efinance.service.HolidaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangwenyun on 14/11/28.
 */
@Service
public class HolidaysBussinessImpl implements HolidaysBussiness {
    @Autowired
    private HolidaysService holidaysService;
    @Override
    public List<Holidays> getAllHolidays() throws BusinessException {
        return holidaysService.findObjects("getAllHolidays",null);
    }

    @Override
    public void insertHolidays(Holidays holidays) throws BusinessException {
        holidaysService.saveObject("insertHolidays",holidays);
    }
}
