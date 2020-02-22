package com.venada.efinance.business.impl;

import com.venada.efinance.business.OrderBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.Order;
import com.venada.efinance.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author hepei
 *
 */
@Service(value="orderBusinessImp")
public class OrderBusinessImp implements OrderBusiness {

	@Autowired
	private OrderService orderServiceImpl;

	@Transactional
	@Override
	public void addOrder(Order order) throws BusinessException {
		orderServiceImpl.saveObject("addorder", order);
	}

	@Override
	public Order queryOrderById(Map<String,Object> condition) throws BusinessException {
		Order order= (Order) orderServiceImpl.getObject("queryorderById", condition);
		return order;
	}

	@Override
	public List<Order> queryOrder(Map<String,Object> condition, PaginationMore page) throws BusinessException{
		page.setTotalRows(getOrderDetailsCount(condition));
		page.repaginate();
		return orderServiceImpl.selectList("queryorder",condition,page);
	}
	
	
	
	public Integer getOrderDetailsCount(Map<String,Object> condition){
		return (Integer) orderServiceImpl.getObject("getOrderDetailsCount", condition);
		
	}
	@Transactional
	@Override
	public void updateOrderById(Order order) throws BusinessException {
		orderServiceImpl.updateObject("updateOrderById", order);
	}
	
	@Override
	public List<Order> queryAllOrder(Map<String,Object> condition,PaginationMore page) throws BusinessException{
		page.setTotalRows(queryAllOrderCount(condition));
		page.repaginate();
		return orderServiceImpl.selectList("queryAllOrder",condition,page);
	}
	
	private int queryAllOrderCount(Map<String,Object> condition) throws BusinessException{
		return (Integer) orderServiceImpl.getObject("queryAllOrderCount", condition);
	}

	@Override
	public void deleteOrderById(int id) throws BusinessException {
		
	}
	@Transactional
	@Override
	public void updateOrder(Map<String, Object> params)
			throws BusinessException {
		orderServiceImpl.updateObject("updateOrder", params);
	}

	@Override
	public Order queryorderBySerialNumber(String serialNumber)
			throws BusinessException {
		Order order= (Order) orderServiceImpl.getObject("queryorderBySerialNumber", serialNumber);
		return order;
	}

	@Override
	public Order queryOrderById(String id) throws BusinessException {
		return (Order) orderServiceImpl.getObject("queryOrderById", id);
	}

	@Override
	public List<Order> queryorderByMobilePhone(Map<String, Object> condition)
			throws BusinessException {
		return orderServiceImpl.findObjects("queryorderByMobilePhone",condition);
	}
	
}
