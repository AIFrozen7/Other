package com.skr.myproject.myapp;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)

                .build();
        ImageLoader.getInstance().init(configuration);

        UMShareAPI.get(this);
        UMConfigure.init(this,"573f0e9267e58e8e48001545","小米",UMConfigure.DEVICE_TYPE_PHONE,"");
        PlatformConfig.setQQZone("1106036236","mjFCi0oxXZKZEWJs");
    }
}
