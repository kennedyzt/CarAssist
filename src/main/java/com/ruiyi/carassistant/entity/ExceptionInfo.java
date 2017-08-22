package com.ruiyi.carassistant.entity;

import java.util.Date;

public class ExceptionInfo {
    private Integer exceptionInfoID;

    private String simCode;

    private Integer userID;

    private Short exceptionType;

    private String exceptionValue;

    private Date exceptionTime;

    private String otherInfo;

    public Integer getExceptionInfoID() {
        return exceptionInfoID;
    }

    public void setExceptionInfoID(Integer exceptionInfoID) {
        this.exceptionInfoID = exceptionInfoID;
    }

    public String getSimCode() {
        return simCode;
    }

    public void setSimCode(String simCode) {
        this.simCode = simCode;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Short getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(Short exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getExceptionValue() {
        return exceptionValue;
    }

    public void setExceptionValue(String exceptionValue) {
        this.exceptionValue = exceptionValue;
    }

    public Date getExceptionTime() {
        return exceptionTime;
    }

    public void setExceptionTime(Date exceptionTime) {
        this.exceptionTime = exceptionTime;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }
}