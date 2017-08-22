package com.ruiyi.carassistant.service;



public interface OBDExceptionInfoService {
    
    /**
     * 添加异常信息, 表: (obd_exception_info).
     * @param packExceInfo
     * @author Yipd
     * @date 2016-11-10 下午2:22:51
     */
    void addExceptionInfo(String packExceInfo);
}
