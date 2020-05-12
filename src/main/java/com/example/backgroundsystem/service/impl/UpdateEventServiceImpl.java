package com.example.backgroundsystem.service.impl;

import com.example.backgroundsystem.domain.blogsys.UpdateEvent;
import com.example.backgroundsystem.mapper.UpdateEventMapper;
import com.example.backgroundsystem.service.UpdateEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateEventServiceImpl implements UpdateEventService {

    @Autowired
    UpdateEventMapper updateEventMapper;

    @Override
    public List<UpdateEvent> listUpdateEvent() {
        return updateEventMapper.listUpdateEvent();
    }
}
