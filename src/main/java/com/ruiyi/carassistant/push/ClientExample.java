package com.ruiyi.carassistant.push;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Custom Client
 * 
 * @author Yipd
 * @date 2016-10-25 上午10:21:39
 */
public class ClientExample {
	protected static final Logger LOG = LoggerFactory.getLogger(ClientExample.class);

	private static final String appKey = "dd1066407b044738b6479275";
	private static final String masterSecret = "e8cc9a76d5b7a580859bcfa7";

	public static void main(String[] args) {
//		testDefaultClient();
//		testCustomClient();
//		testCustomPushClient();
	}

	public static void testDefaultClient() {
		JPushClient client = new JPushClient(masterSecret, appKey);

	//	JPushClient client1 = new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());
	}

	/**
	 * 配置的SSLVersion表示指定至少支持的协议版本，也可能支持其他多个协议版本，最终支持的协议版本列表取决于JRE和运行环境
	 * 
	 * @author Yipd
	 * @date 2016-10-25 上午10:22:27
	 */
	public static void testCustomClient() {
		ClientConfig config = ClientConfig.getInstance();
		config.setMaxRetryTimes(5);
		config.setConnectionTimeout(10 * 1000);	// 10 seconds
		config.setSSLVersion("TLSv1.1");		// JPush server supports SSLv3, TLSv1, TLSv1.1, TLSv1.2

		JPushClient jPushClient = new JPushClient(masterSecret, appKey, null, config);
	}

	public static void testCustomPushClient() {
		ClientConfig config = ClientConfig.getInstance();

		config.setApnsProduction(false); 	// development env
		config.setTimeToLive(60 * 60 * 24); // one day

	//	config.setGlobalPushSetting(false, 60 * 60 * 24); // development env, one day

		JPushClient jPushClient = new JPushClient(masterSecret, appKey, null, config); 	// JPush client

	//	PushClient pushClient = new PushClient(masterSecret, appKey, null, config); 	// push client only

	}
	
}


