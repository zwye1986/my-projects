package com.venada.efinance.business.impl;


import com.venada.efinance.business.PrizeBusiness;
import com.venada.efinance.business.UserConvertCreditsBusiness;
import com.venada.efinance.business.UserWalletBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.exception.ServiceException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.*;
import com.venada.efinance.service.IntegralLotteryService;
import com.venada.efinance.service.LotteryService;
import com.venada.efinance.service.PrizeDetailService;
import com.venada.efinance.service.PrizeService;
import com.venada.efinance.service.RechargeRecordService;
import com.venada.efinance.service.UserIntegralLotteryDetailService;
import com.venada.efinance.service.UserLotteryService;
import com.venada.efinance.service.UserService;
import com.venada.efinance.util.DateUtils;
import com.venada.efinance.util.DecimalUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/***
 * 
 * @author xupei
 *
 */
@Service("prizeBusiness")
public class PrizeBusinessImpl implements PrizeBusiness{
	@Autowired
	private PrizeService prizeService;
	@Autowired
	private IntegralLotteryService integralLotteryService;
	@Autowired
	private PrizeDetailService prizeDetailService;
	@Autowired
	private LotteryService lotteryService;
	@Autowired
	private UserLotteryService userLotteryService;
	@Autowired
	private UserIntegralLotteryDetailService userIntegralLotteryDetailService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserWalletBusiness userWalletBusiness;
	@Autowired
	private UserConvertCreditsBusiness userConvertCreditsBusiness;
	@Autowired
	private RechargeRecordService rechargeRecordService;
	/** Logger */
	private static final Logger logger = LoggerFactory.getLogger(PrizeBusinessImpl.class);
	@Override
	public void insertLottery(List<Lottery> list) throws BusinessException {
		lotteryService.batchAddObject("insertLottery", list);
	}
	@Override
	public List<Prize> queryPirzeList() throws BusinessException {
		return prizeService.findObjects("queryPirzeList", null);
	}
	@Override
	public void lotteryOp(int allnum) throws BusinessException {
		int num = allnum;  //总共的抽奖次数
		int prizeNum =  (Integer) prizeService.getObject("getPrizeNum", null);
	   
		int[] b=new int[prizeNum];
		  for(int i=0;i<b.length;i++){
			  b[i]=new Random().nextInt(num) + 1;
			  for(int j=0;j<i+1;j++){
				  if((i!=j)&&b[i]==b[j])
					  b[i]=new Random().nextInt(num) + 1;
			  }
		  }
		  
		  List<Integer> templist = new ArrayList<Integer>();
		  List<Prize> list = this.queryPirzeList();
		  for(int x = 0;x<list.size();x++){
			  Prize prize = list.get(x);
			  for(int y = 0;y<prize.getNum();y++){
				  templist.add(prize.getId());
			  }
		  }
		  
		  List<Lottery> emptyList = new ArrayList<Lottery>();
		  if(templist.size() == b.length){
			  for(int i=0;i<b.length;i++){
					Lottery lottery = new Lottery();
					lottery.setId(b[i]);
					lottery.setPrizeId(templist.get(i));
					emptyList.add(lottery);
			 }
		  }  
		  lotteryService.batchUpdateObject("updateLotteryByList", emptyList);
	}
	@Override
	public void createUserLotteryData() throws BusinessException {
		userLotteryService.saveObject("createUserLotteryData", null);
	}
	@Override
	public int getAllNum() throws BusinessException {
		return (Integer) userLotteryService.getObject("getAllNum", null);
	}
	@Override
	public List<Lottery> queryLottery() throws BusinessException {
		return lotteryService.findObjects("queryLottery", null);
	}
	@Override
	public int getLotteryNum() throws BusinessException {
		return (Integer) lotteryService.getObject("getLotteryNum", null);
	}
	@Override
	public UserLottery getUserLottery(String mobileNumber)
			throws BusinessException {
		 return (UserLottery) userLotteryService.getObject("getUserLottery", mobileNumber);
	}
	@Override
	public Lottery getEmptyLottery() throws BusinessException {
		return (Lottery) lotteryService.getObject("getEmptyLottery", null);
	}
	@Override
	public void deleteLotteryById(int id) throws BusinessException {
		lotteryService.deleteObject("deleteLotteryById", id);
	}
	@Override
	public void insertPrizeDetail(PrizeDetail detail) throws BusinessException {
		prizeDetailService.saveObject("insertPrizeDetail", detail);
	}
	@Transactional(rollbackFor = { Exception.class, ServiceException.class })
	public int userLottery(User user) throws BusinessException {
		int returnNum = 1;
		UserLottery userLottery = (UserLottery) userLotteryService.getObject("getUserLottery", user.getMobileNumber());
		if(userLottery == null){
			return -4;  //用户没权限抽奖
		}
		if(userLottery.getTimes() < 1){
			return -2;  //次数不够
		}
		if(userLottery.getAllSum()<10000){
			Lottery empty = (Lottery) lotteryService.getObject("getEmptyLottery", null);
			if(empty != null){
				lotteryService.deleteObject("deleteLotteryById", empty.getId());
				PrizeDetail detail = new PrizeDetail();
				detail.setId(UUID.randomUUID().toString());
				detail.setMobileNumber(user.getMobileNumber());
				detail.setDoType(1);
				detail.setPrizeName("0");
				prizeDetailService.saveObject("insertPrizeDetail", detail);
				userLottery.setTimes(userLottery.getTimes()-1);
				userLotteryService.updateObject("updateUserLottery", userLottery);
				returnNum = new Random().nextInt(2)+1;
				if(returnNum == 2){
					returnNum = 6;
				}
				return returnNum;
			}
		}
		
		List<Lottery> list = lotteryService.findObjects("queryLottery", null);
		int number = new Random().nextInt(list.size()) + 1;
		PrizeDetail detail = new PrizeDetail();
		detail.setId(UUID.randomUUID().toString());
		detail.setMobileNumber(user.getMobileNumber());
		detail.setDoType(1);
		
		Lottery lottery = list.get(number-1);
		int prizeId = lottery.getPrizeId();
		if(prizeId==0){
			detail.setPrizeName("0");
			returnNum = new Random().nextInt(2)+1;
			if(returnNum == 2){
				returnNum = 6;
			}
		}else if(prizeId==1){
			detail.setPrizeName("iPhon5s土豪金1部");
			returnNum = 3;
		}else if(prizeId==2){
			detail.setPrizeName("iPad air 1部");
			returnNum = 2;
		}else if(prizeId==3){
			detail.setPrizeName("iPad mini retina 1部");
			returnNum = 0;
		}else if(prizeId==4){
			detail.setPrizeName("小米3手机一部");
			returnNum = 9;
		}else if(prizeId==5){
			detail.setPrizeName("红米手机一部");
			returnNum = 8;
		}else if(prizeId==6){
			detail.setPrizeName("500纳币");
			returnNum = 4;
		}else if(prizeId==7){
			detail.setPrizeName("酷刷刷卡器一台");
			returnNum = 5;
		}else if(prizeId==8){
			detail.setPrizeName("蛙宝会员一年");
			returnNum = 7;
		}
		lotteryService.deleteObject("deleteLotteryById", lottery.getId());
		prizeDetailService.saveObject("insertPrizeDetail", detail);
		userLottery.setTimes(userLottery.getTimes()-1);
		userLotteryService.updateObject("updateUserLottery", userLottery);
		return returnNum;
	}
	
