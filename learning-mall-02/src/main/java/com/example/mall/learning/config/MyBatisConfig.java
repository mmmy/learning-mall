package com.example.mall.learning.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(value = "com.example.mall.learning.mbg.mapper")
public class MyBatisConfig {
}
