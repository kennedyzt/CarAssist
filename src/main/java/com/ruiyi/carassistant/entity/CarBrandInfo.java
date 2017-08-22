package com.ruiyi.carassistant.entity;

public class CarBrandInfo {
    private Integer id;

    private String firstLetter;

    private String brandName;

    private String brandId;

    private String brandLogo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstletter() {
        return firstLetter;
    }

    public void setFirstletter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public String getBrandname() {
        return brandName;
    }

    public void setBrandname(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }
}