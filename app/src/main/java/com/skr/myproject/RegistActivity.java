package com.skr.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.skr.myproject.bean.RegistBean;
import com.skr.myproject.regist.presenter.RegistPresenter;
import com.skr.myproject.regist.view.IRegistView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RegistActivity extends AppCompatActivity implements IRegistView {

    @BindView(R.id.et_reg_name)
    EditText etRegName;
    @BindView(R.id.et_reg_yan)
    EditText etRegYan;
    @BindView(R.id.et_reg_pwd)
    EditText etRegPwd;
    @BindView(R.id.text_login)
    TextView textLogin;
    @BindView(R.id.btn_regist)
    Button btnRegist;

    private RegistPresenter registPresenter;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        bind = ButterKnife.bind(RegistActivity.this);
        registPresenter = new RegistPresenter(this);
    }

    @Override
    public void showMsg(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                RegistBean registBean = gson.fromJson(msg, RegistBean.class);
                String status = registBean.getStatus();
                if (status.equals("0000")) {
                    Intent intent = new Intent(RegistActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegistActivity.this, registBean.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void jumpActivity() {

    }

    @OnClick({R.id.et_reg_name, R.id.et_reg_yan, R.id.et_reg_pwd, R.id.text_login, R.id.btn_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_reg_name:
                break;
            case R.id.et_reg_yan:
                break;
            case R.id.et_reg_pwd:
                break;
            case R.id.text_login:
                Intent intent = new Intent(RegistActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_regist:
                String name = etRegName.getText().toString();
                String pwd = etRegPwd.getText().toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(RegistActivity.this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    registPresenter.registPre(name, pwd);
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
