package com.example.backgroundsystem.service.impl;

import com.example.backgroundsystem.domain.blogsys.UpdateEvent;
import com.example.backgroundsystem.mapper.UpdateEventMapper;
import com.example.backgroundsystem.service.UpdateEventService;
import com.example.backgroundsystem.service.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateEventServiceImpl implements UpdateEventService {

    @Autowired
    UpdateEventMapper updateEventMapper;

    @Override
    public List<UpdateEvent> listUpdateEvent() {
        List<UpdateEvent> updateEvents = updateEventMapper.listUpdateEvent();

        CommonUtils.dealEventDate(updateEvents);
        return updateEvents;
    }
}
