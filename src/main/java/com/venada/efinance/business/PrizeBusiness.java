package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.*;

import java.util.List;
import java.util.Map;








/***
 * 
 * @author xupei
 *
 */

public interface PrizeBusiness {
    void insertLottery(List<Lottery> list) throws BusinessException;
    
    void deleteLottery() throws BusinessException;
    
    List<Prize> queryPirzeList()  throws BusinessException;
    
    void lotteryOp(int allnum) throws BusinessException;
    
    void deleteUserLotteryData() throws BusinessException;
    
    void createUserLotteryData() throws BusinessException;
    
    int getAllNum() throws BusinessException;
    
    double getAllsum() throws BusinessException;
    
    List<Lottery> queryLottery() throws BusinessException;
    
    Lottery getEmptyLottery() throws BusinessException;
    
    int getLotteryNum() throws BusinessException;
    
    UserLottery getUserLottery(String mobileNumber) throws BusinessException;
    
    void deleteLotteryById(int id) throws BusinessException;
    
    void insertPrizeDetail(PrizeDetail detail)  throws BusinessException;
    
    int userLottery(User user) throws BusinessException;
    
    int integralLottery(User user) throws Exception;
    
    List<PrizeDetail> queryPrizeDetail(Map<String,Object> map) throws BusinessException;
    
    List<PrizeDetail> queryLotteryDetail() throws BusinessException;
    
    int getPrizeNum() throws BusinessException;
    
    void deletePrize(int id) throws BusinessException;
    
    void updatePrize(Prize prize) throws BusinessException;
    
     List<PrizeDetail> getPrizeDetailByType(Map<String,Object> map) throws BusinessException;
     
     List<UserIntegralLotteryDetail> getUserIntegralLotteryDetailList(Map<String,Object> map) throws BusinessException;
     
     List<UserIntegralLotteryDetail> getUserIntegralLotteryDetailList(Map<String,Object> map,PaginationMore page) throws BusinessException;

	Integer getLotteryCount(Map<String, Object> condition)
			throws BusinessException;

}
