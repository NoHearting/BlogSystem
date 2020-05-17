package com.example.backgroundsystem.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface BkSysMapper {

    @Select("select count(*) from adminUser where username = #{username} and password = #{password}")
    int login(@Param("username") String username,@Param("password") String password);
}
