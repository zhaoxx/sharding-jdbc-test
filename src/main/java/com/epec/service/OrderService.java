package com.epec.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.epec.config.ShardingJdbcMaster;
import com.epec.mapper.OrderMapper;
import com.epec.model.Order;
import com.epec.model.ao.AddOrderAO;
import com.epec.model.vo.AddressVO;
import com.epec.model.vo.OrderItemVO;
import com.epec.model.vo.OrderVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {
	
	@Resource
	private OrderMapper orderMapper;

	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private AddressService addressService;

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

	@ShardingJdbcMaster
	public List<OrderVO> getOrderListByParams(Long buyerId, String skuCode) {
		return orderMapper.getOrderListByParams(buyerId, skuCode);
	}

	public IPage<OrderVO> getOrderList(Long buyerId, Long orderId){
		IPage<OrderVO> orderPage = this.getPageOrderVOList(buyerId, orderId);
		if (orderPage == null || CollectionUtils.isEmpty(orderPage.getRecords())) {
			return orderPage;
		}

		List<Long> orderIdList = orderPage.getRecords().stream().map(OrderVO::getOrderId).collect(Collectors.toList());

		Map<Long, List<OrderItemVO>> orderItemVOMap = orderItemService.getOrderItemVOMap(buyerId, orderIdList);

		Map<Long, AddressVO> addressVOMap = addressService.getAddressMap(orderIdList);

		orderPage.getRecords().stream().forEach(order -> {
			List<OrderItemVO> orderItemVOList = orderItemVOMap.get(order.getOrderId());
			order.setOrderItemVOList(orderItemVOList);

			AddressVO addressVO = addressVOMap.get(order.getOrderId());
			order.setAddressVO(addressVO);
		});

		return orderPage;
	}

	/**
	 * 强制主库查询
	 * @param buyerId
	 * @param orderId
	 * @return
	 */
	@ShardingJdbcMaster
	public IPage<OrderVO> getOrderListByMaster(Long buyerId, Long orderId){
		return this.getOrderList(buyerId, orderId);
	}

	private IPage<OrderVO> getPageOrderVOList(Long buyerId, Long orderId){
		IPage<OrderVO> pageResult = new Page<>();

		QueryWrapper<Order> queryWrapper = Wrappers.query();
		if (buyerId != null) {
			queryWrapper.eq("buyer_id", buyerId);
		}
		if (orderId != null) {
			queryWrapper.eq("order_id", orderId);
		}
		// 排序
		queryWrapper.orderByAsc("order_id");
		// 分页参数
		Page<Order> pageParam = new Page<Order>(1, 3);
		IPage<Order> pageOrders = orderMapper.selectPage(pageParam, queryWrapper);
		if (pageOrders == null) {
			return pageResult;
		}

		BeanUtils.copyProperties(pageOrders, pageResult);
		if (CollectionUtils.isEmpty(pageOrders.getRecords())){
			return pageResult;
		}
		List<OrderVO> dataList = pageOrders.getRecords().stream().map(order -> covertOrderVO(order)).collect(Collectors.toList());
		pageResult.setRecords(dataList);
		return pageResult;
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
