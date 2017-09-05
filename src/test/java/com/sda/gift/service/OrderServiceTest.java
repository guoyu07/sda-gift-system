package com.sda.gift.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by Allen on 2017/9/5.
 */
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @Test
    public void getAll() throws Exception {
        orderService.getAll();
    }

}