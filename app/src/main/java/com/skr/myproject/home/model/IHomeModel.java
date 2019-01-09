package com.skr.myproject.home.model;

public interface IHomeModel {
    //获取数据
    void getData(String url,ModelInterface modelInterface);
    //定义接口
    interface ModelInterface{
        void loadSuccess(String data);
        void loadFailed();
    }
}
