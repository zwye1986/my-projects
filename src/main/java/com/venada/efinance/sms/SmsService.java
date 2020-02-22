package com.venada.efinance.sms;


import com.venada.efinance.business.SystemConfigBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.exception.SmsException;
import com.venada.efinance.pojo.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

@Service
public class SmsService {

	private static final Logger logger = LoggerFactory
			.getLogger(SmsService.class);

	private String reg;

	private String pwd;

	private String smsUrl;

	private String sourceAdd = "";
	@Autowired
	private SystemConfigBusiness systemConfigBusiness;
	
	
	private static int connectTimeOut = 5000;
	private static int readTimeOut = 10000;
	private static String requestEncoding = "UTF-8";
	public static int getConnectTimeOut() {
		return connectTimeOut;
	}
	public static void setConnectTimeOut(int connectTimeOut) {
		SmsService.connectTimeOut = connectTimeOut;
	}
	public static int getReadTimeOut() {
		return readTimeOut;
	}
	public static void setReadTimeOut(int readTimeOut) {
		SmsService.readTimeOut = readTimeOut;
	}
	public static String getRequestEncoding() {
		return requestEncoding;
	}
	public static void setRequestEncoding(String requestEncoding) {
		SmsService.requestEncoding = requestEncoding;
	}

	public void sendSms(String mobile, String msg,String ...str) throws BusinessException {
		String smsParam;
		
		SystemConfig  systemConfig = systemConfigBusiness.getSystemConfig("106");
		if("0".equals(systemConfig.getParamValue())){
			return;
		}
		if(str.length == 0){
	    //调用华宜短信接口
		
		try {
			smsParam = "reg=" + reg + "&pwd=" + pwd + "&sourceadd=" + sourceAdd
					+ "&phone=" + mobile + "&content="
					+ HttpSend.paraTo16(msg + "【蛙宝网】");
			HttpSend.postSend(smsUrl, smsParam);
		} catch (UnsupportedEncodingException e) {
			logger.error("不支持的编码:" + e.getMessage());
			throw new BusinessException("0003");
		} catch (SmsException smsException) {
			logger.error("短信发送失败:" + smsException.getMessage());
			throw new BusinessException("0003");
		}
		
		}else{
			//调用江苏美圣短信接口
			Map<String, String> map = new HashMap<String, String>();
			map.put("username", "JSMB260043");//此处填写用户账号
			map.put("scode", "xupei19861223");//此处填写用户密码
			map.put("mobile",mobile);//此处填写发送号码
			map.put("tempid",str[0]);//此处填写模板短信编号
		//	map.put("tempid","MB-2013121310");//此处填写模板短信编号
			
			String content = "";
			for(int i = 1;i<str.length;i++){
				if(i == 1){
					content = content+"@"+i+"@="+str[i];
				}else{
					content = content+",@"+i+"@="+str[i];
				}
			}
			map.put("content",content);//此处填写模板短信内容
			String temp = SmsService.doPost("http://mssms.cn:8000/msm/sdk/http/sendsmsutf8.jsp",map, "");
			if("100".equals(temp)){
				logger.error("短信发送失败:"+temp);
				throw new BusinessException("0003"); 
			}else if("101".equals(temp)){
				logger.error("用户账号不存在或密码错误:"+temp);
				throw new BusinessException("0003");
			}else if("102".equals(temp)){
				logger.error("账号已禁用:"+temp);
				throw new BusinessException("0003");
			}else if("114".equals(temp)){
				logger.error("模板短信序号不存在:"+temp);
				throw new BusinessException("0003");
			}else if("115".equals(temp)){
				logger.error("短信签名标签序号不存在:"+temp);
				throw new BusinessException("0003");
			}
			System.out.println("值:" + temp);//此处为短信发送的返回值
		}
	}
	
	
	public void sendSmsYzmByMs(String mobile, String yzm) throws BusinessException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", "JSMB260043");//此处填写用户账号
		map.put("scode", "411920");//此处填写用户密码
		map.put("mobile",mobile);//此处填写发送号码
		map.put("tempid","MB-2013121310");//此处填写模板短信编号
		map.put("content","@1@="+yzm);//此处填写模板短信内容
		String temp = SmsService.doPost("http://mssms.cn:8000/msm/sdk/http/sendsms.jsp",map, "");
		if("100".equals(temp)){
			logger.error("短信发送失败:"+temp);
			throw new BusinessException("0003");
		}else if("101".equals(temp)){
			logger.error("用户账号不存在或密码错误:"+temp);
			throw new BusinessException("0003");
		}else if("102".equals(temp)){
			logger.error("账号已禁用:"+temp);
			throw new BusinessException("0003");
		}else if("114".equals(temp)){
			logger.error("模板短信序号不存在:"+temp);
			throw new BusinessException("0003");
		}else if("115".equals(temp)){
			logger.error("短信签名标签序号不存在:"+temp);
			throw new BusinessException("0003");
		}
	//	System.out.println("值:" + temp);//此处为短信发送的返回值
	}
	
	public static String doPost(String reqUrl, Map<String, String> parameters, String recvEncoding) {
		HttpURLConnection url_con = null;
		String responseContent = null;
		String vchartset=recvEncoding==""?SmsService.requestEncoding:recvEncoding;
		try {
			StringBuffer params = new StringBuffer();
			for (Iterator<?> iter = parameters.entrySet().iterator(); iter.hasNext();) {
				Entry<?, ?> element = (Entry<?, ?>) iter.next();
				params.append(element.getKey().toString());
				params.append("=");
				params.append(URLEncoder.encode(element.getValue().toString(), vchartset));
				params.append("&");
			}

			if (params.length() > 0) {
				params = params.deleteCharAt(params.length() - 1);
			}

			URL url = new URL(reqUrl);
			url_con = (HttpURLConnection) url.openConnection();
			url_con.setRequestMethod("POST");
			url_con.setConnectTimeout(SmsService.connectTimeOut);
			url_con.setReadTimeout(SmsService.readTimeOut);
			url_con.setDoOutput(true);
			byte[] b = params.toString().getBytes();
			url_con.getOutputStream().write(b, 0, b.length);
			url_con.getOutputStream().flush();
			url_con.getOutputStream().close();

			InputStream in = url_con.getInputStream();
			byte[] echo = new byte[10 * 1024];
			int len = in.read(echo);
			responseContent = (new String(echo, 0, len)).trim();
			int code = url_con.getResponseCode();
			if (code != 200) {
				responseContent = "ERROR" + code;
			}

		}
		catch (IOException e) {
			System.out.println("网络故障:"+ e.toString());
		}
		finally {
			if (url_con != null) {
				url_con.disconnect();
			}
		}
		return responseContent;
	}

	public void setReg(String reg) {
		this.reg = reg;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setSmsUrl(String smsUrl) {
		this.smsUrl = smsUrl;
	}

	public void setSourceAdd(String sourceAdd) {
		this.sourceAdd = sourceAdd;
	}
}
