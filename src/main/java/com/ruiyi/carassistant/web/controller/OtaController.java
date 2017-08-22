package com.ruiyi.carassistant.web.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ruiyi.carassistant.entity.CarInfo;
import com.ruiyi.carassistant.entity.ClientSocketInfo;
import com.ruiyi.carassistant.entity.SocketRequest;
import com.ruiyi.carassistant.entity.VersionMap;
import com.ruiyi.carassistant.entity.common.ResultMsg;
import com.ruiyi.carassistant.service.CarInfoService;
import com.ruiyi.carassistant.service.OtaService;
import com.ruiyi.carassistant.service.ServerConfigInfoService;
import com.ruiyi.carassistant.service.VersionInfoService;
import com.ruiyi.carassistant.utils.LoggerUtil;
import com.ruiyi.carassistant.utils.MD5Util;
import com.ruiyi.carassistant.utils.ParseJson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "升级信息接口")
@Controller
public class OtaController {

    @Autowired
    private OtaService otaService;
    @Autowired
    private VersionInfoService versionInfoService;
    @Autowired
    private ServerConfigInfoService serverConfigInfoService;
    @Autowired
    private CarInfoService carInfoService;

    @ApiIgnore
    @RequestMapping("/OtaUpdate")
    public String index() {
        return "Upload";
    }

    @ApiOperation(value = "版本更新", notes = "")
    @RequestMapping(value = "/ota/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg<Map<String, Object>> uploadPackage(@ApiParam(name = "version", value = "版本号") @RequestParam(value = "version", required = true) String newVersion,
                                                        @ApiParam(name = "file", value = "升级文件") MultipartFile file, HttpServletRequest request)
            throws Exception {
        if (newVersion == null || newVersion.equals("")) {
            return ResultMsg.error("版本号未填写...");
        }
        if (file.getSize() == 0) {
            return ResultMsg.error("文件未选择...");
        }
        // 获取旧版本号
        String oldVersion = serverConfigInfoService.getConfigValueByConfigName("linuxNewVersion");
        return otaService.updatePackage(newVersion, file, request, oldVersion);
    }

    @ApiOperation(value = "版本更新", notes = "")
    @RequestMapping(value = "/OtaFileUploadServlet", method = RequestMethod.POST)
    public String otaFileUpload(@ApiParam(name = "version", value = "版本号") @RequestParam(value = "version", required = true) String newVersion,
                                @ApiParam(name = "file", value = "升级文件") MultipartFile file, HttpServletRequest request)
            throws Exception {

        if (newVersion == null || newVersion.equals("")) {
            request.setAttribute("message", "版本号未填写...");
            return "Message";
        }
        if (file.getSize() == 0) {
            request.setAttribute("message", "文件未选择...");
            return "Message";
        }

        // 获取旧版本号
        String oldVersion = serverConfigInfoService.getConfigValueByConfigName("linuxNewVersion");

        // 如果旧版本好与新版本号不同且不为null或""或0,
        if (oldVersion != null && !"".equals(oldVersion.trim()) && !oldVersion.equalsIgnoreCase("0")) {
            if (!oldVersion.equalsIgnoreCase(newVersion)) {
                Boolean state = versionInfoService.insertOldVersionAndUpdateNewVersion("versionmap_6572", oldVersion, "linuxNewVersion", newVersion);
                if (state) {
                    LoggerUtil.info("version uptate success! " + "oldVersion=" + oldVersion + "; linuxNewVersion=" + newVersion);
                } else {
                    request.setAttribute("message", "版本号更新失败...");
                    return "Message";
                }
            } else {
                request.setAttribute("message", "版本号与旧版本号相同...");
                return "Message";
            }
        } else {
            if (!versionInfoService.updateNewVersion("linuxNewVersion", newVersion)) {
                request.setAttribute("message", "版本号更新失败...");
                return "Message";
            }
        }

        Map<String, String> server_config_info = new HashMap<String, String>();
        String[] params = {"linuxOTASHPath", "linuxNewVersion", "linuxOriginalPath", "linuxUpdatePkgPath", "UpdatePkgUrl"};
        List<Map<String, Object>> configInfos = otaService.findConfigInfo(params);
        for (Map<String, Object> map : configInfos) {
            server_config_info.put("" + map.get("configname"), "" + map.get("configvalue"));
        }

        String linuxOriginalPath = server_config_info.get("linuxOriginalPath"); // 编译包存放根路径
        String linuxNewVersion = server_config_info.get("linuxNewVersion"); // 新版本号
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

        LoggerUtil.info("获取所有旧版本号以便制作差分包  ...");
        List<String> oldVersionList = versionInfoService.getOldVersion("versionmap_6572");
        LoggerUtil.info("oldVersionList:　" + Arrays.toString(oldVersionList.toArray()));
        List<String> updateFileList = new ArrayList<>();
        List<String> updateFileMD5List = new ArrayList<>();
        String linuxOTASHPath = server_config_info.get("linuxOTASHPath"); // .sh
        // 路径
        // eg.
        // /home/tools/buildota.sh
        String updatePkgUrl = server_config_info.get("UpdatePkgUrl"); // eg.
        // http://120.24.152.177:8080/OtaUpdatePkg
        String linuxUpdatePkgPath = server_config_info.get("linuxUpdatePkgPath"); // eg.
        // /opt/apache-tomcat-8.5.5/webapps/OtaUpdatePkg
        for (String oldVer : oldVersionList) {
            String cmd = linuxOTASHPath + " " + oldVer + " " + linuxNewVersion + " " + linuxUpdatePkgPath + "/" + oldVer + "/update.zip";
            String shdir = linuxOTASHPath.substring(0, linuxOTASHPath.lastIndexOf("/"));

            Process process = Runtime.getRuntime().exec(cmd, null, new File(shdir));
            LoggerUtil.info("执行shell命令: " + cmd + "\r\n命令返回：" + process.waitFor());

            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                LoggerUtil.info(line);
            }
            updateFileList.add(updatePkgUrl + "/" + oldVer + "/update.zip");
            updateFileMD5List.add(MD5Util.fileMD5(linuxUpdatePkgPath + "/" + oldVer + "/update.zip"));
        }

