package com.mallBuilding.dao;

import com.mallBuilding.entity.BuyShow;
import com.mallBuilding.entity.GoodsAndBuyer;
import com.mallBuilding.entity.OmOrder;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OmOrderDao {

    @Select("select g.image_url,g.name,o.buyer_name,o.buyer_phone,o.buyer_address,o.actual_pay_amount from om_order o,gm_goods g where g.user_id = #{sellerId} and o.goods_id = g.id")
    public List<GoodsAndBuyer> queryBySellerId(Integer sellerId);

    @Select({"select o1.id, o1.goods_id, buyer_id, g.image_url, g.name, g.type, g.price, o1.actual_pay_amount/g.price quantitySiOrder, o1.actual_pay_amount, o1.has_payed, o1.has_shipped, o1.has_confirmed\n" +
            "from om_order o1, gm_goods g\n"+
            "where o1.goods_id = g.id and o1.buyer_id = #{buyerId} and o1.has_payed =#{hasPayed} and o1.has_shipped = #{hasShipped} and o1.has_confirmed = #{hasConfirmed};"})
    public List<BuyShow> queryByBuyerId(Integer buyerId, Integer hasPayed, Integer hasShipped, Integer hasConfirmed);

    @Update("update om_order o set o.has_confirmed = 1 where id = #{id}")
    Boolean confirmBuyOrder(Integer id);

}
