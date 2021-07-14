package com.mallBuilding.dao;

import com.mallBuilding.entity.Admin;
import com.mallBuilding.entity.UmUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao {

    @Select("select * from web_mall_sky.admin where id=#{id} and password=#{password}")
    Admin queryAdminById(Integer id, String password);

    @Select("select id from web_mall_sky.admin where tel_number = #{tel}")
    Integer queryIdByTel(String tel);

    @Select("select salt from web_mall_sky.admin where id=#{id}")
    String querySaltById(Integer id);

}
