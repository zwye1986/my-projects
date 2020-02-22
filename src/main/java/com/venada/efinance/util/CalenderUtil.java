package com.venada.efinance.util;

import com.venada.efinance.pojo.DayList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class CalenderUtil {
	private GregorianCalendar gregorianCalendar = new GregorianCalendar();
	private   String[] stringWeek = new String[] { "SUN", "MON", "TUE", "WED",
			"THU", "FRI", "SAT" };
    

	public static void main(String args[]) {
		CalenderUtil myCalender = new CalenderUtil();
		List<DayList> daylist=myCalender.setDate("2013-10-01");
	    for(DayList d :daylist){
	    	 System.out.println(d.getDay());
	    }
	}

	public List<DayList> setDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		List<DayList> daylist=new ArrayList<DayList>();
		String[] strSysTime = new String[6];
		try {
			Date d = sdf.parse(date);
			strSysTime = (d + "").split(" ");
			daylist=getDay(getMonthDays(d.getYear(),d.getMonth()), getMonthDays(d.getYear(), d.getMonth()+1),
					weekStrat(strSysTime[0]), -1,Integer.valueOf(d.getMonth()));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return daylist;
	}
	

	public List<DayList> getDay(int lastMonDays, int monthDays, int startWeek, int day,int month) {
		// 设置日期颜色并打印
		int j=1;
		List<DayList> daylist=new ArrayList<DayList>();
		for (int d = 0; d < startWeek; d++) {
			DayList dl=new DayList();
			dl.setDay((lastMonDays - startWeek) + d + 1 + "");
			dl.setId(j++);
			dl.setMonth(month);
			dl.setNum("0");
			daylist.add(dl);
		}
		for (int d = startWeek; d < startWeek + monthDays; d++) {
			DayList dl=new DayList();
			dl.setDay(d - startWeek + 1 + "");
			dl.setId(j++);
			dl.setMonth(month+1);
			dl.setNum("0");
			daylist.add(dl);
		}
		for (int d = monthDays + startWeek; d <42; d++) {
			DayList dl=new DayList();
			dl.setDay(d - (monthDays + startWeek) + 1 + "");
			dl.setId(j++);
			dl.setMonth(month+2);
			dl.setNum("0");
			daylist.add(dl);
		}
		return daylist;
	}

	
	public int getMonthDays(int year, int month) {
		// 返回year年month月的天数
		switch (month) {
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		case 2:
			if (gregorianCalendar.isLeapYear(year)) {
				return 29;
			} else {
				return 28;
			}
		default:
			return 31;
		}
	}

	public int weekStrat(String strWeek) {
		// 返回字符串strWeek与星期中的第几天匹配，SUN为第一天
		int strat = 0;
		for (int i = 0; i < 7; i++) {
			if (strWeek.equalsIgnoreCase(stringWeek[i])) {
				strat = i;
				break;
			}
		}
		return strat;
	}
}
