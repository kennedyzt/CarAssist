package com.ruiyi.carassistant.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruiyi.carassistant.service.TravelInfoService;
import com.ruiyi.carassistant.web.exception.ErrorException;
import com.ruiyi.carassistant.web.interceptor.Token;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "行程信息接口")
@Controller
public class TravelInfoController {
    @Autowired
    private TravelInfoService travelInfoService;

    @ApiOperation(value = "获取行程单列表", notes = "")
    @ApiImplicitParams({ @ApiImplicitParam(name = "userID", value = "用户ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "token", value = "用户token", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "SimCode", value = "设备识别码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "总条数", required = true, dataType = "String", paramType = "query") })
    @RequestMapping(value = "/GetTravelInfoServlet", method = RequestMethod.GET)
    @ResponseBody
    @Token
    public Map<String, Object> getTravelInfo(@RequestParam(value = "userID", required = true) String userID, @RequestParam(value = "SimCode", required = true) String simCode,
                                             @RequestParam(value = "pageNum", required = true) String pageNum, @RequestParam(value = "pageSize", required = true) String pageSize)
        throws ErrorException {

        String error = "";
        Boolean success = false;
        Map<String, Object> result = new HashMap<String, Object>();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userID", Integer.valueOf(userID));
        params.put("SimCode", simCode);
        params.put("indexStart", (Integer.valueOf(pageNum) - 1) * Integer.valueOf(pageSize));
        params.put("indexLength", Integer.valueOf(pageSize));
        List<Map<String, Object>> travelinfos = travelInfoService.getTravelInfoByMap(params);
        if (travelinfos != null && travelinfos.size() != 0) {
            success = true;
        } else {
            error = "data is null!";
        }
        result.put("error", error);
        result.put("success", success);
        result.put("returnInfo", travelinfos);
        return result;
    }

    @ApiOperation(value = "同步行程单", notes = "")
    @ApiImplicitParams({ @ApiImplicitParam(name = "userID", value = "用户ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "token", value = "用户token", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "SimCode", value = "设备识别码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "TravelInfoID", value = "行程单编号（获取大于该ID的行程单）", required = true, dataType = "String", paramType = "query") })
    @RequestMapping(value = "/SyncTravelInfo", method = RequestMethod.GET)
    @ResponseBody
    @Token
    public Map<String, Object> SyncTravelInfo(@RequestParam(value = "userID", required = true) String userID, @RequestParam(value = "SimCode", required = true) String simCode,
                                              @RequestParam(value = "TravelInfoID", required = true) String TravelInfoID)
        throws ErrorException {
        String error = "";
        Boolean success = false;
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userID", Integer.valueOf(userID));
        params.put("SimCode", simCode);
        params.put("TravelInfoID", Integer.valueOf(TravelInfoID)); // 已同步完整行程单条数
        List<Map<String, Object>> travelinfos = travelInfoService.getSyncTravelInfoByMap(params);
        if (travelinfos != null && travelinfos.size() != 0) {
            success = true;
        } else {
            error = "data is null!";
        }
        result.put("error", error);
        result.put("success", success);
        // 返回时转换大小写映射
        result.put("returnInfo", travelinfos);
        return result;
    }
}
