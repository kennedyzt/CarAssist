/**
 * @Title: ResultMsg.java
 * @Package com.ruiyi.carassistant.entity.common
 * @Description: TODO(用一句话描述该文件做什么)
 * @author A18ccms A18ccms_gmail_com
 * @date 2017年4月6日 下午1:44:20
 * @version V1.0
 */
package com.ruiyi.carassistant.entity.common;

import java.util.Map;

/**
 * @ClassName: ResultMsg
 * @Description:
 * @author tao.zeng
 * @date 2017年4月6日 下午1:44:20
 */
public class ResultMsg<T> {

    private Boolean success = false;

    private String error = "";

    private String errorCode = "";

    private T returnInfo = null;

    /**
     * @return success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * @param success 要设置的 success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * @return error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error 要设置的 error
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode 要设置的 errorCode
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return returnInfo
     */
    public T getReturnInfo() {
        return returnInfo;
    }

    /**
     * @param returnInfo 要设置的 returnInfo
     */
    public void setReturnInfo(T returnInfo) {
        this.returnInfo = returnInfo;
    }

    /**
     * @Title: success @Description: TODO(这里用一句话描述这个方法的作用) @param @return
     *         设定文件 @return Map<String,Object> 返回类型 @throws
     */
    public static ResultMsg<Map<String, Object>> success() {
        ResultMsg<Map<String, Object>> success = new ResultMsg<>();
        success.success = true;
        return success;
    }

    public static ResultMsg<Map<String, Object>> success(Map<String, Object> returnInfo) {
        ResultMsg<Map<String, Object>> success = new ResultMsg<>();
        success.success = true;
        success.returnInfo = returnInfo;
        return success;
    }

    /**
     * @Title: error @Description: TODO(这里用一句话描述这个方法的作用) @param @param
     *         string @param @return 设定文件 @return ResultMsg<Map<String,Object>>
     *         返回类型 @throws
     */
    public static ResultMsg<Map<String, Object>> error(String errorMsg) {
        ResultMsg<Map<String, Object>> error = new ResultMsg<>();
        error.success = false;
        error.error = errorMsg;
        return error;
    }

    /**
     * @Title: error @Description: TODO(这里用一句话描述这个方法的作用) @param @param
     *         string @param @param string2 @param @return 设定文件 @return
     *         ResultMsg<Map<String,Object>> 返回类型 @throws
     */
    public static ResultMsg<Map<String, Object>> error(String errorMsg, String errorCode) {
        ResultMsg<Map<String, Object>> error = new ResultMsg<>();
        error.success = false;
        error.error = errorMsg;
        error.errorCode = errorCode;
        return error;
    }

    public static ResultMsg<Map<String, Object>> error(Map<String, Object> resultMap) {
        ResultMsg<Map<String, Object>> error = new ResultMsg<>();
        error.success = false;
        error.returnInfo = resultMap;
        return error;
    }

    public static <T> ResultMsg<T> error() {
        ResultMsg<T> error = new ResultMsg<>();
        error.success = false;
        error.error = "服务器异常";
        return error;
    }

}
