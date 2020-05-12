package com.example.backgroundsystem.service.impl;

import com.example.backgroundsystem.domain.blogsys.Link;
import com.example.backgroundsystem.mapper.LinkMapper;
import com.example.backgroundsystem.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkMapper linkMapper;

    @Override
    public List<Link> listLinked() {
        return linkMapper.listLinked();
    }
}
