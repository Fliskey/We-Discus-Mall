package com.mallBuilding.dao;

import com.mallBuilding.entity.BuyShow;
import com.mallBuilding.entity.GoodsAndBuyer;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OmOrderDao {

    @Select("select o.id,g.image_url,g.name,o.buyer_name,o.buyer_phone,o.buyer_address,o.actual_pay_amount , o.has_shipped from om_order o,gm_goods g where g.user_id = #{sellerId} and o.goods_id = g.id")
    public List<GoodsAndBuyer> queryBySellerId(Integer sellerId);

    @Select({
            "select o1.id, o1.goods_id, buyer_id, g.image_url, g.name, g.type, g.price, o1.actual_pay_amount/g.price quantitySiOrder, o1.actual_pay_amount, o1.has_payed, o1.has_shipped, o1.has_confirmed\n"
                    + "from om_order o1, gm_goods g\n"
                    + "where o1.goods_id = g.id and o1.buyer_id = #{buyerId} and o1.has_payed =#{hasPayed} and o1.has_shipped = #{hasShipped} and o1.has_confirmed = #{hasConfirmed};" })
    public List<BuyShow> queryByBuyerId(Integer buyerId, Integer hasPayed, Integer hasShipped, Integer hasConfirmed);

    @Update("update om_order o set o.has_confirmed = 1 where id = #{id}")
    Boolean confirmBuyOrder(Integer id);

    @Select({
            "select o1.id, o1.goods_id, buyer_id, g.image_url, g.name, g.type, g.price, o1.actual_pay_amount/g.price quantitySiOrder, o1.actual_pay_amount, o1.has_payed, o1.has_shipped, o1.has_confirmed\n"
                    + "from om_order o1, gm_goods g\n"
                    + "where o1.goods_id = g.id and o1.buyer_id = #{buyerId} and o1.has_payed =1\n"
                    + "order by o1.has_confirmed,o1.has_shipped;" })
    public List<BuyShow> queryByBuyerIdSimple(Integer buyerId);

    @Delete("delete from om_order o where goods_id = #{gid} and buyer_id = #{bid}")
    Boolean deleteByUserId(Integer gid,Integer bid);

    @Update("update om_order set has_payed = 1 where id = #{id}")
    public boolean updatePay(Integer id);


    @Select("select buyer_id from om_order where goods_id=#{gid}")
    Boolean selectByGood(Integer gid);


    @Select({
            "select o1.id, o1.goods_id, buyer_id, g.user_id seller_id, g.image_url, g.name, g.type, g.price, o1.actual_pay_amount/g.price quantitySiOrder, o1.actual_pay_amount, o1.has_payed, o1.has_shipped, o1.has_confirmed\n"
                    + "from om_order o1, gm_goods g\n"
                    + "where o1.goods_id = g.id \n"
                    + "order by o1.has_confirmed,o1.has_shipped;" })
    public List<BuyShow> adminOrderAll();
}
