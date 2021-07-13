package com.mallBuilding.dao;

import com.mallBuilding.entity.UmLogin;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UmLoginDao {
    @Select("select id from web_mall_sky.um_login where password=#{password}")
    Integer queryLoginIdByPassword(String password);

    @Select("select salt from web_mall_sky.um_login where id=#{id}")
    String querySaltById(Integer id);

    @Select("select * from web_mall_sky.um_login where id=#{id} and password=#{password}")
    UmLogin queryUmLoginByIdAndPassword(Integer id, String password);

}
