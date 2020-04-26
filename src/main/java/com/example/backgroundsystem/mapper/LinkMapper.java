package com.example.backgroundsystem.mapper;

import com.example.backgroundsystem.domain.Link;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LinkMapper {


    @Select("select * from friendLink")
    List<Link> listLinked();

    @Insert("insert into friendLink(friendName,webUrl,headPicture) values(#{friendName},#{webUrl},#{headPicture})")
    void insertLink(Link link);
}
