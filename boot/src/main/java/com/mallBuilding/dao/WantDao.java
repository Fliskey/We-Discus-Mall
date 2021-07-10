package com.mallBuilding.dao;

import com.mallBuilding.entity.GmGoods;
import com.mallBuilding.entity.WantGoods;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WantDao {

    @Select("select g.* from gm_goods g,want_goods w where w.user_id = #{u_id} and w.goods_id = g.id" )
    List<GmGoods> queryByUserId(String u_id);

    @Select("select id from want_goods w where w.goods_id = #{g_id} and w.user_id = #{u_id}" )
    String removeById(String g_id,String u_id);

}
