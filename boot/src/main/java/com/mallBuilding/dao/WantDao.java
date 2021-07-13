package com.mallBuilding.dao;

import com.mallBuilding.entity.GmGoods;
import com.mallBuilding.entity.WantGoods;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WantDao {

    @Select("select g.* from gm_goods g,want_goods w where w.user_id = #{u_id} and w.goods_id = g.id" )
    List<GmGoods> queryByUserId(Integer u_id);

    @Select("select id from want_goods w where w.goods_id = #{g_id} and w.user_id = #{u_id}" )
    String queryId(Integer u_id,Integer g_id);

    @Delete("delete from want_goods where want_goods.id = #{id}" )
    Boolean removeById(Integer id);

    @Select("select count(*)  likeQuantity from want_goods w where w.goods_id = #{g_id}")
    Integer countLike(Integer g_id);

    @Select("select * from want_goods w where w.goods_id = #{g_id} and w.user_id = #{u_id}")
    List<GmGoods> queryByGoodsId(Integer g_id,Integer u_id);

}
