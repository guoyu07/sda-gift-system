package com.sda.gift.mapper;

import com.sda.gift.entity.ProductEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Allen on 2017/8/25.
 */
public interface ProductMapper {
    @Select("SELECT * FROM product")
    @Results({
            @Result(property = "proName",  column = "pro_name"),
            @Result(property = "proId", column = "pro_id"),
            @Result(property = "proUrl", column = "pro_url"),
            @Result(property = "proDescription", column = "pro_description"),
            @Result(property = "proPrice", column = "pro_price")
    })
    List<ProductEntity> getAll();

    @Insert("INSERT INTO product(guid,pro_name,pro_id,pro_url,pro_description,pro_price,available)" +
            " VALUES (#{guid},#{proName},#{proId},#{proUrl},#{proDescription},#{proPrice},#{available})")
    void insert(ProductEntity product);

    @Update("UPDATE product SET pro_name=#{proName}, pro_id=#{proId},pro_url=#{proUrl},pro_description=#{proDescription},pro_price=#{proPrice},available=#{available}")
    void update(ProductEntity product);

}
