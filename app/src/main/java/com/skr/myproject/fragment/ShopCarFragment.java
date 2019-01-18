package com.skr.myproject.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.gson.Gson;
import com.skr.myproject.R;
import com.skr.myproject.adapter.MyShopCartAdapter;
import com.skr.myproject.bean.ShopCartBean;
import com.skr.myproject.shopcart.presenter.ShopCartPresenter;
import com.skr.myproject.shopcart.view.IShopCartView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShopCarFragment extends Fragment implements IShopCartView {
    @BindView(R.id.shopcart_recycler_view)
    RecyclerView shopcartRecyclerView;
    Unbinder unbinder;
    @BindView(R.id.shop_checkbox)
    CheckBox shopCheckbox;
    @BindView(R.id.shop_total)
    TextView shopTotal;
    @BindView(R.id.shop_jie)
    Button shopJie;
    private Map<String, String> map;
    private ShopCartPresenter shopCartPresenter;
    private List<ShopCartBean.DataBean> cartBeanData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_car_layout, container, false);
        map = new HashMap<>();
        unbinder = ButterKnife.bind(this, view);
        shopCartPresenter = new ShopCartPresenter(ShopCarFragment.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        shopcartRecyclerView.setLayoutManager(linearLayoutManager);
        //shopcartRecyclerView.addItemDecoration(new SpaceItemDecoration(20));
        map.put("uid", "71");
        shopCartPresenter.getPresenterData(map);
        return view;
    }

    @Override
    public void getViewData(String data) {
        Gson gson = new Gson();
        ShopCartBean shopCartBean = gson.fromJson(data, ShopCartBean.class);
        cartBeanData = shopCartBean.getData();
        //创建适配器
        final MyShopCartAdapter shopCartAdapter = new MyShopCartAdapter(getActivity(), cartBeanData);
        shopcartRecyclerView.setAdapter(shopCartAdapter);

        shopCartAdapter.setShopCallBackListener(new MyShopCartAdapter.ShopCallBackListener() {
            @Override
            public void callBack(List<ShopCartBean.DataBean> list) {
                //在这里重新遍历已经改状态后的数据，
                // 这里不能break跳出，因为还需要计算后面点击商品的价格和数目，所以必须跑完整个循环
                double totalPrice = 0;
                //勾选商品的数量，不是该商品购买的数量
                int num = 0;
                //所有商品总数，和上面的数量做比对，如果两者相等，则说明全选
                int totalNum = 0;
                for (int i = 0; i < list.size(); i++) {
                    List<ShopCartBean.DataBean.ListBean> listAll = list.get(i).getList();
                    for (int j = 0; j < listAll.size(); j++) {
                       totalNum = totalNum + listAll.get(j).getNum();
                        //取选中的状态
                        if (listAll.get(j).isCheck()) {
                            totalPrice += listAll.get(j).getPrice() * listAll.get(j).getNum();
                            num += listAll.get(j ).getNum();
                        }
                    }
                }
                if (num < totalNum) {
                    shopCheckbox.setChecked(false);
                }else {
                    shopCheckbox.setChecked(true);
                }
                shopTotal.setText("合计:￥"+totalPrice);
                shopJie.setText("去计算（"+num+"）");
            }
        });
        shopCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSeller(shopCheckbox.isChecked());
                shopCartAdapter.notifyDataSetChanged();
            }
        });

        if (cartBeanData!=null){
            cartBeanData.remove(0);
            shopCartAdapter.setList(cartBeanData);
            shopCartAdapter.notifyDataSetChanged();
        }

    }

    private void checkSeller(boolean bool) {
        int totalPrice = 0;
        int num = 0;
        for (int a = 0; a < cartBeanData.size(); a++) {
            //遍历商家，改变状态
            ShopCartBean.DataBean dataBean = cartBeanData.get(a);
            dataBean.setCheck(bool);
            List<ShopCartBean.DataBean.ListBean> listAll = cartBeanData.get(a).getList();
            for (int i = 0; i < listAll.size(); i++) {
                //遍历商品，改变状态
                listAll.get(i).setCheck(bool);
               totalPrice +=  listAll.get(i).getPrice() * listAll.get(i).getNum();
                num += listAll.get(i).getNum();
            }
        }
        if (bool) {
            shopTotal.setText("合计：" + totalPrice);
            shopJie.setText("去结算(" + num + ")");
        } else {
            shopTotal.setText("合计：0.00");
            shopJie.setText("去结算(0)");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
