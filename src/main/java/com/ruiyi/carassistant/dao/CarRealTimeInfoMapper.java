package com.ruiyi.carassistant.dao;

import com.ruiyi.carassistant.entity.CarRealTimeInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CarRealTimeInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarRealTimeInfo record);

    int insertSelective(CarRealTimeInfo record);

    CarRealTimeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarRealTimeInfo record);

    int updateByPrimaryKey(CarRealTimeInfo record);

    CarRealTimeInfo getCarRealTimeInfoByUserIDAndSimCode(@Param("userID") String userID, @Param("simCode") String simCode);
}