package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface UserBusiness {
	public User findUserById(String id) throws BusinessException;

	public User findUserByMoblieNumber(String mobileNumber)
			throws BusinessException;

	public User findUserByMoblieNumberByLogin(String mobileNumber)
			throws BusinessException;
	
	public User findUserByInviteCode(String inviteCode)throws BusinessException;
	
	public List<User>listUserByInviteCodeFromOther(PaginationMore page,Map<String, Object> condition)throws BusinessException;
	
	public List<User>listUserByInviteCodeFromOther(Map<String, Object> condition)throws BusinessException;


	public void updateUserByMobileNumber(User user) throws BusinessException;
	public void updateUserByMobileNumber(User user,LoginLog loginLog) throws BusinessException;

	public List<User> listUserByInviteCodeFromOtherMobilePhone(Map<String, Object> condition) throws BusinessException;
	


	public void complementUserDetail(User user,UserDetail userDetail) throws BusinessException;
	public void addUser(User user,
			UserDetail userDetail,UserQuestion userQuestion) throws BusinessException;


	public UserHis findUserHisByMoblieNumber(String mobileNumber)
			throws BusinessException;

	public List<User> queryWealthTopList(int t) throws BusinessException;

	public void batchAddTopWealth(List<User> users) throws BusinessException;


	public List<User> queryActiveTopList(int t) throws BusinessException;

	public void batchAddTopActive(List<User> users) throws BusinessException;
	public List<User> queryLevelTopList(int t) throws BusinessException;
	
	public List<User>  listAllUsers(PaginationMore page,Map<String,Object>condition) throws BusinessException;

	public void batchAddTopLevel(List<User> users) throws BusinessException;
	
	public List<User> getTopWealth(Map<String,Integer> condition) throws BusinessException;
	
	public List<User> getTopLevel(Map<String,Integer> condition) throws BusinessException;
	
	public List<User> getTopActive(Map<String,Integer> condition) throws BusinessException;
	
	public List<User> getTopInvite(Map<String,Integer> condition) throws BusinessException;

	public List<User> getTopInviteUser(Map<String,Integer> condition) throws BusinessException;
	
	public void deleteUserAllInfo(Map<String,Object>condition)throws BusinessException;
	
	public void freedomUserById(Map<String,Object>condition)throws BusinessException;
	
	public List<User> queryOtherUsers(String gameId) throws BusinessException;
	
	
	public List<User> getTopIncome(Map<String,Integer> condition) throws BusinessException;
	 /**
	  * 校验用户名、密码
	  * @param mobileNumber
	  * @param password
	  * @return
	  * @throws BusinessException
	  */
	public boolean validateLogin(String mobileNumber,String password) throws BusinessException;
	/**
	 * 判断是否是第一次登录
	 * @param mobileNumber
	 * @return
	 * @throws BusinessException
	 */
	public boolean isFirstLogin(String mobileNumber) throws BusinessException;
	/**
	 * 更新密码
	 * @param id
	 * @param password
	 * @throws BusinessException
	 */
	public void updateUserPassword(String id,String password) throws BusinessException;
	public List<User> queryUserListByNumber(List<String> number) throws BusinessException;
	

	public int queryUserLoginLog(String userid) throws BusinessException;
	/**
	 * 根据用户名密码查找用户 for android client
	 * @param mobileNumber
	 * @param password 密码，md5加密之后值
	 * @throws BusinessException
	 */
	public User findUserByMoblieNumberAndPasswordNotCoded(String mobileNumber,
			String password) throws BusinessException;
	
	public User getUserRole(String Id) throws BusinessException;
	
	public void deleteUserAssRoleByUserId(String userId) throws BusinessException;
	
	public int findUserByNickName(String nickName ) throws BusinessException;
	

	public void updateUserInfoMobileNumber(User user,UserDetail userDetail) throws BusinessException;
	
	public void createSvip(User svip,UserDetail uesrDetail,int subAccount,String path,String prefix) throws BusinessException,IOException;
	

	public List<User> getSubsList(PaginationMore page ,String fatherid) throws BusinessException;
	

	public List<User> getVipList(PaginationMore page ,String fatherid) throws BusinessException;
	public List<User> getSvipUserList(String userid) throws BusinessException;

	
	public List<User> queryCashUserList(Map<String,Object> condition,PaginationMore page) throws BusinessException ;
	

	public int findUserByNickName(Map<String,Object> condition) throws BusinessException;
	
	public String obtionInviteCode();
	
	public List<User> querySvipUserList() throws BusinessException;
	public BigDecimal getUserTotalRecharge(Map<String,Object> map) throws BusinessException;
	
	public void updateSvipUserForInviteCode(User u)throws BusinessException;
	
	public BigDecimal getAllGameAmount(String mobilenumber) throws BusinessException;
	
	public BigDecimal getAllRechargeAmount(String userid) throws BusinessException;
	
	public BigDecimal getAllInviteAmount(String userid) throws BusinessException;
	
	public BigDecimal getAllVipAmount(String userid) throws BusinessException;

    public void addUser(User user, UserDetail userDetail) throws BusinessException;

    public int getUsersCountByInviteCodeFromOther(Map<String, Object> condition)
			throws BusinessException;
}
