package com.skr.myproject.regist.presenter;

import com.skr.myproject.RegistActivity;
import com.skr.myproject.api.Api;
import com.skr.myproject.regist.model.IRegistModel;
import com.skr.myproject.regist.model.RegistModel;

public class RegistPresenter implements IRegistPresenter{
    RegistActivity registActivity;
    private final RegistModel registModel;

    public RegistPresenter(RegistActivity registActivity) {
        this.registActivity = registActivity;
        registModel = new RegistModel();
    }

    @Override
    public void registPre(String name, String pwd) {
        registModel.regist(Api.REGIST, name, pwd, new IRegistModel.IRegistCallBack() {
            @Override
            public void onStatus(String data) {
                registActivity.showMsg(data);
                registActivity.jumpActivity();
            }

            @Override
            public void onFailed() {

            }
        });
    }
}
