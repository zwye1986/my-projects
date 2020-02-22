package com.venada.efinance.util;

import java.util.HashMap;
import java.util.Map;

/**
 * For a full list of configuration parameters refer at
 * [https://github.com/paypal
 * /adaptivepayments-sdk-java/wiki/SDK-Configuration-Parameters]
 */
public class Configuration {

	// Creates a configuration map containing credentials and other required
	// configuration parameters.
	public static final Map<String, String> getAcctAndConfig() {
		Map<String, String> configMap = new HashMap<String, String>();
		configMap.putAll(getConfig());

		// Account Credential
//		configMap.put("acct1.UserName", "sunwen_api1.venada.com.cn");
//		configMap.put("acct1.Password", "3YHF8BY2NHH257GH");
//		configMap.put("acct1.Signature",
//				"AFcWxV21C7fd0v3bYYYRCpSSRl31AxddnSxUk091xYkmP6IBVV7V1F4T");
//		configMap.put("acct1.AppId", "APP-0DL61145LG291594V");
		configMap.put("acct1.UserName", "sunwen_b3_api1.venada.com.cn");
		configMap.put("acct1.Password", "1376367430");
		configMap.put("acct1.Signature", "A9jiAq7yd3E1uYQRUy8q0CnRpijxAPq9BLeCo4wEtRibqio5XL33HwoF");
	    configMap.put("acct1.AppId", "APP-80W284485P519543T");
	    
		return configMap;
	}

	public static final Map<String, String> getConfig() {
		Map<String, String> configMap = new HashMap<String, String>();

		// Endpoints are varied depending on whether sandbox OR live is chosen
		// for mode
//		configMap.put("mode", "live");
		configMap.put("mode", "sandbox");

		// These values are defaulted in SDK. If you want to override default
		// values, uncomment it and add your value.
		// configMap.put("http.ConnectionTimeOut", "5000");
		// configMap.put("http.Retry", "2");
		// configMap.put("http.ReadTimeOut", "30000");
		// configMap.put("http.MaxConnection", "100");
		return configMap;
	}
}
