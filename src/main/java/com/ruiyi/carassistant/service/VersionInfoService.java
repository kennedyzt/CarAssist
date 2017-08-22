package com.ruiyi.carassistant.service;

import java.util.List;
import java.util.Map;

import com.ruiyi.carassistant.entity.VersionMap;

public interface VersionInfoService {

    List<String> getOldVersion(String string);

    Long updateURL(String tableName, String fileURL, String oldVersion, String fileMd5);

    Long insertOldVersion(String tableName, String oldVersion);

    Boolean insertOldVersionAndUpdateNewVersion(String tableName, String oldVersion, String configName, String newVersion);

    Boolean updateNewVersion(String configName, String newVersion);

    /**
     * 获取升级信息
     * @param oldV_6572
     * @param oldV_OBD
     * @param oldV_android
     * @return
     * @author Yipd
     * @throws Exception
     * @date 2016-11-3 下午2:51:43
     */
    Map<String, Object> getUpdateInfo(String oldV_6572, String oldV_OBD, String oldV_android) throws Exception;

    VersionMap getUpdateInfo(String obdVersion);

}
