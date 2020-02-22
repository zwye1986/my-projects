package com.venada.efinance.timingTasks;

import com.venada.efinance.business.HolidaysBussiness;
import com.venada.efinance.pojo.Holidays;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangwenyun on 14/12/11.
 */
public class GenerateHoliday {
    public static void main(String[] args){

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        HolidaysBussiness holidaysBussiness = (HolidaysBussiness) ctx.getBean("holidaysBusiness");

        String firstDayStr = "2015-01-01";
        String lastDayStr = "2015-12-31";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date firstDay = sdf.parse(firstDayStr);
            Date lastDay = sdf.parse(lastDayStr);
            try {
                while(com.venada.efinance.util.DateUtils.timeDifference(lastDay,firstDay).get("day") >= 0){
                    Holidays h = new Holidays();
                    h.setDate(firstDay);
                    h.setDay_of_week(Integer.parseInt(com.venada.efinance.util.DateUtils.getDayOfWeekCn(sdf.format(firstDay))));
                    if(h.getDay_of_week() == 6 || h.getDay_of_week() == 7){
                        h.setDes("休息");
                        h.setIs_holiday(1);
                    }else{
                        h.setDes("上班");
                        h.setIs_holiday(0);
                    }
                    holidaysBussiness.insertHolidays(h);
                    firstDay = DateUtils.addDays(firstDay,1);

                }
            } catch (IllegalArgumentException e) {
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
