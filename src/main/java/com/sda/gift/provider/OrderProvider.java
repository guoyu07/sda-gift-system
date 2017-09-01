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
        List<OrderEntity> orderEntities = (List<OrderEntity>)map.get("getAll");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO `order`");
        sb.append("(guid,user_id,pro_id,pro_name,pro_num,take_place,take_time,total_price,activity_name) ");
        sb.append(" VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'getAll[{0}].guid},#'{'getAll[{0}].userId},#'{'getAll[{0}].proId},#'{'getAll[{0}].proName},#'{'getAll[{0}].proNum},#'{'getAll[{0}].takePlace},#'{'getAll[{0}].takeTime},#'{'getAll[{0}].totalPrice},#'{'getAll[{0}].activityName})");
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
