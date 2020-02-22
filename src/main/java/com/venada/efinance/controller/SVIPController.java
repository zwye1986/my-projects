package com.venada.efinance.controller;

import com.venada.efinance.business.*;
import com.venada.efinance.common.controller.BaseController;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.Province;
import com.venada.efinance.pojo.User;
import com.venada.efinance.pojo.UserDetail;
import com.venada.efinance.security.SpringSecurityUtil;
import com.venada.efinance.util.SystemUtils;
import jxl.Workbook;
import jxl.write.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value="/manager/svip")
public class SVIPController extends BaseController {
	@Autowired
	private ProvinceBusiness provinceBusiness;
	@Autowired
	private AreaBusiness areaBusiness;
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	@Autowired
	private UserBusiness userBusiness;
	@Autowired
	private WithdrawalRecordBussiness withdrawalRecordBussiness;
	@Autowired
	private DealDetailBusiness dealDetailBusiness;
	@Autowired
	private LoginLogBusiness loginLogBusiness;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value="/manager")
	public String svipManager(Model model){
		// 加载全国省份
		List<Province> provinces = provinceBusiness.findAllProvinceList();
		model.addAttribute("liveProvince", provinces);

		return "/manager/svip/svipManager";
	}
	
	@RequestMapping(value="/createSvip")
	public @ResponseBody Object createSvip(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("resCode", 1);
		resultMap.put("resMsg", "创建成功！");
		User currentUser = SpringSecurityUtil.getCurrentUser();
		User vipUser = new User();
		//设置userid
		vipUser.setId(String.valueOf(UUID.randomUUID()));
		vipUser.setType(2);
		vipUser.setStatus(1);
		vipUser.setInviteCodeSelf(userBusiness.obtionInviteCode());

		UserDetail vipDetail = new UserDetail();
		//userDetail id
		vipDetail.setId(String.valueOf(UUID.randomUUID()));
		
		//父id
		vipUser.setFatherid(currentUser.getId());
		//昵称
		String nickName = request.getParameter("nickName");
		//前缀
		String prefix = request.getParameter("prefix");
		if(userBusiness.findUserByNickName(nickName) > 0){
			resultMap.put("resCode", 0);
			resultMap.put("resMsg", "昵称已被占用，请重新换一个！");
		}else{
			vipUser.setNickName(nickName);
		}
		
		try {
			//居住地址
			int liveProvince = Integer.valueOf(request.getParameter("liveProvince"));
			vipDetail.setLiveProvince(liveProvince);
			int liveCity = Integer.valueOf(request.getParameter("liveCity"));
			vipDetail.setLiveCity(liveCity);
			int liveArea = Integer.valueOf(request.getParameter("liveArea"));
			vipDetail.setLiveArea(liveArea);
			
			//生成密码
			String vipInitPassword = SystemUtils.randomCheckcode("1234567890", 6);
			vipUser.setVipInitPassword(vipInitPassword);
			vipUser.setPassword(md5PasswordEncoder.encodePassword(vipInitPassword, null));
			int subAccount = Integer.valueOf(request.getParameter("subAccount"));
			double withdrawalRate = Double.valueOf(request.getParameter("withdrawalRate"));
			vipUser.setSvipRate(withdrawalRate);
			vipUser.setMyRate(Double.valueOf(request.getParameter("myRate")));
			userBusiness.createSvip(vipUser,vipDetail,subAccount,request.getSession().getServletContext().getRealPath("/"),prefix);
		} catch (NumberFormatException e) {
			logger.error("创建svip帐号出错："+e.getMessage());
			resultMap.put("resCode", 0);
			resultMap.put("resMsg", "创建svip帐号出错："+e.getMessage());
		} catch (BusinessException e) {
			logger.error("创建svip帐号出错："+e.getMessage());
			resultMap.put("resCode", 0);
			resultMap.put("resMsg", "创建svip帐号出错："+e.getMessage());
		}catch (IOException e) {
			logger.error("创建svip帐号出错："+e.getMessage());
			resultMap.put("resCode", 0);
			resultMap.put("resMsg", "创建svip帐号出错："+e.getMessage());
		}
		return resultMap;
	}
	
	@RequestMapping(value="/getSubs")
	public String getSubs(PaginationMore page,Model model){
		User user = SpringSecurityUtil.getCurrentUser();
		page.setPageSize(20);
		List<User> vipList = userBusiness.getVipList(page, user.getId());
		for(User u : vipList){
			u.setTotalWithdrawal(withdrawalRecordBussiness.countTotalWithdrawalForSvip(u.getId()));
			u.setTotalWithdrawalFee(dealDetailBusiness.countTotalWithdrawalFeeForSvip(u.getId()));
		}
		model.addAttribute("page", page);
		model.addAttribute("list", vipList);
		return "/manager/svip/showSubs";
	}
	
	@RequestMapping(value="/{fatherid}/getSubDetails")
	public String getSubDetails(PaginationMore page,Model model,@PathVariable String fatherid){
		page.setPageSize(10);
		List<User> subsList = userBusiness.getSubsList(page, fatherid);
		for(User u : subsList){
			u.setTotalWithdrawal(withdrawalRecordBussiness.countTotalWithdrawalForSvip(u.getId()));
			u.setTotalWithdrawalFee(dealDetailBusiness.countTotalWithdrawalFeeForSvip(u.getId()));
		}
		model.addAttribute("fatherid", fatherid);
		model.addAttribute("page", page);
		model.addAttribute("list", subsList);
		return "/manager/svip/showSubDetails";
	}
	
	@RequestMapping(value="/{userid}/export")
	public void exportGeneratedUser(HttpServletRequest request,HttpServletResponse response,@PathVariable String userid){
		WritableWorkbook wbook = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			response.reset();// 清空输出流

			// 不能用用中文设置 filename，会出错
			response.setHeader("Content-disposition", "attachment; filename=user.xls");// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型

			
			wbook = Workbook.createWorkbook(os);
			WritableSheet wsheet = wbook.createSheet("用户名单", 0); // 工作表名称
			
//			for(int i = 0 ; i < 10 ; i ++){
//				wsheet.setColumnView(i, 20);
//			}
			
			// 设置Excel字体
			WritableFont wfont = new WritableFont(WritableFont.ARIAL, 14, 
												  WritableFont.BOLD, 
												  false, 
												  jxl.format.UnderlineStyle.NO_UNDERLINE, 
												  jxl.format.Colour.BLACK);
			WritableCellFormat nameFormat = new WritableCellFormat(wfont);
			nameFormat.setAlignment(jxl.format.Alignment.CENTRE);
			nameFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			
			Label userNameLable = new Label(0, 0, "用户名", nameFormat);
			wsheet.addCell(userNameLable);
			
			Label passwordLable = new Label(1, 0, "初始密码", nameFormat);
			wsheet.addCell(passwordLable);
			
			List<User> userList = userBusiness.getSvipUserList(userid);
			int beginRow = 1;
			for(User u : userList){
				Label r1 = new Label(0,beginRow,  u.getMobileNumber(), nameFormat);
				wsheet.addCell(r1);
				Label r2 = new Label(1,beginRow,  u.getVipInitPassword(), nameFormat);
				wsheet.addCell(r2);
				beginRow++;
			}
			
			wbook.write(); // 写入文件
			wbook.close();
		
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally{
			
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
	@RequestMapping(value = "/getLoginLog")
	public String getLoginLog(Model model,PaginationMore page,HttpServletRequest request){
		page.setPageSize(20);
		Map<String, Object> condition = setMapCondition(request);
		condition.put("type", 2);
		model.addAttribute("page", page);
		model.addAttribute("list", loginLogBusiness.querySvipLoginLog(condition, page));
		return "/manager/svip/svipLoginLog";
	}
	
}
