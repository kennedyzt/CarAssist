package com.ruiyi.carassistant.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ruiyi.carassistant.dao.ServerConfigInfoMapper;
import com.ruiyi.carassistant.entity.CarInfo;
import com.ruiyi.carassistant.entity.common.ResultMsg;
import com.ruiyi.carassistant.service.CarInfoService;
import com.ruiyi.carassistant.service.OtaService;
import com.ruiyi.carassistant.service.VersionInfoService;
import com.ruiyi.carassistant.utils.MD5Util;

@Service
public class OtaServiceImpl implements OtaService {

    @Autowired
    private ServerConfigInfoMapper configInfoMapper;
    @Autowired
    private CarInfoService carInfoService;
    @Autowired
    private VersionInfoService versionInfoService;

    @Override
    public List<Map<String, Object>> findConfigInfo(String[] params) {
        return configInfoMapper.findConfigInfo(params);
    }

    @Override
    public void addOrUpdate(CarInfo carInfo) throws Exception {
        if (null == carInfo.getUserID() && null == carInfoService.getCarInfoBySimCode(carInfo.getSimCode())) {
            carInfoService.add(carInfo);
        } else {
            carInfoService.update(carInfo);
        }
    }

    @Override
    public ResultMsg<Map<String, Object>> updatePackage(String newVersion, MultipartFile file, HttpServletRequest request, String oldVersion) throws Exception {
        // 如果旧版本好与新版本号不同且不为null或""或0,
        Map<String, String> server_config_info = new HashMap<String, String>();
        String[] params = { "linuxOTASHPath", "linuxNewVersion", "linuxOriginalPath", "linuxUpdatePkgPath", "UpdatePkgUrl" };
        List<Map<String, Object>> configInfos = configInfoMapper.findConfigInfo(params);
        for (Map<String, Object> map : configInfos) {
            server_config_info.put("" + map.get("configname"), "" + map.get("configvalue"));
        }
        String linuxOriginalPath = server_config_info.get("linuxOriginalPath"); // 编译包存放根路径
        String linuxNewVersion = server_config_info.get("linuxNewVersion"); // 新版本号
        fileUpload(file, linuxOriginalPath, linuxNewVersion);
        if (oldVersion != null && !"".equals(oldVersion.trim())) {
            if (!oldVersion.equals(newVersion)) {
                versionInfoService.insertOldVersionAndUpdateNewVersion("versionmap_6572", oldVersion, "linuxNewVersion", newVersion);
                System.out.println("获取所有旧版本号以便制作差分包  ...");
                List<String> oldVersionList = versionInfoService.getOldVersion("versionmap_6572");
                System.out.println("oldVersionList:　" + Arrays.toString(oldVersionList.toArray()));
                List<String> updateFileList = new ArrayList<>();
                List<String> updateFileMD5List = new ArrayList<>();
                String linuxOTASHPath = server_config_info.get("linuxOTASHPath"); // .sh
                                                                                  // 路径
                                                                                  // eg.
                                                                                  // /home/tools/buildota.sh
                String linuxUpdatePkgPath = server_config_info.get("linuxUpdatePkgPath"); // eg.
                                                                                          // /opt/apache-tomcat-8.5.5/webapps/OtaUpdatePkg
                for (String oldVer : oldVersionList) {
                    String cmd = linuxOTASHPath + " " + oldVer + " " + linuxNewVersion + " " + linuxUpdatePkgPath + "/" + oldVer + "/update.zip";
                    String shdir = linuxOTASHPath.substring(0, linuxOTASHPath.lastIndexOf("/"));

                    Process process = Runtime.getRuntime().exec(cmd, null, new File(shdir));
                    System.out.println("执行shell命令: " + cmd + "\r\n命令返回：" + process.waitFor());

                    BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                    updateFileList.add("/" + oldVer + "/update.zip");
                    updateFileMD5List.add(MD5Util.fileMD5(linuxUpdatePkgPath + "/" + oldVer + "/update.zip"));
                }

                // 存储差分包下载路径和MD5码
                for (int i = 0; i < updateFileList.size(); i++) {
                    versionInfoService.updateURL("versionmap_6572", updateFileList.get(i), oldVersionList.get(i), updateFileMD5List.get(i));
                }
                return ResultMsg.success();
            } else {
                return ResultMsg.error("版本号与旧版本号相同...");
            }
        } else {
            versionInfoService.updateNewVersion("linuxNewVersion", newVersion);
            return ResultMsg.success();
        }
    }

    private void fileUpload(MultipartFile file, String linuxOriginalPath, String linuxNewVersion) {
        String savePath = linuxOriginalPath + "/" + linuxNewVersion; // 编译包存放路径
        String fileName = file.getOriginalFilename();
        File targetFile = new File(savePath, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
