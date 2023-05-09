package com.epec.model.vo;

import lombok.Data;

/**
 * @Description
 * @Author zhaoxianxing
 * @Date 2023/5/9 17:33
 */
@Data
public class OrderItemVO {
    private Long orderItemId;

    private String skuCode;

    private Long buyerId;

    private Long orderId;
}
