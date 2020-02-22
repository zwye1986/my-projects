package com.venada.efinance.business.impl;

import com.venada.efinance.business.*;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.exception.LessException;
import com.venada.efinance.common.exception.ServiceException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.*;
import com.venada.efinance.security.SpringSecurityUtil;
import com.venada.efinance.service.*;
import com.venada.efinance.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/***
 * 
 * @author xupei
 * 
 */
@Service("gameBusiness")
public class GameBusinessImpl implements GameBusiness {
	private static final Logger logger = LoggerFactory
			.getLogger(GameBusinessImpl.class);
	@Autowired
	private GameService gameService;
	@Autowired
	private GameLogService gameLogService;
	@Autowired
	private GameRankService gameRankService;
	@Autowired
	private GameTypeService gameTypeService;
	@Autowired
	private UserGameRelationService userGameRelationService;
	@Autowired
	private GamePicService gamePicService;
	@Autowired
	private GameRecommendService gameRecommendService;
	@Autowired
	private UserWalletBusiness userWalletBusiness;
	@Autowired
	private UserGameJobViewService userGameJobViewService;
	@Autowired
	private LoginStatusService loginStatusService;
	@Autowired
	private UserBusiness userBusiness;
	@Autowired
	private UserService userService;
	@Autowired
	private InviteBenefitBusiness inviteBenefitBusinessImp;
	@Autowired
	private  SystemConfigBusiness systemConfigBusinessImpl;
	@Autowired
	private UserObtainCreditsBusiness userObtainCreditsBusinessImpl;
	@Autowired
	private VersionService versionService;
	@Autowired
	private SystemConfigBusiness systemConfigBusiness;
	@Autowired
	private SecurityCenterBusiness securityCenterBusiness;
	@Autowired
	private WeixinUserBusiness weixinUserBusiness;

