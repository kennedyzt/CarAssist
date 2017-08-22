package com.ruiyi.carassistant.service.impl;

import java.sql.Timestamp;

import com.ruiyi.carassistant.dao.CarRealTimeInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruiyi.carassistant.entity.CarRealTimeInfo;
import com.ruiyi.carassistant.service.CarRealTimeInfoService;

@Service
public class CarRealTimeInfoServiceImpl implements CarRealTimeInfoService {

    @Autowired
    private CarRealTimeInfoMapper carRealTimeInfoMapper;
    
    @Override
    public void saveCarRealTimeInfo(String carRealTimeInfo) {
        JSONArray jsonArray = JSONArray.parseArray(carRealTimeInfo);
        JSONObject json = null;
        for (int i = 0; i < jsonArray.size(); i++) {
            json = jsonArray.getJSONObject(i);
            CarRealTimeInfo RTInfo = new CarRealTimeInfo();
            RTInfo.setSimCode(json.getString("SimCode"));
            RTInfo.setUserID(json.getIntValue("userID"));
            RTInfo.setFireState(json.getString("fireState"));
            String curTime = json.getString("curTime");
            if (curTime != null && 0 != curTime.compareTo("")) {
                Timestamp date = Timestamp.valueOf(curTime);
                System.out.println(date.toString());
                RTInfo.setCurTime(date);
            }

            RTInfo.setVoltage(json.getFloat("voltage"));
            RTInfo.setRMP(json.getIntValue("RMP"));
            RTInfo.setWarterTemp(json.getFloat("warterTemp"));
            RTInfo.setSpeed(json.getIntValue("speed"));
            RTInfo.setAvgSpeed(json.getIntValue("AverageSpeed"));
            RTInfo.setDriveTime(json.getFloat("driveTime"));
            RTInfo.setDriveDuration(json.getIntValue("driveDuration"));
            RTInfo.setDriveDistance(json.getFloat("driveDistance"));
            RTInfo.setCurConsume(json.getFloat("FuelConsume"));
            RTInfo.setAccConsume(json.getFloat("FuelWholeConsume"));
            RTInfo.setOilLeft(json.getFloat("oilLeft"));
            RTInfo.setEngine_load(json.getFloat("engine_load"));
            RTInfo.setCurTripDistance(json.getFloat("TripMileage"));
            RTInfo.setLongitude(json.getFloat("Longitude"));
            RTInfo.setLatitude(json.getFloat("Latitud"));

            carRealTimeInfoMapper.insertSelective(RTInfo);
        }
    }

    @Override
    public CarRealTimeInfo getCarRealTimeInfoByUserIDAndSimCode(String userID, String simCode) {
        return carRealTimeInfoMapper.getCarRealTimeInfoByUserIDAndSimCode(userID,simCode);
    }

}
