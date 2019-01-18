package com.skr.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.commonsdk.proguard.t;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.SnsPlatform;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginAndShareAtivity extends AppCompatActivity {

    @BindView(R.id.qq_login)
    ImageView qqLogin;
    @BindView(R.id.qq_share)
    ImageView qqShare;

    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();
    @BindView(R.id.tv_result)
    TextView tvResult;
    private SHARE_MEDIA[] list = {SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE};
    private UMShareAPI mUMShareAPI;
    private boolean isauth;
    private UMImage image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_share_ativity);
        ButterKnife.bind(this);
        //A.三方平台,添加到遍历的集合中
        initPlatforms();
        //A.获取UM的对象
        mUMShareAPI = UMShareAPI.get(LoginAndShareAtivity.this);
        //A.获取是否授权
        isauth = UMShareAPI.get(this).isAuthorize(this, platforms.get(0).mPlatform);

        /*//分享
        //网络图片
        image = new UMImage(LoginAndShareAtivity.this, "http://b.hiphotos.baidu.com/zhidao/pic/item/63d9f2d3572c11df28e42e30602762d0f703c2e8.jpg");
        final UMImage imagelocal = new UMImage(this, R.mipmap.ic_launcher);
        imagelocal.setThumb(new UMImage(this, R.mipmap.ic_launcher));
        imagelocal.setTitle("aaaaaa");*/


    }

    private void initPlatforms() {
        //A.集合清空
        platforms.clear();
        //A.通过for循环,把数组数据添加到集合中
        for (SHARE_MEDIA e : list) {
            if (!e.toString().equals(SHARE_MEDIA.GENERIC.toString())) {
                platforms.add(e.toSnsPlatform());
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    //A.
    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调，可以用来处理等待框，或相关的文字提示
        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> data) {
            //获取用户授权后的信息
            Set<String> strings = data.keySet();
            data.get("profile_image_url");
            String temp = "";
            for (String key : strings) {
                temp = temp + key + " :" + data.get(key) + "\n";
            }
            if (temp != null) {
                Intent intent = new Intent(LoginAndShareAtivity.this,ShowActivity.class);
                startActivity(intent);
                finish();
            }
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            Toast.makeText(LoginAndShareAtivity.this, "失败："+ throwable.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            Toast.makeText(LoginAndShareAtivity.this, "取消了", Toast.LENGTH_LONG).show();
        }


    };

    //B.分享的逻辑代码
    private UMShareListener shareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(LoginAndShareAtivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(LoginAndShareAtivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(LoginAndShareAtivity.this, "取消了", Toast.LENGTH_LONG).show();

        }
    };


    @OnClick({R.id.qq_login, R.id.qq_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.qq_login:
                if (isauth) {
                    Toast.makeText(LoginAndShareAtivity.this, "授权成功", Toast.LENGTH_SHORT).show();
                    mUMShareAPI.deleteOauth(LoginAndShareAtivity.this, platforms.get(0).mPlatform, authListener);
                } else {
                    mUMShareAPI.doOauthVerify(LoginAndShareAtivity.this, platforms.get(0).mPlatform, authListener);
                }
                mUMShareAPI.getPlatformInfo(LoginAndShareAtivity.this, platforms.get(0).mPlatform, authListener);


                break;
            case R.id.qq_share:

                new ShareAction(LoginAndShareAtivity.this).withMedia(image)
                        .setPlatform(platforms.get(0).mPlatform)
                        .setCallback(shareListener).share();

                new ShareAction(LoginAndShareAtivity.this).setPlatform(SHARE_MEDIA.QQ)
                        .withText("hello")
                        .setCallback(shareListener)
                        .share();

                break;
        }
    }
}
