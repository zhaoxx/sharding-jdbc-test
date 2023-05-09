package com.epec.service;

import com.epec.Application;
import com.epec.model.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class OrderServiceTest {
	
	@Autowired
	private OrderService orderService;

    @Test
    public void getOrderListByUserId() throws Exception {
    	List<Order>  orderList=orderService.getOrderListByBuyerId(1l);
    	for(Order o:orderList){
    		System.out.println(o.getBuyerId());
    	}
    }
    
    
    @Test
    @Rollback(false)
    public void createOrder() throws Exception {
    	Order o1=new Order();
    	o1.setOrderId(1l);
    	o1.setBuyerId(1l);
    	orderService.createOrder(o1);
    	Order o2=new Order();
    	o2.setOrderId(2l);
    	o2.setBuyerId(2l);
    	orderService.createOrder(o2);
    }

}
