package com.venada.efinance.business.impl;

import com.venada.efinance.business.MessageRuleBusiness;
import com.venada.efinance.business.OperationLogBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.enumtype.LogTypeEnum;
import com.venada.efinance.pojo.MessageRule;
import com.venada.efinance.pojo.OperationLog;
import com.venada.efinance.pojo.User;
import com.venada.efinance.service.MessageRuleService;
import com.venada.efinance.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MessageRuleBusinessImpl implements MessageRuleBusiness {
	private static final Logger logger = LoggerFactory.getLogger(MessageRuleBusinessImpl.class);

	@Autowired
	private MessageRuleService  messageRuleServiceImpl;
	@Autowired
	private OperationLogBusiness opeartionLogBusiness;


	@Override
	public void addMessageRule(MessageRule messageRule)
			throws BusinessException {
		messageRuleServiceImpl.saveObject("addMessageRule", messageRule);
	}

	

	@Override
	public void updateMessageRuleById(MessageRule messageRule)
			throws BusinessException {
		messageRuleServiceImpl.updateObject("updateMessageRuleById", messageRule);
	}

	@Override
	public List<MessageRule> queryAllMessageRule(Map<String, Object> condition,
			PaginationMore page) throws BusinessException {
		return null;
	}

	@Override
	public void deleteMessageRuleById(String id) throws BusinessException {
		messageRuleServiceImpl.deleteObject("deleteMessageRuleById", id);
	}

	@Override
	public void updateMessageRule(Map<String, Object> params)
			throws BusinessException {
		messageRuleServiceImpl.updateObject("updateMessageRule", params);
		
	}

	@Override
	public MessageRule queryMessageRuleById(String id) throws BusinessException {
        MessageRule messageRule = (MessageRule) messageRuleServiceImpl.getObject("queryMessageRuleById", id);
		return messageRule;
	}



	@Override
	public List<MessageRule> findMessageRule(String userid)
			throws BusinessException {
		return messageRuleServiceImpl.findObjects("findMessageRule", userid);
	}
	
	@Override
	public String getSendMobileNumber(User user) throws BusinessException{
		String mobileNumber =user.getMobileNumber();
		List<MessageRule> messageRuleList=findMessageRule(user.getId());
		if(!messageRuleList.isEmpty()){
			mobileNumber=messageRuleList.get(0).getMobileNumber();
		}
		return mobileNumber;
	}

	/**
	 * 判断用户是否开启短信接收配置，如果未配置默认是发送
	 * 如果配置，判断是否开启，只有开启短信接收配置和当前时间符合规则才能限制短信接收时间
	 */
	@Override
	public boolean isSend(User user)  throws BusinessException{
		boolean isSend=true;
		Integer switchTag=0;
		Integer startTime=0;
		Integer endTime=24;
		
		OperationLog log = new OperationLog();
		log.setLogType(LogTypeEnum.MessageRule.getIndex());
		log.setDataOld("");
		
		
		List<MessageRule> messageRuleList=findMessageRule(user.getId());
		//从短信接收配置获取记录
		if(!messageRuleList.isEmpty()){
			MessageRule messageRule=messageRuleList.get(0);
			switchTag=messageRule.getSwitchTag();
			if(messageRule.getEndTime()==null||messageRule.getEndTime().equals("")){
				endTime=24;
			}else{
				endTime=Integer.parseInt(messageRule.getEndTime());
			}
			if(messageRule.getStartTime()==null||messageRule.getStartTime().equals("")){
				startTime=0;
			}else{
				startTime=Integer.parseInt(messageRule.getStartTime());
			}
		}
		if(switchTag==1){ //如果开关状态为开，说明启动了配置
			Integer nowTime=DateUtils.getNowHours();
			 if(nowTime>=startTime&&nowTime<=endTime){ //如果当前时间在配置时间区段内，置为发送状态
				 isSend=true;
			 }else{
				 isSend=false;
			 }
		}else{  //如果开关状态为关，说明关闭了配置
			isSend=true;
		}
		log.setDataNew("用户("+user.getId()+")短信开关设置为"+((switchTag==0)?"关":"开")+",startTime="+startTime+",endTime="+endTime+",短信发送时间="+DateUtils.getNowHours());
		opeartionLogBusiness.addOperationLog(log);
		return isSend;
	}
	
}
