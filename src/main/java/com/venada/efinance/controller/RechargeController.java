package com.venada.efinance.controller;

import com.venada.efinance.business.RechargeRecordBusiness;
import com.venada.efinance.business.UserBusiness;
import com.venada.efinance.common.controller.BaseController;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.RechargeRecord;
import com.venada.efinance.pojo.User;
import com.venada.efinance.util.DateUtils;
import jxl.Workbook;
import jxl.write.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * 充值相关操作控制器
 * 
 * @author hepei
 * 
 */
@Controller
@RequestMapping(value = "/manager")
public class RechargeController extends BaseController {

	private static final Logger logger = LoggerFactory
			.getLogger(RechargeController.class);

	@Autowired
	private UserBusiness userBusinessimpl;
	@Autowired
	private RechargeRecordBusiness rechargeRecordBusinessImpl;

	@RequestMapping(value = { "/rechargeRecordList.html", "/rechargeRecordList" })
	public String convertlist(PaginationMore page, Model model,
			HttpServletRequest request) {
		logger.info("后台查询充值记录开始");
		try {
			List<RechargeRecord> rechargeRecordList;
			Map<String, Object> condition = setMapCondition(request);
			logger.info("查询条件:{}",condition.toString());
			String mobilePhone = (String) condition.get("mobilePhone");
			if (mobilePhone != null) {
				User u = userBusinessimpl.findUserByMoblieNumber(mobilePhone);
				if (u != null) {
					condition.put("userId", u.getId());
				} else {
					condition.put("userId", " ");
				}
			}
			
			String actionStartTime = (String) condition.get("actionStartTime");
			String actionEndTime = (String) condition.get("actionEndTime");
			if ((actionStartTime != null) && (!actionStartTime.equals("")) &&(!actionStartTime.contains("00:00:00"))) {
				condition.put("actionStartTime", actionStartTime.concat(" 00:00:00"));
			}
			if((actionEndTime != null)	&& (!actionEndTime.equals(""))&&(!actionEndTime.contains("23:59:59"))){
				condition.put("actionEndTime", actionEndTime.concat(" 23:59:59"));
			}
			page.setPageSize(10);
			rechargeRecordList = rechargeRecordBusinessImpl.getAllRechargeRecords(condition, page);
			
			model.addAttribute("page", page);
			model.addAttribute("rechargeRecordList", rechargeRecordList);
			model.addAttribute("condition", condition);
		} catch (BusinessException e) {
			logger.error(e.getMessage());
		}
		logger.info("后台查询充值记录结束");
		return "/manager/rechargeRecordList";
	}

