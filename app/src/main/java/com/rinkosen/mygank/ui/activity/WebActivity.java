package com.rinkosen.mygank.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.rinkosen.mygank.R;
import com.rinkosen.mygank.base.BaseActivity;
import com.rinkosen.mygank.entity.Gank;
import com.rinkosen.mygank.mvp.presenter.WebPresenter;
import com.rinkosen.mygank.mvp.view.IWebView;

import butterknife.BindView;

/**
 * Created by rinkousen on 17/10/26.
 */

public class WebActivity extends BaseActivity<WebPresenter> implements IWebView {

    @BindView(R.id.web_view)
    WebView mWebView;

    @BindView(R.id.progressbar)
    NumberProgressBar mProgressBar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Gank gank;

    @Override
    protected int getLayout() {
        return R.layout.activity_web;
    }

    @Override
    protected void initPresenter() {
        presenter = new WebPresenter(this);
    }

    @Override
    protected void initParams(Bundle bundle) {
        super.initParams(bundle);
        gank = (Gank) bundle.getSerializable("gank");
    }

    @Override
    protected void initView() {
        super.initView();
        setSupportActionBar(toolbar);
        presenter.setWebViewSettings(mWebView, gank.url);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgress(int progress) {
        mProgressBar.setProgress(progress);
    }

    @Override
    public void setWebTitle(String title) {
        setTitle(title);
    }

    @Override
    public void loadFailure() {

    }
}
