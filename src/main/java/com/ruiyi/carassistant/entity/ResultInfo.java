package com.ruiyi.carassistant.entity;

/**
 * Created by Lenovo on 2016/12/20.
 */
public class ResultInfo {

    private String error = "";
    private Boolean success = false;
    private Object returnInfo;

    public ResultInfo() {
    }

    public ResultInfo(String error, Boolean success, Object returnInfo) {
        this.error = error;
        this.success = success;
        this.returnInfo = returnInfo;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getReturnInfo() {
        return returnInfo;
    }

    public void setReturnInfo(Object returnInfo) {
        this.returnInfo = returnInfo;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "error='" + error + '\'' +
                ", success=" + success +
                ", returnInfo=" + returnInfo +
                '}';
    }
}
