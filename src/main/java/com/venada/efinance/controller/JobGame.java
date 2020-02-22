package com.venada.efinance.controller;

import com.venada.efinance.business.GameBusiness;
import com.venada.efinance.pojo.Game;
import com.venada.efinance.pojo.GameRank;
import com.venada.efinance.pojo.GameRecommend;
import com.venada.efinance.pojo.GameType;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JobGame extends QuartzJobBean {
	/** Logger */
	private static final Logger logger = LoggerFactory.getLogger(JobGame.class);

	public static void main(String args[]) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.setValidating(false);
		context.load("classpath*:applicationContext.xml");
		context.refresh();
		GameBusiness gameBusiness = (GameBusiness) context.getBean("gameBusiness");
		
		
		doParseActivity();
		
	}
	
	public static void doParseActivity(){
		String flashPath = "http://www.wowpower.cn/activity.html";
		String flashStr = downloadPage(flashPath);
		Pattern flashPattern = Pattern
				.compile("<div class=\"content_rc\">\\s*<div class=\"content_rcl\">\\s*<p .*\\s*.*<img.*src.*=\"(.*)?\".*\\s*.*\\s*.*\\s*<h4>(.*)</h4>\\s*<span>(.*)</span>");
		Matcher flashMatcher = flashPattern.matcher(flashStr);
		
		String str1 = "";
		String str2 = "";
		String str3 = "";
		
		
		while (flashMatcher.find()) {
			
			str1 = flashMatcher.group(1);
			str2 = flashMatcher.group(2);
			str3 = flashMatcher.group(3);
			System.out.print("======111=="+str1+"=====22=="+str2+"=====33==="+str3);
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
				sbf.append(line).append("\n");
			}
			br.close();
		} catch (Exception e) {
			logger.error("爬取" + path + "页面失败：" + e.getMessage());
		}
		return sbf.toString();
	}

	public static void download(String urlString, String filename,
			String savePath) throws Exception {
		// 构造URL
		URL url = new URL(urlString);
		// 打开连接
		URLConnection con = url.openConnection();
		// 设置请求超时为5s
		con.setConnectTimeout(5 * 1000);
		// 输入流
		InputStream is = null;
		try {
			is = con.getInputStream();
		} catch (Exception e) {
			logger.error("图片文件不存在：" + urlString);
			return;
		}
		// 1K的数据缓冲
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 输出的文件流
		File sf = new File(savePath);
		if (!sf.exists()) {
			sf.mkdirs();
		}
		
		File picFile = new File(sf.getPath() + "/" + filename);
		if(!picFile.exists()){
			OutputStream os = new FileOutputStream(sf.getPath() + "/" + filename);
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
			// 完毕，关闭所有链接
			os.close();
			is.close();
		}
		
	}

	/**
	 * 业务逻辑处理全部游戏的新增
	 */
	public static void doFlashGameInsert(GameBusiness gameBusiness) {
	//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String flashPath = "http://www.4399.com/flash_fl/2_1.htm";
		String flashStr = downloadPage(flashPath);
		Pattern flashPattern = Pattern
				.compile("最新动作小游戏(.*\\s*)*?<div class=\"clear\"></div>");
		Matcher flashMatcher = flashPattern.matcher(flashStr);
		
		String gameStr = "";
		
		while (flashMatcher.find()) {
			
			gameStr = flashMatcher.group();
			System.out.print("========"+gameStr);
		}

//		for (int i = 1; i <= page; i++) {
//			flashPath = "http://www.5dplay.net/game/all.html?class=0&sclass=0&order=2&page="
//					+ i;
//			flashStr = downloadPage(flashPath);
//
//			// 后去截取后的内容
//			Pattern flashSubContentPattern = Pattern
//					.compile("尾页</a></div><ul class=\"game_list\">.*?</ul>");
//			Matcher flashSubContentMatcher = flashSubContentPattern
//					.matcher(flashStr);
//			while (flashSubContentMatcher.find()) {
//				String subContent = flashSubContentMatcher.group();
//				// 去游戏信息表达式
//				Pattern flashGamePattern = Pattern
//						.compile("<img src=\"(.*?)\" width=\"\\d*?\" height=\"\\d*?\" alt=\"(.*?)\"/></a><a id=\"a_title\" href=\"(.*?)\"");
//				Matcher flashGameMatcher = flashGamePattern.matcher(subContent);
//				while (flashGameMatcher.find()) {
//					String detailUrl = "http://www.5dplay.net"
//							+ flashGameMatcher.group(3);
//					String detailGameStr = downloadPage(detailUrl);
//					Pattern flashGameDetailPattern = Pattern
//							.compile("<div class=\"site_nav\">\\s*<a target=\"_blank\" href=\".*?\">5Dplay小游戏</a> > <a target=\"_blank\" href=\".*?\">(.*?)</a> >\\s*</div>\\s*<div class=\"head_line\">\\s*<span class=\"head_title_icon\"></span>\\s*<div class=\"head_content\">\\s*<a id=\"link\" href=\"(.*?)\"><h2>.*?</h2></a>\\s*<a href=\".*?\" id=\".*?\"><a target=\".*?\" title=\".*?\" href=\".*?\">.*?</a></a>\\s*</div>\\s*</div>\\s*<div id=\"gameinfo_info\">\\s*<div id='user'><a target=\".*?\" title=\".*?\" href=\".*?\"><img src='.*?' alt='.*?' id='.*?'/></a><span id='uname'><a target=\"_blank\" title=\".*?\" href=\".*?\">.*?</a></span></div>\\s*<div id='info'>\\s*<span id='.*?'></span>\\s*<div id='info_txt'>(.*?)</div>\\s*</div>");
//					Matcher flashGameDetailMatcher = flashGameDetailPattern
//							.matcher(detailGameStr);
//					while (flashGameDetailMatcher.find()) {
//						Game game = new Game();
//						game.setId(UUID.randomUUID().toString());
//						game.setGameName(flashGameMatcher.group(2));
//						GameType gameType = new GameType();
//						gameType.setId(UUID.randomUUID().toString());
//						gameType.setTypeName(flashGameDetailMatcher.group(1));
//						Date CreateTime = null;
//						try {
//							CreateTime = sdf.parse(sdf.format(new Date()));
//							gameType.setCreateTime(CreateTime);
//						} catch (Exception e) {
//							logger.error("时间转换失败：" + new Date());
//						}
//						gameType.setOrderBy(999L);
//						gameType.setCategory(Game.FLASH_GAME);
//						// 获取类别ID
//						String gameTypeId = gameBusiness.addGameType(gameType);
//						game.setCategory(Game.FLASH_GAME);
//						game.setType(gameTypeId);
//						game.setCreateTime(CreateTime);
//						game.setGameDescrip(flashGameDetailMatcher.group(3));
//						game.setGameUrl("http://www.5dplay.net"
//								+ flashGameDetailMatcher.group(2));
//						game.setOrderBy(999L);
//						game.setPlayCounts(0);
//
//						String picName = "";
//						if (flashGameMatcher.group(1) != null
//								&& !"".equals(flashGameMatcher.group(1))) {
//							picName = flashGameMatcher.group(1)
//									.substring(
//											flashGameMatcher.group(1)
//													.lastIndexOf("/") + 1);
//						}
//						game.setGamePicUrl(picName);
//
//						try {
//							/**
//							 * 获取图片保存路径
//							 */
//							ClassPathResource cr = new ClassPathResource(
//									"path.properties");
//							Properties pros = new Properties();
//							try {
//								pros.load(cr.getInputStream());
//							} catch (IOException ex) {
//								logger.error("资源文件不存在：path.properties");
//							}
//							/**
//							 * 获取图片保存路径结束
//							 */
//
//							download(flashGameMatcher.group(1), picName,
//									pros.getProperty("gamePic") + "/gamePic");
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//
//						gameBusiness.addGameByJob(game);
//
//					}
//
//				}
//			}
//
//		}
	}

	/**
	 * 热门小游戏的新增
	 */
	public static void doHotGameInsert(GameBusiness gameBusiness) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String flashPath = "http://www.5dplay.net/";
		String flashStr = downloadPage(flashPath);

		Pattern flashPattern = Pattern
				.compile("最热游戏</h3></a>\\s*<ul class=\"game_list\">.*?</ul>");
		Matcher flashMatcher = flashPattern.matcher(flashStr);
		while (flashMatcher.find()) {
			String subContent = flashMatcher.group();
			// 去游戏信息表达式
			Pattern flashGamePattern = Pattern
					.compile("<li><span class=\".*?\" title=\".*?\">.*?</span><a id=\".*?\" href=\"(.*?)\" target=\".*?\" title=\"(.*?)\"><img src=\"(.*?)\" width=\".*?\" height=\".*?\" alt=\".*?\"/>.*?</li>");
			Matcher flashGameMatcher = flashGamePattern.matcher(subContent);
			while (flashGameMatcher.find()) {
				String detailUrl = "http://www.5dplay.net"
						+ flashGameMatcher.group(1);
				String detailGameStr = downloadPage(detailUrl);

				Pattern flashGameDetailPattern = Pattern
						.compile("<div class=\"site_nav\">\\s*<a target=\"_blank\" href=\".*?\">5Dplay小游戏</a> > <a target=\"_blank\" href=\".*?\">(.*?)</a> >\\s*</div>\\s*<div class=\"head_line\">\\s*<span class=\"head_title_icon\"></span>\\s*<div class=\"head_content\">\\s*<a id=\"link\" href=\"(.*?)\"><h2>.*?</h2></a>\\s*<a href=\".*?\" id=\".*?\"><a target=\".*?\" title=\".*?\" href=\".*?\">.*?</a></a>\\s*</div>\\s*</div>\\s*<div id=\"gameinfo_info\">\\s*<div id='user'><a target=\".*?\" title=\".*?\" href=\".*?\"><img src='.*?' alt='.*?' id='.*?'/></a><span id='uname'><a target=\"_blank\" title=\".*?\" href=\".*?\">.*?</a></span></div>\\s*<div id='info'>\\s*<span id='.*?'></span>\\s*<div id='info_txt'>(.*?)</div>\\s*</div>");
				Matcher flashGameDetailMatcher = flashGameDetailPattern
						.matcher(detailGameStr);
				while (flashGameDetailMatcher.find()) {
					GameRank gameRank = new GameRank();
					gameRank.setId(UUID.randomUUID().toString());
					gameRank.setGameName(flashGameMatcher.group(2));
					GameType gameType = new GameType();
					gameType.setId(UUID.randomUUID().toString());
					gameType.setTypeName(flashGameDetailMatcher.group(1));
					Date CreateTime = null;
					try {
						CreateTime = sdf.parse(sdf.format(new Date()));
						gameType.setCreateTime(CreateTime);
					} catch (Exception e) {
						logger.error("时间转换失败：" + new Date());
					}
					gameType.setOrderBy(999L);
					gameType.setCategory(Game.FLASH_GAME);
					// 获取类别ID
					String gameTypeId = gameBusiness.addGameType(gameType);
					gameRank.setCategory(Game.FLASH_GAME);
					gameRank.setType(gameTypeId);
					gameRank.setCreateTime(CreateTime);
					gameRank.setGameDescrip(flashGameDetailMatcher.group(3));
					gameRank.setGameUrl("http://www.5dplay.net"
							+ flashGameDetailMatcher.group(2));
					gameRank.setOrderBy(999L);
					gameRank.setPlayCounts(0);

					String picName = "";
					if (flashGameMatcher.group(3) != null
							&& !"".equals(flashGameMatcher.group(3))) {
						picName = flashGameMatcher.group(3).substring(
								flashGameMatcher.group(3).lastIndexOf("/") + 1);
					}
					gameRank.setGamePicUrl(picName);

					try {
						/**
						 * 获取图片保存路径
						 */
						ClassPathResource cr = new ClassPathResource(
								"path.properties");
						Properties pros = new Properties();
						try {
							pros.load(cr.getInputStream());
						} catch (IOException ex) {
							logger.error("资源文件不存在：path.properties");
						}
						/**
						 * 获取图片保存路径结束
						 */

						download(flashGameMatcher.group(3), picName,
								pros.getProperty("gamePic") + "/gamePic");
					} catch (Exception e) {
						e.printStackTrace();
					}

					gameBusiness.addGameRankByJob(gameRank);
				}

			}
		}

	}

	/**
	 * 热门网页游戏的新增同事插热门游戏到全部游戏表中
	 */
	public static void doHotWebGameInsert(GameBusiness gameBusiness) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String webPath = "http://www.37wan.com/";
		String webStr = downloadPage(webPath);
		Pattern webPattern = Pattern
				.compile("热门游戏</div>.*?<div class=\"row\">");
		Matcher webMatcher = webPattern.matcher(webStr);
		while (webMatcher.find()) {
			String subContent = webMatcher.group();
			Pattern webGamePattern = Pattern
					.compile("<div class=\"game_recommend_small\">\\s*?<div class=\"game_recommend_small_img\"><a href=\"(.*?)\" target=\"_blank\"><img src=\"(.*?)\" alt=\"(.*?)\"/></a></div>\\s*<div class=\"game_recommend_small_bg\">\\s*<div class=\"game_recommend_small_info\">\\s*<a href=\".*?\" target=\"_blank\" class=\".*?\"><span style=\".*?\">.*?</span></a><br/>\\s*<font color=\".*?\">类型：(.*?)</font><br/>\\s*<a href=\".*?\" target=\".*?\">.*?</a>.*?</div>");
			Matcher webGameMatcher = webGamePattern.matcher(subContent);
			while (webGameMatcher.find()) {
			
				GameRank gameRank = new GameRank();
				gameRank.setId(UUID.randomUUID().toString());
				gameRank.setGameName(webGameMatcher.group(3));
				gameRank.setCategory(Game.WEB_GAME);
				GameType gameType = new GameType();
				gameType.setId(UUID.randomUUID().toString());
				gameType.setTypeName(webGameMatcher.group(4));
				Date CreateTime = null;
				try {
					CreateTime = sdf.parse(sdf.format(new Date()));
					gameType.setCreateTime(CreateTime);
				} catch (Exception e) {
					logger.error("时间转换失败：" + new Date());
				}
				gameType.setOrderBy(999L);
				gameType.setCategory(Game.WEB_GAME);
				// 获取类别ID
				String gameTypeId = gameBusiness.addGameType(gameType);
				gameRank.setType(gameTypeId);
				gameRank.setCreateTime(CreateTime);
				gameRank.setGameDescrip("");
				gameRank.setGameUrl(webGameMatcher.group(1));
				gameRank.setOrderBy(999L);
				gameRank.setPlayCounts(0);
				String picName = "";
				if (webGameMatcher.group(2) != null
						&& !"".equals(webGameMatcher.group(2))) {
					picName = webGameMatcher.group(2).substring(
							webGameMatcher.group(2).lastIndexOf("/") + 1);
				}
				gameRank.setGamePicUrl(picName);
				

				try {
					/**
					 * 获取图片保存路径
					 */
					ClassPathResource cr = new ClassPathResource(
							"path.properties");
					Properties pros = new Properties();
					try {
						pros.load(cr.getInputStream());
					} catch (IOException ex) {
						logger.error("资源文件不存在：path.properties");
					}
					/**
					 * 获取图片保存路径结束
					 */

					download("http://www.37wan.com"+webGameMatcher.group(2), picName,
							pros.getProperty("gamePic") + "/gamePic");
				} catch (Exception e) {
					e.printStackTrace();
				}

				gameBusiness.addGameRankByJob(gameRank);
				Game game = new Game();
				game.setId(UUID.randomUUID().toString());
				game.setGameName(webGameMatcher.group(3));
				game.setCategory(Game.WEB_GAME);
				CreateTime = null;
				try {
					CreateTime = sdf.parse(sdf.format(new Date()));
					game.setCreateTime(CreateTime);
				} catch (Exception e) {
					logger.error("时间转换失败：" + new Date());
				}
				game.setType(gameRank.getType());
				game.setCreateTime(CreateTime);
				game.setGameDescrip("");
				game.setGameUrl(webGameMatcher.group(1));
				game.setOrderBy(999L);
				game.setPlayCounts(0);
				game.setGamePicUrl(picName);
				gameBusiness.addGameByJob(game);
			}
		}
			
			
	}
	
	/**
	 * 网页游戏推荐录入
	 */
	public static void doRecommendGameInsert(GameBusiness gameBusiness) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String webPath = "http://www.37wan.com/game_list.php";
		String webStr = downloadPage(webPath);
		Pattern webPattern = Pattern
				.compile("平台热推</div>\\s*<div class=\".*?\"></div>\\s*?</div>\\s*?<div class=\".*?\">.*?<div class=\"game_center_spet\">");
		Matcher webMatcher = webPattern.matcher(webStr);
		while (webMatcher.find()) {
			String subContent = webMatcher.group();
			Pattern webGamePattern = Pattern
					.compile("<div class=\"game_recommend_small\">\\s*?<div class=\"game_recommend_small_img\"><a href=\"(.*?)\" target=\"_blank\"><img src=\"(.*?)\" alt=\"(.*?)\"/></a></div>\\s*<div class=\"game_recommend_small_bg\">\\s*<div class=\"game_recommend_small_info\">\\s*<a href=\".*?\" target=\"_blank\" class=\".*?\"><span style=\".*?\">.*?</span></a><br/>\\s*<font color=\".*?\">类型：(.*?)</font><br/>\\s*<a href=\".*?\" target=\".*?\">.*?</a>.*?</div>");
			Matcher webGameMatcher = webGamePattern.matcher(subContent);
			while (webGameMatcher.find()) {
			
				GameRecommend gameRecommend = new GameRecommend();
				gameRecommend.setId(UUID.randomUUID().toString());
				gameRecommend.setGameName(webGameMatcher.group(3));
				gameRecommend.setCategory(Game.WEB_GAME);
				GameType gameType = new GameType();
				gameType.setId(UUID.randomUUID().toString());
				gameType.setTypeName(webGameMatcher.group(4));
				Date CreateTime = null;
				try {
					CreateTime = sdf.parse(sdf.format(new Date()));
					gameType.setCreateTime(CreateTime);
				} catch (Exception e) {
					logger.error("时间转换失败：" + new Date());
				}
				gameType.setOrderBy(999L);
				gameType.setCategory(Game.WEB_GAME);
				// 获取类别ID
				String gameTypeId = gameBusiness.addGameType(gameType);
				gameRecommend.setType(gameTypeId);
				gameRecommend.setCreateTime(CreateTime);
				gameRecommend.setGameDescrip("");
				gameRecommend.setGameUrl(webGameMatcher.group(1));
				gameRecommend.setOrderBy(999L);
				gameRecommend.setPlayCounts(0);
				String picName = "";
				if (webGameMatcher.group(2) != null
						&& !"".equals(webGameMatcher.group(2))) {
					picName = webGameMatcher.group(2).substring(
							webGameMatcher.group(2).lastIndexOf("/") + 1);
				}
				gameRecommend.setGamePicUrl(picName);
				

				try {
					/**
					 * 获取图片保存路径
					 */
					ClassPathResource cr = new ClassPathResource(
							"path.properties");
					Properties pros = new Properties();
					try {
						pros.load(cr.getInputStream());
					} catch (IOException ex) {
						logger.error("资源文件不存在：path.properties");
					}
					/**
					 * 获取图片保存路径结束
					 */

					download("http://www.37wan.com"+webGameMatcher.group(2), picName,
							pros.getProperty("gamePic") + "/gamePic");
				} catch (Exception e) {
					e.printStackTrace();
				}
				gameBusiness.addGameRecommendByJob(gameRecommend);
				Game game = new Game();
				game.setId(UUID.randomUUID().toString());
				game.setGameName(webGameMatcher.group(3));
				game.setCategory(Game.WEB_GAME);
				CreateTime = null;
				try {
					CreateTime = sdf.parse(sdf.format(new Date()));
					game.setCreateTime(CreateTime);
				} catch (Exception e) {
					logger.error("时间转换失败：" + new Date());
				}
				game.setType(gameRecommend.getType());
				game.setCreateTime(CreateTime);
				game.setGameDescrip("");
				game.setGameUrl(webGameMatcher.group(1));
				game.setOrderBy(999L);
				game.setPlayCounts(0);
				game.setGamePicUrl(picName);
				gameBusiness.addGameByJob(game);
			}
		}
	}

	static int searchIndexFlag =0;
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		if(searchIndexFlag>0)return ;//保证同一时刻只有一个定时器运行,通过这种方式保证每次定时时间到时，只执行一个线程
		searchIndexFlag = 1;//锁定
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.setValidating(false);
		context.load("classpath*:applicationContext.xml");
		context.refresh();
	//	GameBusiness gameBusiness = context.getBean(GameBusiness.class);
		
        
	

	}

}
