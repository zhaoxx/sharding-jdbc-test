package com.epec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epec.mapper.OrderMapper;
import com.epec.model.Order;

@Service
public class OrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	
	public List<Order> getOrderListByBuyerId(Long buyerId) {
		return orderMapper.getOrderListByBuyerId(buyerId);
	}
	
	public void createOrder(Order order) {
		orderMapper.createOrder(order);
	}

}
