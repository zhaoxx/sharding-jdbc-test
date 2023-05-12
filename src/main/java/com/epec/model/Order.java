package com.epec.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@SuppressWarnings("serial")

@Data
@TableName(value ="t_order")
public class Order implements Serializable {

	@TableId(value = "order_id")
	private Long orderId;

	private Long buyerId;

}
