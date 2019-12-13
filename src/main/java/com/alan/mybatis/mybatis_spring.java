package com.alan.mybatis;

import com.alan.mapper.QueryMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class mybatis_spring {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(config.class);
        QueryMapper bean = applicationContext.getBean(QueryMapper.class);
        Map list = bean.list();
        System.out.println(list);
    }
}
