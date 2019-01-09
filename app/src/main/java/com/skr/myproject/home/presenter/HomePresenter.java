package com.skr.myproject.home.presenter;

import com.skr.myproject.api.Api;
import com.skr.myproject.fragment.HomeFragment;
import com.skr.myproject.home.model.HomeModel;
import com.skr.myproject.home.model.IHomeModel;
import com.skr.myproject.login.presenter.ILoginPresenter;

public class HomePresenter implements IHomePresenter{

   HomeFragment homeFragment;
    private final HomeModel homeModel;

    public HomePresenter(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
        homeModel = new HomeModel();
    }

    @Override
    public void getPresenterData() {
        homeModel.getData(Api.SHOPLIST, new IHomeModel.ModelInterface() {
            @Override
            public void loadSuccess(String data) {
                homeFragment.getViewData(data);
            }

            @Override
            public void loadFailed() {

            }
        });
        homeModel.getData(Api.BUNNER, new IHomeModel.ModelInterface() {
            @Override
            public void loadSuccess(String data) {
                homeFragment.getBannerData(data);
            }

            @Override
            public void loadFailed() {

            }
        });
    }

}
