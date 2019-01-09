package com.skr.myproject.regist.model;



public interface IRegistModel {
    void regist(String url,String name,String pwd,IRegistCallBack callBack);
    //定义接口
    interface IRegistCallBack{
        void onStatus(String data);
        void onFailed();
    }
}
