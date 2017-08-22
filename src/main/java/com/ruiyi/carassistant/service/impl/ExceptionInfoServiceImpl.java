package com.ruiyi.carassistant.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruiyi.carassistant.dao.ExceptionInfoMapper;
import com.ruiyi.carassistant.entity.ExceptionInfo;
import com.ruiyi.carassistant.service.ExceptionInfoService;

@Service
public class ExceptionInfoServiceImpl implements ExceptionInfoService {

    @Autowired
    private ExceptionInfoMapper exceptionInfoMapper;

    @Override
    public void addExceptionInfo(String packExceInfo) {
        JSONArray jsonArray = JSONArray.parseArray(packExceInfo);
        JSONObject json = null;
        for (int i = 0; i < jsonArray.size(); i++) {

            ExceptionInfo exceptionInfo = new ExceptionInfo();
            json = jsonArray.getJSONObject(i);
            exceptionInfo.setSimCode(json.getString("SimCode"));
            exceptionInfo.setExceptionType((short) json.getIntValue("ExceptionType"));
            String curTime = json.getString("ExceptionTime");
            if (curTime != null && 0 != curTime.compareTo("")) {
                Timestamp date = Timestamp.valueOf(curTime);
                exceptionInfo.setExceptionTime(date);
            }
            exceptionInfo.setUserID(json.getIntValue("userId"));
            exceptionInfo.setExceptionValue(json.getString("ExceptionValue"));
            exceptionInfo.setOtherInfo(json.getString("otherInfo"));
            Integer exceptionInfoID = exceptionInfoMapper.selectCountByUserIDAndExceptionType(exceptionInfo);
            if (exceptionInfoID != null && exceptionInfoID > 0) {
                exceptionInfo.setSimCode(null);
                exceptionInfo.setUserID(null);
                exceptionInfo.setExceptionType(null);
                exceptionInfo.setExceptionInfoID(exceptionInfoID);
                exceptionInfoMapper.updateByPrimaryKeySelective(exceptionInfo); // only
                                                                                // update
                                                                                // 'ExceptionValue'
                                                                                // and
                                                                                // 'ExceptionTime'.
            } else {
                exceptionInfoMapper.insert(exceptionInfo);
            }
        }
    }
}
