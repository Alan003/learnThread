package com.alan.mybatis;

import com.alan.entity.PmsBrand;
import com.alan.mapper.QueryMapper;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import sun.misc.Resource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class StartupMybatis  {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession(true);
        SqlSession session1 = sqlSessionFactory.openSession(true);
        QueryMapper queryMapper = session.getMapper(QueryMapper.class);
       // QueryMapper queryMapper2 = session1.getMapper(QueryMapper.class);
        System.out.println("queryMapper读取数据"+ queryMapper.list());
        System.out.println("queryMapper读取数据"+ queryMapper.list());

        PmsBrand pmsBrand = new PmsBrand();
        pmsBrand.setId(3L);
        pmsBrand.setName("bbb");
        System.out.println("queryMapper2更新数据"+queryMapper.update(pmsBrand));
//        System.out.println("queryMapper读取数据"+queryMapper.list());
//        System.out.println("queryMapper2读取数据"+queryMapper2.list());



    }

}
