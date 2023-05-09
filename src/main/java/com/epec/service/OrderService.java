package com.epec.service;

import java.util.List;

import com.epec.mapper.AddressMapper;
import com.epec.mapper.OrderItemMapper;
import com.epec.model.AddOrderAO;
import com.epec.model.Address;
import com.epec.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epec.mapper.OrderMapper;
import com.epec.model.Order;

@Service
public class OrderService {
	
	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private OrderItemMapper orderItemMapper;

	@Autowired
	private AddressMapper addressMapper;
	
	public List<Order> getOrderListByBuyerId(Long buyerId) {
		return orderMapper.getOrderListByBuyerId(buyerId);
	}
	
	public void createOrder(Order order) {
		orderMapper.createOrder(order);
	}

	public void saveOrder(AddOrderAO addOrderAO) {
		// 保存主表
		Order order = new Order();
		order.setBuyerId(addOrderAO.getBuyerId());
		orderMapper.insert(order);

		Address address = new Address();
		address.setOrderId(order.getOrderId());
		address.setAddress(addOrderAO.getAddress());
		addressMapper.insert(address);

		addOrderAO.getSkuCodeList().forEach(skuCode -> {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderId(order.getOrderId());
			orderItem.setSkuCode(skuCode);
			orderItem.setBuyerId(addOrderAO.getBuyerId());
			orderItemMapper.insert(orderItem);
		});
	}
}
