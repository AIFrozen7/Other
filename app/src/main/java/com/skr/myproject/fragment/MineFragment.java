package com.skr.myproject.fragment;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;
import com.orhanobut.logger.Logger;
import com.skr.myproject.R;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MineFragment extends Fragment {
    public static final String TAG = "MainActivity";
    @BindView(R.id.btn_sao)
    Button btnSao;
    Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_layout, container, false);

        unbinder = ButterKnife.bind(this, view);
        //初始化zxing对象
        ZXingLibrary.initDisplayOpinion(getActivity());
        Acp.getInstance(getActivity()).request(new AcpOptions.Builder()
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE
                                , Manifest.permission.READ_PHONE_STATE
                                , Manifest.permission.SEND_SMS
                                , Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.CALL_PHONE,
                                Manifest.permission.CAMERA
                        )

                        .build(),
                new AcpListener() {
                    @Override
                    public void onGranted() {
                        // call();
                    }

                    @Override
                    public void onDenied(List<String> permissions) {
                        Toast.makeText(getActivity(), "你拒绝了这个权限", Toast.LENGTH_SHORT).show();
                    }
                });

        return  view;
    }


    private void call() {
        /*为了防止崩溃，我们用异常捕获代码块捕获异常，startActivity(intent)报错，让添加安全权限**/
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == 112) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                    Log.d(TAG, "onActivityResult: "+ result);

                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_sao)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        startActivityForResult(intent, 112);
        Log.d(TAG, "onActivityResult: " + "我很快乐");
        Logger.i("hello444555555");
        Logger.v("hello444");
        Logger.wtf("hello666");
        String JSON_CONTENT =
                "{\"Category\":[{\"categoryId\":1,\"categoryName\":\"饮品\",\"categoryImage\":\"/upload/yinpin.jpg\"},{\"categoryId\":2,\"categoryName\":\"食品\",\"categoryImage\":\"/upload/shiping.jpg\"},{\"categoryId\":3,\"categoryName\":\"酒类\",\"categoryImage\":\"/upload/jiullei.jpg\"}],\"recommend\":{\"id\":11,\"productName\":\"统一老坛泡椒牛肉袋面香辣味110g*24袋\",\"filenameSmall\":\"/upload/ty_ltpj_small.jpg\",\"productPrice\":48.0,\"productCost\":47.5}}";
        Logger.json(JSON_CONTENT);
    }
}
