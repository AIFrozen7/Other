package com.skr.myproject.circle.presenter;

import com.skr.myproject.api.Api;
import com.skr.myproject.circle.model.CiecleModel;
import com.skr.myproject.circle.model.ICircleModel;
import com.skr.myproject.fragment.SecondFragment;

public class CirclePresenter implements ICirclePresenter{

    SecondFragment secondFragment;
    private final CiecleModel ciecleModel;

    public CirclePresenter(SecondFragment secondFragment) {
        this.secondFragment = secondFragment;
        ciecleModel = new CiecleModel();
    }
    @Override
    public void getPresenterData(int page) {
        ciecleModel.getData(Api.SHOP_CIRCLE + page, new ICircleModel.ModelInterface() {
            @Override
            public void loadSuccess(String data) {
                secondFragment.getViewData(data);
            }

            @Override
            public void loadFailed() {

            }
        });
    }
}
