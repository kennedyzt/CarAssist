package com.ruiyi.carassistant.entity;

public class CarGlobalStateInfo {
    private String simCode;

    private Integer userID;

    private Float allDriveTime;

    private Float allDriveDistance;

    private Float allFuelConsume;

    private Float allCost;

    private String otherInfo;

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

    public Float getAllDriveTime() {
        return allDriveTime;
    }

    public void setAllDriveTime(Float allDriveTime) {
        this.allDriveTime = allDriveTime;
    }

    public Float getAllDriveDistance() {
        return allDriveDistance;
    }

    public void setAllDriveDistance(Float allDriveDistance) {
        this.allDriveDistance = allDriveDistance;
    }

    public Float getAllFuelConsume() {
        return allFuelConsume;
    }

    public void setAllFuelConsume(Float allFuelConsume) {
        this.allFuelConsume = allFuelConsume;
    }

    public Float getAllCost() {
        return allCost;
    }

    public void setAllCost(Float allCost) {
        this.allCost = allCost;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }
}