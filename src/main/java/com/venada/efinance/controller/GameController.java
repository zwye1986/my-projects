package com.venada.efinance.controller;

import com.venada.efinance.business.*;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.exception.LessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.enumtype.LogTypeEnum;
import com.venada.efinance.pojo.*;
import com.venada.efinance.security.SpringSecurityUtil;
import com.venada.efinance.sms.SmsService;
import com.venada.efinance.util.LevelUtils;
import com.venada.efinance.util.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class GameController {
	/** Logger */
	private static final Logger logger = LoggerFactory
			.getLogger(GameController.class);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private GameBusiness gameBusiness;
	@Autowired
	private UserBusiness userBusiness;
	@Autowired
	private UserWalletBusiness userWalletBusiness;
	@Autowired
	private LpRecordBusiness lpRecordBusiness;
	@Autowired
	private VersionBusiness versionBusiness;
	@Autowired
	private OperationLogBusiness opeartionLogBusiness;
	@Autowired
	private SystemConfigBusiness systemConfigBusiness;
	@Autowired
	private RechargeRecordBusiness rechargeRecordBusiness;
	@Autowired
	private DealDetailBusiness dealDetailBusiness;
	@Autowired
	private TransactionDetailsBusiness transactionDetailsBusiness;
	@Autowired
	private BankCardBusiness bankCardBusiness;
	@Autowired
	private AmountOptionBusiness amountOptionBusiness;
	@Autowired
	private SecurityCenterBusiness securityCenterBusiness;
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	@Autowired
	private UserDetailBusiness userDetailBusiness;
	@Autowired
	private SmsService smsService;
	@Autowired
	private LpRecordBusiness recordBusiness;
	@Autowired
	private MessageRuleBusiness messageRuleBusinessImpl;
	@Autowired
	private GameBusiness gameBusinessImpl;
	@Autowired
	private InviteBenefitBusiness inviteBenefitBusinessImp;
	@Autowired
	private UserSignInBusiness userSignInBusinessImpl;


	public ModelAndView gameIndex(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("category", Game.FLASH_GAME);
			map.put("count", 12);
			List<Game> gameRankList = new ArrayList<Game>();
			// 查询小游戏排行榜
			gameRankList = gameBusiness.queryGameForRank(map);
			mv.addObject("flashRank", gameRankList);

			// 排行榜上的推荐
			if (gameRankList.size() > 0) {
				mv.addObject("bigFlashRank", gameRankList.get(0));
				Game game = new Game();
				game = gameRankList.get(0);
				if (game != null) {
					// 查询游戏策略
					GamePolicy policy = gameBusiness.getGamePolicyByGameId(game
							.getId());
					mv.addObject("policy", policy);
					int gamePlayCounts = gameBusiness.getGamePlayCounts(game
							.getId());
					mv.addObject("gamePlayCounts", gamePlayCounts);
				}
			}

			map.put("category", Game.WEB_GAME);
			// 查询网页游戏排行榜
			gameRankList = gameBusiness.queryGameForRank(map);
			mv.addObject("webRank", gameRankList);

			// 游戏中心首页查询全部游戏的8个
			map = new HashMap<String, Object>();
			map.put("count", 8);

			// 判断是否为搜索
			String searchtype = request.getParameter("searchtype");
			if ("search".equals(searchtype)) {
				mv.addObject("searchtype", searchtype);
				String keyword = request.getParameter("keyword");
				mv.addObject("keyword", keyword);
				keyword = "%" + SystemUtils.transfer(keyword) + "%";
				// keyword = "%"+keyword+"%";
				map.put("keyword", keyword);

			}

			List<Game> gameList = gameBusiness.queryGames(map);
			mv.addObject("gamelist", gameList);

			// 判断是否查询推荐
			if ("search".equals(searchtype) && gameList.size() == 0) {
				List<GameRecommend> commendList = gameBusiness
						.queryGameRecommendInSearch();
				mv.addObject("commendList", commendList);
			}

			// 计算总共有多少页
			int gamesCounts = gameBusiness.getGamesCounts(map);

			int gamesPage = (gamesCounts / 8) + (gamesCounts % 8 > 0 ? 1 : 0);
			mv.addObject("gamesPage", gamesPage);

			List<Game> rewardList = gameBusiness.querySixGamesByReward();
			mv.addObject("rewardList", rewardList);

			mv.setViewName(".game");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return mv;
	}
	
	
	@RequestMapping(value = "/game")
	public String newGameIndex(HttpServletRequest request,HttpServletResponse response) {
		return ".newGame";
	}
	
	

	@SuppressWarnings("deprecation")
	public ModelAndView showGame(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		try {
			mv.setViewName("game/gamelist");

			Map<String, Object> map = new HashMap<String, Object>();
			String type = request.getParameter("type");

			String sort = request.getParameter("sort");
			String sortseq = request.getParameter("sortseq");
			map.put("sort2", sort);
			map.put("sortseq", sortseq);
			mv.addObject("sort", sort);
			mv.addObject("sortseq", sortseq);

			String beginDeposit = request.getParameter("beginDeposit");
			String endDeposit = request.getParameter("endDeposit");
			map.put("beginDeposit", beginDeposit);
			map.put("endDeposit", endDeposit);
			mv.addObject("beginDeposit", beginDeposit);
			mv.addObject("endDeposit", endDeposit);

			if ("roleplay".equals(type)) {
				map.put("typename1", "%角色扮演%");
				map.put("typename2", "%角色扮演%");
			} else if ("warstrategy".equals(type)) {
				map.put("typename1", "%战争%");
				map.put("typename2", "%策略%");
			} else if ("leisuretournament".equals(type)) {
				map.put("typename1", "%休闲%");
				map.put("typename2", "%竞技%");
			} else if ("businesssimulation".equals(type)) {
				map.put("typename1", "%模拟%");
				map.put("typename2", "%经营%");
			}

			mv.addObject("type", type);

			// 判断是否为搜索
			String searchtype = request.getParameter("searchtype");
			if ("search".equals(searchtype)) {
				mv.addObject("searchtype", searchtype);
				String keyword = request.getParameter("keyword");
				keyword = java.net.URLDecoder.decode(keyword);
				mv.addObject("keyword", keyword);
				keyword = "%" + SystemUtils.transfer(keyword) + "%";
				map.put("keyword", keyword);

			}

			// 计算总共有多少页
			int gamesCounts = gameBusiness.getGamesCounts(map);

			int gamesPage = (gamesCounts / 8) + (gamesCounts % 8 > 0 ? 1 : 0);
			mv.addObject("gamesPage", gamesPage);

			int page = 1;
			try {
				page = Integer.parseInt(request.getParameter("page"));
			} catch (Exception e) {
				logger.error("页数转换为数字失败：" + page);
				page = 1;
			}
			if (page < 1) {
				page = 1;
			}
			if (page > gamesPage && gamesCounts != 0) {
				page = gamesPage;
			}

			map.put("limitCount", 8);
			map.put("limitIndex", (page - 1) * 8);
			List<Game> gameList = gameBusiness.queryGames(map);
			mv.addObject("gamelist", gameList);

			mv.addObject("page", page);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return mv;
	}
	
	@RequestMapping(value = "/showCommendGame", method = RequestMethod.GET)
	public String  showCommendGame(HttpServletRequest request,Model model) {
		model.addAttribute("recommendGames", gameBusiness.queryGameRecommendByRand());
		return "commendGame";
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/showNewGame", method = RequestMethod.GET)
	public String  showNewGame(HttpServletRequest request,Model model) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String type = request.getParameter("type");
			String sort = request.getParameter("sort");
			String sortseq = request.getParameter("sortseq");
			map.put("sort2", sort);
			map.put("sortseq", sortseq);
			model.addAttribute("sort", sort);
			model.addAttribute("sortseq", sortseq);

			String beginDeposit = request.getParameter("beginDeposit");
			String endDeposit = request.getParameter("endDeposit");
			map.put("beginDeposit", beginDeposit);
			map.put("endDeposit", endDeposit);
			model.addAttribute("beginDeposit", beginDeposit);
			model.addAttribute("endDeposit", endDeposit);

			if ("roleplay".equals(type)) {
				map.put("typename1", "%角色扮演%");
				map.put("typename2", "%角色扮演%");
			} else if ("warstrategy".equals(type)) {
				map.put("typename1", "%战争%");
				map.put("typename2", "%策略%");
			} else if ("leisuretournament".equals(type)) {
				map.put("typename1", "%休闲%");
				map.put("typename2", "%竞技%");
			} else if ("businesssimulation".equals(type)) {
				map.put("typename1", "%模拟%");
				map.put("typename2", "%经营%");
			}

			model.addAttribute("type", type);

			// 判断是否为搜索
			String searchtype = request.getParameter("searchtype");
			if ("search".equals(searchtype)) {
				model.addAttribute("searchtype", searchtype);
				String keyword = request.getParameter("keyword");
				keyword = java.net.URLDecoder.decode(keyword);
				model.addAttribute("keyword", keyword);
				keyword = "%" + SystemUtils.transfer(keyword) + "%";
				map.put("keyword", keyword);

			}

			// 计算总共有多少页
			int gamesCounts = gameBusiness.getGamesCounts(map);

			int gamesPage = (gamesCounts / 8) + (gamesCounts % 8 > 0 ? 1 : 0);
			model.addAttribute("gamesPage", gamesPage);

			int page = 1;
			try {
				page = Integer.parseInt(request.getParameter("page"));
			} catch (Exception e) {
				logger.error("页数转换为数字失败：" + page);
				page = 1;
			}
			if (page < 1) {
				page = 1;
			}
			if (page > gamesPage && gamesCounts != 0) {
				page = gamesPage;
			}

			map.put("limitCount", 8);
			map.put("limitIndex", (page - 1) * 8);
			List<Game> gameList = gameBusiness.queryGames(map);
			model.addAttribute("gamelist", gameList);

			model.addAttribute("page", page);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return "game/newgamelist";
	}

	@RequestMapping(value = { "xgame" }, params = { "type" })
	public String xgame(Model model, HttpServletRequest request)
			throws Exception {
		String type = request.getParameter("type");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", 100);
		if ("flash".equals(type)) {
			model.addAttribute("rankname", "小游戏");
			map.put("category", Game.FLASH_GAME);
			List<Game> list = gameBusiness.queryGameForRank(map);
			model.addAttribute("list", list);
		} else if ("web".equals(type)) {
			model.addAttribute("rankname", "网页游戏");
			map.put("category", Game.WEB_GAME);
			List<Game> list = gameBusiness.queryGameForRank(map);
			model.addAttribute("list", list);
		} else {
			throw new Exception();
		}

		// 左侧小游戏排行
		map.put("category", Game.FLASH_GAME);
		map.put("count", 12);
		List<Game> gameRankList = new ArrayList<Game>();
		// 查询小游戏排行榜
		gameRankList = gameBusiness.queryGameForRank(map);
		model.addAttribute("flashRank", gameRankList);
		// 排行榜上的推荐
		if (gameRankList.size() > 0) {
			model.addAttribute("bigFlashRank", gameRankList.get(0));

			Game game = new Game();
			game = gameRankList.get(0);
			if (game != null) {
				// 查询游戏策略
				GamePolicy policy = gameBusiness.getGamePolicyByGameId(game
						.getId());
				model.addAttribute("policy", policy);

				int gamePlayCounts = gameBusiness.getGamePlayCounts(game
						.getId());
				model.addAttribute("gamePlayCounts", gamePlayCounts);

			}
		}
		model.addAttribute("type", type);
		return ".xgame";
	}

	@RequestMapping(value = "/showIndexGame", method = RequestMethod.GET)
	public ModelAndView showIndexGame(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("gameIndexList");

		Map<String, Object> map = new HashMap<String, Object>();
		String type = request.getParameter("type");

		if ("roleplay".equals(type)) {
			map.put("typename1", "%角色扮演%");
			map.put("typename2", "%角色扮演%");
		} else if ("warstrategy".equals(type)) {
			map.put("typename1", "%战争%");
			map.put("typename2", "%策略%");
		} else if ("leisuretournament".equals(type)) {
			map.put("typename1", "%休闲%");
			map.put("typename2", "%竞技%");
		} else if ("businesssimulation".equals(type)) {
			map.put("typename1", "%模拟%");
			map.put("typename2", "%经营%");
		} else if ("other".equals(type)) {
			map.put("typename1", "%其他%");
			map.put("typename2", "%其他%");
		}

		mv.addObject("type", type);

		// 计算总共有多少页
		int gamesCounts = gameBusiness.getGamesCounts(map);
		int gamesPage = (gamesCounts / 3) + (gamesCounts % 3 > 0 ? 1 : 0);

		int page = 1;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			logger.error("页数转换为数字失败：" + page);
			page = 1;
		}
		if (page < 1) {
			page = 1;
		}
		if (page > gamesPage && gamesCounts != 0) {
			page = gamesPage;
		}

		map.put("page", page);
		map.put("limitCount", 3);
		map.put("limitIndex", (page - 1) * 3);
		List<Game> gameList = gameBusiness.queryGames(map);
		mv.addObject("gamelist", gameList);

		mv.addObject("page", page);

		return mv;
	}

	// 弹出框
	@RequestMapping(value = "/showGameDialog", method = RequestMethod.GET)
	public ModelAndView showGameDialog(HttpServletRequest request) {
		User user = SpringSecurityUtil.getCurrentUser();
		ModelAndView mv = new ModelAndView();
		if (user == null) {
			mv.setViewName("game/login");
		} else {
			mv.setViewName("game/gameDialog");
			String id = request.getParameter("id");
			Game game = gameBusiness.getGameDetail(id);
			mv.addObject("game", game);
		}
		return mv;
	}
	
	@RequestMapping(value = "/getRestNum", method = RequestMethod.GET)
	public @ResponseBody int getRestNum(HttpServletRequest request) throws BusinessException, LessException, ParseException{
		int restNum = 0;
		String seq = request.getParameter("seq");
		restNum = gameBusiness.getRestNum(seq);
		return restNum;
	}

	// 确认游戏
	@RequestMapping(value = "/confirmPlay", method = RequestMethod.GET)
	public ModelAndView confirmPlay(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("game/confirm");
		String id = request.getParameter("id");
		int num = 1;
		try {
			num = Integer.parseInt(request.getParameter("num"));
		} catch (Exception e) {
			logger.error("确认游戏数量转换为数字失败：" + num);
			num = 1;
		}

		// 查看用户是否登录
		User user = SpringSecurityUtil.getCurrentUser();
		if (user == null) {
			mv.setViewName("game/login");
		} else {

			Game game = gameBusiness.getGameDetail(id);
			try {
				String[] res = gameBusiness.confirmPlay(num, user, game,
						request.getRemoteAddr());
				if ("-1".equals(res[2])) {
					mv.setViewName("game/less");
				}
				if ("-2".equals(res[2])) {
//					mv.setViewName("game/onlyonce");
				}
				mv.addObject("deposit", res[1]);
				mv.addObject("banance", res[2]);
				mv.addObject("ids", res[0]);
				mv.addObject("game", game);
				mv.addObject("user", user);
				OperationLog log = new OperationLog();
				log.setLogType(LogTypeEnum.GameConfirm.getIndex());
				log.setDataOld("");
				log.setDataNew("领取游戏:" + game.getGameName() + "扣除押金:" + res[1]
						+ "余额:" + res[2]);
				opeartionLogBusiness.addOperationLog(log);
			} catch (LessException e) {
				logger.error("余额不足请充值");
				if ("0002".equals(e.getErrorCode())) {
//					mv.setViewName("game/onlyonce");
				} else if ("0000".equals(e.getErrorCode())) {
					mv.setViewName("game/less");
				} else {
					if ("0004".equals(e.getErrorCode())
							|| "0007".equals(e.getErrorCode())) {
						mv.addObject("msg", "您领取游戏的数量超过了剩余的数量。");
					} else if ("0005".equals(e.getErrorCode())) {
						mv.addObject("msg", "活动尚未开始，请耐心等待！");
					} else if ("0006".equals(e.getErrorCode())) {
						mv.addObject("msg", "活动时间已过，谢谢您的参与！");
					} else if ("0008".equals(e.getErrorCode())) {
						mv.addObject("msg", "您一次领取游戏过多，每次最多只能领取20个。");
					} else if ("0010".equals(e.getErrorCode())) {
						mv.addObject("msg", "系统错误，时间解析错误。");
					} else if ("0011".equals(e.getErrorCode())) {
						mv.addObject("msg", "截止活动开始时间，您账户注册时间未满3个月！");
					} else if ("0012".equals(e.getErrorCode())) {
						mv.addObject("msg", "截止活动开始时间，您账户累计充值额小于10万人民币！");
					} else if ("0013".equals(e.getErrorCode())) {
						mv.addObject("msg", "您不满足领取该游戏的条件！");
					} else if ("0014".equals(e.getErrorCode())) {
						mv.addObject("msg", "您还没有绑定蛙宝网微信账号！");
					} else if ("0015".equals(e.getErrorCode())) {
						mv.addObject("msg", "国庆活动任务时间已过！");
					}
					// mv.addObject("msg", e.getMessage());
					mv.addObject("gameid", game.getId());
					mv.setViewName("game/getGameError");
				}

			} catch (Exception e) {
				logger.error("领取游戏失败：" + e.getMessage());
				mv.addObject("msg", "领取游戏失败，请重试！");
				mv.addObject("gameid", game.getId());
				mv.setViewName("game/getGameError");
			}

		}
		return mv;
	}

	// 展示游戏详细
	@RequestMapping(value = "/showGameDetail", method = RequestMethod.GET)
	public ModelAndView showGameDetail(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(".gameDetail");
		String id = request.getParameter("id");
		Game game = gameBusiness.getGameDetail(id);

		// 更新点击次数
		// int clicknum = game.getClickNum();
		// clicknum = clicknum + 1;
		// game.setClickNum(clicknum);
		// gameBusiness.updateGame(game);

		mv.addObject("game", game);
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("category", Game.FLASH_GAME);
		condition.put("count", 18);
		mv.addObject("leftGameRanks", gameBusiness.queryGameForRank(condition));

		// 查询玩过该游戏的用户
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gameId", id);
		List<UserGameRelation> userGameRelationList = gameBusiness
				.queryUserGameRelationList(map);
		List<String> mobileNumberList = new ArrayList<String>();
		List<String> gameIdList = new ArrayList<String>();
		for (UserGameRelation userGameRelation : userGameRelationList) {
			mobileNumberList.add(userGameRelation.getUserId());
			gameIdList.add(userGameRelation.getGameId());
		}

		if (mobileNumberList.size() != 0) {
			List<User> userList = userBusiness
					.queryUserListByNumber(mobileNumberList);
			mv.addObject("userList", userList);
		}

		// 查询玩家还玩过
		if (gameIdList.size() != 0) {
			List<Game> gameList = gameBusiness
					.queryGamesByUserList(mobileNumberList);
			mv.addObject("gameList", gameList);
		}
		return mv;

	}

	// 展示游戏详细
	public ModelAndView showGameDetailForHtml(@PathVariable String id,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(".gameDetail");

		Game game = gameBusiness.getGameDetail(id);

		// 更新点击次数
		// int clicknum = game.getClickNum();
		// clicknum = clicknum + 1;
		// game.setClickNum(clicknum);
		// gameBusiness.updateGame(game);

		mv.addObject("game", game);
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("category", Game.FLASH_GAME);
		condition.put("count", 18);
		mv.addObject("leftGameRanks", gameBusiness.queryGameForRank(condition));

		// 查询玩过该游戏的用户
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gameId", id);
		List<UserGameRelation> userGameRelationList = gameBusiness
				.queryUserGameRelationList(map);
		List<String> mobileNumberList = new ArrayList<String>();
		List<String> gameIdList = new ArrayList<String>();
		for (UserGameRelation userGameRelation : userGameRelationList) {
			mobileNumberList.add(userGameRelation.getUserId());
			gameIdList.add(userGameRelation.getGameId());
		}

		if (mobileNumberList.size() != 0) {
			List<User> userList = userBusiness
					.queryUserListByNumber(mobileNumberList);
			mv.addObject("userList", userList);
		}

		// 查询玩家还玩过
		if (gameIdList.size() != 0) {
			List<Game> gameList = gameBusiness
					.queryGamesByUserList(mobileNumberList);
			mv.addObject("gameList", gameList);
		}

		SystemConfig systemConfig = systemConfigBusiness.getSystemConfig("112");
		if (systemConfig != null) {
			mv.addObject("xnid", systemConfig.getParamValue());
		}

		return mv;

	}

	// 展示游戏详细
	@RequestMapping(value = "/{id}/showGameDetail", method = RequestMethod.GET)
	public String showGameDetailNew(@PathVariable String id,
			HttpServletRequest request, Model model) {
		Game game = gameBusiness.getGameDetail(id);
		// 首页推荐游戏
		model.addAttribute("recommendGames",gameBusiness.queryGameRecommendInIndex());
		List<User> userlist = userBusiness.queryOtherUsers(id);
		model.addAttribute("otherUsers",userlist);
		
//		model.addAttribute("otherGames",gameBusiness.queryOtherGames(id));
		
		model.addAttribute("game", game);
		return ".gameDetail";
	}

	// 展示游戏详细
	@RequestMapping(value = "/showOtherGames", method = RequestMethod.GET)
	public String showOtherGames(HttpServletRequest request, Model model) {
		String id  = request.getParameter("id");
		Game game = gameBusiness.getGameDetail(id);
		// 首页推荐游戏
		model.addAttribute("recommendGames",gameBusiness.queryGameRecommendInIndex());
		//玩过这个游戏的人还玩过
	//	model.addAttribute("otherGames",gameBusiness.queryOtherGames(id));
		model.addAttribute("game", game);
		return ".gameDetail";
	}

	// 展示游戏详细
	@RequestMapping(value = "/startGame")
	public ModelAndView startGame(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String ids = request.getParameter("ids");
			String gameUrl = request.getParameter("gameUrl");

			if (ids != null && !"".equals(ids)) {
				gameBusiness.startPlay(ids);

				// 判断是否完成任务
				// gameBusiness.finishTask(ids, request.getRemoteAddr());
			}
			response.sendRedirect(gameUrl);
		} catch (IOException e) {
			logger.error("跳转页面出错");
		}
		return null;
	}
	
	@RequestMapping(value = "/usertaskDetail.html")
 	public String taskDetail(Model model,
 			HttpServletRequest request) {
		User user = SpringSecurityUtil.getCurrentUser();
		int vipTag ;
		model.addAttribute("rechargeAmount", rechargeRecordBusiness
				.getRechargeAmountByUserId(user.getId()));
		model.addAttribute("walletAmount",
				userWalletBusiness.getAmountByUserId(user.getId()));
		model.addAttribute("dealDetailAmount", dealDetailBusiness
				.getDealDetailAmountByUserId(user.getId()));
		
		model.addAttribute("level", user.getLevel());
		// 注册日期
		model.addAttribute("createDate", user.getCreateTime());
		// 获取用户下一个积分等级
		int nextlevel = (int) Math
				.ceil(LevelUtils.getLevel(user.getLevel() / 100.0));
		model.addAttribute("nextlevel", LevelUtils.getNextLevel(nextlevel));
		// 计算用户距离下一个积分等级的积分
		int nextLevelCredits = LevelUtils.getNextLevel(user.getLevel(),
				nextlevel);
		model.addAttribute("nextLevelCredits", nextLevelCredits);
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("userid", user.getMobileNumber());
		model.addAttribute("gameBenefit",
				gameBusinessImpl.getUserGameBenefitInfo(condition));
		// 查询推荐奖励
		condition.clear();
		condition.put("inviteUserId", user.getId());
		model.addAttribute("total",
				inviteBenefitBusinessImp.getInviteBenefitTotal(condition));
		// 查询签到奖励
		condition.clear();
		condition.put("mobilePhone", user.getMobileNumber());
		model.addAttribute("signTotal", userSignInBusinessImpl
				.sumSignBenefitByMobileNumber(condition));
		model.addAttribute("isAutoRenew",securityCenterBusiness.isAutoRenew(user.getId()));

		int isExpire = 0;
		if (securityCenterBusiness.isOpen(user.getId())) {
			vipTag = 1;
			// 显示会员到期时间
			SecurityCenter securityCenter = securityCenterBusiness
					.getSecurityCenterByUserId(user.getId());
			model.addAttribute("securityCenter", securityCenter);
		} else {
			vipTag = 0;
			SecurityCenter securityCenter = securityCenterBusiness
					.getSecurityCenterByUserId(user.getId());
			// 判断用户是否过期。 如果SecurityCenter 表没记录说明 尚未开通
			if (securityCenter == null) {
				isExpire = 0;
			} else {
				// 判断用户是否过期。 如果SecurityCenter 表有记录说明过期
				isExpire = 1;
			}
		}
		model.addAttribute("isExpire", isExpire);
		model.addAttribute("vipTag", vipTag);
		SystemConfig systemConfig = systemConfigBusiness.getSystemConfig("104");
		model.addAttribute("month_cost", systemConfig.getParamValue());
		model.addAttribute("item", 3);
   	return ".newTaskDetail";
 	}
	
    @RequestMapping(value = "/showTaskDetail", method = RequestMethod.GET)
	public String showTaskDetail(HttpServletRequest request,Model model) {
    	User user = SpringSecurityUtil.getCurrentUser();
    	if(user != null){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("userId", user.getMobileNumber());
		String type = request.getParameter("type");
		model.addAttribute("type", type);
		int page = 1;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			logger.error("页数转换为数字失败：" + page);
			page = 1;
		}
		if (page < 1) {
			page = 1;
		}
		int gamesCounts = 0;
		int gamesPage = 0;
		List<UserGameRelation> list = null;
   		if ("ing".equals(type)) {   	
   			map.put("status",1);
   		   // 计算总共有多少页
      	   gamesCounts = gameBusiness.getAllGameTaskDetailsCount(map);  
      	   gamesPage = (gamesCounts / 8) + (gamesCounts % 8 > 0 ? 1 : 0);
      	   if (page > gamesPage && gamesCounts != 0) {
				page = gamesPage;
		   }
      	   map.put("limitCount", 8);
		   map.put("limitIndex", (page - 1) * 8);
		   list = gameBusiness.queryNewAllGameTaskDetails(map);
   		}else if ("over".equals(type)) {
   			map.put("status",2);
   		 // 计算总共有多少页
   	   		gamesCounts = gameBusiness.getAllGameTaskDetailsCount(map);  
   	   	    gamesPage = (gamesCounts / 8) + (gamesCounts % 8 > 0 ? 1 : 0);
   	   	    if (page > gamesPage && gamesCounts != 0) {
			page = gamesPage;
	        }
   	   	    map.put("limitCount", 8);
		    map.put("limitIndex", (page - 1) * 8);
		    list = gameBusiness.queryNewAllGameTaskDetails(map);
   		}
   		model.addAttribute("list", list);
   		model.addAttribute("page", page);
   	    model.addAttribute("gamesPage", gamesPage);
    	}
   	    return "tasklist";
    }

	// 进行中的任务
	@RequestMapping(value = "/taskDetail", method = RequestMethod.POST)
	public String toTaskdetailList(PaginationMore page, Model model,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = SpringSecurityUtil.getCurrentUser();

		String type = request.getParameter("type");
		if ("ing".equals(type)) {
			map.put("status", UserGameRelation.InprocessStatus);
		} else if ("over".equals(type)) {
			map.put("status", UserGameRelation.FinishedStatus);
		} else if ("record".equals(type)) {
			map.put("punishRecord", "punishRecord");
		}
		if (user != null) {
			map.put("userId", user.getMobileNumber());
			List<UserGameRelation> list = gameBusiness.queryAllGameTaskDetails(
					map, page);
			model.addAttribute("list", list);
			model.addAttribute("page", page);
			model.addAttribute("type", type);
		}
		return "taskDetail";
	}

	@RequestMapping(value = "/editGame", method = RequestMethod.GET)
	public ModelAndView editGame(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addGame");
		String id = request.getParameter("id");
		Game game = gameBusiness.getGameDetail(id);
		mv.addObject("game", game);

		return mv;
	}

	@RequestMapping("/showSquareGamePic")
	public void showSquarePic(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ServletOutputStream op = response.getOutputStream();
		String id = request.getParameter("id");
		if (!"".equals(id) && id != null) {
			response.reset();
			GamePic gamePic = gameBusiness.getGamePic(id);
			op.write(gamePic.getGamePic());
		}
		op.flush();
		op.close();
	}

	@RequestMapping("/showRectangleGamePic")
	public void showRectanglePic(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ServletOutputStream op = response.getOutputStream();
		String id = request.getParameter("id");
		if (!"".equals(id) && id != null) {
			response.reset();
			GamePic gamePic = gameBusiness.getGamePic(id);
			op.write(gamePic.getGamePic());
		}
		op.flush();
		op.close();

	}

	/**
	 * 后台游戏录入首页
	 */
	@RequestMapping("/manager/adminGameList")
	public ModelAndView adminGameList(PaginationMore page,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("manager/adminGameList");
		Map<String, Object> map = new HashMap<String, Object>();
		String keyword = request.getParameter("keyword");
		String sort = request.getParameter("sort");
		String sortseq = request.getParameter("sortseq");
		map.put("sort2", sort);
		map.put("sortseq", sortseq);
		mv.addObject("sort", sort);
		mv.addObject("sortseq", sortseq);
		mv.addObject("keyword", keyword);
		if (keyword != null && !"".equals(keyword.trim())) {
			keyword = "%" + keyword + "%";
			map.put("keyword", keyword);
		}
		List<Game> list = gameBusiness.queryGames(map, page);
		mv.addObject("list", list);
		mv.addObject("page", page);

		return mv;
	}

	/**
	 * 安卓客户端后台查询列表
	 */
	@RequestMapping("/manager/adminVersionList")
	public ModelAndView adminVersionList(PaginationMore page,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("manager/adminVersionList");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Version> list = gameBusiness.queryVersions(map, page);
		mv.addObject("list", list);
		mv.addObject("page", page);

		return mv;
	}

	/**
	 * 后台排行榜游戏
	 */
	@RequestMapping("/manager/adminRecommendGameList")
	public ModelAndView adminRecommendGameList(PaginationMore page,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("manager/adminRecommendGameList");
		Map<String, Object> map = new HashMap<String, Object>();
		String keyword = request.getParameter("keyword");
		String sort = request.getParameter("sort");
		String sortseq = request.getParameter("sortseq");
		map.put("sort2", sort);
		map.put("sortseq", sortseq);
		mv.addObject("sort", sort);
		mv.addObject("sortseq", sortseq);
		mv.addObject("keyword", keyword);
		mv.addObject("keyword", keyword);
		if (keyword != null && !"".equals(keyword.trim())) {
			keyword = "%" + keyword + "%";
			map.put("keyword", keyword);
		}

		List<GameRecommend> list = gameBusiness.queryGameRecommend(map, page);
		mv.addObject("list", list);
		mv.addObject("page", page);

		return mv;
	}

	/**
	 * 后台排行榜游戏
	 */
	@RequestMapping("/manager/addRecommend")
	public @ResponseBody
	String addRecommend(HttpServletRequest request) {
		String id = request.getParameter("id");
		Game game = gameBusiness.getGameById(id);
		gameBusiness.addGameRecommend(game);
		return "";
	}

	/**
	 * 后台游戏删除
	 */
	@RequestMapping("/manager/adminGameDel")
	public ModelAndView adminGameDel(PaginationMore page,
			HttpServletRequest request) {
		String ids = request.getParameter("ids");
		if (ids != null && !"".equals(ids)) {
			String[] id = ids.split(",");

			for (int i = 0; i < id.length; i++) {
				Game game = gameBusiness.getGameById(id[i]);
				if (game != null) {
					gameBusiness.delGameById(id[i]);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("gameName", game.getGameName());
					gameBusiness.delGameRecommend(map);
				}

			}
		}

		// 跳转页面
		ModelAndView mv = new ModelAndView();
		mv.setViewName("manager/adminGameList");
		Map<String, Object> map = new HashMap<String, Object>();

		List<Game> list = gameBusiness.queryGames(map, page);
		mv.addObject("list", list);
		mv.addObject("page", page);

		return mv;
	}

	/**
	 * 后台推荐游戏删除
	 */
	@RequestMapping("/manager/adminRecommendGameDel")
	public ModelAndView adminRecommendGameDel(PaginationMore page,
			HttpServletRequest request) {
		String ids = request.getParameter("ids");
		if (ids != null && !"".equals(ids)) {
			String[] id = ids.split(",");

			for (int i = 0; i < id.length; i++) {
				gameBusiness.delRecommendGameById(id[i]);
			}
		}

		// 跳转页面
		ModelAndView mv = new ModelAndView();
		mv.setViewName("manager/adminRecommendGameList");

		List<GameRecommend> list = gameBusiness.queryGameRecommend(null, page);
		mv.addObject("list", list);
		mv.addObject("page", page);

		return mv;
	}

	@RequestMapping("/manager/adminGameAdd")
	public ModelAndView adminGameAdd(PaginationMore page,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("manager/adminGameAdd");
		String id = request.getParameter("id");
		if (id != null && !"".equals(id)) {
			Game game = gameBusiness.getGameDetail(id);
			mv.addObject("game", game);

			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("gameId", game.getId());
			// 正方形图片
			condition.put("type", GamePic.Square);
			List<GamePic> list = gameBusiness.queryGamePics(condition);
			mv.addObject("list1", list);
			// 矩形图片
			condition.put("type", GamePic.Rectangle);
			list = gameBusiness.queryGamePics(condition);
			mv.addObject("list2", list);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<GameType> gameTypeList = gameBusiness.queryAllGameType(map);
		mv.addObject("gameTypeList", gameTypeList);
		return mv;
	}

	@RequestMapping("/manager/adminVersionAdd")
	public ModelAndView adminVersionAdd(PaginationMore page,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("manager/adminVersionAdd");
		String id = request.getParameter("id");
		if (id != null && !"".equals(id)) {
			Version version = gameBusiness.getVersion(id);
			mv.addObject("version", version);
		}
		return mv;
	}

	@RequestMapping("/manager/adminGameDealAdd")
	public ModelAndView adminGameDealAdd(PaginationMore page,
			HttpServletRequest request) {
		String id = request.getParameter("id");
		String gameName = request.getParameter("gameName");
		String type = request.getParameter("type");
		String category = request.getParameter("category");
		String gameDescrip = request.getParameter("gameDescrip");
		String gameUrl = request.getParameter("gameUrl");
		String stars = request.getParameter("stars");
		String isMember = request.getParameter("isMember");
		Game game = new Game();
		if (id != null && !"".equals(id)) {
			game = gameBusiness.getGameDetail(id);
		} else {
			game.setId(UUID.randomUUID().toString());
		}
		game.setGameName(gameName);
		game.setStars(stars);
		Date CreateTime = null;
		try {
			CreateTime = sdf.parse(sdf.format(new Date()));
			game.setCreateTime(CreateTime);
		} catch (Exception e) {
			logger.error("时间转换失败：" + new Date());
		}
		game.setCategory(Integer.parseInt(category));
		game.setType(type);
		game.setCreateTime(CreateTime);
		game.setGameDescrip(gameDescrip);
		game.setGameUrl(gameUrl);
		game.setOrderBy(999L);
		game.setPlayCounts(0);
		game.setClickNum(0);
		game.setIsMember(Integer.parseInt(isMember));

		// 判断新增还是更新
		if (id != null && !"".equals(id)) {
			gameBusiness.updateGame(game);
		} else {
			gameBusiness.addGameByJob(game);
		}

		// 跳转页面
		ModelAndView mv = new ModelAndView();
		mv.setViewName("manager/adminGameList");
		Map<String, Object> map = new HashMap<String, Object>();

		List<Game> list = gameBusiness.queryGames(map, page);
		mv.addObject("list", list);
		mv.addObject("page", page);
		return mv;
	}

	@RequestMapping("/manager/adminGamePolicyAdd")
	public ModelAndView adminGamePolicyAdd(PaginationMore page,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("manager/adminGamePolicyAdd");
		String gameId = request.getParameter("gameid");

		if (gameId != null && !"".equals(gameId)) {
			GamePolicy gamePolicy = new GamePolicy();
			gamePolicy = gameBusiness.getGamePolicyByGameId(gameId);
			mv.addObject("gamePolicy", gamePolicy);
		}

		return mv;
	}

	@RequestMapping("/manager/adminGamePolicyDealAdd")
	public ModelAndView adminGameDealPolicyAdd(PaginationMore page,
			HttpServletRequest request) {
		String gameId = request.getParameter("gameId");
		String clicks = request.getParameter("clicks");
		String deposit = request.getParameter("deposit");
		String reward = request.getParameter("reward");
		String punish = request.getParameter("punish");

		GamePolicy gamePolicy = new GamePolicy();
		gamePolicy = gameBusiness.getGamePolicyByGameId(gameId);
		boolean exis = true;
		if (gamePolicy == null) {
			gamePolicy = new GamePolicy();
			gamePolicy.setId(UUID.randomUUID().toString());
			exis = false;
		}
		gamePolicy.setClicks(Integer.parseInt(clicks));
		gamePolicy.setDeposit(Integer.parseInt(deposit)); // 押金
		gamePolicy.setGameId(gameId);
		gamePolicy.setPunish(Integer.parseInt(punish));
		gamePolicy.setReward(Double.parseDouble(reward));

		// 判断新增还是更新
		if (exis == true) {
			gameBusiness.updateGamePolicy(gamePolicy);
		} else {
			gameBusiness.addGamePolicy(gamePolicy);
		}

		// 跳转页面
		ModelAndView mv = new ModelAndView();
		mv.setViewName("manager/adminGameList");
		Map<String, Object> map = new HashMap<String, Object>();

		List<Game> list = gameBusiness.queryGames(map, page);
		mv.addObject("list", list);
		mv.addObject("page", page);
		return mv;
	}

	@RequestMapping("getUserAmount")
	public @ResponseBody
	BigDecimal getUserAmount() {
		BigDecimal amount = null;
		User user = SpringSecurityUtil.getCurrentUser();
		if (user != null) {
			UserWallet userWallet = userWalletBusiness
					.getUserWalletByUserId(user.getId());
			amount = userWallet.getAmount();
		} else {
			amount = new BigDecimal(0);
		}

		return amount;
	}

	@RequestMapping("getUser")
	public @ResponseBody
	int getUser() {
		int exist = 0;
		User user = SpringSecurityUtil.getCurrentUser();
		if (user != null) {
			exist = 1;
		} else {
			exist = 0;
		}

		return exist;
	}

	@RequestMapping("/updateClickNum")
	public ModelAndView updateClickNum(HttpServletRequest request) {
		String id = request.getParameter("id");
		Game game = gameBusiness.getGameById(id);

		if (game != null) {
			int clicknum = game.getClickNum();
			clicknum = clicknum + 1;
			game.setClickNum(clicknum);
			gameBusiness.updateGame(game);
		}
		return null;
	}

	@RequestMapping("/manager/updateOrderBy")
	public ModelAndView updateOrderBy(HttpServletRequest request) {
		String id = request.getParameter("id");
		String orderBy = request.getParameter("orderBy");
		GameRecommend gameRecommend = gameBusiness.getGameRecommendById(id);

		if (gameRecommend != null) {
			gameRecommend.setOrderBy(Long.parseLong(orderBy));
			gameBusiness.updateGameRecommend(gameRecommend);
		}
		return null;
	}

	@RequestMapping("lp.html")
	public String lp() {
		return ".lp";
	}

	@RequestMapping("payment.html")
	public @ResponseBody
	String payment(HttpServletRequest request) throws Exception {
		// 查看用户是否登录
		User user = SpringSecurityUtil.getCurrentUser();

		if (user == null) {
			return "0"; // 未登录
		} else {
			// user =
			// userBusiness.findUserByMoblieNumber(user.getMobileNumber());
			// Map<String,Object> map = new HashMap<String,Object>();
			// map.put("userId",user.getId());
			// map.put("category",1); //注册领10纳币活动
			// List<LpRecord> lplist = lpRecordBusiness.queryLpRecord(map);
			// if(lplist.size() == 0){
			// boolean flag = gameBusiness.payment(request.getRemoteAddr());
			// if(flag == true){
			// LpRecord lpRecord = new LpRecord();
			// lpRecord.setId(UUID.randomUUID().toString());
			// lpRecord.setAddTime(sdf.parse(sdf.format(new Date())));
			// lpRecord.setCategory(1);
			// lpRecord.setUserId(user.getId());
			// lpRecordBusiness.addLpRecord(lpRecord);
			// return "success";
			// }else{
			// return "fail";
			// }
			// }else{
			// return "repeat";
			// }
			return "over";
		}
	}

	@RequestMapping("ifpayment.html")
	public @ResponseBody
	String ifpayment(HttpServletRequest request) throws Exception {
		// 查看用户是否登录
		User user = SpringSecurityUtil.getCurrentUser();

		if (user == null) {
			return "0"; // 未登录
		} else {
			user = userBusiness.findUserByMoblieNumber(user.getMobileNumber());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", user.getId());
			map.put("category", 1); // 注册领10纳币活动
			List<LpRecord> lplist = lpRecordBusiness.queryLpRecord(map);
			if (lplist.size() == 0) {
				return "not";
			} else {
				return "repeat";
			}

		}
	}

	@RequestMapping("ifconfirm.html")
	public @ResponseBody
	String ifconfirm(HttpServletRequest request) throws Exception {
		// 查看用户是否登录
		User user = SpringSecurityUtil.getCurrentUser();

		if (user == null) {
			return "0"; // 未登录
		} else {
			user = userBusiness.findUserByMoblieNumber(user.getMobileNumber());
			// 判断是否领取过活动游戏
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("gameId", "953b65d9-a322-4b57-abc9-33137b8c346c");
			map.put("userId", user.getMobileNumber());

			List<UserGameRelation> oldlist = gameBusiness
					.queryUserGameRelationList(map);

			if (oldlist.size() == 0) {
				return "not";
			} else {
				return "repeat";
			}

		}
	}

	@RequestMapping("/manager/gameRecord")
	public String gameRecord(HttpServletRequest request, PaginationMore page,
			Model model) {
		Map<String, Object> condition = new HashMap<String, Object>();
		String status = request.getParameter("status");
		String userId = request.getParameter("userId");
		String actionStartTime = request.getParameter("actionStartTime");
		String actionEndTime = request.getParameter("actionEndTime");
		if (!"".equals(actionStartTime) && actionStartTime != null) {
			condition.put("actionStartTime", actionStartTime + " 00:00:00");
		}
		if (!"".equals(actionEndTime) && actionEndTime != null) {
			condition.put("actionEndTime", actionEndTime + " 23:59:59");
		}

		condition.put("status", status);
		condition.put("userId", userId);
		String sort2 = request.getParameter("sort");
		String sortseq = request.getParameter("sortseq");
		condition.put("sort2", sort2);
		condition.put("sortseq", sortseq);
		model.addAttribute("actionStartTime", actionStartTime);
		model.addAttribute("actionEndTime", actionEndTime);
		model.addAttribute("status", status);
		model.addAttribute("userId", userId);
		page.setPageSize(10);
		List<UserGameRelation> list = gameBusiness
				.queryAllGameTaskDetailsOptimize(condition, page);
		// List<UserGameRelation> list =
		// gameBusiness.queryAllGameTaskDetails(condition, page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);

		return "manager/gameRecordList";
	}

	@RequestMapping("/manager/adminVersionDealAdd")
	public ModelAndView adminVersionDealAdd(PaginationMore page,
			HttpServletRequest request) {
		String id = request.getParameter("id");
		Version version = new Version();
		if (!"".equals(id) && id != null) {
			version = gameBusiness.getVersion(id);
		}
		String apkName = request.getParameter("apkName");
		String versionCode = request.getParameter("versionCode");
		try {
			version.setDownloadPath(apkName);
			version.setPublishTime(String.valueOf(new Date().getTime()));
			version.setVersionCode(Integer.parseInt(versionCode));
			if (!"".equals(id) && id != null) {
				versionBusiness.updateVersion(version);
			} else {
				versionBusiness.addVersion(version);
			}

		} catch (BusinessException e) {
			logger.error(e.getMessage());
		}

		// 跳转页面
		ModelAndView mv = new ModelAndView();
		mv.setViewName("manager/adminVersionList");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Version> list = gameBusiness.queryVersions(map, page);
		mv.addObject("list", list);
		mv.addObject("page", page);
		return mv;
	}

	/**
	 * 后台安卓客户端信息删除
	 */
	@RequestMapping("/manager/adminVersionDel")
	public ModelAndView adminVersionDel(PaginationMore page,
			HttpServletRequest request) {
		String ids = request.getParameter("ids");
		if (ids != null && !"".equals(ids)) {
			String[] id = ids.split(",");

			for (int i = 0; i < id.length; i++) {
				Version version = gameBusiness.getVersion(id[i]);
				if (version != null) {
					gameBusiness.delVersionById(id[i]);
				}

			}
		}

		// 跳转页面
		ModelAndView mv = new ModelAndView();
		mv.setViewName("manager/adminVersionList");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Version> list = gameBusiness.queryVersions(map, page);
		mv.addObject("list", list);
		mv.addObject("page", page);
		return mv;
	}

	@RequestMapping(value = "/getRest")
	public @ResponseBody
	Object getChristmasGameRest() {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("rest", gameBusiness.getChristmasGameRest());
		return res;
	}

}
