package com.alan.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface QueryMapper {
    @Select("select * from pms_brand where id=1")
    Map list();
}
