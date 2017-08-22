package com.ruiyi.carassistant.entity;

import java.util.Date;

public class CarRealTimeInfo {
    private Integer id;

    private String simCode;

    private String fireState;

    private Date curTime;

    private Integer userID;

    private Float voltage;

    private Integer RMP;

    private Float warterTemp;

    private Integer speed;

    private Float driveTime;

    private Float driveDistance;

    private Float curConsume;

    private Float accConsume;

    private Float oilLeft;

    private Integer driveDuration;

    private Float engine_load;

    private Integer avgSpeed;

    private Float curTripDistance;

    private Float longitude;

    private Float latitude;

    private Date createtime;

    private Date updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSimCode() {
        return simCode;
    }

    public void setSimCode(String simCode) {
        this.simCode = simCode;
    }

    public String getFireState() {
        return fireState;
    }

    public void setFireState(String fireState) {
        this.fireState = fireState;
    }

    public Date getCurTime() {
        return curTime;
    }

    public void setCurTime(Date curTime) {
        this.curTime = curTime;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Float getVoltage() {
        return voltage;
    }

    public void setVoltage(Float voltage) {
        this.voltage = voltage;
    }

    public Integer getRMP() {
        return RMP;
    }

    public void setRMP(Integer RMP) {
        this.RMP = RMP;
    }

    public Float getWarterTemp() {
        return warterTemp;
    }

    public void setWarterTemp(Float warterTemp) {
        this.warterTemp = warterTemp;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Float getDriveTime() {
        return driveTime;
    }

    public void setDriveTime(Float driveTime) {
        this.driveTime = driveTime;
    }

    public Float getDriveDistance() {
        return driveDistance;
    }

    public void setDriveDistance(Float driveDistance) {
        this.driveDistance = driveDistance;
    }

    public Float getCurConsume() {
        return curConsume;
    }

    public void setCurConsume(Float curConsume) {
        this.curConsume = curConsume;
    }

    public Float getAccConsume() {
        return accConsume;
    }

    public void setAccConsume(Float accConsume) {
        this.accConsume = accConsume;
    }

    public Float getOilLeft() {
        return oilLeft;
    }

    public void setOilLeft(Float oilLeft) {
        this.oilLeft = oilLeft;
    }

    public Integer getDriveDuration() {
        return driveDuration;
    }

    public void setDriveDuration(Integer driveDuration) {
        this.driveDuration = driveDuration;
    }

    public Float getEngine_load() {
        return engine_load;
    }

    public void setEngine_load(Float engine_load) {
        this.engine_load = engine_load;
    }

    public Integer getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(Integer avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public Float getCurTripDistance() {
        return curTripDistance;
    }

    public void setCurTripDistance(Float curTripDistance) {
        this.curTripDistance = curTripDistance;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}