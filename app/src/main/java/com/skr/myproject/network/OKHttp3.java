package com.skr.myproject.network;

import android.os.Environment;
import android.os.Handler;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OKHttp3 {
    private static OkHttpClient httpClient;
    private static Handler handler = new Handler();
    private static volatile OKHttp3 instance;

    //添加拦截器
    private Interceptor getAppInterceptor() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //拦截前
                Request request = chain.request();
                //拦截后
                Response response = chain.proceed(request);
                return response;
            }
        };
        return interceptor;
    }

    private OKHttp3() {
        File file = new File(Environment.getDataDirectory(), "cachell");

        httpClient = new OkHttpClient().newBuilder()
                .readTimeout(3000, TimeUnit.SECONDS)//设置读取超时时间
                .connectTimeout(3000, TimeUnit.SECONDS)//设置读取超时时间
                .addInterceptor(getAppInterceptor())
                .cache(new Cache(file, 10 * 1024))
                .build();
    }
    //单例
    public static OKHttp3 getInstance(){
        if (instance == null){
            synchronized (OKHttp3.class){
                if (instance == null){
                    instance = new OKHttp3();
                }
            }
        }
        return instance;
    }





    public void doGet(String url, final NetCallBack netCallBack) {

        final Request request = new Request.Builder().get()
                .url(url)
                .build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        netCallBack.onFailed(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String data = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        netCallBack.onSuccess(data);
                    }
                });
            }
        });


    }

    public void doPost(String url, Map<String, String> map, final NetCallBack netCallBack) {
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : map.keySet()) {
            builder.add(key,map.get(key));
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        netCallBack.onFailed(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String data = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        netCallBack.onSuccess(data);
                    }
                });
            }
        });
    }

    public interface NetCallBack{
        void onSuccess(String data);
        void onFailed(Exception e);
    }

}
