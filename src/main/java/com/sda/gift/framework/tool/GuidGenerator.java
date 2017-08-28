package com.sda.gift.framework.tool;

import java.util.UUID;

/**
 * Created by allen on 2017/5/23.
 */
public class GuidGenerator {
  public static String newGuid(){
    return UUID.randomUUID().toString().replaceAll("-","");
  }
}
