package com.skr.myproject.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.skr.myproject.R;
import com.skr.myproject.adapter.MyCircleAdapter;
import com.skr.myproject.bean.CircleBean;
import com.skr.myproject.circle.presenter.CirclePresenter;
import com.skr.myproject.circle.view.ICircleView;
import com.skr.myproject.space.SpaceItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SecondFragment extends Fragment implements ICircleView {

    Unbinder unbinder;
    @BindView(R.id.xrecy_cler_view)
    XRecyclerView xrecyClerView;
    private CirclePresenter circlePresenter;
    private LinearLayoutManager linearLayoutManager;
    int page = 1;
    private MyCircleAdapter circleAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        //创建线性布局
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置条目之间的的间距
        xrecyClerView.addItemDecoration(new SpaceItemDecoration(30));
        xrecyClerView.setLayoutManager(linearLayoutManager);
        //初始化presenter
        circlePresenter = new CirclePresenter(SecondFragment.this);
        circlePresenter.getPresenterData(page);
        xrecyClerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                circlePresenter.getPresenterData(1);
                circleAdapter.notifyDataSetChanged();
                xrecyClerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                circlePresenter.getPresenterData(page++);
                circleAdapter.notifyDataSetChanged();
                xrecyClerView.loadMoreComplete();
            }
        });
        return view;
    }

    @Override
    public void getViewData(final String data) {
        // Log.i("aa", "run: " + data);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                CircleBean circleBean = gson.fromJson(data, CircleBean.class);
                List<CircleBean.ResultBean> circleBeanResult = circleBean.getResult();
                //创建适配器
                circleAdapter = new MyCircleAdapter(getActivity(), circleBeanResult);
                xrecyClerView.setAdapter(circleAdapter);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
