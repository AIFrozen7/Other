package com.skr.myproject.shopcart.model;

import java.util.Map;

public interface IShopCartModel {
    void getData(String url, Map<String,String> map, ModelCallBack modelCallBack);
    interface ModelCallBack{
        void loadSuccess(String data);
        void loadFailed(Exception e);
    }

}
