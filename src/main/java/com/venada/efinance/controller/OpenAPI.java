package com.venada.efinance.controller;

import com.venada.efinance.business.*;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.exception.LessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.enumtype.LogTypeEnum;
import com.venada.efinance.pojo.*;
import com.venada.efinance.sms.SmsService;
import com.venada.efinance.util.SystemUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("openAPI")
public class OpenAPI {
	/** Logger */
	private static final Logger logger = LoggerFactory.getLogger(OpenAPI.class);
	@Autowired
	private GameBusiness gameBusiness;
	@Autowired
	private UserBusiness userBusiness;
	@Autowired
	private TransactionDetailsBusiness transactionDetailsBusiness;
	@Autowired
	private UserWalletBusiness userWalletBusiness;
	@Autowired
	private AdviceBusiness adviceBusiness;
	@Autowired
	private VersionBusiness versionBusiness;
	@Autowired
	private ActivityBusiness activityBusiness;

	@Autowired
	private WithdrawalRecordBussiness withdrawalRecordBussiness;
	  @Autowired
		private OperationLogBusiness opeartionLogBusiness ;
	  @Autowired
		private SmsService smsService;

	@RequestMapping("recommendList")
	public @ResponseBody
	List<GameForAPI> recommendList() {
		List<GameRecommend> list = new ArrayList<GameRecommend>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", 10);
		list = gameBusiness.queryGameRecommendAPI(map);

		List<GameForAPI> apiList = new ArrayList<GameForAPI>();
		for (GameRecommend game : list) {
			GameForAPI api = new GameForAPI();
			api.setId(game.getId());
			api.setClicks(game.getClicks());
			api.setDeposit(game.getDeposit());
			api.setGameName(game.getGameName());
			api.setPunish(game.getPunish());
			api.setRectangle(game.getRectangle());
			api.setReward(game.getReward());
			api.setSquare(game.getSquare());
			api.setType(game.getType());
			api.setTypeName(game.getTypeName());
			api.setIsMember(game.getIsMember());
			apiList.add(api);
		}
		return apiList;

	}
	
	@RequestMapping(value={"/getCheckCode"},method = {RequestMethod.GET})
	public @ResponseBody Object getCheckCode(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String mobileNumber = request.getParameter("mobileNumber");
		String yzm = request.getParameter("yzm");
		User user = userBusiness.findUserByMoblieNumber(mobileNumber);
		if (user != null) {
			resultMap.put("resultCode", 'f');
			resultMap.put("resultMsg", "用户已经存在,不能重复注册!");
			return resultMap;
		}
		smsService.sendSms(mobileNumber, "", "MB-2013121310",yzm);
		resultMap.put("resCode", 0);
		resultMap.put("resMsg", "成功");
		return resultMap; 
	}

	@RequestMapping("hotList")
	public @ResponseBody
	List<GameForAPI> HotList() {
		List<Game> list = new ArrayList<Game>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", 10);
		list = gameBusiness.queryGameForRank(map);
		List<GameForAPI> apiList = new ArrayList<GameForAPI>();
		for (Game game : list) {
			GameForAPI api = new GameForAPI();
			api.setId(game.getId());
			api.setClickNum(game.getClickNum());
			api.setClicks(game.getClicks());
			api.setDeposit(game.getDeposit());
			api.setGameName(game.getGameName());
			api.setPunish(game.getPunish());
			api.setRectangle(game.getRectangle());
			api.setReward(game.getReward());
			api.setSquare(game.getSquare());
			api.setType(game.getType());
			api.setTypeName(game.getTypeName());
			api.setIsMember(game.getIsMember());
			apiList.add(api);
		}
		return apiList;

	}

