package com.alan.mapper;

import com.alan.entity.PmsBrand;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Bean;

import java.util.Map;

public interface QueryMapper {
   //@Select("select * from pms_brand where id=1")
    Map list();

    int add(PmsBrand pmsBrand);

    int update(PmsBrand pmsBrand);
}