	@Transactional(rollbackFor = { Exception.class, ServiceException.class })
	public synchronized int integralLottery(User user) throws Exception {
		user =   (User) userService.getObject("findUserById", user.getId());
		int returnNum = 1;
		String returnName = "";
		Integer mycredit = user.getCredits();
		if(mycredit < 10000){
			return -4;  //积分不够
		}
		
		int status = userConvertCreditsBusiness.conSumeCredits(user.getId(), 10000);
		if(status==2){
			return -4;  //积分不够
		}
		
		List<IntegralLottery> list = integralLotteryService.findObjects("queryIntegralLottery", null);
		Random random = new Random();
		int number = random.nextInt(10000);
		
		int index = 0;
		for(int i = 0;i<list.size();i++){
			IntegralLottery lottery = list.get(i);
			if(number>=lottery.getBeginnum() && number<=lottery.getEndnum()){
				index = i;
			}
		}
		
		if(index==0){
			returnNum = 6;
			returnName = "再接再厉";
		}else{
			returnNum = list.get(index).getId();
			returnName = list.get(index).getName();
		}
	
		UserIntegralLotteryDetail detail = new UserIntegralLotteryDetail();
		detail.setId(UUID.randomUUID().toString());
		detail.setUserId(user.getId());
		detail.setPrizeId(returnNum);
		detail.setPrizeName(returnName);
		detail.setIntegral(10000);
		detail.setCreateTime(new Date());
		userIntegralLotteryDetailService.saveObject("insertUserIntegralLotteryDetail", detail);
		
		if(returnNum == 0 || returnNum ==1 || returnNum==2){
			int months = 0;
			if(returnNum==0){
				months=6;
			}else if(returnNum==1){
				months=3;
			}else if(returnNum==2){
				months=1;
			}
			userWalletBusiness.randomVip(user, months);
		}else if(returnNum==7 || returnNum == 8 || returnNum == 9){
			BigDecimal money = new BigDecimal(0);
			if(returnNum == 7){
				money = new BigDecimal(100);
			}else if(returnNum == 8){
				money = new BigDecimal(10);
			}else if(returnNum == 9){
				money = new BigDecimal(1);
			}
			
			
			// 查询被充值用户电子钱包
			UserWallet userWallet = userWalletBusiness.getUserWalletByUserId(user.getId());
			// 添加充值流水号
			String trackingId = generateCSerialNumber();
            RechargeRecord rechargeRecord = new RechargeRecord();
            rechargeRecord.setId(UUID.randomUUID().toString());
            rechargeRecord.setAmount(money);
			rechargeRecord.setSerialNumber(trackingId);
			// 先设置为正在处理，等paypal|tenpay回调后再正式增加金额
			rechargeRecord.setStatus("3");
			rechargeRecord.setIpAddress("127.0.0.1");
			rechargeRecord.setDateTime(new Date());
			// 根据手机号码，获得用户信息
			rechargeRecord.setUserId(user.getId());
			rechargeRecord.setDescription("抽奖");
			rechargeRecord.setBalance(DecimalUtil.add(userWallet.getAmount(), money));
			rechargeRecordService.saveObject("addRechargeRecord",
					rechargeRecord);
			
			// 更新被充值用户电子钱包,增加余额
			userWallet.setAmount(DecimalUtil.add(userWallet.getAmount(),money));
			userWalletBusiness.updateUserWallet(userWallet);
		}
		
        

		return returnNum;
	}
	
