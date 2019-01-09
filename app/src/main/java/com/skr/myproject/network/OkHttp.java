package com.skr.myproject.network;


import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttp {

    private static OkHttpClient okHttpClient;
    private static Request request;
    private static OkHttpClient client;
    private static RequestBody build;
    private static Request requestBuild;
    private static volatile OkHttp instance;
    private final OkHttpClient client1;

    //添加拦截
    private Interceptor getAppInterceptor() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                return response;
            }
        };
        return interceptor;
    }

    private OkHttp() {
        File file = new File(Environment.getDataDirectory(), "cachell");
        client1 = new OkHttpClient()
                .newBuilder()
                .readTimeout(3000, TimeUnit.SECONDS)
                .connectTimeout(3000, TimeUnit.SECONDS)
                .addInterceptor(getAppInterceptor())
                .cache(new Cache(file, 10 * 1024))
                .build();
    }
    //单例okhttp
    public static OkHttp getInstance(){
        if (instance==null){
            synchronized (OkHttp.class){
                if (instance==null){
                    instance = new OkHttp();
                }
            }
        }
        return instance;
    }


    public static void okHttpGet(String url, Callback callback) {
        //创建okhttpclient
        okHttpClient = new OkHttpClient();
        //创建request
        request = new Request.Builder().url(url).method("GET", null).build();
        okHttpClient.newCall(request).enqueue(callback);

    }

    public static void okHttpPost(String url, String name, String pwd, Callback callback) {
        client = new OkHttpClient();
        build = new FormBody.Builder().add("phone", name).add("pwd", pwd).build();
        requestBuild = new Request.Builder().url(url).post(OkHttp.build).build();
        client.newCall(requestBuild).enqueue(callback);
    }
}
