package com.sda.gift.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by Allen on 2017/8/24.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    private String guid;
    private String userId;
    private String proId;
    private String proName;
    private String proNum;
    private String takePlace;
    private String takeTime;
    private BigDecimal totalPrice;
    private String activityName;
}
