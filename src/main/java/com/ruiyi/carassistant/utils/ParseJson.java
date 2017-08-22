package com.ruiyi.carassistant.utils;

import java.net.Socket;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruiyi.carassistant.entity.ClientSocketInfo;
import com.ruiyi.carassistant.entity.SocketResult;
import com.ruiyi.carassistant.entity.common.ResultMsg;
import com.ruiyi.carassistant.service.CarRealTimeInfoService;
import com.ruiyi.carassistant.service.ExceptionInfoService;
import com.ruiyi.carassistant.service.OBDExceptionInfoService;
import com.ruiyi.carassistant.service.TravelInfoService;
import com.ruiyi.carassistant.service.VersionInfoService;

@Component
public class ParseJson {
    private static ExceptionInfoService exceptionInfoService;
    private static OBDExceptionInfoService obdExceptionInfoService;
    private static CarRealTimeInfoService carRealTimeInfoService;
    private static TravelInfoService travelInfoService;
    private static VersionInfoService versionInfoService;
    public static JSONObject wifiParamsJsonObject = null;
    public static JSONObject updateWifiResultJsonObject = null;
    // 存放链接的所有socket及相关GPS信息
    public static Map<String, ClientSocketInfo> clientSocketMap = new ConcurrentHashMap<>();

    @Autowired
    public void setExceptionInfoService(ExceptionInfoService exceptionInfoService) {
        ParseJson.exceptionInfoService = exceptionInfoService;
    }

    @Autowired
    public void setTravelInfoService(TravelInfoService travelInfoService) {
        ParseJson.travelInfoService = travelInfoService;
    }

    @Autowired
    public void setVersionInfoService(VersionInfoService versionInfoService) {
        ParseJson.versionInfoService = versionInfoService;
    }

    @Autowired
    public void setCarRealTimeInfoService(CarRealTimeInfoService carRealTimeInfoService) {
        ParseJson.carRealTimeInfoService = carRealTimeInfoService;
    }

    @Autowired
    public void setObdExceptionInfoService(OBDExceptionInfoService obdExceptionInfoService) {
        ParseJson.obdExceptionInfoService = obdExceptionInfoService;
    }

    public static String PreProcessTerminalData(String jsonString) throws ParseException {
        return "";
    }

    private static final String GETLOCURL = "http://api.cellocation.com/cell/?mcc=460&mnc=1&lac=LAC&ci=CI&output=xml";

    public static Map<String, Object> getLoc(String lac, String ci) {
        String requestUrl = GETLOCURL.replace("LAC", lac).replace("CI", ci);
        Map<String, Object> paramMap = RequestUtil.sendGet(requestUrl);
        return paramMap;
    }

    public static SocketResult ParseTerminalData(String jsonString, Socket clientSocket) {
        SocketResult socketResult = new SocketResult(false);
        Map<String, Object> result = new HashMap<>(); // socketMap中的result
        if ("".equals(jsonString))
            return null;
        try {
            JSONObject jsonObj = JSON.parseObject(jsonString);
            String method = jsonObj.getString("method");
            String params = jsonObj.getString("params");
            if (method != null && params != null) {
                socketResult.setMethod(method);
                JSONObject jsonObject = JSONObject.parseObject(params);
                switch (method) {
                    case "PackExceInfo":
                        // 汽车异常信息
                        exceptionInfoService.addExceptionInfo(params);
                        break;
                    case "carRTStateInfo":
                        // 汽车实时数据
                        carRealTimeInfoService.saveCarRealTimeInfo(params);
                        break;
                    case "PackTripInfo":
                        // 临时行程单
                        ResultMsg<Map<String, Object>> saveResultMsg = travelInfoService.saveTravelInfo(jsonObject, clientSocket);
                        if (!saveResultMsg.getSuccess()) {
                            return socketResult;
                        }
                        result.put("userId", saveResultMsg.getReturnInfo().get("userId"));
                        result.put("travelInfoId", saveResultMsg.getReturnInfo().get("travelInfoId"));
                        socketResult.setResult(result);
                        break;
                    case "3g_requestUpdate":
                        String oldV_6572 = jsonObject.getString("oldVersion_6572");
                        String oldV_OBD = jsonObject.getString("oldVersion_OBD");
                        String oldV_android = jsonObject.getString("oldVersion_android");
                        Map<String, Object> updateInfo = versionInfoService.getUpdateInfo(oldV_6572, oldV_OBD, oldV_android);
                        // jsonReturn = new JSONObject(updateInfo);
                        // TODO 逻辑有问题待修改
                        break;
                    case "OBDExceptionInfo":
                        obdExceptionInfoService.addExceptionInfo(params);
                        break;
                    case "PackLocalInfo":
                        JSONObject gpsJsonObject = JSONObject.parseObject(params);
                        LoggerUtil.debug("收到GPS信息：" + gpsJsonObject);
                        String longitude = gpsJsonObject.getString("longtitude");
                        String latitude = gpsJsonObject.getString("latitude");
                        if (longitude != null && latitude != null) {
                            ClientSocketInfo nowClientSocketInfo = new ClientSocketInfo(clientSocket);
                            nowClientSocketInfo.setTrailStr("[{\"longtitude\":\"" + longitude + "\",\"latitude\":\"" + latitude + "\"}]");
                            clientSocketMap.put(gpsJsonObject.getString("SimCode"), nowClientSocketInfo);
                        } else {
                            Map<String, Object> gpsResultMap = getLoc(gpsJsonObject.getString("lac"), gpsJsonObject.getString("cid"));
                            if (null != gpsResultMap.get("lon")) {
                                ClientSocketInfo nowClientSocketInfo = new ClientSocketInfo(clientSocket);
                                nowClientSocketInfo.setTrailStr("[{\"longtitude\":\"" + gpsResultMap.get("lon").toString() + "\",\"latitude\":\"" + gpsResultMap.get("lat") + "\"}]");
                                clientSocketMap.put(gpsJsonObject.getString("SimCode"), nowClientSocketInfo);
                            }
                        }
                        break;
                    case "PackWifiInfo":
                        wifiParamsJsonObject = JSON.parseObject(params);
                        break;
                    case "UpdateWifiInfo":
                        updateWifiResultJsonObject = JSON.parseObject(params);
                        break;
                    case "obd_update":
                        result.put("status", jsonObject.get("status"));
                        socketResult.setResult(result);
                        break;
                    default:
                        LoggerUtil.info("method in not exist!");
                        break;
                }
            }
        } catch (Exception e) {
            LoggerUtil.error(e.getMessage(), e);
            return socketResult;
        }
        socketResult.setSuccess(true);
        return socketResult;
    }
}
