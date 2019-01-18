package com.skr.myproject.circle.model;

import com.skr.myproject.network.OkHttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CiecleModel implements ICircleModel {
    @Override
    public void getData(String url, final ModelInterface modelInterface) {
        OkHttp.okHttpGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                modelInterface.loadSuccess(response.body().string());
            }
        });
    }
}
