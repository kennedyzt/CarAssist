package com.ruiyi.carassistant.service;

import java.util.Map;

public interface GlobalInfoService {

    Map<String, Object> getGlobalInfoBysimCodeAnduserID(String simCode, String userID);

    /**
     * 保存汽车全局信息, 表: (carglobalstateinfo).
     * @param carGlobalStateInfo
     * @author Yipd
     * @date 2016-11-10 下午2:28:26
     */
    void saveCarGlobalStateInfo(String carGlobalStateInfo);

}
