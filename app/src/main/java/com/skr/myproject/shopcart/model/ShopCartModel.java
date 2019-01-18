package com.skr.myproject.shopcart.model;

import com.skr.myproject.network.OKHttp3;

import java.util.Map;

public class ShopCartModel implements IShopCartModel {
    @Override
    public void getData(String url, Map<String,String> map, final ModelCallBack modelCallBack) {
        OKHttp3.getInstance().doPost(url, map, new OKHttp3.NetCallBack() {
            @Override
            public void onSuccess(String data) {
                modelCallBack.loadSuccess(data);
            }

            @Override
            public void onFailed(Exception e) {
                modelCallBack.loadFailed(e);

            }
        });
    }
}
