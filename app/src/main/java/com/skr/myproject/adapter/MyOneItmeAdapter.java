package com.skr.myproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.skr.myproject.R;
import com.skr.myproject.bean.CircleBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOneItmeAdapter extends RecyclerView.Adapter<MyOneItmeAdapter.ViewHolder> {
    private Context context;
    private List<CircleBean.ResultBean> circleBeanResult;

    public MyOneItmeAdapter(Context context, List<CircleBean.ResultBean> circleBeanResult) {
        this.context = context;
        this.circleBeanResult = circleBeanResult;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.one_itme_layout, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Glide.with(context).load(circleBeanResult.get(i).getImage()).into(viewHolder.oneImg1);
        Glide.with(context).load(circleBeanResult.get(i).getImage()).into(viewHolder.oneImg2);
    }

    @Override
    public int getItemCount() {
        return circleBeanResult.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.one_img1)
        ImageView oneImg1;
        @BindView(R.id.one_img2)
        ImageView oneImg2;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
