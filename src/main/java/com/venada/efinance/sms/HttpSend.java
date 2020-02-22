package com.venada.efinance.sms;

import com.venada.efinance.common.exception.SmsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpSend {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpSend.class);
	
	public static String getSend(String strUrl, String param) {
		URL url = null;
		HttpURLConnection connection = null;

		try {
			url = new URL(strUrl + "?" + param);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.connect();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}

			reader.close();
			return buffer.toString();
		} catch (IOException e) {
			logger.error("通过Get方式发送短信失败:"+e.getMessage());
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public static String postSend(String strUrl, String param)  throws SmsException{

		URL url = null;
		HttpURLConnection connection = null;

		try {
			url = new URL(strUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.connect();

			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());
			out.writeBytes(param);
			out.flush();
			out.close();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}

			reader.close();
			return buffer.toString();
		} catch (IOException e) {
			logger.error("通过Post方式发送短信失败:"+e.getMessage());
			throw new SmsException("2001");
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public static String paraTo16(String str)
			throws UnsupportedEncodingException {
		String hs = "";

		byte[] byStr = str.getBytes("UTF-8");
		
		for (int i = 0; i < byStr.length; i++) {
			String temp = "";
			temp = (Integer.toHexString(byStr[i] & 0xFF));
			if (temp.length() == 1)
				temp = "%0" + temp;
			else
				temp = "%" + temp;
			hs = hs + temp;
		}
		
		return hs.toUpperCase();
	}
}