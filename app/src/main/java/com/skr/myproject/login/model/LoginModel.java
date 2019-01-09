package com.skr.myproject.login.model;

import com.skr.myproject.network.OkHttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginModel implements ILoginModel {
    @Override
    public void login(String url, String name, String pwd, final ILoginCallBack iLoginCallBack) {
        OkHttp.okHttpPost(url, name, pwd, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                iLoginCallBack.onStatus(response.body().string());
            }
        });
    }


}
