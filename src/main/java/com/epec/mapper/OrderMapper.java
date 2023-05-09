package com.epec.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.epec.model.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
	
	List<Order> getOrderListByBuyerId(Long buyerId);
	
	void createOrder(Order order);

}
