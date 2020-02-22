package com.venada.efinance.timingTasks;

import com.venada.efinance.business.UserBusiness;
import com.venada.efinance.pojo.User;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class TopActiveTask {
	
	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.setValidating(false);
		context.load("classpath*:applicationContext.xml");
		context.refresh();

		UserBusiness userBusiness = (UserBusiness) context
				.getBean("userBusiness");
		List<User> users = userBusiness.queryActiveTopList(6);
//		userBusiness.batchAddTopActive(users);
	}

}
