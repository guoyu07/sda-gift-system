package com.sda.gift.mapper;

import com.sda.gift.entity.OrderEntity;
import com.sda.gift.provider.OrderProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Allen on 2017/8/25.
 */
@Repository
public interface OrderMapper {

    @Select("SELECT * FROM `order` WHERE user_id=#{userId}")
    @Results({
            @Result(property = "userId",  column = "user_id"),
            @Result(property = "proName",  column = "pro_name"),
            @Result(property = "proId", column = "pro_id"),
            @Result(property = "proNum", column = "pro_num"),
            @Result(property = "takePlace", column = "tale_place"),
            @Result(property = "totalPrice", column = "total_price"),
            @Result(property = "activityName", column = "activity_name")
    })
    List<OrderEntity> query(String userId);

    @Insert("INSERT INTO order (guid,user_id,pro_id,pro_name,pro_num,take_place,take_time,total_price,activity_name)" +
            " VALUES (#{guid},#{userId},#{proId},#{proName},#{proNum},#{takePlace},#{takeTime},#{totalPrice},#{activityName})")
    void insert(OrderEntity order);

    @Delete("DELETE FROM order WHERE user_id=#{userId} AND activity_name=#{activityName}")
    void delete(String userId,String activityName );

    @InsertProvider(type=OrderProvider.class,method = "insertAll")
    void insertAll(@Param("list") List<OrderEntity> list);

}
