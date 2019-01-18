package com.skr.myproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.skr.myproject.R;
import com.skr.myproject.bean.CircleBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCircleAdapter extends XRecyclerView.Adapter<XRecyclerView.ViewHolder> {

    private Context context;
    private List<CircleBean.ResultBean> circleBeanResult;
    public static final int TYPE_ONE_IMAGE = 0;
    public static final int TYPE_TWO_IMAGE = 1;
    public MyCircleAdapter(Context context, List<CircleBean.ResultBean> circleBeanResult) {
        this.context = context;
        this.circleBeanResult = circleBeanResult;
    }
    @Override
    public int getItemCount() {
        return circleBeanResult.size();
    }
    @NonNull
    @Override
    public XRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == TYPE_ONE_IMAGE) {
            return new ItemOneViewHolder(LayoutInflater.from(context).inflate(R.layout.circle_layout, viewGroup, false));
        } else {
            return new ItemTwoViewHolder(LayoutInflater.from(context).inflate(R.layout.circle_two__layout, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull XRecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ItemOneViewHolder) {
            ((ItemOneViewHolder) viewHolder).circleTextoneName.setText(circleBeanResult.get(i).getNickName());
            //Glid圆形图片
            RequestOptions requestOptions = RequestOptions.circleCropTransform();
            Glide.with(context).load(circleBeanResult.get(i).getHeadPic()).apply(requestOptions).into(((ItemOneViewHolder) viewHolder).circleOneImg);
            //long类型转化成data类型
            Long dateLong = Long.valueOf(circleBeanResult.get(i).getCreateTime());
            String time = new SimpleDateFormat("yyyy-MM-dd hh:ss").format(new Date(dateLong));
            ((ItemOneViewHolder) viewHolder).circleTextoneTime.setText(time);
            ((ItemOneViewHolder) viewHolder).circleTextoneTitle.setText(circleBeanResult.get(i).getContent());
            ((ItemOneViewHolder) viewHolder).textOnePrise.setText(circleBeanResult.get(i).getGreatNum());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ItemOneViewHolder) viewHolder).recyclerOneView.setLayoutManager(linearLayoutManager);
            MyOneItmeAdapter oneItmeAdapter = new MyOneItmeAdapter(context, circleBeanResult);
            ((ItemOneViewHolder) viewHolder).recyclerOneView.setAdapter(oneItmeAdapter);
        }
        if (viewHolder instanceof ItemTwoViewHolder) {
            ((ItemTwoViewHolder) viewHolder).circleTexttwoName.setText(circleBeanResult.get(i).getNickName());

            RequestOptions requestOptions = RequestOptions.circleCropTransform();
            Glide.with(context).load(circleBeanResult.get(i).getHeadPic()).apply(requestOptions).into(((ItemTwoViewHolder) viewHolder).circleTwoImg);
            Long dateLong = Long.valueOf(circleBeanResult.get(i).getCreateTime());
            String time = new SimpleDateFormat("yyyy-MM-dd hh:ss").format(new Date(dateLong));
            ((ItemTwoViewHolder) viewHolder).circleTexttwoTime.setText(time);
            ((ItemTwoViewHolder) viewHolder).circleTexttwoTitle.setText(circleBeanResult.get(i).getContent());
            ((ItemTwoViewHolder) viewHolder).textTwoPrise.setText(circleBeanResult.get(i).getGreatNum());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ItemTwoViewHolder) viewHolder).recyclerTwoView.setLayoutManager(linearLayoutManager);
            MyTwoItmeAdapter twoItmeAdapter = new MyTwoItmeAdapter(context, circleBeanResult);
            ((ItemTwoViewHolder) viewHolder).recyclerTwoView.setAdapter(twoItmeAdapter);
        }
    }


    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    public class ItemOneViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.circle_one_img)
        ImageView circleOneImg;
        @BindView(R.id.circle_textone_name)
        TextView circleTextoneName;
        @BindView(R.id.circle_textone_time)
        TextView circleTextoneTime;
        @BindView(R.id.top_relativity)
        RelativeLayout topRelativity;
        @BindView(R.id.circle_textone_title)
        TextView circleTextoneTitle;
        @BindView(R.id.recycler_one_view)
        RecyclerView recyclerOneView;
        @BindView(R.id.text_one_prise)
        TextView textOnePrise;
        @BindView(R.id.circle_imgone_prise)
        ImageView circleImgonePrise;

        public ItemOneViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class ItemTwoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.circle_two_img)
        ImageView circleTwoImg;
        @BindView(R.id.circle_texttwo_name)
        TextView circleTexttwoName;
        @BindView(R.id.circle_texttwo_time)
        TextView circleTexttwoTime;
        @BindView(R.id.top_two_relativity)
        RelativeLayout topTwoRelativity;
        @BindView(R.id.circle_texttwo_title)
        TextView circleTexttwoTitle;
        @BindView(R.id.recycler_two_view)
        RecyclerView recyclerTwoView;
        @BindView(R.id.text_two_prise)
        TextView textTwoPrise;
        @BindView(R.id.circle_img_prise)
        ImageView circleImgPrise;

        public ItemTwoViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
