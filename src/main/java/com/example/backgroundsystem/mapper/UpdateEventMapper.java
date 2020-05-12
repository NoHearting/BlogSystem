package com.example.backgroundsystem.mapper;

import com.example.backgroundsystem.domain.blogsys.UpdateEvent;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UpdateEventMapper {

    @Select("select * from updateEvent")
    List<UpdateEvent> listUpdateEvent();

    int countAll();
}
