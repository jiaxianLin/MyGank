package com.rinkosen.mygank.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rinkosen.mygank.R;
import com.rinkosen.mygank.adapter.GankAdapter;
import com.rinkosen.mygank.base.App;
import com.rinkosen.mygank.base.BaseActivity;
import com.rinkosen.mygank.entity.Gank;
import com.rinkosen.mygank.entity.Meizi;
import com.rinkosen.mygank.mvp.presenter.GankPresenter;
import com.rinkosen.mygank.mvp.view.GankView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;

/**
 * Created by rinkousen on 17/10/18.
 */

public class GankActivity extends BaseActivity<GankPresenter> implements GankView, BaseQuickAdapter.OnItemClickListener {

    private Meizi meizi;

    @BindView(R.id.gank_list)
    RecyclerView recyclerView;

    @BindView(R.id.iv_gank)
    ImageView mImageView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.smooth_bar)
    SmoothProgressBar mSmoothProgressBar;

    GankAdapter adapter;



    @Override
    protected int getLayout() {
        return R.layout.activity_gank;
    }

    @Override
    protected void initView() {
        super.initView();
        adapter = new GankAdapter(new ArrayList<Gank>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    protected void initPresenter() {
        presenter = new GankPresenter(this);
    }

    @Override
    protected void initParams(Bundle bundle) {
        super.initParams(bundle);
        meizi = (Meizi) bundle.getSerializable("meizi");
    }

    @Override
    protected void initData() {
        super.initData();
        mImageView.setImageDrawable(App.getgApp().getShareDrawble());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(meizi.publishedAt);
        presenter.getGankData(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void showProgress() {
        mSmoothProgressBar.progressiveStart();
    }

    @Override
    public void dismissProgress() {
        mSmoothProgressBar.progressiveStop();
    }

    @Override
    public void showGankData(List<Gank> data) {
        adapter.addData(data);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("gank", (Serializable) adapter.getData().get(position));
        startActivity(intent);
    }
}
