package com.sda.gift.service;

import com.sda.gift.dao.mapper.OrderMapper;
import com.sda.gift.model.dto.OrderDto;
import com.sda.gift.model.entity.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Allen on 2017/8/24.
 */
@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Transactional
    public void saveOrder(List<OrderEntity> orderEntities, String userId, String activityName){
        List<OrderEntity> oldOrders = orderMapper.query(userId,activityName);
        if(oldOrders.size()>0){
            orderMapper.delete(userId,activityName);
        }
        orderMapper.insertList(orderEntities);
    }

    public List<OrderEntity> getOrder(String userId, String activityName){
        return orderMapper.query(userId,activityName);
    }

    public List<OrderDto> getAll(){
        List orderDtoList = new ArrayList();
        List<OrderEntity> orderEntities = orderMapper.queryAll();
        List<OrderEntity> distinctOrderEntity = orderEntities.stream().filter(distinctByKey(p -> p.getUserId())).collect(Collectors.toList());
        log.info(distinctOrderEntity.toString());
        return orderDtoList;
    }
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
