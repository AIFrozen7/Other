package com.skr.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.Gson;
import com.skr.myproject.bean.ShopDetails;
import com.skr.myproject.details.presenter.DetailsPresenter;
import com.skr.myproject.details.view.IDetailsView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements IDetailsView {


    @BindView(R.id.details_web)
    WebView detailsWeb;
    private DetailsPresenter detailsPresenter;
    private String commodityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(DetailsActivity.this);
        Intent intent = getIntent();
        commodityId = intent.getStringExtra("id");
        detailsPresenter = new DetailsPresenter(DetailsActivity.this);


        detailsPresenter.getPresenterData(commodityId);

    }


    @Override
    public void getDetails(final String details) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Log.i("aa", "run: "+details);
                Gson gson = new Gson();
                ShopDetails shopDetails = gson.fromJson(details, ShopDetails.class);
                //mWebView.loadDataWithBaseURL(null, data, "text/html", "utf-8", null);

                detailsWeb.loadDataWithBaseURL(null,shopDetails.getResult().getDetails(),"text/html","UTF-8",null);
                detailsWeb.setWebViewClient(new WebViewClient());
                WebSettings settings = detailsWeb.getSettings();
                settings.setJavaScriptEnabled(true);

            }
        });
    }
}
