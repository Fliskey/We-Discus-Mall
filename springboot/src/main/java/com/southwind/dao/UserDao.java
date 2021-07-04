package com.southwind.dao;

import com.southwind.entity.UmUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    @Select("select * from web_mall_building.um_user where name=#{name} and password=#{password}")
    UmUser queryUserByName(String name,String password);
}
