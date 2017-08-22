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

import com.ruiyi.carassistant.service.NewsCategoryService;
import com.ruiyi.carassistant.service.NewsListService;
import com.ruiyi.carassistant.web.interceptor.Token;

@Api(tags = "新闻信息接口")
@Controller
public class NewsInfoController {
    
    @Autowired
    private NewsListService newsListService;
    
    @Autowired
    private NewsCategoryService newsCategoryService;

    @ApiOperation(value="获取新闻列表",notes="")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userID", value = "用户ID", required = true, dataType = "String", paramType="query"),
        @ApiImplicitParam(name = "token", value = "用户token", required = true, dataType = "String", paramType="query"),
        @ApiImplicitParam(name = "newsCategoryID", value = "种类ID", required = true, dataType = "String", paramType="query"),
        @ApiImplicitParam(name = "pageNum", value = "当前页数", required = true, dataType = "String", paramType="query"),
        @ApiImplicitParam(name = "pageSize", value = "总条数", required = true, dataType = "String", paramType="query")
    })
    @RequestMapping(value = "/GetNewsListServlet", method = RequestMethod.GET)
    @ResponseBody
    @Token
    public Map<String, Object> getNewsList(
            @RequestParam(value = "pageNum", required = true) String pageNum,
            @RequestParam(value = "pageSize", required = true) String pageSize,
            @RequestParam(value = "newsCategoryID", required = true) String newsCategoryID) {
        String error = "";
        Boolean success = false;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("indexStart", (Integer.valueOf(pageNum) - 1) * Integer.valueOf(pageSize));
        params.put("indexLength", Integer.valueOf(pageSize));
        params.put("newsCategoryID", newsCategoryID);
        List<Map<String, Object>> newsList = newsListService.getNewsList(params);
        if (newsList != null && newsList.size() != 0) {
            success = true;
        } else {
            error = "data is null!";
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("error", error);
        result.put("success", success);
        result.put("returnInfo", newsList);
        return result;
    }
    
    @ApiOperation(value="获取新闻种类",notes="")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userID", value = "用户ID", required = true, dataType = "String", paramType="query"),
        @ApiImplicitParam(name = "token", value = "用户token", required = true, dataType = "String", paramType="query")
    })
    @RequestMapping(value = "/getNewsCategoryList", method = RequestMethod.GET)
    @ResponseBody
    @Token
    public Map<String, Object> getNewsInfoType() {
        boolean success = false;
        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> categorylist = null;
        try {
            categorylist = newsCategoryService.getCategoryInfo();
            success = true;
            result.put("error", "");
            result.put("returnInfo", categorylist);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("error", e.getMessage());
            result.put("returnInfo", "");
        }
        result.put("success", success);
        return result;
    }
}
