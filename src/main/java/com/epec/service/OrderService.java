package com.epec.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.epec.mapper.OrderMapper;
import com.epec.model.Order;
import com.epec.model.ao.AddOrderAO;
import com.epec.model.vo.AddressVO;
import com.epec.model.vo.OrderItemVO;
import com.epec.model.vo.OrderVO;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {
	
	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private AddressService addressService;
	
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

		// 保存地址信息
		addressService.saveAddress(addOrderAO, order.getOrderId());

		// 保存订单明细信息
		orderItemService.saveOrderItem(addOrderAO, order.getOrderId());
	}

	public List<OrderVO> getAllOrderList(Long buyerId){
		List<OrderVO> orderVOList = this.getOrderVOList(buyerId);
		if (CollectionUtils.isEmpty(orderVOList)) {
			return Lists.newArrayList();
		}

		List<Long> orderIdList = orderVOList.stream().map(OrderVO::getOrderId).collect(Collectors.toList());

		Map<Long, List<OrderItemVO>> orderItemVOMap = orderItemService.getOrderItemVOMap(orderIdList);

		Map<Long, AddressVO> addressVOMap = addressService.getAddressMap(orderIdList);

		orderVOList.stream().forEach(order -> {
			List<OrderItemVO> orderItemVOList = orderItemVOMap.get(order.getOrderId());
			order.setOrderItemVOList(orderItemVOList);

			AddressVO addressVO = addressVOMap.get(order.getOrderId());
			order.setAddressVO(addressVO);
		});

		return orderVOList;
	}

	private List<OrderVO> getOrderVOList(Long buyerId){
		QueryWrapper<Order> queryWrapper = Wrappers.query();
		queryWrapper.eq("buyer_id", buyerId);
		List<Order> orderList = orderMapper.selectList(queryWrapper);
		if (CollectionUtils.isEmpty(orderList)) {
			return Lists.newArrayList();
		}
		return orderList.stream().map(order -> covertOrderVO(order)).collect(Collectors.toList());
	}

	private OrderVO covertOrderVO(Order order) {
		if (order == null) {
			return null;
		}
		OrderVO orderVO = new OrderVO();
		BeanUtils.copyProperties(order, orderVO);
		return orderVO;
	}
}
