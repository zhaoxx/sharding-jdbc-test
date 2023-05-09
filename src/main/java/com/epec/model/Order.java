package com.epec.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Order implements Serializable {

	private Long orderId;

	private Long buyerId;

	private Long sellerId;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
}
