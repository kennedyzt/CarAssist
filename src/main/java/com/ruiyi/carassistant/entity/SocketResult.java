package com.ruiyi.carassistant.entity;

import java.util.HashMap;
import java.util.Map;

public class SocketResult {
    private Boolean success;
    private String method;
    private Map<String, Object> result = new HashMap<String, Object>();

    /**
     * @param success
     * @param method
     * @param result
     */
    public SocketResult(Boolean success, String method, Map<String, Object> result) {
        super();
        this.success = success;
        this.method = method;
        this.result = result;
    }

    /**
     * 
     */
    public SocketResult() {
        super();
    }

    /**
     * @param success
     */
    public SocketResult(Boolean success) {
        super();
        this.success = success;
    }

    /**
     * @return the success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * @return the method
     */
    public String getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * @return the result
     */
    public Map<String, Object> getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

}
