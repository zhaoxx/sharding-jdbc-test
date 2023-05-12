package com.epec.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.epec.mapper.OrderItemMapper;
import com.epec.model.OrderItem;
import com.epec.model.ao.AddOrderAO;
import com.epec.model.vo.OrderItemVO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author zhaoxianxing
 * @Date 2023/5/9 18:13
 */
@Service
public class OrderItemService {

    @Resource
    private OrderItemMapper orderItemMapper;

    public void saveOrderItem(AddOrderAO addOrderAO, Long orderId){
        addOrderAO.getSkuCodeList().forEach(skuCode -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setSkuCode(skuCode);
            orderItem.setBuyerId(addOrderAO.getBuyerId());
            orderItemMapper.insert(orderItem);
        });
    }

    public Map<Long, List<OrderItemVO>> getOrderItemVOMap(Long buyerId, List<Long> orderIdList) {
        QueryWrapper<OrderItem> queryWrapper = Wrappers.query();
        queryWrapper.in("order_id", orderIdList);
        if (buyerId != null) {
            queryWrapper.eq("buyer_id", buyerId);
        }
        List<OrderItem> orderItemList = orderItemMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(orderItemList)) {
            return Maps.newHashMap();
        }

        Map<Long, List<OrderItemVO>> map = Maps.newHashMap();
        orderItemList.stream().forEach(orderItem -> {
            OrderItemVO orderItemVO = this.covertOrderItemVO(orderItem);
            List<OrderItemVO> orderItemVOs = map.get(orderItemVO.getOrderId());
            if (CollectionUtils.isEmpty(orderItemVOs)) {
                orderItemVOs = Lists.newArrayList();
            }
            orderItemVOs.add(orderItemVO);
            map.put(orderItemVO.getOrderId(), orderItemVOs);
        });

        return map;
    }

    private OrderItemVO covertOrderItemVO(OrderItem orderItem) {
        if (orderItem == null) {
            return null;
        }
        OrderItemVO orderItemVO = new OrderItemVO();
        BeanUtils.copyProperties(orderItem, orderItemVO);
        return orderItemVO;
    }
}
