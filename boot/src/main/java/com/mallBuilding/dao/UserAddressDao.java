package com.mallBuilding.dao;


import com.mallBuilding.entity.UserAddress;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddressDao {

    @Select("select id from user_address where user_id=#{id} and consignee_name = #{name} and consignee_tel = #{tel} and consignee_address = #{add}")
    public Integer queryId(Integer id,String name,String tel,String add);

    @Select("select * from user_address where user_id = #{id}")
    public UserAddress queryById(Integer id);
}
