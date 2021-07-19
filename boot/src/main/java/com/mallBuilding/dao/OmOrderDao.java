package com.mallBuilding.dao;

import com.mallBuilding.entity.GoodsAndBuyer;
import com.mallBuilding.entity.OmOrder;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OmOrderDao {

    @Select("select g.image_url,g.name,o.buyer_name,o.buyer_phone,o.buyer_address,o.actual_pay_amount from om_order o,gm_goods g where g.user_id = #{sellerId} and o.goods_id = g.id")
    public List<GoodsAndBuyer> queryBySellerId(Integer sellerId);
}
