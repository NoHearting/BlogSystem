package com.example.backgroundsystem.mapper;

import com.example.backgroundsystem.domain.blogsys.Blog;
import org.apache.ibatis.annotations.Insert;

public interface TestMapper {


    @Insert("insert into blog(title,content,writeTime,readTimes) values(#{title},#{content},#{writeTime},#{readTimes})")
    void insertToBlog(Blog blog);

}
