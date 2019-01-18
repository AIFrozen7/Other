package com.skr.myproject.circle.model;

public interface ICircleModel {
    void getData(String url,ModelInterface modelInterface);
    interface ModelInterface{
        void loadSuccess(String data);
        void loadFailed();
    }
}
