package com.venada.efinance.controller;

import com.venada.efinance.business.GameBusiness;
import com.venada.efinance.business.SystemConfigBusiness;
import com.venada.efinance.business.UserBusiness;
import com.venada.efinance.business.UserWalletBusiness;
import com.venada.efinance.pojo.Game;
import com.venada.efinance.pojo.SystemConfig;
import com.venada.efinance.pojo.User;
import com.venada.efinance.pojo.UserWallet;
import com.venada.efinance.security.SpringSecurityUtil;
import com.venada.efinance.util.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class VipController {

	@Autowired
	private SystemConfigBusiness systemConfigBusiness;
	@Autowired
	private UserBusiness userBusiness;
	@Autowired
	private UserWalletBusiness userWalletBusiness;
	private static final Logger logger = LoggerFactory
			.getLogger(VipController.class);
	@Autowired
	private GameBusiness gameBusiness;

	@RequestMapping("vip_index.html")
	public String vipIndex(Model model) {
		SystemConfig systemConfig = systemConfigBusiness.getSystemConfig("104");
		model.addAttribute("month_cost", systemConfig.getParamValue());
		return ".vipIndex";
	}

	@RequestMapping("game_privilege.html")
	public String gamePrivilege(HttpServletRequest request, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", 25);
		List<Game> list = gameBusiness.queryVipGamesForSimple(map);
		model.addAttribute("alllist", list);

		// 随机推荐
		Collections.shuffle(list);
		list = list.subList(0, 6);
		model.addAttribute("randomlist", list);

		// 角色扮演
		map.put("typename1", "%角色扮演%");
		map.put("typename2", "%角色扮演%");
		list = gameBusiness.queryVipGamesForSimple(map);
		model.addAttribute("rolelist", list);
		// 战争策略
		map.put("typename1", "%战争%");
		map.put("typename2", "%策略%");
		list = gameBusiness.queryVipGamesForSimple(map);
		model.addAttribute("warlist", list);
		// 休闲竞技
		map.put("typename1", "%休闲%");
		map.put("typename2", "%竞技%");
		list = gameBusiness.queryVipGamesForSimple(map);
		model.addAttribute("leisuretournamentlist", list);
		// 模拟经营
		map.put("typename1", "%模拟%");
		map.put("typename2", "%经营%");
		list = gameBusiness.queryVipGamesForSimple(map);
		model.addAttribute("businesssimulationlist", list);

		// 会员游戏特权页面分页查询
		map = new HashMap<String, Object>();
		map.put("ismember", 1);
		String sort = request.getParameter("sort");
		map.put("sort", sort);
		model.addAttribute("sort", sort);
		// 计算总共有多少页
		int gamesCounts = gameBusiness.getGamesCounts(map);

		int gamesPage = (gamesCounts / 6) + (gamesCounts % 6 > 0 ? 1 : 0);
		model.addAttribute("gamesPage", gamesPage);

		int page = 1;

		map.put("limitCount", 6);
		map.put("limitIndex", (page - 1) * 6);
		List<Game> gameList = gameBusiness.queryGames(map);
		model.addAttribute("gamelist", gameList);
		model.addAttribute("page", page);

		map = new HashMap<String, Object>();
		map.put("ismember", 1);
		map.put("sort", "clickNum");
		map.put("count", 5);
		List<Game> hotList = gameBusiness.queryGames(map);
		model.addAttribute("hotlist", hotList);

		map.put("sort", "looks");
		map.put("count", 14);
		List<Game> lookList = gameBusiness.queryGames(map);
		model.addAttribute("looklist", lookList);
		SystemConfig systemConfig = systemConfigBusiness.getSystemConfig("104");
		model.addAttribute("month_cost", systemConfig.getParamValue());
		return ".gamePrivilege";
	}

	@RequestMapping(value = "/showVipGame", method = RequestMethod.GET)
	public ModelAndView showGame(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		try {
			mv.setViewName("vip/vipGamelist");

			Map<String, Object> map = new HashMap<String, Object>();
			// 会员游戏
			map.put("ismember", 1);

			String sort = request.getParameter("sort");

			map.put("sort", sort);

			mv.addObject("sort", sort);

			// 计算总共有多少页
			int gamesCounts = gameBusiness.getGamesCounts(map);

			int gamesPage = (gamesCounts / 6) + (gamesCounts % 6 > 0 ? 1 : 0);
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

			map.put("limitCount", 6);
			map.put("limitIndex", (page - 1) * 6);
			List<Game> gameList = gameBusiness.queryGames(map);
			mv.addObject("gamelist", gameList);

			mv.addObject("page", page);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return mv;
	}

	@RequestMapping("vip_privilege.html")
	public String vipPrivilege(Model model) {
		SystemConfig systemConfig = systemConfigBusiness.getSystemConfig("104");
		model.addAttribute("month_cost", systemConfig.getParamValue());
		return ".vipPrivilege";
	}

	@RequestMapping("grow_system.html")
	public String growSystem() {
		return ".growSystem";
	}

	@RequestMapping("all_pri_vip.html")
	public String allPriVip() {
		return ".allPriVip";
	}

	@RequestMapping("hy_detail.html")
	public String hyDetail() {
		return ".hyDetail";
	}

	@RequestMapping("giveVip.html")
	public String giveVip(Model model) {
		SystemConfig systemConfig = systemConfigBusiness.getSystemConfig("104");
		model.addAttribute("month_cost", systemConfig.getParamValue());
		return ".giveVip";
	}

	@RequestMapping("user/vip/{mobileNumber}/{mode}/{times}/payForGivenVip")
	public String payForGivenVip(@PathVariable String mobileNumber,
			@PathVariable int mode, @PathVariable int times, Model model,
			HttpServletRequest request) {
		try {
			int months = 0;
			if (mode == 1) {// 月
				months = times;
			} else if (mode == 2) {
				months = times * 12;
			}
			int needToPay = count(mode, times);
			User user = SpringSecurityUtil.getCurrentUser();
			UserWallet userWallet = userWalletBusiness.getAmountByUserId(user
					.getId());
			BigDecimal balance = userWallet.getAmount();
			if (balance.compareTo(new BigDecimal(needToPay)) == -1) {
				model.addAttribute("msg", "赠送失败，您的余额不足！");
			} else {
				User userGiven = userBusiness
						.findUserByMoblieNumber(mobileNumber);
				if (userGiven == null) {
					model.addAttribute("msg", "赠送失败，用户不存在！");
				} else {
					model.addAttribute("msg", "赠送好友会员成功！");
					userWalletBusiness.payForFriends(userGiven, user,
							needToPay, request.getRemoteAddr(), userWallet,
							months);
				}
			}
		} catch (Exception e) {
			logger.error("赠送好友会员失败：" + e.getMessage());
			model.addAttribute("msg", "赠送好友会员失败，系统异常！");
		}
		return "/vip/vipTips";
	}

	@RequestMapping("/{mobileNumber}/checkMobileNumber")
	public @ResponseBody
	Object checkMobileNumber(@PathVariable String mobileNumber) {
		if (!SystemUtils.checkMobileNumber(mobileNumber)) {
			return 1;
		}
		if (userBusiness.findUserByMoblieNumber(mobileNumber) == null) {
			return 2;
		}
		return 0;
	}

	@RequestMapping("/{mode}/{times}/countAmountGivenVip")
	public @ResponseBody
	Object countAmountGivenVip(@PathVariable int mode, @PathVariable int times) {

		return count(mode, times);
	}

	private int count(int mode, int times) {
		SystemConfig systemConfig = systemConfigBusiness.getSystemConfig("104");
		int monthCost = Integer.parseInt(systemConfig.getParamValue());
		if (mode == 1) {// 月
			return monthCost * times;
		} else if (mode == 2) {
			return monthCost * times * 12;
		}
		return 0;
	}

	@RequestMapping("/user/vip/{mode}/{times}/payForRenewal")
	public String payForRenewal(@PathVariable int mode,
			@PathVariable int times, Model model, HttpServletRequest request) {
		try {
			int months = 0;
			if (mode == 1) {// 月
				months = times;
			} else if (mode == 2) {
				months = times * 12;
			}
			int needToPay = count(mode, times);
			User user = SpringSecurityUtil.getCurrentUser();
			UserWallet userWallet = userWalletBusiness.getAmountByUserId(user
					.getId());
			BigDecimal balance = userWallet.getAmount();
			if (balance.compareTo(new BigDecimal(needToPay)) == -1) {
				model.addAttribute("msg", "续费VIP会员失败，您的余额不足！");
			} else {
				model.addAttribute("msg", "续费VIP会员成功！");
				userWalletBusiness.payForRenewal(user, needToPay, request.getRemoteAddr(), userWallet, months);
			}
		} catch (Exception e) {
			logger.error("续费VIP会员失败：" + e.getMessage());
			model.addAttribute("msg", "续费VIP会员失败，系统异常！");
		}
		return "/vip/vipTips";
	}
}
