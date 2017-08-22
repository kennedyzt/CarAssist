package com.ruiyi.carassistant.entity;

import com.ruiyi.carassistant.entity.common.BaseData;

public class CarInfo extends BaseData {
    private static final long serialVersionUID = 1L;

    private String simCode;

    private String autoBrand;

    private String autoSubBrand;

    private Integer userID;

    private String carNumber;

    private String vin;

    private String obdAppVersion;

    private String obdVersion;

    /**
     * @param simCode
     * @param userID
     * @param obdAppVersion
     * @param obdVersion
     */
    public CarInfo(String simCode, Integer userID, String obdAppVersion, String obdVersion) {
        super();
        this.simCode = simCode;
        this.userID = userID;
        this.obdAppVersion = obdAppVersion;
        this.obdVersion = obdVersion;
    }

    /**
     * 
     */
    public CarInfo() {
        super();
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    private String brandName;
    private String brandLogo;
    private String seriesName;
    private String carType;

    public String getSimCode() {
        return simCode;
    }

    public void setSimCode(String simCode) {
        this.simCode = simCode;
    }

    public String getAutoBrand() {
        return autoBrand;
    }

    public void setAutoBrand(String autoBrand) {
        this.autoBrand = autoBrand;
    }

    public String getAutoSubBrand() {
        return autoSubBrand;
    }

    public void setAutoSubBrand(String autoSubBrand) {
        this.autoSubBrand = autoSubBrand;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    /**
     * @return the obdAppVersion
     */
    public String getObdAppVersion() {
        return obdAppVersion;
    }

    /**
     * @param obdAppVersion the obdAppVersion to set
     */
    public void setObdAppVersion(String obdAppVersion) {
        this.obdAppVersion = obdAppVersion;
    }

    /**
     * @return the obdVersion
     */
    public String getObdVersion() {
        return obdVersion;
    }

    /**
     * @param obdVersion the obdVersion to set
     */
    public void setObdVersion(String obdVersion) {
        this.obdVersion = obdVersion;
    }

}
