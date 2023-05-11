package com.epec.service;

import com.alibaba.fastjson2.JSON;
import com.epec.model.ao.AddOrderAO;
import com.epec.model.vo.OrderVO;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

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
//        for(int i=0;i<20;i++) {
//            Random random = new Random();
//            long radomBuyerId = random.nextInt(20);
//
//            AddOrderAO addOrderAO = new AddOrderAO();
//            addOrderAO.setBuyerId(radomBuyerId);
//            addOrderAO.setAddress("收货地址:"+i);
//            List<String> skuCodeList = Lists.newArrayList("sku1", "sku2");
//            addOrderAO.setSkuCodeList(skuCodeList);
//            orderService.saveOrder(addOrderAO);
//        }
    }

    @Test
    public void getOrder(){
        Long buyerId = (long)15;
        List<OrderVO> orderVOList = orderService.getAllOrderList(buyerId, null);
        System.out.println(JSON.toJSONString(orderVOList));
    }

}
