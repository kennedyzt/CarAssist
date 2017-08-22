package com.ruiyi.carassistant.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ruiyi.carassistant.entity.common.BaseData;

@SuppressWarnings("serial")
@Document
public class TravelInfo extends BaseData {
    @Id
    private String travelInfoId;

    private String customer;

    private Integer userId;

    private String simCode;

    private Float avgSpeed = new Float(0); // 从点火到熄火本行程的平均速度

    private Float driveTime = new Float(0); // 从点火到熄火本行程的驾驶时长（秒）

    private Float distance = new Float(0); // 从点火到熄火本行程的里程数(公里)

    private Float consume = new Float(0); // 油耗

    private Float consumePerMile = new Float(0);

    private Float cost = new Float(0);

    private String trailStr;

    private Float grade;

    private String startLocation;

    private String startLocationDesc;

    private String endLocation;

    private String endLocationDesc;

    private Float topSpeed = new Float(0);

    private String startTime;

    private String endTime;

    private String carState;

    private String realTimeLocation;

    private Integer gplus;

    private Integer reduce;

    public String getRealTimeLocation() {
        return realTimeLocation;
    }

    public void setRealTimeLocation(String realTimeLocation) {
        this.realTimeLocation = realTimeLocation;
    }

    public String getCarState() {
        return carState;
    }

    public void setCarState(String carState) {
        this.carState = carState;
    }

    public String getStartLocationDesc() {
        return startLocationDesc;
    }

    public void setStartLocationDesc(String startLocationDesc) {
        this.startLocationDesc = startLocationDesc;
    }

    public String getEndLocationDesc() {
        return endLocationDesc;
    }

    public void setEndLocationDesc(String endLocationDesc) {
        this.endLocationDesc = endLocationDesc;
    }

    public String getSimCode() {
        return simCode;
    }

    public void setSimCode(String simCode) {
        this.simCode = simCode;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public Float getAvgSpeed() {
        return avgSpeed == null ? new Float(0) : avgSpeed;
    }

    public void setAvgSpeed(Float avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public Float getDriveTime() {
        return driveTime == null ? new Float(0) : driveTime;
    }

    public void setDriveTime(Float driveTime) {
        this.driveTime = driveTime;
    }

    public Float getDistance() {
        return distance == null ? new Float(0) : distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Float getConsume() {
        return consume == null ? new Float(0) : consume;
    }

    public void setConsume(Float consume) {
        this.consume = consume;
    }

    public Float getConsumePerMile() {
        return consumePerMile == null ? new Float(0) : consumePerMile;
    }

    public void setConsumePerMile(Float consumePerMile) {
        this.consumePerMile = consumePerMile;
    }

    public Float getCost() {
        return cost == null ? new Float(0) : cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public String getTrailStr() {
        return trailStr;
    }

    public void setTrailStr(String trailStr) {
        this.trailStr = trailStr;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public Float getTopSpeed() {
        return topSpeed == null ? new Float(0) : topSpeed;
    }

    public void setTopSpeed(Float topSpeed) {
        this.topSpeed = topSpeed;
    }

    /**
     * @return gplus
     */
    public Integer getGplus() {
        return gplus;
    }

    /**
     * @param gplus 要设置的 gplus
     */
    public void setGplus(Integer gplus) {
        this.gplus = gplus;
    }

    /**
     * @return reduce
     */
    public Integer getReduce() {
        return reduce;
    }

    /**
     * @param reduce 要设置的 reduce
     */
    public void setReduce(Integer reduce) {
        this.reduce = reduce;
    }

    /**
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * @return customer
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * @param customer 要设置的 customer
     */
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    /**
     * @return the travelInfoId
     */
    public String getTravelInfoId() {
        return travelInfoId;
    }

    /**
     * @param travelInfoId the travelInfoId to set
     */
    public void setTravelInfoId(String travelInfoId) {
        this.travelInfoId = travelInfoId;
    }

    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
