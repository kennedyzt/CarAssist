package com.ruiyi.carassistant.im.api;

import java.io.File;

import com.ruiyi.carassistant.im.comm.wrapper.BodyWrapper;
import com.ruiyi.carassistant.im.comm.wrapper.HeaderWrapper;
import com.ruiyi.carassistant.im.comm.wrapper.QueryWrapper;
import com.ruiyi.carassistant.im.comm.wrapper.ResponseWrapper;

public interface RestAPIInvoker {
	ResponseWrapper sendRequest(String method, String url, HeaderWrapper header, BodyWrapper body, QueryWrapper query);
	ResponseWrapper uploadFile(String url, HeaderWrapper header, File file);
    ResponseWrapper downloadFile(String url, HeaderWrapper header, QueryWrapper query);
}
