package com.mallBuilding.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressDao {

    @Select("select id from user_address where consignee_name = #{name} and consignee_tel = #{phone} and consignee_address=#{add}")
    public Integer queryAddId(String name,String phone,String add);
}
