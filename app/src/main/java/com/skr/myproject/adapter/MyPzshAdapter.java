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

public class MyPzshAdapter extends BaseAdapter {
    private Context context;
    private List<MyData.ResultBean.PzshBean.CommodityListBeanX> pzshList;

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
            holder = new ViewHolder();
            holder.pzsh_img = convertView.findViewById(R.id.pzsh_img);
            holder.pzsh_title = convertView.findViewById(R.id.pzsh_title);
            holder.pzsh_price = convertView.findViewById(R.id.pzsh_price);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(pzshList.get(position).getMasterPic(), holder.pzsh_img);
        holder.pzsh_title.setText(pzshList.get(position).getCommodityName());
        holder.pzsh_price.setText("￥:"+pzshList.get(position).getPrice());
        return convertView;
    }

    class ViewHolder {
        private ImageView pzsh_img;
        private TextView pzsh_title, pzsh_price;
    }
}
