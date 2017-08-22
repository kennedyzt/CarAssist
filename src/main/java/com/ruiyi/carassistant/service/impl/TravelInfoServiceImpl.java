package com.ruiyi.carassistant.service.impl;

import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.ruiyi.carassistant.db.travelinfo.TravelInfoRepository;
import com.ruiyi.carassistant.entity.ClientSocketInfo;
import com.ruiyi.carassistant.entity.TravelInfo;
import com.ruiyi.carassistant.entity.common.ResultMsg;
import com.ruiyi.carassistant.service.TravelInfoService;
import com.ruiyi.carassistant.utils.DateUtil;
import com.ruiyi.carassistant.utils.ParseJson;
import com.ruiyi.carassistant.utils.StringUtil;

@Service
public class TravelInfoServiceImpl implements TravelInfoService {
    @Autowired
    private TravelInfoRepository travelInfoRepository;

    @Override
    public List<Map<String, Object>> getTravelInfoByMap(Map<String, Object> params) {
        BasicDBObject filter_dbobject = new BasicDBObject();
        filter_dbobject.put("simCode", params.get("SimCode"));
        filter_dbobject.put("userId", params.get("userID"));
        return travelInfoRepository.getTravelInfoByMap(filter_dbobject, Integer.parseInt(params.get("indexLength").toString()), Integer.valueOf(params.get("indexStart").toString()));
    }

    @Override
    public List<Map<String, Object>> getSyncTravelInfoByMap(Map<String, Object> params) {
        return travelInfoRepository.getSyncTravelInfoByParams(params.get("SimCode").toString(), Integer.parseInt(params.get("userID").toString()),
<<<<<<< HEAD
                Integer.parseInt(params.get("TravelInfoID").toString()));
=======
            Integer.parseInt(params.get("TravelInfoID").toString()));
>>>>>>> 47194f29866932d95f2512306c16bf2eb1dc2d17
    }

    @Override
    public ResultMsg<Map<String, Object>> saveTravelInfo(JSONObject json, Socket clientSocket) {
        TravelInfo tripInfo = json.toJavaObject(TravelInfo.class);
        if (!StringUtil.isEmpty(tripInfo.getStartTime()) && !StringUtil.isEmpty(tripInfo.getEndTime())) {
            tripInfo.setCarState("2"); //
            tripInfo.setStartTime(null); // 结束时不修改开始时间
        } else {
            tripInfo.setCarState("1");
        }
        // 处理当有多条travelInfoID为0未上传的情况-
        // TODO 根据travelInfoID插入提升性能 if (tripInfo.getStartTime() == null &&
        // tripInfo.getEndTime() != null &&
        // tripInfo.getTravelInfoID().equals("0")) {
        if (StringUtil.isEmpty(tripInfo.getStartTime()) && !StringUtil.isEmpty(tripInfo.getEndTime())) {
            TravelInfo lastTravelInfo = travelInfoRepository.getLastTripInfoBySimCode(tripInfo.getSimCode());
            tripInfo.setTravelInfoId(lastTravelInfo.getTravelInfoId());
        }
        if (!StringUtil.isEmpty(tripInfo.getEndTime())) {
            try {
                travelInfoRepository.updateByPrimaryKeySelective(tripInfo);
            } catch (Exception e) {
                e.printStackTrace();
                return ResultMsg.error("更新失败,主键找不到主键");
            }
        } else {
            // 插入时才返回id
            tripInfo.setTravelInfoId(null);
            tripInfo.setCreateDate(DateUtil.getCurrentTime());
<<<<<<< HEAD
            travelInfoRepository.insert(tripInfo);
            if (!StringUtil.isEmpty(tripInfo.getTrailStr()) && !tripInfo.getTrailStr().equals("[]")) {
                String trailJsonStr = tripInfo.getTrailStr();
                tripInfo.setTrailStr(null);
                travelInfoRepository.appendTrailStr(tripInfo.getTravelInfoId(), trailJsonStr);
            }
=======
            if (!StringUtil.isEmpty(tripInfo.getTrailStr())) {
                String trailJsonStr = tripInfo.getTrailStr();
                tripInfo.setTrailStr(null);
                travelInfoRepository.insert(tripInfo);
                travelInfoRepository.appendTrailStr(tripInfo.getTravelInfoId(), trailJsonStr);
            }
            travelInfoRepository.insert(tripInfo);
>>>>>>> 47194f29866932d95f2512306c16bf2eb1dc2d17
        }
        // 构建socket相关信息方便汽车定位
        buildClientSocketMap(clientSocket, tripInfo.getSimCode(), tripInfo.getTrailStr());
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("travelInfoId", tripInfo.getTravelInfoId());
        resultMap.put("userId", tripInfo.getUserId());
        return ResultMsg.success(resultMap);
    }

    private void buildClientSocketMap(Socket clientSocket, String simCode, String trailStr) {
        if (clientSocket != null) {
            ClientSocketInfo clientSocketInfo = new ClientSocketInfo(clientSocket);

            if (null != ParseJson.clientSocketMap.get(simCode)) {
                if (null == trailStr) {
                    ParseJson.clientSocketMap.put(simCode, ParseJson.clientSocketMap.get(simCode).socket(clientSocket));
                } else {
                    clientSocketInfo.setTrailStr(trailStr);
                    ParseJson.clientSocketMap.put(simCode, clientSocketInfo);
                }
            } else {
                if (null != trailStr) {
                    clientSocketInfo.setTrailStr(trailStr);
                }
                ParseJson.clientSocketMap.put(simCode, clientSocketInfo);
            }

        }
    }
}
