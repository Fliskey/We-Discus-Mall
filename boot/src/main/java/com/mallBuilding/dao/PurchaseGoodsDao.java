package com.mallBuilding.dao;


import com.mallBuilding.entity.GmGoods;
import com.mallBuilding.entity.PurchaseGoods;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    @Select("select p.id " +
            "from web_mall_sky.gm_goods g, web_mall_sky.purchase_goods p " +
            "where g.id = #{id} and g.id = p.goods_id")
    Integer queryPidByGid(Integer id);

    @Delete("delete from purchase_goods p where user_id = #{uid} and goods_id = #{gid}")
    Boolean deleteByUserId(Integer uid,Integer gid);

    @Update("update purchase_goods p set p.quantity = #{quantity} where user_id = #{uid} and goods_id = #{gid}")
    Boolean updateByUserId(Integer uid,Integer gid, Integer quantity);

}
