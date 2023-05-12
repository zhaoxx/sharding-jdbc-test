package com.epec.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.epec.mapper.AddressMapper;
import com.epec.model.Address;
import com.epec.model.ao.AddOrderAO;
import com.epec.model.vo.AddressVO;
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
 * @Date 2023/5/9 18:19
 */
@Service
public class AddressService {

    @Resource
    private AddressMapper addressMapper;

    public void saveAddress(AddOrderAO addOrderAO, Long orderId){
        Address address = new Address();
        address.setOrderId(orderId);
        address.setAddress(addOrderAO.getAddress());
        addressMapper.insert(address);
    }

    public Map<Long, AddressVO> getAddressMap(List<Long> orderIdList) {
        QueryWrapper<Address> queryWrapper = Wrappers.query();
        queryWrapper.in("order_id", orderIdList);
        List<Address> addressesList = addressMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(addressesList)) {
            return Maps.newHashMap();
        }

        Map<Long, AddressVO> map = Maps.newHashMap();
        addressesList.stream().forEach(address -> {
            AddressVO addressVO = this.covertAddressVO(address);
            map.put(addressVO.getOrderId(), addressVO);
        });

        return map;
    }

    private AddressVO covertAddressVO(Address address) {
        if (address == null) {
            return null;
        }
        AddressVO addressVO = new AddressVO();
        BeanUtils.copyProperties(address, addressVO);
        return addressVO;
    }
}
