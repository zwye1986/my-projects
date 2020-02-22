package com.venada.efinance.test;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

import com.venada.efinance.util.DateUtils;

public class TestUtils {

	public static void main(String[] args) {
		String tempCurrDay = DateUtils.getDayOfWeekCn(DateFormatUtils
				.format(org.apache.commons.lang.time.DateUtils
						.addDays(new Date(), 5), "yyyy-MM-dd"));
		System.out.println(tempCurrDay);
		System.out.println(org.apache.commons.lang.time.DateUtils
						.addDays(new Date(), 5));
	}

}
