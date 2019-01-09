package com.skr.myproject.regist.model;

import com.skr.myproject.network.OkHttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RegistModel implements IRegistModel{
    @Override
    public void regist(String url, String name, String pwd, final IRegistCallBack callBack) {
        OkHttp.okHttpPost(url, name, pwd, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onStatus(response.body().string());
            }
        });
    }
}
