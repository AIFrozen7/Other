package com.skr.myproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.skr.myproject.R;
import com.skr.myproject.bean.CircleBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyTwoItmeAdapter extends RecyclerView.Adapter<MyTwoItmeAdapter.ViewHolder> {


    private Context context;
    private List<CircleBean.ResultBean> circleBeanResult;

    public MyTwoItmeAdapter(Context context, List<CircleBean.ResultBean> circleBeanResult) {
        this.context = context;
        this.circleBeanResult = circleBeanResult;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.two_itme_layout, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //设置图片圆角角度
        RoundedCorners roundedCorners= new RoundedCorners(20);
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        RequestOptions options=RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
        Glide.with(context).load(circleBeanResult.get(i+1).getImage()).apply(options).into(viewHolder.twoImg1);
        Glide.with(context).load(circleBeanResult.get(i+1).getImage()).apply(options).into(viewHolder.twoImg2);
        Glide.with(context).load(circleBeanResult.get(i+1).getImage()).apply(options).into(viewHolder.twoImg3);
    }

    @Override
    public int getItemCount() {
        return circleBeanResult.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.two_img1)
        ImageView twoImg1;
        @BindView(R.id.two_img2)
        ImageView twoImg2;
        @BindView(R.id.two_img3)
        ImageView twoImg3;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
