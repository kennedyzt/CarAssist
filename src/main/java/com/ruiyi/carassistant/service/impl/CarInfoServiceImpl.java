package com.ruiyi.carassistant.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruiyi.carassistant.dao.CarInfoMapper;
import com.ruiyi.carassistant.entity.CarGlobalStateInfo;
import com.ruiyi.carassistant.entity.CarInfo;
import com.ruiyi.carassistant.service.CarInfoService;

@Service
public class CarInfoServiceImpl implements CarInfoService {

    @Autowired
    private CarInfoMapper carInfoMapper;

    @Override
    public String getSimCodeByUserID(Map<String, Object> params) {
        return carInfoMapper.getSimCodeByUserID(params);
    }

    @Override
    public List<Map<String, Object>> getCarInfoListByUserID(Map<String, Object> userInfo) {
        return carInfoMapper.getCarInfoListByUserID(userInfo);
    }

    @Override
    public Long countBySimCode(String simCode) {
        return carInfoMapper.countBySimCode(simCode);
    }

    @Override
    public Boolean changeBox(String userId, String simCode) throws Exception {
        carInfoMapper.changeBoxInUse(simCode, userId);
        carInfoMapper.changeBoxNotInUse(simCode, userId);
        return true;
    }

    @Override
    public Boolean bindBox(String userID, String simCode, String brandName, String brandLogo, String seriesName, String carType, String carNumber) {
        Boolean success = false;
        CarInfo carInfo = new CarInfo();
        carInfo.setUserID(Integer.valueOf(userID));
        carInfo.setSimCode(simCode);
        carInfo.setBrandName(brandName);
        carInfo.setBrandLogo(brandLogo);
        carInfo.setSeriesName(seriesName);
        carInfo.setCarType(carType);
        carInfo.setCarNumber(carNumber);
        if (null == carInfoMapper.getCarInfoBySimCode(carInfo.getSimCode())) {
            carInfoMapper.insertSelective(carInfo);
        } else {
            carInfoMapper.updateByPrimaryKeySelective(carInfo);
        }
        return success;
    }

    @Override
    public Long uploadCarInfo(String autoBrand, String autoSubBrand, String simCode) {
        return carInfoMapper.uploadCarInfo(autoBrand, autoSubBrand, simCode);
    }

    @Override
    public Boolean unBindBox(String userID, String simCode) {
        Boolean success = false;
        int line = carInfoMapper.deleteByUserIDAndSimCode(userID, simCode);
        if (line > 0) {
            success = true;
        }
        return success;
    }

    @Override
    public List<Map<String, Object>> getAllCarBrand() {
        return carInfoMapper.getAllCarBrand();
    }

    @Override
    public List<Map<String, Object>> getCarSeriesByBrandId(String brandId) {
        return carInfoMapper.getCarSeriesByBrandId(brandId);
    }

    @Override
    public List<Map<String, Object>> getCarModelsBySeriesId(String seriesId) {
        return carInfoMapper.getCarModelsBySeriesId(seriesId);
    }

    @Override
    public Map<String, Object> getConsumeInfo(Map<String, Object> paramMap) {
        Map<String, Object> result = carInfoMapper.getConsumeInfo(paramMap);
        List<Map<String, Object>> list = carInfoMapper.getConsumeByMonth(paramMap); // 201703
        String[] monthArray = new String[] { "january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december" };
        Map<String, Object> monthMap = new HashMap<String, Object>();
        for (int i = 0; i < 12; i++) {
            monthMap.put(monthArray[i], 0);
        }
        for (int j = 0; j < list.size(); j++) {
            Map<String, Object> map = list.get(j);
            monthMap.put(monthArray[Integer.valueOf(map.get("month").toString()) - 1], map.get("monthConsume"));
        }
        result.putAll(monthMap);
        return result;
    }

    @Override
    public void add(CarInfo carInfo) {
        carInfoMapper.insert(carInfo);
    }

    @Override
    public void update(CarInfo carInfo) {
        carInfoMapper.updateByPrimaryKeySelective(carInfo);
    }

    @Override
    public CarInfo getCarInfoBySimCode(String simCode) {
        return carInfoMapper.getCarInfoBySimCode(simCode);
    }

    @Override
    public void insertSelective(CarInfo carInfo) {
        carInfoMapper.insertSelective(carInfo);
    }

}
