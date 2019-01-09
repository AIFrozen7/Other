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

public class MyMlssAdapter extends BaseAdapter {
    private Context context;
    List<MyData.ResultBean.MlssBean.CommodityListBeanXX> mlssList;

    public MyMlssAdapter(Context context, List<MyData.ResultBean.MlssBean.CommodityListBeanXX> mlssList) {
        this.context = context;
        this.mlssList = mlssList;
    }

    @Override
    public int getCount() {
        return mlssList.size();
    }

    @Override
    public Object getItem(int position) {
        return mlssList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.mlsh_grid_layout, null);
            holder = new ViewHolder();
            holder.mlss_img = convertView.findViewById(R.id.mlsh_img);
            holder.mlss_title = convertView.findViewById(R.id.mlsh_title);
            holder.mlss_price = convertView.findViewById(R.id.mlsh_price);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(mlssList.get(position).getMasterPic(), holder.mlss_img);
        holder.mlss_title.setText(mlssList.get(position).getCommodityName());
        holder.mlss_price.setText("￥:"+mlssList.get(position).getPrice());
        return convertView;
    }

    class ViewHolder {
        private ImageView mlss_img;
        private TextView mlss_title, mlss_price;
    }
}
