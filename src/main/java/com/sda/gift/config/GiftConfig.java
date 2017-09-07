package com.sda.gift.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2017/8/29.
 */
@ConfigurationProperties(prefix = "gift")
@Data
@Component
public class GiftConfig {
    private String activityName;
    private int quota;
    private String pigId;
    private List<String> pigPlace=new ArrayList<>();
}
