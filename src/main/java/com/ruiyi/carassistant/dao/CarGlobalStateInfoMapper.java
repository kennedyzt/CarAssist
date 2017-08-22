package com.ruiyi.carassistant.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ruiyi.carassistant.entity.CarGlobalStateInfo;

public interface CarGlobalStateInfoMapper {
    int deleteByPrimaryKey(Integer simcode);

    int insert(CarGlobalStateInfo record);

    int insertSelective(CarGlobalStateInfo record);

    CarGlobalStateInfo selectByPrimaryKey(Integer simcode);

    int updateByPrimaryKeySelective(CarGlobalStateInfo record);

    int updateByPrimaryKey(CarGlobalStateInfo record);

    @Select("select c.allDriveDistance, u.userName, u.userHeadUrl from carglobalstateinfo c INNER JOIN userinfo u ON c.userID = u.userID order by c.allDriveDistance desc")
    List<Map<String, Object>> getRankListByDistance();

    @Select("select * from carglobalstateinfo where SimCode = #{simCode} and userID = #{userID}")
    Map<String, Object> getGlobalInfoBysimCodeAnduserID(@Param("simCode")String simCode, @Param("userID")String userID);

    @Select("select count(SimCode) from carglobalstateinfo where SimCode = #{simCode}")
    Long selectCountBySimCode(@Param("simCode")String simCode);
}