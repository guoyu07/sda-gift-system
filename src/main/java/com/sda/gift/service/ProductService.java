package com.sda.gift.service;

import com.sda.gift.entity.ProductEntity;
import com.sda.gift.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/8/24.
 */
@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public List<ProductEntity> list(){
        return productMapper.getAll();
    }


    public void add(ProductEntity productEntity){
        productMapper.insert(productEntity);
    }

    public void save(ProductEntity productEntity){
        productMapper.update(productEntity);
    }
}
