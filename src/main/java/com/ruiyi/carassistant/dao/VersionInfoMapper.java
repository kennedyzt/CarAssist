package com.ruiyi.carassistant.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ruiyi.carassistant.entity.VersionMap;

public interface VersionInfoMapper {
    @Select("select versionMapID ,fileURL,fileMD5 from versionmap_6572 where oldVersion = #{oldVersion}")
    VersionMap getUpdateInfo(Map<String, Object> params);

    @Select("select oldVersion from ${tableName}")
    List<String> getOldVersion(@Param("tableName") String string);

    @Update("update ${tableName} set fileURL=#{fileURL},fileMd5=#{fileMd5} where oldVersion=#{oldVersion}")
    Long updateURL(@Param("tableName") String tableName, @Param("fileURL") String fileURL, @Param("oldVersion") String oldVersion, @Param("fileMd5") String fileMd5);

    @Insert("insert into ${tableName}(oldVersion) values(#{oldVersion})")
    Long insertOldVersion(@Param("tableName") String tableName, @Param("oldVersion") String oldVersion);

}
