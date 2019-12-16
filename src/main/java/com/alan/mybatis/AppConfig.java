package com.alan.mybatis;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan("com.alan.mapper")
public class AppConfig {

    @Bean
    public DataSource dataSource() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("driver","com.mysql.jdbc.Driver");
        map.put("url","jdbc:mysql://47.106.159.61:3306/mall?useUnicode=true&characterEncoding=utf-8");
        map.put("username","root");
        map.put("password","root");
        return DruidDataSourceFactory.createDataSource(map);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory () throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }
}
