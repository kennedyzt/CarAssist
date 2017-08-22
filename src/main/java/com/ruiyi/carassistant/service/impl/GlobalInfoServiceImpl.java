package com.ruiyi.carassistant.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ruiyi.carassistant.dao.CarGlobalStateInfoMapper;
import com.ruiyi.carassistant.entity.CarGlobalStateInfo;
import com.ruiyi.carassistant.service.GlobalInfoService;

@Service
public class GlobalInfoServiceImpl implements GlobalInfoService {

    @Autowired
    private CarGlobalStateInfoMapper carglobalstateinfoMapper;
    
    @Override
    public Map<String, Object> getGlobalInfoBysimCodeAnduserID(String simCode, String userID) {
        return carglobalstateinfoMapper.getGlobalInfoBysimCodeAnduserID(simCode, userID);
    }

    @Override
    public void saveCarGlobalStateInfo(String carGlobalStateInfo) {
        JSONObject jsonGobalInfo = JSONObject.parseObject(carGlobalStateInfo);
        CarGlobalStateInfo globalInfo = new CarGlobalStateInfo();
        globalInfo.setUserID(jsonGobalInfo.getIntValue("userID"));
        globalInfo.setSimCode(jsonGobalInfo.getString("SimCode"));
        globalInfo.setOtherInfo(jsonGobalInfo.getString("OtherInfo"));
        globalInfo.setAllDriveTime(jsonGobalInfo.getFloat("allDriveTime"));
        globalInfo.setAllDriveDistance(jsonGobalInfo.getFloat("allDriveDistance"));
        globalInfo.setAllFuelConsume(jsonGobalInfo.getFloat("allFuelConsume"));
        globalInfo.setAllCost(globalInfo.getAllFuelConsume() * 6.f);
        carglobalstateinfoMapper.insert(globalInfo);
    }

}
