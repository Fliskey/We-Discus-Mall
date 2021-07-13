package com.mallBuilding.dao;


import com.mallBuilding.entity.GmGoods;
import com.mallBuilding.entity.PurchaseGoods;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseGoodsDao {
    @Select("select * from web_mall_sky.gm_goods where type=#{type}")
    List<GmGoods> queryGoodsByType(String type);

    @Select("select * from web_mall_sky.purchase_goods where user_id=#{userId}")
    List<PurchaseGoods> queryGoodsByUserId(Integer userId);

    @Select("select * from web_mall_sky.gm_goods where id=#{id}")
    GmGoods queryEachGoodsByGid(Integer id);

    @Select("select g.* " +
            "from web_mall_sky.gm_goods g, web_mall_sky.purchase_goods p " +
            "where p.user_id=#{userId} and p.goods_id = g.id")
    List<GmGoods> queryGMGoodsByUserId(Integer userid);
}
