package com.sda.gift.service;

import com.sda.gift.entity.OrderEntity;
import com.sda.gift.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Allen on 2017/8/24.
 */
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Transactional
    public void saveOrder(List<OrderEntity> orderEntities, String userId, String activityName){
        orderMapper.delete(userId,activityName);
        orderMapper.insertAll(orderEntities);
    }

    public void addOrder(List<OrderEntity> orderEntities){
        orderMapper.insertAll(orderEntities);
    }

    public List<OrderEntity> getOrder(String userId){
        return orderMapper.query(userId);
    }
}
