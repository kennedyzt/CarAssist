package com.ruiyi.carassistant.geocode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruiyi.carassistant.utils.OkHttpUtil;

import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 2016/12/15.
 */
public class GeocodeUtil {

    public static void main(String[] args) throws IOException, InterruptedException {
        /*OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(new LoggingInterceptor()).build();
        //client.interceptors().add(new LoggingInterceptor()); //添加应用拦截器
        //client.networkInterceptors().add(new LoggingInterceptor()); //添加网络拦截器

        Request request = new Request.Builder()
                .url("http://restapi.amap.com/v3/geocode/regeo?parameters")
                .build();

        client.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("服务器端错误: " + response);
                }
                System.out.println(response.body().string());
            }
        });*/
        /*Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("服务器端错误: " + response);
        }

        System.out.println(response.body().string());*/
        Map<String, String> params = new HashMap<String, String>();
        params.put("key", "29a600ef852fe015f67f99141d425606");
        params.put("location", "104.0395503,30.6027033|103.0395503,30.6027033");
        params.put("batch", "true");
        OkHttpUtil.get("http://restapi.amap.com/v3/geocode/regeo", params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                /*for (Object obj : JSON.parseObject(response.body().string()).getJSONArray("regeocodes")) {
                    if (obj instanceof JSONObject) {
                        System.out.println(((JSONObject) obj).getString("formatted_address"));
                    }
                }*/
                JSONArray regeocodes = JSON.parseObject(response.body().string()).getJSONArray("regeocodes");
                String formatted_address = regeocodes.getJSONObject(1).getString("formatted_address");
                System.out.print(formatted_address);
                //System.out.println(response.body().string());
            }
        });
    }
}
