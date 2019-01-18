package com.skr.myproject.details.presenter;

import android.util.Log;

import com.skr.myproject.DetailsActivity;
import com.skr.myproject.api.Api;
import com.skr.myproject.details.model.DetailsModel;
import com.skr.myproject.details.model.IDetailsModel;

public class DetailsPresenter implements IDetailsPresenter {
    DetailsActivity detailsActivity;
    private final DetailsModel detailsModel;

    public DetailsPresenter(DetailsActivity detailsActivity) {
        this.detailsActivity = detailsActivity;
        detailsModel = new DetailsModel();
    }

    @Override
    public void getPresenterData(String details) {
        detailsModel.getData(Api.SHOP_DETAILS + details, new IDetailsModel.ModelInterface() {
            @Override
            public void loadSuccess(String data) {
                //Log.i("aa", "loadSuccess: "+data);
                detailsActivity.getDetails(data);
            }

            @Override
            public void loadFaild() {

            }
        });
    }
}
