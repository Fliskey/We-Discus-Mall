package com.mallBuilding.dao;

import com.mallBuilding.entity.UmLogin;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UmLoginDao {
    @Select("select id" +
            "from web_mall_building" +
            "where password={#password}")
    Integer queryLoginByPassword(String password);
}
