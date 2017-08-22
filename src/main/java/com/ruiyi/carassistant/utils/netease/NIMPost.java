package com.ruiyi.carassistant.utils.netease;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Firrela
 * @time 2016/3/7.
 */
public class NIMPost {
	private static String APPKEY = "69778b390e5f3eabe7c077c1eb5a26b6"; // AppKey
	private static String SECRET = "3013bdd6a93f"; // AppSecret

	private static Logger logger = LoggerFactory.getLogger(NIMPost.class);

	public static String postNIMServer(String url, final HttpEntity entity) throws IOException {
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		HttpPost post = httpClientUtil.createPost(url, entity, null);

		// addHeader
		HttpClientUtil.addHeader(post, "AppKey", APPKEY);
		String nonce = UUIDUtil.getUUID();
		String curTime = String.valueOf(System.currentTimeMillis() / 1000);
		HttpClientUtil.addHeader(post, "Nonce", nonce);
		HttpClientUtil.addHeader(post, "CurTime", curTime);
		String checksum = getCheckSum(nonce, curTime, SECRET);
		HttpClientUtil.addHeader(post, "CheckSum", checksum);

		// logger
		logger.info("Nonce {} | CurlTime {} | CheckSum {}", new Object[] { nonce, curTime, checksum });

		return httpClientUtil.fetchData(post);
	}

	private static String getCheckSum(String nonce, String curTime, String appSecret) {
		String plaintext = new StringBuffer(appSecret).append(nonce).append(curTime).toString();
		return EncodeUtil.encode(plaintext, "SHA1");
	}
}
