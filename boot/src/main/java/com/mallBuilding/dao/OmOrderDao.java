package com.mallBuilding.dao;

import com.mallBuilding.entity.GoodsAndBuyer;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OmOrderDao {

    @Select("select o.id,g.image_url,g.name,o.buyer_name,o.buyer_phone,o.buyer_address,o.actual_pay_amount from om_order o,gm_goods g where g.user_id = #{sellerId} and o.goods_id = g.id")
    public List<GoodsAndBuyer> queryBySellerId(Integer sellerId);

    @Delete("delete from om_order o where goods_id = #{gid} and buyer_id = #{bid}")
    Boolean deleteByUserId(Integer gid,Integer bid);

    @Select("select buyer_id from om_order where goods_id=#{gid}")
    Boolean selectByGood(Integer gid);
}
