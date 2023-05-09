package com.epec.mapper;

import com.epec.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author JiaZH
 * @date 2022/07/15
 */
@Slf4j
@SpringBootTest
public class OrderMapperTest {

    @Autowired
    OrderMapper orderMapper;

    @Test
    void insert() {
        Order order = new Order();
        order.setBuyerId(3l);
        orderMapper.insert(order);
        log.info("数据ID为：{}", order.getOrderId());
    }

    @Test
    void batchInsert() {
        for (int i = 0; i < 5000; i++) {
            Order order = new Order();
            order.setBuyerId((long)i);
            orderMapper.insert(order);
            log.info("数据ID为：{}", order.getOrderId());
        }
    }

    @Test
    void selectById() {
        Order order = orderMapper.selectById(754744279401758720L);
        log.info("查询数据为：{}", order);
    }

    @Test
    void selectAll() {
        List<Order> orders = orderMapper.selectList(null);
        log.info("查询数据量为：{}", orders.size());
    }

}