	@RequestMapping("playedList")
	public @ResponseBody
	List<GameForAPI> playedList(HttpServletRequest request) {
		List<Game> list = new ArrayList<Game>();
		Map<String, Object> map = new HashMap<String, Object>();
		String count = request.getParameter("count");
		if (!"".equals(count) && count != null) {
			map.put("count", Integer.parseInt(count));
		} else {
			map.put("count", 4);
		}

		list = gameBusiness.queryGamesByRand(map);
		List<GameForAPI> apiList = new ArrayList<GameForAPI>();
		for (Game game : list) {
			GameForAPI api = new GameForAPI();
			api.setId(game.getId());
			api.setClickNum(game.getClickNum());
			api.setClicks(game.getClicks());
			api.setDeposit(game.getDeposit());
			api.setGameName(game.getGameName());
			api.setPunish(game.getPunish());
			api.setRectangle(game.getRectangle());
			api.setReward(game.getReward());
			api.setSquare(game.getSquare());
			api.setType(game.getType());
			api.setTypeName(game.getTypeName());
			api.setIsMember(game.getIsMember());
			apiList.add(api);
		}
		return apiList;
	}

	@RequestMapping("gameUnionList")
	public @ResponseBody
	List<GameForAPI> gameUnionList() {
		List<Game> list = new ArrayList<Game>();
		list = gameBusiness.queryUnionGames();
		List<GameForAPI> apiList = new ArrayList<GameForAPI>();
		for (Game game : list) {
			GameForAPI api = new GameForAPI();
			api.setId(game.getId());
			api.setClickNum(game.getClickNum());
			api.setClicks(game.getClicks());
			api.setDeposit(game.getDeposit());
			api.setGameName(game.getGameName());
			api.setPunish(game.getPunish());
			api.setRectangle(game.getRectangle());
			api.setReward(game.getReward());
			api.setSquare(game.getSquare());
			api.setType(game.getType());
			api.setTypeName(game.getTypeName());
			api.setIsMember(game.getIsMember());
			apiList.add(api);
		}
		return apiList;

	}

