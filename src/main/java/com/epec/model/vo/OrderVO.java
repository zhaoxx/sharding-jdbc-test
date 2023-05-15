package com.epec.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author zhaoxianxing
 * @Date 2023/5/9 17:32
 */
@Data
public class OrderVO {
    private Long orderId;
    private Long buyerId;
    private List<OrderItemVO> orderItemVOList;
    private AddressVO addressVO;
    private Integer orderItemCount;
}
