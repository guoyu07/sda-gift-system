package com.sda.gift.mapper;

import com.sda.gift.entity.OrderEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * Created by Allen on 2017/8/25.
 */
@Repository
public interface OrderMapper {
    @Insert("INSERT INTO order (guid,user_id,pro_id,pro_name,pro_num,place,take_time,total_price,activity_name)" +
            " VALUES (#{guid},#{userId},#{proId},#{proName},#{proNum},#{takePlace},#{takeTime},#{totalPrice},#{activityName})")
    void insert(OrderEntity order);

    @Delete("DELETE FROM order WHERE user_id=#{userId} AND activity_name=#{activityName}")
    void delete(String userId,String activityName );
}
