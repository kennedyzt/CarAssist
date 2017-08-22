package com.ruiyi.carassistant.sms;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;

/**
 * Created by Lenovo on 2016/12/26.
 */
public class SampleSMS {
    public static void main(String[] args) throws ClientException {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIZWJOHd1Eongr", "Zotlxmu6OJOki7eahS3gJeQbTtwjoL");
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Sms",  "sms.aliyuncs.com");
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendSmsRequest request = new SingleSendSmsRequest();
        try {
            request.setSignName("控制台创建的签名名称");
            request.setTemplateCode("控制台创建的模板CODE");
            request.setParamString("{}");
            request.setRecNum("15528801837");
            SingleSendSmsResponse httpResponse = client.getAcsResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
        }
        catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
