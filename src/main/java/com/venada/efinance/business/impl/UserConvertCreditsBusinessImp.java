package com.venada.efinance.business.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.venada.efinance.business.CommodityBusiness;
import com.venada.efinance.business.UserBusiness;
import com.venada.efinance.business.UserConvertCreditsBusiness;
import com.venada.efinance.business.WeixinUserBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.exception.ServiceException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.Commodity;
import com.venada.efinance.pojo.ConvertCredits;
import com.venada.efinance.pojo.User;
import com.venada.efinance.service.CommodityService;
import com.venada.efinance.service.UserConvertCreditsService;
import com.venada.efinance.service.UserService;

/**
 * 
 * @author hepei
 * 
 */
@Service(value = "userConvertCreditsBusinessImp")
public class UserConvertCreditsBusinessImp implements UserConvertCreditsBusiness {

	@Autowired
	private UserConvertCreditsService userConvertCreditsServiceImpl;

	@Autowired
	private UserService userService;
    @Autowired
    private UserBusiness userBusiness;
	@Autowired
	private CommodityService commodityService;
	@Autowired
	private WeixinUserBusiness weixinUserBusiness;
    @Autowired
    private CommodityBusiness commodityBusiness;



	@Override
	public List<ConvertCredits> queryConvertCredits(
			Map<String, Object> condition, PaginationMore page)
			throws BusinessException {
		page.setTotalRows(queryAllConvertCreditsCount(condition));
		page.repaginate();
		return userConvertCreditsServiceImpl.selectList("queryAllConvertCredits",
				condition, page);

	}
	
	

	@Override
	public void addConvertCredits(ConvertCredits convertCredits)
			throws BusinessException {
		userConvertCreditsServiceImpl.saveObject("addConvertCredits", convertCredits);
	}

	@Override
	public ConvertCredits queryConvertCreditsById(Map<String, Object> condition)
			throws BusinessException {
		return (ConvertCredits) userConvertCreditsServiceImpl.getObject("queryConvertCreditsById",
				condition);
	}

	@Override
	public void updateConvertCreditsById(ConvertCredits convertCredits)
			throws BusinessException {
		userConvertCreditsServiceImpl.updateObject("updateConvertCreditsById", convertCredits);
	}

	

	@Override
	public void deleteConvertCreditsById(int id) throws BusinessException {
		userConvertCreditsServiceImpl.deleteObject("deleteConvertCredits", id);
	}

	@Override
	@Transactional(rollbackFor = {Exception.class,ServiceException.class})
	public synchronized int exchange(User user, Commodity commodity,String mobilePhone)
			throws BusinessException {

        Commodity _commodity = commodityBusiness.getCommodity(commodity.getId());

		if(Integer.parseInt(commodity.getCount()) > _commodity.getNum()){
//            throw new BusinessException("1001",new Object[]{"兑换失败","您兑换商品的数量超过了库存量！"});
            return 1;

		}

        user = userBusiness.findUserById(user.getId());

        if(user.getCredits() < _commodity.getIntegral()*Integer.parseInt(commodity.getCount())){
//            throw new BusinessException("1001",new Object[]{"兑换失败","您当前积分不够兑换所选商品！"});
            return 2;
		}
        	user.setCredits(user.getCredits() - _commodity.getIntegral()*Integer.parseInt(commodity.getCount()));
			_commodity.setCount(commodity.getCount());
			_commodity.setNum(_commodity.getNum() - Integer.parseInt(commodity.getCount()));
		userService.updateObject("updateUserByMobileNumber", user);
		commodityService.updateObject("updateCommodity", _commodity);
		if(mobilePhone==null||mobilePhone==""){
			mobilePhone="0";
		}
		ConvertCredits convertCredits = new ConvertCredits();
		convertCredits.setId(UUID.randomUUID().toString());
		convertCredits.setUserId(user.getId());
		convertCredits.setExpendCredits(Long.parseLong(String.valueOf(_commodity.getIntegral()*Integer.parseInt(commodity.getCount()))));
		convertCredits.setStatus(0);
		convertCredits.setCreditsGoodsId(_commodity.getId());
		convertCredits.setNum(Integer.parseInt(_commodity.getCount()));
		convertCredits.setCreditsGoodsName(_commodity.getName());
		convertCredits.setMobileNumber(mobilePhone);

		userConvertCreditsServiceImpl.saveObject("addConvertCredits", convertCredits);
		//---微信提醒

//		WeixinUser weixinUser=weixinUserBusiness.getWeixinUserByUserId(user.getId());
//		if(weixinUser!=null){
//			AdvancedUtil.sendCustomMessage(TokenThread.accessToken.getAccessToken(), AdvancedUtil.makeTextCustomMessage(weixinUser.getOpenid(),
//				DateUtils.currentTime("yyyy-MM-dd HH:mm:ss")+"\n您从蛙宝网积分商城成功兑换了"+commodity.getCount()+"个"+_commodity.getName()));
//		}
		//---微信提醒结束
        return 0;
	}

	@Override
	public List<ConvertCredits> queryConvertCreditsByMobilePhone(
			Map<String, Object> condition) throws BusinessException {
		return userConvertCreditsServiceImpl.findObjects(
				"queryConvertCreditsByMobilePhone", condition);
	}
	
	@Override
	public  int queryAllConvertCreditsCount(Map<String, Object> condition)
			throws BusinessException {
		return (Integer) userConvertCreditsServiceImpl.getObject(
				"queryAllConvertCreditsCount", condition);
	}



	@Override
	/**
	 * 返回0 用户未找到。
	 * 返回1 操作成功
	 * 返回2 用户积分不够
	 */
	public  int conSumeCredits(String userid, int credits) throws BusinessException {
		User user=userBusiness.findUserById(userid);
		if(user!=null){
			if(credits>user.getCredits()){
				return 2;
			}else{
	        	user.setCredits( (user.getCredits() -credits));
				userService.updateObject("updateUserByMobileNumber", user);
				return 1;
			}
		}else{
			return 0;
		}
		
	}

	
}
