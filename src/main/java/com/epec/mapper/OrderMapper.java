package com.epec.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.epec.model.Order;
import com.epec.model.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
	
	List<Order> getOrderListByBuyerId(Long buyerId);
	
	void createOrder(Order order);

	List<OrderVO> getOrderListByParams(@Param("buyerId") Long buyerId, @Param("skuCode") String skuCode);
}
