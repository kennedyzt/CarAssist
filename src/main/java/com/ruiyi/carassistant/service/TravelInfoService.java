package com.ruiyi.carassistant.service;

import java.net.Socket;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.ruiyi.carassistant.entity.common.ResultMsg;

public interface TravelInfoService {

    List<Map<String, Object>> getTravelInfoByMap(Map<String, Object> params);

    List<Map<String, Object>> getSyncTravelInfoByMap(Map<String, Object> params);

    ResultMsg<Map<String, Object>> saveTravelInfo(JSONObject json, Socket clientSocket);

}
