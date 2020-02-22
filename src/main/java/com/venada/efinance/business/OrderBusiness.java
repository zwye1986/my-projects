package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.Order;

import java.util.List;
import java.util.Map;

public interface OrderBusiness {
	
	public List<Order> queryOrder(Map<String,Object> condition,PaginationMore page)throws BusinessException;
	
	public void addOrder(Order order) throws BusinessException;
	
	public Order queryOrderById(Map<String,Object> condition)throws BusinessException;

	public void updateOrderById(Order order)throws BusinessException;

	public List<Order> queryAllOrder(Map<String,Object> condition,PaginationMore page) throws BusinessException;
	
	public void deleteOrderById(int id) throws BusinessException;
	
	public void updateOrder(Map<String,Object> params ) throws BusinessException;
	
	public Order queryorderBySerialNumber(String SerialNumber ) throws BusinessException;
	
	public Order queryOrderById(String id) throws BusinessException;
	
	public List<Order> queryorderByMobilePhone(Map<String,Object> condition)throws BusinessException;
}
