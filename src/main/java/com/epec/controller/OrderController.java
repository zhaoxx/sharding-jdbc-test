package com.epec.controller;

import com.alibaba.fastjson2.JSON;
import com.epec.model.ao.AddOrderAO;
import com.epec.service.OrderService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("getOrder")
	public String getOrder(@RequestParam(value = "buyerId", required = false) Long buyerId,
						   @RequestParam(value = "orderId", required = false) Long orderId) {
		return JSON.toJSONString(orderService.getOrderList(buyerId, orderId));
	}

	@GetMapping("getMasterOrder")
	public String getMasterOrder(@RequestParam(value = "buyerId", required = false) Long buyerId,
						   @RequestParam(value = "orderId", required = false) Long orderId) {
		return JSON.toJSONString(orderService.getOrderListByMaster(buyerId, orderId));
	}

	@GetMapping("getOrderListByParams")
	public String getOrder(@RequestParam(value = "buyerId", required = false) Long buyerId,
						   @RequestParam(value = "skuCode", required = false) String skuCode) {
		return JSON.toJSONString(orderService.getOrderListByParams(buyerId, skuCode));
	}

	@GetMapping("createOrder")
	public String createOrderRest(@RequestParam(value = "buyerId") Long buyerId) {
		AddOrderAO addOrderAO = new AddOrderAO();
		addOrderAO.setBuyerId(buyerId);
		addOrderAO.setAddress("收货地址:"+System.currentTimeMillis());
		List<String> skuCodeList = Lists.newArrayList("sku1", "sku2");
		addOrderAO.setSkuCodeList(skuCodeList);
		orderService.saveOrder(addOrderAO);
		return "success";
	}


}
