package com.ruiyi.carassistant.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ruiyi.carassistant.entity.Obderror;

public interface ObdErrorMapper {
    int deleteByPrimaryKey(String obdcode);

    int insert(Obderror record);

    int insertSelective(Obderror record);

    Obderror selectByPrimaryKey(String obdcode);

    int updateByPrimaryKeySelective(Obderror record);

    int updateByPrimaryKey(Obderror record);

    @Select("select * from obd_error_desc where obderror_name = #{code}")
    List<Map<String, Object>> getErrorByCode(@Param("code") String code);
}