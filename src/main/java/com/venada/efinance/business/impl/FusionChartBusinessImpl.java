package com.venada.efinance.business.impl;

import com.venada.efinance.business.FusionChartBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.exception.ServiceException;
import com.venada.efinance.pojo.FusionChart;
import com.venada.efinance.pojo.FusionChartData;
import com.venada.efinance.service.FusionChartDataService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FusionChartBusinessImpl implements FusionChartBusiness {
	private static final Logger logger = LoggerFactory.getLogger(FusionChartBusinessImpl.class);
	
	@Autowired
	private FusionChartDataService fusionChartDataService ;

	
	/**
	 * 查询当天充值，与 提现金额柱状图Ø
	 */
	public String selectRechargeAndWithdrawal(Map<String,Object> map) throws BusinessException {
		JSONObject jsonObj = null ; 
		try {
			List<FusionChartData> list = fusionChartDataService.findObjects("selectRechargeAndWithdrawal", map);
			FusionChart fc = new FusionChart("当日充值与提现对比柱状图","名称","金额");
			fc.setData(list);
			jsonObj = JSONObject.fromObject(fc);
			jsonObj.put("formatNumberScale", "0");
			jsonObj.put("canvaspadding", "20");
			
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"selectRechargeAndWithdrawal()方法出错！\t", e.getMessage() });
		}
		return jsonObj.toString();
	}

	/**
	 * 查询当月每天充值的总金额
	 */
	public String selectRechargeLine(Map<String,Object> map) throws BusinessException {
		JSONObject jsonObj = null ; 
		try {
			List<FusionChartData> list = fusionChartDataService.findObjects("selectRechargeLine", map);
			FusionChart fc = new FusionChart("当月每天充值的总金额","日期","金额");
			fc.setData(list);
			jsonObj = JSONObject.fromObject(fc);
			jsonObj.put("formatNumberScale", "0");
			jsonObj.put("canvaspadding", "20");
			
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"selectRechargeLine()方法出错！\t", e.getMessage() });
		}
		return jsonObj.toString();
	}

	/**
	 * 查询当月每天提现的总金额
	 */
	public String selectWithdrawalLine(Map<String,Object> map) throws BusinessException {
		JSONObject jsonObj = null ; 
		try {
			List<FusionChartData> list = fusionChartDataService.findObjects("selectWithdrawalLine", map);
			FusionChart fc = new FusionChart("当月每天提现的总金额","日期","金额");
			fc.setData(list);
			jsonObj = JSONObject.fromObject(fc);
			jsonObj.put("formatNumberScale", "0");
			jsonObj.put("canvaspadding", "20");
			
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"selectWithdrawalLine()方法出错！\t", e.getMessage() });
		}
		return jsonObj.toString();
	}
	
	

	
	/**
	 * 查询当月每天成功提现的总金额
	 */
	public String selectWithdrawalSuccessLine(Map<String,Object> map) throws BusinessException {
		JSONObject jsonObj = null ; 
		try {
			List<FusionChartData> list = fusionChartDataService.findObjects("selectWithdrawalSuccessLine", map);
			FusionChart fc = new FusionChart("当月每天成功提现的总金额","日期","金额");
			fc.setData(list);
			jsonObj = JSONObject.fromObject(fc);
			jsonObj.put("formatNumberScale", "0");
			jsonObj.put("canvaspadding", "20");
			
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"selectWithdrawalLine()方法出错！\t", e.getMessage() });
		}
		return jsonObj.toString();
	}
	
	/**
	 * 查询当天，每个时刻新增的用户数
	 */
	public String selectUserByHour() throws BusinessException {
		JSONObject jsonObj = null ; 
		try {
			List<FusionChartData> list = fusionChartDataService.findObjects("selectUserByHour", null);
			FusionChart fc = new FusionChart("当天每个时刻新增用户数","时刻","个数");
			fc.setData(list);
			jsonObj = JSONObject.fromObject(fc);
			jsonObj.put("formatNumberScale", "0");
			jsonObj.put("canvaspadding", "20");
			
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"selectUserByHour()方法出错！\t", e.getMessage() });
		}
		return jsonObj.toString();
	}

	/**
	 * 查询当月，每天新增的用户数
	 */
	public String selectUserByDay() throws BusinessException {
		JSONObject jsonObj = null ; 
		try {
			List<FusionChartData> list = fusionChartDataService.findObjects("selectUserByDay", null);
			FusionChart fc = new FusionChart("当月每天新增用户数","日期","个数");
			fc.setData(list);
			jsonObj = JSONObject.fromObject(fc);
			jsonObj.put("formatNumberScale", "0");
			jsonObj.put("canvaspadding", "20");
			
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"selectUserByDay()方法出错！\t", e.getMessage() });
		}
		return jsonObj.toString();
	}

	/**
	 * 查询预计时间段用户的提现金额
	 */
	public String estimatedCostByDate(Map<String,Object> map) throws BusinessException {
		JSONObject jsonObj = null ; 
		try {
			List<FusionChartData> list = fusionChartDataService.findObjects("estimatedCostByDate", map);
			FusionChart fc = new FusionChart("预计时间段用户的提现金额","日期","金额");
			fc.setData(list);
			jsonObj = JSONObject.fromObject(fc);
			jsonObj.put("formatNumberScale", "0");
			jsonObj.put("canvaspadding", "20");
			
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"estimatedCostByDate()方法出错！\t", e.getMessage() });
		}
		return jsonObj.toString();
	}

	/**
	 * 各押金段的领取人数
	 */
	public String depostGeter() throws BusinessException {
		JSONObject jsonObj = null ; 
		try {
			List<FusionChartData> list = fusionChartDataService.findObjects("depostGeter", null);
			FusionChart fc = new FusionChart("各押金段的领取人数","押金","个数");
			fc.setData(list);
			jsonObj = JSONObject.fromObject(fc);
			jsonObj.put("formatNumberScale", "0");
			jsonObj.put("canvaspadding", "20");
			
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"depostGeter()方法出错！\t", e.getMessage() });
		}
		return jsonObj.toString();
	}

	/**
	 * 性别比例
	 */
	public String sexRatio() throws BusinessException {
		JSONObject jsonObj = null ; 
		try {
			List<FusionChartData> list = fusionChartDataService.findObjects("sexRatio", null);
			FusionChart fc = new FusionChart("用户性别比例","性别","个数");
			fc.setData(list);
			jsonObj = JSONObject.fromObject(fc);
			jsonObj.put("formatNumberScale", "0");
			jsonObj.put("canvaspadding", "20");
			
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"sexRatio()方法出错！\t", e.getMessage() });
		}
		return jsonObj.toString();
	}

	/**
	 * 收入段统计
	 */
	public String incomeCount() throws BusinessException {
		JSONObject jsonObj = null ; 
		try {
			List<FusionChartData> list = fusionChartDataService.findObjects("incomeCount", null);
			FusionChart fc = new FusionChart("用户收入段比例","收入区间","个数");
			fc.setData(list);
			jsonObj = JSONObject.fromObject(fc);
			jsonObj.put("formatNumberScale", "0");
			jsonObj.put("canvaspadding", "20");
			
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"incomeCount()方法出错！\t", e.getMessage() });
		}
		return jsonObj.toString();
	}

	/**
	 * 年龄段人数占用比例
	 */
	public String ageRatio() throws BusinessException {
		JSONObject jsonObj = null ; 
		try {
			List<FusionChartData> list = fusionChartDataService.findObjects("ageRatio", null);
			FusionChart fc = new FusionChart("各年龄段人数占用比例","年龄段","个数");
			fc.setData(list);
			jsonObj = JSONObject.fromObject(fc);
			jsonObj.put("formatNumberScale", "0");
			jsonObj.put("canvaspadding", "20");
			
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"ageRatio()方法出错！\t", e.getMessage() });
		}
		return jsonObj.toString();
	}

	/**
	 * 游戏点击量前十
	 */
	public String gameCount() throws BusinessException {
		JSONObject jsonObj = null ; 
		try {
			List<FusionChartData> list = fusionChartDataService.findObjects("gameCount", null);
			FusionChart fc = new FusionChart("游戏点击量前十名","游戏名称","点击次数");
			fc.setData(list);
			jsonObj = JSONObject.fromObject(fc);
			jsonObj.put("formatNumberScale", "0");
			jsonObj.put("canvaspadding", "20");
			
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"gameCount()方法出错！\t", e.getMessage() });
		}
		return jsonObj.toString();
	}

	@Override
	public String getAllInfo(Map<String,Object> condition) throws BusinessException {
		JSONObject jsonObj = null ; 
		try {
			List<FusionChartData> list = fusionChartDataService.findObjects("getAllInfo", condition);
			FusionChart fc = new FusionChart("资金总览(充值金额+邀请奖励+签到奖励+已完成任务返利-未完成任务的押金-提现总金额-会员费用=用户余额)","名称","金额");
			fc.setData(list);
			jsonObj = JSONObject.fromObject(fc);
			jsonObj.put("formatNumberScale", "0");
			jsonObj.put("canvaspadding", "20");
			
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"getAllInfo()方法出错！\t", e.getMessage() });
		}
		return jsonObj.toString();
	}
	
	@Override
    public String getAllCapitalInfo(Map<String,Object> condition) throws BusinessException {
        JSONObject jsonObj = null ; 
        try {
            List<FusionChartData> list = fusionChartDataService.findObjects("getAllCapitalInfo", condition);
            FusionChart fc = new FusionChart("资金总览","名称","金额");
            fc.setData(list);
            jsonObj = JSONObject.fromObject(fc);
            jsonObj.put("formatNumberScale", "0");
            jsonObj.put("canvaspadding", "20");
            
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            throw new BusinessException("0002", new String[] {"getAllCapitalInfo()方法出错！\t", e.getMessage() });
        }
        return jsonObj.toString();
    }
	

}
