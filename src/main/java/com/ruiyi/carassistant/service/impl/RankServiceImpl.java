package com.ruiyi.carassistant.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruiyi.carassistant.dao.CarGlobalStateInfoMapper;
import com.ruiyi.carassistant.service.RankService;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private CarGlobalStateInfoMapper carglobalstateinfoMapper;

    @Override
    public List<Map<String, Object>> getRankListByDistance() {
        return carglobalstateinfoMapper.getRankListByDistance();
    }
    
    
}
