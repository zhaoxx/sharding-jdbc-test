package com.epec.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.epec.model.OrderItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description
 * @Author zhaoxianxing
 * @Date 2023/5/9 16:19
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {
}
