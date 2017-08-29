package com.sda.gift.mapper;

import com.sda.gift.entity.OrderEntity;
import com.sda.gift.provider.OrderProvider;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Allen on 2017/8/25.
 */
@Repository
public interface OrderMapper {
    @Insert("INSERT INTO order (guid,user_id,pro_id,pro_name,pro_num,take_place,take_time,total_price,activity_name)" +
            " VALUES (#{guid},#{userId},#{proId},#{proName},#{proNum},#{takePlace},#{takeTime},#{totalPrice},#{activityName})")
    void insert(OrderEntity order);

    @Delete("DELETE FROM order WHERE user_id=#{userId} AND activity_name=#{activityName}")
    void delete(String userId,String activityName );

    @InsertProvider(type=OrderProvider.class,method = "insertAll")
    void insertAll(@Param("list") List<OrderEntity> list);
//@Insert({"<script>",
//        "INSERT INTO  order (guid,user_id,pro_id,pro_name,pro_num,take_place,take_time,total_price,activity_name) VALUES ",
//        "<foreach collection='list' item='order' separator = ',' >" +
//                "(#{order.guid},#{order.userId},#{order.proId},#{order.proName},#{order.proNum},#{order.takePlace},#{order.takeTime},#{order.totalPrice},#{order.activityName})" +
//                "</foreach>",
//        "</script>"})
//void insertAll(List<OrderEntity> orderEntities);

}
