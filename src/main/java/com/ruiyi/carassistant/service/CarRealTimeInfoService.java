package com.ruiyi.carassistant.service;


import com.ruiyi.carassistant.entity.CarRealTimeInfo;

public interface CarRealTimeInfoService {

    /**
     * 保存汽车实时信息, 表: (carrealtimeinfo).
     * @param carRealTimeInfo
     * @author Yipd
     * @date 2016-11-10 下午2:32:05
     */
    void saveCarRealTimeInfo(String carRealTimeInfo);

    CarRealTimeInfo getCarRealTimeInfoByUserIDAndSimCode(String userID, String simCode);
}
