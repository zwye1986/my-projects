package com.venada.efinance.business.impl;

import com.venada.efinance.business.*;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.exception.ServiceException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.*;
import com.venada.efinance.service.*;
import com.venada.efinance.util.DecimalUtil;
import com.venada.efinance.util.NameGenerator;
import com.venada.efinance.util.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service("userBusiness")
public class UserBusinessImpl implements UserBusiness {
	@Autowired
	private UserService userService;
	@Autowired
	private UserDetailService userDetailService;
	@Autowired
	private LoginLogService loginLogService;
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	@Autowired
	private UserWalletBusiness userWalletBusiness;
	@Autowired
	private ValidateCodeBusiness validateCodeBusiness;
	@Autowired
	private UserQuestionService questionService;
	@Autowired
	private UserDetailBusiness userDetailBusiness;
	@Autowired
	private AreaBusiness areaBusiness;
	@Autowired
	private UserGameRelationService userGameRelationService;

	@Override
	public User findUserById(String id) throws BusinessException {
		return (User) userService.getObject("findUserById", id);
	}

	@Override
	public User findUserByMoblieNumber(String mobileNumber)
			throws BusinessException {
		return (User) userService.getObject("findUserByMoblieNumber",
				mobileNumber);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class, ServiceException.class })
	public void updateUserByMobileNumber(User user, LoginLog loginLog)
			throws BusinessException {
		userService.updateObject("updateUserByMobileNumber", user);
		logUserLogin(loginLog);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class, ServiceException.class })
	public void addUser(User user, UserDetail userDetail,
			UserQuestion userQuestion) throws BusinessException {
		userService.saveObject("addUser", user);
		userDetailService.saveObject("addUserDetail", userDetail);
		questionService.saveObject("saveUserQuestion", userQuestion);
		userWalletBusiness.generateAccount(user.getId());
	}


	@Override
	public UserHis findUserHisByMoblieNumber(String mobileNumber)
			throws BusinessException {
		return (UserHis) userService.getObject("findUserHisByMoblieNumber",
				mobileNumber);
	}

	@Override
	public List<User> queryWealthTopList(int t) throws BusinessException {
		return userService.findObjects("queryWealthTopList", t);
	}

	@Override
	public void batchAddTopWealth(List<User> users) throws BusinessException {
		userService.batchAddObject("batchAddTopWealth", users);
	}


	@Override
	public List<User> queryActiveTopList(int t) throws BusinessException {
		return userService.findObjects("queryActiveTopList", t);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class, ServiceException.class })
	public void batchAddTopActive(List<User> users) throws BusinessException {
		userService.batchAddObject("batchAddTopActive", users);
	}

	@Override
	public List<User> queryLevelTopList(int t) throws BusinessException {
		return userService.findObjects("userService", t);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class, ServiceException.class })
	public void batchAddTopLevel(List<User> users) throws BusinessException {
		userService.batchAddObject("batchAddTopLevel", users);

	}

	@Override
	public List<User> getTopWealth(Map<String, Integer> condition)
			throws BusinessException {
		return userService.findObjects("getTopWealth", condition);
	}

	@Override
	public List<User> getTopLevel(Map<String, Integer> condition)
			throws BusinessException {
		return userService.findObjects("getTopLevel", condition);
	}

	@Override
	public List<User> getTopActive(Map<String, Integer> condition)
			throws BusinessException {
		return userService.findObjects("getTopActive", condition);
	}

	@Override
	public List<User> getTopIncome(Map<String, Integer> condition)
			throws BusinessException {
		return userService.findObjects("getTopIncome", condition);
	}

	@Override
	public List<User> getTopInvite(Map<String, Integer> condition)
			throws BusinessException {
		return userService.findObjects("getTopInvite", condition);
	}

	@Override
	public List<User> getTopInviteUser(Map<String, Integer> condition)
			throws BusinessException {
		return userService.findObjects("getTopInviteUser", condition);
	}

	@Override
	public boolean validateLogin(String mobileNumber, String password)
			throws BusinessException {
		if (mobileNumber != null
				&& !"".equals(mobileNumber.replaceAll("\\s*", ""))
				&& password != null
				&& !"".equals(password.replaceAll("\\s*", ""))) {
			User user = findUserByMoblieNumber(mobileNumber);
			if (user != null
					&& md5PasswordEncoder.encodePassword(password, null)
							.equalsIgnoreCase(user.getPassword())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isFirstLogin(String mobileNumber) throws BusinessException {
		if (mobileNumber != null
				&& !"".equals(mobileNumber.replaceAll("\\s*", ""))) {
			User user = findUserByMoblieNumber(mobileNumber);
			List<ValidateCode> validateCodeList = validateCodeBusiness
					.queryLastestCodeByMobileNumber(mobileNumber);
			if (user == null && validateCodeList.size() > 0) {
				return true;
			}
		}
		return false;
	}


	@Override
	public void updateUserPassword(String id, String password)
			throws BusinessException {
		Map map = new HashMap();
		map.put("id", id);
		map.put("password", password);
		userService.updateObject("updateUserPassword", map);

	}

	@Override
	public List<User> queryUserListByNumber(List<String> number)
			throws BusinessException {
		List<User> userList = userService.findObjects("queryUserListByNumber",
				number);
		return userList;
	}

	private void logUserLogin(LoginLog loginLog) throws BusinessException {
		loginLogService.saveObject("logUserLogin", loginLog);
	}

	@Override
	public int queryUserLoginLog(String userid) throws BusinessException {
		return (Integer) loginLogService.getObject("queryUserLoginLog", userid);
	}

	@Override
	public void updateUserByMobileNumber(User user) throws BusinessException {
		userService.updateObject("updateUserByMobileNumber", user);
	}

	@Override
	public User findUserByMoblieNumberAndPasswordNotCoded(String mobileNumber,
			String password) throws BusinessException {

		Map map = new HashMap();
		map.put("mobileNumber", mobileNumber);
		map.put("password", password);
		return (User) userService.getObject(
				"findUserByMoblieNumberAndPassword", map);

	}

	@Override
	@Transactional(rollbackFor = { Exception.class, ServiceException.class })
	public void complementUserDetail(User user, UserDetail userDetail)
			throws BusinessException {
		userService.updateObject("updateUserByMobileNumber", user);
		userDetailService.updateObject("updateUserDetailByMobileNumber",
				userDetail);
	}

	@Override
	public List<User> listAllUsers(PaginationMore page,
			Map<String, Object> condition) throws BusinessException {
		page.setTotalRows(getUsersCount(condition));
		page.repaginate();
		return userService.selectList("listAllUser", condition, page);
	}

	private int getUsersCount(Map<String, Object> condition)
			throws BusinessException {
		return (Integer) userService.getObject("getUsersCount", condition);
	}

	@Override
	public User getUserRole(String Id) throws BusinessException {
		return (User) userService.getObject("getUserRole", Id);
	}

	@Override
	public void deleteUserAssRoleByUserId(String userId)
			throws BusinessException {
		userService.deleteObject("deleteUserAssRoleByUserId", userId);
	}

	@Override
	public User findUserByMoblieNumberByLogin(String mobileNumber)
			throws BusinessException {
		return (User) userService.getObject("findUserByMoblieNumberByLogin",
				mobileNumber);
	}

	@Override
	public User findUserByInviteCode(String inviteCode)
			throws BusinessException {
		return (User) userService.getObject("findUserByInviteCode", inviteCode);
	}

	@Override
	public List<User> listUserByInviteCodeFromOther(PaginationMore page,
			Map<String, Object> condition) throws BusinessException {
		page.setTotalRows(getUsersCountByInviteCodeFromOther(condition));
		page.repaginate();
		return userService.selectList("listUserByInviteCodeFromOther",
				condition, page);
	}

	@Override
	public List<User> listUserByInviteCodeFromOtherMobilePhone(
			Map<String, Object> condition) throws BusinessException {
		return userService.findObjects(
				"listUserByInviteCodeFromOtherMobilePhone", condition);
	}

	@Override
	public List<User> listUserByInviteCodeFromOther(
			Map<String, Object> condition) throws BusinessException {
		return userService.findObjects("listUserByInviteCodeFromOther",
				condition);
	}
	
	

	@Override
	public int findUserByNickName(String nickName) throws BusinessException {
		return (Integer) userService.getObject("findUserByNickName", nickName);
	}
	
	@Override
	public int findUserByNickName(Map<String,Object> condition) throws BusinessException {
		return (Integer) userService.getObject("findUserByCondition", condition);
	}


	@Override
	@Transactional(rollbackFor = { Exception.class, ServiceException.class })
	public void updateUserInfoMobileNumber(User user, UserDetail userDetail)
			throws BusinessException {
		updateUserByMobileNumber(user);
		userDetailBusiness.updateUserDetailByMobileNumber(userDetail);
	}

	@Override
	public void deleteUserAllInfo(Map<String, Object> condition)
			throws BusinessException {
		userService.updateObject("deleteUserAllInfo", condition);
	}
	
	
	@Override
	public void freedomUserById(Map<String, Object> condition)
			throws BusinessException {
		userService.updateObject("freedomUserById", condition);
	}
	

	@Override
	@Transactional(rollbackFor = { Exception.class, ServiceException.class })
	public void createSvip(User svip, UserDetail userDetail, int subAccount,String path,String prefix)
			throws BusinessException,IOException {
		UserQuestion userQuestion = new UserQuestion();
		userQuestion.setId(UUID.randomUUID().toString());
		userQuestion.setUserid(svip.getId());
		userQuestion.setQuestionId("101");
		userQuestion.setAnswer("123");
		Area area = areaBusiness.getAreaById(userDetail.getLiveArea());
		String mobileNumber = "";
		if(prefix == null || "".equals(prefix.trim())){
			mobileNumber = generateNoRepeatAccount(area.getCode());
		}else{
			mobileNumber = generateNoRepeatAccount(prefix);
		}
		svip.setMobileNumber(mobileNumber);
		userDetail.setMobileNumber(mobileNumber);
		userDetail.setUserid(svip.getId());
		addUser(svip, userDetail, userQuestion);

		for (int i = 0; i < subAccount; i++) {
			User subVip = new User();
			// 设置userid
			subVip.setId(String.valueOf(UUID.randomUUID()));
			subVip.setType(2);
			subVip.setStatus(1);
			subVip.setInviteCodeSelf(this.obtionInviteCode());
			UserDetail subVipDetail = new UserDetail();
			// userDetail id
			subVipDetail.setId(String.valueOf(UUID.randomUUID()));
			subVipDetail.setUserid(subVip.getId());
			// 父id
			subVip.setFatherid(svip.getId());
			// 昵称
			String nickName = generateNoRepeatNickName(path);
			subVip.setNickName(nickName);

			String account = "";
			
			if(prefix == null || "".equals(prefix.trim())){
				account = generateNoRepeatAccount(area.getCode());
			}else{
				account = generateNoRepeatAccount(prefix);
			}
			
			subVip.setMobileNumber(account);
			subVipDetail.setMobileNumber(account);
			// 生成密码
			String vipInitPassword = SystemUtils.randomCheckcode("1234567890",
					6);
			subVip.setVipInitPassword(vipInitPassword);
			subVip.setPassword(md5PasswordEncoder.encodePassword(
					vipInitPassword, null));
			subVip.setSvipRate(svip.getSvipRate());
			UserQuestion subUserQuestion = new UserQuestion();
			subUserQuestion.setId(UUID.randomUUID().toString());
			subUserQuestion.setUserid(subVip.getId());
			subUserQuestion.setQuestionId("101");
			subUserQuestion.setAnswer("123");
			addUser(subVip, subVipDetail, subUserQuestion);
			
		}
	}

	private String generateNoRepeatNickName(String path) throws IOException{
		NameGenerator nameGenerator = new NameGenerator(path+"/nameOrin.txt");
		Random random = new Random();
		String nickName = nameGenerator.compose(random.nextInt(6) + 2);
		int c = findUserByNickName(nickName);
		if (c > 0) {
			nickName = generateNoRepeatNickName(path);
		}
		return nickName;
	}
	
	private String generateNoRepeatAccount(String code){
		String account = code + SystemUtils.randomCheckcode("1234567890", 5);
		if(findUserByMoblieNumber(account) != null){
			account = generateNoRepeatAccount(code);
		}
		return account;
	}

	private int getSubsCount(String fatherid) throws BusinessException {
		
		return (Integer) userService.getObject("getSubsCount", fatherid);
	}

	@Override
	public List<User> getSubsList(PaginationMore page ,String fatherid) throws BusinessException {
		page.setTotalRows(getSubsCount(fatherid));
		page.repaginate();
		Map<String,Object> conditions = new HashMap<String,Object>();
		conditions.put("fatherid", fatherid);
		return userService.selectList("getSubsList", conditions, page);
	}

	private int getVipListCount(String fatherid) throws BusinessException {
		return (Integer) userService.getObject("getVipListCount", fatherid);
	}

	@Override
	public List<User> getVipList(PaginationMore page ,String fatherid) throws BusinessException {
		page.setTotalRows(getVipListCount(fatherid));
		page.repaginate();
		Map<String,Object> conditions = new HashMap<String,Object>();
		conditions.put("fatherid", fatherid);
		return userService.selectList("getVipList", conditions, page);
	}

	@Override
	public List<User> getSvipUserList(String userid) throws BusinessException {
		return userService.findObjects("getSvipUserList", userid);
	}

	@Override
	public List<User> queryCashUserList(Map<String, Object> condition,
			PaginationMore page) throws BusinessException {
			page.setTotalRows(getCashUserCount(condition));
			page.repaginate();
		    List<User> list = userService.selectList("queryCashUser", condition, page);
		    return list;
	}

	private int getCashUserCount(Map<String, Object> condition)
			throws BusinessException {
		int count = (Integer) userService.getObject("getCashUserCount", condition);
		return count;
	}

	/**
	 * 递归，先随机生成6位密码，在查询数据库中是否有重复。有重新生成
	 * 随着用户增长，性能会有影响
	 * @return
	 */
	@Override
	public String obtionInviteCode(){
		String code="000000";
		code=DecimalUtil.getNumber6FromRandom();
		User user=findUserByInviteCode(code);
		if(user!=null){
			code=obtionInviteCode();
		}
		return code;
	}

	@Override
	public List<User> querySvipUserList() throws BusinessException {
		return userService.findObjects("querySvipUserList",null);
	}

	@Override
	public void updateSvipUserForInviteCode(User u) throws BusinessException {
		userService.updateObject("updateSvipUserForInviteCode", u);
	}

	@Override
	public BigDecimal getUserTotalRecharge(Map<String, Object> map)
			throws BusinessException {
		
		return (BigDecimal) userService.getObject("getUserTotalRecharge", map);
	}

	@Override
	public BigDecimal getAllGameAmount(String mobilenumber)
			throws BusinessException {
		return (BigDecimal) userGameRelationService.getObject("getAllGameAmount", mobilenumber);
	}

	@Override
	public BigDecimal getAllRechargeAmount(String userid)
			throws BusinessException {
		return (BigDecimal) userGameRelationService.getObject("getAllRechargeAmount", userid);
	}
	
	@Override
	public BigDecimal getAllInviteAmount(String userid)
			throws BusinessException {
		return (BigDecimal) userGameRelationService.getObject("getAllInviteAmount", userid);
	}
	
	@Override
	public BigDecimal getAllVipAmount(String userid)
			throws BusinessException {
		return (BigDecimal) userGameRelationService.getObject("getAllVipAmount", userid);
	}

    @Override
    @Transactional(rollbackFor = {Exception.class,BusinessException.class})
    public void addUser(User user, UserDetail userDetail)
            throws BusinessException {
        userService.saveObject("addUser", user);
        userDetailService.saveObject("addUserDetail", userDetail);
        userWalletBusiness.generateAccount(user.getId());
    }

	@Override
	public List<User> queryOtherUsers(String gameId) throws BusinessException {
		return userService.findObjects("queryOtherUsers", gameId);
	}

	@Override
	public int getUsersCountByInviteCodeFromOther(Map<String, Object> condition)
			throws BusinessException {
		return (Integer) userService.getObject(
				"getUsersCountByInviteCodeFromOther", condition);
	}

	
}
