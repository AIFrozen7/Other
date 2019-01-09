package com.skr.myproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;


import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.skr.myproject.R;
import com.skr.myproject.adapter.MyMlssAdapter;
import com.skr.myproject.adapter.MyPzshAdapter;
import com.skr.myproject.adapter.MyRxxpAdapter;
import com.skr.myproject.bean.MyBanner;
import com.skr.myproject.bean.MyData;
import com.skr.myproject.home.presenter.HomePresenter;
import com.skr.myproject.home.view.IHomeView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.List;

public class HomeFragment extends Fragment implements IHomeView {

   // private XBanner xbanner;
    private GridView grid_view_rxxp;
    private ListView list_view_mlss;
    private GridView grid_view_pzsh;
    private List<MyData.ResultBean.MlssBean.CommodityListBeanXX> mlssList;
    private List<MyData.ResultBean.PzshBean.CommodityListBeanX> pzshList;
    private List<MyData.ResultBean.RxxpBean.CommodityListBean> rxxpList;
    private MZBannerView mzbanner;
    private ImageView banner_image;
    private List<MyBanner.ResultBean> result;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_layout, container, false);
        //xbanner = view.findViewById(R.id.xbanner);
        mzbanner = view.findViewById(R.id.banner);
        grid_view_rxxp = view.findViewById(R.id.grid_view_rxxp);
        list_view_mlss = view.findViewById(R.id.list_view_mlss);
        grid_view_pzsh = view.findViewById(R.id.grid_view_pzsh);

        //初始化presenter
        HomePresenter homePresenter = new HomePresenter(this);
        homePresenter.getPresenterData();
        return view;
    }

    @Override
    public void getViewData(String mViewData) {
        Gson gson = new Gson();
        MyData myData = gson.fromJson(mViewData, MyData.class);
        mlssList = myData.getResult().getMlss().get(0).getCommodityList();
        pzshList = myData.getResult().getPzsh().get(0).getCommodityList();
        rxxpList = myData.getResult().getRxxp().get(0).getCommodityList();

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (rxxpList != null){
                    MyRxxpAdapter rxxpAdapter = new MyRxxpAdapter(getActivity(),rxxpList);
                    grid_view_rxxp.setAdapter(rxxpAdapter);
                }
                if (mlssList != null){
                    MyMlssAdapter myMlssAdapter = new MyMlssAdapter(getActivity(),mlssList);
                    list_view_mlss.setAdapter(myMlssAdapter);
                }
                if (pzshList != null){
                    MyPzshAdapter pzshAdapter = new MyPzshAdapter(getActivity(),pzshList);
                    grid_view_pzsh.setAdapter(pzshAdapter);
                }

            }
        });


    }

    @Override
    public void getBannerData(String banner) {
        Gson gson = new Gson();
        MyBanner myBanner = gson.fromJson(banner, MyBanner.class);
        result = myBanner.getResult();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
               /* xbanner.setData(result,null);
                xbanner.setmAdapter(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, View view, int position) {
                        Glide.with(getActivity()).load(result.get(position).getImageUrl()).into((ImageView) view);
                    }
                });
                xbanner.setPageTransformer(Transformer.Default);
                xbanner.setPageChangeDuration(500);*/
                mzbanner.setPages(result, new MZHolderCreator<BannerViewHolder>() {

                    @Override
                    public BannerViewHolder createViewHolder() {
                        return new BannerViewHolder();
                    }
                });
                mzbanner.setDelayedTime(500);
                mzbanner.setDuration(500);

            }
        });
    }

   @Override
    public void onResume() {
        super.onResume();
        mzbanner.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        mzbanner.pause();
    }

    private class BannerViewHolder implements MZViewHolder {
        @Override
        public View createView(Context context) {
            View bview =LayoutInflater.from(getActivity()).inflate(R.layout.banner_item_layout,null);
            banner_image = bview.findViewById(R.id.banner_image);
            return bview;

        }

        @Override
        public void onBind(Context context, int i, Object o) {

            ImageLoader.getInstance().displayImage(result.get(i).getImageUrl(),banner_image);

        }
    }
}
