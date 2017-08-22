package com.ruiyi.carassistant.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ruiyi.carassistant.entity.ServerConfigInfo;

public interface ServerConfigInfoMapper {
    int deleteByPrimaryKey(String configname);

    int insert(ServerConfigInfo record);

    int insertSelective(ServerConfigInfo record);

    ServerConfigInfo selectByPrimaryKey(String configname);

    int updateByPrimaryKeySelective(ServerConfigInfo record);

    int updateByPrimaryKey(ServerConfigInfo record);

    List<Map<String, Object>> findConfigInfo(String[] params);

    @Select("select configvalue from server_config_info where configname = #{configName}")
    String getConfigValueByConfigName(@Param("configName")String configName);

    @Update("update server_config_info set configvalue=#{newVersion} where configname=#{configName}")
    Long updateNewVersion(@Param("configName")String configName, @Param("newVersion")String newVersion);

    @Select("select configname from server_config_info where configvalue = #{configValue}")
    String getConfigNameByConfigValue(@Param("configValue")String configValue);
}