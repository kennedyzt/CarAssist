package com.ruiyi.carassistant.entity;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class SocketRequest {
    private String method;
    private Map<String, Object> params = new HashMap<>();

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
     * @return the params
     */
    public Map<String, Object> getParams() {
        return params;
    }

    /**
     * @param params the params to set
     */
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    /**
     * @param method
     */
    public SocketRequest(String method) {
        super();
        this.method = method;
    }

    /**
     * @param method
     * @param params
     */
    public SocketRequest(String method, Map<String, Object> params) {
        super();
        this.method = method;
        this.params = params;
    }

    public JSONObject getJsonObject() {
        JSONObject json = new JSONObject();
        json.put("method", method);
        json.put("params", params);
        return json;
    }

}
