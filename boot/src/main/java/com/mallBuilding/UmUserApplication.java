package com.mallBuilding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mallBuilding.mapper")
@MapperScan("com.mallBuilding.dao")
public class UmUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UmUserApplication.class, args);
    }

}
