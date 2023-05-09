package com.epec.mapper;

import java.util.List;

import com.epec.model.Order;

public interface OrderMapper {
	
	List<Order> getOrderListByBuyerId(Long buyerId);
	
	void createOrder(Order order);

}