	@RequestMapping("gameList")
	public @ResponseBody
	List<GameForAPI> gameList(HttpServletRequest request,HttpServletResponse response) {
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		String beginDeposit = request.getParameter("beginDeposit");
		String endDeposit = request.getParameter("endDeposit");
		String sortseq = request.getParameter("sortseq");
		String ismember = request.getParameter("ismember");
		int page = 1;
		int limitIndex = 0;
		String sort = request.getParameter("sort");
		int count = 0;
		if (!"".equals(request.getParameter("count"))
				&& request.getParameter("count") != null) {
			count = Integer.parseInt(request.getParameter("count"));
		} else {
			count = 4;
		}
		try {
			page = Integer.parseInt(request.getParameter("page"));
			limitIndex = (page - 1) * count;
		} catch (Exception e) {
			logger.error("页数转换为数字失败：" + request.getParameter("page"));
			return new ArrayList<GameForAPI>();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		if (!"".equals(keyword) && keyword != null) {
			keyword = "%" + SystemUtils.transfer(keyword) + "%";
			map.put("keyword", keyword);
		}
		map.put("sort2", sort);
		map.put("limitCount", count);
		map.put("limitIndex", limitIndex);
		map.put("beginDeposit", beginDeposit);
		map.put("endDeposit", endDeposit);
		if (sortseq != null && !"".equals(sortseq.trim())) {
			map.put("sortseq", sortseq);
		}
		if("1".equals(ismember)){
			map.put("ismember", 1);
		}
		List<Game> list = gameBusiness.queryGames(map);
		List<GameForAPI> apiList = new ArrayList<GameForAPI>();
		for (Game game : list) {
			GameForAPI api = new GameForAPI();
			api.setId(game.getId());
			api.setClickNum(game.getClickNum());
			api.setClicks(game.getClicks());
			api.setDeposit(game.getDeposit());
			api.setGameName(game.getGameName());
			api.setPunish(game.getPunish());
			api.setRectangle(game.getRectangle());
			api.setReward(game.getReward());
			api.setSquare(game.getSquare());
			api.setType(game.getType());
			api.setTypeName(game.getTypeName());
			api.setIsMember(game.getIsMember());
			api.setPlayers(game.getPlayers());
			apiList.add(api);
		}
		return apiList;

	}

	@RequestMapping("gameListForRank")
	public @ResponseBody
	List<GameForAPI> gameListForRank(HttpServletRequest request) {
		int page = 1;
		int limitIndex = 0;
		int count = 0;
		if (!"".equals(request.getParameter("count"))
				&& request.getParameter("count") != null) {
			count = Integer.parseInt(request.getParameter("count"));
		} else {
			count = 4;
		}
		try {
			page = Integer.parseInt(request.getParameter("page"));
			limitIndex = (page - 1) * count;
		} catch (Exception e) {
			logger.error("页数转换为数字失败：" + request.getParameter("page"));
			return new ArrayList<GameForAPI>();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("clickNum", "clickNum");
		map.put("limitCount", count);
		map.put("limitIndex", limitIndex);
		List<Game> list = gameBusiness.queryGames(map);
		List<GameForAPI> apiList = new ArrayList<GameForAPI>();
		for (Game game : list) {
			GameForAPI api = new GameForAPI();
			api.setId(game.getId());
			api.setClickNum(game.getClickNum());
			api.setClicks(game.getClicks());
			api.setDeposit(game.getDeposit());
			api.setGameName(game.getGameName());
			api.setPunish(game.getPunish());
			api.setRectangle(game.getRectangle());
			api.setReward(game.getReward());
			api.setSquare(game.getSquare());
			api.setType(game.getType());
			api.setTypeName(game.getTypeName());
			api.setIsMember(game.getIsMember());
			apiList.add(api);
		}
		return apiList;

	}

	// 玩过游戏的人还玩过哪些游戏
	@RequestMapping("otherGames")
	public @ResponseBody
	List<GameForAPI> otherGames(HttpServletRequest request) {
		List<GameRecommend> gameList = gameBusiness.queryGameRecommendInIndex();
		List<GameRecommend> list = new ArrayList<GameRecommend>();
		for (int i = 0; i < gameList.size(); i++) {
			if (i < 4) {
				list.add(gameList.get(i));
			}
		}
		List<GameForAPI> apiList = new ArrayList<GameForAPI>();
		for (GameRecommend game : list) {
			GameForAPI api = new GameForAPI();
			api.setId(game.getId());
			api.setClickNum(0);
			api.setClicks(game.getClicks());
			api.setDeposit(game.getDeposit());
			api.setGameName(game.getGameName());
			api.setPunish(game.getPunish());
			api.setRectangle(game.getRectangle());
			api.setReward(game.getReward());
			api.setSquare(game.getSquare());
			api.setType(game.getType());
			api.setTypeName(game.getTypeName());
			api.setIsMember(game.getIsMember());
			apiList.add(api);
		}
		return apiList;

	}

	// 任务明细
	@RequestMapping(value = "/taskDetailAPI", method = { RequestMethod.POST })
	public @ResponseBody
	Object taskDetailAPI(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String mobileNumber = request.getParameter("mobileNumber");
		String password = request.getParameter("password");
		User user = userBusiness.findUserByMoblieNumberAndPasswordNotCoded(
				mobileNumber, password);
		if (user == null) {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("resCode", 102);
			resultMap.put("resMsg", "用户不存在！");
			return resultMap;
		} else {
			int page = 1;
			int limitIndex = 0;
			int count = 0;
			if (!"".equals(request.getParameter("count"))
					&& request.getParameter("count") != null) {
				count = Integer.parseInt(request.getParameter("count"));
			} else {
				count = 15;
			}
			try {
				page = Integer.parseInt(request.getParameter("page"));
				limitIndex = (page - 1) * count;
			} catch (Exception e) {
				logger.error("页数转换为数字失败：" + request.getParameter("page"));
				return new ArrayList<Game>();
			}
			map.put("limitCount", count);
			map.put("limitIndex", limitIndex);
			String type = request.getParameter("type");
			if ("ing".equals(type)) {
				map.put("status", UserGameRelation.InprocessStatus);
			} else if ("over".equals(type)) {
				map.put("status", UserGameRelation.FinishedStatus);
			} else if ("record".equals(type)) {
				map.put("punishRecord", "punishRecord");
			}

			map.put("userId", user.getMobileNumber());
			List<UserGameRelation> list = gameBusiness
					.queryAllGameTaskDetails(map);
			return list;
		}

	}

	// 任务明细
	@RequestMapping(value = "/accountDetailAPI", method = { RequestMethod.POST })
	public @ResponseBody
	Object accountDetailAPI(HttpServletRequest request) {
		Map<String, Object> condition = new HashMap<String, Object>();
		String mobileNumber = request.getParameter("mobileNumber");
		String password = request.getParameter("password");
		User user = userBusiness.findUserByMoblieNumberAndPasswordNotCoded(
				mobileNumber, password);
		if (user == null) {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("resCode", 102);
			resultMap.put("resMsg", "用户不存在！");
			return resultMap;
		} else {
			int page = 1;
			int limitIndex = 0;
			int count = 0;
			if (!"".equals(request.getParameter("count"))
					&& request.getParameter("count") != null) {
				count = Integer.parseInt(request.getParameter("count"));
			} else {
				count = 15;
			}
			try {
				page = Integer.parseInt(request.getParameter("page"));
				limitIndex = (page - 1) * count;
			} catch (Exception e) {
				logger.error("页数转换为数字失败：" + request.getParameter("page"));
				return new ArrayList<Game>();
			}
			condition.put("limitCount", count);
			condition.put("limitIndex", limitIndex);
			condition.put("userid", user.getId());
			List<TransactionDetails> list = transactionDetailsBusiness
					.queryAllTransactionDetails(condition);
			return list;
		}

	}

	// 确认游戏
	@RequestMapping(value = "/receiveGame", method = RequestMethod.POST)
	public @ResponseBody
	Object receiveGame(HttpServletRequest request) throws BusinessException, ParseException {
		String mobileNumber = request.getParameter("mobileNumber");
		String password = request.getParameter("password");
		User user = userBusiness.findUserByMoblieNumberAndPasswordNotCoded(
				mobileNumber, password);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (user == null) {

			resultMap.put("resCode", 100);
			resultMap.put("resMsg", "用户不存在！");
			return resultMap;
		} else {
			int num = 1;
			try {
				num = Integer.parseInt(request.getParameter("num"));
			} catch (Exception e) {
				logger.error("确认游戏数量转换为数字失败：" + num);
				num = 1;
			}
			String gameId = request.getParameter("gameId");
			Game game = gameBusiness.getGameDetail(gameId);
			try {
				String[] res = gameBusiness.confirmPlay(num, user, game,
						request.getRemoteAddr());

				resultMap.put("resCode", 103);
				resultMap.put("resMsg", "成功");
				resultMap.put("banance", res[2]);
				OperationLog log = new OperationLog();
				log.setLogType(LogTypeEnum.GameConfirm.getIndex());
				log.setDataOld("");
				log.setDataNew("领取游戏:"+game.getGameName()+"扣除押金:"+res[1]+"余额:"+res[2]);
				log.setCreateBy(user.getId());
				log.setCreateTime(new Date());
				opeartionLogBusiness.addOperationLog(log);
				return resultMap;
                
			} catch (LessException e) {
				if ("0002".equals(e.getErrorCode())) {
					resultMap.put("resCode", 102);
					resultMap.put("resMsg", "该游戏为活动游戏只能领一次");
					return resultMap;
				}else if ("0003".equals(e.getErrorCode())){
					resultMap.put("resCode", 104);
					resultMap.put("resMsg", "非会员不能领取会员游戏");
					return resultMap;
				}else {
					resultMap.put("resCode", 101);
					resultMap.put("resMsg", "余额不足");
					return resultMap;
				}

			}
		}
	}

	// 查询余额
	@RequestMapping(value = "/queryBalance", method = RequestMethod.POST)
	public @ResponseBody
	Object queryBalance(HttpServletRequest request) {
		String mobileNumber = request.getParameter("mobileNumber");
		String password = request.getParameter("password");
		User user = userBusiness.findUserByMoblieNumberAndPasswordNotCoded(
				mobileNumber, password);
		if(user==null){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("resCode", 100);
			resultMap.put("resMsg", "用户不存在");
			return resultMap; 
		}
		UserWallet userWallet = userWalletBusiness.getUserWalletByUserId(user
				.getId());
		return userWallet.getAmount();
	}

	// 开始游戏
	@RequestMapping(value = "/playGame", method = RequestMethod.POST)
	public @ResponseBody
	Object playGame(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String ids = request.getParameter("ids");

			if (ids != null && !"".equals(ids)) {
				int re = gameBusiness.startPlay(ids);
				int count = gameBusiness.getUserGameRelationById(ids)
						.getPlaynum();
				// 判断是否完成任务
				// gameBusiness.finishTask(ids, request.getRemoteAddr());
				if (re == 0) {
					resultMap.put("resCode", 100);
					resultMap.put("resMsg", "成功");

				} else {
					resultMap.put("resCode", 101);
					resultMap.put("resMsg", "你今天已经点击过");
				}
				resultMap.put("count", count);
			}

		} catch (Exception e) {
			resultMap.put("resCode", 102);
			resultMap.put("resMsg", "失败");
			logger.error("跳转页面出错");
		}
		return resultMap;
	}

	// 开始游戏
	@RequestMapping(value = "/gameDesc", method = RequestMethod.POST)
	public @ResponseBody
	Object gameDesc(HttpServletRequest request) {

		String id = request.getParameter("id");
		GameForAPI ngame = new GameForAPI();
		if (id != null && !"".equals(id)) {
			Game game = gameBusiness.getGameDetail(id);

			ngame.setGameDescrip(game.getGameDescrip());
		}
		return ngame;

	}

	// 任务明细
	@RequestMapping(value = "/loginStatus", method = { RequestMethod.POST })
	public @ResponseBody
	Object loginStatus(HttpServletRequest request) {
		String mobile = request.getParameter("mobileNumber");
		String loginTime = request.getParameter("logintime");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<LoginStatus> list = gameBusiness.queryLoginStatus(mobile);

			if (list.size() > 0) {
				resultMap.put("resCode", 1);
				resultMap.put("resMsg", "重复号码");
			} else {
				LoginStatus status = new LoginStatus();
				status.setId(UUID.randomUUID().toString());
				status.setMobile(mobile);
				status.setLoginTime(loginTime);
				gameBusiness.addLoginStatus(status);
				resultMap.put("resCode", 0);
				resultMap.put("resMsg", "成功");
			}
		} catch (Exception e) {
			logger.error("loginStatus方法发生异常：" + e.getMessage());
			resultMap.put("resCode", 2);
			resultMap.put("resMsg", "服务器发生异常");
		}
		return resultMap;
	}

