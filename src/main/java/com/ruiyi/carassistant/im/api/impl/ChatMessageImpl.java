package com.ruiyi.carassistant.im.api.impl;

import com.ruiyi.carassistant.im.api.ChatMessageAPI;
import com.ruiyi.carassistant.im.api.EasemobRestAPI;
import com.ruiyi.carassistant.im.comm.constant.HTTPMethod;
import com.ruiyi.carassistant.im.comm.helper.HeaderHelper;
import com.ruiyi.carassistant.im.comm.wrapper.HeaderWrapper;
import com.ruiyi.carassistant.im.comm.wrapper.QueryWrapper;

public class ChatMessageImpl extends EasemobRestAPI implements ChatMessageAPI {

    private static final String ROOT_URI = "chatmessages";

    public Object exportChatMessages(Long limit, String cursor, String query) {
        String url = getContext().getSeriveURL() + getResourceRootURI();
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();
        QueryWrapper queryWrapper = QueryWrapper.newInstance().addLimit(limit).addCursor(cursor).addQueryLang(query);

        return getInvoker().sendRequest(HTTPMethod.METHOD_DELETE, url, header, null, queryWrapper);
    }

    @Override
    public String getResourceRootURI() {
        return ROOT_URI;
    }
}
