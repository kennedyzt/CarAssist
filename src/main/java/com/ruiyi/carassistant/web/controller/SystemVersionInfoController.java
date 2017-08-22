package com.ruiyi.carassistant.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiyi.carassistant.service.VersionInfoService;
import com.ruiyi.carassistant.web.exception.ErrorException;

@Api(tags = "系统信息接口")
@Controller
public class SystemVersionInfoController {
    
    @Autowired
    private VersionInfoService versionInfoService;

    @ApiOperation(value="获取差分包URL",notes="")
    @RequestMapping(value = "/GetSystemVersionInfoServlet", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getSystemVersionInfo(
            @ApiParam("6572版本号") @RequestParam(value = "oldVersion_6572", required = false) String oldV_6572,
            @ApiParam("OBD版本号")@RequestParam(value = "oldVersion_OBD", required = false) String oldV_OBD,
            @ApiParam("Android版本号")@RequestParam(value = "oldVersion_android", required = false) String oldV_android) throws ErrorException {
        boolean success = false;
        String error = "";
        Map<String, Object> returnInfo = null;
        try {
            returnInfo = versionInfoService.getUpdateInfo(oldV_6572,oldV_OBD,oldV_android);
            success = true;
        } catch (Exception e) {
            error = e.getMessage();
            e.printStackTrace();
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("error", error);
        result.put("success", success);
        result.put("returnInfo", returnInfo);
        return result;
    }
    
}
