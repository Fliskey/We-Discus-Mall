package com.mallBuilding.dao;


import com.mallBuilding.entity.GmGoods;
import com.mallBuilding.entity.GoodsAndBuyer;
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

    @Select("select o.id,g.image_url,g.name,g.id goodsId,o.buyer_name,o.buyer_phone,o.buyer_address,o.actual_pay_amount/g.price quantity,o.actual_pay_amount , o.has_shipped,o.has_confirmed,o.has_payed " +
            "from om_order o,gm_goods g " +
            "where o.id = #{orderId} and g.id = o.goods_id")
    public GoodsAndBuyer queryGoodsByOrderId(Integer orderId);

    @Select("select quantity from gm_goods where id = #{gid}")
    public Integer findQuantityById(Integer id);

    @Select("select name from gm_goods where id = #{gid}")
    public String findName(Integer id);

    @Select("select * from web_mall_sky.gm_goods where name LIKE CONCAT('%',#{content},'%') or description LIKE CONCAT('%',#{content},'%')")
    List<GmGoods> findSelect(String content);
}
