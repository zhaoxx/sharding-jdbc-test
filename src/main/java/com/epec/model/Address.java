package com.epec.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description
 * @Author zhaoxianxing
 * @Date 2023/5/9 16:40
 */
@Data
@TableName(value ="t_address")
public class Address {

    @TableId(value = "address_id")
    private Long addressId;

    private Long orderId;

    private String address;
}
