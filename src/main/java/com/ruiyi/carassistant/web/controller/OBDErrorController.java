package com.ruiyi.carassistant.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiyi.carassistant.service.OBDErrorService;
import com.ruiyi.carassistant.web.exception.ErrorException;
import com.ruiyi.carassistant.web.interceptor.Token;

@Api(tags = "OBD错误码接口")
@Controller
public class OBDErrorController {

    @Autowired
    private OBDErrorService obdErrorService;
    
    @ApiOperation(value="获取错误码对应的描述信息",notes="")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userID", value = "用户ID", required = true, dataType = "String", paramType="query"),
        @ApiImplicitParam(name = "token", value = "用户token", required = true, dataType = "String", paramType="query"),
        @ApiImplicitParam(name = "OBDErrorCode", value = "OBD错误码", required = true, dataType = "String", paramType="query")
    })
    @RequestMapping(value = "/SearchOBDErrorCode", method = RequestMethod.GET)
    @ResponseBody
    @Token
    public Map<String, Object> getErrorCode(@RequestParam(value="OBDErrorCode", required=true) String code) throws Exception {
        
    	List<Map<String, Object>> retList = obdErrorService.getErrorByCode(code);
        if (retList == null || retList.size() == 0) {
            throw new ErrorException("data is null!");
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("error", "");
        result.put("success", true);
        result.put("returnInfo", retList);
        return result;
    }
}
