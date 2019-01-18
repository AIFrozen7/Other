package com.skr.myproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BannerWebActivity extends Activity {

    @BindView(R.id.banner_webview)
    WebView bannerWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_web);
        ButterKnife.bind(BannerWebActivity.this);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        bannerWebview.loadUrl(url);
        bannerWebview.setWebViewClient(new WebViewClient());
        WebSettings settings = bannerWebview.getSettings();
        settings.setJavaScriptEnabled(true);

    }
}
