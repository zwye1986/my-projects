package com.venada.efinance.pojo;

import java.util.Date;

/**
 * Created by zhangwenyun on 14/11/28.
 */
public class Holidays {
    private Date date;
    private Integer day_of_week;
    private String des;
    /**
     * 0:工作日
     * 1:节假日
     */
    private Integer is_holiday;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDay_of_week() {
        return day_of_week;
    }

    public void setDay_of_week(Integer day_of_week) {
        this.day_of_week = day_of_week;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Integer getIs_holiday() {
        return is_holiday;
    }

    public void setIs_holiday(Integer is_holiday) {
        this.is_holiday = is_holiday;
    }

    @Override
    public String toString() {
        return "Holidays{" +
                "date=" + date +
                ", day_of_week=" + day_of_week +
                ", des='" + des + '\'' +
                ", is_holiday=" + is_holiday +
                '}';
    }
}
