package com.southwind;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.southwind.mapper")
@MapperScan("com.southwind.dao")
public class UmUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UmUserApplication.class, args);
    }

}
