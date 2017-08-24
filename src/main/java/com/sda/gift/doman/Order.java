package com.sda.gift.doman;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Allen on 2017/8/24.
 */
public class Order {
    private String guid;
    private String userId;
    private String proId;
    private String proName;
    private String proNum;
    private String place;
    private String takeTime;
    private BigDecimal totalPrice;
    private Date createTime;

    public Order(String guid, String userId, String proId, String proName, String proNum, String place, String takeTime, BigDecimal totalPrice, Date createTime) {
        this.guid = guid;
        this.userId = userId;
        this.proId = proId;
        this.proName = proName;
        this.proNum = proNum;
        this.place = place;
        this.takeTime = takeTime;
        this.totalPrice = totalPrice;
        this.createTime = createTime;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProNum() {
        return proNum;
    }

    public void setProNum(String proNum) {
        this.proNum = proNum;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(String takeTime) {
        this.takeTime = takeTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
