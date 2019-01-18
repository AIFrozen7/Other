package com.skr.myproject.shopcart.presenter;

import com.skr.myproject.api.Api;
import com.skr.myproject.fragment.ShopCarFragment;
import com.skr.myproject.shopcart.model.IShopCartModel;
import com.skr.myproject.shopcart.model.ShopCartModel;

import java.util.Map;

public class ShopCartPresenter implements IShopCartPresenter{
    ShopCarFragment shopCarFragment;
    private final ShopCartModel shopCartModel;

    public ShopCartPresenter(ShopCarFragment shopCarFragment) {
        this.shopCarFragment = shopCarFragment;
        shopCartModel = new ShopCartModel();
    }

    @Override
    public void getPresenterData(Map<String,String> map) {
        shopCartModel.getData(Api.SHOPPING_CART, map, new IShopCartModel.ModelCallBack() {
            @Override
            public void loadSuccess(String data) {
                shopCarFragment.getViewData(data);
            }

            @Override
            public void loadFailed(Exception e) {

            }
        });

    }
}
