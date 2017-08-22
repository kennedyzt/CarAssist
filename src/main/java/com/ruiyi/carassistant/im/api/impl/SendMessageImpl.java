package com.ruiyi.carassistant.im.api.impl;

import com.ruiyi.carassistant.im.api.EasemobRestAPI;
import com.ruiyi.carassistant.im.api.SendMessageAPI;
import com.ruiyi.carassistant.im.comm.constant.HTTPMethod;
import com.ruiyi.carassistant.im.comm.helper.HeaderHelper;
import com.ruiyi.carassistant.im.comm.wrapper.BodyWrapper;
import com.ruiyi.carassistant.im.comm.wrapper.HeaderWrapper;

public class SendMessageImpl extends EasemobRestAPI implements SendMessageAPI {
    private static final String ROOT_URI = "/messages";

    @Override
    public String getResourceRootURI() {
        return ROOT_URI;
    }

    public Object sendMessage(Object payload) {
        String  url = getContext().getSeriveURL() + getResourceRootURI();
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();
        BodyWrapper body = (BodyWrapper) payload;

        return getInvoker().sendRequest(HTTPMethod.METHOD_POST, url, header, body, null);
    }
}
