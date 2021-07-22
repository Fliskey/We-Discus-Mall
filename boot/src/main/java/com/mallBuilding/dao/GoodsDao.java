package com.mallBuilding.dao;


import com.mallBuilding.entity.GmGoods;
import com.mallBuilding.entity.UmUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsDao {
    @Select("select * from web_mall_sky.gm_goods where type=#{type}")
    List<GmGoods> queryGoodsByType(String type);

    @Select("select * from web_mall_sky.gm_goods where user_id=#{userId}")
    List<GmGoods> queryGoodsByUserId(Integer userId);

    @Select("select * from web_mall_sky.gm_goods where id=#{goodsId}")
    GmGoods queryGoodsByGoodsId(Integer goodsId);
}
