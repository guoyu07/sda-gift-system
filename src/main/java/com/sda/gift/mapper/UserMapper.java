package com.sda.gift.mapper;

import com.sda.gift.entity.UserEntity;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Allen on 2017/8/25.
 */
public interface UserMapper {

    @Select("SELECT * FROM user WHERE user_id={#userId}")
    @Results({
            @Result(property = "userId",  column = "user_id"),
            @Result(property = "idNumber", column = "id_number"),
    })
    UserEntity select(String userId);

}