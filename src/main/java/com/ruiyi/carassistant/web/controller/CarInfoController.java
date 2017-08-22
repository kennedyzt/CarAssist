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

import com.ruiyi.carassistant.entity.CarInfo;
import com.ruiyi.carassistant.entity.ResultInfo;
import com.ruiyi.carassistant.entity.common.ResultMsg;
import com.ruiyi.carassistant.service.CarInfoService;
import com.ruiyi.carassistant.web.exception.ErrorException;
import com.ruiyi.carassistant.web.interceptor.Token;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "汽车信息接口")
@Controller
public class CarInfoController {

    @Autowired
    private CarInfoService carInfoService;

    @ApiOperation(value = "绑定盒子_new", notes = "")
    @RequestMapping(value = "/bindBox", method = RequestMethod.GET)
    @ResponseBody
    @Token
    public ResultMsg<Map<String, Object>> bindBox(@RequestParam(value = "userID", required = true) String userID, @RequestParam(value = "SimCode", required = true) String simCode,
                                                  @RequestParam(value = "brandName", required = true) String brandName, @RequestParam(value = "brandLogo", required = true) String brandLogo,
                                                  @RequestParam(value = "seriesName", required = true) String seriesName, @RequestParam(value = "carType", required = true) String carType,
                                                  @RequestParam(value = "carNumber", required = true) String carNumber, @RequestParam(value = "obdAppVersion", required = true) String obdAppVersion,
                                                  @RequestParam(value = "obdVersion", required = true) String obdVersion)
        throws ErrorException {
        CarInfo carInfo = buildCarInfo(userID, simCode, brandName, brandLogo, seriesName, carType, carNumber, obdAppVersion, obdVersion);
        CarInfo oldCarInfo = carInfoService.getCarInfoBySimCode(carInfo.getSimCode());
        if (oldCarInfo == null) {
            carInfoService.insertSelective(carInfo);
        } else if (null == oldCarInfo.getUserID()) {
            carInfoService.update(carInfo);
        } else {
            return ResultMsg.error("obd已绑定");
        }
        return ResultMsg.success();
    }

    /**
     * @param userID
     * @param simCode
     * @param brandName
     * @param brandLogo
     * @param seriesName
     * @param carType
     * @param carNumber
     * @return
     */
    private CarInfo buildCarInfo(String userID, String simCode, String brandName, String brandLogo, String seriesName, String carType, String carNumber, String obdAppVersion, String obdVersion) {
        CarInfo carInfo = new CarInfo();
        carInfo.setUserID(Integer.valueOf(userID));
        carInfo.setSimCode(simCode);
        carInfo.setBrandName(brandName);
        carInfo.setBrandLogo(brandLogo);
        carInfo.setSeriesName(seriesName);
        carInfo.setCarType(carType);
        carInfo.setCarNumber(carNumber);
        carInfo.setObdAppVersion(obdAppVersion);
        carInfo.setObdVersion(obdVersion);
        return carInfo;
    }

