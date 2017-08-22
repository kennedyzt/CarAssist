package com.ruiyi.carassistant.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.ruiyi.carassistant.entity.CarInfo;
import com.ruiyi.carassistant.entity.common.ResultMsg;

public interface OtaService {

    List<Map<String, Object>> findConfigInfo(String[] params);

    void addOrUpdate(CarInfo carInfo) throws Exception;

    ResultMsg<Map<String, Object>> updatePackage(String newVersion, MultipartFile file, HttpServletRequest request, String oldVersion) throws Exception;

}
