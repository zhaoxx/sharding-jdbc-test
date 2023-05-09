package com.epec.service;

import com.epec.model.AddOrderAO;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Description
 * @Author zhaoxianxing
 * @Date 2023/5/9 17:12
 */
@Slf4j
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void saveOrder(){
        AddOrderAO addOrderAO = new AddOrderAO();
        addOrderAO.setBuyerId(10l);
        addOrderAO.setAddress("收货地址");
        List<String> skuCodeList = Lists.newArrayList("sku1", "sku2");
        addOrderAO.setSkuCodeList(skuCodeList);
        orderService.saveOrder(addOrderAO);
    }
}
