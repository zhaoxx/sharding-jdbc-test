package com.epec.model;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value ="t_order_item")
public class OrderItem {

	@TableId(value = "order_item_id")
	private Long orderItemId;
	
	private String skuCode;
	
	private Long orderId;
}
