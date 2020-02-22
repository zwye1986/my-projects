package com.venada.efinance.mobile.controller;

import com.venada.efinance.business.RechargeRecordBusiness;
import com.venada.efinance.business.TransactionDetailsBusiness;
import com.venada.efinance.business.UserBusiness;
import com.venada.efinance.business.WithdrawalRecordBussiness;
import com.venada.efinance.common.controller.BaseController;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.RechargeRecord;
import com.venada.efinance.pojo.TransactionDetails;
import com.venada.efinance.pojo.User;
import com.venada.efinance.pojo.WithdrawalRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TransactionMobileController extends BaseController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(TransactionMobileController.class);
	
	@Autowired
	private WithdrawalRecordBussiness withdrawalRecordBussiness;
	
	@Autowired
	private RechargeRecordBusiness rechargeRecordBusiness;
	
	@Autowired
	private TransactionDetailsBusiness transactionDetailsBusiness;

    @Autowired
    private UserBusiness userBusiness;
	
	@RequestMapping(value="/{userid}/{password}/{currentPage}/{pageSize}/queryWithdrawalRecordForAndriod")
	public @ResponseBody Object queryWithdrawalRecordForAndriod(@PathVariable String userid,@PathVariable String password , @PathVariable Integer currentPage , @PathVariable Integer pageSize){
		int begin = (currentPage - 1) * pageSize;
		Map<String,Object> conditions = new HashMap<String,Object>();
		conditions.put("userid", userid);
		conditions.put("password", password);
		conditions.put("begin", begin);
		conditions.put("end", pageSize);
		List<WithdrawalRecord> list = null;
		try {
			list = withdrawalRecordBussiness.queryWithdrawalRecordForAndriod(conditions);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("查询提现记录出错 ：" + e.getMessage());
		}
		return list;
	}
	
	@RequestMapping(value="/{userid}/{password}/{currentPage}/{pageSize}/queryRechargeRecordForMobile")
	public @ResponseBody Object queryRechargeRecordForMobile(@PathVariable String userid,@PathVariable String password,@PathVariable Integer currentPage , @PathVariable Integer pageSize){
		int begin = (currentPage - 1) * pageSize;
		Map<String,Object> conditions = new HashMap<String,Object>();
		conditions.put("userid", userid);
		conditions.put("password", password);
		conditions.put("begin", begin);
		conditions.put("end", pageSize);
		List<RechargeRecord> list = null;
		try {
			list = rechargeRecordBusiness.queryRechargeRecordsForMobile(conditions);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("查询充值记录出错 ：" + e.getMessage());
		}
		return list;
	}
	
	@RequestMapping(value="/{userid}/{password}/{currentPage}/{pageSize}/queryTransactionDetailsForMobile")
	public @ResponseBody Object queryTransactionDetailsForMobile(@PathVariable String userid,@PathVariable String password,@PathVariable Integer currentPage , @PathVariable Integer pageSize){
		int begin = (currentPage - 1) * pageSize;
		Map<String,Object> conditions = new HashMap<String,Object>();
		conditions.put("userid", userid);
		conditions.put("password", password);
		conditions.put("begin", begin);
		conditions.put("end", pageSize);
		List<TransactionDetails> list = null;
		try {
			list = transactionDetailsBusiness.queryTransactionDetailsForMobile(conditions);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("查询交易记录出错 ：" + e.getMessage());
		}
		return list;
	}

    /**
     * 用于Android客户端提现记录查询
     * @param userid
     * @param password
     * @return
     */

    @RequestMapping(value="/{userid}/{password}/queryWithdrawalRecordForAndriod")
    public @ResponseBody Object queryWithdrawalRecordForAndriod(@PathVariable String userid,@PathVariable String password){
        Map<String,Object> conditions = new HashMap<String,Object>();
        conditions.put("userid", userid);
        conditions.put("password", password);
        return withdrawalRecordBussiness.queryWithdrawalRecordForAndriod(conditions);
    }

    /**
     * 用于Android客户端的目标查询
     * @param userid
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(value="/{userid}/{password}/getTargetQueryResult")
    public @ResponseBody Object getTargetQueryResult(@PathVariable String userid,@PathVariable String password,HttpServletRequest request){
        User user = userBusiness.findUserById(userid);
        if(user == null || !password.equals(user.getPassword())){
            Map<String,Object> resultMap = new HashMap<String,Object>();
            resultMap.put("resCode", 0);
            resultMap.put("resMsg", "用户不存在或者密码错误！");
            return resultMap;
        }

        Map<String, Object> condition = setMapCondition(request);

        if (condition.get("keyword") != null) {
            condition.put("keyword", "%" + condition.get("keyword") + "%");
        }

        if (condition.get("startTime") != null) {
            String startTime = condition.get("startTime").toString();
            condition.put("startTime", startTime.concat(" 00:00:00"));
        }

        if (condition.get("endTime") != null) {
            String endTime = condition.get("endTime").toString();
            condition.put("endTime", endTime.concat(" 23:59:59"));
        }

        condition.put("userid", userid);
        condition.put("password", password);
        List<TransactionDetails> list = transactionDetailsBusiness.queryAllTransactionDetails(condition);
        return list;
    }
}
