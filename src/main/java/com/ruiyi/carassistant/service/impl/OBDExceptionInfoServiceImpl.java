package com.ruiyi.carassistant.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruiyi.carassistant.service.OBDExceptionInfoService;

@Service
public class OBDExceptionInfoServiceImpl implements OBDExceptionInfoService {

	/*@Autowired
    private OBDExceptionInfoMapper obdExceptionInfoMapper;*/

    @Override
    public void addExceptionInfo(String obdExceInfo) {
        if (obdExceInfo != null && !obdExceInfo.equals("")) {
            JSONObject logInfo = JSON.parseObject(obdExceInfo);
            if (logInfo == null) return;
            String userId = logInfo.getString("UserID");
            String content = logInfo.getString("Content");
            if (userId == null || "".equals(userId) || content == null || "".equals(content)) return;
//	        obdExceptionInfoMapper.addExceptionInfo(JSON.parseObject(obdExceInfo));
            File file = new File("/usr/logs/obd/" + userId + "/" + userId + "-log.txt");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
            try (
                    FileWriter writer = new FileWriter(file, true);
            ) {
                writer.write(content);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
