package com.venada.efinance.util;

import org.apache.commons.validator.routines.EmailValidator;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class SystemUtils {
	/**
	 * 随机生成验证码
	 * 
	 * @param length
	 *            要生成字符串长度
	 * @return
	 */
	
	public static final double PI = 3.1415926;
	
	public static final String randomCheckcode(String str , int length) {

		Random randGen = null;

		char[] numbersAndLetters = null;

		Object initLock = new Object();

		if (length < 1) {
			return null;
		}
		if (randGen == null) {

			synchronized (initLock) {

				if (randGen == null) {
					randGen = new Random();
					numbersAndLetters = str
							.toCharArray();
				}
			}
		}
		char[] randBuffer = new char[length];

		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(str.length())];
		}

		return new String(randBuffer);
	}

	/**
	 * 
	 * @param mobileNumber
	 *            要验证手机号码
	 * @return
	 */
	public static final boolean checkMobileNumber(String mobileNumber) {
		Pattern p = Pattern.compile("^1\\d{10}$");
		Matcher m = p.matcher(mobileNumber);
		return m.matches();
	}

	/**
	 * 生成验证码
	 * 
	 * @param code 验证码字符
	 * @param fontSize 字体大小
	 * @return
	 */
	public static final BufferedImage drawImage(String code, int fontSize) {
		
		int size = code.length();
		int width = (int) ((size + 1) * fontSize);
		int height = (int)(fontSize * 1.67);
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		Random random = new Random();
		// 填充矩形
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		// 绘制黑色边框
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width - 1, height - 1);

		// 绘制干扰线
		for (int i = 0; i < 3; i++) {
			g.setColor(new Color(random.nextInt(100), random.nextInt(100), random.nextInt(100)));
			g.drawLine(random.nextInt(width - 2), random.nextInt(height - 2), random.nextInt(width - 5), random.nextInt(height - 5));
		}
		// 绘制干扰矩形
		g.setColor(new Color(random.nextInt(100), random.nextInt(100), random.nextInt(100)));
		g.drawRect(random.nextInt(width - 2), random.nextInt(height - 2), random.nextInt(width - 5), random.nextInt(height - 5));
		
		for (int i = 0; i <30; i++) {
			g.setColor(new Color(random.nextInt(100), random.nextInt(100), random.nextInt(100)));
			g.drawString(".", random.nextInt(width - 2), random.nextInt(height - 2));
		}
		

		
		char[] chars = new char[code.length()];
		int unitWidth = (int) fontSize;
		code.getChars(0, code.length(), chars, 0);
		for (int i = 0; i < chars.length; i++) {
			// 为文字定位
			int x = (int) (i * unitWidth + random.nextInt(unitWidth * 4 / 15) + fontSize / 2);
			int y = random.nextInt(height / 3) + (int) fontSize;
			// 设置字体旋转，正负30度
			AffineTransform at = AffineTransform.getRotateInstance(((random.nextInt(60) - 30) * PI) / 180, x, y);
			g.setTransform(at);
			g.setColor(new Color(random.nextInt(180), random.nextInt(180), random.nextInt(180),random.nextInt(100)+155));
			int f = random.nextInt(4);
			Font font = null;
			switch (f) {
			case 1: {
				font = new Font("Impact", Font.PLAIN, fontSize);
				break;
			}
			case 2: {
				font = new Font("century", Font.ITALIC, fontSize);
				
				break;
			}
			case 3: {
				font = new Font("Georgia", Font.ROMAN_BASELINE, fontSize);
				break;
			}
			case 4: {
				font = new Font("Times New Roman", Font.TRUETYPE_FONT, fontSize);
				break;
			}
			default:
				font = new Font("Andale Mono", Font.HANGING_BASELINE, fontSize);
				break;
			}
			g.setFont(font);
			g.drawChars(chars, i, 1, x, y);
		}
		return image;
	}

	public static final boolean emailValidate(String email) {
		return EmailValidator.getInstance().isValid(email);
	}

	/**
	 * 检测字符串是否不为空(null,"","null")
	 * 
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s) {
		return s != null && !"".equals(s) && !"null".equals(s);
	}

	/**
	 * 检测字符串是否为空(null,"","null")
	 * 
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s) {
		return s == null || "".equals(s) || "null".equals(s);
	}

	/**
	 * 字符串转换为字符串数组
	 * 
	 * @param str
	 *            字符串
	 * @param splitRegex
	 *            分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str, String splitRegex) {
		if (isEmpty(str)) {
			return null;
		}
		return str.split(splitRegex);
	}

	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * 
	 * @param str
	 *            字符串
	 * @return
	 */
	public static String[] str2StrArray(String str) {
		return str2StrArray(str, ",\\s*");
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * 
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date) {
		return date2Str(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date) {
		if (notEmpty(date)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		} else {
			return null;
		}
	}

	/**
	 * 按照参数format的格式，日期转字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date, String format) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} else {
			return "";
		}
	}

	public static String Html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
																										// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
																									// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			textStr = htmlStr;

			/**
			 * 过滤特殊字符
			 */
			if (textStr != null) {
				Pattern p = Pattern.compile("\\s*|\t|\r|\n");
				Matcher m = p.matcher(textStr);
				textStr = m.replaceAll("");
				textStr = textStr.replaceAll("&nbsp;", "");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return textStr;// 返回文本字符串
	}

	// 模糊查询%和_转义
	public static String transfer(String keyword) {
		if (keyword.contains("%") || keyword.contains("_")) {
			keyword = keyword.replaceAll("\\\\", "\\\\\\\\")
					.replaceAll("\\%", "\\\\%").replaceAll("\\_", "\\\\_");
		}
		keyword = keyword.replaceAll("\"", "&quot").replaceAll("\'", "&quot");
		return keyword;
	}

	/**
	 * 验证用户名 （大小写英文字母、汉字、数字、下划线组成的长度3-12个字节）
	 * 
	 * @parameter string str 字符串
	 * @return boolean
	 */
	public static final boolean checkName(String nickName) {
		Pattern p = Pattern.compile("^([\u4e00-\u9fa5a-zA-Z0-9_]){1,20}$");
		Matcher m = p.matcher(nickName);
		return m.matches();
	}

	// 过滤特殊字符
	public static String StringFilter(String str) throws PatternSyntaxException {
		// 只允许字母和数字
		// String regEx = "[^a-zA-Z0-9]";
		// 清除掉所有特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	// public static void webPost(String _url,String mobileNumber,String
	// password) throws Exception{
	//
	// /**
	// * 首先要和URL下的URLConnection对话。 URLConnection可以很容易的从URL得到。比如： // Using
	// * java.net.URL and //java.net.URLConnection
	// */
	// // 一旦发送成功，用以下方法就可以得到服务器的回应：
	// OutputStreamWriter out = null;
	// InputStream l_urlStream = null;
	// BufferedReader l_reader = null;
	// URL url = new URL(_url);
	//
	// URLConnection connection = url.openConnection();
	// /**
	// * 然后把连接设为输出模式。URLConnection通常作为输入来使用，比如下载一个Web页。
	// * 通过把URLConnection设为输出，你可以把数据向你个Web页传送。下面是如何做：
	// */
	// connection.setDoOutput(true);
	// /**
	// * 最后，为了得到OutputStream，简单起见，把它约束在Writer并且放入POST信息中，例如： ...
	// */
	// out = new OutputStreamWriter( connection.getOutputStream());
	// out.write("mobileNumber="+mobileNumber+"&password="+password);
	// //post的关键所在！
	// out.flush();
	// out.close();
	// l_urlStream = connection.getInputStream();
	// // 三层包装！
	// l_reader = new BufferedReader(new InputStreamReader( l_urlStream));
	// String sCurrentLine = "";
	// String sTotalString = "";
	// while ((sCurrentLine = l_reader.readLine()) != null) {
	// sTotalString += sCurrentLine + "/r/n";
	//
	// }
	// System.out.println(sTotalString);
	// l_reader.close();
	// l_urlStream.close();
	// }

	public static <T> T toBean(String str, Class<T> c) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		T t = null;
		t = mapper.readValue(str, c);
		return t;
	}


	/**
	 * 把一个文件转化为字节
	 * 
	 * @param file
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] getByte(File file) throws Exception {
		byte[] bytes = null;
		if (file != null) {
			InputStream is = new FileInputStream(file);
			int length = (int) file.length();
			if (length > Integer.MAX_VALUE) // 当文件的长度超过了int的最大值
			{
				System.out.println("this file is max ");
				return null;
			}
			bytes = new byte[length];
			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length
					&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}
			// 如果得到的字节长度和file实际的长度不一致就可能出错了
			if (offset < bytes.length) {
				System.out.println("file length is error");
				return null;
			}
			is.close();
		}
		return bytes;
	}

	/**
	 * 根据byte数组，生成文件
	 */
	public static void getFile(byte[] bfile, String filePath, String fileName) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			File dir = new File(filePath);
			if (!dir.exists() && dir.isDirectory()) {// 判断文件目录是否存在
				dir.mkdirs();
			}
			file = new File(filePath + "\\" + fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bfile);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public static byte[] hex2byte(String str) throws Exception { // 字符串转二进制
		if (str == null)
			return null;
		str = str.trim();
		int len = str.length();
		if (len == 0 || len % 2 == 1)
			return null;
		byte[] b = new byte[len / 2];
		for (int i = 0; i < str.length(); i += 2) {
			b[i / 2] = (byte) Integer.decode("0x" + str.substring(i, i + 2))
					.intValue();
		}
		return b;
	}
	
	
	public static boolean checkPassword(String password){
//		String reg = "((?=.*\\d)(?=.*[a-zA-Z])(?=.*[`@#$%~!^&*()_+\\-={}|//[//]:\"'\\;<>?|,./]).{8,20})";
		String reg = "((?=.*\\d)(?=.*[a-zA-Z]).{8,20})";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(password);
		return m.matches();
	}
	
	public static void main(String[] args){
		Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
		System.out.println(md5PasswordEncoder.encodePassword("mcafee123", null));
	}
	
}
