package com.ruiyi.carassistant.entity;

import java.util.Date;

public class VersionMap {
    private Integer versionMapID;

    private String oldVersion;

    private String newVersion;

    private String fileUrl;

    private String fileMd5;

    private String otherInfo;

    private Date createDate;

    private Date updateDate;

    /**
     * @return the versionMapID
     */
    public Integer getVersionMapID() {
        return versionMapID;
    }

    /**
     * @param versionMapID the versionMapID to set
     */
    public void setVersionMapID(Integer versionMapID) {
        this.versionMapID = versionMapID;
    }

    /**
     * @return the oldVersion
     */
    public String getOldVersion() {
        return oldVersion;
    }

    /**
     * @param oldVersion the oldVersion to set
     */
    public void setOldVersion(String oldVersion) {
        this.oldVersion = oldVersion;
    }

    /**
     * @return the newVersion
     */
    public String getNewVersion() {
        return newVersion;
    }

    /**
     * @param newVersion the newVersion to set
     */
    public void setNewVersion(String newVersion) {
        this.newVersion = newVersion;
    }

    /**
     * @return the fileUrl
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * @param fileUrl the fileUrl to set
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    /**
     * @return the fileMd5
     */
    public String getFileMd5() {
        return fileMd5;
    }

    /**
     * @param fileMd5 the fileMd5 to set
     */
    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    /**
     * @return the otherInfo
     */
    public String getOtherInfo() {
        return otherInfo;
    }

    /**
     * @param otherInfo the otherInfo to set
     */
    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the updateDate
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate the updateDate to set
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

}
