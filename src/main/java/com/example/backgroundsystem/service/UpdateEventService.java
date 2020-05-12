package com.example.backgroundsystem.service;

import com.example.backgroundsystem.domain.blogsys.UpdateEvent;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UpdateEventService {

    List<UpdateEvent> listUpdateEvent();
}
