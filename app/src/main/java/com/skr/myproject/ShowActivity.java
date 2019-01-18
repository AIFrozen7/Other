package com.skr.myproject;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.skr.myproject.fragment.HomeFragment;
import com.skr.myproject.fragment.MineFragment;
import com.skr.myproject.fragment.SecondFragment;
import com.skr.myproject.fragment.MemuFragment;
import com.skr.myproject.fragment.ShopCarFragment;

import butterknife.ButterKnife;

public class ShowActivity extends AppCompatActivity {

    private RelativeLayout relative_layout;
    private RadioGroup radio_group;
    private HomeFragment homeFragment;
    private SecondFragment secondFragment;
    private ShopCarFragment shopCarFragment;
    private MemuFragment memuFragment;
    private MineFragment mineFragment;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioButton rb5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(ShowActivity.this);
        relative_layout = findViewById(R.id.relative_layout);
        radio_group = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);

        final FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        homeFragment = new HomeFragment();
        transaction.add(R.id.relative_layout,homeFragment);
        transaction.commit();
        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                FragmentTransaction beginTransaction = manager.beginTransaction();
                if (homeFragment!=null){
                    beginTransaction.hide(homeFragment);
                }
                if (secondFragment!=null){
                    beginTransaction.hide(secondFragment);
                }
                if (shopCarFragment!=null){
                    beginTransaction.hide(shopCarFragment);
                }
                if (memuFragment!=null){
                    beginTransaction.hide(memuFragment);
                }
                if (mineFragment!=null){
                    beginTransaction.hide(mineFragment);
                }

                switch (checkedId){
                    case R.id.rb1:
                        if (homeFragment == null){
                            homeFragment = new HomeFragment();
                            beginTransaction.add(R.id.relative_layout,homeFragment);
                        }else{
                            beginTransaction.show(homeFragment);
                        }
                        break;
                    case R.id.rb2:
                        if (secondFragment == null){
                            secondFragment = new SecondFragment();
                            beginTransaction.add(R.id.relative_layout,secondFragment);
                        }else{
                            beginTransaction.show(secondFragment);
                        }
                        break;
                    case R.id.rb3:
                        if (shopCarFragment == null){
                            shopCarFragment = new ShopCarFragment();
                            beginTransaction.add(R.id.relative_layout,shopCarFragment);
                        }else{
                            beginTransaction.show(shopCarFragment);
                        }
                        break;
                    case R.id.rb4:
                        if (memuFragment== null){
                            memuFragment = new MemuFragment();
                            beginTransaction.add(R.id.relative_layout,memuFragment);
                        }else{
                            beginTransaction.show(memuFragment);
                        }
                        break;
                    case R.id.rb5:

                        if (mineFragment== null){
                            mineFragment = new MineFragment();
                            beginTransaction.add(R.id.relative_layout,mineFragment);
                        }else{
                            beginTransaction.show(mineFragment);

                        }
                        break;
                }
                beginTransaction.commit();
            }
        });
    }


}