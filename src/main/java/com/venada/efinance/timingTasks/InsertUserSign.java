package com.venada.efinance.timingTasks;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created by zhangwenyun on 15/2/8.
 */
public class InsertUserSign {
    public static void main(String[] args){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String beginStr = "2015-04-23 09:30:09";
        try {
            Date beginDay = sdf.parse(beginStr);
            Date endDay = sdf.parse("2015-05-08 20:09:23");
            Random r = new Random();
            int c = 9;
            while(endDay.getTime() - beginDay.getTime() >= 0){
                beginDay.setHours(r.nextInt(23) + 1);
                beginDay.setMinutes(r.nextInt(59));
                beginDay.setSeconds(r.nextInt(59));
                String date = sdf.format(beginDay);
                String date1 = sdf1.format(beginDay);
                String insertSQL = "insert into ec_user_sign values ('"+UUID.randomUUID()+"','18112626489','"+date+"',160.00,"+c+",'您今天已经签到成功!','"+date1+"');";
                System.out.println(insertSQL);
                beginDay = DateUtils.addDays(beginDay,1);
                c++;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
//SignIn
//        DateFormatUtils
//        DateUtils.
    }
}
