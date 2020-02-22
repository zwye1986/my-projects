package com.venada.efinance.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.venada.efinance.business.SecurityQuestionBusiness;
import com.venada.efinance.business.SystemConfigBusiness;
import com.venada.efinance.business.UserBusiness;
import com.venada.efinance.business.ValidateCodeBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.SecurityQuestion;
import com.venada.efinance.pojo.SystemConfig;
import com.venada.efinance.pojo.User;
import com.venada.efinance.pojo.ValidateCode;
import com.venada.efinance.sms.SmsService;
import com.venada.efinance.util.SystemUtils;

@Controller
@SessionAttributes("securityQuestions")
public class RegistController {

    private static final Logger logger = LoggerFactory.getLogger(RegistController.class);

    @Autowired
    private UserBusiness userBusiness;
    @Autowired
    private SystemConfigBusiness systemConfigBusiness;
    @Autowired
    private ValidateCodeBusiness validateCodeBusiness;
    @Autowired
    private SmsService smsService;
    @Autowired
    private SecurityQuestionBusiness securityQuestionBusiness;

    @RequestMapping(value="/fillNecessaryInfo",method = RequestMethod.POST)
    public String fillnecessaryInfo(Model model , HttpServletRequest request){
        HttpSession session = request.getSession();
        String mobileNumber = request.getParameter("mobileNumber");
        String code = request.getParameter("code");
        String smsCode = String.valueOf(session.getAttribute("smsCode"));
        model.addAttribute("mobileNumber", mobileNumber);
        if(code != null && code.equalsIgnoreCase(smsCode)){
            model.addAttribute("code", code);
            List<SecurityQuestion> securityQuestions = securityQuestionBusiness
                    .getAll();
            model.addAttribute("securityQuestions", securityQuestions);
            return ".fillNecessaryInfo";
        }
        return ".403";
    }
    @RequestMapping(value={"/{mobileNumber}/regist3.html" , "/{mobileNumber}/regist3" } , method = RequestMethod.POST)
    public String gotoRegistPage3(@PathVariable String mobileNumber,Model model,HttpServletRequest request){
        HttpSession session = request.getSession();
        model.addAttribute("mobileNumber", mobileNumber);
        String code = request.getParameter("code");
        String smsCode = String.valueOf(session.getAttribute("smsCode"));
        if(code != null && code.equalsIgnoreCase(smsCode)){
            model.addAttribute("code", code);
        }
        return ".regist3";
    }