    @RequestMapping(value="/exportRechargeRecordeList" , method = RequestMethod.POST)
    public void exportRechargeRecordeList(HttpServletRequest request,HttpServletResponse response){
        logger.info("后台导出充值记录开始");
        OutputStream os = null;
        List<RechargeRecord> rechargeRecordList;
        try {

            Map<String, Object> condition = setMapCondition(request);
            logger.info("查询条件:{}",condition.toString());
            String mobilePhone = (String) condition.get("mobilePhone");
            if (mobilePhone != null) {
                User u = userBusinessimpl.findUserByMoblieNumber(mobilePhone);
                if (u != null) {
                    condition.put("userId", u.getId());
                } else {
                    condition.put("userId", " ");
                }
            }

            String actionStartTime = (String) condition.get("actionStartTime");
            String actionEndTime = (String) condition.get("actionEndTime");
            if ((actionStartTime != null) && (!actionStartTime.equals("")) &&(!actionStartTime.contains("00:00:00"))) {
                condition.put("actionStartTime", actionStartTime.concat(" 00:00:00"));
            }
            if((actionEndTime != null)	&& (!actionEndTime.equals(""))&&(!actionEndTime.contains("23:59:59"))){
                condition.put("actionEndTime", actionEndTime.concat(" 23:59:59"));
            }
            rechargeRecordList = rechargeRecordBusinessImpl.getRechargeRecordesByConditionToExport(condition);
             os = response.getOutputStream();
            WritableWorkbook wbook = Workbook.createWorkbook(os);
            response.reset();// 清空输出流
            // 不能用用中文设置 filename，会出错
            response.setHeader("Content-disposition",
                    "attachment; filename=exp.xls");// 设定输出文件头
            response.setContentType("application/msexcel");// 定义输出类型

            WritableSheet wsheet = wbook.createSheet("充值记录", 0); // 工作表名称

            wsheet.setColumnView(1, 70);
            wsheet.setColumnView(2, 15);
            wsheet.setColumnView(3, 20);
            wsheet.setColumnView(4, 30);
            wsheet.setColumnView(5, 50);
            wsheet.setColumnView(6, 50);

            // 设置Excel字体
            WritableFont wfont = new WritableFont(WritableFont.ARIAL,
                    20,
                    WritableFont.BOLD, false,
                    jxl.format.UnderlineStyle.NO_UNDERLINE,
                    jxl.format.Colour.BLACK);
            WritableCellFormat nameFormat = new WritableCellFormat(wfont);
            nameFormat.setAlignment(jxl.format.Alignment.CENTRE);
            nameFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            WritableCellFormat contentFormat = new WritableCellFormat(new WritableFont(WritableFont.ARIAL,
                    14,
                    WritableFont.NO_BOLD, false,
                    jxl.format.UnderlineStyle.NO_UNDERLINE,
                    jxl.format.Colour.BLACK));

            Label index = new Label(0, 0, "序号", nameFormat);
            wsheet.addCell(index);

            Label serialNumber = new Label(1, 0, "交易流水号", nameFormat);
            wsheet.addCell(serialNumber);

            Label name = new Label(2, 0, "客户姓名", nameFormat);
            wsheet.addCell(name);

            Label mobileNumber = new Label(3, 0, "手机号码", nameFormat);
            wsheet.addCell(mobileNumber);

            Label amount = new Label(4, 0, "充值金额", nameFormat);
            wsheet.addCell(amount);

            Label withdrawalTime = new Label(5, 0, "充值时间", nameFormat);
            wsheet.addCell(withdrawalTime);

            Label status = new Label(6, 0, "状态", nameFormat);
            wsheet.addCell(status);

            int beginRow = 1;
            String s ;
            for(RechargeRecord rechargeRecord : rechargeRecordList){
                wsheet.addCell(new Label(0,beginRow,String.valueOf(beginRow),contentFormat));
                wsheet.addCell(new Label(1,beginRow,rechargeRecord.getSerialNumber(),contentFormat));
                wsheet.addCell(new Label(2,beginRow,rechargeRecord.getUser() == null ? "" : rechargeRecord.getUser().getName(),contentFormat));
                wsheet.addCell(new Label(3,beginRow,rechargeRecord.getUser() == null ? "" : rechargeRecord.getUser().getMobileNumber(),contentFormat));
                wsheet.addCell(new Label(4,beginRow,String.valueOf(rechargeRecord.getAmount()),contentFormat));
                wsheet.addCell(new Label(5,beginRow, DateUtils.toString(rechargeRecord.getDateTime(), "yyyy-MM-dd"),contentFormat));
                if("0".equals(rechargeRecord.getStatus())){
                    s = "充值成功";
                }else if("1".equals(rechargeRecord.getStatus())){
                    s = "充值失败";
                }else if("2".equals(rechargeRecord.getStatus())){
                    s = "正在处理";
                }else{
                    s = "未知状态";
                }
                wsheet.addCell(new Label(6,beginRow,s,contentFormat));
                beginRow ++;
            }

            wbook.write(); // 写入文件
            wbook.close();
        } catch (BusinessException e) {
            logger.error(e.getMessage());
        }catch(IOException e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }catch(WriteException e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }finally {
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        logger.info("后台导出充值记录结束");
    }
}
