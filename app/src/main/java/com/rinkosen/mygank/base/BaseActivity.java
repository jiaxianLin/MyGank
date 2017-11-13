package com.rinkosen.mygank.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.rinkosen.mygank.mvp.presenter.BasePresenter;

import butterknife.ButterKnife;

/**
 * Created by rinkousen on 17/10/16.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    protected T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null){
            savedInstanceState = getIntent().getExtras();
        }

        if(savedInstanceState != null){
            initParams(savedInstanceState);
        }

        setContentView(getLayout());
        initPresenter();
        initView();
        initData();
    }

    protected abstract int getLayout();

    protected abstract void initPresenter();

    protected void initParams(Bundle bundle){

    }

    protected void initView(){
        ButterKnife.bind(this);
    }

    protected void initData(){

    }
}
