package com.skr.myproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.skr.myproject.bean.LoginBean;
import com.skr.myproject.login.presenter.LoginPresenter;
import com.skr.myproject.login.view.ILoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.et_login_name)
    EditText etLoginName;
    @BindView(R.id.et_login_pwd)
    EditText etLoginPwd;
    @BindView(R.id.ck_rem_pwd)
    CheckBox ckRemPwd;
    @BindView(R.id.text_reg)
    TextView textReg;
    @BindView(R.id.btn_login)
    Button btnLogin;

    private LoginPresenter loginPresenter;
    private SharedPreferences preferences;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind = ButterKnife.bind(this);
        preferences = getSharedPreferences("config", MODE_PRIVATE);
        boolean flag = preferences.getBoolean("flag", false);
        ckRemPwd.setChecked(flag);
        if (flag) {
            String name = preferences.getString("name", "");
            String pwd = preferences.getString("pwd", "");
            etLoginName.setText(name);
            etLoginPwd.setText(pwd);

        }
        //初始化presenter
        loginPresenter = new LoginPresenter(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etLoginName.getText().toString();
                String pwd = etLoginPwd.getText().toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(MainActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                } else {

                    loginPresenter.loginPre(name, pwd);
                    SharedPreferences.Editor edit = preferences.edit();
                    if (ckRemPwd.isChecked()) {
                        edit.putBoolean("flag", true);
                        edit.putString("name", name);
                        edit.putString("pwd", pwd);
                    } else {
                        edit.putBoolean("flag", false);
                    }
                    edit.commit();
                }
            }
        });
        textReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showMsg(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(msg, LoginBean.class);
                String status = loginBean.getStatus();
                if (status.equals("0000")) {
                    Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void jumpActivity() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