    @RequestMapping(value={"/regist.html" , "/regist" } , method = RequestMethod.GET)
    public String gotoRegistPage(){
        return ".regist1";
    }
    @RequestMapping(value={ "/{mobileNumber}/regist2" } , method = RequestMethod.GET)
    public String gotoRegistPage2(@PathVariable String mobileNumber,Model model,
                                  @RequestParam(value = "inviteCode", required = false) String inviteCode,HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        if (inviteCode != null && !inviteCode.equals("")) {
            session.setAttribute("inviteCode", inviteCode);
            Cookie cookie = new Cookie("inviteCode",inviteCode );
            response.addCookie(cookie);
        }else{
            session.setAttribute("inviteCode", "");
            Cookie mycookies[] = request.getCookies();
            for (int i = 0; i < mycookies.length; i++) {
                if ("inviteCode".equalsIgnoreCase(mycookies[i].getName())) {
                    mycookies[i].setValue("");
                }
            }
        }
        model.addAttribute("mobileNumber", mobileNumber);
        return ".regist2";
    }
    @RequestMapping(value={"/regist1.html" , "/regist1" } , method = RequestMethod.POST)
    public @ResponseBody Object gotoRegistPage1(HttpServletRequest request){
        // 返回结果
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            HttpSession session = request.getSession();
            // 手机号码
            String mobileNumber = request.getParameter("mobileNumber");
            if (mobileNumber == null) {
                // 手机号码不符合规则
                resultMap.put("resCode", 0);
                resultMap.put("resMsg", "手机号码不能为空!");
                return resultMap;
            } else {
                boolean b = SystemUtils.checkMobileNumber(mobileNumber);
                if (!b) {
                    // 手机号码不符合规则
                    resultMap.put("resCode", 0);
                    resultMap.put("resMsg", "手机号码不符合规则!");
                    return resultMap;
                }
            }

            SystemConfig sysConfig = systemConfigBusiness.getSystemConfig("101");
            if ("Y".equals(sysConfig.getParamValue())) {
                // 验证码
                String checkCode = request.getParameter("checkCode");
                if (checkCode != null) {
                    checkCode = checkCode.replaceAll("\\s*", "");
                    if (!(session.getAttribute("registCode") != null && checkCode
                            .equalsIgnoreCase(session.getAttribute("registCode")
                                    .toString()))) {
                        resultMap.put("resCode", 1);
                        resultMap.put("resMsg", "验证码输入错误！");
                        return resultMap;
                    }
                } else {
                    resultMap.put("resCode", 1);
                    resultMap.put("resMsg", "请输入验证码！");
                    return resultMap;
                }
            }

            User user = userBusiness.findUserByMoblieNumber(mobileNumber);
            if (user != null) {
                resultMap.put("resCode", 0);
                resultMap.put("resMsg", "用户已经存在,不能重复注册!");
                return resultMap;
            }

            List<ValidateCode> validateCodeList = validateCodeBusiness.queryLastestCodeByMobileNumber(mobileNumber);
            if(validateCodeList.size() > 0){
                ValidateCode validateCode = validateCodeList.get(0);
                int crossTime = validateCodeBusiness.queryCrossTimeById(validateCode.getId());
                if(crossTime < 60){
                    crossTime = 1;
                }else{
                    crossTime = crossTime/60 + 2;
                }
                resultMap.put("resCode", 0);
                resultMap.put("resMsg", "请勿重复注册! 如果您未收到上次注册的短信密码,请于"+crossTime+"分钟后重新注册！");
                return resultMap;
            }

            // 随机生成用户初始密码，6位字符串，可能包含字符：23456789ABCDEFGHJKLMNPRSTUVWXYZ
            String smsCode = SystemUtils.randomCheckcode("23456789ABCDEFGHJKLMNPRSTUVWXYZ",6);

            validateCodeBusiness.sendSmsByTemple(mobileNumber , smsCode , "MB-2014011415");
            session.setAttribute("smsCode",smsCode);
            resultMap.put("resCode", 2);
            resultMap.put("resMsg", "短信发送成功!");
            resultMap.put("mobileNumber", mobileNumber);
            return resultMap;
        } catch (BusinessException e) {
            logger.error(e.getMessage());
            resultMap.put("resCode", 3);
            resultMap.put("resMsg", "系统错误，请重试!");
            return resultMap;
        }
    }

    @RequestMapping(value="/{mobileNumber}/checkMobileNumber" , method = RequestMethod.POST)
    public @ResponseBody Object checkMobileNumber(@PathVariable String mobileNumber){
        Map<String,Object> resMap = new HashMap<String, Object>();
        if(!SystemUtils.checkMobileNumber(mobileNumber)){
            resMap.put("resCode", 0);
            resMap.put("resMsg", "请输入正确的手机号码！");
            return resMap;
        }else{
            if(userBusiness.findUserByMoblieNumber(mobileNumber) != null){
                resMap.put("resCode", 0);
                resMap.put("resMsg", "用户已经存在，不能重复注册！");
                return resMap;
            }
        }
        resMap.put("resCode", 1);
        return resMap;
    }

    @RequestMapping(value="/{checkCode}/{codeName}/validateRegistCheckCode" , method = RequestMethod.POST)
    public @ResponseBody Object validateRegistCheckCode(@PathVariable String checkCode,@PathVariable String codeName,HttpSession session){
        Map<String,Object> resMap = new HashMap<String, Object>();
        if(checkCode == null){
            resMap.put("resCode", 0);
            resMap.put("resMsg", "验证码不能为空！");
            return resMap;
        }else{
            if(!checkCode.equalsIgnoreCase(String.valueOf(session.getAttribute(codeName)))){
                resMap.put("resCode", 0);
                resMap.put("resMsg", "验证码输入错误！");
                return resMap;
            }
        }
        resMap.put("resCode", 1);
        return resMap;
    }

    @RequestMapping(value = "/{mobileNumber}/resendCode" , method = RequestMethod.POST )
    public void resendCheckCode(@PathVariable String mobileNumber,HttpServletResponse response){
        List<ValidateCode> codeList = validateCodeBusiness.queryLastestCodeByMobileNumber(mobileNumber);
        String initPassword;
        if(codeList != null && codeList.size() > 0 ){
            ValidateCode validateCode = codeList.get(0);
            initPassword = validateCode.getCode();
        }else{
            initPassword  = SystemUtils.randomCheckcode("23456789ABCDEFGHJKLMNPRSTUVWXYZ",6);

        }
        validateCodeBusiness.sendSmsByTemple(mobileNumber , initPassword ,"MB-2014011415");
    }
    @RequestMapping(value="/{mobileNumber}/{code}/validateMessageCode")
    public @ResponseBody Object validateMessageCode(@PathVariable String mobileNumber , @PathVariable String code){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        Map<String,Object> conditions = new HashMap<String, Object>();
        conditions.put("mobileNumber", mobileNumber);
        conditions.put("code", code);
        ValidateCode validateCode = validateCodeBusiness.queryLastestCodeByMobileNumberAndCode(conditions);
        if(validateCode == null){
            resultMap.put("resCode", 0);
            resultMap.put("resMsg", "验证码错误！");
        }else{
            resultMap.put("resCode", 1);
        }
        return resultMap;
    }

    @RequestMapping(value = "/agreement.html")
    public String agreement() {
        return ".agreement";
    }
}
