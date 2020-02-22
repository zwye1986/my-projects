package com.venada.efinance.controller;

import com.venada.efinance.business.GameBusiness;
import com.venada.efinance.pojo.Game;
import com.venada.efinance.pojo.GameType;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class ExpireGameTask extends QuartzJobBean{
	/** Logger */
	private static final Logger logger = LoggerFactory.getLogger(ExpireGameTask.class);
	
	@Autowired
	private GameBusiness gameBusiness;

	/**
	 * 业务逻辑处理
	 */
	public void doGameInsert() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String str = downloadPage("http://www.37wan.com/game_list.php");
//		Pattern p = Pattern.compile("div class=\"game_pinyin_frame gt_(\\w*)\" id=\"\\w*\">\\s*.*\\s*.*</div>");
//		Matcher m = p.matcher(str);		
//		while (m.find()) {
//
//			String str_sub = m.group();
//			Pattern p_sub = Pattern
//					.compile("<div class=\"game_pinyin_name\"><a href=\"(.*)\" target=\"_blank\" rel=\"\">(.*)</a></div>");
//			Matcher m_sub = p_sub.matcher(str_sub);
//			while (m_sub.find()) {
//				System.out.println("网页游戏信息:" + m_sub.group(1) + "="
//						+ m_sub.group(2) + "=" + m.group(1));
//			}
//		}
		String flashPath = "http://www.5dplay.net/game/all.html?class=0&sclass=0&order=2";
		String flashStr = downloadPage(flashPath);
		Pattern flashPattern = Pattern.compile("下一页</a><a href=\".*?.page=(\\d+)\"\\s*?>.*?</a></div><ul class=\"game_list\">");
		Matcher flashMatcher = flashPattern.matcher(flashStr);	
		int page = 0;
		while (flashMatcher.find()){
			try{
				page = Integer.parseInt(flashMatcher.group(1));
			}catch(Exception e){
				logger.error("数字转换错误，爬虫页面为："+flashPath+"\n获得的数字为"+e.getMessage());
			}
			System.out.println();
		}
		
		for(int i = 1;i<=page;i++){
			flashPath = "http://www.5dplay.net/game/all.html?class=0&sclass=0&order=2&page="+i;
			flashStr = downloadPage(flashPath);
			
			//后去截取后的内容
			Pattern flashSubContentPattern = Pattern.compile("尾页</a></div><ul class=\"game_list\">.*?</ul>");
			Matcher flashSubContentMatcher = flashSubContentPattern.matcher(flashStr);
			while(flashSubContentMatcher.find()){
				String subContent = flashSubContentMatcher.group();
				//去游戏信息表达式
				Pattern flashGamePattern = Pattern.compile("<img src=\"(.*?)\" width=\"\\d*?\" height=\"\\d*?\" alt=\"(.*?)\"/></a><a id=\"a_title\" href=\"(.*?)\"");
				Matcher flashGameMatcher = flashGamePattern.matcher(subContent);
				while (flashGameMatcher.find()){
					String detailUrl = "http://www.5dplay.net"+flashGameMatcher.group(3);
					String detailGameStr = downloadPage(detailUrl);
					Pattern flashGameDetailPattern = Pattern.compile("<div class=\"site_nav\">\\s*<a target=\"_blank\" href=\".*?\">5Dplay小游戏</a> > <a target=\"_blank\" href=\".*?\">(.*?)</a> >\\s*</div>\\s*<div class=\"head_line\">\\s*<span class=\"head_title_icon\"></span>\\s*<div class=\"head_content\">\\s*<a id=\"link\" href=\"(.*?)\"><h2>.*?</h2></a>\\s*<a href=\".*?\" id=\".*?\"><a target=\".*?\" title=\".*?\" href=\".*?\">.*?</a></a>\\s*</div>\\s*</div>\\s*<div id=\"gameinfo_info\">\\s*<div id='user'><a target=\".*?\" title=\".*?\" href=\".*?\"><img src='.*?' alt='.*?' id='.*?'/></a><span id='uname'><a target=\"_blank\" title=\".*?\" href=\".*?\">剑jian</a></span></div>\\s*<div id='info'>\\s*<span id='.*?'></span>\\s*<div id='info_txt'>(.*?)</div>\\s*</div>");
					Matcher flashGameDetailMatcher = flashGameDetailPattern.matcher(detailGameStr);
					while (flashGameDetailMatcher.find()){
						Game game = new Game();
						game.setId(UUID.randomUUID().toString());
						GameType gameType = new GameType();
						gameType.setId(UUID.randomUUID().toString());
						gameType.setTypeName(flashGameDetailMatcher.group(1));
						try{
						gameType.setCreateTime(sdf.parse(sdf.format(new Date())));
						}catch(Exception e){
							logger.error("时间转换失败：" + new Date());
						}
						gameType.setOrderBy(999L);
					//	gameBusiness.addGameType(gameType);
						GenericXmlApplicationContext context = new GenericXmlApplicationContext();
						context.setValidating(false);
						context.load("classpath*:applicationContext.xml");
						context.refresh();
						GameBusiness gameBusiness = context.getBean(GameBusiness.class);
						System.out.println("======="+gameBusiness);
					//    System.out.println("============"+flashGameDetailMatcher.group(1));
					}
					
				}
			}
			
		}		
	}

	public static String downloadPage(String path) {
		String CHARSET = "utf-8";
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(path);
		StringBuffer sbf = new StringBuffer();
		try {
			httpClient.executeMethod(getMethod);
			InputStream ins = getMethod.getResponseBodyAsStream();
			// 按指定的字符集构建文件流
			BufferedReader br = new BufferedReader(new InputStreamReader(ins,
					CHARSET));
			String line = null;
			while ((line = br.readLine()) != null) {
				sbf.append(line);
			}
			br.close();
		} catch (Exception e) {
			logger.error("爬取" + path + "页面失败：" + e.getMessage());
		}
		return sbf.toString();
	}

	

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		this.doGameInsert();
	}

}
