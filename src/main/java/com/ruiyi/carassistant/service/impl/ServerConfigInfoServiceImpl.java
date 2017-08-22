package com.ruiyi.carassistant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruiyi.carassistant.dao.ServerConfigInfoMapper;
import com.ruiyi.carassistant.service.ServerConfigInfoService;

@Service
public class ServerConfigInfoServiceImpl implements ServerConfigInfoService {
    
    @Autowired
    private ServerConfigInfoMapper serverConfigInfoMapper;

    @Override
    public String getConfigNameByConfigValue(String configName) {
        return serverConfigInfoMapper.getConfigNameByConfigValue(configName);
    }

    @Override
    public String getConfigValueByConfigName(String configValue) {
        return serverConfigInfoMapper.getConfigValueByConfigName(configValue);
    }

}
