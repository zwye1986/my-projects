package com.venada.efinance.controller;

import com.venada.efinance.business.ExchangeCodeBussiness;
import com.venada.efinance.business.OpponentGetDataBusiness;
import com.venada.efinance.business.UserBusiness;
import com.venada.efinance.pojo.*;
import com.venada.efinance.util.AppContext;
import com.venada.efinance.util.DateUtils;
import com.venada.efinance.util.HttpTookit;
import com.venada.efinance.util.SystemUtils;
import net.sf.json.JSONObject;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class QuartzGetData extends QuartzJobBean {

	@Autowired
	private OpponentGetDataBusiness opponentGetDataBusinessImpl;
	
	@Autowired
	private ExchangeCodeBussiness exchangeCodeBusinessImpl;
	@Autowired
	private UserBusiness userBusiness;
	/**
	 * 充值
	 */
	public void saveQianbaoRecharge() {
		System.out.println("saveQianbaoRecharge开始运行");
		Map<String, Class<?>> m = new HashMap<String, Class<?>>();
		m.put("message", String.class);
		m.put("data", QianBaoRecharge.class);
		String jsonStr = HttpTookit.doPost("http://www.qianbao666.com/wallet/latestRechargeMore.html",new HashMap<String ,String>());
		System.out.println("jsonStr"+jsonStr);
		QianBaoRechargeBean qianBaoRechargeBean = (QianBaoRechargeBean) JSONObject
				.toBean(JSONObject.fromObject(jsonStr),
						QianBaoRechargeBean.class, m);
		List<QianBaoRecharge> list = qianBaoRechargeBean.getData();
		Calendar calendar = Calendar.getInstance();
		String year=String.valueOf(calendar.get(Calendar.YEAR));
		
		for (int i = 0; i < list.size(); i++) {
			QianBaoRecharge data=list.get(i);
			HashMap condition=new HashMap<String,Object>();
			condition.put("id", data.getId());
			condition.put("dotype", 0);
			if(opponentGetDataBusinessImpl.getOpponentDataById(condition).isEmpty()){
				OpponentData oppdata=new OpponentData();
				oppdata.setId(data.getId());
				oppdata.setTelephone(data.getTelephone());
				oppdata.setDotype(0);
				oppdata.setMoney(new BigDecimal(data.getMoney().replace(",","")));
				String time=year+"-"+data.getTime();
				try {
					oppdata.setActiontime(DateUtils.parseDate(time, "yyyy-MM-dd HH:mm"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				try{
				opponentGetDataBusinessImpl.addOpponentData(oppdata);
				}catch(Exception be){
					System.out.println(data.getId()+"重复！");
					continue;
				}
			}
		}
	}
/**
 * 提现
 */
	public void saveQianbaoTransfer() {
		System.out.println("saveQianbaoTransfer开始运行");
		Map<String, Class<?>> m = new HashMap<String, Class<?>>();
		m.put("message", String.class);
		m.put("data", QianBaoRecharge.class);
		Calendar calendar = Calendar.getInstance();
		 String year=String.valueOf(calendar.get(Calendar.YEAR));
		String jsonStr = HttpTookit.doPost("http://www.qianbao666.com/wallet/latestTransferMore.html",new HashMap<String ,String>());
		QianBaoRechargeBean qianBaoRechargeBean = (QianBaoRechargeBean) JSONObject
				.toBean(JSONObject.fromObject(jsonStr),
						QianBaoRechargeBean.class, m);
		List<QianBaoRecharge> list = qianBaoRechargeBean.getData();
		
		for (int i = 0; i < list.size(); i++) {
			QianBaoRecharge data=list.get(i);
			HashMap condition=new HashMap<String,Object>();
			condition.put("id", data.getId());
			condition.put("dotype", 1);
			if(opponentGetDataBusinessImpl.getOpponentDataById(condition).isEmpty()){
				OpponentData oppdata=new OpponentData();
				oppdata.setId(data.getId());
				oppdata.setTelephone(data.getTelephone());
				oppdata.setDotype(1);
				oppdata.setMoney(new BigDecimal(data.getMoney().replace(",","")));
				String time=year+"-"+data.getTime();
				try {
					oppdata.setActiontime(DateUtils.parseDate(time, "yyyy-MM-dd HH:mm"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				try{
					opponentGetDataBusinessImpl.addOpponentData(oppdata);
					}catch(Exception be){
						System.out.println(data.getId()+"重复！");
						continue;
					}
			}
		}
	}
	
	
	public String obtionInviteCode(){
		String code="000000";
		code=SystemUtils.randomCheckcode("abcdefghijklmn0pqrstuvwxyz1234567890", 6);
		if(exchangeCodeBusinessImpl.getExchangecode(code)!=null){
			code=obtionInviteCode();
		}
		return code;
	}
	
	/**
	 * 生成20w个兑换码
	 */
	public void getExchangeCode(){
		for(int i=0;i<59466;i++){
			ExchangeCode ec =new ExchangeCode();
			ec.setExchangecode(obtionInviteCode());
			exchangeCodeBusinessImpl.addExchangecode(ec);
		}
	}
	
	/**
	 * 补全svip的推荐码
	 */
	public void updateUserInviteCode(){
		List<User> userlist=userBusiness.querySvipUserList();
		
		for(User u :userlist){
			u.setInviteCodeSelf(userBusiness.obtionInviteCode());
			userBusiness.updateSvipUserForInviteCode(u);
		}
	}

	public static void main(String[] args) {
		ApplicationContext ctx =   new ClassPathXmlApplicationContext("applicationContext.xml");
		QuartzGetData data=(QuartzGetData)	ctx.getBean("quartzGetData");
//		data.saveQianbaoTransfer();
//		data.saveQianbaoRecharge();
//		data.getExchangeCode();
		data.updateUserInviteCode();

	}
	
	public void signInUp() {
		System.out.println("signInUp开始运行");
		Map<String, Class<?>> m = new HashMap<String, Class<?>>();
		m.put("message", String.class);
		m.put("data", QianBaoRecharge.class);
        Map<String ,String> condition=new HashMap<String ,String>();
       for(int i=0;i<10;i++){
    	   String jsonStr = HttpTookit.doPost("http://www.wowpower.cn/mobilePhone/18761640621/57fefe27faf698052c1a4f2360133c3b/signIn",new HashMap<String ,String>());
   		   System.out.println("jsonStr"+jsonStr);
       }
		
		
	}
	
	@Override
	protected void executeInternal(JobExecutionContext jobexecutioncontext)
			throws JobExecutionException {
		
		ApplicationContext ctx =   AppContext.getInstance();
		QuartzGetData data=(QuartzGetData)	ctx.getBean("quartzGetData");
		data.saveQianbaoRecharge();
		data.saveQianbaoTransfer();
	}
}
