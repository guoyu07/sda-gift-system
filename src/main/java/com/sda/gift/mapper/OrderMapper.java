package com.sda.gift.mapper;

import com.sda.gift.entity.OrderEntity;
import org.apache.ibatis.annotations.Insert;

/**
 * Created by Allen on 2017/8/25.
 */
public interface OrderMapper {
    @Insert("INSERT INTO order (guid,user_id,pro_id,pro_name,pro_num,place,take_time,total_price,activity_name)" +
            " VALUES (#{guid},#{userId},#{proId},#{proName},#{proNum},#{place},#{takeTime},#{totalPrice},#{activityName})")
    void insert(OrderEntity order);
}
