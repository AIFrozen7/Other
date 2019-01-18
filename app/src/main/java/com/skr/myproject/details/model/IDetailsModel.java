package com.skr.myproject.details.model;

import java.util.Map;

public interface IDetailsModel {
    void getData(String url, ModelInterface modelInterface);
    interface ModelInterface{
        void loadSuccess(String data);
        void loadFaild();
    }
}
