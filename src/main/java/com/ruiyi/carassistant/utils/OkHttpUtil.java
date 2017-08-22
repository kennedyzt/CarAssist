package com.ruiyi.carassistant.utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import okhttp3.*;
import okhttp3.FormBody.Builder;

/**
 * OKHttp请求工具类
 *
 * @author xujunhe
 *
 */
public class OkHttpUtil {

    public static Interceptor LOGGING_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            /*System.out.println(String.format("发送请求: [%s] %s%n%s",
                    request.url(), chain.connection(), request.headers()));*/
            /*System.out.println(String.format("%s-URL: %s %n",chain.request().method(),
                    chain.request().url()));*/
            System.out.println(String.format("Sending request: %s [%s] %n",
                    request.method(), request.url()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            System.out.println(String.format("Received response for [%s] in %.1fms%n",
                    response.request().url(), (t2 - t1) / 1e6d));

            return response;
        }
    };

    private static final OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(LOGGING_INTERCEPTOR).build();

    /**
     * get请求
     *
     * @param url
     *            请求地址
     * @param params
     *            请求参数
     * @return 返回结果
     * @throws IOException
     */
    public static Response get(String url, Map<String, String> params)
            throws IOException {
        return createGetCall(url, params).execute();
    }

    /**
     * get异步请求
     *
     * @param url
     *            请求地址
     * @param params
     *            请求参数
     * @return 返回结果
     * @throws IOException
     */
    public static void get(String url, Map<String, String> params,
                           Callback callback) {
        createGetCall(url, params).enqueue(callback);
    }

    /**
     * post同步请求
     *
     * @param url
     *            请求地址
     * @param params
     *            请求参数
     * @return 返回结果
     * @throws IOException
     */
    public static Response post(String url, Map<String, String> params)
            throws IOException {
        return createPostCall(url, params).execute();
    }

    /**
     * post异步请求
     *
     * @param url
     *            请求地址
     * @param params
     *            请求参数
     * @param callback
     *            返回结果
     */
    public static void post(String url, Map<String, String> params,
                            Callback callback) {
        createPostCall(url, params).enqueue(callback);
    }

    /**
     * post同步请求
     *
     * @param url
     *            请求地址
     * @param params
     *            提交参数
     * @param files
     *            提交文件
     * @return 返回结果
     * @throws IOException
     */
    public static Response post(String url, Map<String, String> params,
                                Map<String, File> files) throws IOException {

        return createPostCall(url, params, files).execute();

    }

    /**
     * post异步请求
     *
     * @param url
     *            请求地址
     * @param params
     *            提交参数
     * @param files
     *            提交文件
     * @param callback
     *            返回结果
     */
    public static void post(String url, Map<String, String> params,
                            Map<String, File> files, Callback callback) {
        createPostCall(url, params, files).enqueue(callback);
    }

    private static Call createGetCall(String url, Map<String, String> params) {
        String urlParams = buildUrlParams(params);
        Request request = new Request.Builder().get()
                .url(url + '?' + urlParams).build();
        return client.newCall(request);
    }

    private static String buildUrlParams(Map<String, String> params) {
        if (params == null || params.isEmpty()) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (result.length() > 0)
                result.append("&");
            result.append(entry.getKey());
            result.append("=");
            result.append(entry.getValue());
        }
        return result.toString();
    }

    private static Call createPostCall(String url, Map<String, String> params,
                                       Map<String, File> files) {
        okhttp3.MultipartBody.Builder builder = new MultipartBody.Builder();
        // 上传的参数
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.addFormDataPart(entry.getKey(), entry.getValue());
            }
        }
        // 设置上传的文件
        if (files != null && !files.isEmpty()) {

            for (Entry<String, File> entry : files.entrySet()) {
                File file = entry.getValue();
                String contentType = null;

                boolean isPng = file.getName().endsWith(".png")
                        || file.getName().endsWith(".PNG");

                if (isPng) {
                    contentType = "image/png; charset=UTF-8";
                }

                boolean isJpg = file.getName().endsWith(".jpg")
                        || file.getName().endsWith(".JPG")
                        || file.getName().endsWith(".jpeg")
                        || file.getName().endsWith(".JPEG");
                if (isJpg) {
                    contentType = "image/jpeg; charset=UTF-8";
                }
                if (file != null && file.exists()) {
                    RequestBody body = RequestBody.create(
                            MediaType.parse(contentType), file);
                    builder.addFormDataPart(entry.getKey(), file.getName(),
                            body);
                }
            }
        }
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder().url(url).post(requestBody)
                .build();
        return client.newCall(request);
    }

    private static Call createPostCall(String url, Map<String, String> params) {
        Builder builder = new FormBody.Builder();
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder().url(url).post(requestBody)
                .build();
        return client.newCall(request);
    }

}