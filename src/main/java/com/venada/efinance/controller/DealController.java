package com.venada.efinance.controller;

import com.venada.efinance.business.RechargeRecordBusiness;
import com.venada.efinance.business.TransactionDetailsBusiness;
import com.venada.efinance.common.controller.BaseController;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.RechargeRecord;
import com.venada.efinance.pojo.TransactionDetails;
import com.venada.efinance.pojo.User;
import com.venada.efinance.security.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/user")
public class DealController extends BaseController {
	@Autowired
	private TransactionDetailsBusiness transactionDetailsBusiness;
	@Autowired
	private RechargeRecordBusiness rechargeRecordBusiness;
	
	@RequestMapping(value={"/{currentPage}/dealDetail","/{currentPage}/manager"})
	public String dealDetail(@PathVariable int currentPage , Model model , PaginationMore page){
		Map<String, Object> condition = new HashMap<String, Object>();
		User user = SpringSecurityUtil.getCurrentUser();
		condition.put("userid", user.getId());
		
		page.setCurrentPage(currentPage);
		page.setPageSize(15);
		page.setTotalRows(transactionDetailsBusiness.getTransactionDetailsCount(condition));
		int totalPages = page.getTotalRows()%page.getPageSize() == 0 ? page.getTotalRows()/page.getPageSize() : (page.getTotalRows()/page.getPageSize()) + 1;
		page.setTotalPages(totalPages);
		page.repaginate();
		
		List<TransactionDetails> list = transactionDetailsBusiness
				.queryAllTransactionDetails(condition, page);
		model.addAttribute("transactionDetails", list);
		model.addAttribute("page", page);
		model.addAttribute("item", 4);
		return ".newDealDetail";
	}
	
	@RequestMapping(value="/{currentPage}/rechargeList")
	public String rechargeList(@PathVariable int currentPage , Model model , PaginationMore page){
		Map<String, Object> condition = new HashMap<String, Object>();
		User user = SpringSecurityUtil.getCurrentUser();
		condition.put("userid", user.getId());
		
		page.setCurrentPage(currentPage);
		page.setPageSize(15);
		page.setTotalRows(rechargeRecordBusiness.getAllRechargeRecordsCount(condition));
		int totalPages = page.getTotalRows()%page.getPageSize() == 0 ? page.getTotalRows()/page.getPageSize() : (page.getTotalRows()/page.getPageSize()) + 1;
		page.setTotalPages(totalPages);
		page.repaginate();
		
		List<RechargeRecord> list = rechargeRecordBusiness.getRechargeRecords(condition, page);
		model.addAttribute("rechargeList", list);
		model.addAttribute("page", page);
		model.addAttribute("item", 4);
		return ".newRechargeList";
	}
	
	@RequestMapping(value="/{currentPage}/withDrawList")
	public String withDrawList(@PathVariable int currentPage , Model model , PaginationMore page){
		Map<String, Object> condition = new HashMap<String, Object>();
		User user = SpringSecurityUtil.getCurrentUser();
		condition.put("userid", user.getId());
		
		page.setCurrentPage(currentPage);
		page.setPageSize(15);
		page.setTotalRows(transactionDetailsBusiness.getWithdrawalRecordsCount(condition));
		int totalPages = page.getTotalRows()%page.getPageSize() == 0 ? page.getTotalRows()/page.getPageSize() : (page.getTotalRows()/page.getPageSize()) + 1;
		page.setTotalPages(totalPages);
		page.repaginate();
		
		List<TransactionDetails> list = transactionDetailsBusiness.getWithdrawalRecords(condition, page);
		model.addAttribute("withdrawList", list);
		model.addAttribute("page", page);
		model.addAttribute("item", 4);
		return ".newWithDrawal";
	}
	
	@RequestMapping(value = "/targetList", method = {RequestMethod.POST , RequestMethod.GET})
	public String tagetQuery(PaginationMore page, Model model,
			HttpServletRequest request) {
		Map<String, Object> condition = setMapCondition(request);

		User user = SpringSecurityUtil.getCurrentUser();

		if (condition.get("startTime") != null) {
			String startTime = condition.get("startTime").toString();
			condition.put("startTime", startTime.concat(" 00:00:00"));
		}

		if (condition.get("endTime") != null) {
			String endTime = condition.get("endTime").toString();
			condition.put("endTime", endTime.concat(" 23:59:59"));
		}

		condition.put("userid", user.getId());
		page.setPageSize(15);
		page.setTotalRows(transactionDetailsBusiness.getTransactionDetailsCount(condition));
		int totalPages = page.getTotalRows()%page.getPageSize() == 0 ? page.getTotalRows()/page.getPageSize() : (page.getTotalRows()/page.getPageSize()) + 1;
		page.setTotalPages(totalPages);
		List<TransactionDetails> list = transactionDetailsBusiness
				.queryAllTransactionDetails(condition, page);
		model.addAttribute("target", list);
		model.addAttribute("page", page);
		model.addAttribute("condition", condition);
		return ".newTargetQuery";
	}
}
