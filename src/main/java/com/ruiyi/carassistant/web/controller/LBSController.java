package com.ruiyi.carassistant.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruiyi.carassistant.entity.ClientSocketInfo;
import com.ruiyi.carassistant.entity.common.ResultMsg;
import com.ruiyi.carassistant.thread.GetGPSThread;
import com.ruiyi.carassistant.utils.LoggerUtil;
import com.ruiyi.carassistant.utils.ParseJson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: LBSController
 * @Description:
 * @author tao.zeng
 * @date 2017年3月4日 下午4:07:17
 */
@Api(tags = "LBS信息接口")
@Controller
@RequestMapping("/lbs")
public class LBSController {

    @ApiOperation(value = "获取汽车当前位置")
    @ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "simcode", value = "simcode", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userID", value = "userID", required = true, dataType = "String", paramType = "query") })
    @RequestMapping(value = "/getlonlatbysimcode", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg<Map<String, Object>> getLonLat(@RequestParam(value = "token", required = true) String token, @RequestParam(value = "simcode", required = true) String simcode,
                                                    @RequestParam(value = "userID", required = true) final String userID, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> returnInfo = new HashMap<String, Object>();
        result.put("error", "");
        result.put("success", true);
        ClientSocketInfo clientSocketInfo = ParseJson.clientSocketMap.get(simcode);
        if (null != clientSocketInfo) {
            GetGPSThread getGPSThread = new GetGPSThread(clientSocketInfo.getSocket(), userID);
            Thread t1 = new Thread(getGPSThread);
            t1.start();
            if (null == clientSocketInfo.getTrailStr()) {
                return ResultMsg.error("OBD未收到坐标");
            }
            JSONArray jsonArray = JSONArray.parseArray(clientSocketInfo.getTrailStr());
            JSONObject json = jsonArray.getJSONObject(jsonArray.size() - 1);
            LoggerUtil.debug("gps json对象：" + json);
            returnInfo.put("longitude", json.getString("longtitude"));
            returnInfo.put("latitude", json.getString("latitude"));
            return ResultMsg.success(returnInfo);
        } else {
            return ResultMsg.error("OBD不在线");
        }
    }

}
