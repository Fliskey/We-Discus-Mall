package com.mallBuilding.dao;

import com.mallBuilding.entity.UmUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    @Select("select * from web_mall_sky.um_user where id=#{id} and password=#{password}")
    UmUser queryUserByName(Integer id,String password);

    @Select("select id from web_mall_sky.um_user where tel_number = #{tel}")
    Integer queryIdByTel(String tel);

}
