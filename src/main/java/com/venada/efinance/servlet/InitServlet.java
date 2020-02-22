package com.venada.efinance.servlet;


import com.venada.efinance.business.HolidaysBussiness;
import com.venada.efinance.pojo.Holidays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 初始化servlet
 * 
 * @author hepei
 * @date 2014-08-29
 */
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(InitServlet.class);
    private HolidaysBussiness holidaysBussiness;

	public void init() throws ServletException {
		// 获取web.xml中配置的参数
		TokenThread.appid = getInitParameter("appid");
		TokenThread.appsecret = getInitParameter("appsecret");

		log.info("weixin api appid:{}", TokenThread.appid);
		log.info("weixin api appsecret:{}", TokenThread.appsecret);

		// 未配置appid、appsecret时给出提示
		if ("".equals(TokenThread.appid) || "".equals(TokenThread.appsecret)) {
			log.error("appid and appsecret configuration error, please check carefully.");
		} else {
			// 启动定时获取access_token的线程
			new Thread(new TokenThread()).start();
		}

        // 获取WebApplicationContext
        log.info("------------------------begin get  WebApplicationContext --------------------");
        ServletContext servletContext = getServletContext();
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        holidaysBussiness = (HolidaysBussiness) wac.getBean("holidaysBusiness");

        //加载全年节假日信息
        log.info("------------------------begin loading holidays --------------------");
        List<Holidays> holidaysList = holidaysBussiness.getAllHolidays();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for(Holidays h : holidaysList){
            servletContext.setAttribute(sdf.format(h.getDate()),h);
        }
        log.info("------------------------loading holidays finished--------------------");

	}
}