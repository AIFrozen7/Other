package com.skr.myproject.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.skr.myproject.R;
import com.skr.myproject.bean.MyData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyPzshAdapter extends BaseAdapter {
    private Context context;
    private List<MyData.ResultBean.PzshBean.CommodityListBeanX> pzshList;
    private Unbinder bind;

    public MyPzshAdapter(Context context, List<MyData.ResultBean.PzshBean.CommodityListBeanX> pzshList) {
        this.context = context;
        this.pzshList = pzshList;
    }

    @Override
    public int getCount() {
        return pzshList.size();
    }

    @Override
    public Object getItem(int position) {
        return pzshList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.pzsh_grid_layout, null);
            holder = new ViewHolder(convertView);
            bind = ButterKnife.bind(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(pzshList.get(position).getMasterPic(), holder.pzshImg);
        holder.pzshTitle.setText(pzshList.get(position).getCommodityName());
        holder.pzshPrice.setText("￥:" + pzshList.get(position).getPrice());
        return convertView;
    }



    class ViewHolder {
        @BindView(R.id.pzsh_img)
        ImageView pzshImg;
        @BindView(R.id.pzsh_title)
        TextView pzshTitle;
        @BindView(R.id.pzsh_price)
        TextView pzshPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
