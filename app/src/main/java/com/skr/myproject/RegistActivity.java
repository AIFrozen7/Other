package com.skr.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class RegistActivity extends AppCompatActivity implements IRegistView {

    private EditText et_reg_name;
    private EditText et_reg_pwd;
    private EditText et_reg_yan;
    private TextView text_login;
    private Button btn_regist;
    private RegistPresenter registPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        et_reg_name = findViewById(R.id.et_reg_name);
        et_reg_yan = findViewById(R.id.et_reg_yan);
        et_reg_pwd = findViewById(R.id.et_reg_pwd);
        btn_regist = findViewById(R.id.btn_regist);
        text_login = findViewById(R.id.text_login);

        registPresenter = new RegistPresenter(this);
        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_reg_name.getText().toString();
                String pwd = et_reg_pwd.getText().toString();
                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd)){
                    Toast.makeText(RegistActivity.this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    registPresenter.registPre(name,pwd);
                }
            }
        });
        text_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void showMsg(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                RegistBean registBean = gson.fromJson(msg, RegistBean.class);
                String status = registBean.getStatus();
                if (status.equals("0000")){
                    Intent intent = new Intent(RegistActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(RegistActivity.this, registBean.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void jumpActivity() {

    }
}
