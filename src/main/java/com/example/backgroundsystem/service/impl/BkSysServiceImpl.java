package com.example.backgroundsystem.service.impl;

import com.example.backgroundsystem.mapper.BkSysMapper;
import com.example.backgroundsystem.service.BkSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BkSysServiceImpl implements BkSysService {

    @Autowired
    private BkSysMapper bkSysMapper;

    @Override
    public boolean login(String username, String password) {
        return bkSysMapper.login(username,password)>0;
    }
}
