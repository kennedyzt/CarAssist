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
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiyi.carassistant.service.RankService;
import com.ruiyi.carassistant.web.exception.ErrorException;
import com.ruiyi.carassistant.web.interceptor.Token;

@Api(tags = "排行信息接口")
@Controller
public class RankController {

    @Autowired
    private RankService rankService;
    
    @ApiOperation(value="获取排行榜数据",notes="")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userID", value = "用户ID", required = true, dataType = "String", paramType="query"),
        @ApiImplicitParam(name = "token", value = "用户token", required = true, dataType = "String", paramType="query")
    })
    @RequestMapping(value = "/GetRankListByDistanceServlet", method = RequestMethod.GET)
    @ResponseBody
    @Token
    public Map<String, Object> getRankListByDistance() throws Exception {
        List<Map<String, Object>> retMap = rankService.getRankListByDistance();
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
