package com.venada.efinance.controller;


import com.venada.efinance.business.*;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.*;
import com.venada.efinance.security.SpringSecurityUtil;
import com.venada.efinance.sms.SmsService;
import com.venada.efinance.util.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

 
@Controller
@SessionAttributes({"PHOTO_PATH","LEVEL","WALLETAMOUNT","DEALDETAILAMOUNT","VIPTAG"})
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	@Autowired
	private UserBusiness userBusiness;
	@Autowired
	private UserDetailBusiness userDetailBusiness;
	@Autowired
	private GameBusiness gameBusiness;
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	@Autowired
	private UserWalletBusiness userWalletBusiness;
	@Autowired
	private DealDetailBusiness dealDetailBusiness;
	@Autowired
	private AdvertisingBusiness advertisingBusiness;
	@Autowired
	private UserQuestionBusiness userQuestionBusiness;
	@Autowired
	private ValidateCodeBusiness validateCodeBusiness;
	@Autowired
	private RechargeRecordBusiness rechargeRecordBusiness;
	@Autowired
	private SecurityCenterBusiness securityCenterBusiness ;
	@Autowired
	private SignUpBusiness signUpBusinessImpl;
	@Autowired
	private SystemConfigBusiness systemConfigBusiness;
	

	@RequestMapping(value = { "/", "/index", "/index.html", "/index.jsp"})
	public String newIndex(HttpServletRequest request, Model model,@RequestParam(value = "inviteCode", required = false) String inviteCode) {
		HttpSession session = request.getSession();
		if (inviteCode != null && !inviteCode.equals("")) {
			session.setAttribute("inviteCode", inviteCode);
		}

		int vipTag;
		User user = SpringSecurityUtil.getCurrentUser();
        List<Advertising> advertisings = advertisingBusiness.getAdvertiseListByPosition(1);
        model.addAttribute("advertisings",advertisings);
		if(user != null){
			model.addAttribute("user", user);
			UserDetail userDetail = userDetailBusiness.findUserDetailByMoblieNumber(user.getMobileNumber());
			 if(userDetail !=null && userDetail.getPhoto() != null){
				 model.addAttribute("PHOTO_PATH", userDetail.getUserid()+"/getPhoto");
			 }
			model.addAttribute("LEVEL", user.getLevel());
			if(securityCenterBusiness.isOpen(user.getId())){
				vipTag=1;
			}else{
				vipTag=0;
			}

			model.addAttribute("VIPTAG",vipTag);
			model.addAttribute("WALLETAMOUNT", userWalletBusiness.getAmountByUserId(user.getId()));
			model.addAttribute("DEALDETAILAMOUNT", dealDetailBusiness.getDealDetailAmountByUserId(user.getId()) == null ? new BigDecimal(0) :  dealDetailBusiness.getDealDetailAmountByUserId(user.getId()));
		}

		
		
		return ".newIndex";
	}
	
	@RequestMapping(value={"indexRecommend"})
	public String indexRecommend(Model model){
		model.addAttribute("recommendGames", gameBusiness.queryGameRecommendInIndex());
		return "indexRecommend";
	}
	
	@RequestMapping(value={"hotRecommend"})
	public String hotRecommend(Model model){
		model.addAttribute("recommendGames", gameBusiness.queryGameRecommendInIndex());
		return "hotRecommend";
	}
	
	@RequestMapping(value={"rechargeRecord"})
	public String rechargeRecord(Model model){
		//充值记录
		List<RechargeRecord> rechargeRecordList = rechargeRecordBusiness.getRechargeRecordsForIndex();
		model.addAttribute("rechargeRecordList",rechargeRecordList);
		return "rechargeRecord";
	}
	
	@RequestMapping(value = "ios.html" )
	public String ios(){
		return ".ios";
	}

	@RequestMapping(value = "inputMobileNumber.html" )
	public String findPassword() {
		return ".resetPassword";
	}
	

    @RequestMapping(value="/validateCellMessage.html" , method = RequestMethod.POST)
    public @ResponseBody    Object validateCellMessage(HttpServletRequest request,HttpSession session ,Model model){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        try {
            String mobileNumber;
            if(request.getParameter("mobileNumber") == null || "".equals(request.getParameter("mobileNumber").replaceAll("\\s*", "")) || !SystemUtils.checkMobileNumber(request.getParameter("mobileNumber").replaceAll("\\s*", ""))){
                resultMap.put("resCode", "0");
                resultMap.put("resMsg", "手机号码错误！");
                return resultMap;
            }else{
                mobileNumber = request.getParameter("mobileNumber").replaceAll("\\s*", "");
            }
            User user = userBusiness.findUserByMoblieNumber(mobileNumber);
            if(user == null){
                resultMap.put("resCode", "0");
                resultMap.put("resMsg", "该手机号码不存在！");
                return resultMap;
            }
            UserQuestion userQuestion = userQuestionBusiness.getAnswerByUserid(user.getId());
            model.addAttribute("userQuestion", userQuestion);
            String messageCode = request.getParameter("code");
            String newPwd = request.getParameter("newPwd");
            String newPwdRepeat = request.getParameter("newPwdRepeat");


            //校验短信验证码
            if (messageCode == null || "".equals(messageCode.replaceAll("\\s*", ""))) {
                resultMap.put("resCode", "0");
                resultMap.put("resMsg", "请输入短信验证码！");
                return resultMap;
            } else {
                messageCode = messageCode.replaceAll("\\s*", "");
                List<ValidateCode> validateCodeList = validateCodeBusiness.queryLastestCodeByMobileNumber(mobileNumber);
                if(validateCodeList.size() == 0){
                    resultMap.put("resCode", "0");
                    resultMap.put("resMsg", "短信验证码错误或已失效！");
                    return resultMap;
                }
                String _messageCode = validateCodeList.get(0).getCode();

                if(!messageCode.equalsIgnoreCase(_messageCode)){
                    resultMap.put("resCode", "0");
                    resultMap.put("resMsg", "短信验证码错误！");
                    return resultMap;
                }
            }

            if (newPwd == null || "".equals(newPwd.replaceAll("\\s*", ""))) {
                resultMap.put("resCode", "0");
                resultMap.put("resMsg", "请输入新密码！");
                return resultMap;
            } else {
                newPwd = newPwd.replaceAll("\\s*", "");
                if(!SystemUtils.checkPassword(newPwd)){
                    resultMap.put("resCode", "0");
                    resultMap.put("resMsg", "密码长度为8～20位,且必须包含数字、字母！");
                    return resultMap;
                }
            }

            if (newPwdRepeat == null || "".equals(newPwdRepeat.replaceAll("\\s*", ""))) {
                resultMap.put("resCode", "0");
                resultMap.put("resMsg", "请输入确认密码！");
                return resultMap;
            } else {
                newPwdRepeat = newPwdRepeat.replaceAll("\\s*", "");
            }

            if (newPwd.equals(newPwdRepeat)) {
                user.setPassword(md5PasswordEncoder.encodePassword(newPwd, ""));
                userBusiness.updateUserByMobileNumber(user);
                session.setAttribute("loginMsg", "密码修改成功!请用新密码重新登录。");

                resultMap.put("resCode", "1");
            } else {
                resultMap.put("resCode", "0");
                resultMap.put("resMsg", "2次输入不一致，请重新输入！");
                return resultMap;
            }
        } catch (BusinessException e) {
            logger.error(e.getMessage());
        }
        return resultMap;

    }

	@RequestMapping(value= "/help")
	public String toNewHelpCenter(){
		return ".newHelp";
	}
	
	
	
	@RequestMapping(value= "/level/rule")
	public String toLevelrules(){
		return ".levelRule";
	}

	@RequestMapping(value="/android_detail.html")
	public String androidDetail(){
		return ".android";
	}
	
	@RequestMapping(value="/android.html")
	public String android(){
		return ".androidV2";
	}
	
	@RequestMapping(value="/map.html")
	public String map(){
		return ".map";
	}
	
	@RequestMapping(value="/feslogo.html")
	public String feslogo(){
		return ".feslogo";
	}
	
	@RequestMapping(value="/video.html")
	public String video(){
		return ".video";
	}
	
	@RequestMapping(value="/help_about.html")
	public String help_about(){
		return ".help_about";
	}
	
	@RequestMapping(value="/process.html")
	public String process(){
		return ".process";
	}
	
	@RequestMapping(value="/help_partner.html")
	public String help_partner(){
		return ".help_partner";
	}
	
	@RequestMapping(value="/help_contact.html")
	public String help_contact(){
		return ".help_contact";
	}
	


	@RequestMapping(value="/shop.html")
	public String shop(Model model){
		List<SignUp>signUplist = signUpBusinessImpl.querySignUpList(null);
		model.addAttribute("signUplist", signUplist);
		return "/shop_activity";
	}
	
	@RequestMapping(value = { "xy.html"})
	public String xy(){
		return "xy";
	}

    @RequestMapping(value="/loadRanks")
    public String loadRanks(Model model){
        Map<String,Integer> limits = new HashMap<String,Integer>();
        limits.put("begin", 0);
        limits.put("end", 5);
        model.addAttribute("wealthTop", userBusiness.getTopWealth(limits));
        model.addAttribute("incomeTop", userBusiness.getTopIncome(limits));
        model.addAttribute("levelTop", userBusiness.getTopLevel(limits));
        model.addAttribute("activeTop", userBusiness.getTopActive(limits));
//        model.addAttribute("inviteTop", userBusiness.getTopInvite(limits));
        return "index_ranks";
    }

    @RequestMapping(value="/loadAdver")
    public String loadAdver(Model model){
        List<Advertising> advertisings =  advertisingBusiness.getAdvertiseListByPosition(2);
        model.addAttribute("advertisings",advertisings);
        return "adver";
    }

    @RequestMapping(value = "findPwd.html" )
    public String findPassword(HttpSession session) {
        session.invalidate();
        return ".findPwd";
    }

    @RequestMapping(value="/{mobileNumber}/setCode" , method = RequestMethod.POST)
    public @ResponseBody
    Object setCode(HttpSession session,@PathVariable String mobileNumber){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        try {
            String code = SystemUtils.randomCheckcode("23456789ABCDEFGHJKLMNPRSTUVWXYZ",6);
            validateCodeBusiness.sendSmsByTemple(mobileNumber, code,"MB-2014011435");
            session.setAttribute("smsCode",code);
        } catch (BusinessException e) {
            resultMap.put("resCode", 0);
            resultMap.put("resMsg", "处理失败，请重试！");
            logger.error(e.getMessage());
        }
        resultMap.put("resCode", 1);
        return resultMap;
    }

    @RequestMapping(value="/agree.html")
    public String agree(){
        return ".agree";
    }
    
    @RequestMapping(value="/newyear.html")
    public String newyear(Model model){
    	SystemConfig systemConfig = null;
    	systemConfig = systemConfigBusiness.getSystemConfig("120");
    	model.addAttribute("id1", systemConfig.getParamValue());
    	systemConfig = systemConfigBusiness.getSystemConfig("121");
    	model.addAttribute("id2", systemConfig.getParamValue());
    	systemConfig = systemConfigBusiness.getSystemConfig("122");
    	model.addAttribute("id3", systemConfig.getParamValue());
        return ".newyear";
    }

}