        // 存储差分包下载路径和MD5码
        for (int i = 0; i < updateFileList.size(); i++) {
            Long lineNum = versionInfoService.updateURL("versionmap_6572", updateFileList.get(i), oldVersionList.get(i), updateFileMD5List.get(i));
            if (lineNum != null && lineNum > 0) {
                LoggerUtil.info("update success!" + "fileURL=" + updateFileList.get(i) + " fileMD5=" + updateFileMD5List.get(i));
            }
        }

        request.setAttribute("message", "success!");
        return "Message";
    }

    @ApiOperation(value = "请求升级版本")
    @RequestMapping(value = "/updateobd", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg<Map<String, Object>> updateObd(@RequestParam(value = "simCode", required = true) String simCode) {

        CarInfo carInfo = carInfoService.getCarInfoBySimCode(simCode);
        VersionMap versionMap = versionInfoService.getUpdateInfo(carInfo.getObdVersion());
        if (null != versionMap.getFileUrl()) {
            ClientSocketInfo clientSocketInfo = ParseJson.clientSocketMap.get(simCode);
            if (null == clientSocketInfo) {
                return ResultMsg.error("obd不在线");
            }
            try {
                PrintWriter out = new PrintWriter(clientSocketInfo.getSocket().getOutputStream(), true);
                Map<String, Object> socketParams = new HashMap<>();
                socketParams.put("fileUrl", versionMap.getFileUrl());
                socketParams.put("fileMd5", versionMap.getFileMd5());
                SocketRequest socketRequest = new SocketRequest("obd_update", socketParams);
                out.println(socketRequest.getJsonObject());
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ResultMsg.success();
        } else {
            return ResultMsg.error("已是最新版本");
        }
    }

    @ApiOperation(value = "obd上线", notes = "")
    @RequestMapping(value = "/obdonline", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg<Map<String, Object>> register(@RequestParam(value = "simCode", required = true) String simCode, @RequestParam(value = "userId", required = true) Integer userId,
                                                   @RequestParam(value = "obdAppVersion", required = true) String obdAppVersion,
                                                   @RequestParam(value = "obdVersion", required = true) String obdVersion) {
        //
        CarInfo carInfo = new CarInfo(simCode, userId, obdAppVersion, obdVersion);
        try {
            otaService.addOrUpdate(carInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMsg.error();
        }
        return ResultMsg.success();
    }

}
