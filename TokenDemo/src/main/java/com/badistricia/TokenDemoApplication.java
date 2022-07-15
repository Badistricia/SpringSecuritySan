package com.badistricia;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.badistricia.user.mapper")
public class TokenDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TokenDemoApplication.class, args);
    }
}
