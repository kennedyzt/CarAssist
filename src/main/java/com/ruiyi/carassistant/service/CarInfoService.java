package com.ruiyi.carassistant.service;

import java.util.List;
import java.util.Map;

import com.ruiyi.carassistant.entity.CarInfo;

public interface CarInfoService {

    String getSimCodeByUserID(Map<String, Object> params);

    Long countBySimCode(String simCode);

    Long uploadCarInfo(String autoBrand, String autoSubBrand, String simCode);

    Boolean unBindBox(String userID, String simCode);

    List<Map<String, Object>> getAllCarBrand();

    List<Map<String, Object>> getCarSeriesByBrandId(String brandId);

    List<Map<String, Object>> getCarModelsBySeriesId(String seriesId);

    Boolean bindBox(String userID, String simCode, String brandName, String brandLogo, String seriesName, String carType, String carNumber);

    List<Map<String, Object>> getCarInfoListByUserID(Map<String, Object> userInfo);

    /**
     * @Title: getConsumeInfo @Description: TODO(这里用一句话描述这个方法的作用) @param @param
     *         paramMap @param @return 设定文件 @return Map<String,Object>
     *         返回类型 @throws
     */
    Map<String, Object> getConsumeInfo(Map<String, Object> paramMap);

    Boolean changeBox(String userID, String simCode) throws Exception;

    void add(CarInfo carInfo);

    void update(CarInfo carInfo);

    CarInfo getCarInfoBySimCode(String simCode);

    void insertSelective(CarInfo carInfo);
}
