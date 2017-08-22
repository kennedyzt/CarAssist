package com.ruiyi.carassistant.web.controller;

import com.ruiyi.carassistant.entity.CarRealTimeInfo;
import com.ruiyi.carassistant.entity.ResultInfo;
import com.ruiyi.carassistant.service.CarRealTimeInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Lenovo on 2017/2/13.
 */
@Api("汽车实时信息接口")
@Controller
public class CarRealTimeInfoController {

    @Autowired
    private CarRealTimeInfoService carRealTimeInfoService;

    @ApiOperation(value="获取指定汽车实时信息",notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户ID", required = true, dataType = "String", paramType="query"),
            @ApiImplicitParam(name = "token", value = "用户token", required = true, dataType = "String", paramType="query"),
            @ApiImplicitParam(name = "simCode", value = "设备识别码", required = true, dataType = "String", paramType="query")
    })
    @RequestMapping(value = "/getCarRealTimeInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getCarRealTimeInfo (
            @RequestParam(value = "userID", required = true) String userID,
            @RequestParam(value = "simCode", required = true) String simCode) {
        ResultInfo resultInfo = new ResultInfo();
        CarRealTimeInfo carRealTimeInfo = carRealTimeInfoService.getCarRealTimeInfoByUserIDAndSimCode(userID, simCode);
        if (carRealTimeInfo != null) {
            resultInfo.setSuccess(true);
            resultInfo.setReturnInfo(carRealTimeInfo);
        }
        return resultInfo;
    }

}
