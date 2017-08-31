package com.sda.gift.service;

import com.sda.gift.entity.OrderEntity;
import com.sda.gift.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/8/24.
 */
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public void saveOrder(List<OrderEntity> orderEntities){
        orderMapper.insertAll(orderEntities);
    }
}
