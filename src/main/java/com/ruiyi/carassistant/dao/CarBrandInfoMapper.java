package com.ruiyi.carassistant.dao;

import com.ruiyi.carassistant.entity.CarBrandInfo;

public interface CarBrandInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarBrandInfo record);

    int insertSelective(CarBrandInfo record);

    CarBrandInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarBrandInfo record);

    int updateByPrimaryKey(CarBrandInfo record);
}