	public void addGame(Game obj) throws BusinessException {
		try {
			gameService.saveObject("addGame", obj);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"addGame(" + obj + ")方法出错！\t", e.getMessage() });
		}
	}

	public void addGameRank(GameRank obj) throws BusinessException {
		try {
			gameService.saveObject("addGameRank", obj);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"addGameRank(" + obj + ")方法出错！\t", e.getMessage() });
		}

	}

	@Override
	public String addGameType(GameType obj) throws BusinessException {
		try {
			String typename = obj.getTypeName();
			if ("".equals(typename) || typename == null) {
				typename = Game.GAMETYPE_OTHER;
				obj.setTypeName(typename);
			}
			GameType gameType = this.getGameType(obj);
			if (gameType == null) {
				gameService.saveObject("addGameType", obj);
				return obj.getId();
			} else {
				return gameType.getId();
			}
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"addGameType(" + obj + ")方法出错！\t", e.getMessage() });
		}
	}

	/**
	 * 
	 * @param typename
	 *            类别名称
	 * @return GameType
	 * @throws BusinessException
	 */
	@Override
	public GameType getGameType(GameType obj) throws BusinessException {
		try {
			return (GameType) gameService.getObject("getGameType", obj);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"addGameType(" + obj + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public GamePolicy getGamePolicyByGameId(String gameId)
			throws BusinessException {
		try {
			return (GamePolicy) gameService.getObject("getGamePolicy", gameId);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"getGamePolicyByGameId(" + gameId + ")方法出错！\t",
					e.getMessage() });
		}
	}

	public void addGameByJob(Game obj) throws BusinessException {
		try {
			Game game = this.getGame(obj.getGameName());
			if (game == null) {
				this.addGame(obj);
			}

			// 插入游戏策略表数据
			GamePolicy policy = (GamePolicy) gameService.getObject(
					"getGamePolicy", obj.getId());
			if (policy == null) {
				policy = new GamePolicy();
				policy.setId(UUID.randomUUID().toString());
				policy.setClicks(0);
				policy.setDeposit(0); // 押金
				policy.setGameId(obj.getId());
				policy.setPunish(0);
				policy.setReward(0);
				gameService.saveObject("addGamePolicy", policy);
			}
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"addGameByJob(" + obj + ")方法出错！\t", e.getMessage() });
		}
	}

	public void addGameRankByJob(GameRank obj) throws BusinessException {
		try {
			GameRank gameRank = this.getGameRank(obj.getGameName());
			if (gameRank == null) {
				this.addGameRank(obj);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"addGameRankByJob(" + obj + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public Game getGame(String gamename) throws BusinessException {

		try {
			return (Game) gameService.getObject("getGameByName", gamename);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"getGame(" + gamename + ")方法出错！\t", e.getMessage() });
		}

	}

	@Override
	public Game getGameById(String id) throws BusinessException {

		try {
			return (Game) gameService.getObject("getGameById", id);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"getGameById(" + id + ")方法出错！\t", e.getMessage() });
		}

	}

	@Override
	public GameRank getGameRank(String gamename) throws BusinessException {
		try {
			return (GameRank) gameService.getObject("getGameRankByName",
					gamename);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"getGameRank(" + gamename + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public GameRecommend getGameRecommend(String gamename)
			throws BusinessException {
		try {
			return (GameRecommend) gameService.getObject(
					"getGameRecommendByName", gamename);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002",
					new String[] { "getGameRecommend(" + gamename + ")方法出错！\t",
							e.getMessage() });
		}
	}

	@Override
	public void delGame(Map<String, Object> map) throws BusinessException {
		try {
			gameService.deleteObject("deleteGame", map);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"delGame(" + map + ")方法出错！\t", e.getMessage() });
		}
	}
	
	@Override
	public void delVersionById(String id) throws BusinessException {
		try {
			versionService.deleteObject("deleteVersionById", id);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"delVersionById(" + id + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public void delGameRank(Map<String, Object> map) throws BusinessException {
		try {
			gameService.deleteObject("deleteGameRank", map);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"delGameRank(" + map + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public void addGameRecommendByJob(GameRecommend obj)
			throws BusinessException {
		try {
			GameRecommend gameRecommend = this.getGameRecommend(obj
					.getGameName());
			if (gameRecommend == null) {
				this.addGameRecommend(obj);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002",
					new String[] { "addGameRecommendByJob(" + obj + ")方法出错！\t",
							e.getMessage() });
		}
	}

	@Override
	public void addGameRecommend(GameRecommend obj) throws BusinessException {
		try {
			gameService.saveObject("addGameRecommend", obj);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"addGameRecommend(" + obj + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public void delGameRecommend(Map<String, Object> map)
			throws BusinessException {
		try {
			gameService.deleteObject("deleteGameRecommend", map);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"delGameRecommend(" + map + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public List<GameRank> queryGameRanks(Map<String, Object> map)
			throws BusinessException {
		try {
			List<GameRank> list = gameRankService.findObjects("queryGameRanks",
					map);
			return list;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"queryGameRanks(" + map + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public int getGamePlayCounts(String gameId) throws BusinessException {
		try {
			return (Integer) userGameRelationService.getObject(
					"getGamePlayCounts", gameId);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002",
					new String[] { "getGamePlayCounts(" + gameId + ")方法出错！\t",
							e.getMessage() });
		}
	}

	@Override
	public List<Game> queryGames(Map<String, Object> map)
			throws BusinessException {
		try {
			List<Game> list = gameService.findObjects("queryGames", map);
			return list;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"queryGames(" + map + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public int getGamesCounts(Map<String, Object> map) throws BusinessException {
		try {
			int gamesCounts = (Integer) gameService.getObject("getGamesCounts",
					map);
			return gamesCounts;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"getGamesCounts(" + map + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public int getRecommendGamesCounts(Map<String, Object> map)
			throws BusinessException {
		try {
			int gamesCounts = (Integer) gameRecommendService.getObject(
					"getRecommendGamesCounts", map);
			return gamesCounts;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"getRecommendGamesCounts(" + map + ")方法出错！\t",
					e.getMessage() });
		}
	}

	@Override
	public void delGameById(String id) throws BusinessException {
		try {
			gameService.deleteObject("delGameById", id);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"delGameById(" + id + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public Game getGameDetail(String id) throws BusinessException {
		try {
			Game game = (Game) gameService.getObject("getGameDetail", id);
			return game;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"getGameDetail(" + id + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public void addUserGameRelationList(List<UserGameRelation> list)
			throws BusinessException {
		try {
			userGameRelationService.saveObject("addUserGameRelationList", list);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002",
					new String[] { "addUserGameRelation(" + list + ")方法出错！\t",
							e.getMessage() });
		}
	}

	@Override
	public void addGamePic(GamePic gamePic) throws BusinessException {
		try {
			gamePicService.saveObject("addGamePic", gamePic);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"addGamePic(" + gamePic + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public void delGamePic(Map<String, Object> map) throws BusinessException {
		try {
			gamePicService.deleteObject("delGamePic", map);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"delGamePic(" + map + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public GamePic getGamePic(String id) throws BusinessException {
		try {
			return (GamePic) gamePicService.getObject("getGamePic", id);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"getGamePic(" + id + ")方法出错！\t", e.getMessage() });
		}
	}

	/**
	 * 首页上左侧查询热门游戏
	 */
	@Override
	public List<GameRank> queryGameRanksInIndex() throws BusinessException {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("category", Game.FLASH_GAME);

			List<GameRank> list = gameRankService.findObjects("queryGameRanks",
					map);
			return list;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"queryGameRanksInIndex()方法出错！\t", e.getMessage() });
		}
	}

	/**
	 * 首页上推荐的5个游戏
	 */
	@Override
	public List<GameRecommend> queryGameRecommendInIndex()
			throws BusinessException {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("category", Game.WEB_GAME);
			List<GameRecommend> list = gameRecommendService.findObjects(
					"queryGameRecommend", map);
			return list;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"queryGameRecommendInIndex()方法出错！\t", e.getMessage() });
		}
	}
	
	public List<GameRecommend> queryGameRecommendByRand()
			throws BusinessException {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("category", Game.WEB_GAME);
			List<GameRecommend> list = gameRecommendService.findObjects(
					"queryGameRecommendByRand", map);
			return list;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"queryGameRecommendInIndex()方法出错！\t", e.getMessage() });
		}
	}

	/**
	 * 搜索无结果游戏推荐
	 */
	@Override
	public List<GameRecommend> queryGameRecommendInSearch()
			throws BusinessException {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("category", Game.WEB_GAME);
			map.put("count", 15);
			List<GameRecommend> list = gameRecommendService.findObjects(
					"queryGameRecommend", map);
			return list;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"queryGameRecommendInIndex()方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public List<UserGameRelation> queryUserGameRelationList(
			Map<String, Object> map) throws BusinessException {
		try {
			List<UserGameRelation> userGameRelationList = userGameRelationService
					.findObjects("queryUserGameRelationList", map);
			return userGameRelationList;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"queryUserGameRelationList(" + map + ")方法出错！\t",
					e.getMessage() });
		}
	}

	@Override
	public List<Game> queryGamesByIdList(List<String> list)
			throws BusinessException {
		try {
			List<Game> gameList = gameService.findObjects("queryGamesByIdList",
					list);
			return gameList;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"queryGamesByIdList(" + list + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public void addGameLogList(List<GameLog> list) throws BusinessException {
		try {
			gameLogService.saveObject("addGameLogList", list);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"addGameLogList(" + list + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public List<GameLog> queryGameLog(Map<String, Object> map)
			throws BusinessException {
		try {
			return gameLogService.findObjects("queryGameLog", map);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"queryGameLog(" + map + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public List<UserGameRelation> queryAllGameTaskDetails(
			Map<String, Object> condition, PaginationMore page)
			throws BusinessException {
		try {
			page.setTotalRows(getAllGameTaskDetailsCount(condition));
			page.repaginate();
//			return userGameRelationService.selectList(
//					"queryAllGameTaskDetails", condition, page);
		    List<UserGameRelation> list = userGameRelationService.selectList("queryAllGameTaskDetails", condition, page);
		  
		    return list;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"queryAllGameTaskDetails(" + condition + "," + page
							+ ")方法出错！\t", e.getMessage() });
		}
	}
	
	@Override
	public List<UserGameRelation> queryNewAllGameTaskDetails(
			Map<String, Object> condition)
			throws BusinessException {
		try {
		
		    List<UserGameRelation> list = userGameRelationService.findObjects("queryAllGameTaskDetails", condition);
		  
		    return list;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"queryAllGameTaskDetails(" + condition + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public List<UserGameRelation> queryAllGameTaskDetails(
			Map<String, Object> condition) throws BusinessException {
		try {
			List<UserGameRelation> list = userGameRelationService.findObjects("queryAllGameTaskDetails", condition);
//			   for(int i = 0;i<list.size();i++){
//			    	UserGameRelation relation = list.get(i);
//			    	int playnum = (Integer) gameLogService.getObject("queryGameLogNum", relation.getId());
//			    	relation.setPlaynum(playnum);
//			    }
		
			return list;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"queryAllGameTaskDetails(" + condition + ")方法出错！\t",
					e.getMessage() });
		}
	}

	@Override
	public int getAllGameTaskDetailsCount(Map<String, Object> condition)
			throws BusinessException {
		try {
			int count = (Integer) userGameRelationService.getObject("getAllGameTaskDetailsCount", condition);
			return count;
			
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"getAllGameTaskDetailsCount(" + condition + ")方法出错！\t",
					e.getMessage() });
		}
	}

	@Override
	public List<Game> queryGames(Map<String, Object> condition,
			PaginationMore page) throws BusinessException {
		try {
			page.setTotalRows(getGamesCounts(condition));
			page.setPageSize(10);
			page.repaginate();
			return gameService.selectList("queryGamesByAdmin", condition, page);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"queryGames(" + condition + "," + page + ")方法出错！\t",
					e.getMessage() });
		}
	}

	@Override
	public List<GameType> queryAllGameType(Map<String, Object> map)
			throws BusinessException {
		try {
			return gameTypeService.findObjects("queryAllGameType", map);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"queryAllGameType(" + map + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public void updateGame(Game game) throws BusinessException {
		try {
			gameService.updateObject("updateGame", game);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"updateGame(" + game + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public void addGamePolicy(GamePolicy obj) throws BusinessException {
		try {
			gameService.saveObject("addGamePolicy", obj);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"addGamePolicy(" + obj + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public void updateGamePolicy(GamePolicy obj) throws BusinessException {
		try {
			gameService.saveObject("updateGamePolicy", obj);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"updateGamePolicy(" + obj + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public void updateUserGameRelation(UserGameRelation userGameRelation)
			throws BusinessException {
		try {
			userGameRelationService.updateObject("updateUserGameRelation",
					userGameRelation);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"updateUserGameRelation(" + userGameRelation + ")方法出错！\t",
					e.getMessage() });
		}
	}

	@Override
	public List<GamePic> queryGamePics(Map<String, Object> map)
			throws BusinessException {
		try {
			return gamePicService.findObjects("queryGamePic", map);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"queryGamePics(" + map + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public GamePolicy getGamePolicyByRelationId(String relationId)
			throws BusinessException {
		try {
			return (GamePolicy) gameService.getObject(
					"getGamePolicyByRelationId", relationId);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"getGamePolicyByRelationId(" + relationId + ")方法出错！\t",
					e.getMessage() });
		}
	}

	@Override
	public UserGameRelation getUserGameRelationById(String id)
			throws BusinessException {
		try {
			return (UserGameRelation) userGameRelationService.getObject(
					"getUserGameRelationById", id);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"getUserGameRelationById(" + id + ")方法出错！\t",
					e.getMessage() });
		}
	}
	
	@Override
	public List<UserGameRelation> queryUserGameRelationByIds(List<String> ids)
			throws BusinessException {
		try {
			return userGameRelationService.findObjects("queryUserGameRelationByIds", ids);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"queryUserGameRelationByIds(" + ids + ")方法出错！\t",
					e.getMessage() });
		}
	}
	
	public int getRestNum(String seq) throws BusinessException,LessException, ParseException{
		SystemConfig systemConfig = null;
		if("1".equals(seq)){
			systemConfig = systemConfigBusiness.getSystemConfig("120");
		}else if("2".equals(seq)){
			systemConfig = systemConfigBusiness.getSystemConfig("121");
		}else if("3".equals(seq)){
			systemConfig = systemConfigBusiness.getSystemConfig("122");
		}
		if(systemConfig != null){
			int rest = getChristmasGameRest(systemConfig.getParamValue());
			return rest;
		}else{
			return 0;
		}
		
	}


	@Transactional(rollbackFor = { Exception.class, ServiceException.class })
	public String[] confirmPlay(int num, User user, Game game, String addr)
			throws BusinessException, LessException, ParseException {
		logger.info("用户："+user.getMobileNumber()+"开始领取游戏，游戏ID为："+game.getId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String[] str = new String[3];
		List<UserGameRelation> list = new ArrayList<UserGameRelation>();
		BigDecimal banance = null;
		int deposit = 0;
		String ids = "";
		
		//判断是否领取过活动游戏
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("gameId","953b65d9-a322-4b57-abc9-33137b8c346c");
		map.put("userId",user.getMobileNumber());
		
		List<UserGameRelation> oldlist = this.queryUserGameRelationList(map);
		if( (oldlist.size()>0 && "953b65d9-a322-4b57-abc9-33137b8c346c".equals(game.getId())) || ("953b65d9-a322-4b57-abc9-33137b8c346c".equals(game.getId()) && num > 1)){
			banance = new BigDecimal(-2);//活动游戏领了多次
			throw new LessException("0002", new String[] { "领取错误",
					"活动游戏领取多次" });
		}
		
		if (!securityCenterBusiness.isOpen(user.getId()) && game.getIsMember() == 1) {		
			throw new LessException("0003", new String[] { "领取错误",
			"非会员不能领取会员游戏" });    //非会员不能领取会员游戏
		}
		
//		if("e099b7cf-6876-46c2-b466-fe523642ca31".equals(game.getId()) || "33b0d68d-b7be-4f16-b3cc-58fc91fb2d21".equals(game.getId())){
//			boolean flag = weixinUserBusiness.isOAuthByUserid(user.getId());
//			if(flag ==false){
//				throw new LessException("0014", new String[] { "系统错误",
//				"未绑定微信" }); 
//			}
//			
//			String time = "2014-10-08 00:00:00";
//			
//			Date now = new Date();
//			Date endtime = DateUtils.parseDate(time, "yyyy-MM-dd HH:mm:ss");
//			long n = now.getTime();
//			long max = endtime.getTime();
//			if(n>max){
//				throw new LessException("0015", new String[] { "系统错误",
//				"国庆活动任务时间已过!" }); 
//			}
//			
//		}
		
//		if(num > 20 ){
//			throw new LessException("0008", new String[] { "领取错误",
//			"您一次领取游戏过多，每次最多只能领取20个。" });
//		}
		
		SystemConfig systemConfig1 = systemConfigBusiness.getSystemConfig("120");
		SystemConfig systemConfig2 = systemConfigBusiness.getSystemConfig("121");
		SystemConfig systemConfig3 = systemConfigBusiness.getSystemConfig("122");

		
		
		if(systemConfig1 != null && systemConfig1.getParamValue().equals(game.getId())){
			//判断圣诞专属游戏是否可以被领取
			int b = isChristmasGameAvailable(num,"2015-02-09 00:00:00","2015-02-18 23:59:59",systemConfig1.getParamValue());
			if(b == 1){
				final  Integer s = 0;
				synchronized (s) {
				int rest = getChristmasGameRest(systemConfig1.getParamValue());
				if(rest < num){
					throw new LessException("0004", new String[] { "领取错误",
					"您领取游戏超过了限量！" });     
				}
				
				rest = rest - num;
				Map<String,Object> restMap = new HashMap<String,Object>();
				restMap.put("rest", rest);
				restMap.put("id", systemConfig1.getParamValue());
				gameService.updateObject("updateChristmasGameRestByMap", restMap);
				}
			}else{
				if(b == 2){
					throw new LessException("0005", new String[] { "领取错误",
					"新年活动游戏暂未开放" });     
				}
				
				if(b == 3){
					throw new LessException("0006", new String[] { "领取错误",
					"新年活动游戏已过" });  
				}
				
				if(b == 4){
					throw new LessException("0004", new String[] { "领取错误",
					"您领取游戏超过了限量！" });  
				}
				
			}
		}
		
		if(systemConfig2 != null && systemConfig2.getParamValue().equals(game.getId())){
			//判断圣诞专属游戏是否可以被领取
			int b = isChristmasGameAvailable(num,"2015-02-09 00:00:00","2015-02-18 23:59:59",systemConfig2.getParamValue());
			if(b == 1){
				final  Integer s = 0;
				synchronized (s) {
				int rest = getChristmasGameRest(systemConfig2.getParamValue());
				
				if(rest < num){
					throw new LessException("0004", new String[] { "领取错误",
					"您领取游戏超过了限量！" });     
				}
				
				rest = rest - num;
				Map<String,Object> restMap = new HashMap<String,Object>();
				restMap.put("rest", rest);
				restMap.put("id", systemConfig2.getParamValue());
				gameService.updateObject("updateChristmasGameRestByMap", restMap);
				}
			}else{
				if(b == 2){
					throw new LessException("0005", new String[] { "领取错误",
					"活动暂未开始" });     
				}
				
				if(b == 3){
					throw new LessException("0006", new String[] { "领取错误",
					"活动已结束，感谢参与！" });  
				}
				
				if(b == 4){
					throw new LessException("0004", new String[] { "领取错误",
					"您领取游戏超过了限量！" });  
				}
				
			}
		}
		
		if(systemConfig3 != null && systemConfig3.getParamValue().equals(game.getId())){
			//判断圣诞专属游戏是否可以被领取
			int b = isChristmasGameAvailable(num,"2015-02-09 00:00:00","2015-02-18 23:59:59",systemConfig3.getParamValue());
			if(b == 1){
				final  Integer s = 0;
				synchronized (s) {
				int rest = getChristmasGameRest(systemConfig3.getParamValue());
				
				if(rest < num){
					throw new LessException("0004", new String[] { "领取错误",
					"您领取游戏超过了限量！" });     
				}
				
				rest = rest - num;
				Map<String,Object> restMap = new HashMap<String,Object>();
				restMap.put("rest", rest);
				restMap.put("id", systemConfig3.getParamValue());
				gameService.updateObject("updateChristmasGameRestByMap", restMap);
				}
			}else{
				if(b == 2){
					throw new LessException("0005", new String[] { "领取错误",
					"活动暂未开始" });     
				}
				
				if(b == 3){
					throw new LessException("0006", new String[] { "领取错误",
					"活动已结束，感谢参与！" });  
				}
				
				if(b == 4){
					throw new LessException("0004", new String[] { "领取错误",
					"您领取游戏超过了限量！" });  
				}
				
			}
		}
		
		
		for (int i = 0; i < num; i++) {

			deposit = deposit + game.getDeposit();

			UserGameRelation userGameRelation = new UserGameRelation();
			userGameRelation.setId(UUID.randomUUID().toString());
			// 保存主键
			if (i == 0) {
				ids = userGameRelation.getId();
			} else {
				ids = ids + "," + userGameRelation.getId();
			}
			userGameRelation.setStatus(UserGameRelation.InprocessStatus);
			userGameRelation.setUserId(user.getMobileNumber());
			userGameRelation.setGameId(game.getId());
			Date curDate = null;
			try {
				curDate = sdf.parse(sdf.format(new Date()));
				userGameRelation.setCreateTime(curDate);
				// 就算失效时间
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				calendar.add(Calendar.DATE, game.getClicks() - 1);// 加数字多少天
				String newDate = sdf.format(calendar.getTime())
						.substring(0, 10) + " 23:59:59";
				userGameRelation.setInvalidTime(sdf.parse(newDate));
				userGameRelation.setPolicyClicks(game.getClicks());
				userGameRelation.setPolicyPunish(game.getPunish());
				userGameRelation.setPolicyReward(game.getReward());
				userGameRelation.setPolicyDeposit(game.getDeposit());
			} catch (ParseException e) {
				logger.error("时间转换错误:" + new Date());
			}

			// 扣除每个游戏的押金
			UserWallet userWallet = userWalletBusiness
					.getUserWalletByUserId(user.getId());
			Long money = new Long((long) game.getDeposit());
			if (userWallet.getAmount().compareTo(BigDecimal.valueOf(money)) >= 0) {
				// 扣除用户账户押金
				banance = userWalletBusiness.withGameDeposit(
						BigDecimal.valueOf(money), userWallet, addr);
				logger.info("扣除用户"+user.getMobileNumber()+"押金:"+money+",领取游戏为"+game.getGameName());
				// 结束扣除用户押金
				userGameRelation.setBanance(banance);
                
			} else {
				banance = new BigDecimal(-1);
				throw new LessException("0000", new String[] { "余额不足",
						"当前账户余额不足请充值" });
			}
			
			if(user.getInviteCodeFromOther()!=null&&!user.getInviteCodeFromOther().equals("")){
				SystemConfig sysConfig = systemConfigBusinessImpl.getSystemConfig("107");
					if(sysConfig!=null&&!"".equals(sysConfig)){
							  BigDecimal inviteBenefitPre=BigDecimal.valueOf(Double.valueOf(sysConfig.getParamValue()));
							  if(inviteBenefitPre.compareTo(new BigDecimal("0.00"))>0){
								  User userOther= userBusiness.findUserByInviteCode(user.getInviteCodeFromOther());
								  if(userOther!=null&&!userOther.equals("")){
									  InviteBenefit inviteBenefit =new InviteBenefit();
									  BigDecimal benefit=BigDecimal.valueOf(money).multiply(inviteBenefitPre);
									  inviteBenefit.setId(UUID.randomUUID().toString());
									  inviteBenefit.setUserid(user.getId());
									  inviteBenefit.setInviteUserId(userOther.getId());
									  inviteBenefit.setBenefit(benefit.setScale(2,BigDecimal.ROUND_DOWN));
									  inviteBenefit.setActiontime(new Date());
									  inviteBenefit.setContent("您推荐的用户"+user.getNickName()+"领取【"+game.getGameName()+"】任务,您获取了"+benefit+"纳币！");
									  UserWallet  userOtherWallet = userWalletBusiness.getUserWalletByUserId(userOther.getId());
									  userWalletBusiness.withInviteBenefit(benefit, userOtherWallet, addr);
									  inviteBenefitBusinessImp.addInviteBenefit(inviteBenefit);
								  } 
							  }
					}
				}
			
			// 增加等级积分
			// modiy by hepei 2013-09-23 1元换算为10积分
			user = userBusiness.findUserById(user.getId());
			Long credits = (long) (money * 10);
			user.setLevel(user.getLevel() + credits.intValue()); //等级积分
			user.setCredits(user.getCredits()+credits.intValue());//可用积分
			userService.updateObject("updateUserByMobileNumber", user);
			
			// 增加积分记录
			ObtainCredits obtainCredits = new ObtainCredits();
			obtainCredits.setId(UUID.randomUUID().toString());
			obtainCredits.setActionTime(new Date());
			obtainCredits.setCreateTime(new Date());
			obtainCredits.setUserId(user.getId());
			obtainCredits.setCredits(credits);
			obtainCredits.setActionType(1);
			userObtainCreditsBusinessImpl.addObtainCredits(obtainCredits);
			
			// 结束扣除每个游戏的押金

			list.add(userGameRelation);
//			WeixinUser weixinUser=weixinUserBusiness.getWeixinUserByUserId(user.getId());
//			if(weixinUser!=null){
//			 AdvancedUtil.sendCustomMessage(TokenThread.accessToken.getAccessToken(), AdvancedUtil.makeTextCustomMessage(weixinUser.getOpenid(),"领取任务为"+game.getGameName()+"\n押金:"+money+"纳币"));
//			}
			}
		str[0] = ids;
		str[1] = String.valueOf(deposit);
		str[2] = String.valueOf(banance);
		if (banance.compareTo(new BigDecimal(-1)) > 0) {
			this.addUserGameRelationList(list);
		}
		return str;
	}

	
	@Transactional(rollbackFor = { Exception.class, ServiceException.class })
	public boolean payment(String addr) throws Exception {
		boolean flag = false;
		try{
			
		
		User user = SpringSecurityUtil.getCurrentUser();
		UserWallet userWallet = userWalletBusiness.getUserWalletByUserId(user
				.getId());
		SystemConfig config = systemConfigBusiness.getSystemConfig("110");
		Long money = new Long(config.getParamValue());

		// 返还用户奖励
		userWalletBusiness.withAddGameReward(
				BigDecimal.valueOf(money), userWallet, addr);
		
		// 结束扣除用户押金
        flag = true;
		}catch(Exception e){
			flag = false;
			throw new Exception("payment发生异常");
		}
		return flag;
	}

	@Override
	public List<Game> queryGameForRank(Map<String, Object> map)
			throws BusinessException {
		try {
			List<Game> list = gameService.findObjects("queryGameForRank", map);
			return list;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"queryGameForRank(" + map + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	@Transactional(rollbackFor = { Exception.class, ServiceException.class })
	public int startPlay(String ids) throws BusinessException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<GameLog> list = new ArrayList<GameLog>();
			
			List<String> strList = new ArrayList<String>();
			for (String str : ids.split(",")) {
				strList.add(str);
			}
			List<UserGameRelation> userGameRelationList = this.queryUserGameRelationByIds(strList);
			for (UserGameRelation userGameRelation : userGameRelationList) {
				
				Date curDate = null;
				try {
					curDate = sdf.parse(sdf.format(new Date()));
				} catch (ParseException e1) {
					logger.error("时间转换错误:" + new Date());
				}
				GameLog gameLog = new GameLog();
				// GamePolicy gamePolicy =
				// gameBusiness.getGamePolicyByRelationId(str);
				
				if (userGameRelation != null) {
					int playnum = (Integer) gameLogService.getObject("queryGameLogNum", userGameRelation.getId());
					userGameRelation.setPlaynum(playnum);
			    	
					if(userGameRelation.getPlaynum()<userGameRelation.getClicks()){
					
					if (sdf.format(userGameRelation.getInvalidTime())
							.substring(0, 10)
							.equals(sdf.format(curDate).substring(0, 10))) {
						gameLog.setId(UUID.randomUUID().toString());
						gameLog.setRelationId(userGameRelation.getId());
						gameLog.setCreateTime(curDate);

						list.add(gameLog);
					} else {
						String str1 = sdf.format(curDate).substring(0, 10);
						String str2 = "";
						if(userGameRelation.getClickDate() != null){
							str2 = sdf.format(userGameRelation.getClickDate()).substring(0, 10);
						}	   
						if (!str1.equals(str2)) {
							gameLog.setId(UUID.randomUUID().toString());
							gameLog.setRelationId(userGameRelation.getId());
							gameLog.setCreateTime(curDate);

							list.add(gameLog);
						}
					}
				}
					userGameRelation.setClickDate(curDate);
					this.updateUserGameRelation(userGameRelation);
				}

			}

			if (list.size() > 0) {
				this.addGameLogList(list);
				return 0;
			}else{
				return 1;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"startPlay(" + ids + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	@Transactional(rollbackFor = { Exception.class, ServiceException.class })
	public void finishTask(String ids, String addr) throws BusinessException {
		try {
			for (String str : ids.split(",")) {
				Map<String, Object> condition = new HashMap<String, Object>();
				condition.put("id", str);
				List<UserGameRelation> userGameRelationList = this
						.queryAllGameTaskDetails(condition);
				Date date = new Date();
				if (userGameRelationList.size() > 0) {
					UserGameRelation userGameRelation = userGameRelationList
							.get(0);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					if (userGameRelation.getPlaynum() >= userGameRelation
							.getClicks()
							&& sdf.parse(sdf.format(date)).compareTo(
									sdf.parse(sdf.format(userGameRelation
											.getCreateTime()))) != 0 && userGameRelation.getStatus()==1) {

						// 完成任务

						User user = SpringSecurityUtil.getCurrentUser();
						if(user == null){
							user = userBusiness.findUserByMoblieNumber(userGameRelation.getUserId());
						}
						UserWallet userWallet = userWalletBusiness
								.getUserWalletByUserId(user.getId());
						Long money = new Long(
								(long) userGameRelation.getDeposit());

						// 返还用户账户押金
						BigDecimal banance = userWalletBusiness
								.withAddGameDeposit(BigDecimal.valueOf(money),
										userWallet, addr);
						// 结束扣除用户押金

						double money_reward = userGameRelation.getReward();
						// 返还用户奖励
						banance = userWalletBusiness.withAddGameReward(
								BigDecimal.valueOf(money_reward), userWallet, addr);
						// 结束扣除用户押金

						userGameRelation
								.setStatus(UserGameRelation.FinishedStatus);
						userGameRelation.setBanance(banance);
						this.updateUserGameRelation(userGameRelation);

						// 结束完成任务

					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"finishTask(" + ids + "," + addr + ")方法出错！\t",
					e.getMessage() });
		}
	}

	@Override
	public List<Game> queryGamesByUserList(List<String> list)
			throws BusinessException {
		List<Game> gamelist = new ArrayList<Game>();
		gamelist = gameService.findObjects("queryGamesByUserList", list);
		return gamelist;
	}

	@Override
	public List<Game> querySixGamesByReward() throws BusinessException {
		List<Game> gamelist = new ArrayList<Game>();
		gamelist = gameService.findObjects("querySixGamesByReward", null);
		return gamelist;
	}

	@Override
	public List<GameRecommend> queryGameRecommendAPI(Map<String, Object> map)
			throws BusinessException {
		try {
			List<GameRecommend> list = gameRecommendService.findObjects(
					"queryGameRecommend", map);
			return list;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"queryGameRecommendInIndex()方法出错！\t", e.getMessage() });
		}
	}

	@Override
	@Transactional(rollbackFor = { Exception.class, ServiceException.class })
	public void gameOp() throws BusinessException {
		List<UserGameJobView> userGameJobViewList = userGameJobViewService
				.findObjects("queryUserGameJobViewList", null);
		Date now = new Date();
		for (UserGameJobView userGameJobView : userGameJobViewList) {
			if (now.compareTo(userGameJobView.getInvalidTime()) >= 0) {

			} else {

			}
		}
	}

	@Override
	public List<Game> queryGamesByRand(Map<String, Object> map)
			throws BusinessException {
		try {
			List<Game> list = gameService.findObjects("queryGamesByRand", map);
			return list;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"queryGames(" + map + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public List<Game> queryUnionGames() throws BusinessException {
		try {
			List<Game> list = gameService.findObjects("queryUnionGames", null);
			return list;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"queryUnionGames()方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public List<GameRecommend> queryGameRecommend(
			Map<String, Object> condition, PaginationMore page)
			throws BusinessException {
		try {
			page.setTotalRows(getRecommendGamesCounts(condition));
			page.setPageSize(10);
			page.repaginate();
			return gameRecommendService.selectList("queryGameRecommend",
					condition, page);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"queryGames(" + condition + "," + page + ")方法出错！\t",
					e.getMessage() });
		}
	}

	@Override
	public void addGameRecommend(Game game) throws BusinessException {
		GameRecommend gameRecommend = this.getGameRecommend(game.getGameName());
		if (gameRecommend == null) {
			gameRecommend = new GameRecommend();
			gameRecommend.setId(UUID.randomUUID().toString());
			gameRecommend.setGameName(game.getGameName());
			gameRecommend.setOrderBy(999L);
			this.addGameRecommend(gameRecommend);
		}
	}

	@Override
	public void delRecommendGameById(String id) throws BusinessException {
		try {
			gameRecommendService.deleteObject("delRecommendGameById", id);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"delRecommendGameById(" + id + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public GameRecommend getGameRecommendById(String id)
			throws BusinessException {
		try {
			return (GameRecommend) gameService.getObject(
					"getGameRecommendById", id);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"getGameRecommendById(" + id + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public void updateGameRecommend(GameRecommend gameRecommend)
			throws BusinessException {
		gameService.updateObject("updateGameRecommend", gameRecommend);
	}

	@Override
	public List<LoginStatus> queryLoginStatus(String mobile) throws Exception {
		return loginStatusService.findObjects("queryLoginStatus", mobile);
	}

	@Override
	public void addLoginStatus(LoginStatus status) throws BusinessException {
		 loginStatusService.saveObject("addLoginStatus", status);
	}

	@Override
	public List<Version> queryVersions(Map<String, Object> condition,
			PaginationMore page) throws BusinessException {
		try {
			page.setTotalRows(getVersionCounts(condition));
			page.setPageSize(10);
			page.repaginate();
			return versionService.selectList("queryVersionsByAdmin", condition, page);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"queryGames(" + condition + "," + page + ")方法出错！\t",
					e.getMessage() });
		}
	}

	@Override
	public int getVersionCounts(Map<String, Object> map)
			throws BusinessException {
		try {
			int gamesCounts = (Integer) gameService.getObject("getVersionCounts",
					map);
			return gamesCounts;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"getVersionCounts(" + map + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public Version getVersion(String id) throws BusinessException {
		try {
			return (Version) versionService.getObject("getVersion", id);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"Version(" + id + ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public List<Game> queryVipGamesForSimple(Map<String, Object> map)
			throws BusinessException {
		
		return gameService.findObjects("queryVipGamesForSimple", map);
	}
	/**
	 * 
	 * @return 1:pass 2:时间没到 3:时间已过 4:剩余的游戏不够
	 */
	private int isChristmasGameAvailable(int num,String startTime,String endTime){
		if(startTime != null){
			boolean a = DateUtils.compareCurrentDate(startTime);//"2013-12-25 00:00:00"
			
			if(!a){
				return 2;
			}
		}
		if(endTime != null){
			boolean b = DateUtils.compareCurrentDate(endTime);//"2014-01-01 23:59:59"
			if(b){
				return 3;
			}
		}
		
		
		int rest = getChristmasGameRest();
		if(rest < num){
			return 4;
		}
		
		return 1;
	}
	
	/**
	 * 
	 * @return 1:pass 2:时间没到 3:时间已过 4:剩余的游戏不够
	 */
	private int isChristmasGameAvailable(int num,String startTime,String endTime,String id){
		if(startTime != null){
			boolean a = DateUtils.compareCurrentDate(startTime);//"2013-12-25 00:00:00"
			
			if(!a){
				return 2;
			}
		}
		if(endTime != null){
			boolean b = DateUtils.compareCurrentDate(endTime);//"2014-01-01 23:59:59"
			if(b){
				return 3;
			}
		}
		
		
		int rest = getChristmasGameRest(id);
		if(rest < num){
			return 4;
		}
		
		return 1;
	}

	
	public int getChristmasGameRest(){
			return (Integer) gameService.getObject("getChristmasGameRest", null);
		
	}
	
	public int getChristmasGameRest(String gameid){
		return (Integer) gameService.getObject("getChristmasGameRestByString", gameid);
    }

	@Override
	public BigDecimal getGameAmountTotalByUserid(Map<String, Object> condition)
			throws BusinessException {
		return (BigDecimal) gameService.getObject("getGameAmountTotalByUserid", condition);
	}

	@Override
	public BigDecimal getGameRewardTotalByUserid(Map<String, Object> condition)
			throws BusinessException {
		return (BigDecimal) gameService.getObject("getGameRewardTotalByUserid", condition);
	}

	@Override
	public List<UserGameRelation> queryAllGameTaskDetailsOptimize(
			Map<String, Object> condition, PaginationMore page)
			throws BusinessException {
		try {
			page.setTotalRows(getAllGameTaskDetailsCount(condition));
			page.repaginate();
//			return userGameRelationService.selectList(
//					"queryAllGameTaskDetails", condition, page);
		    List<UserGameRelation> list = userGameRelationService.selectList("queryAllGameTaskDetailsOptimize", condition, page);
//		    for(int i = 0;i<list.size();i++){
//		    	UserGameRelation relation = list.get(i);
//		    	int playnum = (Integer) gameLogService.getObject("queryGameLogNum", relation.getId());
//		    	relation.setPlaynum(playnum);
//		    }
		    return list;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"queryAllGameTaskDetails(" + condition + "," + page
							+ ")方法出错！\t", e.getMessage() });
		}
	}

	@Override
	public UserGameRelation getUserGameBenefitInfo(Map<String, Object> condition)
			throws BusinessException {
		try {
			UserGameRelation  userGameRelation = (UserGameRelation) userGameRelationService.getObject("getUserGameBenefitInfo", condition);
			int installmentMoney = (Integer) userGameRelationService.getObject("getInstallmentsMoney", condition);
			int installmentReward = (Integer) userGameRelationService.getObject("getInstallmentsReward", condition);
			userGameRelation.setDeposit(userGameRelation.getDeposit()+installmentMoney);
			userGameRelation.setReward(userGameRelation.getReward()+installmentReward);
			return userGameRelation;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"getUserGameBenefitInfo(" + condition + ")方法出错！\t",
					e.getMessage() });
		}
	}

	@Override
	public int getTaskNum(Map<String,Object> map) throws BusinessException {
		try{
			return (Integer) userGameRelationService.getObject("getTaskNum", map);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {
					"getTaskNum(" + map + ")方法出错！\t",
					e.getMessage() });
		}
	}

	@Override
	public List<Game> queryOtherGames(String gameId) throws BusinessException {
		return gameService.findObjects("queryOtherGames",gameId);
	}
}