	public String generateCSerialNumber() throws BusinessException {
		try {
			return "C" + DateUtils.today("yyyyMMddHHmmssSSS")
					+ System.currentTimeMillis();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"generateCSerialNumber()方法出错！\t", e.getMessage() });
		}
	}
	
	@Override
	public List<PrizeDetail> queryPrizeDetail(Map<String,Object> map)
			throws BusinessException {
		return prizeDetailService.findObjects("prizeDetailService", map);
	}
	@Override
	public List<PrizeDetail> queryLotteryDetail() throws BusinessException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("doType",1);
		return prizeDetailService.findObjects("prizeDetailService", map);
	}
	@Override
	public int getPrizeNum() throws BusinessException {
		return (Integer) prizeService.getObject("getPrizeNum", null);
	}
	@Override
	public void deletePrize(int id) throws BusinessException {
		prizeService.deleteObject("deletePrize", id);
	}
	@Override
	public void updatePrize(Prize prize) throws BusinessException {
		prizeService.updateObject("updatePrize", prize);
	}
	@Override
	public void deleteUserLotteryData() throws BusinessException {
		userLotteryService.deleteObject("deleteUserLotteryData", null);
	}
	@Override
	public double getAllsum() throws BusinessException {
		return (Double) userLotteryService.getObject("getAllsum", null);
	}
	
	@Override
	public List<PrizeDetail> getPrizeDetailByType(Map<String,Object> map)
			throws BusinessException {
		return prizeDetailService.findObjects("getPrizeDetailByType", map);
	}
	@Override
	public void deleteLottery() throws BusinessException {
		lotteryService.deleteObject("deleteLottery", null);
	}
	
	public List<UserIntegralLotteryDetail> getUserIntegralLotteryDetailList(Map<String,Object> map) throws BusinessException{
		List<UserIntegralLotteryDetail> list = userIntegralLotteryDetailService.findObjects("queryUserIntegralLotteryDetail", map);
		return list;
	}
	@Override
	public List<UserIntegralLotteryDetail> getUserIntegralLotteryDetailList(
			Map<String, Object> map, PaginationMore page)
			throws BusinessException {
		page.setTotalRows(getLotteryCount(map));
		page.repaginate();
		return userIntegralLotteryDetailService.selectList("queryUserIntegralLotteryDetail", map, page);

	}
	
	@Override
	public Integer getLotteryCount(Map<String,Object> condition)
			throws BusinessException {
		
		return (Integer)userIntegralLotteryDetailService.getObject("getLotteryCount", condition);
	}
	
}