    @ApiOperation(value = "解绑盒子", notes = "")
    @ApiImplicitParams({ @ApiImplicitParam(name = "userID", value = "用户ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "token", value = "用户token", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "SimCode", value = "设备识别码", required = true, dataType = "String", paramType = "query") })
    @RequestMapping(value = "/unBindBoxServlet", method = RequestMethod.GET)
    @ResponseBody
    @Token
    public Map<String, Object> unBindBox(@RequestParam(value = "userID", required = true) String userID, @RequestParam(value = "SimCode", required = true) String simCode) throws ErrorException {
        String error = "";
        Boolean success = false;
        Map<String, Object> result = new HashMap<String, Object>();

        Long count = carInfoService.countBySimCode(simCode);
        if (count != null && count > 0) {
            Boolean state = carInfoService.unBindBox(userID, simCode);
            if (state) {
                success = true;
            } else {
                throw new ErrorException("fail to unBind!");
            }
        } else {
            throw new ErrorException("SimCode is not exist!");
        }
        result.put("error", error);
        result.put("success", success);
        result.put("returnInfo", "");
        return result;
    }

    @ApiOperation(value = "改变盒子", notes = "")
    @RequestMapping(value = "/changeBox", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg<Map<String, Object>> changeBox(@RequestParam(value = "userId", required = true) String userId, @RequestParam(value = "simCode", required = true) String simCode)
        throws ErrorException {
        try {
            carInfoService.changeBox(userId, simCode);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMsg.error("服务器异常");
        }
        return ResultMsg.success();
    }

    @ApiOperation(value = "上传汽车基本信息", notes = "")
    @ApiImplicitParams({ @ApiImplicitParam(name = "userID", value = "用户ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "token", value = "用户token", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "autoBrand", value = "车牌号", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "autoSubBrand", value = "车型", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "SimCode", value = "设备识别码", required = true, dataType = "String", paramType = "query") })
    @RequestMapping(value = "/UploadCarInfoServlet", method = RequestMethod.GET)
    @ResponseBody
    @Token
    public Map<String, Object> uploadCarInfo(@RequestParam(value = "autoBrand", required = true) String autoBrand, @RequestParam(value = "autoSubBrand", required = true) String autoSubBrand,
                                             @RequestParam(value = "SimCode", required = true) String simCode)
        throws ErrorException {
        String error = "";
        Boolean success = false;
        Map<String, Object> result = new HashMap<String, Object>();

        Long count = carInfoService.countBySimCode(simCode);
        if (count != null && count > 0) {
            Long num = carInfoService.uploadCarInfo(autoBrand, autoSubBrand, simCode);
            if (num != null && count > 0) {
                success = true;
            } else {
                throw new ErrorException("fail to upload!");
            }
        } else {
            throw new ErrorException("SimCode is not exist!");
        }
        result.put("error", error);
        result.put("success", success);
        result.put("returnInfo", "");
        return result;
    }

    @ApiOperation(value = "获取所有的汽车品牌信息", notes = "")
    @ApiImplicitParams({ @ApiImplicitParam(name = "userID", value = "用户ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "token", value = "用户token", required = true, dataType = "String", paramType = "query"), })
    @RequestMapping(value = "/getAllCarBrand", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getAllCarBrand() {
        ResultInfo resultInfo = new ResultInfo();
        List<Map<String, Object>> brandList = carInfoService.getAllCarBrand();
        if (brandList != null && brandList.size() > 0) {
            resultInfo.setSuccess(true);
            resultInfo.setReturnInfo(brandList);
        }
        return resultInfo;
    }

    @ApiOperation(value = "获取指定品牌所有系列", notes = "")
    @ApiImplicitParams({ @ApiImplicitParam(name = "userID", value = "用户ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "token", value = "用户token", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "brandId", value = "汽车品牌ID", required = true, dataType = "String", paramType = "query") })
    @RequestMapping(value = "/getCarSeriesByBrandId", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getCarSeriesByBrandId(@RequestParam(value = "brandId", required = true) String brandId) {
        ResultInfo resultInfo = new ResultInfo();
        List<Map<String, Object>> seriesList = carInfoService.getCarSeriesByBrandId(brandId);
        if (seriesList != null && seriesList.size() > 0) {
            resultInfo.setSuccess(true);
            resultInfo.setReturnInfo(seriesList);
        }
        return resultInfo;
    }

    @ApiOperation(value = "获取指定汽车品牌的所有车型信息", notes = "")
    @ApiImplicitParams({ @ApiImplicitParam(name = "userID", value = "用户ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "token", value = "用户token", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "seriesId", value = "汽车系列ID", required = true, dataType = "String", paramType = "query") })
    @RequestMapping(value = "/getCarModelsBySeriesId", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getCarModelsBySeriesId(@RequestParam(value = "seriesId", required = true) String seriesId) {
        ResultInfo resultInfo = new ResultInfo();
        List<Map<String, Object>> modelsList = carInfoService.getCarModelsBySeriesId(seriesId);
        if (modelsList != null && modelsList.size() > 0) {
            resultInfo.setSuccess(true);
            resultInfo.setReturnInfo(modelsList);
        }
        return resultInfo;
    }

    @RequestMapping(value = "/getConsumeInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getConsumeInfo(@RequestParam(value = "simCode", required = true) String simCode, @RequestParam(value = "year", required = true) String year) {
        ResultInfo resultInfo = new ResultInfo();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("simCode", simCode);
        paramMap.put("year", year);
        Map<String, Object> modelsList = carInfoService.getConsumeInfo(paramMap);
        if (modelsList != null && modelsList.size() > 0) {
            resultInfo.setSuccess(true);
            resultInfo.setReturnInfo(modelsList);
        }
        return resultInfo;
    }
}
