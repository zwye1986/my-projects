package com.venada.efinance.controller;

import com.venada.efinance.business.*;
import com.venada.efinance.common.controller.BaseController;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.enumtype.UserStatus;
import com.venada.efinance.pojo.*;
import com.venada.efinance.security.SpringSecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class UserManagerController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(UserManagerController.class);

	@Autowired
	private UserBusiness userBusinessImpl;
	@Autowired
	private UserDetailBusiness userDetailBusinessImpl;
	@Autowired
	private UserWalletBusiness UserWalletBusinessImpl;
	@Autowired
	private RoleBusiness  roleBusinessImpl;
	@Autowired
	private UserAssRoleBusiness  userAssRoleBusinessImpl;
	@Autowired
	private ProvinceBusiness provinceBusinessImpl;
	@Autowired
	private CityBusiness cityBusinessImpl;
	@Autowired
	private AreaBusiness areaBusinessImpl;
	@Autowired
	private BankCardBusiness bankCardBusinessImpl;
	@Autowired
	private SecurityCenterBusiness securityCenterBusinessImpl;

	@RequestMapping("manager/userList")
	public String userList(PaginationMore page, Model model,
			HttpServletRequest request) {
		Map<String, Object> condition = setMapCondition(request);
		page.setPageSize(10);
		String sortseq=request.getParameter("sortseq");
		String sort=request.getParameter("sort");
		String startAmount = (String) condition.get("startAmount");
		String endAmount = (String) condition.get("endAmount");
		if ((startAmount != null) && (!startAmount.equals(""))
				&&(endAmount != null) && (!endAmount.equals(""))) {
			Integer startA = Integer.valueOf(startAmount);
			Integer endA = Integer.valueOf(endAmount);
			if (startA >= endA) {
				condition.put("startAmount", endA);
				condition.put("endAmount", startA);
			}
		}
		String startTime = (String) condition.get("startTime");
		String endTime = (String) condition.get("endTime");
		if ((startTime != null) && (!startTime.equals("")) || (endTime != null)
				&& (!endTime.equals(""))) {
			condition.put("startTime", startTime.concat(" 00:00:00"));
			condition.put("endTime", endTime.concat(" 23:59:59"));
		}
		condition.put("sortseq", sortseq);
		condition.put("sort", sort);
		List<User> list = userBusinessImpl.listAllUsers(page, condition);
		List<User>userlist=new ArrayList<User>();
		for(User user:list){
			if(securityCenterBusinessImpl.isOpen(user.getId())){
				user.setVipTag("1");
			}else{
				user.setVipTag("0");
			}
			userlist.add(user);
		}
		model.addAttribute("userList", userlist);
		model.addAttribute("page", page);
		model.addAttribute("condition", condition);
		return "manager/userManagerlist";
	}
	
	
	@RequestMapping(value = { "manager/deleteUserAllInfoById" })
	@ResponseBody
	public Map<String, Object> deleteUserAllInfo(
			@RequestParam(value = "Id", required = false) String Id) {
		logger.info("删除用户"+Id);
		User user =userBusinessImpl.findUserById(Id);
		Map<String, Object> data = new HashMap<String, Object>();
		if (user==null||user.equals("")) {
			data.put("msgCode", "false");
			data.put("msg", "用户不存在");
		}
		Map <String,Object> condition=new HashMap<String,Object>();
		condition.put("mobileNumber", user.getMobileNumber());
		try {
			userBusinessImpl.deleteUserAllInfo(condition);
			data.put("msgCode", "true");
			data.put("msg", "删除用户成功！");
		} catch (BusinessException e) {
			data.put("msgCode", "false");
			data.put("msg", "用户删除失败");
		}
		return data;
	}
	
	@RequestMapping(value = { "manager/freedomUserById" })
	@ResponseBody
	public Map<String, Object> freedomUserById(
			@RequestParam(value = "Id", required = false) String Id,
			@RequestParam(value = "type", required = false) String type) {
		logger.info("设置用户可以自由套现" + Id);
		User user = userBusinessImpl.findUserById(Id);
		Map<String, Object> data = new HashMap<String, Object>();
		if (user == null || user.equals("")) {
			data.put("msgCode", "false");
			data.put("msg", "用户不存在");
		}

		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("id", user.getId());
		if (type.equals("freedom")) {
			condition.put("status", UserStatus.freedom.getIndex());
		} else if (type.equals("unfreedom")) {
			condition.put("status", UserStatus.Normal.getIndex());
		} else {
			condition.put("status", UserStatus.Normal.getIndex());
		}
		try {
			userBusinessImpl.freedomUserById(condition);
			data.put("msgCode", "true");
			data.put("msg", "设置用户成功！");
		} catch (BusinessException e) {
			data.put("msgCode", "false");
			data.put("msg", "用户设置失败");
		}
		return data;
	}
	
	
	@RequestMapping("manager/user/showDetail")
	public String showDetail(Model model,@RequestParam(value = "id", required = false) String Id) {
		User user=userBusinessImpl.findUserById(Id);
		UserDetail userDetail=new UserDetail();
		UserWallet userWallet=new UserWallet() ;
		Province province=new Province();
		City city=new City();
		Area area=new Area();
		List<BankCard> bankcardlist=new ArrayList<BankCard>();
		Map<String, Object> condition = new HashMap<String, Object>();
		 List<User> userlist=new ArrayList<User>();
		if(user!=null){
			String uid=user.getId();
			 userDetail=userDetailBusinessImpl.findUserDetailByUserId(uid);
			 userWallet=  UserWalletBusinessImpl.getUserWalletByUserId(uid);
			 province=provinceBusinessImpl.getProvinceById(userDetail.getLiveProvince());
			 city=cityBusinessImpl.getCityById(userDetail.getLiveCity());
			 area=areaBusinessImpl.getAreaById(userDetail.getLiveArea());
			 bankcardlist =bankCardBusinessImpl.getAllByUserId(uid);
			 if(user.getInviteCodeSelf()!=null){
				 condition.put("inviteCodeFromOther", user.getInviteCodeSelf());
				 userlist=userBusinessImpl.listUserByInviteCodeFromOther(condition);
			 }
		}
		model.addAttribute("bankcardlist",bankcardlist);
		model.addAttribute("province",province);
		model.addAttribute("area",area);
		model.addAttribute("city",city);
		model.addAttribute("user", user);
		model.addAttribute("userDetail", userDetail);
		model.addAttribute("userlist", userlist);
		model.addAttribute("userWallet", userWallet);
		return "manager/showUserDetail";
	}
	
	@RequestMapping(value = "manager/getRoleTree")
	@ResponseBody
	public Object getResourceTree(@RequestParam(value = "userId", required = false) String userId) {
		List<Role> list = new ArrayList<Role>();
		List<Role> haslist=new ArrayList<Role>();
		haslist=roleBusinessImpl.getRoleByUserId(userId);
		list=roleBusinessImpl.queryAllRole();
		for (Role res : list) {
			   res.setChecked(false);
			for (Role r : haslist) {
				if (res.getId().equals(r.getId())) {
					res.setChecked(true);
				}
			}
		}
		return list;
	}
	
	@RequestMapping(value = { "manager/getUserById" })
	@ResponseBody
	public Map<String, Object> getUserById(
			@RequestParam(value = "Id", required = false) String Id) {
		User user =userBusinessImpl.getUserRole(Id);
		String roleName="";
		String roleId="";
		List<Role> roleList = user.getUserRoles();
		for (int i = 0; i < roleList.size(); i++) {
			if (i == roleList.size()-1) {
				roleName += roleList.get(i).getRoleName();
				roleId+= roleList.get(i).getId();
			} else {
				roleName += roleList.get(i).getRoleName() + ",";
				roleId+= roleList.get(i).getId()+",";
			}
		}
		Map<String, Object> data = new HashMap<String, Object>();
		if (user==null||user.equals("")) {
			data.put("msgCode", "false");
		} else {
			data.put("msgCode", "true");
			data.put("id",user.getId());
			data.put("name",user.getNickName());
			data.put("mobileNumber",user.getMobileNumber());
			data.put("roleName",roleName);
			data.put("roleId",roleId);
		}
		return data;
	}
	
	
	
	@RequestMapping(value = "manager/updateUser")
	@ResponseBody
	@Transactional
	public Map<String, Object> updateUser(User u) {
		Map<String, Object> data = new HashMap<String, Object>();
		List<UserAssRole> userAssRoleList = new ArrayList<UserAssRole>();
		if (u.getMobileNumber() != null) {
			u.setName(u.getName());
			u.setId(u.getId());
			u.setModifyTime(new Date());
			u.setModifyBy(SpringSecurityUtil.getCurrUsername());
			String[] resSelect = u.getRoleSelid().split(",");
			try {
				userBusinessImpl.deleteUserAssRoleByUserId(String.valueOf(u.getId()));
				if (resSelect.length > 0 && resSelect[0] != ""&& resSelect[0].length() > 0) {
					for (String roleId : resSelect) {
						UserAssRole r = new UserAssRole();
						r.setUserId(u.getId());
						r.setRoleId(Integer.valueOf(roleId));
						userAssRoleList.add(r);
					}
					userAssRoleBusinessImpl.insertUserAssRole(userAssRoleList);
				}
				data.put("msgcode", "true");
				data.put("msg", "用户更新成功");
			} catch (Exception e) {
				data.put("msgcode", "false");
				data.put("msg", "用户更新失败");
			}
		} else {
			data.put("msgcode", "false");
			data.put("msg", "用户名为空");
		}
		return data;
	}
}
