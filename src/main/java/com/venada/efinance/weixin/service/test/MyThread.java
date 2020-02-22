package com.venada.efinance.weixin.service.test;

import com.venada.efinance.pojo.QianBaoRecharge;
import com.venada.efinance.util.HttpTookit;

import java.util.HashMap;
import java.util.Map;
/**
 * 测试积分
 * @author hepei
 *
 */
class MyThread implements java.lang.Runnable {
	private int threadId;

	public MyThread(int id) {
		this.threadId = id;
	}

	@Override
	public  void run() {
		System.out.println("signInUp开始运行");
		Map<String, Class<?>> m = new HashMap<String, Class<?>>();
		m.put("message", String.class);
		m.put("data", QianBaoRecharge.class);
        for(int i=0;i<2;i++){
    	   String jsonStr = HttpTookit.doPost("http://localhost:8080/wowpower/mobilePhone/18761640621/57fefe27faf698052c1a4f2360133c3b/signIn",new HashMap<String ,String>());
   		   System.out.println("jsonStr"+jsonStr);
       }
	}
}


