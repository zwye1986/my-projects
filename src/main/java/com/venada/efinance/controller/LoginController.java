package com.venada.efinance.controller;

import com.venada.efinance.business.*;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.*;
import com.venada.efinance.util.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
@SessionAttributes({"securityQuestions","mobileNumber"})
public class LoginController {
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	@Autowired
	private SystemConfigBusiness systemConfigBusiness;
	@Autowired
	private UserBusiness userBusiness;
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	@Autowired
	private SecurityQuestionBusiness securityQuestionBusiness;
	@Autowired
	private ValidateCodeBusiness validateCodeBusiness;

	@RequestMapping(value = { "/login.html", "/login" })
	public String userlogin(HttpServletRequest request, Model model) {
		if ("0".equals(request.getParameter("times"))) {
			model.addAttribute("time", 0);
			HttpSession session = request.getSession();
			String inviteCode=request.getParameter("inviteCode");
			if(inviteCode!=null){
				model.addAttribute("inviteCode",inviteCode);
				session.setAttribute("inviteCode", inviteCode);
			}
		}
		return ".login";
	}

	@RequestMapping(value = "/{checkCode}/validateLoginCheckCode")
	@ResponseBody
	public Object validateLoginCheckCode(@PathVariable String checkCode,
			HttpServletRequest request, HttpSession session) {
		SystemConfig sysConfig = systemConfigBusiness.getSystemConfig("101");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if ("Y".equals(sysConfig.getParamValue())) {
			// 验证码
			if (checkCode != null) {
				checkCode = checkCode.replaceAll("\\s*", "");
				if (!(session.getAttribute("loginCode") != null && checkCode
						.equalsIgnoreCase(session.getAttribute("loginCode")
								.toString()))) {
					resultMap.put("resultCode", 1);
				} else {
					resultMap.put("resultCode", 0);
				}
			} else {
				resultMap.put("resultCode", 1);
			}
		} else {
			resultMap.put("resultCode", 0);
		}
		return resultMap;
	}

	@RequestMapping(value = "/setNewPassword")
	public String setNewPassword(Model model, HttpServletRequest request) {
		String mobileNumber = request.getParameter("mobileNumber");
		String password = request.getParameter("password");
		Map<String, String> map = new HashMap<String, String>();
		map.put("mobileNumber", mobileNumber);
		map.put("password", password);
		List<ValidateCode> validateCodes = validateCodeBusiness
				.queryLastestCodeByMobileNumberAndPassword(map);
		if (validateCodes == null || validateCodes.size() == 0) {
			model.addAttribute("error", "error");
			return ".login";
		}
		model.addAttribute("mobileNumber", mobileNumber);
		List<SecurityQuestion> securityQuestions = securityQuestionBusiness
				.getAll();
		model.addAttribute("securityQuestions", securityQuestions);
	//	return ".setNewPassword";
		return ".szxmm";
	}

