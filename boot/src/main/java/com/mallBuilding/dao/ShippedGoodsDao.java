package com.mallBuilding.dao;

import com.mallBuilding.entity.ShippedGoods;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface ShippedGoodsDao {
    @Select("select * from shipped_goods where order_id =#{id}")
    public ShippedGoods hasShipped(Integer id);
}
