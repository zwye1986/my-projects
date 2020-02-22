package com.venada.efinance.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContext {

	  private static ApplicationContext instance;

	  public synchronized static ApplicationContext getInstance() {
	    if (instance == null) {
	      instance =  new ClassPathXmlApplicationContext("/applicationContext.xml");
	    }
	    return instance;
	  }
	 
	} 