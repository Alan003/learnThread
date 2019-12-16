package com.alan.mybatis;

import com.alan.mapper.QueryMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.util.Map;


public class mybatis_spring {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        SqlSessionFactory sqlSessionFactory = (SqlSessionFactory)applicationContext.getBean("sqlSessionFactory");
        //DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //QueryMapper mapper = sqlSession.getMapper(QueryMapper.class);
        QueryMapper mapper = applicationContext.getBean(QueryMapper.class);
        Map list = mapper.list();
        System.out.println(list);
    }
}
