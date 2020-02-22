package com.venada.efinance.timingTasks;

import com.venada.efinance.business.BankBusiness;
import com.venada.efinance.pojo.Bank;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InitBank {
	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.setValidating(false);
		context.load("classpath*:applicationContext.xml");
		context.refresh();
		BankBusiness bankBusiness = (BankBusiness)context.getBean("bankBusiness");

		BufferedReader br = null;
		String ret = null;
		File f = null;
		try {
			f = new File("/Users/zhangwy/Downloads/bank.html");
			br = new BufferedReader(new FileReader(f));
			String line = null;
			StringBuffer sb = new StringBuffer((int) f.length());
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			ret = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
				}
			}
		}
		Pattern pattern = Pattern
				.compile("<a href=\".*?\" class=\"bankAlphabet-list-bank\" bank=\"(.*?)\" shortname=\"(.*?)\" >");
		Matcher pMatcher = pattern.matcher(ret);
		List<Bank> banks = new ArrayList<Bank>();
		while (pMatcher.find()) {
			Bank bank = new Bank();
			try {
				String s1 = pMatcher.group(1);
				System.out.println("s1 = " + s1);
				bank.setBankName(s1);
				String s2 = pMatcher.group(2);
				System.out.println("s2 = " + s2);
				bank.setShortName(s2);
				banks.add(bank);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		bankBusiness.initBank(banks);
	
	}

}
