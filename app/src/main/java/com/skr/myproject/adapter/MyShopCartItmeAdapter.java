package com.skr.myproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.skr.myproject.R;
import com.skr.myproject.bean.ShopCartBean;
import com.skr.myproject.customview.CustomView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyShopCartItmeAdapter extends RecyclerView.Adapter<MyShopCartItmeAdapter.ViewHolder> {


    private Context context;
    private List<ShopCartBean.DataBean.ListBean> listBeans;
    private ShopCallBackListener shopCallBackListener;

    public void setShopCallBackListener(ShopCallBackListener shopCallBackListener) {
        this.shopCallBackListener = shopCallBackListener;
    }

    public MyShopCartItmeAdapter(Context context, List<ShopCartBean.DataBean.ListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.shopcart_itme_layout, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        Glide.with(context).load(listBeans.get(i).getImages().split("\\|")[0].replace("https", "http")).into(viewHolder.goodsImg);
        viewHolder.googsTitle.setText(listBeans.get(i).getTitle());
        viewHolder.goodsPrice.setText("￥:" + listBeans.get(i).getPrice() + "元");
        viewHolder.custom_edit_text.setText(listBeans.get(i).getNum() + "");
        viewHolder.customView.setClickListener(new CustomView.OnAddOrDelClickListener() {
            @Override
            public void onAddClick(View view) {
                int number = viewHolder.customView.getNumber();
                number++;
                viewHolder.customView.setNumber(number);
                listBeans.get(i).setNum(number);
            }

            @Override
            public void onDelClick(View view) {
                int num = viewHolder.customView.getNumber();
                num--;
                viewHolder.customView.setNumber(num);
                listBeans.get(i).setNum(num);
            }
        });

        //根据记录状态改变勾选
        viewHolder.goodsCheckbox.setChecked(listBeans.get(i).isCheck());
        //选中改变的监听
        viewHolder.goodsCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //优先改变自己的状态
                listBeans.get(i).setCheck(isChecked);
                if (shopCallBackListener != null) {
                    shopCallBackListener.callBack();
                }
            }
        });
        viewHolder.customView.setData(this, listBeans, i);
        viewHolder.customView.setCallBackListener(new CustomView.CallBackListener() {
            @Override
            public void mycallBack() {
                if (shopCallBackListener != null) {
                    shopCallBackListener.callBack();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView custom_edit_text;
        @BindView(R.id.goods_checkbox)
        CheckBox goodsCheckbox;
        @BindView(R.id.goods_img)
        ImageView goodsImg;
        @BindView(R.id.googs_title)
        TextView googsTitle;
        @BindView(R.id.goods_price)
        TextView goodsPrice;
        @BindView(R.id.custom_view)
        CustomView customView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            custom_edit_text = itemView.findViewById(R.id.custom_edit_text);
        }
    }

    //在子商品中的adapter中，修改子商品的全选和反选
    public void selectAllOrRemoveAll(boolean selectAll) {
        for (ShopCartBean.DataBean.ListBean listBean : listBeans) {
            listBean.setCheck(selectAll);
        }
        notifyDataSetChanged();
    }

    //定义接口
    public interface ShopCallBackListener {
        void callBack();
    }
}
