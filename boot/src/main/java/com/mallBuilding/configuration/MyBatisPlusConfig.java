package com.mallBuilding.configuration;

import org.springframework.context.annotation.Configuration;

//实现分页 需要的拦截器
import org.springframework.context.annotation.Bean;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

//实现分页所需
@Configuration
public class MyBatisPlusConfig{
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