    @RequestMapping(value = "dealSetNewPassword", method = RequestMethod.POST)
    public String forceSetNewPasswordAndNickName(HttpServletRequest request,
                                                 HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            String inviteCode=(String) session.getAttribute("inviteCode");
            if (inviteCode == null || inviteCode.equals("")) {
                Cookie mycookies[] = request.getCookies();
                for (int i = 0; i < mycookies.length; i++) {
                    if ("inviteCode".equalsIgnoreCase(mycookies[i].getName())) {
                        inviteCode = mycookies[i].getValue();
                    }
                }
            }

            String mobileNumber = request.getParameter("mobileNumber");
            String code = request.getParameter("code");
            String nickName = request.getParameter("nickName");
            String newPwd = request.getParameter("newPwd");
            String newPwdRepeat = request.getParameter("newPwdRepeat");
            String questionId = request.getParameter("question");
            String answer = request.getParameter("answer");
            resetParams(mobileNumber,code, nickName, newPwd, newPwdRepeat, questionId, answer, request);
            if (mobileNumber == null
                    || !SystemUtils.checkMobileNumber(mobileNumber.replaceAll(
                    "\\s*", ""))) {
                request.setAttribute("error", "参数有误！");
                return ".fillNecessaryInfo";

            } else {
                mobileNumber = mobileNumber.replaceAll("\\s*", "");
            }

            User _user = userBusiness.findUserByMoblieNumber(mobileNumber);
            if(_user != null){
                request.setAttribute("error", "您不是首次登录，不能修改昵称！");
                resetParams(mobileNumber,code, nickName, newPwd, newPwdRepeat, questionId, answer, request);
                return ".fillNecessaryInfo";
            }

            if (nickName == null || "".equals(nickName.replaceAll("\\s*", ""))) {
                request.setAttribute("error", "请输入昵称！");
                return ".fillNecessaryInfo";
            } else {
                nickName = nickName.replaceAll("\\s*", "");
                if(userBusiness.findUserByNickName(nickName) > 0){
                    request.setAttribute("error", "该昵称已被使用，请换一个昵称！");
                    return ".fillNecessaryInfo";
                }
                if (!SystemUtils.checkName(nickName)) {
                    request.setAttribute("error", "昵称不能包含特殊字符！");
                    return ".fillNecessaryInfo";
                }
            }

            if (questionId == null
                    || "".equals(questionId.replaceAll("\\s*", ""))) {
                request.setAttribute("error", "请设置安全问题！");
                return ".fillNecessaryInfo";
            } else {
                questionId = questionId.replaceAll("\\s*", "");
            }

            if (answer == null || "".equals(answer.replaceAll("\\s*", ""))) {
                request.setAttribute("error", "请设置安全问题答案！");
                return ".fillNecessaryInfo";
            } else {
                answer = answer.replaceAll("\\s*", "");
            }

            if (newPwd == null || "".equals(newPwd.replaceAll("\\s*", ""))) {
                request.setAttribute("error", "请输入新密码！");
                return ".fillNecessaryInfo";
            } else {
                newPwd = newPwd.replaceAll("\\s*", "");
                if(!SystemUtils.checkPassword(newPwd)){
                    request.setAttribute("error",  "密码长度8-20位，必须包含数字、字母。");
                    return ".fillNecessaryInfo";
                }
            }

            if (newPwdRepeat == null
                    || "".equals(newPwdRepeat.replaceAll("\\s*", ""))) {
                request.setAttribute("newPwdRepeatError", "请输入确认密码！");
                request.getRequestDispatcher("error").forward(request,
                        response);
                return ".fillNecessaryInfo";
            } else {
                newPwdRepeat = newPwdRepeat.replaceAll("\\s*", "");
            }

            if (newPwd.equals(newPwdRepeat)) {

                User user = new User();
                user.setInviteCodeFromOther(inviteCode);
                user.setId(UUID.randomUUID().toString().toUpperCase());
                user.setInviteCodeSelf(userBusiness.obtionInviteCode());
                user.setMobileNumber(mobileNumber);
                user.setPassword(md5PasswordEncoder
                        .encodePassword(newPwd, null));
                user.setNickName(nickName);
                user.setStatus(1);
                user.setCreateTime(new Date());
                UserDetail userDetail = new UserDetail();
                userDetail.setId(UUID.randomUUID().toString().toUpperCase());
                userDetail.setUserid(user.getId());
                userDetail.setMobileNumber(mobileNumber);

                UserQuestion userQuestion = new UserQuestion();
                userQuestion.setId(UUID.randomUUID().toString());
                userQuestion.setUserid(user.getId());
                userQuestion.setQuestionId(questionId);
                userQuestion.setAnswer(answer);

                userBusiness.addUser(user, userDetail, userQuestion);
                session.setAttribute("loginMsg", "密码设置成功，请重新登录！");
                return ".login";
            } else {
                request.setAttribute("error", "2次输入不一致，请重新输入！");
                return ".fillNecessaryInfo";
            }
        } catch (BusinessException e) {
            logger.error(e.getMessage());
        } catch (ServletException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return ".fillNecessaryInfo";
    }

	@RequestMapping(value = "/{mobileNumber}/checkIsFirstLogin")
	@ResponseBody
	public Object checkIsFirstLogin(@PathVariable String mobileNumber) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		boolean b = userBusiness.isFirstLogin(mobileNumber);
		if (b) {
			resMap.put("resCode", 1);
		} else {
			resMap.put("resCode", 0);
		}
		return resMap;
	}


    private void resetParams(String mobileNumber,String code,String nickName ,String newPwd,String newPwdRepeat,String questionId,String answer,HttpServletRequest request){
        if(nickName != null){
            request.setAttribute("nickName",nickName);
        }
        if(newPwd != null){
            request.setAttribute("newPwd",newPwd);
        }
        if(newPwdRepeat != null){
            request.setAttribute("newPwdRepeat",newPwdRepeat);
        }
        if(questionId != null){
            request.setAttribute("questionId",questionId);
        }
        if(answer != null){
            request.setAttribute("answer",answer);
        }
        if(mobileNumber != null){
            request.setAttribute("mobileNumber",mobileNumber);
        }
        if(code != null){
            request.setAttribute("code",code);
        }
    }

}
