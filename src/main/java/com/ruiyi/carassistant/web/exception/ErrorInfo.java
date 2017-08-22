package com.ruiyi.carassistant.web.exception;

import java.util.Map;

public class ErrorInfo {
    private Boolean success;
    private String error;
    private Map<String, Object> returnInfo;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Map<String, Object> getReturnInfo() {
        return returnInfo;
    }

    public void setReturnInfo(Map<String, Object> returnInfo) {
        this.returnInfo = returnInfo;
    }

}
