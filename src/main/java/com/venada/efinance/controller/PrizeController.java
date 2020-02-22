package com.venada.efinance.controller;

import com.venada.efinance.business.PrizeBusiness;
import com.venada.efinance.business.UserDetailBusiness;
import com.venada.efinance.pojo.*;
import com.venada.efinance.security.SpringSecurityUtil;
import com.venada.efinance.util.AppContext;
import com.venada.efinance.util.DateUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class PrizeController extends QuartzJobBean{
	/** Logger */
	private static final Logger logger = LoggerFactory.getLogger(PrizeController.class);
	@Autowired
	private PrizeBusiness prizeBusiness;
	@Autowired
	private UserDetailBusiness userDetailBusiness;
	//抽奖转盘
	@RequestMapping(value="/demo.html")
	public String demo(Model model){
		int number = new Random().nextInt(10) + 1;
		model.addAttribute("number", number);
		return "demo";
	}
	
	@RequestMapping(value="/festival.html")
	public String festival(Model model){
		User user = SpringSecurityUtil.getCurrentUser();
		if(user == null){
			model.addAttribute("lotterTime", 0);
		}else{
			UserLottery userLottery = prizeBusiness.getUserLottery(user.getMobileNumber());
			if(userLottery == null){
				model.addAttribute("lotterTime", 0);
			}else{
				int lotteryNum = userLottery.getTimes();
				model.addAttribute("lotterTime", lotteryNum);
			}
			
		}
		
				
		Map<String,Object> condition = new HashMap<String,Object>();
		List <String> dotype=new ArrayList<String>();
		dotype.add("2");// 查询每天领取游戏最多的用户
		dotype.add("3");//查询每天第二名到第10名的随机用户
		dotype.add("1");//圆盘抽奖
		dotype.add("4");//推荐奖
		condition.put("dotype", dotype);
		List<PrizeDetail> lotteryList = prizeBusiness.getPrizeDetailByType(condition);
		model.addAttribute("allList", lotteryList);
		
		lotteryList = prizeBusiness.queryLotteryDetail();
		model.addAttribute("lotteryList", lotteryList);
		
		return ".festival";
	}
	
	//抽奖
	@RequestMapping(value="/lottery.html")
	public @ResponseBody int lettery(){	
		
		User user = SpringSecurityUtil.getCurrentUser();
		if(user == null){
			return -1;  //用户未登录
		}else{
			UserDetail detail = userDetailBusiness.findUserDetailByUserId(user.getId());
			if(detail.getPhoto() == null){
				return -3; //用户没上传头像
			}
					
		}
		if(!DateUtils.compareCurrentDate("2014-01-02 12:00:00")){			
			   return -5;  //活动时间没到
	    }
		if(DateUtils.compareCurrentDate("2014-01-02 20:00:00")){			
			   return -6;  //活动时间没到
	    }
		final Integer s = 0;
		synchronized (s) {
		  try {
			  return prizeBusiness.userLottery(user);
		} catch (Exception e) {
			return 1;
		}
		  
		}	
	}
	
	
	
	

	static int searchIndexFlag =0;
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		
		if(searchIndexFlag>0)return ;//保证同一时刻只有一个定时器运行,通过这种方式保证每次定时时间到时，只执行一个线程
		searchIndexFlag = 1;//锁定
		ApplicationContext ctx =   AppContext.getInstance();
		PrizeBusiness prizeBusiness = (PrizeBusiness) ctx.getBean("prizeBusiness");
		
		//生成每个人的抽奖资格表数据ec_user_lottery
		prizeBusiness.deleteUserLotteryData();
		prizeBusiness.createUserLotteryData();
		
		
		
		int allnum = prizeBusiness.getAllNum();	
		double allsum = prizeBusiness.getAllsum();
		if(allsum < 1000000){
			prizeBusiness.deletePrize(1);  //删除5s
			prizeBusiness.deletePrize(2);  //删除air
			prizeBusiness.deletePrize(3);  //删除mini
			Prize prize = new Prize(); 
			prize.setId(4);
			prize.setNum(1);
			prizeBusiness.updatePrize(prize);  //更新小米3数量一台
			
			prize = new Prize();
			prize.setId(5);
			prize.setNum(1);
			prizeBusiness.updatePrize(prize); //更新红米数量一台
		}
		int prizeNum =  prizeBusiness.getPrizeNum();
		if(allnum < prizeNum){
			allnum = prizeNum;
		}
		List<Lottery> list = new ArrayList<Lottery>();
		for(int i= 0;i<allnum;i++){
			Lottery lottery = new Lottery();
			lottery.setPrizeId(0);
			list.add(lottery);
		}
		try{
		prizeBusiness.deleteLottery();
		prizeBusiness.insertLottery(list);
		
		prizeBusiness.lotteryOp(allnum);
		searchIndexFlag =0;
		}catch(Exception e){
			searchIndexFlag =0;
			logger.error("定时任务生成抽奖列表错误："+e.getMessage());
		}
	}
	public void getAllsum(){
		System.out.println(""+prizeBusiness.getAllsum());
	};
	
	public static void main(String[] args) {
		ApplicationContext ctx =   new ClassPathXmlApplicationContext("applicationContext.xml");
		PrizeController data=(PrizeController)	ctx.getBean("prizeController");
		data.getAllsum();

	}
}
