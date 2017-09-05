package com.sda.gift.service;

import com.sda.gift.dao.mapper.OrderMapper;
import com.sda.gift.model.entity.OrderEntity;
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
        List<OrderEntity> oldOrders = orderMapper.query(userId,activityName);
        if(oldOrders.size()>0){
            orderMapper.delete(userId,activityName);
        }
        orderMapper.insertAll(orderEntities);
    }

    public List<OrderEntity> getOrder(String userId, String activityName){
        return orderMapper.query(userId,activityName);
    }
}
