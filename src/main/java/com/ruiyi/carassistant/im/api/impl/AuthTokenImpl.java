package com.ruiyi.carassistant.im.api.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ruiyi.carassistant.im.api.AuthTokenAPI;
import com.ruiyi.carassistant.im.api.EasemobRestAPI;
import com.ruiyi.carassistant.im.comm.body.AuthTokenBody;
import com.ruiyi.carassistant.im.comm.constant.HTTPMethod;
import com.ruiyi.carassistant.im.comm.helper.HeaderHelper;
import com.ruiyi.carassistant.im.comm.wrapper.BodyWrapper;
import com.ruiyi.carassistant.im.comm.wrapper.HeaderWrapper;

public class AuthTokenImpl extends EasemobRestAPI implements AuthTokenAPI{
	
	public static final String ROOT_URI = "/token";
	
	private static final Logger log = LoggerFactory.getLogger(AuthTokenImpl.class);
	
	@Override
	public String getResourceRootURI() {
		return ROOT_URI;
	}

	public Object getAuthToken(String clientId, String clientSecret) {
		String url = getContext().getSeriveURL() + getResourceRootURI();
		BodyWrapper body = new AuthTokenBody(clientId, clientSecret);
		HeaderWrapper header = HeaderHelper.getDefaultHeader();
		
		return getInvoker().sendRequest(HTTPMethod.METHOD_POST, url, header, body, null);
	}
}
