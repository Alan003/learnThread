package com.alan.mybatis;

import com.alan.mapper.QueryMapper;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class StartupMybatis  {
    public static void main(String[] args) throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("driver","com.mysql.jdbc.Driver");
        map.put("url","jdbc:mysql://47.106.159.61:3306/mall?useUnicode=true&characterEncoding=utf-8");
        map.put("username","root");
        map.put("password","root");
        DataSource dataSource = DruidDataSourceFactory.createDataSource(map);

        TransactionFactory transactionFactory =
                new JdbcTransactionFactory();
        Environment environment =
                new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMappers("com.alan.mapper");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        SqlSession session = sqlSessionFactory.openSession();
        QueryMapper queryMapper = session.getMapper(QueryMapper.class);
        Map list = queryMapper.list();
        System.out.println(list);

    }

}
