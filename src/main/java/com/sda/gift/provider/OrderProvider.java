package com.sda.gift.provider;

import com.sda.gift.entity.OrderEntity;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/8/29.
 */
public class OrderProvider {

    public String insertAll(Map map){
        List<OrderEntity> orderEntities = (List<OrderEntity>)map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO `order`");
        sb.append("(guid,user_id,pro_id,pro_name,pro_num,take_place,take_time,total_price,activity_name) ");
        sb.append(" VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].guid},#'{'list[{0}].userId},#'{'list[{0}].proId},#'{'list[{0}].proName},#'{'list[{0}].proNum},#'{'list[{0}].takePlace},#'{'list[{0}].takeTime},#'{'list[{0}].totalPrice},#'{'list[{0}].activityName})");
        for(int i = 0 ;i<orderEntities.size();i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < orderEntities.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(";");
        System.out.println(sb.toString());
        return sb.toString();
    }
}
