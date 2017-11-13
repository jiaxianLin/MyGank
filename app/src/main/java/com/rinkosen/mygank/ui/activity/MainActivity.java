package com.rinkosen.mygank.ui.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.rinkosen.mygank.R;
import com.rinkosen.mygank.adapter.MainAdapter;
import com.rinkosen.mygank.base.BaseActivity;
import com.rinkosen.mygank.entity.Meizi;
import com.rinkosen.mygank.mvp.model.MeiziData;
import com.rinkosen.mygank.mvp.presenter.MainPresenter;
import com.rinkosen.mygank.mvp.view.MainView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swrLayout;

    MainAdapter adapter;

    List<MeiziData> datas;

    boolean isRefresh;

    private LoadMoreView loadMoreView;

    int page = 1;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {
        presenter = new MainPresenter(this);
    }

    @Override
    protected void initView() {
        super.initView();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        adapter = new MainAdapter(this, new ArrayList<Meizi>());
        adapter.setLoadMoreView(getLoadMoreView());
        adapter.setOnLoadMoreListener(this, recyclerView);
        adapter.setOnItemChildClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        swrLayout.setOnRefreshListener(this);



    }

    public LoadMoreView getLoadMoreView() {
        return loadMoreView == null ? new LoadMoreView() {
            @Override
            public int getLayoutId() {
                return R.layout.quick_view_load_more;
            }

            @Override
            protected int getLoadingViewId() {
                return R.id.load_more_loading_view;
            }

            @Override
            protected int getLoadFailViewId() {
                return R.id.load_more_load_fail_view;
            }

            @Override
            protected int getLoadEndViewId() {
                return R.id.load_more_load_end_view;
            }
        } : loadMoreView;
    }

    @Override
    protected void initData() {
        super.initData();
        setTitle("Gank.io");
        presenter.getMeiziData(page);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void showMeiziList(List<Meizi> data) {
        page ++;
        if(isRefresh){
            adapter.setNewData(data);
            swrLayout.setRefreshing(false);
        } else {
            adapter.addData(data);
            adapter.loadMoreComplete();
        }
        isRefresh = false;

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.iv_meizi:

                break;
            case R.id.rl_gank:

                break;
        }
    }

    @Override
    public void onLoadMoreRequested() {
        isRefresh = false;
        presenter.getMeiziData(page);
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        page = 1;
        presenter.getMeiziData(page);

    }
}
