package com.epec.model;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value ="t_order_item")
public class OrderItem {
	
	private Long orderItemId;
	
	private String skuCode;

	private Long buyerId;
	
	private Long orderId;
}
