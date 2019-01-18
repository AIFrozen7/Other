package com.skr.myproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.skr.myproject.R;
import com.skr.myproject.bean.ShopCartBean;
import com.skr.myproject.space.SpaceItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyShopCartAdapter extends RecyclerView.Adapter<MyShopCartAdapter.ViewHolder> {


    private Context context;
    private List<ShopCartBean.DataBean> cartBeanData;
    private List<ShopCartBean.DataBean.ListBean> listBeans;
    private ShopCallBackListener shopCallBackListener;

    public void setShopCallBackListener(ShopCallBackListener shopCallBackListener) {
        this.shopCallBackListener = shopCallBackListener;
    }

    public MyShopCartAdapter(Context context, List<ShopCartBean.DataBean> cartBeanData) {
        this.context = context;
        this.cartBeanData = cartBeanData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.merchant_layout, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.merchantCheck.setText(cartBeanData.get(i).getSellerName());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        viewHolder.merchantRecyclerView.setLayoutManager(linearLayoutManager);

        listBeans = cartBeanData.get(i).getList();
        //创建适配器
        final MyShopCartItmeAdapter shopCartItmeAdapter = new MyShopCartItmeAdapter(context,listBeans);
        viewHolder.merchantRecyclerView.setAdapter(shopCartItmeAdapter);
        //viewHolder.merchantRecyclerView.addItemDecoration(new SpaceItemDecoration(10));

        viewHolder.merchantCheck.setChecked(cartBeanData.get(i).isCheck());



        shopCartItmeAdapter.setShopCallBackListener(new MyShopCartItmeAdapter.ShopCallBackListener() {
            @Override
            public void callBack() {
                if (shopCartItmeAdapter!=null){
                    shopCallBackListener.callBack(cartBeanData);
                }
                List<ShopCartBean.DataBean.ListBean> listBeanList = cartBeanData.get(i).getList();
                boolean isAllChecked = true;
                for (ShopCartBean.DataBean.ListBean bean: listBeanList) {
                    if (!bean.isCheck()){
                        isAllChecked= false;
                        break;
                    }
                }
                viewHolder.merchantCheck.setChecked(isAllChecked);
                cartBeanData.get(i).setCheck(isAllChecked);
            }
        });

        viewHolder.merchantCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartBeanData.get(i).setCheck(viewHolder.merchantCheck.isChecked());
                shopCartItmeAdapter.selectAllOrRemoveAll(viewHolder.merchantCheck.isChecked());
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartBeanData.size();
    }

    @OnClick(R.id.merchant_check)
    public void onViewClicked() {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.merchant_check)
        CheckBox merchantCheck;
        @BindView(R.id.merchant_recycler_view)
        RecyclerView merchantRecyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ShopCallBackListener {
        void callBack(List<ShopCartBean.DataBean> list);
    }

    public void setList(List<ShopCartBean.DataBean> cartBeanData){
        this.cartBeanData = cartBeanData;
       notifyDataSetChanged();
    }
}
