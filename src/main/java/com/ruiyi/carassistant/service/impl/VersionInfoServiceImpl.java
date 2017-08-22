package com.ruiyi.carassistant.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruiyi.carassistant.dao.ServerConfigInfoMapper;
import com.ruiyi.carassistant.dao.VersionInfoMapper;
import com.ruiyi.carassistant.entity.VersionMap;
import com.ruiyi.carassistant.service.VersionInfoService;

@Service
public class VersionInfoServiceImpl implements VersionInfoService {

    @Autowired
    private VersionInfoMapper versionInfoMapper;
    @Autowired
    private ServerConfigInfoMapper serverConfigInfoMapper;

    @Override
    public List<String> getOldVersion(String string) {
        return versionInfoMapper.getOldVersion(string);
    }

    @Override
    public Long updateURL(String tableName, String fileURL, String oldVersion, String fileMd5) {
        return versionInfoMapper.updateURL(tableName, fileURL, oldVersion, fileMd5);
    }

    @Override
    public Long insertOldVersion(String tableName, String oldVersion) {
        return versionInfoMapper.insertOldVersion(tableName, oldVersion);
    }

    @Override
    public Boolean insertOldVersionAndUpdateNewVersion(String tableName, String oldVersion, String configName, String newVersion) {
        Long num1 = versionInfoMapper.insertOldVersion(tableName, oldVersion);
        Long num2 = serverConfigInfoMapper.updateNewVersion(configName, newVersion);
        if (num1 != null && num1 > 0 && num2 != null && num2 > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateNewVersion(String configName, String newVersion) {
        Long num = serverConfigInfoMapper.updateNewVersion(configName, newVersion);
        if (num != null && num > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Map<String, Object> getUpdateInfo(String oldV_6572, String oldV_OBD, String oldV_android) throws Exception {
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> returnInfo = new HashMap<>();
        Map<String, Object> fileMD5 = new HashMap<>();
        if (oldV_6572 != null && !oldV_6572.trim().isEmpty()) {
            params.put("tableName", "versionmap_6572");
            params.put("oldVersion", oldV_6572);
            returnInfo.put("url_6572", "");
            fileMD5.put("fileMD5_6572", "");
        } else if (oldV_OBD != null && !oldV_OBD.trim().isEmpty()) {
            params.put("tableName", "versionmap_OBD");
            params.put("oldVersion", oldV_OBD);
            returnInfo.put("url_OBD", "");
            fileMD5.put("fileMD5_OBD", "");
        } else if (oldV_android != null && !oldV_android.trim().isEmpty()) {
            params.put("tableName", "versionmap_Android");
            params.put("oldVersion", oldV_android);
            returnInfo.put("url_android", "");
            fileMD5.put("fileMD5_android", "");
        } else {
            throw new Exception("parameters error!");
        }
        VersionMap retMap = versionInfoMapper.getUpdateInfo(params);
        if (retMap == null) {
            throw new Exception("version is not exist!");
        }
        for (String key : returnInfo.keySet()) {
            returnInfo.put(key, retMap.getFileUrl());
        }
        for (String key : fileMD5.keySet()) {
            returnInfo.put(key, retMap.getFileMd5());
        }
        return returnInfo;
    }

    @Override
    public VersionMap getUpdateInfo(String obdVersion) {
        Map<String, Object> params = new HashMap<>();
        params.put("oldVersion", obdVersion);
        return versionInfoMapper.getUpdateInfo(params);
    }

}
