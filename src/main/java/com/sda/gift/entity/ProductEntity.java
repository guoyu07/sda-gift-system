package com.sda.gift.entity;

import lombok.Data;

/**
 * Created by Allen on 2017/8/24.
 */
@Data
public class ProductEntity {
    private String guid;
    private String proName;
    private String proId;
    private String proUrl;
    private String proDescription;
    private String proPrice;
    private String proNum;
    private int available;
}
