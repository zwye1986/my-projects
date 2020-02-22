package com.venada.efinance.business.impl;

import com.venada.efinance.business.SignSysConfigBusiness;
import com.venada.efinance.business.SystemConfigBusiness;
import com.venada.efinance.business.UserBusiness;
import com.venada.efinance.business.UserSignInBusiness;
import com.venada.efinance.business.UserWalletBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.controller.UserSignController;
import com.venada.efinance.pojo.SignIn;
import com.venada.efinance.pojo.User;
import com.venada.efinance.security.SpringSecurityUtil;
import com.venada.efinance.service.UserSignInService;
import com.venada.efinance.util.DateUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class UserSignInBusinessImpl implements UserSignInBusiness {
	@Autowired
	private UserSignInService userSignInServiceImpl;
	@Autowired
	private SystemConfigBusiness systemConfigBusinessImpl ;
	@Autowired
	private UserWalletBusiness userWalletBusinessImpl;
	@Autowired
	private UserBusiness userBusiness;
	@Autowired
	private SignSysConfigBusiness signSysConfigBusinessImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(UserSignController.class);
	
	@Override
	public void addSignIn(SignIn obj) throws BusinessException {
		userSignInServiceImpl.saveObject("addSignIn", obj);
	}

	@Override
	public List<SignIn> listSignInBySelf(Map<String,Object> condition,PaginationMore page) throws BusinessException {
		page.setTotalRows(getSignInBySelfCount(condition));
		page.repaginate();
		
		return userSignInServiceImpl.selectList("listSignInBySelf", condition, page);
	}

	@Override
	public Integer getSignInBySelfCount(Map<String,Object> condition)
			throws BusinessException {
		
		return (Integer)userSignInServiceImpl.getObject("getSignInBySelfCount", condition);
	}
	
	@Override
	public Integer getCustomerSignInMonthCount(Map<String,Object> condition)
			throws BusinessException {
		return (Integer)userSignInServiceImpl.getObject("getCustomerSignInMonthCount", condition);
	}
	
	

	@Override
	public List<SignIn> listSignInBySelf(Map<String,Object> condition)
			throws BusinessException {
		
		return userSignInServiceImpl.findObjects("listSignInBySelfBefore", condition);
	}

	@Override
	public List<String> listSignInByMonth(Map<String, Object> condition)
			throws BusinessException {
		List<SignIn> signInList=	userSignInServiceImpl.findObjects("listSignInByMonth", condition);
		List<String> signInDays=new ArrayList<String>();
		for(SignIn signIn:signInList ){
			signInDays.add(signIn.getSignday());
		}
		return signInDays;
	}

	@Override
	public Integer getSignInByAllCount()throws BusinessException {
		return (Integer)userSignInServiceImpl.getObject("getSignInByAllCount",null);
	}

	@Override
	public List<SignIn> listSignByYearAndMonth(Map<String, Object> condition)
			throws BusinessException {
		return userSignInServiceImpl.findObjects("listSignByYearAndMonth", condition);
	}

	@Override
	public List<SignIn> listSignByMobilePhone(Map<String, Object> condition)
			throws BusinessException {
		return userSignInServiceImpl.findObjects("listSignByMobilePhone", condition);
	}
	
	@Override
	public List<SignIn> listSignByMobileNumber(Map<String, Object> condition, PaginationMore page)
			throws BusinessException {
		page.setTotalRows(getSignInBySelfCount(condition));
		page.repaginate();
		return userSignInServiceImpl.selectList("listSignByMobileNumber", condition, page);
	}

	@Override
	public BigDecimal sumSignBenefitByMobileNumber(Map<String, Object> condition)
			throws BusinessException {
		return (BigDecimal) userSignInServiceImpl.getObject("sumSignBenefitByMobileNumber", condition);
	}

	@Override
	public List<SignIn> getCustomerSignInMonthSeriesCount(
			Map<String, Object> condition) throws BusinessException {
		return  userSignInServiceImpl.findObjects("getCustomerSignInMonthSeriesCount", condition);

	}

	@Override
	@Transactional
	public Map<String, Object> getSignResult() throws BusinessException {
		User user =SpringSecurityUtil.getCurrentUser();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object>  isSignInResult=isSignIn(0);
		BigDecimal signbenefit=new BigDecimal(0);
		
		
		boolean flag=Boolean.valueOf(isSignInResult.get("flag").toString());
        if(flag){
        	result.put("flag", "havesignIn");
        	result.put("msg","您今日已经签到!");
        }else{
        	if(systemConfigBusinessImpl.getSystemConfig("115").getParamValue().equals("Y")){
    			BigDecimal allAmount=signSysConfigBusinessImpl.getAllAmountByUser(user);
    			signbenefit=signSysConfigBusinessImpl.getSignBenfitByUser(allAmount);
    		}else{
    			signbenefit =new BigDecimal(systemConfigBusinessImpl.getSystemConfig("105").getParamValue());
    		}
        	SignIn signIn=new SignIn();
        	signIn.setMobilePhone(user.getMobileNumber());
        	Map<String, Object>  isSignInResultBefore=isSignIn(-1);
        	boolean flagbefore=Boolean.valueOf(isSignInResultBefore.get("flag").toString());
        	if(flagbefore){
        		@SuppressWarnings("unchecked")
				List<SignIn>signInListBefore=(List<SignIn>)isSignInResultBefore.get("signInlist");
        	   signIn.setNewSignCount(signInListBefore.get(0).getNewSignCount()+1);
        	}else{
        		 signIn.setNewSignCount(1);
        	}
        	signIn.setSigndatetime(new Date());
        	signIn.setSignbenefit(signbenefit);
        	signIn.setId(UUID.randomUUID().toString());
        	String content="";
        	if(systemConfigBusinessImpl.getSystemConfig("115").getParamValue().equals("Y")){
        		content="您今天已经签到成功!";
        	}else{
        		if(signIn.getNewSignCount()>1){
    				if (signIn.getNewSignCount() == 7) {
    					signIn.setSignbenefit(signbenefit.multiply(new BigDecimal(5)));
    					content = "您已经连续签到" + signIn.getNewSignCount() + "次,本次签到获取"+signIn.getSignbenefit()
    							+ "纳币";
    				}else if (signIn.getNewSignCount() >= 8) {
    					signIn.setNewSignCount(1);
    					content="您签到"+signIn.getNewSignCount()+"次,本次签到获取"+signIn.getSignbenefit()+"纳币";
    				}else{
    					content = "您已经连续签到" + signIn.getNewSignCount() + "次,本次签到获取"+signIn.getSignbenefit()+ "纳币";
    				}
            	}else{
            		content="您签到"+signIn.getNewSignCount()+"次,本次签到获取"+signIn.getSignbenefit()+"纳币";
            	}
        		
        	}
        	Integer nowTime=DateUtils.getNowHours();
			if (nowTime >= 1 && nowTime <24) {
				signIn.setContent(content);
				signIn.setSignDate(new Date());
				addSignIn(signIn);
				userWalletBusinessImpl.addSignBenefit(signIn.getSignbenefit(),
						user.getId());
				result.put("flag", "signInsuccess");
				result.put("msg", "您签到成功!");
				logger.info("用户 " + user.getMobileNumber() + " 签到成功!");
			} else {
				result.put("flag", "signInnotTime");
				result.put("msg", "请于1点之后进行签到!");
			}
        	

        }
		

		return result;
	}
	
	/**
	 * 判断是否签到,如果异常认为也是签到过了。
	 * -1 代表昨天 0 代表今天
	 * @return
	 */
	public synchronized Map<String ,Object> isSignIn(int i){
		Map<String, Object> result = new HashMap<String, Object>();
		boolean flag=true;
		List<SignIn>signInlist=new ArrayList<SignIn>();
		try{
		Map<String, Object> condition = new HashMap<String, Object>();
		User user = SpringSecurityUtil.getCurrentUser();
		String signStartTime=DateUtils.getDayStart(i);
		String signEndTime=DateUtils.getDayEnd(i);
		condition.put("signStartTime", signStartTime);
		condition.put("signEndTime", signEndTime);
		condition.put("mobilePhone", user.getMobileNumber());
	    signInlist=listSignInBySelf(condition);
		if(signInlist.isEmpty()){
			 flag=false;
			 result.put("flag", flag);
			 result.put("signInlist", signInlist);
			 return result;
		}else{
			 result.put("flag", flag);
			 result.put("signInlist", signInlist);
			return result;
		}
		}catch(Exception e){
			 flag=true;
			 result.put("flag", flag);
			 result.put("signInlist", signInlist);
			 return result;
		}
	}

	@Override
	@Transactional
	public synchronized  Map<String, Object> userSignInTodayByMobilePhone(
			String mobileNumber, String password) throws BusinessException {
		Map<String, Object> result = new HashMap<String, Object>();
		User user =userBusiness.findUserByMoblieNumber(mobileNumber);
		if(user==null){
			result.put("errorcode", "1");
			result.put("errormsg", "用户不存在");
			return result;
		}else{
			if(!user.getPassword().equals(password)){
				result.put("errorcode", "2");
				result.put("errormsg", "密码错误");
				return result;
			}
		}
		Map<String, Object>  isSignInResult=isSignIn(user,0);
        BigDecimal signbenefit=new BigDecimal(systemConfigBusinessImpl.getSystemConfig("105").getParamValue());
		boolean flag=Boolean.valueOf(isSignInResult.get("flag").toString());
        if(flag){
        	result.put("flag", "havesignIn");
        	result.put("msg","您今日已经签到!");
        }else{
        	SignIn signIn=new SignIn();
        	signIn.setMobilePhone(user.getMobileNumber());
        	if(systemConfigBusinessImpl.getSystemConfig("115").getParamValue().equals("Y")){
    			BigDecimal allAmount=signSysConfigBusinessImpl.getAllAmountByUser(user);
    			signbenefit=signSysConfigBusinessImpl.getSignBenfitByUser(allAmount);
    		}else{
    			signbenefit =new BigDecimal(systemConfigBusinessImpl.getSystemConfig("105").getParamValue());
    		}
        	Map<String, Object>  isSignInResultBefore=isSignIn(user,-1);
        	boolean flagbefore=Boolean.valueOf(isSignInResultBefore.get("flag").toString());
        	if(flagbefore){
        		List<SignIn>signInListBefore=(List<SignIn>)isSignInResultBefore.get("signInlist");
        	   signIn.setNewSignCount(signInListBefore.get(0).getNewSignCount()+1);
        	}else{
        		 signIn.setNewSignCount(1);
        	}
        	signIn.setSigndatetime(new Date());
        	signIn.setSignbenefit(signbenefit);
        	signIn.setId(UUID.randomUUID().toString());
        	String content="";
			if (systemConfigBusinessImpl.getSystemConfig("115").getParamValue().equals("Y")) {
				content = "您今天已经签到成功!";
			} else {
				if (signIn.getNewSignCount() > 1) {
					if (signIn.getNewSignCount() == 7) {
						signIn.setSignbenefit(signbenefit
								.multiply(new BigDecimal(5)));
						content = "您已经连续签到" + signIn.getNewSignCount()
								+ "次,本次签到获取" + signIn.getSignbenefit() + "纳币";
					} else if (signIn.getNewSignCount() >= 8) {
						signIn.setNewSignCount(1);
						content = "您签到" + signIn.getNewSignCount() + "次,本次签到获取"
								+ signIn.getSignbenefit() + "纳币";
					} else {
						content = "您已经连续签到" + signIn.getNewSignCount()
								+ "次,本次签到获取" + signIn.getSignbenefit() + "纳币";
					}
				} else {
					content = "您签到" + signIn.getNewSignCount() + "次,本次签到获取"
							+ signIn.getSignbenefit() + "纳币";
				}
			}
        	Integer nowTime=DateUtils.getNowHours();
			if (nowTime >= 1 && nowTime <24) {
				signIn.setContent(content);
	        	signIn.setSignDate(new Date());
	        	addSignIn(signIn);
	        	
	        	userWalletBusinessImpl.addSignBenefit(signIn.getSignbenefit(),user.getId());
	        	result.put("flag", "signInsuccess");
	        	result.put("msg","您签到成功!");
	        	logger.info("用户mobilePhone='"+user.getMobileNumber()+"'签到成功!");
			} else {
				result.put("flag", "signInnotTime");
				result.put("msg", "请于1点之后进行签到!");
			}

        }
		return result;
	}

	/**
	 * 判断是否签到,如果异常认为也是签到过了。
	 * -1 代表昨天 0 代表今天
	 * @return
	 */
	public Map<String ,Object> isSignIn(User user,int i){
		Map<String, Object> result = new HashMap<String, Object>();
		boolean flag=true;
		List<SignIn>signInlist=new ArrayList<SignIn>();
		try{
		Map<String, Object> condition = new HashMap<String, Object>();
		String signStartTime=DateUtils.getDayStart(i);
		String signEndTime=DateUtils.getDayEnd(i);
		condition.put("signStartTime", signStartTime);
		condition.put("signEndTime", signEndTime);
		condition.put("mobilePhone", user.getMobileNumber());
	    signInlist=listSignInBySelf(condition);
		if(signInlist.isEmpty()){
			 flag=false;
			 result.put("flag", flag);
			 result.put("signInlist", signInlist);
			 return result;
		}else{
			 result.put("flag", flag);
			 result.put("signInlist", signInlist);
			return result;
		}
		}catch(Exception e){
			 flag=true;
			 result.put("flag", flag);
			 result.put("signInlist", signInlist);
			 return result;
		}
	}

}

