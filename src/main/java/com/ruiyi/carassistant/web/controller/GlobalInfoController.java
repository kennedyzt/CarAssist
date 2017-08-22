package com.ruiyi.carassistant.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiyi.carassistant.service.GlobalInfoService;
import com.ruiyi.carassistant.web.exception.ErrorException;
import com.ruiyi.carassistant.web.interceptor.Token;

@Api(tags = "全局信息接口")
@Controller
public class GlobalInfoController {
    
    @Autowired
    private GlobalInfoService globalInfoService;

    @ApiOperation(value="获取全局信息",notes="")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userID", value = "用户ID", required = true, dataType = "String", paramType="query"),
        @ApiImplicitParam(name = "token", value = "用户token", required = true, dataType = "String", paramType="query"),
        @ApiImplicitParam(name = "SimCode", value = "设备识别码", required = true, dataType = "String", paramType="query")
    })
    @RequestMapping(value = "/GetGlobalInfoServlet", method = RequestMethod.GET)
    @ResponseBody
    @Token
    public Map<String, Object> getGlobalInfo(
            @RequestParam(value = "SimCode", required = true) String simCode,
            @RequestParam(value = "userID", required = true) String userID) throws ErrorException {

        Map<String, Object> retMap = globalInfoService.getGlobalInfoBysimCodeAnduserID(simCode, userID);
        if (retMap == null) {
            throw new ErrorException("data is null!");
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("error", "");
        result.put("success", true);
        result.put("returnInfo", retMap);
        return result;
    }
}
