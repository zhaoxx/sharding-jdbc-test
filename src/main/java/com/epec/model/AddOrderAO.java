package com.epec.model;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author zhaoxianxing
 * @Date 2023/5/9 17:03
 */
@Data
public class AddOrderAO {
    private Long buyerId;
    private List<String> skuCodeList;
    private String address;
}