	// 任务明细
	@RequestMapping(value = "/userAdvice", method = { RequestMethod.POST })
	public @ResponseBody
	Object userAdvice(HttpServletRequest request) {
		String mobile = request.getParameter("mobileNumber");
		String str = request.getParameter("str");
		String name = request.getParameter("name");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Advice ad = new Advice();
			ad.setId(UUID.randomUUID().toString());
			ad.setCreateTime(sdf.parse(sdf.format(new Date())));
			ad.setContact(mobile);
			ad.setAdvice(str);
			ad.setName(name);
			adviceBusiness.saveAdvice(ad);
			resultMap.put("resCode", 0);
			resultMap.put("resMsg", "成功");
		} catch (Exception e) {
			logger.error("loginStatus方法发生异常：" + e.getMessage());
			resultMap.put("resCode", 2);
			resultMap.put("resMsg", "服务器发生异常");
		}
		return resultMap;
	}

	@RequestMapping(value = "/getLastApp")
	public @ResponseBody
	Version getLastApp() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Version version = new Version();
		try {
			version = versionBusiness.getLastApp();
		} catch (BusinessException e) {
			resultMap.put("resCode", 2);
			resultMap.put("resMsg", "服务器发生异常");
		}
		return version;
	}

//	@RequestMapping(value = "/updateApp")
	public @ResponseBody
	Version updateApp(int versionCode, String apkName) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Version version = new Version();
		try {
			version.setDownloadPath(apkName);
			version.setPublishTime(String.valueOf(new Date().getTime()));
			version.setVersionCode(versionCode);
			versionBusiness.addVersion(version);

		} catch (BusinessException e) {
			resultMap.put("resCode", 2);
			resultMap.put("resMsg", "服务器发生异常");
		}
		return version;
	}

	/**
	 * 为手机客户端准备的查询用户提现记录的API
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryT", method = { RequestMethod.POST })
	public @ResponseBody
	Object queryWithdrawalRecords(HttpServletRequest request) {
		List<WithdrawalRecord> withdrawalRecords = new ArrayList<WithdrawalRecord>();
		
		Object[] userCond = this.checkUser(request);
		User u = (User) userCond[1];
		if (u == null) {
			return userCond[0];
		}

		List<SimpleWithdrawalRecord> simpleWithdrawalRecords = new ArrayList<SimpleWithdrawalRecord>();
		Map<String, Object> cond = this.spliceCondtion(request);
		cond.put("userId", u.getId());
		
		try {
			withdrawalRecords = withdrawalRecordBussiness
					.queryWithDrawalRecordForApi(cond);

			for (WithdrawalRecord w : withdrawalRecords) {
				SimpleWithdrawalRecord t = new SimpleWithdrawalRecord();
				BeanUtils.copyProperties(t, w);
				simpleWithdrawalRecords.add(t);
			}
		} catch (Exception e) {
			logger.error("移动端接口处理提现记录出错,原因:{}", e.getMessage());
		}
		
		return simpleWithdrawalRecords;
	}

	/**
	 * 拼接请求参数
	 * 
	 * @param request
	 * @return
	 */
	private Map<String, Object> spliceCondtion(HttpServletRequest request) {
		Map<String, Object> cond = new HashMap<String, Object>();
		int page = 1;
		int limitIndex = 0;
		int count = 0;

		if (!"".equals(request.getParameter("count"))
				&& request.getParameter("count") != null) {
			count = Integer.parseInt(request.getParameter("count"));
		} else {
			count = 15;
		}

		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			logger.error("页数转换为数字失败：" + request.getParameter("page"));
			page = 1;
		}

		limitIndex = (page - 1) * count;

		cond.put("limitCount", count);
		cond.put("limitIndex", limitIndex);
		return cond;
	}

	/**
	 * 如果需要校验用户是否登录，统一调用该方法
	 * 
	 * @param request
	 * @return 注意对象数组的顺序
	 */
	private Object[] checkUser(HttpServletRequest request) {
		String mobileNumber = request.getParameter("mobileNumber");
		String password = request.getParameter("password");
		User user = userBusiness.findUserByMoblieNumberAndPasswordNotCoded(
				mobileNumber, password);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (user == null) {
			resultMap.put("resCode", 100);
			resultMap.put("resMsg", "用户不存在！");
		}
		return new Object[] { resultMap, user };
	}
	
	/**
	 * 活动列表查询接口
	 * 
	 * @param request
	 * @return 活动列表
	 */
	@RequestMapping(value = "/queryActivity", method = { RequestMethod.POST })
	public @ResponseBody List<Activity> activityList(HttpServletRequest request,PaginationMore page){
		List<Activity> list = new ArrayList<Activity>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", "ing");
		list = activityBusiness.queryActivityList(map);
		for (Activity activity : list) {
			activity.setPic(null);
		}
		return list;
	}
	
	@RequestMapping(value = "/queryTaskNum")
	public @ResponseBody Object  queryTaskNum(HttpServletRequest request){
		String mobileNumber = request.getParameter("mobileNumber");
		String password = request.getParameter("password");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		User user = userBusiness.findUserByMoblieNumberAndPasswordNotCoded(mobileNumber, password);
		if(user==null){		
			resultMap.put("resCode", 100);
			resultMap.put("resMsg", "用户不存在");
			return resultMap; 
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", UserGameRelation.InprocessStatus);
		map.put("mobileNumber", mobileNumber);
		int ingnum = gameBusiness.getTaskNum(map);
		resultMap.put("ingnum", ingnum);
		map.put("status", UserGameRelation.FinishedStatus);
		int finishednum = gameBusiness.getTaskNum(map);
		resultMap.put("finishednum", finishednum);
		return resultMap; 
	}
	
	@RequestMapping(value={"test"})
	public void test(HttpServletRequest request,HttpServletResponse response){
		OrderIdRecord orderIdRecord = new OrderIdRecord();
		orderIdRecord.setOrderId("1111");
		orderIdRecord.setCreateTime(new Date());
		orderIdRecord.setAmount(new BigDecimal(123.23));
		orderIdRecord.setUserId("123");
		userWalletBusiness.insertOrderIdRecord(orderIdRecord);
	}
}