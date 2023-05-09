package com.epec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.epec.model.Order;
import com.epec.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(path="/{buyerId}", method={RequestMethod.GET})
	public List<Order> getOrderListByBuyerId(@PathVariable("buyerId") Long buyerId) {
		return orderService.getOrderListByBuyerId(buyerId);
	}
	
	@RequestMapping(path="/{buyerId}/{orderId}", method={RequestMethod.GET})
	public String createOrderRest(@PathVariable("buyerId") Long buyerId, @PathVariable("orderId") Long orderId) {
		Order order = new Order();
		order.setBuyerId(buyerId);
		order.setOrderId(orderId);
		orderService.createOrder(order);
		return "success";
	}
	
	@RequestMapping(path="/createOrder", method={RequestMethod.GET})
	public String createOrder(Long buyerId, Long orderId) {
		Order order = new Order();
		order.setBuyerId(buyerId);
		order.setOrderId(orderId);
		orderService.createOrder(order);
		return "success";
	}

}
