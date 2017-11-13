package com.rinkosen.mygank.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.rinkosen.mygank.R;
import com.rinkosen.mygank.base.App;
import com.rinkosen.mygank.base.BaseActivity;
import com.rinkosen.mygank.entity.Meizi;

import butterknife.BindView;

/**
 * Created by rinkousen on 17/10/30.
 */

public class MeiziActivity extends BaseActivity {

    @BindView(R.id.meizi_image)
    ImageView imageview;

    Meizi meizi;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int getLayout() {
        return R.layout.activity_meizi;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initParams(Bundle bundle) {
        super.initParams(bundle);
        meizi = (Meizi) bundle.getSerializable("meizi");
    }

    @Override
    protected void initView() {
        super.initView();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageview.setImageDrawable(App.getgApp().getShareDrawble());
        Glide.with(this).load(meizi.url).into(imageview);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
